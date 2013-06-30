import java.awt.Rectangle;

import org.newdawn.slick.tiled.TiledMap;


public class PlayerMapCollisionChecker {

	private Rectangle playerRec;
	private TiledMap tiledMap;


	private boolean[][] blocked;
	
	
	public PlayerMapCollisionChecker(TiledMap tiledMap,StartingPoint startingPoint){
		this.tiledMap=tiledMap;
	
			
		playerRec = new Rectangle();
		playerRec.setBounds(startingPoint.getX(), startingPoint.getY(), Constant.PLAYER_REC_WIDTH, Constant.PLAYER_REC_HEIGHT);
		

		fillBoolMap();
	}
	
	
	private void fillBoolMap() {
		blocked = new boolean[tiledMap.getWidth()][tiledMap.getHeight()];

		for (int xAxis = 0; xAxis < tiledMap.getWidth(); xAxis++) {
			for (int yAxis = 0; yAxis < tiledMap.getHeight(); yAxis++) {

				int tileID = tiledMap.getTileId(xAxis, yAxis, 0);
				String value = tiledMap.getTileProperty(tileID, "blocked",
						"false");
				

				if ("true".equals(value)) {
					blocked[xAxis][yAxis] = true;
				}

			}
		
		}

	}
	
	public boolean isNotBlockedInAnyDirection(float x, float y){
		if(!isBlockedRight(x, y) && !isBlockedLeft(x, y) && !isBlockedDown(x, y) && !isBlockedUp(x, y))
			return true;
		else
			return false;
	}
	
	public boolean isBlockedRight(float x, float y) {
		if (checkTopRightCorner(x, y) || checkBottomRightCorner(x, y)
				|| checkMidpointOneRight(x, y) || checkMidpointTwoRight(x, y)
				|| checkMidpointThreeRight(x, y))
			return true;
		else
			return false;

	}

	public boolean isBlockedLeft(float x, float y) {
		if (checkTopLeftCorner(x, y) || checkBottomLefttCorner(x, y)
				|| checkMidpointOneLeft(x, y) || checkMidpointTwoLeft(x, y)
				|| checkMidpointThreeLeft(x, y))
			return true;
		else
			return false;

	}

	public boolean isBlockedDown(float x, float y) {
		if (checkMidpointOneBot(x, y) || checkMidpointTwoBot(x, y)
				|| checkMidpointThreeBot(x, y))
			return true;
		else
			return false;

	}

	public boolean isBlockedUp(float x, float y) {
		if (checkMidpointOneTop(x, y) || checkMidpointTwoTop(x, y)
				|| checkMidpointThreeTop(x, y))
			return true;
		else
			return false;

	}

	
	//TODO
	
	//Remove SCREEN_SCROLL_DISTANCE from collision checker and replace it with deltaX, deltaY
	private boolean checkMidpointThreeTop(float x, float y) {
		int xBlock = (int) (x += 24) / Constant.BLOCK_SIZE;
		int yBlock = (int) (y -= Constant.SCREEN_SCROLL_DISTANCE) / Constant.BLOCK_SIZE;
		return blocked[xBlock][yBlock];
	}

	private boolean checkMidpointTwoTop(float x, float y) {
		int xBlock = (int) (x += 16) / Constant.BLOCK_SIZE;
		int yBlock = (int) (y -= Constant.SCREEN_SCROLL_DISTANCE) /Constant.BLOCK_SIZE;
		return blocked[xBlock][yBlock];
	}

	private boolean checkMidpointOneTop(float x, float y) {
		int xBlock = (int) (x += 8) / Constant.BLOCK_SIZE;
		int yBlock = (int) (y -= Constant.SCREEN_SCROLL_DISTANCE) / Constant.BLOCK_SIZE;
		return blocked[xBlock][yBlock];
	}

	private boolean checkMidpointTwoRight(float x, float y) {
		int xBlock = (int) (x += Constant.SCREEN_SCROLL_DISTANCE
				+ playerRec.getWidth())
				/ Constant.BLOCK_SIZE;
		int yBlock = (int) (y += 36) / Constant.BLOCK_SIZE;

		return (blocked[xBlock][yBlock]);
	}

