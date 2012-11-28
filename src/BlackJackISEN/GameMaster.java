package BlackJackISEN;

import java.util.Iterator;

import java.util.Vector;

import BlackJackISEN.Players.CroupierPlayer;
import BlackJackISEN.Players.FemalePlayer;
import BlackJackISEN.Players.Joueur;
import BlackJackISEN.Players.MalePlayer;
import BlackJackISEN.Players.Player;
import Exception.SaisieErroneeException;

/**
 * Class qui gere le debut de partie, les tours, affiche les mains des joueurs,
 * et le statue de fin de round, les evenements qui se sont passés. Cette class
 * est dedié a l'information sur les evenements sous forme de string et
 * l'organisation d'un tour de jeu
 * 
 * @author matthieu
 * 
 */
public class GameMaster extends Croupier {

	/**
	 * Constructeur de gameMaster
	 * 
	 * @param nombre_de_jeux
	 * @param nombre_de_cartes
	 * @param miseMini
	 * @param tableNumber
	 * @param players
	 * @throws SaisieErroneeException
	 */
	public GameMaster(int nombre_de_jeux, int nombre_de_cartes, int miseMini,
			int tableNumber, Vector<Player> players)
			throws SaisieErroneeException {
		super(nombre_de_jeux, nombre_de_cartes, miseMini, tableNumber);
		this.players = players;
		this.players.add(new CroupierPlayer());
	}

	/**
	 * permet de voir l'etat de chaque joueur avec leur main et leur Tapis( sauf
	 * le croupier)
	 * 
	 * @return la totalité des mains et tapis de chaque joueurs sous forme de
	 *         String
	 */
	private String playersStatue() {
		String table = "";

		for (Player player : players) {
			if (player.getClass().equals(MalePlayer.class)) {

				table = table
						+ player.playerHand()
						+ "  tapis :"
						+ String.valueOf(((MalePlayer) player).getStack()
								.getStack()) + "\n";
			} else if (player.getClass().equals(FemalePlayer.class)) {
				table = table
						+ player.playerHand()
						+ "  tapis :"
						+ String.valueOf(((FemalePlayer) player).getStack()
								.getStack()) + "\n";
			} else if (player.getClass().equals(Joueur.class)) {
				table = table
						+ player.playerHand()
						+ "  tapis :"
						+ String.valueOf(((Joueur) player).getStack()
								.getStack()) + "\n";
			} else
				table = table + player.playerHand() + "\n";
		}
		return table;
	}

	/**
	 * gere le tour pour une partie (distribution, pioche des joueurs, et fin de
	 * tour)
	 */
	private void round() {
		int currentDeck = 0;
		int beforeDeck = 0;
		boolean roundStatue = true;
		distribution();
		while (roundStatue) {
			beforeDeck = sabot.getCurrentDeck().getDeck().size();
			for (Player player : players) {
				if (player.getHand().handValue() < 21) {
					distribution(player.strategy(players
							.get(players.size() - 1).getHand()), player);
					currentDeck = sabot.getCurrentDeck().getDeck().size();
					if (player.getClass().equals(MalePlayer.class)) {
						((MalePlayer) player).setTurn(false);
					} else if (player.getClass().equals(FemalePlayer.class)) {
						((FemalePlayer) player).setTurn(false);
					} else if (player.getClass().equals(Joueur.class)) {
						((Joueur) player).setTurn(false);
					}
				}
			}

			if (currentDeck == beforeDeck) {
				roundStatue = false;
			}

		}

	}

	/**
	 * gere le deroulement d'une partie paris, demarage du tour de jeu, les
	 * recompenses et si le joueur doit quitter la table la parole des joueurs
	 * les agressions
	 * 
	 * @return String tous les evenements qui se sont deroulé pendant la partie
	 */
	public String game() {
		timeToBet();
		round();
		takeYourReward();
		String evenement = tableName + " mise Mini :" + miseMini + "\n" + "\n"
				+ playersStatue();
		// vide les main des joueurs et reinitilize les variables finich et turn
		// a true pour un prochain tour de jeu
		// gére l'offre de verre et la parole des joueurs
		for (Player player : players) {
			player.endHand();
			player.setFinish(true);
			if (player.getClass().equals(MalePlayer.class)) {
				((MalePlayer) player).setTurn(true);
				// offre des verres
				if (Math.random() > 0.4) {
					int i = (int) (Math.random() * (players.size() - 1));
					evenement = evenement
							+ ((MalePlayer) player).offrireunVerre(players
									.get(i))
							+ ((MalePlayer) player).speak(players.get(i));
					i = (int) (Math.random() * (players.size() - 1));
					// agresstion du player homme
					if (Math.random() * players.get(i).getIvresse() / 10 > 1) {
						evenement = evenement
								+ ((MalePlayer) player)
										.agressBy(players.get(i));
					}
				}
				// parle
				if (Math.random() > 0.3) {
					evenement = evenement
							+ ((MalePlayer) player)
									.speak(players.get((int) (Math.random() * (players
											.size() - 1))));
				}
			} else if (player.getClass().equals(FemalePlayer.class)) {
				((FemalePlayer) player).setTurn(true);
				// offre des verres
				if (Math.random() > 0.6) {

					int i = (int) (Math.random() * (players.size() - 1));
					evenement = evenement
							+ ((FemalePlayer) player).offrireunVerre(players
									.get(i))
							+ ((FemalePlayer) player).speak(players.get(i));
					i = (int) (Math.random() * (players.size() - 1));
					// agression de la player femme
					if (Math.random() * players.get(i).getIvresse() / 10 > 1) {
						evenement = evenement
								+ ((FemalePlayer) player).agressBy(players
										.get(i));
					}
				}
				// parle
				if (Math.random() > 0.2) {
					evenement = evenement
							+ ((FemalePlayer) player)
									.speak(players.get((int) (Math.random() * (players
											.size() - 1))));

				}
			} else if (player.getClass().equals(Joueur.class)) {
				((Joueur) player).setTurn(true);
			} else {
				int i = (int) (Math.random() * (players.size() - 1));
				evenement = evenement
						+ ((CroupierPlayer) player).offrireunVerre(players
								.get(i))
						+ ((CroupierPlayer) player).speak(players.get(i));
				i = (int) (Math.random() * (players.size() - 1));
				// agressetion du croupier
				if (Math.random() * players.get(i).getIvresse() / 10 > 1) {
					evenement = evenement
							+ ((CroupierPlayer) player)
									.agressBy(players.get(i));
				}
				// parle
				if (Math.random() > 0.6) {
					evenement = evenement
							+ ((CroupierPlayer) player)
									.speak(players.get((int) (Math.random() * (players
											.size() - 1))));

				}
			}

		}

		evenement = evenement + "\n";
		// gere si le joeur a le droit de rester ou non sur la table en fonction
		// de la mise
		synchronized (players) {
			Iterator<Player> iter = players.iterator();
			while (iter.hasNext()) {
				Object o = iter.next();
				if (o.getClass().equals(MalePlayer.class)) {
					if (((MalePlayer) o).getStack().getStack() < this.miseMini) {
						((MalePlayer) o).setPlaying(false);
						iter.remove();
					}
				} else if (o.getClass().equals(FemalePlayer.class)) {
					if (((FemalePlayer) o).getStack().getStack() < this.miseMini) {
						((FemalePlayer) o).setPlaying(false);
						iter.remove();
					}
				} else if (o.getClass().equals(Joueur.class)) {
					if (((Joueur) o).getStack().getStack() < this.miseMini) {
						((Joueur) o).setPlaying(false);
						iter.remove();
					}
				}
			}

		}
		return evenement;
	}

}
