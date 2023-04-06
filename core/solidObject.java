package core;
//this is the class for storing geometry information of a 3D model
public abstract class solidObject implements model{
	
	//reference point of the model (in world coordinate)
	public vector start;
	
	//the reference axis of the model  (in world coordinate)
	public vector iDirection, jDirection, kDirection;
	 
	//A rough  boundary of this model, contains 6 polygons
	public polygon3D[] boundary;
	
	//whether this model need to be sent to drawing pipeline
	public boolean visible;
	
	//whether to draw the boundary box
	public boolean drawBoundary;
	
	//whether to enable occlusion culling during visibility test 
	public boolean enableOcclusionCulling = true;
	
	//whether the camera is inside the object
	public boolean cameraIsInside_;
	
	//vertices
	public vector[] v;
	
	//the 3D polygons of the object
	public polygon3D[] polygons;
	
	//the centre of the model in world coordinate
	public vector centre;
	
	//the centre of the model in camera coordinate
	public vector tempCentre = new vector(0,0,0);
	
	public static vector temp = new vector(0,0,0);
	
	
	public static int screen_w = mainThread.screen_w;
	public static int screen_h = mainThread.screen_h;
	
	//get a rough 3D boundary of the model in camera coordinate
	public  polygon3D[] getBoundary(){
		return boundary;
	}
	
	//a flag which indicates if the polygons need to be sorted
	public boolean sortedPolygons = true;
	public int sortIndexStart;

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
	
