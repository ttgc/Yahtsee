import java.util.Arrays;
import java.util.Random;

public class DiceSet {
	private int[] dices;
	private boolean brelan;
	private boolean carre;
	private boolean full;
	private boolean yams;
	private boolean suite;
	private boolean grd_suite;
	private int reroll;
	private int cumul;
	private Random rdm;
	
	public DiceSet() {
		dices = new int[5];
		brelan = false;
		carre = false;
		full = false;
		yams = false;
		suite = false;
		grd_suite = false;
		reroll = 2;
		cumul = 0;
		rdm = new Random();
		
		for (int i=0;i<5;i++) {
			dices[i] = rdm.nextInt(6)+1;
			cumul += dices[i];
		}
		
		verification();
	}
	
	private void brelan_to_yams(int[] count) {
		yams = false;
		carre = false;
		brelan = false;
		for (int i=0;i<6;i++){
			switch (count[i]) {
			case 5:
				yams = true;
			case 4:
				carre = true;
			case 3:
				brelan = true;
				break;
			}
			if (brelan) {
				break;
			}
		}
	}
	
	private void suite_check(int[] count) {
		suite = false;
		grd_suite = false;
		int[] config1 = {1,1,1,1,1,0};
		int[] config2 = {0,1,1,1,1,1};
		if (Arrays.equals(count, config1) || Arrays.equals(count, config2)) {
			grd_suite = true;
			suite = true;
		} else {
			for (int i=0;i<3;i++) {
				if (count[i] >= 1 && count[i+1] >= 1 && count[i+2] >= 1 && count[i+3] >= 1) {
					suite = true;
					break;
				}
			}
		}
	}
	
	public void verification() {
		int[] count = {0,0,0,0,0,0};
		for (int i=0;i<5;i++) {
			count[dices[i]-1]++;
		}
		
		brelan_to_yams(count);
		
		full = false;
		if (brelan && (!carre)) {
			for (int i=0;i<6;i++){
				if (count[i] == 2) {
					full = true;
				}
			}
		}
		
		suite_check(count);
	}
	
	public int roll(boolean[] rolled) {
		if (reroll <= 0) {
			return -1;
		}
		reroll--;
		
		for (int i=0;i<5;i++) {
			if (rolled[i]) {
				cumul -= dices[i];
				dices[i] = rdm.nextInt(6)+1;
				cumul += dices[i];
			}
		}
		
		verification();
		return reroll;
	}
	
	public int calculate_score(int combi) {
		/**
		 * combi values
		 * 0 = chance
		 * 1 to 6 = number of x (with x = combi value)
		 * 7 = brelan
		 * 8 = carre
		 * 9 = yams
		 * 10 = full
		 * 11 = suite
		 * 12 = grande suite
		 */
		int[] count = {0,0,0,0,0,0};
		for (int i=0;i<5;i++) {
			count[dices[i]-1]++;
		}
		
		switch (combi) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			return count[combi-1]*combi;
		case 7:
			if (brelan) {
				return cumul;
			}
			return 0;
		case 8:
			if (carre) {
				return cumul;
			}
			return 0;
		case 9:
			if(yams) {
				return 50;
			}
			return 0;
		case 10:
			if(full) {
				return 25;
			}
			return 0;
		case 11:
			if(suite) {
				return 30;
			}
			return 0;
		case 12:
			if(grd_suite) {
				return 40;
			}
			return 0;
		default:
			return cumul;
		}
	}
	
	//getters
	public int[] getDices() { return dices; }
	public boolean getBrelan() { return brelan; }
	public boolean getCarre() { return carre; }
	public boolean getYams() { return yams; }
	public boolean getFull() { return full; }
	public boolean getSuite() { return suite; }
	public boolean getGrd_suite() { return grd_suite; }
	public int getReroll() { return reroll; }
	public int getCumul() { return cumul; }
	public Random getRdm() { return rdm; }

}
