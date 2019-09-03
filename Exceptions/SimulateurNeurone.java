import java.util.ArrayList;

/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
 class Position{
	private double x;
	private double y;

	public Position(double leX, double leY) {
		x = leX;
		y = leY;
	}

	public Position() {
		this(0, 0);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}

class Neurone{
	private Position position;
	private double signal;
	private double facteurAtt;
	private ArrayList<Neurone> connexions;

	public Neurone(Position laPosition, double leFacteurAtt) {
		signal = 0;
		position = laPosition;
		facteurAtt = leFacteurAtt;
		connexions = new ArrayList<Neurone>();
	}

	public Position getPosition() {
		return position;
	}

	public int getNbConnexions() {
		return connexions.size();
	}

	public Neurone getConnexion(int index) { //IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
		return connexions.get(index);
	}

	public double getAttenuation() {
		return facteurAtt;
	}

	public double getSignal() {
		return signal;
	}

	public void connexion(Neurone n) {
		connexions.add(n);
	}

	public void recoitStimulus(double stimulus) {
		signal = stimulus * facteurAtt;
		for (Neurone n : connexions) {
			n.recoitStimulus(signal);
		}
	}

	public String toString() {
		String text = "Le neurone en position ";
		text += position + " avec attenuation ";
		text += facteurAtt;
		if (connexions.isEmpty()) {
			return text + " sans connexion\n";
		}
		text += " en connexion avec\n";
		for (Neurone n : connexions) {
			text += "- un neurone en position " + n.getPosition() + "\n";
		}
		return text;
	}
}

class NeuroneCumulatif extends Neurone{

	public NeuroneCumulatif(Position position, double facteurAtt) {
		super(position, facteurAtt);
	}

	public void recoitStimulus(double stimulus) {
		super.recoitStimulus(
				(super.getSignal() + stimulus * super.getAttenuation())
				/ super.getAttenuation());
	}
}
/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/
public class SimulateurNeurone {

    public static void main(String[] args) {
        // TEST DE LA PARTIE 1
        System.out.println("Test de la partie 1:");
        System.out.println("--------------------");

        Position position1 = new Position(0, 1);
        Position position2 = new Position(1, 0);
        Position position3 = new Position(1, 1);

        Neurone neuron1 = new Neurone(position1, 0.5);
        Neurone neuron2 = new Neurone(position2, 1.0);
        Neurone neuron3 = new Neurone(position3, 2.0);

        neuron1.connexion(neuron2);
        neuron2.connexion(neuron3);
        neuron1.recoitStimulus(10);

        System.out.println("Signaux : ");
        System.out.println(neuron1.getSignal());
        System.out.println(neuron2.getSignal());
        System.out.println(neuron3.getSignal());

        System.out.println();
        System.out.println("Premiere connexion du neurone 1");
        System.out.println(neuron1.getConnexion(0));


        // FIN TEST DE LA PARTIE 1

        // TEST DE LA PARTIE 2
        System.out.println("Test de la partie 2:");
        System.out.println("--------------------");

        Position position5 = new Position(0, 0);
        NeuroneCumulatif neuron5 = new NeuroneCumulatif(position5, 0.5);
        neuron5.recoitStimulus(10);
        neuron5.recoitStimulus(10);
        System.out.println("Signal du neurone cumulatif  -> " + neuron5.getSignal());

        // FIN TEST DE LA PARTIE 2

        // TEST DE LA PARTIE 3
        System.out.println();
        System.out.println("Test de la partie 3:");
        System.out.println("--------------------");
        Cerveau cerveau = new Cerveau();

        // parametres de construction du neurone:
        // la  position et le facteur d'attenuation
        cerveau.ajouterNeurone(new Position(0,0), 0.5);
        cerveau.ajouterNeurone(new Position(0,1), 0.2);
        cerveau.ajouterNeurone(new Position(1,0), 1.0);

        // parametres de construction du neurone cumulatif:
        // la  position et le facteur d'attenuation
        cerveau.ajouterNeuroneCumulatif(new Position(1,1), 0.8);
        cerveau.creerConnexions();
        cerveau.stimuler(0, 10);

        System.out.println("Signal du 3eme neurone -> " + cerveau.sonder(3));
        System.out.println(cerveau);
        // FIN TEST DE LA PARTIE 3
    }
}
