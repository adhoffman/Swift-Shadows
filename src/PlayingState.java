import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class PlayingState extends BasicGameState {

	private TiledMap tiledMap;
	private MapScrollerController mapScrollerController;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		tiledMap = new TiledMap("maps/testMap.tmx");
		mapScrollerController = new MapScrollerController(tiledMap);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {

		mapScrollerController.render();

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();

		mapScrollerController.update(input);

	}

	@Override
	public int getID() {
		return 0;
	}

}
