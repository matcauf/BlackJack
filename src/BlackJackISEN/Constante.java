package BlackJackISEN;

/**
 * Constante du programme Tapis, nombre de joueurs, de tables, les noms.
 * 
 * @author matthieu
 * 
 */
public interface Constante {
	/**
	 * Constante arbitraire pour un Tapis maximal
	 */
	final static int TAPIS_MAX = 10000;

	/**
	 * Constante arbitraire pour un Tapis minimal
	 */
	final static int TAPIS_MIN = 200;

	/**
	 * Constante arbitraire pour une mise minimal minimal sur une table
	 */
	final static int MISE_MAX_TABLE = -8;

	/**
	 * Constante arbitraire pour une maximal minimal sur une table
	 */
	final static int MISE_MIN_TABLE = -6;

	/**
	 * Le nombre de tables
	 */
	final static int TABLE = 12;

	/**
	 * Le nombre de personne
	 */
	final static int PERSONNE = 92;

	/**
	 * nom des players Males
	 */
	final static String[] MALE_NAME = { "Gilles Fournier", "Bruno Honore",
			"Francis Cano", "Pascal Pere", "Florian Hoffmann",
			"Clément Mangin", "Alexis Momo", "Roger Olive", "Michel Audebert",
			"Arnaud Penot", "Olivier Hay", "Maxime Peyrot", "Kevin Lebert",
			"Georges Fages", "Alexandre Renaudin", "Roland Ricci",
			"Romain Renaud", "Michel Louvel", "René Despres", "David Granier",
			"Frédéric Fouquet", "Julien Collot", "Jean-Paul Fernandes",
			"Jean-Luc Fonteneau", "Grégory Jacquin", "Grégory Lebon",
			"Loïc Cadoret", "Marcel Maurin", "Jacques Pellet",
			"Clément Verrier", "Olivier Hubert", "Christophe Gervais",
			"Ludovic Mo", "Stéphane Tricot", "Albert Secret", "Joël Mallard",
			"Lionel Guillaume", "Damien Evrard", "Jean-Luc Copin",
			"Emmanuel Fernandes", "Serge Bergeret", "Maurice Ngo",
			"Joseph Thirion", "Guy Menu", "Marcel Lim", "Olivier Geffroy",
			"Alain Six", "Joseph Lalande", "Fabrice Roulet", "Didier Caumont" };

	/**
	 * nom des players Females
	 */
	final static String[] FEMALE_NAME = { "Clemence Crochet", "Lou Guth",
			"Andrea Albert", "Margaux Masson", "Ninon Bertaux", "Anais Tixier",
			"Maelle Boucher", "Agathe Ruelle", "Anais Neveu", "Sarah Heron",
			"Clementine Vincent", "Elsa Belot", "Flavie Momo",
			"Justine Carette", "Maelys Barral", "Agathe Rouillard",
			"Margot Boucher", "Anais Faucher", "Meline Villeneuve",
			"Louane Pierrat", "Lilou Ravel", "Anais Ripoll",
			"Capucine Fraysse", "Eloise Dupuis", "Lily Roman", "Alexia Marc",
			"Ninon Etcheverry", "Leonie Giroux", "Jeanne Diallo", "Zoe Levy",
			"Oceane Cariou", "Oceane Cadot", "Sarah Gallois", "Elise Cortes",
			"Leonie Delage", "Louise Carpentier", "Maeva Billon",
			"Juliette Antoine", "Leane Reboul", "Agathe Prud'homme",
			"Cloe Elie", "Elisa Huchet", "Alexia Gautron", "Cassandra Derouet",
			"Lucie Eloy", "Lisa Boileau", "Maelys Fernandes", "Andrea Campion",
			"Lina Eyraud", "Faustine Puech" };

}
