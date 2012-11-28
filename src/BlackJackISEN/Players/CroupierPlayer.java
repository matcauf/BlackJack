package BlackJackISEN.Players;

import Exception.SaisieErroneeException;

/**
 * genere le croupier en tant que joueur (il ne gere pas la distribution des
 * cartes) il peut offrir des cerres, parler et etre agresser se qui exluera
 * directement le joueur du casino
 * 
 * @author matthieu
 * 
 */
public class CroupierPlayer extends Player implements IvrePlayer, Replique {

	/**
	 * Constructeur du croupier (similaire a player) et aussi le nom cf player
	 * 
	 * 
	 */
	public CroupierPlayer() throws SaisieErroneeException {
		super("Croupier");
		genderStrategie = 17;
	}

	public String speak(Player player) {
		String s = "";
		if (!this.equals(player)) {
			s = this.name + " dit à " + player.Getname() + " :";
		} else
			s = this.name + " dit:";

		if (player.getClass().equals(MalePlayer.class)) {
			if (this.ivresse < 3) {
				return s
						+ CROUPIER_REPLIQUES[(int) (Math.random() * CROUPIER_REPLIQUES.length)]
						+ "\n";
			} else if (this.ivresse < 10) {
				return s
						+ CROUPIER_REPLIQUES[(int) (Math.random() * CROUPIER_REPLIQUES.length)]
						+ "\n";
			} else
				return s
						+ CROUPIER_REPLIQUES[(int) (Math.random() * CROUPIER_REPLIQUES.length)]
						+ "\n";
		} else if (player.getClass().equals(FemalePlayer.class)) {

			if (this.ivresse < 3) {
				return s
						+ CROUPIER_REPLIQUES[(int) (Math.random() * CROUPIER_REPLIQUES.length)]
						+ "\n";
			} else if (this.ivresse < 10) {
				return s
						+ CROUPIER_REPLIQUES[(int) (Math.random() * CROUPIER_REPLIQUES.length)]
						+ "\n";
			} else
				return s
						+ CROUPIER_REPLIQUES[(int) (Math.random() * CROUPIER_REPLIQUES.length)]
						+ "\n";
		} else if (player.getClass().equals(CroupierPlayer.class)) {

			if (this.ivresse < 3) {
				return s
						+ CROUPIER_REPLIQUES[(int) (Math.random() * CROUPIER_REPLIQUES.length)]
						+ "\n";
			} else if (this.ivresse < 10) {
				return s
						+ CROUPIER_REPLIQUES[(int) (Math.random() * CROUPIER_REPLIQUES.length)]
						+ "\n";
			} else
				return s
						+ CROUPIER_REPLIQUES[(int) (Math.random() * CROUPIER_REPLIQUES.length)]
						+ "\n";

		} else
			return s + "rien\n";
	}

	public String offrireunVerre(Player player) {
		int i = (int) (Math.random() * 80 + 10);
		player.ivresse();
		if (!this.equals(player)) {
			return this.name + " a offert un verre à " + player.Getname()
					+ " d'une valeur de " + String.valueOf(i) + "\n";
		} else
			return this.name + " prend un verre d'une valeur de "
					+ String.valueOf(i) + "\n";

	}

	public String agressBy(Player agresseur) {
		if (!this.equals(agresseur)) {
			if (agresseur.equals(MalePlayer.class)) {
				((MalePlayer) agresseur).setStack(0);

			} else if (agresseur.getClass().equals(FemalePlayer.class)) {
				((FemalePlayer) agresseur).setStack(0);

			} else if (agresseur.getClass().equals(Joueur.class)) {
				((Joueur) agresseur).setStack(0);
			}
			return agresseur.name
					+ "agresse le croupier et est exclu du casino\n";
		} else
			return "";
	}
}
