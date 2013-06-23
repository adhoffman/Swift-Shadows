package adhoffman.swiftshadows;

import org.newdawn.slick.Input;
import org.newdawn.slick.tiled.TiledMap;

public class MapScrollerController {

	private TiledMap tiledMap;
	private int x;
	private int y;
	private int totalPixelWidth;
	private int totalPixelHeight;
	private int pixelBlock;

	public MapScrollerController(TiledMap tiledMap) {

		this.tiledMap = tiledMap;
		y = 0;
		x = 0;
		pixelBlock = 16;
		totalPixelWidth = tiledMap.getWidth() * pixelBlock;
		totalPixelHeight = tiledMap.getHeight() * pixelBlock;

	}

	public void update(Input input) {

		if (input.isKeyDown(Input.KEY_UP)) {
			if (canMoveUp())
				y += Constant.SCREEN_SCROLL_DISTANCE;
		}

		if (input.isKeyDown(Input.KEY_DOWN)) {
			if (canMoveDown())
				y -= Constant.SCREEN_SCROLL_DISTANCE;
		}

		if (input.isKeyDown(Input.KEY_LEFT)) {
			if (canMovelLeft())
				x += Constant.SCREEN_SCROLL_DISTANCE;
		}

		if (input.isKeyDown(Input.KEY_RIGHT)) {
			if (canMoveRight())
				x -= Constant.SCREEN_SCROLL_DISTANCE;
		}

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

	private boolean canMovelLeft() {
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
