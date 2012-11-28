package BlackJackISEN.Players;

/**
 * Interface donnant le comportement des players faces à l'alcool
 * 
 * @author matthieu
 * 
 */
public interface IvrePlayer {

	/**
	 * offre un verre a un joueur tout verre offert est bu
	 * 
	 * @param player
	 *            joueur a qui on veut offrir un vert
	 * @return une chaine de caractère de l'evenement
	 */
	public String offrireunVerre(Player player);

	/**
	 * si le joueur est agressé certaint evenement peuvent se passé soit une
	 * partie du tapis de la victime est volé ou une partie du tapis de
	 * l'agresseur est volé et va dans le tapis de la victime
	 * 
	 * L'agresseur peut etre exclu du casino si il agresse le croupier il est
	 * directement exclu
	 * 
	 * @param agresseur
	 *            l'agresseur du joueur
	 * @return l'evenement sous chaine de caractére
	 */
	public String agressBy(Player agresseur);

}
