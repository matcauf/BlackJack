package Exception;

public class SaisieErroneeException extends Exception {

	/**
	 * class pour les exceptions personnalisées
	 */
	private static final long serialVersionUID = 1L;

	

	public SaisieErroneeException(String s) {
		super(s);
	}

	/**
	 * gere si le nombre de joueurs ne depasse pas 7
	 * 
	 * @param nombre
	 * @throws SaisieErroneeException
	 */
	public static void controleJoueur(int nombre) throws SaisieErroneeException {
		if (nombre > 7 || nombre < 0) {
			throw new SaisieErroneeException(
					"Trop de joueurs pour une table <= 7");
		}
	}

	/**
	 * controle si le nom du joueur et different de ""
	 * 
	 * @param name
	 * @throws SaisieErroneeException
	 */
	public static void controleJoueur(String name)
			throws SaisieErroneeException {
		if (name.equals("")) {
			throw new SaisieErroneeException("pas de nom");
		}
	}

	/**
	 * controle si le tapis n'ai pas trop faible
	 * 
	 * @param tapis
	 * @throws SaisieErroneeException
	 */
	public static void controleTapis(double tapis)
			throws SaisieErroneeException {
		if (tapis < 20) {
			throw new SaisieErroneeException(
					"cette table ne convient pas au casino");
		}
	}

	/**
	 * controle si la mise minimale n'est pas trop elevée pour la table
	 * 
	 * @param mise
	 * @param tapis
	 * @throws SaisieErroneeException
	 */
	public static void controleTapis(int mise, double tapis)
			throws SaisieErroneeException {
		if (mise > (int) tapis / 4) {
			throw new SaisieErroneeException(
					"Ce casino est trop cher pour vous");
		}
	}

	/**
	 * Controle si la mise ou la table est superieur à 1
	 * 
	 * @param mise
	 * @throws SaisieErroneeException
	 */
	public static void controleNombre(int mise) throws SaisieErroneeException {
		if (mise < 1) {
			throw new SaisieErroneeException("mise mini trop petite");
		}
	}

	/**
	 * Controle si le sabot à au moins un jeu
	 * 
	 * @param jeux
	 * @throws SaisieErroneeException
	 */
	public static void controleSabot(int jeux) throws SaisieErroneeException {
		if (jeux < 1) {
			throw new SaisieErroneeException("il faut au moins un jeu");
		}
	}

	/**
	 * Controle si le nombre de cartes est de au moins de 52 cartes
	 * 
	 * @param cartes
	 * @throws SaisieErroneeException
	 */
	public static void controleDeck(int cartes) throws SaisieErroneeException {
		if (cartes < 52) {
			throw new SaisieErroneeException("il faut au moins 52 cartes");
		}
	}

}
