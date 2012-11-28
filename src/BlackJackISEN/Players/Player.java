package BlackJackISEN.Players;

import BlackJackISEN.cartes.Hand;
import Exception.SaisieErroneeException;

/**
 * 
 * @author matthieu class abstrate generant la base d'un joueur de blackjack
 *         tout joueur a un nom et une main
 */
abstract public class Player {

	/**
	 * Variable permetant de savoir si le joueur n'a pas déjà refusé de piocher
	 * une fois
	 */
	protected boolean finish = true;

	protected String name;
	protected Hand hand = new Hand();

	/**
	 * Strategie en fonction du genre
	 */
	protected int genderStrategie = 0;
	/**
	 * alcoolemis qui agis sur diverce chose
	 */
	protected int ivresse = 0;

	/**
	 * Variable pour savoir si le joueur est sur une table ou non
	 */
	protected boolean playing = false;

	/**
	 * Constructeur de Player grace a son nom
	 * 
	 * @param name
	 *            nom du joueurs
	 * @throws SaisieErroneeException
	 *             Controle si le nom est non nul
	 */
	public Player(String name) throws SaisieErroneeException {
		try {
			this.name = name;
			SaisieErroneeException.controleJoueur(this.name);
		} catch (SaisieErroneeException e) {
			System.out.println(e + " probleme de nom nul");
			this.name = "Unknown";
		}
	}

	/**
	 * affiche la main du avec son score de main
	 * 
	 * @return main + score de main
	 */
	public String playerHand() {
		return name + " : " + hand.handView() + " = "
				+ String.valueOf(hand.handValue());
	}

	/**
	 * defini la strategie que le joueur emploira en fonction de la main du
	 * croupier
	 * 
	 * @param croupierHand
	 * @return true = je pioche false = je ne pioche pas
	 */
	public Boolean strategy(Hand croupierHand) {
		if (hand.handValue() <= (genderStrategie + (int) (Math.random()
				* ivresse / 20))
				&& finish == true) {
			return true;
		} else {
			finish = false;
			return false;
		}
	}

	/**
	 * Fonction permetant de faire parler des joueurs avec d'autre
	 * 
	 * @param player
	 *            joueur avec qui nous parlons
	 * @return une phrase qui vise un joueur
	 */
	abstract protected String speak(Player player);

	/**
	 * Incremente de +1 l'ivresse du joueur.
	 */
	public void ivresse() {
		ivresse++;
	}

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public void endHand() {
		hand.emptyHand();
	}

	public String Getname() {
		return name;
	}

	public float getIvresse() {
		return ivresse;
	}
}
