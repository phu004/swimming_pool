package core;
public class wallNorth extends solidObject{
	
	public wallNorth(vector origin){
		//define the origin point in object space
		start = origin.myClone();
		centre = origin.myClone();
		tempCentre = origin.myClone();
			
		//define axis in object space
		iDirection = new vector(0.25f/2,0,0);
		jDirection = new vector(0,0.25f/2,0);
		kDirection = new vector(0,0,0.25f/2);
		
		//create bounding box
		makeBoundary(0.199f, 0.043f, 0.003f);
				
		//create polygons 
		makePolygons();
		
		//drawBoundary = true;
	}
	
	public void makePolygons(){
		
		//create vertices
		v = new vector[16];
		
		v[0] = put(-0.2f, -0.016f, -0.004f);
		v[1] = put(0.2f, -0.016f, -0.004f);
		v[2] = put(0.2f, -0.043f, -0.004f);
		v[3] = put(-0.2f, -0.043f, -0.004f);
		
		v[4] = put(-0.2f,  0.0205f, -0.004f);
		v[5] = put(0.2f,  0.0205f, -0.004f);
		v[6] = put(0.2f, -0.016f, -0.004f);
		v[7] = put(-0.2f, -0.016f, -0.004f);
	
		
		
		//create polygons
		polygons = new polygon3D[2];
		polygons[0] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[12], 16f, 1, 6 );
		polygons[1] = new polygon3D(new vector[]{v[4],v[5],v[6],v[7]}, v[4], v[5], v[7], mainThread.textures[13], 10f, 1, 6 );
		

		polygons[0].diffuse_I = 22;
		polygons[1].diffuse_I = 28;
		
		polygon3D[] wallLower = new polygon3D[2];
		
		v[8] = put(-0.2f, -0.04f, -0.0057f);
		v[9] = put(0.2f, -0.04f, -0.0057f);
		v[10] = put(0.2f, -0.0435f, -0.0057f);
		v[11] = put(-0.2f, -0.0435f, -0.0057f);
		
		v[12] = put(-0.2f, -0.04f, -0.004f);
		v[13] = put(0.2f, -0.04f, -0.004f);
		v[14] = put(0.2f, -0.04f, -0.0057f);
		v[15] = put(-0.2f, -0.04f, -0.0057f);
		
		
		
		wallLower[0] = new polygon3D(new vector[]{v[8],v[9],v[10],v[11]}, v[8], v[9], v[11], mainThread.textures[17], 120f, 2, 5 );
		wallLower[1] = new polygon3D(new vector[]{v[12],v[13],v[14],v[15]}, v[12], v[13], v[15], mainThread.textures[17], 120f, 2, 5 );
		
		for(int i = 0; i < wallLower.length; i++){
			sceneGraph.addMisc(wallLower[i]);
			
		}
		
		wallLower[0].diffuse_I=20;
		wallLower[1].diffuse_I=13;
		
		for(int i = 0; i < polygons.length; i++)
			polygons[i].diffuse_I = 63 - (31 - polygons[i].diffuse_I)*2;
		
		for(int i = 0; i < wallLower.length; i++)
			wallLower[i].diffuse_I = 63 - (31 - wallLower[i].diffuse_I)*2;
	}

}
