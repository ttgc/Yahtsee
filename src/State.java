import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class State extends BasicGameState {
	protected Image[] dice;
	private Player joueur;
	private IA[] ordi;
	private int ordre;
	private boolean[] selection;
	private boolean game_over;

	public State() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		dice = new Image[6];
		dice[0] = new Image("pictures/Dice1.png");
		dice[1] = new Image("pictures/Dice2.png");
		dice[2] = new Image("pictures/Dice3.png");
		dice[3] = new Image("pictures/Dice4.png");
		dice[4] = new Image("pictures/Dice5.png");
		dice[5] = new Image("pictures/Dice6.png");
		joueur = new Player("Joueur");
		ordi = new IA[3];
		ordi[0] = new IA("Jamy");
		ordi[1] = new IA("Jean-robert");
		ordi[2] = new IA("Jean-michel");
		ordre = 0;
		selection = new boolean[5];
		selection[0] = true;
		selection[1] = true;
		selection[2] = true;
		selection[3] = true;
		selection[4] = true;
		game_over = false;

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.setBackground(new Color(255, 255, 255));
		g.setColor(new Color(0,0,0));
		
		if (ordre == 0) {
			g.drawImage(dice[joueur.getJeu().getDices()[0]-1], 50, 7.5f);
			g.drawImage(dice[joueur.getJeu().getDices()[1]-1], 50, 126);
			g.drawImage(dice[joueur.getJeu().getDices()[2]-1], 50, 244.5f);
			g.drawImage(dice[joueur.getJeu().getDices()[3]-1], 50, 363);
			g.drawImage(dice[joueur.getJeu().getDices()[4]-1], 50, 481.5f);
			g.setColor(new Color(255,0,0));
			float[] posy = {7.5f,126,244.5f,363,481.5f};
			for (int i=0;i<5;i++){
				if (!selection[i]) {
					g.drawRect(50, posy[i], 96, 111);
				}
			}
			if (joueur.getJeu().getReroll() > 0) {
				g.setColor(new Color(0,255,0));
			} else {
				g.setColor(new Color(255,0,0));
			}
		} else {
			g.drawImage(dice[ordi[ordre-1].getJeu().getDices()[0]-1], 50, 7.5f);
			g.drawImage(dice[ordi[ordre-1].getJeu().getDices()[1]-1], 50, 126);
			g.drawImage(dice[ordi[ordre-1].getJeu().getDices()[2]-1], 50, 244.5f);
			g.drawImage(dice[ordi[ordre-1].getJeu().getDices()[3]-1], 50, 363);
			g.drawImage(dice[ordi[ordre-1].getJeu().getDices()[4]-1], 50, 481.5f);
			g.setColor(new Color(255,0,0));
		}
		g.fillRoundRect(10, 126, 30, 348,12);
		g.setColor(new Color(0, 0, 0));
		g.drawString("R\ne\nl\na\nn\nc\ne\n\n", 20, 232);
		
		/**
		 * X repartition			Y repartition
		 * 0-150 : dices			35 unit per category
		 * 150-300 : indic
		 * 300-425 : J
		 * 425-550 : IA1
		 * 550-675 : IA2
		 * 675-800 : IA3
		 */
		
		g.drawLine(300, 0, 300, 600);
		g.drawLine(425, 0, 425, 600);
		g.drawLine(550, 0, 550, 600);
		g.drawLine(675, 0, 675, 600);
		
		g.drawLine(150, 40, 800, 40);
		g.drawLine(150, 75, 800, 75);
		g.drawLine(150, 110, 800, 110);
		g.drawLine(150, 145, 800, 145);
		g.drawLine(150, 180, 800, 180);
		g.drawLine(150, 215, 800, 215);
		g.drawLine(150, 250, 800, 250);
		g.drawLine(150, 285, 800, 285);
		g.drawLine(150, 320, 800, 320);
		g.drawLine(150, 355, 800, 355);
		g.drawLine(150, 390, 800, 390);
		g.drawLine(150, 425, 800, 425);
		g.drawLine(150, 460, 800, 460);
		g.drawLine(150, 495, 800, 495);
		g.drawLine(150, 530, 800, 530);
		g.drawLine(150, 565, 800, 565);
		
		g.drawString("Nombre de 1", 150, 45);
		g.drawString("Nombre de 2", 150, 80);
		g.drawString("Nombre de 3", 150, 115);
		g.drawString("Nombre de 4", 150, 150);
		g.drawString("Nombre de 5", 150, 185);
		g.drawString("Nombre de 6", 150, 220);
		g.drawString("Sous-total", 150, 255);
		g.drawString("Bonus si >63", 150, 290);
		g.drawString("Brelan", 150, 325);
		g.drawString("Carre", 150, 360);
		g.drawString("Full", 150, 395);
		g.drawString("Yahtzee !", 150, 430);
		g.drawString("Petite suite", 150, 465);
		g.drawString("Grande suite", 150, 500);
		g.drawString("Chance", 150, 535);
		g.drawString("Total", 150, 570);
		
		g.drawString(joueur.getName(), 330, 10);
		g.drawString(ordi[0].getName(), 465, 10);
		g.drawString(ordi[1].getName(), 560, 10);
		g.drawString(ordi[2].getName(), 685, 10);
		
		int[] posx = {360,485,610,735};
		int[] posy = {540,50,85,120,155,190,225,330,365,435,400,470,505,295};
		for (int k=0;k<posy.length;k++) {
			//joueur
			if (!joueur.getScore_grid_empty()[k]) {
				g.drawString(Integer.toString(joueur.getScore_grid()[k]), posx[0], posy[k]);
			}
		}
		g.drawString(Integer.toString(joueur.getScore_grid()[1]+joueur.getScore_grid()[2]+joueur.getScore_grid()[3]+joueur.getScore_grid()[4]+joueur.getScore_grid()[5]+joueur.getScore_grid()[6]), posx[0], 260);
		g.drawString(Integer.toString(joueur.getScore()), posx[0], 575);
		for (int i=1;i<posx.length;i++) {
			for (int k=0;k<posy.length;k++) {
				//IA
				if (!ordi[i-1].getScore_grid_empty()[k]) {
					g.drawString(Integer.toString(ordi[i-1].getScore_grid()[k]), posx[i], posy[k]);
				}
			}
			g.drawString(Integer.toString(ordi[i-1].getScore_grid()[1]+ordi[i-1].getScore_grid()[2]+ordi[i-1].getScore_grid()[3]+ordi[i-1].getScore_grid()[4]+ordi[i-1].getScore_grid()[5]+ordi[i-1].getScore_grid()[6]), posx[i], 260);
			g.drawString(Integer.toString(ordi[i-1].getScore()), posx[i], 575);
		}
		
		if (game_over) {
			g.setColor(new Color(255,0,0));
			int[] sc = {joueur.getScore(),ordi[0].getScore(),ordi[1].getScore(),ordi[2].getScore()};
			int max_pos = 0;
			for (int i=0;i<4;i++) {
				if (sc[i] > sc[max_pos]) {
					max_pos = i;
				}
			}
			switch(max_pos) {
			case 0:
				g.drawRect(300, 0, 125, 600);
				break;
			case 1:
				g.drawRect(425, 0, 125, 600);
				break;
			case 2:
				g.drawRect(550, 0, 125, 600);
				break;
			case 3:
				g.drawRect(675, 0, 125, 600);
				break;
			}
			g.setColor(new Color(0,0,0));
		}
				
	}//Image 96x111

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		// TODO Auto-generated method stub
		super.mouseClicked(button, x, y, clickCount);
		if (button == Input.MOUSE_LEFT_BUTTON) {
			if (ordre == 0 && x >= 50 && x <= 146) { //dés
				if (y >= 7.5f && y <= 118.5f) {
					if (selection[0]) {
						selection[0] = false;
					} else {
						selection[0] = true;
					}
				} else if (y >= 126 && y <= 237) {
					if (selection[1]) {
						selection[1] = false;
					} else {
						selection[1] = true;
					}
				} else if (y >= 244.5f && y <= 355.5f) {
					if (selection[2]) {
						selection[2] = false;
					} else {
						selection[2] = true;
					}
				} else if (y >= 363 && y <= 474) {
					if (selection[3]) {
						selection[3] = false;
					} else {
						selection[3] = true;
					}
				} else if (y >= 481.5f && y <= 592.5f) {
					if (selection[4]) {
						selection[4] = false;
					} else {
						selection[4] = true;
					}
				}
			} else if (ordre == 0 && x >= 10 && x <= 40 && y >= 126 && y <= 474 && joueur.getJeu().getReroll() > 0) {
				joueur.tour(selection);
			} else if (ordre == 0 && x >= 150 && x <= 800) {
				int verif = 0;
				if (y >= 40 && y < 75) {
					verif = joueur.insert_score(1);
					joueur.insert_score(13);
				} else if (y >= 75 && y < 110) {
					verif = joueur.insert_score(2);
					joueur.insert_score(13);
				} else if (y >= 110 && y < 145) {
					verif = joueur.insert_score(3);
					joueur.insert_score(13);
				} else if (y >= 145 && y < 180) {
					verif = joueur.insert_score(4);
					joueur.insert_score(13);
				} else if (y >= 180 && y < 215) {
					verif = joueur.insert_score(5);
					joueur.insert_score(13);
				} else if (y >= 215 && y < 250) {
					verif = joueur.insert_score(6);
					joueur.insert_score(13);
				} else if (y >= 320 && y < 355) {
					verif = joueur.insert_score(7);
				} else if (y >= 355 && y < 390) {
					verif = joueur.insert_score(8);
				} else if (y >= 390 && y < 425) {
					verif = joueur.insert_score(10);
				} else if (y >= 425 && y < 460) {
					verif = joueur.insert_score(9);
				} else if (y >= 460 && y < 495) {
					verif = joueur.insert_score(11);
				} else if (y >= 495 && y < 530) {
					verif = joueur.insert_score(12);
				} else if (y >= 530 && y < 565) {
					verif = joueur.insert_score(0);
				}
				
				if (verif != -1  && y >= 40 && !(y >= 250 && y < 320) && y < 565) {
					for (int i=0;i<3;i++) {
						ordre = i+1;
						ordi[i].action();
					}
					game_over = true;
					for (int i=0;i<joueur.getScore_grid_empty().length-1;i++) {
						if (joueur.getScore_grid_empty()[i]) {
							game_over = false;
							break;
						}
					}
					if (!game_over) {
						ordre = 0;
						for (int i=0;i<5;i++) {
							selection[i] = true;
						}
						joueur.reset();
					}
				}
			}
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
