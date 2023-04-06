package core;

//containers  are invisible cuboid that partition the space
//into smaller volumes, polygons that inside different containers will never compare
//against one another during hidden surface removal. 

public class container implements model{

	//reference point of the model (in world coordinate)
	public vector start;
	
	//3d models inside the container
	public model[] models;
	
	//the transparent polygons of the object
	public polygon3D[] transparentPolygons;
	
	//3d models inside the container and always drawn before anything else
	public model[] misc;
	
	//A rough  boundary of this model, contains 6 polygons
	public polygon3D[] boundary;
	
	//the centre of the model in world coordinate
	public vector centre;
	
	//the centre of the model in camera coordinate
	public vector tempCentre;
	
	//whether the contents of the container need to be sent to drawing pipeline
	public boolean visible;
	
	//whether to draw the boundary box
	public boolean drawBoundary;
	
	//whether the camera is inside the object
	public boolean cameraIsInside_;
	
	//the number of models that are enclosed inside this container 
	public int modelCount;
	
	//the reference axis of the model  (in world coordinate)
	public vector iDirection, jDirection, kDirection;
	
	public static int screen_w = mainThread.screen_w;
	public static int screen_h = mainThread.screen_h;
	
	
	//constructor
	public container(vector start, float l, float h, float w){
		this.start = start;
		
		iDirection = new vector(1,0,0);
		jDirection = new vector(0,1,0);
		kDirection = new vector(0,0,1);
		
		centre = start.myClone();

		tempCentre = centre.myClone();
		
		makeBoundary(l,h,w);
		
		visible = testVisibility();
		
		models = new model[50];
		
		misc = new model[10];
		
	}
	
	//Create a rough 3D cuboid boundary for this model.
	public final void makeBoundary(float l, float h, float w){
		boundary = new polygon3D[6];
		vector[] front = new vector[]{put(l, h, w), put(-l, h, w), put(-l, -h, w), put(l, -h, w)};
		boundary[0] = new polygon3D(front, front[0], front[1], front[3], null, 1,1,0);
		vector[] right = new vector[]{put(l, h, -w), put(l, h, w), put(l, -h, w), put(l, -h, -w)};
		boundary[1] = new polygon3D(right, right[0], right[1], right[3], null, 1,1,0);
		vector[] back = new vector[]{put(-l, h, -w), put(l, h, -w), put(l, -h, -w), put(-l, -h, -w)};
		boundary[2] = new polygon3D(back, back[0], back[1], back[3], null, 1,1,0);
		vector[] left = new vector[]{put(-l, h, w), put(-l, h, -w), put(-l, -h, -w), put(-l, -h, w)};
		boundary[3] = new polygon3D(left, left[0], left[1], left[3], null, 1,1,0);
		vector[] top = new vector[]{put(-l, h, w), put(l, h, w), put(l, h, -w), put(-l, h, -w)};
		boundary[4] = new polygon3D(top, top[0], top[1], top[3], null, 1,1,0);
		vector[] buttom = new vector[]{put(-l, -h, -w), put(l, -h, -w), put(l, -h, w), put(-l, -h, w)};
		boundary[5] = new polygon3D(buttom, buttom[0], buttom[1], buttom[3], null, 0,0,0);
		
		boundary[0].color = createColor(0,0,31); boundary[0].diffuse_I = 45;
		boundary[1].color = createColor(0,31,0); boundary[1].diffuse_I = 45;
		boundary[2].color = createColor(31,0,0); boundary[2].diffuse_I = 45;
		boundary[3].color = createColor(31,31,0);boundary[3].diffuse_I = 45;
		boundary[4].color = createColor(0,31,31);boundary[4].diffuse_I = 45;
		boundary[5].color = createColor(31,0,31);boundary[5].diffuse_I = 45;
	}
	
	//get a rough 3D boundary of the model in camera coordinate
	public  polygon3D[] getBoundary(){
		return boundary;
	}

	//get centre of this model in camera coordinate
	public  vector getCentre(){
		return tempCentre;
	}
	
	//return centre in world coordinate
	public  vector getRealCentre(){
		return centre;
	}
	
	//return visibility
	public boolean getVisibility(){
		return visible;
	}
	
