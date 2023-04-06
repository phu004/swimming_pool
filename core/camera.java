package core;
import java.awt.*;

public class camera{
	public static vector position;

	public static vector view_Direction;

	public static boolean MOVE_FORWARD, MOVE_BACKWARD, SLIDE_LEFT, SLIDE_RIGHT, UP_TYPED, DOWN_TYPED, RIGHT_TYPED, LEFT_TYPED;
	
	public static boolean updating;
	
	public static int XZ_angle, YZ_angle;

	public static final vector viewDirection = new vector(0, 0, 1);
	
	//a rectangle that represents the screen area
	public static final Rectangle screen = new Rectangle(0,0,mainThread.screen_w, mainThread.screen_h);
		
	
	public camera(vector p){
		position = p;
		view_Direction = new vector(0, 0, 1);
		XZ_angle = 0;
		YZ_angle = 0;
	}

	public void update(){
		
		updating = false;
		

		if(UP_TYPED){
			YZ_angle+=2;
			if(YZ_angle > 89 && YZ_angle < 180)
				YZ_angle = 89;
			updating = true;
			
		}

		if(DOWN_TYPED){
			YZ_angle-=2;
			if(YZ_angle < 271 && YZ_angle > 180)
				YZ_angle = -89;
			updating = true;
		
		}


		if(RIGHT_TYPED){
			XZ_angle+=2;
			updating = true;
			
		}

		if(LEFT_TYPED){
			XZ_angle-=2;
			updating = true;
			
		}
			
		if(MOVE_FORWARD){
			position.add(view_Direction, 0.00015f);
			updating = true;
			
			
		}

		if(MOVE_BACKWARD){
			position.subtract(view_Direction, 0.00015f);
			updating = true;
		}

		if(SLIDE_LEFT){
			vector left = view_Direction.cross(new vector(0, -1, 0));
			left.unit();
			position.subtract(left, 0.00015f);
			updating = true;
		}

		if(SLIDE_RIGHT){
			vector right = view_Direction.cross(new vector(0, 1, 0));    
			right.unit();
			position.subtract(right, 0.00015f);
			updating = true;
		}


		
		XZ_angle = (XZ_angle + 360) % 360;
		YZ_angle = (YZ_angle + 360) % 360;
		
		view_Direction.set(viewDirection);
		view_Direction.rotate_YZ(YZ_angle);
		view_Direction.rotate_XZ(XZ_angle);
		view_Direction.y*=-1;
		view_Direction.x*=-1;
		view_Direction.unit();
		

	}

}