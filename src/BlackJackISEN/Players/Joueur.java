package BlackJackISEN.Players;

import javax.swing.JOptionPane;

import BlackJackISEN.cartes.Hand;
import Exception.SaisieErroneeException;

/**
 * Joueur non IA ne pouvant pas parler ni offrir a boire (mais la suite du
 * programme et prevu pour que le joueurs offir boire,parler ou agresser)
 * 
 * @author matthieu
 * 
 */
public class Joueur extends Player implements MoneyPlayers {

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
	public Joueur(String name, int stack) throws SaisieErroneeException {
		super(name);
		this.tapis = new Tapis(stack);

	}

	/**
	 * Redefinition de la fonction presente dans Player pour le cas sepcial d'un
	 * joueur non IA
	 */
	public Boolean strategy(Hand croupierHand) {
		int i = 1;
		Hand uneCarte = new Hand();
		uneCarte.setHand(croupierHand.getHand().get(0));
		if (finish == true) {
			i = JOptionPane.showConfirmDialog(
					null,
					"Votre main : " + playerHand() + "\n"
							+ "La carte decouverte du croupier est "
							+ uneCarte.handView() + " d'une valeur de "
							+ uneCarte.handValue()
							+ "\n oui pour piocher sinon non",
					"c'est votre tour", JOptionPane.YES_NO_OPTION);
		}
		if (ivresse > 6) {
			if (Math.random() > 0.5) {
				i = 0;
			} else
				i = 1;
		}
		if (i == 0) {
			return true;
		} else {
			finish = false;
			return false;
		}
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
		String playerBet = "";
		try {
			playerBet = JOptionPane.showInputDialog("Votre Tapis est de"
					+ String.valueOf(tapis.getStack())
					+ "entrez votre mise pour ce tour", miseMini);
			tapis.setMise(Integer.parseInt(playerBet));
		} catch (NumberFormatException e) {
			System.out.println("Erreur" + e.getMessage());
			betturn(miseMini);
		}
	}

	/**
	 * cette fonction n'est jamais appelé mais est revu pour étre utilisé dans
	 * le programme
	 */
	protected String speak(Player player) {
		if (player.getClass().equals(MalePlayer.class)) {
			return "tu as de beau yeux tu sais ?";
		} else if (player.getClass().equals(FemalePlayer.class)) {
			return "toi tu as l'air d'etre chaude";

		} else
			return "";
	}

	public Tapis getStack() {
		return this.tapis;
	}

	public void setStack(int value) {
		this.tapis.setStack(value);
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}
}
