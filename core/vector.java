package core;
public final class vector{
	//x, y, z component of the vector
	public float x, y, z;

	//2d coordinate on screen
	public float screenX, screenY;

	public static final int Z_length = 475;

	public static float old_X, old_Y, old_Z, zInverse, lengthInverse;
	
	public static int half_screen_w = mainThread.half_screen_w;
	public static int half_screen_h = mainThread.half_screen_h;

	public vector(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;

		//calculate its 2D location on the screen
		updateLocation();
	}

	public void add(vector v){
		x+=v.x;
		y+=v.y;
		z+=v.z;
	}

	public void add(float a, float b, float c){
		x+=a;
		y+=b;
		z+=c;
	}

	public void add(vector v, float scaler){
		x+=v.x*scaler;
		y+=v.y*scaler;
		z+=v.z*scaler;
	}

	public void subtract(vector v, float scaler){
		x-=v.x*scaler;
		y-=v.y*scaler;
		z-=v.z*scaler;
	}

	public void subtract(vector v){
		x-=v.x;
		y-=v.y;
		z-=v.z;
	}
	
	public void subtract(float x, float y, float z){
		this.x-=x;
		this.y-=y;
		this.z-=z;
	}

	//amplify each component of the vector by a number
	public void scale(float a){
		x*=a;
		y*=a;
		z*=a;
	}

	//normalize the vector
	public void unit(){
		lengthInverse = 1/getLength();
		x = x*lengthInverse;
		y = y*lengthInverse;
		z = z*lengthInverse;
	}
	
	//normalize the vector using sqrt look up table
	//Won't work if the length of the vector is bigger than 20
	public void fastUnit(){
		lengthInverse = gameData.sqrts[(int)(1600*(x*x + y*y + z*z))&32767];
		x = x*lengthInverse;
		y = y*lengthInverse;
		z = z*lengthInverse;
	}
	

	//find the magnitude pf the vector
	public float getLength(){
		return (float)Math.sqrt(x*x + y*y + z*z);
	}

	//retrun the dot product of this vector with another vector
	public float dot(vector v){
		return x*v.x + y*v.y + z*v.z;
	}
	
	public float dot(byte[] v){
		return x*v[0] + y*v[1] + z*v[2];
	}

	public float dot(float a, float b, float c){
		return x*a + y*b + z*c;
	}

	//return the cross product of this vector with another vector
	public final vector cross(vector v){
		return new vector(y*v.z - z*v.y, z*v.x - x*v.z, x*v.y - y*v.x);
	}
	
	public void cross(vector v1, vector v2){
		x = v1.y*v2.z - v1.z*v2.y;
		y = v1.z*v2.x - v1.x*v2.z;
		z = v1.x*v2.y - v1.y*v2.x;
	}

	//rotate the vector along Y axis
	public void  rotate_XZ(int angle){
		float sin = gameData.sin[angle];
		float cos = gameData.cos[angle];
		old_X = x;
		old_Z = z;
		x = cos*old_X - sin*old_Z;
		z = sin*old_X + cos*old_Z;
	}

	//rotate the vector along X axis
	public void rotate_YZ(int angle){
		float sin = gameData.sin[angle];
		float cos = gameData.cos[angle];
		old_Y = y;
		old_Z = z;
		y = cos*old_Y - sin*old_Z;
		z = sin*old_Y + cos*old_Z;
	}
	
	//rotate the vector along Z axis
	public void rotate_XY(int angle){
		float sin = gameData.sin[angle];
		float cos = gameData.cos[angle];
		old_X = x;
		old_Y = y;
		x = cos*old_X - sin*old_Y;
		y = sin*old_X + cos*old_Y;
	}

	//set all the component equal to the corresponding component of a given vector
	public void set(vector v){
		x = v.x;
		y = v.y;
		z = v.z;
	}

	public void set(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	//set all the component to 0
	public void reset(){
		x = 0;
		y = 0;
		z = 0;
	
		
	}

	public void updateLocation(){
		//find the 2D screen location of this vector
		zInverse = Z_length/z;
		screenX = x*zInverse + half_screen_w; 
		screenY = -y*zInverse + half_screen_h;
	}
	
	//set the 2D location of this vector to the 2D location of a given vector
	public void setScreenLocation(vector v){
		screenX = v.screenX;
		screenY = v.screenY;
	}

	public vector myClone(){
		return new vector(x,y,z);
	}

	public String toString(){
		return "(" + x + ", " + y + ", " + z + ")";
	}

}