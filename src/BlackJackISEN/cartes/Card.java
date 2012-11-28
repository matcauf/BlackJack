package BlackJackISEN.cartes;

import Exception.SaisieErroneeException;

/**
 * Classe de generation de cartes couleur, valeur et leur nom.
 * 
 * @author matthieu
 * 
 */
public class Card {
	private int value;
	private String color;

	/**
	 * méthode permetant de créer un objet carte
	 * 
	 * @param value
	 *            valeur de la carte (1~13) corespondant (As~Roi)
	 * @param color
	 *            couleur de la carte
	 */
	public Card(int value, String color) {
		try {
			SaisieErroneeException.controleJoueur(color);
			this.value = value;
			this.color = color;
		} catch (SaisieErroneeException e) {
			System.out.println(e);
			this.color = "unknow";
		}

	}

	/**
	 * return la figure de la carte si la carte est inconue Return JOKER
	 * 
	 * @return string Valeur de la figure de la carte
	 */
	public String getSybling() {
		switch (value) {
		case (0):
			return "A";
		case (1):
			return "2";
		case (2):
			return "3";
		case (3):
			return "4";
		case (4):
			return "5";
		case (5):
			return "6";
		case (6):
			return "7";
		case (7):
			return "8";
		case (8):
			return "9";
		case (9):
			return "10";
		case (10):
			return "J";
		case (11):
			return "Q";
		case (12):
			return "R";
		default:
			return "Joker";
		}
	}

	/**
	 * return la valeur de la carte entre 1 et 10 et si la carte est inconnu c
	 * est 11
	 * 
	 * @param card
	 *            carte a evaluer
	 * @return int entre 1 et 10 et 11 si carte inconnu
	 */
	public int CardValue(Card card) {
		switch (card.getValue()) {
		case (0):
			return 1;
		case (1):
			return 2;
		case (2):
			return 3;
		case (3):
			return 4;
		case (4):
			return 5;
		case (5):
			return 6;
		case (6):
			return 7;
		case (7):
			return 8;
		case (8):
			return 9;
		case (9):
			return 10;
		case (10):
			return 10;
		case (11):
			return 10;
		case (12):
			return 10;
		default:
			return 11;
		}
	}

	public int getValue() {
		return value;
	}

	public String getColor() {
		return color;
	}

}