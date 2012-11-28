package BlackJackISEN;

import java.util.Vector;

import javax.swing.JOptionPane;

import BlackJackISEN.Players.FemalePlayer;
import BlackJackISEN.Players.Joueur;
import BlackJackISEN.Players.MalePlayer;
import BlackJackISEN.Players.Player;
import BlackJackISEN.cartes.Hand;
import BlackJackISEN.cartes.Sabot;
import Exception.SaisieErroneeException;

/**
 * Cette class contient tous se qui est necessaire pour organiser une partie de
 * blacjJack autour d'une table tour de mise, qui gagne ... Mais elle n'est pas
 * prevu pour étre instancié telle quelle
 * 
 * @author matthieu
 * 
 */
abstract public class Croupier implements Constante {
	/**
	 * la table du Croupier contient des joueurs
	 */
	protected Vector<Player> players = new Vector<Player>();
	/**
	 * Une table contient un sabot
	 */
	protected Sabot sabot;
	/**
	 * Une table contient une mise mini
	 */
	protected int miseMini = 0;
	/**
	 * Une table a un nom pour l'identifier
	 */
	protected String tableName = "Table ";

	/**
	 * Constructeur de croupier
	 * 
	 * @param nombre_de_jeux
	 *            nombre de jeux dans le sabot
	 * @param nombre_de_cartes
	 *            nombre de carte par jeux
	 * @param miseMini
	 *            mise minimal
	 * @param tableNumber
	 *            numero de la table
	 */
	public Croupier(int nombre_de_jeux, int nombre_de_cartes, int miseMini,
			int tableNumber) {

		this.sabot = new Sabot(nombre_de_cartes, nombre_de_jeux);
		this.miseMini = miseMini;

		try {
			this.tableName = this.tableName + String.valueOf(tableNumber);
			SaisieErroneeException.controleNombre(tableNumber);
		} catch (SaisieErroneeException e) {
			System.out.println(e + " nom de table invalide");
		}
		try {
			SaisieErroneeException.controleNombre(this.miseMini);
			this.miseMini = miseMini;
		} catch (SaisieErroneeException e) {
			this.miseMini = (int) ((Math.random() * 500) + 1);
		}

	}

	/**
	 * fait piocher une carte au joueur
	 * 
	 * @param player
	 *            joueur à faire piocher
	 */
	private void distribution(Player player) {
		Hand tempHand = new Hand();
		tempHand = player.getHand();
		tempHand.hit(sabot.hitCard());
		player.setHand(tempHand);
	}

	/**
	 * gere si le joueur pioche ou non
	 * 
	 * @param hit
	 *            true = pioche false= pioche pas
	 * @param player
	 *            joueur pour qui on peut faire piocher une carte
	 */
	protected void distribution(boolean hit, Player player) {
		if (hit) {
			distribution(player);
		}
	}

	/**
	 * distribution du debut de jeu avec les 5 premières cartes sont brulées
	 */
	protected void distribution() {
		for (int i = 0; i < 5; i++) {
			sabot.hitCard();
		}

		for (Player player : players) {
			distribution(player);
			distribution(player);
		}

	}

	/**
	 * fonction permettant de savoir si le joueur a gagnée
	 * 
	 * @param playerHand
	 *            main du joueur a évaluer
	 * @param croupierHand
	 *            main du croupier de la table
	 * @return true = gagnée false = perdu
	 */
	private boolean youWin(Hand playerHand, Hand croupierHand) {
		if (croupierHand.handValue() > 21 && playerHand.handValue() <= 21) {
			return true;
		} else if (playerHand.handValue() >= croupierHand.handValue()
				&& playerHand.handValue() <= 21) {
			return true;
		} else
			return false;
	}

	/**
	 * Gere le temps de parole pour le pari des joueurs
	 */
	protected void timeToBet() {
		for (Player player : players) {
			if (player.getClass().equals(MalePlayer.class)) {
				((MalePlayer) player).betturn(this.miseMini);
			} else if (player.getClass().equals(FemalePlayer.class)) {
				((FemalePlayer) player).betturn(this.miseMini);
			} else if (player.getClass().equals(Joueur.class)) {
				((Joueur) player).betturn(this.miseMini);
			}
		}

	}

	/**
	 * donne la recompense ou non a un joueur
	 */
	protected void takeYourReward() {
		Hand croupierHand = players.get(players.size() - 1).getHand();
		boolean winStatue = false;
		for (Player player : players) {

			if (player.getClass().equals(MalePlayer.class)) {
				winStatue = youWin(player.getHand(), croupierHand);
				((MalePlayer) player).win(winStatue,
						((MalePlayer) player).isTurn(), this.miseMini);
			} else if (player.getClass().equals(FemalePlayer.class)) {
				winStatue = youWin(player.getHand(), croupierHand);
				((FemalePlayer) player).win(winStatue,
						((FemalePlayer) player).isTurn(), this.miseMini);
			} else if (player.getClass().equals(Joueur.class)) {
				winStatue = youWin(player.getHand(), croupierHand);
				((Joueur) player).win(winStatue, ((Joueur) player).isTurn(),
						this.miseMini);
			}
		}
	}

	/**
	 * permet d'ajouter un joueur a la table si sont tapis peut suivre la table
	 * et le configure
	 * 
	 * @param player
	 *            joueur a faire entrer sur la table
	 */
	public void newOnMytable(Player player) {
		if (players.size() < 8) {
			try {
				players.add(players.size() - 2, player);
				player.setPlaying(true);
				if(player.getClass().equals(Joueur.class))
				{
					JOptionPane.showMessageDialog(null, "vous étes maintenant sur la " + this.tableName);
				}
			} catch (ArrayIndexOutOfBoundsException e) {

			}
		}

	}

	public int getMiseMini() {
		return miseMini;
	}
}
