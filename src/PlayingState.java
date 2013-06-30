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
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		tiledMap = new TiledMap("maps/testMap.tmx");
		
		mapScrollerController = new MapScrollerController(tiledMap);
		startingPoint=new StartingPoint(50,50);
		
		
		player=new Player(tiledMap,startingPoint);

	}

	

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gr)
			throws SlickException {

		mapScrollerController.render();
		player.render();

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		
		
		player.update(input,delta);
		//mapScrollerController.update(player.broadcastLocation());

		

	}

	@Override
	public int getID() {
		return 0;
	}

}
