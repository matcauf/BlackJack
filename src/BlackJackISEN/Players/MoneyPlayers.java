package BlackJackISEN.Players;

/**
 * interface definissant le comportement des joueurs utilisant un tapis
 * 
 * @author matthieu
 * 
 */
public interface MoneyPlayers {

	/**
	 * methode calculant et modifiant le tapis des joueurs en fonction de leur
	 * main et si ils font blackjack au premier tour ou non
	 * 
	 * @param winner
	 *            envoit si le joueur a gagné
	 * @param turn
	 *            envois si c est le premier tour ou non
	 * @param miseMini
	 *            Mise minimal de la table
	 */
	public void win(boolean winner, boolean turn, int miseMini);

	/**
	 * Permet au joueur de parier en respectant la mise mini de la table
	 * 
	 * @param miseMini
	 *            miseMini de la table
	 */
	public void betturn(int miseMini);

}
