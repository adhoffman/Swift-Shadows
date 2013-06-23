package adhoffman.swiftshadows;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class PlayingState extends BasicGameState {

	private Player player;
	private StartingPoint startingPoint;
	private TiledMap tiledMap;
	private MapScrollerController mapScrollerController;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

		tiledMap = new TiledMap("maps/testMap.tmx");

		mapScrollerController = new MapScrollerController(tiledMap);
		startingPoint = new StartingPoint(50, 50);

		player = new Player(tiledMap, startingPoint);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		mapScrollerController.render();
		player.render();

		g.setColor(Color.white);
		g.drawString("FPS: " + gc.getFPS(), 10, 10);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();

		player.update(input, delta);
		//mapScrollerController.update(input);
	}

	@Override
	public int getID() {
		return Constant.STATE_PLAYING;
	}

}
