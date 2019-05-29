/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
import java.util.ArrayList;

class Vehicule{
    private String nom;
    private double vitesseMaximale;
    private int poids;
    private int niveauCarburant;
    
    public Vehicule(String unNom, double uneVitesseMaximale, int unPoids, int unNiveauCarburant) {
        nom = unNom;
        vitesseMaximale = uneVitesseMaximale;
        poids = unPoids;
        niveauCarburant = unNiveauCarburant;
    }
    
    public Vehicule() {
        this("Anonyme", 130, 1000, 0);
    }

    public String toString() {
        return nom + " -> vitesse max = " + vitesseMaximale + " km/h, poids = " + poids + " kg";
    }

    public boolean meilleur(Vehicule autreVehicule) {
        return performance() > autreVehicule.performance();
    }

    public String getNom() {
         return nom;
     }
     
     public double getVitesseMax() {
         return vitesseMaximale;
     }
     
     public int getPoids() {
         return poids;
     }
     
     public int getCarburant() {
         return niveauCarburant;
     }

     private double performance(){
        return vitesseMaximale / poids;
     }
     
     public boolean estDeuxRoues() {
        return false;
    }
}

class Voiture extends Vehicule{
    private String categorie;
    
    public Voiture(String unNom, double uneVitesseMaximale, int unPoids, int unNiveauCarburant, String uneCategorie) {
        super(unNom, uneVitesseMaximale, unPoids, unNiveauCarburant);
        categorie = uneCategorie;
    }

    public String toString() {
        return super.getNom() + " -> vitesse max = " + super.getVitesseMax() + " km/h, poids = "
                + super.getPoids() + " kg, Voiture de " + categorie;
    }    
    
    public String getCategorie() {
        return categorie;
    }
}

class Moto extends Vehicule{

    private boolean possedeSidecar;
    
    public Moto(String unNom, double uneVitesseMaximale, int unPoids, int unNiveauCarburant, boolean possede) {
        super(unNom, uneVitesseMaximale, unPoids, unNiveauCarburant);
        possedeSidecar = possede;
    }
    
    public Moto(String unNom, double uneVitesseMaximale, int unPoids, int unNiveauCarburant) {
        this(unNom, uneVitesseMaximale, unPoids, unNiveauCarburant, false);
    }
    
    public String toString() {
        String description = super.getNom() + "-> vitesse max = " + super.getVitesseMax() + " km/h, poids = ";
        description += super.getPoids() + " kg, Moto";
        if (possedeSidecar) {
            description += ", avec sidecar";
        }
        return description;
    }
    
    public boolean estDeuxRoues() {
        return !possedeSidecar;
    }
}

class GrandPrix extends Rallye{
    private ArrayList<Vehicule> vehicules;
    
    public GrandPrix() {
        vehicules = new ArrayList<Vehicule>();
    }    
    
    public void ajouter(Vehicule vehicule) {
        vehicules.add(vehicule);
    }

    public boolean check() {
        if (vehicules.size() > 1) {
            if (vehicules.get(0).estDeuxRoues()) {
                for (int i = 1; i < vehicules.size(); i++) {
                    if (!vehicules.get(i).estDeuxRoues()){
                        return false;                       
                    }
                } 
            } else {
                for (int i = 1; i < vehicules.size(); i++) {
                    if (vehicules.get(i).estDeuxRoues()){
                        return false;                       
                    }
                }
            }
        }
        return true;
    }
    
    void run(int tours) {
        if (!check()) {
            System.out.println("Pas de Grand Prix");
            return;
        }
        ArrayList<Vehicule> arrivees = new ArrayList<Vehicule>();
        for (Vehicule vehicule : vehicules) {
            if (vehicule.getCarburant() - tours > 0) {
                arrivees.add(vehicule);
            }
        }
        if (!arrivees.isEmpty()) {
            Vehicule gagnant = arrivees.get(0);
            if (arrivees.size() > 1) {
                for (int i = 1; i < arrivees.size(); i++) {
                    if (arrivees.get(i).meilleur(gagnant)) {
                        gagnant = arrivees.get(i);
                    }
                }
            }
            System.out.println("Le gagnant du grand prix est :\n" + 
                    gagnant.toString());
        } else {
            System.out.println("Elimination de tous les vehicules\r\n");
        }
        
    }
}

abstract class Rallye{
    public abstract boolean check();
}
/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/
public class Course {

    public static void main(String[] args) {

        // PARTIE 1
        System.out.println("Test partie 1 : ");
        System.out.println("----------------");
        Vehicule v0 = new Vehicule();
        System.out.println(v0);

        // les arguments du constructeurs sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        Vehicule v1 = new Vehicule("Ferrari", 300.0, 800, 30);
        Vehicule v2 = new Vehicule("Renault Clio", 180.0, 1000, 20);
        System.out.println();
        System.out.println(v1);
        System.out.println();
        System.out.println(v2);

        if (v1.meilleur(v2)) {
            System.out.println("Le premier vehicule est meilleur que le second");
        } else {
            System.out.println("Le second vehicule est meilleur que le premier");
        }
        // FIN PARTIE 1

        // PARTIE2
        System.out.println();
        System.out.println("Test partie 2 : ");
        System.out.println("----------------");

        // les trois premiers arguments sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        // le dernier argument indique la presence d'un sidecar
        Moto m1 = new Moto("Honda", 200.0, 250, 15, true);
        Moto m2 = new Moto("Kawasaki", 280.0, 180, 10);
        System.out.println(m1);
        System.out.println();
        System.out.println(m2);
        System.out.println();

        // les trois premiers arguments sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        // le dernier argument indique la categorie
        Voiture vt1 = new Voiture("Lamborghini", 320, 1200, 40, "course");
        Voiture vt2 = new Voiture("BMW", 190, 2000, 35, "tourisme");
        System.out.println(vt1);
        System.out.println();
        System.out.println(vt2);
        System.out.println();
        // FIN PARTIE 2

        // PARTIE 3
        System.out.println();
        System.out.println("Test partie 3 : ");
        System.out.println("----------------");

        GrandPrix gp1 = new GrandPrix();
        gp1.ajouter(v1);
        gp1.ajouter(v2);
        System.out.println(gp1.check());

        GrandPrix gp2 = new GrandPrix();
        gp2.ajouter(vt1);
        gp2.ajouter(vt2);
        gp2.ajouter(m2);
        System.out.println(gp2.check());

        GrandPrix gp3 = new GrandPrix();
        gp3.ajouter(vt1);
        gp3.ajouter(vt2);
        gp3.ajouter(m1);
        System.out.println(gp3.check());

        GrandPrix gp4 = new GrandPrix();
        gp4.ajouter(m1);
        gp4.ajouter(m2);
        System.out.println(gp4.check());
        // FIN PARTIE 3

        // PARTIE 4
        System.out.println();
        System.out.println("Test partie 4 : ");
        System.out.println("----------------");
        GrandPrix gp5 = new GrandPrix();
        gp5.ajouter(vt1);
        gp5.ajouter(vt2);

        System.out.println("Premiere course :");
        gp5.run(11);
        System.out.println();

        System.out.println("Deuxieme  course :");
        gp3.run(40);
        System.out.println();

        System.out.println("Troisieme  course :");
        gp2.run(11);
        // FIN PARTIE 4
    }
}
