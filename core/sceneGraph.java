package core;
//Scene graph  is a tree of bounding boxes(container). Each bounding box can contain  3D models or
//other bounding boxes.  In the process of  hidden surface removal, if the bounding boxes are rendered 
//invisible (i.e outside view screen or completely behind other objects), all the objects/polygons  inside the bounding 
//boxes will also be rendered invisible

public class sceneGraph {
	//root node of the graph
	public static container world;
	
	public static skybox Skybox;
	
	public static polygon3D[] transparent;
	
	public static polygon3D[] misc;
	
	
	public static void createScene(){	
		//create skybox 
		Skybox = new skybox();
		
		transparent = new polygon3D[16];
		
		misc = new polygon3D[30];
		
	
		
		//define world 
		world = new container(new vector(0,0,0), 10,10,10);
		world.addModel(new poolLower(new vector(0,-0.05f/2,0.04f/2)));
		
		container poolUpper = new container(new vector(0, 0.02625f/2, 0.03f/2), 0.05f/2, 0.0375f/2, 0.1f/2);
		world.addModel(poolUpper);
		
		//poolUpper.drawBoundary = true;
	
		poolUpper.addModel(new poolFloor(new vector(0, -0.01375f/2, 0.04f/2)));
		poolUpper.addModel(new wallWest(new vector(-0.051f/2, 0.0f, 0.04f/2)));
		
		poolUpper.addModel(new door(new vector(-0.0555f/2, -0.00375f/2, 0.03f/2)));
		poolUpper.addModel(new door(new vector(-0.0555f/2, -0.00375f/2, 0.04f/2)));
		poolUpper.addModel(new wallNorth(new vector(0, 0, 0.058f)));
		poolUpper.addModel(new wallSouth(new vector(0, 0, -0.023f)));
		poolUpper.addModel(new poolCeiling(new vector(0, 0.005f, 0.0175f)));
		poolUpper.addModel(new pillar(new vector(-0.0175f,-0.0015f,0.026f)));
		poolUpper.addModel(new pillar(new vector(-0.0175f,-0.0015f,0.045f)));
		poolUpper.addModel(new pillar(new vector(-0.0175f,-0.0015f,-0.0098f)));
		poolUpper.addModel(new pillar(new vector(-0.0175f,-0.0015f, 0.008f)));
		poolUpper.addModel(new pillar(new vector(0.0175f,-0.0015f,0.026f)));
		poolUpper.addModel(new pillar(new vector(0.0175f,-0.0015f,0.045f)));
		poolUpper.addModel(new pillar(new vector(0.0175f,-0.0015f,-0.0098f)));
		poolUpper.addModel(new pillar(new vector(0.0175f,-0.0015f, 0.008f)));
		poolUpper.addModel(new wallEast(new vector(0.051f/2, 0.0f, 0.04f/2)));
		
		container poolUpperEast = new container(new vector(0.0355f, 0.0045f, 0), 0.01f, 0.01f, 0.04f);
		
		poolUpperEast.addModel(new saunaRoom(new vector(0.0306f,0,0.030625f)));
		poolUpperEast.addModel(new steamRoom(new vector(0.0306f,0, 0.015425f)));
		
	
		world.addModel(poolUpperEast);
		
		world.addMisc(new poolLadder(new vector(-0.0161f,-0.0041f,0.04f), 35, 1));
		
		world.addMisc(new poolLadder(new vector(0.0161f,-0.0041f,0.04f), 36, 0));
		
		world.addMisc(new poolLadder(new vector(0.0161f,-0.0041f,-0.002f), 37, 0));
		
		world.addMisc(new poolLadder(new vector(-0.0161f,-0.0041f,-0.002f), 38, 1));
		
		
		world.update();
	}
	

	//update scene graph
	public static void updateAndDraw(){
		//update and draw world
		world.update();
		
		//update skyBox
		Skybox.update();
		
		//update misc polygons
		for(int i = 0; i < misc.length; i++){
			if(misc[i] != null)
				misc[i].update();
		}
		
	
		
		//update and sort transparent polygons
		for(int i = 0; i < transparent.length; i++){
			transparent[i].update();
		}
		
	
		if(camera.updating){
			for(int i = 1; i < transparent.length; i++){
				for(int j = 0; j <transparent.length - i; j++){
					if(geometry.comparePolygons(transparent[j], transparent[j+1])){
						polygon3D temp = transparent[j+1];
						transparent[j+1] = transparent[j];
						transparent[j] = temp;
					}
				}
			}
			
			
			
		}
		
		//draw misc
		for(int i = 0; i < misc.length; i++){
			if(misc[i] != null)
				misc[i].draw();
		}
		
		Skybox.draw();
		
		
		
		//draw transparent polygons after all solid objects are drawn
		for(int i = 0; i < transparent.length; i++)
			transparent[i].draw();
		
	
	}
	
	public static void addTransparent(polygon3D p){
		for(int i = 0; i < transparent.length; i++)
			if(transparent[i] == null){
				transparent[i] = p;
				break;
			}
	}
	
	public static void addMisc(polygon3D p){
		for(int i = 0; i < misc.length; i++)
			if(misc[i] == null){
				misc[i] = p;
				break;
			}
	}
	

	
}
