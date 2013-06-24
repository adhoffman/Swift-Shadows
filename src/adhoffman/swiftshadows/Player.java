package adhoffman.swiftshadows;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public class Player {

	public final Image image;
	public final Vector2f position = new Vector2f();

	private float verticalSpeed;
	private float horizontalSpeed;

	private boolean isJumping;

	private PlayerMapCollisionChecker mapCollisionChecker;

	public Player(TiledMap tiledMap, Vector2f spawnPosition) throws SlickException {

		mapCollisionChecker = new PlayerMapCollisionChecker(tiledMap, spawnPosition);
		image = new Image("res/sneak.png");

		position.x = spawnPosition.x;
		position.y = spawnPosition.y;

		verticalSpeed = 0;
		horizontalSpeed = 0;
		isJumping = true;

	}

	public void render() {
		image.draw(position.x += horizontalSpeed, position.y += verticalSpeed);
	}

	public void update(Input input, int delta) {
		position.x += horizontalSpeed;
		position.y += verticalSpeed;

		if (mapCollisionChecker.isNotBlockedInAnyDirection(position.x, position.y))
			isJumping = true;
		else {
			isJumping = false;
			verticalSpeed = 0;
			decreaseHorizontalSpeed();
		}

		if (input.isKeyDown(Input.KEY_UP)) {
			if (!mapCollisionChecker.isBlockedUp(position.x, position.y) && (!isJumping))
				verticalSpeed -= Constant.PLAYER_JUMP_THRUST;
		}

		if (input.isKeyDown(Input.KEY_DOWN)) {
			if (!mapCollisionChecker.isBlockedDown(position.x, position.y))
				verticalSpeed += Constant.SCREEN_SCROLL_DISTANCE;
		}

		if (input.isKeyDown(Input.KEY_LEFT)) {
			if (!mapCollisionChecker.isBlockedLeft(position.x, position.y))
				if (horizontalSpeedIsNotTooFastToTheLeft())
					horizontalSpeed -= Constant.PLAYER_SIDE_MOMENTUM;
		}

		if (input.isKeyDown(Input.KEY_RIGHT)) {
			if (!mapCollisionChecker.isBlockedRight(position.x, position.y))
				if (horizontalSpeedIsNotTooFastToTheRight())
					horizontalSpeed += Constant.PLAYER_SIDE_MOMENTUM;
		}

		if (isJumping) {
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

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

}
