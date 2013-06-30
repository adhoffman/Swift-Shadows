import org.newdawn.slick.Input;
import org.newdawn.slick.tiled.TiledMap;


public class PlayerMovementComponent {

	
	private int xLocation;
	private int yLocation;
	private float verticalSpeed;
	private float horizontalSpeed;
	private boolean isJumping;

	private PlayerMapCollisionChecker mapCollisionChecker;
	
	public PlayerMovementComponent(TiledMap tiledMap, StartingPoint startingPoint){
		
		mapCollisionChecker=new PlayerMapCollisionChecker(tiledMap,startingPoint);
		verticalSpeed = 0;
		horizontalSpeed = 0;
		
		xLocation = startingPoint.getX();
		yLocation = startingPoint.getY();

		isJumping = true;
		
	}
	
	
	
	public void update(Input input, int delta){
		
		
		if (mapCollisionChecker.isNotBlockedInAnyDirection(
				xLocation += horizontalSpeed, yLocation += verticalSpeed))
			isJumping = true;
		else {
			isJumping = false;
			verticalSpeed = 0;
			calculateHorizontalFriction();
		}

		if (ifUpKeyDown(input)) {
			if (ifPlayerCanMoveUp())
				verticalSpeed -= Constant.PLAYER_JUMP_THRUST;
		}

		if (ifDownKeyDown(input)) {
			if (ifPlayerCanMoveDown())
				verticalSpeed += Constant.SCREEN_SCROLL_DISTANCE;
		}

		if (ifLeftKeyDown(input)) {
			if (ifPlayerCanMoveLeft())
				if (horizontalSpeedIsNotTooFastToTheLeft())
					horizontalSpeed -= Constant.PLAYER_SIDE_MOMENTUM;
		}

		if (ifRightKeyDown(input)) {
			if (ifPlayerCanMoveRight())
				if (horizontalSpeedIsNotTooFastToTheRight())
					horizontalSpeed += Constant.PLAYER_SIDE_MOMENTUM;
		}

		if (isJumping == true) {
			verticalSpeed += (Constant.GRAVITY * delta);
		}

		xLocation += horizontalSpeed;
		yLocation += verticalSpeed;
	}
	
	
	private boolean horizontalSpeedIsNotTooFastToTheRight() {
		if (horizontalSpeed < Constant.PLAYER_HORIZONTAL_TERMINAL_VELOCITY)
			return true;
		else
			return false;
	}

	private boolean horizontalSpeedIsNotTooFastToTheLeft() {
		if (horizontalSpeed > -Constant.PLAYER_HORIZONTAL_TERMINAL_VELOCITY)
			return true;
		else
			return false;
	}

	private boolean ifPlayerCanMoveRight() {
		if (!mapCollisionChecker.isBlockedRight(xLocation, yLocation))
			return true;
		else
			return false;

	}

	private boolean ifPlayerCanMoveLeft() {
		if (!mapCollisionChecker.isBlockedLeft(xLocation, yLocation))
			return true;
		else
			return false;
	}

	private boolean ifPlayerCanMoveDown() {
		if (!mapCollisionChecker.isBlockedDown(xLocation, yLocation))
			return true;
		else
			return false;
	}

	private boolean ifPlayerCanMoveUp() {
		if (!mapCollisionChecker.isBlockedUp(xLocation, yLocation)
				&& (isJumping == false))
			return true;
		else
			return false;
	}

	private boolean ifUpKeyDown(Input input) {
		if (input.isKeyDown(Input.KEY_UP))
			return true;
		else
			return false;
	}

	private boolean ifDownKeyDown(Input input) {
		if (input.isKeyDown(Input.KEY_DOWN))
			return true;
		else
			return false;
	}

	private boolean ifLeftKeyDown(Input input) {
		if (input.isKeyDown(Input.KEY_LEFT))
			return true;
		else
			return false;
	}

	private boolean ifRightKeyDown(Input input) {
		if (input.isKeyDown(Input.KEY_RIGHT))
			return true;
		else
			return false;
	}

	private void calculateHorizontalFriction() {
		if (horizontalSpeed > 0) {
			horizontalSpeed -= Constant.PLAYER_SIDE_MOMENTUM
					* Constant.COEFICIENT_OF_FRICTION;
		}
		if (horizontalSpeed < 0) {
			horizontalSpeed += Constant.PLAYER_SIDE_MOMENTUM
					* Constant.COEFICIENT_OF_FRICTION;
		}
		if (horizontalSpeed == 0) {

		}

	}
}