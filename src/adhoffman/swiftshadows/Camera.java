package adhoffman.swiftshadows;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Camera {

	private final Vector2f scale = new Vector2f(1, 1);
	private final Vector2f position = new Vector2f();
	private final Vector2f offset = new Vector2f();

	private Vector2f targetPosition;
	private Vector2f targetOffset;

	private boolean lockedOnTarget;

	private float speed;

	private Rectangle bounds = new Rectangle(0, 0, 0, 0);
	private boolean isBounded;

	public Camera(float x, float y, float speed) {
		this.setSpeed(speed);
		moveTo(x, y);
	}

	public void update(int delta) {
		if (isLockedOnTarget()) {
			if (isBounded) {
				moveTo(Math.min(Math.max(targetPosition.x - Constant.SCREEN_WIDTH / 2f, bounds.getX()), bounds.getX() + bounds.getWidth()), Math.min(Math.max(targetPosition.y - Constant.SCREEN_HEIGHT / 2f, bounds.getY()), bounds.getY() + bounds.getHeight()));
			} else {
				moveTo(targetPosition.x - Constant.SCREEN_WIDTH / 2f, targetPosition.y - Constant.SCREEN_HEIGHT / 2f);
			}
			offsetTo(targetOffset.x, targetOffset.y);
		}
	}

	public void moveTo(float x, float y) {
		position.x = x;
		position.y = y;
	}

	public void translate(float dx, float dy) {
		position.x += dx;
		position.y += dy;
	}

	public void offsetTo(float x, float y) {
		offset.x = x;
		offset.y = y;
	}

	public void offset(float dx, float dy) {
		offset.x += dx;
		offset.y += dy;
	}

	/**
	 * Limit camera movement within this rectangle
	 * 
	 * @param minX
	 *            top-left x
	 * @param minY
	 *            top-left y
	 * @param maxX
	 *            bottom-right x
	 * @param maxY
	 *            bottom-right y
	 */
	public void bind(float minX, float minY, float maxX, float maxY) {
		// TODO: take 'Rectangle' instance to allow resizing on the fly?
		bounds.setBounds(minX, minY, maxX, maxY);
		isBounded = true;
	}

	public void unbind() {
		isBounded = false;
	}

	public void toggleBind() {
		isBounded = !isBounded;
	}

	public void unfollow() {
		lockedOnTarget = false;
		targetPosition = null;
		targetOffset = null;
	}

	public void follow(Vector2f targetPosition) {
		follow(targetPosition, new Vector2f(), true);
	}

	/**
	 * focus the targetPosition vector when updating, use offset to focus the
	 * center-point or other specific points of the target
	 * 
	 * @param targetPosition
	 * @param targetOffset
	 * @param lockedOnTarget
	 *            true if should immediately lock onto the target
	 */
	public void follow(Vector2f targetPosition, Vector2f targetOffset, boolean lockedOnTarget) {
		if (targetPosition == null) {
			throw new NullPointerException("Camera target should not be null.");
		}
		if (targetOffset == null) {
			throw new NullPointerException("Camera target should not be null.");
		}
		this.targetPosition = targetPosition;
		this.targetOffset = targetOffset;
		this.lockedOnTarget = lockedOnTarget;
	}

	public boolean toggleLockedOnTarget() {
		return lockedOnTarget = !lockedOnTarget;
	}

	public boolean isLockedOnTarget() {
		return lockedOnTarget;
	}

	public void setLockedOnTarget(boolean lockedOnTarget) {
		this.lockedOnTarget = lockedOnTarget;
	}

	public float getX() {
		return position.x + offset.x;
	}

	public float getY() {
		return position.y + offset.y;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
