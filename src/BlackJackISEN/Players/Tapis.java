package BlackJackISEN.Players;

import Exception.SaisieErroneeException;

/**
 * class gerant le tapis du joueur le retrait d'argent ou le gain et la perte
 * 
 * @author matthieu
 * 
 */
public class Tapis {

	/**
	 * Etat du tapis
	 */
	private int stack = 0;

	/**
	 * mise courante du joueur
	 */
	private int mise = 0;

	/**
	 * Constructeur de tapis
	 * 
	 * @param stack
	 *            tapis de départ
	 */
	public Tapis(int stack) {
		try {

			this.stack = stack;
			SaisieErroneeException.controleTapis(this.stack);
		} catch (SaisieErroneeException e) {
			this.stack = ((int) Math.random() * 500 + 200);
		}
	}

	/**
	 * gére les gains ou pertes des joueurs en fonction de leur mise si la mise
	 * est trop petite elle sera ramené a la mise minimale
	 * 
	 * @param mise
	 *            mise du joueur
	 * @param n
	 *            ratio multiplicateur de la mise
	 * @param miseMini
	 *            mise minimal de la table
	 */
	public void money(int mise, double n, int miseMini) {

		if (mise < miseMini || mise > stack) {
			mise = miseMini;
		}
		stack = stack - mise;
		stack = stack + (int) (n * mise);
	}

	public int getStack() {
		return stack;
	}

	public void setStack(int stack) {
		this.stack = stack;
	}

	public int getMise() {
		return mise;
	}

	public void setMise(int mise) {
		this.mise = mise;
	}

}
