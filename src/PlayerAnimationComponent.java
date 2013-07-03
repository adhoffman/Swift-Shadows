import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PlayerAnimationComponent {

	private int xLocation;
	private int yLocation;
	private Image player;
	private Image playerJumpingRight;
	private Image playerJumpingLeft;

	private float horizontalSpeed;

	private boolean isJumping;

	public PlayerAnimationComponent() throws SlickException {
		player = new Image("res/sneak.png");
		playerJumpingRight = new Image("res/sneakJumpingRight.png");
		playerJumpingLeft = new Image("res/sneakJumpingLeft.png");

		xLocation = 0;
		yLocation = 0;
		horizontalSpeed = 0;

		isJumping = false;
	}

	public void render() {
		drawPlayerSprite();

	}
	public void updateAnimtaionLocation(int x, int y) {
		xLocation = x;
		yLocation = y;
	}

	public void updateIsJumpingForAnimations(boolean jumping) {
		isJumping = jumping;
	}

	public void updateHorizontalSpeedForJumping(float speed) {
		horizontalSpeed = speed;
	}

	private void drawPlayerSprite() {
		if (playerIsNotJumping()) {
			player.draw(xLocation, yLocation);
		} else if (playerIsJumpingAndMovingRight()) {
			playerJumpingRight.draw(xLocation, yLocation);
		} else if (playerIsJumpingAndMovingLeft()) {
			playerJumpingLeft.draw(xLocation, yLocation);
		}
	}

	private boolean playerIsJumpingAndMovingLeft() {
		if (isJumping == true && horizontalSpeed < 0)
			return true;
		else
			return false;
	}

	private boolean playerIsJumpingAndMovingRight() {
		if (isJumping == true && horizontalSpeed > 0)
			return true;
		else
			return false;
	}

	private boolean playerIsNotJumping() {
		if (isJumping == false)
			return true;
		else
			return false;
	}

	

}
