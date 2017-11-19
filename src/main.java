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
		
		/*boolean[] rl = {true,true,true,true,true};
        
        DiceSet ds = new DiceSet();
        System.out.println(Arrays.toString(ds.getDices()));
        System.out.println(ds.getReroll()+" - "+ds.getCumul());
        for (int i=0;i<=13;i++) {
            System.out.println(ds.calculate_score(i));
        }
        ds.roll(rl);
        //rl[2] = false;
        System.out.println(Arrays.toString(ds.getDices()));
        System.out.println(ds.getReroll()+" - "+ds.getCumul());
        for (int i=0;i<=13;i++) {
            System.out.println(ds.calculate_score(i));
        }
        ds.roll(rl);
        rl[1] = false;
        rl[4] = false;
        rl[2] = true;
        System.out.println(Arrays.toString(ds.getDices()));
        System.out.println(ds.getReroll()+" - "+ds.getCumul());
        for (int i=0;i<=13;i++) {
            System.out.println(ds.calculate_score(i));
        }
        ds.roll(rl);
        System.out.println(Arrays.toString(ds.getDices()));
        System.out.println(ds.getReroll()+" - "+ds.getCumul());
        for (int i=0;i<=13;i++) {
            System.out.println(ds.calculate_score(i));
        }
        ds.roll(rl);*/

	}

}
