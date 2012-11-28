package BlackJackISEN;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

import BlackJackISEN.Players.FemalePlayer;
import BlackJackISEN.Players.Joueur;
import BlackJackISEN.Players.MalePlayer;
import BlackJackISEN.Players.Player;
import Exception.SaisieErroneeException;

/**
 * Gere tout le casino creer des joueurs les tables et les géres dans leurs
 * globalité
 * 
 * @author matthieu
 * 
 */
public class Room implements Constante {

	/**
	 * le casino contient une liste de player
	 */
	private Vector<Player> players = new Vector<Player>();
	/**
	 * le casino contient plusieurs tables
	 */
	private Vector<GameMaster> tables = new Vector<GameMaster>();
	/**
	 * Les evenement qui se passent dans le casino
	 */
	private String evenement = "";
	/**
	 * sere à savoir si le joueur non IA joue ou non
	 */
	private static boolean player_non_IA = false;
	/**
	 * savoir si le programme doit tourner a l'infini ou non
	 */
	private static boolean infinite = false;
	/**
	 * savoir si le joueur est encore dans le casino ou non
	 */
	private static boolean joueurStatue = false;

	/**
	 * Construteur de room se referer a iniRoom()
	 * 
	 * @throws SaisieErroneeException
	 */
	public Room() throws SaisieErroneeException {
		initRoom();
	}

