import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class main extends StateBasedGame {

	public main(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		addState(new State());

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AppGameContainer app;
		try {
			app = new AppGameContainer(new main("Yahtzee !"));
			app.setDisplayMode(800, 600, false);
			app.setShowFPS(false);
			app.start();
		} catch(SlickException e){
			e.printStackTrace();
		}

	}

}
