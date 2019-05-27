class Souris {

    public static final int ESPERANCE_VIE_DEFAUT = 36;

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/

 private int poids;
    private String couleur;
    private int age;
    private int esperanceVie;
    private boolean clonee;
    public static final String MESSAGE_PAS_CLONEE = "Une nouvelle souris !";
    public static final String MESSAGE_CLONEE = "Clonage d'une souris !";
    public Souris(int lePoids, String laCouleur, int lAge,  int lEsperanceVie, boolean estClonee, String message) {
        poids = lePoids;
        couleur = laCouleur;
        age = lAge;
        esperanceVie = lEsperanceVie;
        clonee = estClonee;
        System.out.println(message);
    }
    
    public Souris(int lePoids, String laCouleur, int lAge,  int lEsperanceVie) {
        this(lePoids, laCouleur, lAge, lEsperanceVie, false, MESSAGE_PAS_CLONEE);
    }
    
    public Souris(int lePoids, String laCouleur, int lAge) {
        this(lePoids, laCouleur, lAge, ESPERANCE_VIE_DEFAUT, false, MESSAGE_PAS_CLONEE);
    }
    
    public Souris(int lePoids, String laCouleur) {
        this(lePoids, laCouleur, 0, ESPERANCE_VIE_DEFAUT, false, MESSAGE_PAS_CLONEE);
    }   
    
    public Souris(Souris autre) {
        this(
                autre.getPoids(),
                autre.getCouleur(),
                autre.getAge(), 
                autre.getEsperanceVie() * 4 / 5, 
                true, 
                MESSAGE_CLONEE
        ); 
    }
    
    public Souris() {
        this(0, "", 0, ESPERANCE_VIE_DEFAUT, false, MESSAGE_PAS_CLONEE);
    }
    
    public int getPoids() {
        return poids;
    }
    
    public String getCouleur() {
        return couleur;
    }
    
    public int getAge() {
        return age;
    }
    
    public int getEsperanceVie() {
        return esperanceVie;
    }
    
    public boolean isClonee() {
        return clonee;
    }
    
    public void setPoids(int lePoids) {
        poids = lePoids;
    }
    
    public void setCouleur(String laCouleur) {
        couleur = laCouleur;
    }
    
    public void setAge(int lAge) {
        age = lAge;
    }
    
    public void setEsperanceVie(int lEsperanceVie) {
        esperanceVie = lEsperanceVie;
    }
    
    public void setClonee(boolean estClonee) {
        clonee = estClonee;
    }
    
    public String toString() {
        String isCloned = clonee ? ", clonee, " : " ";
        return "Une souris " + couleur  + isCloned 
                + "de " + age + " mois et pesant " + poids + " grammes";
    }
    
    public void vieillir() {
        age++;
        if (clonee && age > esperanceVie / 2) {
            couleur = "verte";
        }       
    }
    
    public void evolue() {
        age = esperanceVie;
        if (clonee) {
            couleur = "verte";
        }
    }
}
/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/

public class Labo {

    public static void main(String[] args) {
        Souris s1 = new Souris(50, "blanche", 2);
        Souris s2 = new Souris(45, "grise");
        Souris s3 = new Souris(s2);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        s1.evolue();
        s2.evolue();
        s3.evolue();
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}
