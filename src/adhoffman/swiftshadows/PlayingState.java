package adhoffman.swiftshadows;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class PlayingState extends BasicGameState {

	private Player player;
	private TiledMap tiledMap;
	private MapScrollerController mapScrollerController;
	private Camera camera = new Camera(0, 0, 3);

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		tiledMap = new TiledMap("maps/testMap.tmx");

		mapScrollerController = new MapScrollerController(tiledMap);

		player = new Player(tiledMap, new Vector2f(50, 50));
		camera.follow(player.position, new Vector2f(-player.image.getWidth() / 2, -player.image.getHeight() / 2f), true);
		camera.bind(20, 20, 680, 900);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.translate(-camera.getX(), -camera.getY());

		tiledMap.render(0, 0, 0, 0, tiledMap.getWidth(), tiledMap.getHeight());
		player.render();

		g.translate(camera.getX(), camera.getY());

		g.setColor(Color.white);
		g.drawString("FPS: " + gc.getFPS(), 10, 10);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();

		player.update(input, delta);
		camera.update(delta);

		if (input.isKeyPressed(Input.KEY_SPACE)) {
			camera.toggleLockedOnTarget();
		}
		//mapScrollerController.update(input);
	}

	@Override
	public int getID() {
		return Constant.STATE_PLAYING;
	}

}