	//Create a rough 3D cuboid boundary for this model.
	public void makeBoundary(float l, float h, float w){
		boundary = new polygon3D[6];
		vector[] front = new vector[]{put(l, h, w), put(-l, h, w), put(-l, -h, w), put(l, -h, w)};
		boundary[0] = new polygon3D(front, front[0], front[1], front[3], null, 0,0,0);
		vector[] right = new vector[]{put(l, h, -w), put(l, h, w), put(l, -h, w), put(l, -h, -w)};
		boundary[1] = new polygon3D(right, right[0], right[1], right[3], null, 0,0,0);
		vector[] back = new vector[]{put(-l, h, -w), put(l, h, -w), put(l, -h, -w), put(-l, -h, -w)};
		boundary[2] = new polygon3D(back, back[0], back[1], back[3], null, 0,0,0);
		vector[] left = new vector[]{put(-l, h, w), put(-l, h, -w), put(-l, -h, -w), put(-l, -h, w)};
		boundary[3] = new polygon3D(left, left[0], left[1], left[3], null, 0,0,0);
		vector[] top = new vector[]{put(-l, h, w), put(l, h, w), put(l, h, -w), put(-l, h, -w)};
		boundary[4] = new polygon3D(top, top[0], top[1], top[0], null, 1,1,0);
		vector[] buttom = new vector[]{put(-l, -h, -w), put(l, -h, -w), put(l, -h, w), put(-l, -h, w)};
		boundary[5] = new polygon3D(buttom, buttom[0], buttom[1], buttom[3], null, 0,0,0);
		
		boundary[0].color = createColor(0,0,31); boundary[0].diffuse_I = 45;
		boundary[1].color = createColor(0,31,0); boundary[1].diffuse_I = 45;
		boundary[2].color = createColor(31,0,0); boundary[2].diffuse_I = 45;
		boundary[3].color = createColor(31,31,0);boundary[3].diffuse_I = 45;
		boundary[4].color = createColor(0,31,31);boundary[4].diffuse_I = 45;
		boundary[5].color = createColor(31,0,31);boundary[5].diffuse_I = 45;
		 
		 
	}

	
	public  boolean testVisibility(){
		cameraIsInside_ = testCamera_();
		
		
		if(testCamera())
			return true;
		
		//view frusturm culling
		int xMax = 0;
		int yMax = 0;
		int xMin = (screen_w -1);
		int yMin = (screen_h -1);
		
		int dx, dy;
		
		
		for(int i = 0; i < 6; i++){
			if(boundary[i].visible){
				xMax = Math.max(xMax, (int)boundary[i].xMax);
				xMin = Math.min(xMin, (int)boundary[i].xMin);
				yMax = Math.max(yMax, (int)boundary[i].yMax);
				yMin = Math.min(yMin, (int)boundary[i].yMin);
			}
		}
		
		if(xMax < xMin || yMax < yMin)
			return false;
	
		if(!enableOcclusionCulling){
			
			return true;
		}
		
	
		//image based occlusion culling
		if(xMax > (screen_w -1))
			xMax = (screen_w -1);
		if(xMin < 0)
			xMin = 0;
		if(yMax > (screen_h -1))
			yMax = (screen_h -1);
		if(yMin < 0)
			yMin = 0;
		
			dx = 21;
			dy = 21;
		
		if(xMax - xMin < 200){
			dx = 1;
		}
		

		if(yMax - yMin < 200){
			dy = 1;
		}
			
		
		for(int i = yMin; i <=yMax; i+=dy){
			for(int j = xMin + (i%2)*5; j <= xMax; j+=dx){
				if(mainThread.zBuffer[j + i*screen_w] == 0){
					return true;
				}	
			}
			if(mainThread.zBuffer[xMax + i*screen_w] == 0){
				return true;
			}	
		}
		for(int j = xMin; j <= xMax; j+=dx){
			if(mainThread.zBuffer[j + yMax*screen_w] == 0){
				return true;
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
		
		for(int i = 0; i < 6; i++){
			temp.reset();
			temp.subtract(boundary[i].centre);
			temp.add(boundary[i].normal, -0.001f);
			if(temp.dot(boundary[i].normal) > 0){
				cameraIsInside = false;
				break;
			}
		}
		return cameraIsInside;
	}
	
	public boolean testCamera_(){

		cameraIsInside_ = true;
		
		for(int i = 0; i < 6; i++){
			temp.reset();
			temp.subtract(boundary[i].centre);
			if(temp.dot(boundary[i].normal) > 0){
				cameraIsInside_ = false;
				break;
			}
		}
		return cameraIsInside_;
	}
	
	//update the 3D model
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
		
		//test if the model is visible, 
		visible = testVisibility();
		
		
		if(!visible)
			return;

		//update center in camera coordinate
		tempCentre.set(centre);
		tempCentre.subtract(camera.position);
		tempCentre.rotate_XZ(camera.XZ_angle);
		tempCentre.rotate_YZ(camera.YZ_angle);
		tempCentre.updateLocation();
		
		//update polygons
		for(int i = 0; i < polygons.length; i++){
			polygons[i].update();
		}
		
		
		
		//sort polygons 
		//if(!sortedPolygons && ((mainThread.frameIndex <= 1) || camera.updating)){
		//	sortPolygons();
		//	
		//}
		
		draw();
	}
	
	public void sortPolygons(){
		int length = polygons.length;// - backGroundModelCount;
		for(int i = 1; i < length; i++){
			for(int j = sortIndexStart; j <length - i; j++){
				if(geometry.comparePolygons(polygons[j+1], polygons[j])){
					polygon3D temp = polygons[j+1];
					polygons[j+1] = polygons[j];
					polygons[j] = temp;
				}
			}
		}
	}
	

	
	//draw  the model
	public void draw(){
		if(visible){
			
			if(drawBoundary){
				for(int i = 0; i < boundary.length; i++){
					boundary[i].draw();
				}
			}else{
				for(int i = 0; i < polygons.length; i++){
					polygons[i].draw();
				}
			}
		}
	}
	
	//the centre of the model is calculated by averaging the centres
	//of the first 4 polygons from the boundary
	public final void findCentre(){
		centre = new vector(0,0,0);
		for(int i = 0; i < 4; i++)
			centre.add(boundary[i].centre);
		centre.scale(1.0f/4);
		tempCentre.set(centre);
	}
	
	//create a arbitrary vertex
	public  vector put(double i, double j, double k){
		vector temp = start.myClone();
		temp.add(iDirection, (float)i);
		temp.add(jDirection, (float)j);
		temp.add(kDirection, (float)k);
		return temp;
	}
	
	//change the 3d geometry of a vertex
	public final void change(float i, float j, float k, vector v){
		v.set(start);
		v.add(iDirection, i);
		v.add(jDirection, j);
		v.add(kDirection, k);
	}	
	
	//create color in binary format 
	public static int createColor(int r, int g, int b){
		return  b + (g << 6) + (r << 11);
	}

	
	public boolean testCameraisInside(){
		return cameraIsInside_;
	}
}
