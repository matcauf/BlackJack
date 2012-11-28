package BlackJackISEN.cartes;

import java.util.ArrayList;
import java.util.List;

import Exception.SaisieErroneeException;

/**
 * class gerant le sabot du jeux sa creation le tirage de carte et le changement
 * de deck
 * 
 * 
 * @author matthieu
 * 
 */
public class Sabot {

	/**
	 * Le sabot qui composé de divers decks
	 */
	private List<Deck> sabot = new ArrayList<Deck>();

	/**
	 * Deck Temporaire
	 */
	private Deck currentDeck = new Deck();

	/**
	 * Constructeur du sabot
	 * 
	 * @param nombre_de_cartes
	 *            nombres de cartes par jeux
	 * @param games
	 *            dans le sabot
	 */
	public Sabot(int nombre_de_cartes, int games) {
		try {
			SaisieErroneeException.controleSabot(games);
			initSabot(nombre_de_cartes, games);
		} catch (SaisieErroneeException e) {
			initSabot(nombre_de_cartes, 1);
		} finally {
			currentDeck = getNewDeck();
		}
	}

	/**
	 * initialize le Sabot
	 * 
	 * @param nombre_de_cartes
	 *            nombre de cartes dans le jeux
	 * @param games
	 *            nombre de deck
	 */
	private void initSabot(int nombre_de_cartes, int games) {
		for (int i = 0; i < games; i++) {
			sabot.add(new Deck(nombre_de_cartes));
		}
	}

	/**
	 * Prend un jeu dans le sabot et remet le precedant en dessou du sabot
	 * 
	 * @return un jeu
	 */
	private Deck getNewDeck() {
		Deck clone = new Deck();
		for (Card card : sabot.get(0).getDeck()) {
			clone.addCard(card);
		}
		sabot.add(sabot.get(0));
		sabot.remove(0);
		return clone;
	}

	/**
	 * Gere le tirage de carte et si le deck est vide en prend un nouveau
	 * 
	 * @return la carte tirée
	 */
	public Card hitCard() {
		if (currentDeck.getDeck().size() == 0) {
			currentDeck = getNewDeck();
		}

		return currentDeck.Hit();
	}

	public Deck getCurrentDeck() {
		return currentDeck;
	}

	public List<Deck> getSabot() {
		return sabot;
	}

}
