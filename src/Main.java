import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {

	private static String GAME_NAME = "Swift Shadows";
	
	

	private static BasicGameState playingState = new PlayingState();

	public Main(String name) {
		super(name);
		this.addState(playingState);

		enterState(playingState.getID());
	}

	public static void main(String[] args) throws IOException {
		
		AppGameContainer agc;

		try {
			agc = new AppGameContainer(new Main(GAME_NAME));
			agc.setDisplayMode(Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT, false);
			agc.setShowFPS(true);
			agc.setTargetFrameRate(60);
			agc.setVSync(true);
			agc.start();

		} catch (SlickException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {

	}

}
