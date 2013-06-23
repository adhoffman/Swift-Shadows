package adhoffman.swiftshadows;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Player {

	private Image player;

	private int xLocation;
	private int yLocation;
	private float verticalSpeed;
	private float horizontalSpeed;
	private boolean isJumping;

	private PlayerMapCollisionChecker mapCollisionChecker;

	public Player(TiledMap tiledMap, StartingPoint startingPoint) throws SlickException {

		mapCollisionChecker = new PlayerMapCollisionChecker(tiledMap, startingPoint);
		player = new Image("res/sneak.png");

		xLocation = startingPoint.getX();
		yLocation = startingPoint.getY();

		verticalSpeed = 0;
		horizontalSpeed = 0;
		isJumping = true;

	}

	public void render() {
		player.draw(xLocation += horizontalSpeed, yLocation += verticalSpeed);
	}

	public void update(Input input, int delta) {

		if (mapCollisionChecker.isNotBlockedInAnyDirection(xLocation += horizontalSpeed, yLocation += verticalSpeed))
			isJumping = true;
		else {
			isJumping = false;
			verticalSpeed = 0;
			decreaseHorizontalSpeed();
		}

		if (input.isKeyDown(Input.KEY_UP)) {
			if (!mapCollisionChecker.isBlockedUp(xLocation, yLocation) && (isJumping == false))
				verticalSpeed -= Constant.PLAYER_JUMP_THRUST;
		}

		if (input.isKeyDown(Input.KEY_DOWN)) {
			if (!mapCollisionChecker.isBlockedDown(xLocation, yLocation))
				verticalSpeed += Constant.SCREEN_SCROLL_DISTANCE;
		}

		if (input.isKeyDown(Input.KEY_LEFT)) {
			if (!mapCollisionChecker.isBlockedLeft(xLocation, yLocation))
				if (horizontalSpeedIsNotTooFastToTheLeft())
					horizontalSpeed -= Constant.PLAYER_SIDE_MOMENTUM;
		}

		if (input.isKeyDown(Input.KEY_RIGHT)) {
			if (!mapCollisionChecker.isBlockedRight(xLocation, yLocation))
				if (horizontalSpeedIsNotTooFastToTheRight())
					horizontalSpeed += Constant.PLAYER_SIDE_MOMENTUM;
		}

		if (isJumping == true) {
			verticalSpeed += (Constant.GRAVITY * delta);
		}

	}

	private void decreaseHorizontalSpeed() {
		if (horizontalSpeed > 0) {
			horizontalSpeed -= Constant.PLAYER_SIDE_MOMENTUM * 1.2;
		}
		if (horizontalSpeed < 0) {
			horizontalSpeed += Constant.PLAYER_SIDE_MOMENTUM * 1.2;
		}
		if (horizontalSpeed == 0) {

		}

	}

	private boolean horizontalSpeedIsNotTooFastToTheRight() {
		if ((horizontalSpeed += Constant.PLAYER_SIDE_MOMENTUM) <= Constant.PLAYER_HORIZONTAL_TERMINAL_VELOCITY)
			return true;
		else
			return false;
	}

	private boolean horizontalSpeedIsNotTooFastToTheLeft() {
		if ((horizontalSpeed -= Constant.PLAYER_SIDE_MOMENTUM) >= -Constant.PLAYER_HORIZONTAL_TERMINAL_VELOCITY)
			return true;
		else
			return false;
	}

	public int getX() {
		return xLocation;
	}

	public int getY() {
		return yLocation;
	}

}
