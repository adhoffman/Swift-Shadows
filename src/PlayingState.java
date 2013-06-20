import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PlayingState extends BasicGameState {

	private Rectangle playerRec;
	private Player player;
	

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		playerRec = new Rectangle(100, 100, 50, 50);
		player=new Player(playerRec);


	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {
		gr.setColor(Color.black);
		gr.drawRect((float)playerRec.getX(), (float)playerRec.getY(), (float)playerRec.getWidth(),(float) playerRec.getHeight());

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		player.update(gc.getInput(), delta);
		
		
		

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
