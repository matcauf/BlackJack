package BlackJackISEN.Players;

/**
 * Contient les repliques des joueurs en fonctions de leur sexe ou fonction et
 * de leur taux d'alcoolemis (Les repliques pour les femmes ne sont mises, mais
 * le programme est prevu pour faire la diffrence entre les genres)
 * 
 * @author matthieu
 * 
 */
public interface Replique {

	final static String[] MALE_REPLIQUES_SOBRE = {
			"Bonsoir c'est ma première au Black Jack",
			"J'aime perdre de l'argent",
			"C'est pas juste tu as beaucoup trop de chance, compte tu les cartes",
			"J'aimerais bien comprendre les règles du jeux",
			"Voulez vous un verre?" };

	final static String[] MALE_REPLIQUES_MOYEN = {
			"Mmmmh t'es en beauté ce soir",
			"Vous savez j'ai un super cabrioler voulez vous l'essayez? ;-)",
			"Bonsoir belle demoiselle", "Tu as de beaux yeux tu sais" };

	final static String[] MALE_REPLIQUE_IVRE = {
			"J'aime manger des chips et vous?",
			"Mais faites all in de toute façon vous savez pas jouer",
			"le rouge est ma couleur préférée", "Are you talking to me?",
			"j'aime me battre", "t'es chaude toi" };

	final static String[] CROUPIER_REPLIQUES = {
			"Vous devriez faire attention où vous allez être ruiné",
			"Bien joué", "J'aime ce métier", "VIVE LE BLACK JACK",
			"Faites vos jeux" };

}
