import java.awt.Rectangle;

import org.newdawn.slick.Input;




public class Player {
	
	private Rectangle rec;
	private int movement;
	
	public Player(Rectangle rec){
		this.rec= rec;
	}
	
	public void update(Input input,int delta){
		if (input.isKeyPressed(Input.KEY_UP)==true) {
			rec.translate(0, (int) (rec.getY()-movement));
		}
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			rec.translate(0, (int) (rec.getY()+movement));
		}
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			rec.translate((int) (rec.getX()+movement), 0);
		}
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			rec.translate((int) (rec.getX()-movement),0);
		}
	}
	
	public void render(){
		
	}
}
