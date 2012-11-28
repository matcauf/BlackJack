package BlackJackISEN.cartes;

import java.util.ArrayList;
import java.util.List;

/**
 * Gere la main du joueur son score et son affichage
 * 
 * @author landouzy CAUFFIEZ
 * 
 * 
 */
public class Hand {

	/**
	 * Une main est composé de plusieurs cartes
	 */
	private List<Card> hand = new ArrayList<Card>();

	/**
	 * donne la valeur optimale de la main
	 * 
	 * 
	 * @return un int qui represente la valeur de la main
	 */
	public int handValue() {
		int value = 0;
		int asNumber = 0;
		for (Card card : hand) {
			if (card.CardValue(card) == 1) {
				asNumber++;
			} else {
				value = value + card.CardValue(card);
			}
		}
		return HandOptimunValue(asNumber, value);

	}

	/**
	 * donne la valeur optimale de la main en tenant compte des as
	 * 
	 * @param asNumber
	 *            nombre d'as
	 * @param value
	 *            valeur de la main sans prendre en compte les as
	 * @return la valeur optimale de la main
	 */
	private int HandOptimunValue(int asNumber, int value) {
		for (int i = 0; i < asNumber; i++) {
			if (value + 11 < 21) {
				value = value + 11;
			} else
				value++;

		}
		return value;
	}

	public void hit(Card card) {
		hand.add(card);
	}

	/**
	 * vide la main
	 */
	public void emptyHand() {
		hand.clear();
	}

	/**
	 * affiche la main
	 * 
	 * @return un string qui affiche la main du joueur
	 */
	public String handView() {
		String cartes = "";
		for (Card card : hand) {
			if (cartes == "") {
				cartes = card.getSybling() + "" + card.getColor();
			} else
				cartes = cartes + "/" + card.getSybling() + ""
						+ card.getColor();
		}
		return cartes;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(Card card) {
		hand.add(card);
	}
}
