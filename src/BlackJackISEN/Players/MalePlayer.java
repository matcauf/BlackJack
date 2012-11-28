package BlackJackISEN.Players;

import Exception.SaisieErroneeException;

/**
 * defini la maniere dont un joueur demandera des cartes gerera son tapis il
 * peut offrir des verres, parler etre agresser.
 * 
 * @author matthieu
 * 
 */
public class MalePlayer extends Player implements MoneyPlayers, IvrePlayer,
		Replique {
	/**
	 * Tapis du joueur
	 */
	private Tapis tapis;

	/**
	 * savoir si on est premier tour de jeu ou non
	 */
	private boolean turn = true;

	/**
	 * Constructeur de joueur initialisant son tapis et aussi le nom cf player
	 * 
	 * @param name
	 *            nom du joueur
	 * @param stack
	 *            tapis de départ du joueurs
	 * @throws SaisieErroneeException
	 *             controle de nom + controle de tapis
	 */
	public MalePlayer(String name, int stack) throws SaisieErroneeException {
		super(name);
		genderStrategie = 19;
		this.tapis = new Tapis(stack);
	}

	public void win(boolean i, boolean tourn, int miseMini) {
		if (i && tourn) {
			tapis.money(tapis.getMise(), 1.5, miseMini);
		} else if (i) {
			tapis.money(tapis.getMise(), 2, miseMini);
		} else
			tapis.money(tapis.getMise(), -1, miseMini);
	}

	public void betturn(int miseMini) {
		tapis.setMise((int) (Math.random()
				* (tapis.getStack() * 0.15 - miseMini) + miseMini));
	}

	public String speak(Player player) {
		try {
			if (!this.equals(player)) {
				String s = this.name + " dit à " + player.Getname() + " :";
				if (player.getClass().equals(MalePlayer.class)) {
					if (this.ivresse < 3) {
						return s
								+ MALE_REPLIQUES_SOBRE[(int) (Math.random() * MALE_REPLIQUES_SOBRE.length)]
								+ "\n";
					} else if (this.ivresse < 10) {
						return s
								+ MALE_REPLIQUES_MOYEN[(int) (Math.random() * MALE_REPLIQUES_MOYEN.length)]
								+ "\n";
					} else
						return s
								+ MALE_REPLIQUE_IVRE[(int) (Math.random() * MALE_REPLIQUE_IVRE.length)]
								+ "\n";
				} else if (player.getClass().equals(FemalePlayer.class)) {

					if (this.ivresse < 3) {
						return s
								+ MALE_REPLIQUES_SOBRE[(int) (Math.random() * MALE_REPLIQUES_SOBRE.length)]
								+ "\n";
					} else if (this.ivresse < 10) {
						return s
								+ MALE_REPLIQUES_MOYEN[(int) (Math.random() * MALE_REPLIQUES_MOYEN.length)]
								+ "\n";
					} else
						return s
								+ MALE_REPLIQUE_IVRE[(int) (Math.random() * MALE_REPLIQUE_IVRE.length)]
								+ "\n";
				} else if (player.getClass().equals(CroupierPlayer.class)) {

					if (this.ivresse < 3) {
						return s
								+ MALE_REPLIQUES_SOBRE[(int) (Math.random() * MALE_REPLIQUES_SOBRE.length)]
								+ "\n";
					} else if (this.ivresse < 10) {
						return s
								+ MALE_REPLIQUES_MOYEN[(int) (Math.random() * MALE_REPLIQUES_MOYEN.length)]
								+ "\n";
					} else
						return s
								+ MALE_REPLIQUE_IVRE[(int) (Math.random() * MALE_REPLIQUE_IVRE.length)]
								+ "\n";

				} else
					return s + "rien\n";
			} else
				return "";
		} catch (ArrayIndexOutOfBoundsException e) {
			return e + "probleme de repliques\n";
		}
	}

	public String offrireunVerre(Player player) {
		int i = (int) (Math.random() * 80 + 10);
		setStack(this.tapis.getStack() - i);
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

			String s = "";
			if (agresseur.equals(MalePlayer.class)) {
				int i = (int) (Math.random() * ((MalePlayer) agresseur)
						.getStack().getStack());
				if (Math.random() > 0.5) {
					this.tapis.setStack(this.tapis.getStack() + i);
					((MalePlayer) agresseur).setStack(((MalePlayer) agresseur)
							.getStack().getStack() - i);
					s = agresseur.Getname() + " agresse " + this.name
							+ "et perd " + String.valueOf(i) + "\n";
				} else {
					this.tapis.setStack(this.tapis.getStack() - i);
					((MalePlayer) agresseur).setStack(((MalePlayer) agresseur)
							.getStack().getStack() + i);
					s = agresseur.Getname() + " agresse " + this.name
							+ "et gagne " + String.valueOf(i) + "\n";
				}
				if (Math.random() > 0.6) {
					((MalePlayer) agresseur).setStack(0);
					return s + agresseur.name + " est exclu du casino\n";
				} else
					return s;

			} else if (agresseur.getClass().equals(FemalePlayer.class)) {
				int i = (int) (Math.random() * ((FemalePlayer) agresseur)
						.getStack().getStack());
				if (Math.random() > 0.4) {
					this.tapis.setStack(this.tapis.getStack() + i);
					((FemalePlayer) agresseur)
							.setStack(((FemalePlayer) agresseur).getStack()
									.getStack() - i);
					s = agresseur.Getname() + " agresse " + this.name
							+ "et perd " + String.valueOf(i) + "\n";
				} else {
					this.tapis.setStack(this.tapis.getStack() - i);
					((FemalePlayer) agresseur)
							.setStack(((FemalePlayer) agresseur).getStack()
									.getStack() + i);
					s = agresseur.Getname() + " agresse " + this.name
							+ "et gagne " + String.valueOf(i) + "\n";
				}
				if (Math.random() > 0.4) {
					((FemalePlayer) agresseur).setStack(0);
					return s + agresseur.name + " est exclu du casino\n";
				} else
					return s;

			} else if (agresseur.getClass().equals(Joueur.class)) {
				int i = (int) (Math.random() * ((Joueur) agresseur).getStack()
						.getStack());
				if (Math.random() > 0.5) {
					this.tapis.setStack(this.tapis.getStack() + i);
					((Joueur) agresseur).setStack(((Joueur) agresseur)
							.getStack().getStack() - i);
					s = agresseur.Getname() + " agresse " + this.name
							+ "et perd " + String.valueOf(i) + "\n";
				} else {
					this.tapis.setStack(this.tapis.getStack() - i);
					((Joueur) agresseur).setStack(((Joueur) agresseur)
							.getStack().getStack() + i);
					s = agresseur.Getname() + " agresse " + this.name
							+ "et gagne " + String.valueOf(i) + "\n";
				}
				if (Math.random() > 0.7) {
					((Joueur) agresseur).setStack(0);
					return s + agresseur.name + " est exclu du casino\n";
				} else
					return s;
			} else
				return "";
		} else
			return "";
	}

	public Tapis getStack() {
		return this.tapis;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public void setStack(int value) {
		this.tapis.setStack(value);
	}
}
