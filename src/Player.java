import java.awt.Rectangle;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Player {
	

	private Rectangle playerRec;
	private Image player;
	private int x;
	private int y;

	private boolean[][] blocked;
	private int blockSize;
	private int playerRecHeight;
	private int playerRecWidth;
	
	private TiledMap tiledMap;
	
	
	public Player(TiledMap tiledMap) throws SlickException{
		
		this.tiledMap=tiledMap;
		
		
		playerRecHeight = 48;
		playerRecWidth = 32;
		player = new Image("res/sneak.png");
		playerRec = new Rectangle();
		playerRec.setBounds(x, y, playerRecWidth, playerRecHeight);
		
		x = 50;
		y = 50;

		blockSize = 16;
		
		
		fillBoolMap();
		
	}
	
	public void render(){
		player.draw(x, y);
	}
	
	public void update(Input input){
		
		
		if (input.isKeyDown(Input.KEY_UP)) {
			if (!isBlockedUp(x, y))
				y -= Constant.SCREEN_SCROLL_DISTANCE;
		}

		if (input.isKeyDown(Input.KEY_DOWN)) {
			if (!isBlockedDown(x, y))
				y += Constant.SCREEN_SCROLL_DISTANCE;
		}

		if (input.isKeyDown(Input.KEY_LEFT)) {
			if (!isBlockedLeft(x, y))
				x -= Constant.SCREEN_SCROLL_DISTANCE;
		}
		
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			if (!isBlockedRight(x, y))
				x += Constant.SCREEN_SCROLL_DISTANCE;
		}
		
	}
	
	private void fillBoolMap() {
		blocked = new boolean[tiledMap.getWidth()][tiledMap.getHeight()];

		for (int xAxis = 0; xAxis < tiledMap.getWidth(); xAxis++) {
			for (int yAxis = 0; yAxis < tiledMap.getHeight(); yAxis++) {

				int tileID = tiledMap.getTileId(xAxis, yAxis, 0);
				String value = tiledMap.getTileProperty(tileID, "blocked",
						"false");
				System.out.print(value + "||");

				if ("true".equals(value)) {
					blocked[xAxis][yAxis] = true;
				}

			}
			System.out.println();
		}

	}
	
	private boolean isBlockedRight(float x, float y) {
		if (checkTopRightCorner(x, y) || checkBottomRightCorner(x, y)
				|| checkMidpointOneRight(x, y) || checkMidpointTwoRight(x, y)
				|| checkMidpointThreeRight(x, y))
			return true;
		else
			return false;

	}

	private boolean isBlockedLeft(float x, float y) {
		if (checkTopLeftCorner(x, y) || checkBottomLefttCorner(x, y)
				|| checkMidpointOneLeft(x, y) || checkMidpointTwoLeft(x, y)
				|| checkMidpointThreeLeft(x, y))
			return true;
		else
			return false;

	}

	private boolean isBlockedDown(float x, float y) {
		if (checkMidpointOneBot(x, y) || checkMidpointTwoBot(x, y)
				|| checkMidpointThreeBot(x, y))
			return true;
		else
			return false;

	}

	private boolean isBlockedUp(float x, float y) {
		if (checkMidpointOneTop(x, y) || checkMidpointTwoTop(x, y)
				|| checkMidpointThreeTop(x, y))
			return true;
		else
			return false;

	}

	private boolean checkMidpointThreeTop(float x, float y) {
		int xBlock = (int) (x += 24) / blockSize;
		int yBlock = (int) (y -= Constant.SCREEN_SCROLL_DISTANCE) / blockSize;
		return blocked[xBlock][yBlock];
	}

	private boolean checkMidpointTwoTop(float x, float y) {
		int xBlock = (int) (x += 16) / blockSize;
		int yBlock = (int) (y -= Constant.SCREEN_SCROLL_DISTANCE) / blockSize;
		return blocked[xBlock][yBlock];
	}

	private boolean checkMidpointOneTop(float x, float y) {
		int xBlock = (int) (x += 8) / blockSize;
		int yBlock = (int) (y -= Constant.SCREEN_SCROLL_DISTANCE) / blockSize;
		return blocked[xBlock][yBlock];
	}

	private boolean checkMidpointTwoRight(float x, float y) {
		int xBlock = (int) (x += Constant.SCREEN_SCROLL_DISTANCE
				+ playerRec.getWidth())
				/ blockSize;
		int yBlock = (int) (y += 36) / blockSize;

		return (blocked[xBlock][yBlock]);
	}

	private boolean checkMidpointThreeRight(float x, float y) {
		int xBlock = (int) (x += Constant.SCREEN_SCROLL_DISTANCE
				+ playerRec.getWidth())
				/ blockSize;
		int yBlock = (int) (y += 24) / blockSize;

		return (blocked[xBlock][yBlock]);
	}

	private boolean checkMidpointOneRight(float x, float y) {
		int xBlock = (int) (x += Constant.SCREEN_SCROLL_DISTANCE
				+ playerRec.getWidth())
				/ blockSize;
		int yBlock = (int) (y += 12) / blockSize;

		return (blocked[xBlock][yBlock]);
	}

	private boolean checkBottomRightCorner(float x, float y) {
		int xBlock = (int) (x += Constant.SCREEN_SCROLL_DISTANCE
				+ playerRec.getWidth())
				/ blockSize;
		int yBlock = (int) (y += playerRec.getHeight()) / blockSize;

		return (blocked[xBlock][yBlock]);
	}

	private boolean checkTopRightCorner(float x, float y) {
		int xBlock = (int) (x += Constant.SCREEN_SCROLL_DISTANCE
				+ playerRec.getWidth())
				/ blockSize;
		int yBlock = (int) y / blockSize;

		return (blocked[xBlock][yBlock]);
	}

	private boolean checkMidpointThreeLeft(float x, float y) {
		int xBlock = (int) (x -= Constant.SCREEN_SCROLL_DISTANCE) / blockSize;
		int yBlock = (int) (y += 36) / blockSize;
		return blocked[xBlock][yBlock];
	}

	private boolean checkMidpointTwoLeft(float x, float y) {
		int xBlock = (int) (x -= Constant.SCREEN_SCROLL_DISTANCE) / blockSize;
		int yBlock = (int) (y += 24) / blockSize;
		return blocked[xBlock][yBlock];
	}

	private boolean checkMidpointOneLeft(float x, float y) {
		int xBlock = (int) (x -= Constant.SCREEN_SCROLL_DISTANCE) / blockSize;
		int yBlock = (int) (y += 12) / blockSize;
		return blocked[xBlock][yBlock];
	}

	private boolean checkBottomLefttCorner(float x, float y) {
		int xBlock = (int) (x -= Constant.SCREEN_SCROLL_DISTANCE) / blockSize;
		int yBlock = (int) (y += playerRec.getHeight()) / blockSize;
		return blocked[xBlock][yBlock];
	}

	private boolean checkTopLeftCorner(float x, float y) {
		int xBlock = (int) (x -= Constant.SCREEN_SCROLL_DISTANCE) / blockSize;
		int yBlock = (int) y / blockSize;
		return blocked[xBlock][yBlock];
	}

	private boolean checkMidpointThreeBot(float x, float y) {
		int xBlock = (int) (x += 24) / blockSize;
		int yBlock = (int) (y += Constant.SCREEN_SCROLL_DISTANCE
				+ playerRec.getHeight())
				/ blockSize;
		return blocked[xBlock][yBlock];
	}

	private boolean checkMidpointTwoBot(float x, float y) {
		int xBlock = (int) (x += 16) / blockSize;
		int yBlock = (int) (y += Constant.SCREEN_SCROLL_DISTANCE
				+ playerRec.getHeight())
				/ blockSize;
		return blocked[xBlock][yBlock];
	}

	private boolean checkMidpointOneBot(float x, float y) {
		int xBlock = (int) (x += 8) / blockSize;
		int yBlock = (int) (y += Constant.SCREEN_SCROLL_DISTANCE
				+ playerRec.getHeight())
				/ blockSize;
		return blocked[xBlock][yBlock];
	}
}
