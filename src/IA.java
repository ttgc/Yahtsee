public class IA {
	private DiceSet jeu;
	private int[] score_grid;
	private boolean[] score_grid_empty;
	private int score;
	private String name;
	
	/**
	 * Score position :
	 * 0 = chance
	 * 1 to 6 = dice x score (with x = position)
	 * 7 = brelan
	 * 8 = carre
	 * 9 = yams
	 * 10 = full
     * 11 = suite
	 * 12 = grande suite
	 * 13 = bonus high table
	 */
	
	public IA(String name) {
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
	
	public void action() {
		boolean[] dice_ctrl = {true,true,true,true,true};
		boolean score_set = false;
		
		while (jeu.getReroll() > 0 && (!score_set)) {
			if (jeu.getYams()) {
				if (score_grid_empty[9]) {
					score_set = true;
					continue;
				}
			} 
			if (jeu.getFull()) {
				if (score_grid_empty[10]) {
					score_set = true;
					continue;
				}
			}
			if (jeu.getGrd_suite()) {
				if (score_grid_empty[12]) {
					score_set = true;
					continue;
				}
			}
			
			int[] count = {0,0,0,0,0,0};
			int max = 0;
			for (int i=0;i<5;i++) {
				count[jeu.getDices()[i]-1]++;
			}
			for (int i=0;i<6;i++) {
				if (count[i] > count[max]) {
					max = i;
				}
			}
			
			if (jeu.getBrelan() && (score_grid_empty[7] || score_grid_empty[8] || score_grid_empty[9])) {
				int brval = 0;
				for (int i=0;i<6;i++) {
					if (count[i] >= 3) {
						brval = i+1;
						break;
					}
				}
				for (int i=0;i<5;i++) {
					if (jeu.getDices()[i] == brval) {
						dice_ctrl[i] = false;
					}
				}
			} else if (jeu.getSuite()) {
				int startsuite = 0;
				for (int i=0;i<3;i++) {
					if (count[i] >= 1 && count[i+1] >= 1 && count[i+2] >= 1 && count[i+3] >= 1) {
						startsuite = i+1;
						break;
					}
				}
				for (int i=0;i<5;i++) {
					if ((jeu.getDices()[i] != startsuite && jeu.getDices()[i] != startsuite+1 && jeu.getDices()[i] != startsuite+2 && jeu.getDices()[i] != startsuite+3) || (count[jeu.getDices()[i]-1] >= 2) ) {
						dice_ctrl[i] = false;
						break;
					}
				}
			} else if (count[max] == 2 && (score_grid_empty[7] || score_grid_empty[9] || score_grid_empty[8] || score_grid_empty[10])){
				boolean rcfull = false;
				int valp1=max+1,valp2=0;
				for (int i=0;i<6;i++) {
					if (!score_grid_empty[10]) {
						break;
					}
					if (i == max) {
						continue;
					}
					if (count[i] == 2) {
						rcfull = true;
						valp2 = i+1;
						break;
					}
				}
				if (rcfull) {
					for (int i=0;i<5;i++) {
						if (jeu.getDices()[i] != valp1 && jeu.getDices()[i] != valp2) {
							dice_ctrl[i] = false;
							break;
						}
					}
				} else {
					for (int i=0;i<5;i++) {
						if (jeu.getDices()[i] != valp1) {
							dice_ctrl[i] = false;
						}
					}
				}
			} else if (score_grid_empty[11] || score_grid_empty[12]) {
				for (int i=0;i<5;i++) {
					if (count[jeu.getDices()[i]-1] >= 2) {
						dice_ctrl[i] = false;
						count[jeu.getDices()[i]-1]--;
					}
					if (i>1 && count[jeu.getDices()[i-1]-1] == 0 && count[max] == 1) {
						dice_ctrl[i] = false;
					}
					if (i<4 && count[jeu.getDices()[i+1]-1] == 0 && count[max] == 1) {
						dice_ctrl[i] = false;
					}
				}
			}
			
			jeu.roll(dice_ctrl);
			for (int i=0;i<5;i++){
				dice_ctrl[i] = true;
			}
		}
		
		boolean finish = false;
		int verif = 0;
		if (jeu.getYams()) {
			verif = insert_score(9);
			if (verif >= 0) {
				finish = true;
			}
		} 
		if (jeu.getCarre() && (!finish)) {
			verif = insert_score(8);
			if (verif >= 0) {
				finish = true;
			}
		}
		if (jeu.getGrd_suite() && (!finish)) {
			verif = insert_score(12);
			if (verif >= 0) {
				finish = true;
			}
		}
		if (jeu.getSuite() && (!finish)) {
			verif = insert_score(11);
			if (verif >= 0) {
				finish = true;
			}
		}
		if (jeu.getFull() && (!finish)) {
			verif = insert_score(10);
			if (verif >= 0) {
				finish = true;
			}
		}
		if (jeu.getBrelan() && (!finish)) {
			verif = insert_score(7);
			if (verif >= 0) {
				finish = true;
			}
		}
		if (jeu.getCumul() > 15 && (!finish)) {
			verif = insert_score(0);
			if (verif >= 0) {
				finish = true;
			}
		}
		int i = 1;
		while ((!finish) && i <= 6) {
			verif = insert_score(i);
			if (verif >= 0) {
				insert_score(13);
				finish = true;
			}
			i++;
		}
		if (!finish) {
			int[] priority = {0,9,8,10,12,7,11};
			for (i=0;i<priority.length;i++) {
				verif = insert_score(priority[i]);
				if (verif != -1) {
					finish = true;
					break;
				}
			}
		}
	}
	
	//getters
	public DiceSet getJeu() { return jeu; }
	public int[] getScore_grid() { return score_grid; }
	public boolean[] getScore_grid_empty() { return score_grid_empty; }
	public int getScore() { return score; }
	public String getName() { return name; }

}
