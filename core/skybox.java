package core;
//The skybox as the background of the scene
public class skybox {
	
	private  polygon3D[] polygons; 
	private  vector[] v;

	//3D axis of the object 
	public vector iDirection, jDirection, kDirection;
	
	public static int screen_w = mainThread.screen_w;
	public static int screen_h = mainThread.screen_h;
	
	public skybox(){

		iDirection = new vector(0.01f,0,0);
		jDirection = new vector(0,0.01f,0);
		kDirection = new vector(0,0,0.01f);

		
		//create vertices
		v = new vector[8];
		
		v[0] = put(-10, 10, 10);
		v[1] = put(10, 10, 10);
		v[2] = put(10,-10, 10);
		v[3] = put(-10, -10, 10);
		v[4] = put(10,10, -10);
		v[5] = put(10,-10, -10);
		v[6] = put(-10,10, -10);
		v[7] = put(-10, -10, -10);
		
		
		
		//create polygons
		polygons = new polygon3D[6];
		
		polygons[0] = new polygon3D(new vector[]{v[0], v[1], v[2], v[3]}, v[0], v[1], v[3], mainThread.textures[4], 1, 1, 1);
		polygons[1] = new polygon3D(new vector[]{v[1], v[4], v[5], v[2]}, v[1], v[4], v[2], mainThread.textures[1],1,1,1);
		polygons[2] = new polygon3D(new vector[]{v[4], v[6], v[7], v[5]}, v[4], v[6], v[5], mainThread.textures[2],1,1,1);
		polygons[3] = new polygon3D(new vector[]{v[6], v[0], v[3], v[7]}, v[6], v[0], v[7], mainThread.textures[3],1,1,1);
		polygons[4] = new polygon3D(new vector[]{v[6], v[4], v[1], v[0]}, v[4], v[1], v[6], mainThread.textures[0],1,1,1);
		polygons[5] = new polygon3D(new vector[]{v[3], v[2], v[5], v[7]}, v[7], v[3], v[5], mainThread.textures[5],1,1,1);

	}
	
	public void update(){
		float x = camera.position.x;
		float y = camera.position.y;
		float z = camera.position.z;
		camera.position.set(0,0,0);
		for(int i = 0; i < 6; i ++){
			polygons[i].update();
		}
		camera.position.set(x,y,z);
	}
	
	
	public void draw(){
		//test whether all the pixels have been filled
		int xMax = screen_w-1;
		int yMax = screen_h-1;
		int xMin = 0;
		int yMin = 0;

		boolean viewIsFilled = true;
		
		outerloop:
		for(int i = yMin; i <yMax; i+=11){
			for(int j = xMin + (i%2)*11; j < xMax; j+=11){
				if(mainThread.zBuffer[j + i*screen_w] == 0){
					viewIsFilled = false;
					break outerloop;
				}	
			
			}
		}
		

		
		if(viewIsFilled)
			return;
		
		
		
		float x = camera.position.x;
		float y = camera.position.y;
		float z = camera.position.z;
		camera.position.set(0,0,0);
		for(int index = 0; index < 6; index ++){
			polygons[index].draw(); 
		}
		camera.position.set(x,y,z);
	}
	
	public vector put(double i, double j, double k){
		vector vertex = new vector(0,0,0);
		vertex.add(iDirection, (float)i);
		vertex.add(jDirection, (float)j);
		vertex.add(kDirection, (float)k);
		return vertex;
	}
	
}
