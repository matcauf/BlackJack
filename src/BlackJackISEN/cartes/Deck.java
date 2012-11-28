package BlackJackISEN.cartes;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import Exception.SaisieErroneeException;

/**
 * class permettant de génerer un objet jeu de cartes aussi le melange et permet
 * de tirer des cartes dans celui-ci
 * 
 * @author Cauffiez
 * 
 */
public class Deck {

	/**
	 * Liste de cartes qui composent le deck
	 */
	private List<Card> deck = new ArrayList<Card>();

	/**
	 * Constructeur qui genére un jeu de 52 cartes minimun
	 * 
	 * @param nombre_de_cartes
	 *            Nombre de cartes dans le jeu
	 */
	public Deck(int nombre_de_cartes) {
		try {
			SaisieErroneeException.controleDeck(nombre_de_cartes);
			initDeck(nombre_de_cartes);
		} catch (SaisieErroneeException e) {
			System.out.println(e);
			initDeck(52);
		}

	}

	public Deck() {

	}

	/**
	 * Methode permetant d'initialiser un jeu de carte et le melange
	 * 
	 * @param nombre_de_cartes
	 *            Nombre de cartes dans le jeu
	 */
	private void initDeck(int nombre_de_cartes) {
		for (int j = 0; j < 4; j++) {

			for (int i = 0; i < nombre_de_cartes / 4; i++) {
				switch (j) {
				case (0):
					deck.add(new Card(i, "♥"));
					break;
				case (1):
					deck.add(new Card(i, "♠"));
					break;
				case (2):
					deck.add(new Card(i, "♦"));
					break;
				case (3):
					deck.add(new Card(i, "♣"));
					break;
				default:
					deck.add(new Card(i, "Neutre"));
					break;
				}
			}
		}
		Collections.shuffle(deck);

	}

	/**
	 * Pioche une carte et la suprime du haut de deck
	 * 
	 * @return la carte piochée
	 */
	public Card Hit() {
		Card temp = deck.get(0);
		deck.remove(deck.get(0));
		return temp;
	}

	public List<Card> getDeck() {
		return deck;
	}

	public void addCard(Card card) {
		deck.add(card);
	}

}