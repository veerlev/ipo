class Souris {

    public static final int ESPERANCE_VIE_DEFAUT = 36;

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
/*
 * Le code fourni et reproduit plus bas :
— « construit » des souris ;
— les fait évoluer au moyen d’une méthode evolue ;
— affiche les souris avant et après les avoir fait évoluer.
Le corps de la classe Souris manque et c’est ce qu’il vous est demandé d’écrire.
Une souris est caractérisée par son poids en grammes (un int), sa couleur (une
String), son âge en mois (un int), son espérance de vie (un int) et une indication sur le fait qu’elle soit clonée ou pas (un booléen).
Ces attributs seront nommés respectivement : poids, age, couleur, esperanceVie
et clonee.
Par ailleurs, les méthodes publiques de la classe Souris sont :
— des constructeurs conformes à la méthode main fournie, avec l’ordre suivant pour les paramètres : le poids, la couleur, l’âge et l’espérance de vie.
Ces deux derniers paramètres ont pour valeur par défaut zéro et 36 respectivement. La valeur 36 est stockée dans une constante fournie que vous
utiliserez en écrivant ESPERANCE_VIE_DEFAUT. Les constructeurs afficheront le message Une nouvelle souris ! ;
— un constructeur de copie qui doit afficher le message Clonage d’une souris ! ;
une souris clonée a les mêmes caractéristiques que la souris d’origine, sauf
son espérance de vie qui est moindre : les 4 cinquième de celle de la souris
d’origine ;
— une méthode toString() produisant une représentation d’une Souris
respectant strictement le format suivant :
Une souris <couleur>[, clonee,] de <age> mois et pesant <poids>
grammes (sur une seule ligne)
2
où <age> est à remplacer par l’âge de la souris et <poids> par son
poids. Le bout de phrase « , clonee, » ne sera affiché que si la souris
a été clonée ;
— une méthode vieillir qui augmentera d’une unité l’âge de la souris. Si
la souris est clonée, elle doit devenir verte si elle atteint un âge supérieur
à la moitié de son espérance de vie ; même si elle n’est pas appelée explicitement dans la méthode main(), cette méthode doit être publique ; elle
sera testée ;
— et une méthode evolue faisant vieillir la souris depuis son âge courant
jusqu’à son espérance de vie.
Tous les affichages demandés se feront sur le terminal et seront terminés par un
saut de ligne. Un exemple de déroulement est fourni plus bas.
1.2 Exemple de déroulement
Une nouvelle souris !
Une nouvelle souris !
Clonage d’une souris !
Une souris blanche de 2 mois et pesant 50 grammes
Une souris grise de 0 mois et pesant 45 grammes
Une souris grise, clonee, de 0 mois et pesant 45 grammes
Une souris blanche de 36 mois et pesant 50 grammes
Une souris grise de 36 mois et pesant 45 grammes
Une souris verte, clonee, de 28 mois et pesant 45 grammes
 * 
 * */
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