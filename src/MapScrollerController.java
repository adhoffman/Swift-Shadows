import org.newdawn.slick.tiled.TiledMap;

public class MapScrollerController {

	private TiledMap tiledMap;
	private int x;
	private int y;
	private int totalPixelWidth;
	private int totalPixelHeight;
	

	public MapScrollerController(TiledMap tiledMap) {

		this.tiledMap = tiledMap;
		y = 0;
		x = 0;
		totalPixelWidth = tiledMap.getWidth() * Constant.BLOCK_SIZE;
		totalPixelHeight = tiledMap.getHeight() * Constant.BLOCK_SIZE;
		
		

	}


	public void update(Point point){
		
		
		checkPlayerLocationInScreenLocations();
		
		
		if(point.getX()>0 && canMoveRight())
			x -= Constant.SCREEN_SCROLL_DISTANCE;
		
		if(point.getX()<0 && canMoveLeft())
			x += Constant.SCREEN_SCROLL_DISTANCE;
		
		if(point.getY()<0 && canMoveUp())
			y += Constant.SCREEN_SCROLL_DISTANCE;
		
		if(point.getY()>0 && canMoveDown())
			y -= Constant.SCREEN_SCROLL_DISTANCE;
		
	}
	
	private void checkPlayerLocationInScreenLocations() {
		// TODO Auto-generated method stub
		
	}


	public void render() {

		tiledMap.render(x, y, 0, 0, tiledMap.getWidth(), tiledMap.getHeight());
	}
	

	private boolean canMoveRight() {
		if ((x > -(totalPixelWidth - Constant.SCREEN_WIDTH)))
			return true;
		else
			return false;
	}

	private boolean canMoveLeft() {
		if ((x < 0))
			return true;
		else
			return false;
	}

	private boolean canMoveDown() {
		if ((y > -(totalPixelHeight - Constant.SCREEN_HEIGHT)))
			return true;
		else
			return false;
	}

	private boolean canMoveUp() {
		if ((y < 0))
			return true;
		else
			return false;
	}

}
