import java.util.ArrayList;
import java.util.Random;

/*******************************************
 * Completez le programme à partir d'ici.
 *******************************************/
class Postulant{
	private String nom;
	private int nbElecteurs;
	
	public Postulant(String leNom, int leNbElecteurs) {
		nom = leNom;
		nbElecteurs = leNbElecteurs;
	}
	
	public Postulant(String leNom) {
		this(leNom, 0);
	}
	
	public Postulant(Postulant postulant) {
		this(postulant.getNom(), postulant.getVotes());
	}
	
	public void elect() {
		nbElecteurs++;
	}
	
	public void init() {
		nbElecteurs = 0;
	}
	
	public int getVotes() {
		return nbElecteurs;
	}
	
	public String getNom() {
		return nom;
	}
}

class Scrutin{
	private ArrayList<Postulant> postulants;
	private int nbVotantsMax;
	private int date;
	private ArrayList<Vote> votes;
	
	public Scrutin(ArrayList<Postulant> lesPostulants, int leNbVotantsMax, int laDate, boolean initialiser) {
		nbVotantsMax = leNbVotantsMax;
		date = laDate;
		votes = new ArrayList<Vote>();
		postulants = new ArrayList<Postulant>();
		if (lesPostulants != null) {
			for (Postulant p : lesPostulants) {
				Postulant postulant = new Postulant(p);
				if (initialiser) {
					postulant.init();
				}
				postulants.add(postulant);
			}			
		}
	}
	
	public Scrutin(ArrayList<Postulant> lesPostulants, int leNbVotantsMax, int laDate) {
		this(lesPostulants, leNbVotantsMax, laDate, true);
	}
	
	public int calculerVotants() {
		int resultat = 0;
		for (Postulant p : postulants) {
			resultat += p.getVotes();
		}
		return resultat;
	}
	
	public void compterVotes() {
		for (Vote vote : votes) {
			if (!vote.estInvalide()) {
				for (Postulant p : postulants) {
					if (vote.getPostulant().equals(p.getNom())) {
						p.elect();
					}
				}
			}
		}
	}
	
	public void simuler(double taux, int dateVote) {
		int nbVotants = (int) (nbVotantsMax * taux);
		for (int i = 0; i < nbVotants; i++) {
			int candNum = Utils.randomInt(postulants.size());
			boolean signe = i % 2 == 1;
			if (i % 3 == 0) {
				votes.add(new BulletinElectronique(postulants.get(candNum).getNom(), dateVote, date));
			}
			if (i % 3 == 1) {
				votes.add(new BulletinPapier(postulants.get(candNum).getNom(), dateVote, date, signe));
			}
			if (i % 3 == 2) {
				votes.add(new BulletinCourrier(postulants.get(candNum).getNom(), dateVote, date, signe));
			}
			System.out.println(votes.get(i));
		}
	}
	
	public String gagnant() {		
		Postulant gagnant = postulants.get(0);
		int max = gagnant.getVotes();
		for (int i = 1; i < postulants.size(); i++) {
			Postulant autre = postulants.get(i);
			if (max <= autre.getVotes()) {
				gagnant = autre;
				max = autre.getVotes();
			}
		}
		return gagnant.getNom();
	}

	public void resultats() {
		int nbEffectif = calculerVotants();
		if (nbEffectif == 0) {
			System.out.println("Scrutin annulé, pas de votants");
			return;
		}
		System.out.format("Taux de participation -> %.1f pour cent\n",
					nbEffectif * 100.0 / nbVotantsMax); 
		System.out.println("Nombre effectif de votants -> " + nbEffectif);
		System.out.println("Le chef choisi est -> " + gagnant());
		System.out.println("\nRépartition des électeurs ");  
		for (Postulant p : postulants) {
			System.out.format("%s -> %.1f pour cent des électeurs\n", 
					p.getNom(), p.getVotes() * 100.0 / nbEffectif);
		}
		System.out.println();
	}
		
}

abstract class Vote{
	private String nomPostulant;
	private int date;
	private int dateLimite;
	
	public Vote(String leNom, int laDate, int laDateLimite) {
		nomPostulant = leNom;
		date = laDate;
		dateLimite = laDateLimite;
	}
	
	abstract public boolean estInvalide(); 
	
	public int getDate() {
		return date;
	}
	
	public int getDateLimite() {
		return dateLimite;
	}
	
	public String getPostulant() {
		return nomPostulant;
	}
	
	public String toString() {
		return " pour " + nomPostulant + " -> " + (estInvalide() ? "invalide" : "valide"); 
	}
	
}

class BulletinPapier extends Vote{	
	private boolean estSigne;
	public BulletinPapier(String nom, int date, int dateLimite, boolean leEstSigne) {
		super(nom, date, dateLimite);
		estSigne = leEstSigne;
	}
	public boolean estInvalide() {
		return !estSigne;
	}
	
	public String toString() {
		return "vote par bulletin papier" + super.toString();
	}
}
/*******************************************
 * Ne pas modifier les parties fournies
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/

class Utils {

    private static final Random RANDOM = new Random();

    // NE PAS UTILISER CETTE METHODE DANS LES PARTIES A COMPLETER
    public static void setSeed(long seed) {
        RANDOM.setSeed(seed);
    }

    // génère un entier entre 0 et max (max non compris)
    public static int randomInt(int max) {
        return RANDOM.nextInt(max);
    }
}

/**
 * Classe pour tester la simulation
 */

class Votation {

    public static void main(String args[]) {
        Utils.setSeed(20000);
        // TEST 1
        System.out.println("Test partie I:");
        System.out.println("--------------");

        ArrayList<Postulant> postulants = new ArrayList<Postulant>();
        postulants.add(new Postulant("Tarek Oxlama", 2));
        postulants.add(new Postulant("Nicolai Tarcozi", 3));
        postulants.add(new Postulant("Vlad Imirboutine", 2));
        postulants.add(new Postulant("Angel Anerckjel", 4));

        // 30 -> nombre maximal de votants
        // 15 jour du scrutin
        Scrutin scrutin = new Scrutin(postulants, 30, 15, false);

        scrutin.resultats();

        // FIN TEST 1

        // TEST 2
        System.out.println("Test partie II:");
        System.out.println("---------------");

        scrutin = new Scrutin(postulants, 20, 15);
        // tous les bulletins passent le check de la date
        // les parametres de simuler sont dans l'ordre:
        // le pourcentage de votants et le jour du vote
        scrutin.simuler(0.75, 12);
        scrutin.compterVotes();
        scrutin.resultats();

        scrutin = new Scrutin(postulants, 20, 15);
        // seuls les bulletins papier non courrier passent
        scrutin.simuler(0.75, 15);
        scrutin.compterVotes();
        scrutin.resultats();

        scrutin = new Scrutin(postulants, 20, 15);
        // les bulletins electroniques ne passent pas
        scrutin.simuler(0.75, 15);
        scrutin.compterVotes();
        scrutin.resultats();
        //FIN TEST 2

    }
}
