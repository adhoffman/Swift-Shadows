package adhoffman.swiftshadows;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {

	private static String GAME_NAME = "Swift Shadows";

	public Main(String name) {
		super(name);
	}

	public static void main(String[] args) throws SlickException {
		// The game scales while still keeping the aspect ratio!
		AppGameContainer agc = new AppGameContainer(new ScalableGame(new Main(GAME_NAME), Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT, true));
		agc.setResizable(true);

		// Bug in ScalableGame has issue with rendering fps, will be done manually!
		agc.setShowFPS(false);

		// no need for vsync in windowed mode
		agc.setVSync(false);

		agc.setDisplayMode(Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT, false);
		agc.setTargetFrameRate(60);

		agc.start();
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		// This method is for adding our initial game states.
		// Slick will initialize them by itself when starting the game.
		// The first state will also always be the default state.
		addState(new PlayingState());
	}

}
