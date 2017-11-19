
public class Player {
	private DiceSet jeu;
	private int[] score_grid;
	private boolean[] score_grid_empty;
	private int score;
	private String name;
	
	/**
	 * Score position :
	 * 0 = chance
	 * 1 to 6 = dice x score (with x = position)
	 * 7 = 7 = brelan
	 * 8 = carre
	 * 9 = yams
	 * 10 = full
     * 11 = suite
	 * 12 = grande suite
	 * 13 = bonus high table
	 */
	
	public Player(String name) {
		this.name = name;
		score = 0;
		score_grid = new int[14];
		score_grid_empty = new boolean[14];
		for (int i=0;i<14;i++) {
			score_grid[i] = 0;
			score_grid_empty[i] = true;
		}
		jeu = new DiceSet();
	}
	
	public int insert_score(int position) {
		if (!score_grid_empty[position]) {
			return -1;
		}
		
		if (position == 13) {
			int cumul = 0;
			for (int i=1;i<=6;i++) {
				cumul += score_grid[i];
			}
			if (cumul >= 63) {
				score_grid[13] = 35;
				score_grid_empty[13] = false;
				score += 35;
				return 35;
			} else {
				return 0;
			}
		}
		
		int sc = jeu.calculate_score(position);
		score_grid[position] = sc;
		score_grid_empty[position] = false;
		score += sc;
		return sc;
	}
	
	public void tour(boolean[] rolled) {
		jeu.roll(rolled);
	}
	
	public void reset() {
		jeu = new DiceSet();
	}
	
	//getters
	public DiceSet getJeu() { return jeu; }
	public int[] getScore_grid() { return score_grid; }
	public boolean[] getScore_grid_empty() { return score_grid_empty; }
	public int getScore() { return score; }
	public String getName() { return name; }

}
