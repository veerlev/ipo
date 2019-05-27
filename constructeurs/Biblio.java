import java.util.ArrayList;

class Auteur {

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
	// Completer la classe Auteur ici
	private String nom;
	private boolean prime;
	
	public Auteur(String unNom, boolean estPrime) {
		nom = unNom;
		prime = estPrime;
	}
	
	public String getNom() {
		return nom;
	}
	
	public boolean getPrix() {
		return prime;
	}
}

class Oeuvre
{
 	// Completer la classe Oeuvre ici
	private String titre;
	private String langue;
	private final Auteur AUTEUR;
	
	public Oeuvre(String unTitre,Auteur  unAuteur, String uneLangue) {
		titre = unTitre;
		AUTEUR = unAuteur;
		langue = uneLangue;
	}
	
	public Oeuvre(String unTitre, Auteur  unAuteur) {
		titre = unTitre;
		AUTEUR = unAuteur;
		langue = "francais";
	}
	
	public String getTitre() {
		return titre;
	}
	
	public Auteur getAuteur() {
		return AUTEUR;
	}
	
	public void afficher() {
		System.out.println(titre + ", " + AUTEUR.getNom() + ", en " + langue);
	}
	
	public String getLangue() {
		return langue;
	}
}

class Exemplaire{
	private Oeuvre oeuvre;
	private static final String MESSAGE_NOUVEL = "Nouvel exemplaire -> ";
	private static final String MESSAGE_COPIE = "Copie d'un exemplaire de -> ";
	
	public Exemplaire(Oeuvre uneOeuvre, String message) {
		oeuvre = uneOeuvre;
		System.out.print(message);
		System.out.println(oeuvre.getTitre() + ", " 
				+ oeuvre.getAuteur().getNom() + ", en " + oeuvre.getLangue());
	}
	
	public Exemplaire(Oeuvre uneOeuvre) {
		this(uneOeuvre, MESSAGE_NOUVEL);
	}
	
	public Exemplaire(Exemplaire unExemplaire) {
		this(unExemplaire.getOeuvre(), MESSAGE_COPIE);
	}
	
	public Oeuvre getOeuvre() {
		return oeuvre;
	}
	
	public void afficher() {
		System.out.print("Un exemplaire de ");
		oeuvre.afficher();
	}
}

class Bibliotheque{
	private ArrayList<Exemplaire> exemplaires;
	private String nom;
	
	public Bibliotheque(String unNom) {
		nom = unNom;
		exemplaires = new ArrayList<Exemplaire>();
		System.out.println("La bibliothèque " + nom + " est ouverte !");
	}
	
	public String getNom() {
		return nom;
	}
	
	public int  getNbExemplaires() {
		return exemplaires.size();
	}
	
	public void stocker(Oeuvre oeuvre){
		exemplaires.add(new Exemplaire(oeuvre));
	}
	
    public void stocker(Oeuvre oeuvre, int nombre){
    	for (int i = 0; i < nombre; i++) {
    		stocker(oeuvre);
    	}
	}
    
    public ArrayList<Exemplaire> listerExemplaires(String langue) {
    	ArrayList<Exemplaire> liste = new ArrayList<Exemplaire>();
    	for(Exemplaire e : exemplaires) {
    		if (e.getOeuvre().getLangue().equals(langue)) {
    			liste.add(e);
    		}
    	}
    	return liste;
    }
    
    public ArrayList<Exemplaire> listerExemplaires(){
    	return exemplaires;
    }
    
    public int compterExemplaires(Oeuvre oeuvre) {
    	int nombre = 0;
    	for (Exemplaire e : exemplaires) {
    		if (e.getOeuvre().equals(oeuvre)) {
    			nombre++;
    		}
    	}
    	return nombre;
    }
    
    public void afficherAuteur(boolean prime) {
    	if (prime) {
    		afficherAuteur();
    	} else {
    		for (Exemplaire e : exemplaires) {
        		Auteur auteur = e.getOeuvre().getAuteur();
        		if (!auteur.getPrix()) {
        			System.out.println(auteur.getNom());
        		}
        	}
    	}
    }
    
    public void afficherAuteur() {
    	for (Exemplaire e : exemplaires) {
    		Auteur auteur = e.getOeuvre().getAuteur();
    		if (auteur.getPrix()) {
    			System.out.println(auteur.getNom());
    		}
    	}
    }
}

// completer les autres classes ici


/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
/*******************************************
 * Ce qui suit est propose' pour vous aider
 * dans vos tests, mais votre programme sera
 * teste' avec d'autres donnees.
 *******************************************/

public class Biblio {

    public static void afficherExemplaires(ArrayList<Exemplaire> exemplaires) {
        for (Exemplaire exemplaire : exemplaires) {
            System.out.print("\t");
            exemplaire.afficher();
        }
    }

    public static void main(String[] args) {
        // create and store all the exemplaries
        Auteur a1 = new Auteur("Victor Hugo", false);
        Auteur a2 = new Auteur("Alexandre Dumas", false);
        Auteur a3 = new Auteur("Raymond Queneau", true);

        Oeuvre o1 = new Oeuvre("Les Miserables", a1, "francais");
        Oeuvre o2 = new Oeuvre("L\'Homme qui rit", a1, "francais");
        Oeuvre o3 = new Oeuvre("Le Comte de Monte-Cristo", a2, "francais");
        Oeuvre o4 = new Oeuvre("Zazie dans le metro", a3, "francais");
        Oeuvre o5 = new Oeuvre("The count of Monte-Cristo", a2, "anglais");

        Bibliotheque biblio = new Bibliotheque("municipale");
        biblio.stocker(o1, 2);
        biblio.stocker(o2);
        biblio.stocker(o3, 3);
        biblio.stocker(o4);
        biblio.stocker(o5);

        // ...
        System.out.println("La bibliotheque " + biblio.getNom() + " offre ");
        afficherExemplaires(biblio.listerExemplaires());
        String langue = "anglais";
        System.out.println("Les exemplaires en " + langue + " sont  ");
        afficherExemplaires(biblio.listerExemplaires(langue));
        System.out.println("Les auteurs a succes sont  ");
        biblio.afficherAuteur();
        System.out.print("Il y a " + biblio.compterExemplaires(o3) + " exemplaires");
        System.out.println(" de  " + o3.getTitre());
    }
}
