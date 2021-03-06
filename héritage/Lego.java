/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
import java.util.ArrayList;

class Construction{
    private int noMaximal;
    private ArrayList<Composant> composants;
    
    public Construction(int unNoMaximal) {
        noMaximal = unNoMaximal;
        composants = new ArrayList<Composant>();
    }
    
    public int taille() {
        return composants.size();
    }
    
    public int tailleMax() {
        return noMaximal;
    }
    
    public void ajouterComposant(Piece piece, int quantite) {
        if (composants.size() < noMaximal) {
            composants.add(new Composant(piece, quantite));
        } else {
            System.out.println("Ajout de composant impossible");
        }
    }
    
    public String toString() {
        String description = "";
        for (Composant c : composants) {
            description += c.getPiece().toString() + " (quantite " +  c.getQuantite() + ")\n";                  
        }
        return description;
    }
    
}

class Composant{
    private Piece piece;
    private int quantite;
    
    public Composant(Piece unePiece, int uneQuantite) {
        piece = unePiece;
        quantite = uneQuantite;
    }
    
    public Piece getPiece() {
        return piece;
    }
    
    public int getQuantite() {
        return quantite;
    }
    
}

class Piece{
    private String nom;
    
    public Piece(String unNom) {
        nom = unNom;
    }
    
    public String getNom() {
        return nom;
    }
    
    public String toString() {
        return nom;
    }
}

class Simple extends Piece{
    private String orientation;
    
    public Simple(String unNom, String uneOrientation) {
        super(unNom);
        orientation = uneOrientation;
    }
    
    public Simple(String unNom) {
        super(unNom);
        orientation = "aucune";
    }
    
    public String getOrientation() {
        return orientation;
    }
    
    public String toString() {
        return super.getNom() + (orientation.equals("aucune") ? "" : " " + orientation);
    }
}

class Composee extends Piece{
    private ArrayList<Piece> pieces;
    private int noMaximal;
        
    public Composee(String unNom, int unNoMaximal) {
        super(unNom);
        pieces = new ArrayList<Piece>();
        noMaximal = unNoMaximal;
    }
    
    public int taille() {
        return pieces.size();
    }
    
    public int tailleMax() {
        return noMaximal;
    }
    
    public void construire(Piece piece) {
        if (pieces.size() < noMaximal) {
            pieces.add(piece);
        } else {
            System.out.println("Construction impossible");
        }
    }
    
    public String toString() {
        String description = super.getNom() + " (";
        for (Piece p : pieces) {
            description += p.toString() + ",";
        }
        return description.substring(0, description.length() - 1) + ")";
    }
}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
/*******************************************
 * Ce qui suit est propose' pour vous aider
 * dans vos tests, mais votre programme sera
 * teste' avec d'autres donnees.
 *******************************************/

class Lego {

    public static void main(String[] args) {
        // declare un jeu de construction de 10 pieces
        Construction maison = new Construction(10);

        // ce jeu a pour premier composant: 59 briques standard
        // une brique standard a par defaut "aucune" comme orientation
        maison.ajouterComposant(new Simple("brique standard"), 59);

        // declare une piece dont le nom est "porte", composee de 2 autres pieces
        Composee porte = new Composee("porte", 2);

        // cette piece composee est constituee de deux pieces simples:
        // un cadran de porte orient'e a gauche
        // un battant orient'e a gauche
        porte.construire(new Simple("cadran porte", "gauche"));
        porte.construire(new Simple("battant", "gauche"));

        // le jeu a pour second composant: 1 porte
        maison.ajouterComposant(porte, 1);

        // déclare une piece composee de 3 autres pieces dont le nom est "fenetre"
        Composee fenetre = new Composee("fenetre", 3);

        // cette piece composee est constitu'ee des trois pieces simples:
        // un cadran de fenetre (aucune orientation)
        // un volet orient'e a gauche
        // un volet orient'e a droite
        fenetre.construire(new Simple("cadran fenetre"));
        fenetre.construire(new Simple("volet", "gauche"));
        fenetre.construire(new Simple("volet", "droit"));

        // le jeu a pour troisieme composant: 2 fenetres
        maison.ajouterComposant(fenetre, 2);

        // affiche tous les composants composants (nom de la piece et quantit'e)
        // pour les pieces compos'ees : affiche aussi chaque piece les constituant
        System.out.println("Affichage du jeu de construction : ");
        System.out.println(maison);
    }
}