	private boolean checkMidpointThreeRight(float x, float y) {
		int xBlock = (int) (x += Constant.SCREEN_SCROLL_DISTANCE
				+ playerRec.getWidth())
				/ Constant.BLOCK_SIZE;
		int yBlock = (int) (y += 24) / Constant.BLOCK_SIZE;

		return (blocked[xBlock][yBlock]);
	}

	private boolean checkMidpointOneRight(float x, float y) {
		int xBlock = (int) (x += Constant.SCREEN_SCROLL_DISTANCE
				+ playerRec.getWidth())
				/ Constant.BLOCK_SIZE;
		int yBlock = (int) (y += 12) / Constant.BLOCK_SIZE;

		return (blocked[xBlock][yBlock]);
	}

	private boolean checkBottomRightCorner(float x, float y) {
		int xBlock = (int) (x += Constant.SCREEN_SCROLL_DISTANCE
				+ playerRec.getWidth())
				/ Constant.BLOCK_SIZE;
		int yBlock = (int) (y += playerRec.getHeight()) / Constant.BLOCK_SIZE;

		return (blocked[xBlock][yBlock]);
	}

	private boolean checkTopRightCorner(float x, float y) {
		int xBlock = (int) (x += Constant.SCREEN_SCROLL_DISTANCE
				+ playerRec.getWidth())
				/ Constant.BLOCK_SIZE;
		int yBlock = (int) y / Constant.BLOCK_SIZE;

		return (blocked[xBlock][yBlock]);
	}

	private boolean checkMidpointThreeLeft(float x, float y) {
		int xBlock = (int) (x -= Constant.SCREEN_SCROLL_DISTANCE) / Constant.BLOCK_SIZE;
		int yBlock = (int) (y += 36) / Constant.BLOCK_SIZE;
		return blocked[xBlock][yBlock];
	}

	private boolean checkMidpointTwoLeft(float x, float y) {
		int xBlock = (int) (x -= Constant.SCREEN_SCROLL_DISTANCE) / Constant.BLOCK_SIZE;
		int yBlock = (int) (y += 24) / Constant.BLOCK_SIZE;
		return blocked[xBlock][yBlock];
	}

	private boolean checkMidpointOneLeft(float x, float y) {
		int xBlock = (int) (x -= Constant.SCREEN_SCROLL_DISTANCE) / Constant.BLOCK_SIZE;
		int yBlock = (int) (y += 12) / Constant.BLOCK_SIZE;
		return blocked[xBlock][yBlock];
	}

	private boolean checkBottomLefttCorner(float x, float y) {
		int xBlock = (int) (x -= Constant.SCREEN_SCROLL_DISTANCE) / Constant.BLOCK_SIZE;
		int yBlock = (int) (y += playerRec.getHeight()) / Constant.BLOCK_SIZE;
		return blocked[xBlock][yBlock];
	}

	private boolean checkTopLeftCorner(float x, float y) {
		int xBlock = (int) (x -= Constant.SCREEN_SCROLL_DISTANCE) / Constant.BLOCK_SIZE;
		int yBlock = (int) y / Constant.BLOCK_SIZE;
		return blocked[xBlock][yBlock];
	}

	private boolean checkMidpointThreeBot(float x, float y) {
		int xBlock = (int) (x += 24) / Constant.BLOCK_SIZE;
		int yBlock = (int) (y += Constant.SCREEN_SCROLL_DISTANCE
				+ playerRec.getHeight())
				/ Constant.BLOCK_SIZE;
		return blocked[xBlock][yBlock];
	}

	private boolean checkMidpointTwoBot(float x, float y) {
		int xBlock = (int) (x += 16) / Constant.BLOCK_SIZE;
		int yBlock = (int) (y += Constant.SCREEN_SCROLL_DISTANCE
				+ playerRec.getHeight())
				/ Constant.BLOCK_SIZE;
		return blocked[xBlock][yBlock];
	}

	private boolean checkMidpointOneBot(float x, float y) {
		int xBlock = (int) (x += 8) / Constant.BLOCK_SIZE;
		int yBlock = (int) (y += Constant.SCREEN_SCROLL_DISTANCE
				+ playerRec.getHeight())
				/ Constant.BLOCK_SIZE;
		return blocked[xBlock][yBlock];
	}
	
}
