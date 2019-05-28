import java.util.ArrayList;

class Timbre {

    public static final int ANNEE_COURANTE = 2016;
    public static final int VALEUR_TIMBRE_DEFAUT = 1;
    public static final String PAYS_DEFAUT = "Suisse";
    public static final String CODE_DEFAUT = "lambda";

    public static final int BASE_1_EXEMPLAIRES = 100;
    public static final int BASE_2_EXEMPLAIRES = 1000;
    public static final double PRIX_BASE_1 = 600;
    public static final double PRIX_BASE_2 = 400;
    public static final double PRIX_BASE_3 = 50;

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
    private String code;
    private int annee;
    private String pays;
    private double valeurFaciale;

    public Timbre() {
        code = CODE_DEFAUT;
        annee = ANNEE_COURANTE;
        pays = PAYS_DEFAUT;
        valeurFaciale = VALEUR_TIMBRE_DEFAUT;
    }

    public Timbre(String unCode) {
        this(unCode, ANNEE_COURANTE, PAYS_DEFAUT, VALEUR_TIMBRE_DEFAUT);
    }

    public Timbre(String unCode, int uneAnnee) {
        this(unCode, uneAnnee, PAYS_DEFAUT, VALEUR_TIMBRE_DEFAUT);
    }

    public Timbre(String unCode, int uneAnnee, String unPays) {
        this(unCode, uneAnnee, unPays, VALEUR_TIMBRE_DEFAUT);
    }

    public Timbre(String unCode, int uneAnnee, String unPays, double uneValeur) {
        code = unCode;
        annee = uneAnnee;
        pays = unPays;
        valeurFaciale = uneValeur;

    }

    public double vente() {
        return ANNEE_COURANTE - annee < 5 ? valeurFaciale : valeurFaciale * 2.5 * (ANNEE_COURANTE - annee);
    }

    public String toString() {
        return "Timbre de code " + code + " datant de " + annee +" (provenance " + pays + ") " +
                "ayant pour valeur faciale " +  valeurFaciale + " francs";
    }

    public int age() {
        return ANNEE_COURANTE - annee;
    }

    public String getCode() {
        return code;
    }

    public int getAnnee() {
        return annee;
    }

    public String getPays(){
        return pays;
    }

    public double getValeurFaciale() {
        return valeurFaciale;
    }
}

class Rare extends Timbre{
    private int nombre;

    public Rare(int unNombre) {
        this(CODE_DEFAUT, ANNEE_COURANTE, PAYS_DEFAUT, VALEUR_TIMBRE_DEFAUT, unNombre);
    }

    public Rare(String unCode, int unNombre) {
        this(unCode, ANNEE_COURANTE, PAYS_DEFAUT, VALEUR_TIMBRE_DEFAUT, unNombre);
    }
    
    public Rare(String unCode, int uneAnnee, int unNombre) {
        this(unCode, uneAnnee, PAYS_DEFAUT, VALEUR_TIMBRE_DEFAUT, unNombre);
    }

    public Rare(String unCode, int uneAnnee, String unPays, int unNombre) {
        this (unCode, uneAnnee, unPays, VALEUR_TIMBRE_DEFAUT, unNombre);
    }


    public Rare(String unCode, int uneAnnee, String unPays, double uneValeur, int unNombre) {
        super(unCode, uneAnnee, unPays, uneValeur);
        nombre = unNombre;
    }

    public double vente() {
        int age = ANNEE_COURANTE - this.getAnnee();
        return nombre < BASE_1_EXEMPLAIRES ? PRIX_BASE_1 * age / 10.0
                : (nombre <= BASE_2_EXEMPLAIRES ?
                PRIX_BASE_2  * age / 10.0
                : PRIX_BASE_3 * age / 10.0);
    }

    public int getExemplaires() {
        return nombre;
    }

    public String toString() {
        return super.toString() +
                "\nNombre d'exemplaires -> " + nombre;
    }

}

class Commemoratif extends Timbre {
    public Commemoratif() {
        super(CODE_DEFAUT, ANNEE_COURANTE, PAYS_DEFAUT, VALEUR_TIMBRE_DEFAUT);
    }

    public Commemoratif(String unCode, int uneAnnee) {
        super(unCode, uneAnnee, PAYS_DEFAUT, VALEUR_TIMBRE_DEFAUT);
    }

    public Commemoratif(String unCode, int uneAnnee, String unPays) {
        super(unCode, uneAnnee, unPays, VALEUR_TIMBRE_DEFAUT);
    }

    public Commemoratif(String unNom, int uneAnnee, String unPays, double uneValeur) {
        super(unNom, uneAnnee, unPays, uneValeur);
    }

    public String toString() {
        return super.toString() + "\nTimbre celebrant un evenement";
    }

    public double vente() {
        return super.vente() * 2;
    }
}
    
/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/

class Philatelie {

    public static void main(String[] args) {

        // ordre des parametres: nom, annee d'emission, pays, valeur faciale,
        // nombre d'exemplaires
        Rare t1 = new Rare("Guarana-4574", 1960, "Mexique", 0.2, 98);

        // ordre des parametres: nom, annee d'emission, pays, valeur faciale
        Commemoratif t2 = new Commemoratif("700eme-501", 2002, "Suisse", 1.5);
        Timbre t3 = new Timbre("Setchuan-302", 2004, "Chine", 0.2);

        Rare t4 = new Rare("Yoddle-201", 1916, "Suisse", 0.8, 3);


        ArrayList<Timbre> collection = new ArrayList<Timbre>();

        collection.add(t1);
        collection.add(t2);
        collection.add(t3);
        collection.add(t4);

        for (Timbre timbre : collection) {
            System.out.println(timbre);
            System.out.println("Prix vente : " + timbre.vente() + " francs");
            System.out.println();
        }
    }
}

