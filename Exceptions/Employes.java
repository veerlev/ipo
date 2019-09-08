/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

 class Employe{
 	private final String nom;
 	private double revenu;
 	private int taux;
 	private double montantPrime;
 	public static final int TAUX_DEFAUT = 100;

 	public Employe(String leNom, double leRevenu, int leTaux) {
 		nom = leNom;
 		revenu = leRevenu;
 		montantPrime = 0;
 		if (leTaux >= 10 && leTaux <= 100) {
 			taux = leTaux;
 		} else {
 			if (leTaux < 10) {
 				taux = 10;
 			} else {
 				taux = 100;
 			}
 		}
 		System.out.print("Nous avons un nouvel employé : " + nom + ",");
 	}

 	public Employe(String leNom, double leRevenu) {
 		this(leNom, leRevenu, TAUX_DEFAUT);
 	}

 	public double revenuAnnuel() {
 		return 12 * revenu * (taux / 100.0)  + montantPrime;
 	}

 	public void demandePrime() {
 		int maxTentativesNb = 5;
 		Scanner in = new Scanner(System.in);
 		while (maxTentativesNb > 0) {
 			System.out.println("Montant de la prime souhaitée par " + nom + " ?");
 			double tentativePrime = revenu;
 			try {
 				tentativePrime = in.nextDouble();
 				if (tentativePrime > 0 && tentativePrime < revenu * 2.0 / 100) {
 					montantPrime = tentativePrime;
 					in.close();
 					return;
 				} else {
 					System.out.println("Trop cher!");
 				}
 			} catch(InputMismatchException e) {
 				System.out.println("Vous devez introduire un nombre!");
 				in.nextLine();
 			}
 			maxTentativesNb--;
 		}
 		in.close();
 	}

 	public String toString() {
 		String text = nom + " : \n" + "  Taux d'occupation : " + taux + "%. Salaire annuel : ";
 		text += String.format("%.2f", this.revenuAnnuel());
 		text += " francs";
 		if (montantPrime > 0) {
 			text +=", Prime : " + String.format("%.2f", montantPrime);
 		}
 		text += ".\n";
 		return text;
 	}
 }

 class Manager extends Employe{
	private int nombreJours;
	private int nombreClients;
	public static final int FACTEUR_GAIN_CLIENT = 500;
	public static final int FACTEUR_GAIN_VOYAGE = 100;

	public Manager(String nom, double revenu, int nbj, int nbc, int taux) {
		super(nom, revenu, taux);
		nombreJours = nbj;
		nombreClients = nbc;
		System.out.println(" c'est un manager.");
	}

	public Manager(String nom, double revenu, int nbj, int nbc) {
		this(nom, revenu, nbj, nbc, TAUX_DEFAUT);
	}

	public double revenuAnnuel() {
		return super.revenuAnnuel() + FACTEUR_GAIN_CLIENT * nombreClients
				+ FACTEUR_GAIN_VOYAGE * nombreJours;
	}

	public String toString() {
		return super.toString() + "  A voyagé " + nombreJours + " jours et apporté "
				+ nombreClients + " nouveaux clients.\n";
	}
}

class Testeur extends Employe{
	private int nombreErreurs;
	public static final int FACTEUR_GAIN_ERREURS = 10;

	public Testeur(String nom, double revenu, int nbe, int taux) {
		super(nom, revenu, taux);
		nombreErreurs = nbe;
		System.out.println(" c'est un testeur.");
	}

	public Testeur(String nom, double revenu, int nbe) {
		this(nom, revenu, nbe, TAUX_DEFAUT);
	}

	public double revenuAnnuel() {
		return super.revenuAnnuel() + nombreErreurs * FACTEUR_GAIN_ERREURS;
	}

	public String toString() {
		return super.toString() + "  A corrigé " + nombreErreurs + " erreurs.";
	}
}

class Programmeur extends Employe{
	private int nombreProjets;
	public static final int FACTEUR_GAIN_PROJETS = 200;

	public Programmeur(String nom, double revenu, int nbp, int taux) {
		super(nom, revenu, taux);
		nombreProjets = nbp;
		System.out.println(" c'est un programmeur.");
	}

	public Programmeur(String nom, double revenu, int nbp) {
		this(nom, revenu, nbp, TAUX_DEFAUT);
	}

	public double revenuAnnuel() {
		return super.revenuAnnuel() + nombreProjets * FACTEUR_GAIN_PROJETS;
	}

	public String toString() {
		return super.toString() + "  A mené à  bien " + nombreProjets + " projets";
	}
}
/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
class Employes {
    public static void main(String[] args) {

        List<Employe> staff = new ArrayList<Employe>();

        // TEST PARTIE 1:

        System.out.println("Test partie 1 : ");
        staff.add(new Manager("Serge Legrand", 7456, 30, 4 ));
        staff.add(new Programmeur("Paul Lepetit" , 6456, 3, 75 ));
        staff.add(new Testeur("Pierre Lelong", 5456, 124, 50 ));

        System.out.println("Affichage des employés : ");
        for (Employe modele : staff) {
            System.out.println(modele);
        }
        // FIN TEST PARTIE 1
        // TEST PARTIE 2
        System.out.println("Test partie 2 : ");

        staff.get(0).demandePrime();

        System.out.println("Affichage après demande de prime : ");
        System.out.println(staff.get(0));

        // FIN TEST PARTIE 2
    }
}