	/**
	 * initilise le casino cree les joueurs le tapis, les tables et les affectes
	 * pour attribuer les variables se conferer au fichier Contante.java
	 * 
	 * @throws SaisieErroneeException
	 */
	private void initRoom() throws SaisieErroneeException {
		int end, start;
		try {
			if (PERSONNE <= 0) {
				int i = JOptionPane
						.showConfirmDialog(
								null,
								"Attention il n'y aura personne dans le casino. Continuer? ",
								"Warning", JOptionPane.YES_NO_OPTION);
				if (i != 0) {
					System.exit(0);
				}
			}
			for (int i = 0; i < PERSONNE; i++) {
				if (Math.random() > 0.5) {
					players.add(new MalePlayer(
							MALE_NAME[(int) (Math.random() * MALE_NAME.length)],
							(int) (Math.random() * (TAPIS_MAX - TAPIS_MIN) + TAPIS_MIN)));
				} else {
					players.add(new FemalePlayer(FEMALE_NAME[(int) (Math
							.random() * FEMALE_NAME.length)], (int) (Math
							.random() * (TAPIS_MAX - TAPIS_MIN) + TAPIS_MIN)));
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// probleme de nom
			for (int i = 0; i < PERSONNE; i++) {
				if (Math.random() > 0.5) {
					players.add(new MalePlayer("MalePlayer" + i, (int) (Math
							.random() * (TAPIS_MAX - TAPIS_MIN) + TAPIS_MIN)));
					System.out.println(e
							+ " probleme dans les noms tableau vide");
				} else {
					players.add(new FemalePlayer(
							"FemalePlayer" + i,
							(int) (Math.random() * (TAPIS_MAX - TAPIS_MIN) + TAPIS_MIN)));
					System.out.println(e
							+ " probleme dans les noms tableau vide");
				}
			}
		}
		// cette partie gere l'utilisateur du programme
		if (JOptionPane
				.showConfirmDialog(
						null,
						"Voulez-vous jouer ? (non implique que le casino jouera sans vous)",
						"Jouer", JOptionPane.YES_NO_OPTION) == 0) {
			initJoueur();
			player_non_IA = true;

		} else if (JOptionPane
				.showConfirmDialog(null, "creer une boucle infini ?", "Jouer",
						JOptionPane.YES_NO_OPTION) == 0) {
			infinite = true;
			joueurStatue = true;
		}
		// fin de la partie de l'utilisateur qui gere le programme
		Collections.shuffle(players);

		if (players.size() - 1 == 0) {
			end = 1;
		} else
			end = players.size() - 1;

		// affecte les joueurs a leur table
		for (int i = 0; i < TABLE; i++) {
			Vector<Player> temp = new Vector<Player>();
			for (start = end - 7; start < end; start++) {
				try {

					temp.add(players.get(start));
					players.get(start).setPlaying(true);
					if(players.get(start).getClass().equals(Joueur.class))
					{
						JOptionPane.showMessageDialog(null, "vous étes maintenant sur la table " + (i + 1));
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("sortie du tableau");
				}
			}
			if (start - 7 < 0) {
				start = 0;
				end = end - 7;
			} else if (start == 0) {
				end = 0;
			} else {
				start = start - 7;
				end = end - 7;
			}
			tables.add(new GameMaster(-6, 52, (int) (Math.random()
					* (MISE_MAX_TABLE - MISE_MIN_TABLE) + MISE_MIN_TABLE),
					i + 1, temp));
		}
	}

	/**
	 * fait entrer des personne dans la salle de maniere aleatoire
	 * 
	 * @throws SaisieErroneeException
	 */
	private void repeuplement() throws SaisieErroneeException {
		int peuple = (int) (Math.random() * PERSONNE);
		evenement = evenement + "\n \n Acceuil \n";
		int j;
		if (players.size() < peuple) {
			for (int i = 0; i < peuple - players.size(); i++) {

				// creer un joueur non IA si les options en debut de programme
				// sont bien definit
				if (!joueurStatue && player_non_IA) {
					if (JOptionPane.showConfirmDialog(null,
							"voulez-vous revenir ?", "revenir",
							JOptionPane.YES_NO_OPTION) == 0) {
						initJoueur();
					} else if ((JOptionPane.showConfirmDialog(null,
							"Quitter ?", "Jouer", JOptionPane.YES_NO_OPTION) == 0)) {
						System.exit(0);
					} else if (JOptionPane
							.showConfirmDialog(
									null,
									"creer une boucle infini ? (tout se pasera sans vous?)",
									"Jouer", JOptionPane.YES_NO_OPTION) == 0) {
						player_non_IA = false;
						infinite = true;
						joueurStatue = true;
					} else {
						player_non_IA = false;
						infinite = false;
						joueurStatue = true;
					}

				}

				// creer un joueur male ou female de maniere aleatoire
				if (Math.random() > 0.5) {

					j = (int) (Math.random() * MALE_NAME.length);
					players.add(new MalePlayer(MALE_NAME[j], (int) (Math
							.random() * (TAPIS_MAX - TAPIS_MIN) + TAPIS_MIN)));
					evenement = evenement + MALE_NAME[j] + " entre\n";

				} else {
					j = (int) (Math.random() * FEMALE_NAME.length);
					players.add(new FemalePlayer(FEMALE_NAME[j], (int) (Math
							.random() * (TAPIS_MAX - TAPIS_MIN) + TAPIS_MIN)));
					evenement = evenement + FEMALE_NAME[j] + " entre\n";
				}
			}
		}

	}

	/**
	 * Fonction permetant de donner la vie au programme et gere chaque tables
	 * avec se programme le casino est en moyenne 80% plein
	 * 
	 * appele continuelement la fonction croupier.game, le repeuplemement du
	 * casino et le placement des joueurs
	 * 
	 * @throws SaisieErroneeException
	 */
	public void live() throws SaisieErroneeException {
		Scanner sc = new Scanner(System.in);
		while (true) {
			evenement = "";
			// lance un round sur toutes les tables du casino
			for (GameMaster croupier : tables) {
				evenement = evenement + croupier.game() + "\n";
			}
			repeuplement();
			placement();
			System.out.println(evenement);

			// permet au jouers de voir se qu'il se passe
			if (!infinite && !player_non_IA) {
				System.out
						.println("Si vous entrez exit le programme se fermera sinon entrez autre chose");
				if (sc.next().equals("exit")) {
					System.exit(0);
				}

			}

		}

	}

	/**
	 * Replace les joueurs sur les tables en fonction de leurs tapis si aucune
	 * table n'est libre ils sont mis au bar si ils sont au bar ils boivent un
	 * verre si leur tapis et trop faible pour le casino ils partent
	 */
	private void placement() {
		evenement = evenement + "\n" + "\n" + "Bar" + "\n" + "";
		synchronized (players) {
			Iterator<Player> iter = players.iterator();
			while (iter.hasNext()) {
				Object o = iter.next();
				// on regarde si le joueur est sur une table ou non
				if (!((Player) o).isPlaying()) {
					List<GameMaster> tablesDisponibles = new ArrayList<GameMaster>();
					boolean continuer = false;
					// tout le monde prend un verre
					if (o.getClass().equals(MalePlayer.class)) {
						if (Math.random() > 0.6) {
							evenement = evenement
									+ ((MalePlayer) o)
											.offrireunVerre(((Player) o));
						}
						// on regarde si une table est comptaible avec le tapis
						// du joueurs
						for (GameMaster croupier : tables) {
							if (((MalePlayer) o).getStack().getStack() > croupier.miseMini) {
								// on essais de l'ajouter a la table
								tablesDisponibles.add(croupier);
								continuer = true;
							}
						}
					} else if (o.getClass().equals(FemalePlayer.class)) {
						if (Math.random() > 0.6) {
							evenement = evenement
									+ ((FemalePlayer) o)
											.offrireunVerre(((Player) o));
						}
						for (GameMaster croupier : tables) {
							if (((FemalePlayer) o).getStack().getStack() > croupier.miseMini) {
								tablesDisponibles.add(croupier);
								continuer = true;
							}
						}
					} else if (o.getClass().equals(Joueur.class)) {

						for (GameMaster croupier : tables) {
							if (((Joueur) o).getStack().getStack() > croupier.miseMini) {
								tablesDisponibles.add(croupier);
								continuer = true;
							}
						}
					}
					if (!continuer) {
						// les personnes dont le tapis est trop faible partent
						// du casino
						if (o.getClass().equals(Joueur.class)) {
							joueurStatue = false;
						}
						evenement = evenement + ((Player) o).Getname()
								+ " part" + "\n";
						iter.remove();
					} else {
						tablesDisponibles.get(
								(int) (Math.random() * (tablesDisponibles
										.size() - 1))).newOnMytable((Player) o);
					}

				}

			}

		}
	}

	/**
	 * créer un joueur non IA
	 * 
	 * @throws SaisieErroneeException
	 */
	private void initJoueur() throws SaisieErroneeException {
		String playerName = "";
		try {
			playerName = JOptionPane.showInputDialog("entrez votre prenom",
					"Joueur1");
			SaisieErroneeException.controleJoueur(playerName);
			players.add(new Joueur(playerName, (int) (Math.random()
					* (TAPIS_MAX - TAPIS_MIN) + TAPIS_MIN)));
			joueurStatue = true;

		} catch (SaisieErroneeException e) {
			System.out.println("Erreur" + e.getMessage());
			initJoueur();
		}
	}

}