	//update container and its contents
	public void update(){
		//update boundary for this model
		for(int i = 0; i < boundary.length; i++){
			//update center in camera coordinate
			boundary[i].centre.set(boundary[i].realCentre);
			boundary[i].centre.subtract(camera.position);
			boundary[i].centre.rotate_XZ(camera.XZ_angle);
			boundary[i].centre.rotate_YZ(camera.YZ_angle);
		
			boundary[i].normal.set(boundary[i].realNormal);
			boundary[i].normal.rotate_XZ(camera.XZ_angle);
			boundary[i].normal.rotate_YZ(camera.YZ_angle);
			
			boundary[i].update();
		}
		
		//test if the container is visible, 
		visible = testVisibility();
		
		
	
		
		if(!visible)
			return;
		
		//update center in camera coordinate
		tempCentre.set(centre);
		tempCentre.subtract(camera.position);
		tempCentre.rotate_XZ(camera.XZ_angle);
		tempCentre.rotate_YZ(camera.YZ_angle);
		tempCentre.updateLocation();
		
		int length = modelCount; // - backGroundModelCount;
		
		//roughly sort models from based on their depths
		if(mainThread.frameIndex <=1 ){
			for(int i = 1; i < length; i++){
				for(int j = 0; j <length - i; j++){
					if(geometry.compareModels(models[j+1],models[j])){
						model temp = models[j+1];
						models[j+1] = models[j];
						models[j] = temp;
					}
				}
			}
		}
		
		//update the models which lie inside the container
		for(int i = 0; i < modelCount; i++){
			models[i].update();
		}
		
		
		
		if(misc != null){
			for(int i = 0; i < misc.length; i++){
				if(misc[i] != null)
					misc[i].update();
			}
			
		}
		
		
		//sort models inside the container, the further models will get drawn before
		//the closer models.

		 length = modelCount; // - backGroundModelCount;


		//roughly sort models from based on their depths
		if(((mainThread.frameIndex <=1) || camera.updating)){
			
			
			for(int i = 1; i < length; i++){
				for(int j = 0; j <length - i; j++){
					if(geometry.compareModels(models[j+1],models[j])){
						model temp = models[j+1];
						models[j+1] = models[j];
						models[j] = temp;
					}
				}
			}
		}
		
		if(drawBoundary)
			draw();
		
	}
	
	public  boolean testVisibility(){
		
		
		cameraIsInside_ = testCamera_();
		
		if(testCamera())
			return true;
					
			//occlusion culling
			int xMax = 0;
			int yMax = 0;
			int xMin = screen_w -1;
			int yMin = screen_h -1;
			
			for(int i = 0; i < 6; i++){
				if(boundary[i].visible){
					if(drawBoundary){
					
								
						
						
					}
					xMax = Math.max(xMax, (int)boundary[i].xMax);
					xMin = Math.min(xMin, (int)boundary[i].xMin);
					yMax = Math.max(yMax, (int)boundary[i].yMax);
					yMin = Math.min(yMin, (int)boundary[i].yMin);
				}
			}
			
			if(xMax < xMin || yMax < yMin)
				return false;
			
			if(xMax > (screen_w -1))
				xMax = (screen_w -1);
			if(xMin < 0)
				xMin = 0;
			if(yMax > (screen_h -1))
				yMax = (screen_h -1);
			if(yMin < 0)
				yMin = 0;
		
			
			for(int i = yMin; i <yMax; i+=21){
				for(int j = xMin + (i%2)*11; j < xMax; j+=21){
					if(mainThread.zBuffer[j + i*screen_w] == 0){
						return true;
						
					}	
				
				}
			}
			
			int yMid = (yMax + yMin)/2;
			for(int i = yMid * screen_w + xMin; i < yMid * screen_w + xMax; i++){
				if(mainThread.zBuffer[i] == 0){
					return true;
				}
			}
			
			int xMid = (xMax + xMin)/2;
			for(int i = yMin * screen_w + xMid; i < yMax*screen_w + xMid; i+= screen_w){
				if(mainThread.zBuffer[i] == 0){
					return true;
				}
			}
			
			return false;
		
	}
	

	
	public boolean testCamera(){
		boolean cameraIsInside = true;
		vector origin = new vector(0,0,0);
		
		for(int i = 0; i < 6; i++){
			origin.reset();
			origin.subtract(boundary[i].centre);
			origin.add(boundary[i].normal, -0.001f);
			if(origin.dot(boundary[i].normal) > 0){
				cameraIsInside = false;
				break;
			}
		}
		return cameraIsInside;
	}
	
	public boolean testCamera_(){

		cameraIsInside_ = true;
		vector origin = new vector(0,0,0);
		for(int i = 0; i < 6; i++){
			origin.reset();
			origin.subtract(boundary[i].centre);
			if(origin.dot(boundary[i].normal) > 0){
				cameraIsInside_ = false;
				break;
			}
		}
		return cameraIsInside_;
	}
	
	
	public void addModel(model m){
		for(int i = 0; i < models.length; i ++){
			if(models[i] == null){
				models[i] = m;
				modelCount++;
				return;
			}
		}
	}
	
	public void addMisc(model m){
		for(int i = 0; i < misc.length; i++)
			if(misc[i] == null){
				misc[i] = m;
				return;
			}
	}
	
	public void draw(){
		for(int i = 0; i < boundary.length; i++){
			boundary[i].draw();
		}
	}
	
	//add transparent polygons to a separate polygon group
	public void addTransparentPolygon(polygon3D p){
		for(int i = 0; i < transparentPolygons.length; i++){
			if(transparentPolygons[i] == null){
				transparentPolygons[i] = p;
				return;
			}
		}
	}
	
	//create a arbitrary vertex
	public final vector put(float i, float j, float k){
		vector temp = start.myClone();
		temp.add(iDirection, i);
		temp.add(jDirection, j);
		temp.add(kDirection, k);
		return temp;
	}
	
	//create color in 16bits (5-6-5) format 
	public static int createColor(int r, int g, int b){
		return  b + (g << 6) + (r << 11);
	}


	public boolean testCameraisInside(){
		return cameraIsInside_;
	}
	
}
