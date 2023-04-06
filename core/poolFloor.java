package core;
public class poolFloor extends solidObject{
	
	public poolFloor(vector origin){
		//define the origin point in object space
		start = origin.myClone();
		centre = origin.myClone();
		tempCentre = origin.myClone();
			
		//define axis in object space
		iDirection = new vector(0.25f/2,0,0);
		jDirection = new vector(0,0.25f/2,0);
		kDirection = new vector(0,0,0.25f/2);
		
		//create bounding box
		makeBoundary(0.2f, 0.01f, 0.35f);
				
		//create polygons 
		makePolygons();
		
		//drawBoundary = true;
	}
	
	public void makePolygons(){
		//create vertices
		v = new vector[72];
		v[0] = put(-0.13f, 0.012f, 0.23f);
		v[1] = put(-0.12f, 0.012f, 0.23f);
		v[2] = put(-0.12f, 0.012f, -0.23f);
		v[3] = put(-0.13f, 0.012f, -0.23f);
		v[4] = put(-0.12f, 0.0099f, 0.23f);
		v[5] = put(-0.12f, 0.0099f, -0.23f);
		
		v[6] = put(0.13f, 0.012f, 0.23f);
		v[7] = put(0.12f, 0.012f, 0.23f);
		v[8] = put(0.12f, 0.012f, -0.23f);
		v[9] = put(0.13f, 0.012f, -0.23f);
		v[10] = put(0.12f, 0.0099f, 0.23f);
		v[11] = put(0.12f, 0.0099f, -0.23f);
		
		v[12] = put(-0.13f, 0.012f, 0.24f);
		v[13] = put(0.13f, 0.012f, 0.24f);
		v[14] = put(0.13f, 0.012f, 0.23f);
		v[15] = put(-0.13f, 0.012f, 0.23f);
		v[16] = put(-0.13f, 0.0099f, 0.23f);
		v[17] = put(0.13f, 0.0099f, 0.23f);
		
		v[18] = put(-0.13f, 0.012f, -0.24f);
		v[19] = put(0.13f, 0.012f, -0.24f);
		v[20] = put(0.13f, 0.012f, -0.23f);
		v[21] = put(-0.13f, 0.012f, -0.23f);
		v[22] = put(-0.13f, 0.0099f, -0.23f);
		v[23] = put(0.13f, 0.0099f, -0.23f);
		
		
		v[24] = put(-0.2f, 0.012f, 0.23f);
		v[25] = put(-0.13f, 0.012f, 0.23f);
		v[26] = put(-0.13f, 0.012f, -0.23f);
		v[27] = put(-0.2f, 0.012f, -0.23f);
	
		v[28] = put(0.13f, 0.012f, 0.23f);
		v[29] = put(0.2f, 0.012f, 0.23f);
		v[30] = put(0.2f, 0.012f, -0.23f);
		v[31] = put(0.13f, 0.012f, -0.23f);
		
		v[32] = put(-0.13f, 0.012f, 0.3f);
		v[33] = put(0.13f, 0.012f, 0.3f);
		v[34] = put(0.13f, 0.012f, 0.24f);
		v[35] = put(-0.13f, 0.012f, 0.24f);
		
		v[36] = put(0.13f, 0.012f, -0.34f);
		v[37] = put(-0.13f, 0.012f, -0.34f);
		v[38] = put(-0.13f, 0.012f, -0.24f);
		v[39] = put(0.13f, 0.012f, -0.24f);
		
		v[40] = put(-0.2f,  0.012f, 0.3);
		v[41] = put(-0.13f,  0.012f, 0.3);
		v[42] = put(-0.13f,  0.012f, 0.24);
		v[43] = put(-0.2f,  0.012f, 0.24);
		
		v[44] = put(0.2f,  0.012f, 0.24);
		v[45] = put(0.13f,  0.012f, 0.24);
		v[46] = put(0.13f,  0.012f, 0.3);
		v[47] = put(0.2f,  0.012f, 0.3);
		
		
		v[48] = put(-0.2f,  0.012f, 0.24);
		v[49] = put(-0.13f,  0.012f, 0.24);
		v[50] = put(-0.13f,  0.012f, 0.23);
		v[51] = put(-0.2f,  0.012f, 0.23);
		
		v[52] = put(0.2f,  0.012f, 0.23);
		v[53] = put(0.13f,  0.012f, 0.23);
		v[54] = put(0.13f,  0.012f, 0.24);
		v[55] = put(0.2f,  0.012f, 0.24);
		
		v[56] = put(-0.2f,  0.012f, -0.24);
		v[57] = put(-0.13f,  0.012f, -0.24);
		v[58] = put(-0.13f,  0.012f, -0.34);
		v[59] = put(-0.2f,  0.012f, -0.34);
		
		v[60] = put(0.2f,  0.012f, -0.34);
		v[61] = put(0.13f,  0.012f, -0.34);
		v[62] = put(0.13f,  0.012f, -0.24);
		v[63] = put(0.2f,  0.012f, -0.24);
		
		v[64] = put(-0.2f,  0.012f, -0.23);
		v[65] = put(-0.13f,  0.012f, -0.23);
		v[66] = put(-0.13f,  0.012f, -0.24);
		v[67] = put(-0.2f,  0.012f, -0.24);
		
		v[68] = put(0.2f,  0.012f, -0.24);
		v[69] = put(0.13f,  0.012f, -0.24);
		v[70] = put(0.13f,  0.012f, -0.23);
		v[71] = put(0.2f,  0.012f, -0.23);
		
		
		
		
		
		
		
		vector O = new vector(-0.025f, -0.0053749997f, 0.050375f);
		vector U =  new vector(-0.016225f, -0.0053749997f, 0.050375f);
		vector V =  new vector(-0.025f, -0.0053749997f, -0.010375001f);
		
		//create polygons
		polygons = new polygon3D[12];
		polygons[0] = new polygon3D(new vector[]{v[24],v[25],v[26],v[27]}, O,U,V, mainThread.textures[11], 1.5f, 10, 6 );
		polygons[1] = new polygon3D(new vector[]{v[28],v[29],v[30],v[31]},O,U,V, mainThread.textures[11], 1.5f, 10, 6 );
		polygons[2] = new polygon3D(new vector[]{v[32],v[33],v[34],v[35]}, O,U,V, mainThread.textures[11], 1.5f, 10, 6 );
		polygons[3] = new polygon3D(new vector[]{v[36],v[37],v[38],v[39]},O,U,V, mainThread.textures[11], 1.5f, 10, 6  );
		polygons[4] = new polygon3D(new vector[]{v[40],v[41],v[42],v[43]},O,U,V, mainThread.textures[11], 1.5f, 10, 6  );
		polygons[5] = new polygon3D(new vector[]{v[44],v[45],v[46],v[47]},O,U,V, mainThread.textures[11], 1.5f, 10, 6  );
		polygons[6] = new polygon3D(new vector[]{v[48],v[49],v[50],v[51]},O,U,V, mainThread.textures[11], 1.5f, 10, 6  );
		polygons[7] = new polygon3D(new vector[]{v[52],v[53],v[54],v[55]},O,U,V, mainThread.textures[11], 1.5f, 10, 6  );
		polygons[8] = new polygon3D(new vector[]{v[56],v[57],v[58],v[59]},O,U,V, mainThread.textures[11], 1.5f, 10, 6  );
		polygons[9] = new polygon3D(new vector[]{v[60],v[61],v[62],v[63]},O,U,V, mainThread.textures[11], 1.5f, 10, 6  );
		polygons[10] = new polygon3D(new vector[]{v[64],v[65],v[66],v[67]},O,U,V, mainThread.textures[11], 1.5f, 10, 6  );
		polygons[11] = new polygon3D(new vector[]{v[68],v[69],v[70],v[71]},O,U,V, mainThread.textures[11], 1.5f, 10, 6  );
		
		for(int i = 0; i < polygons.length; i++){
			polygons[i].diffuse_I = 28;
		}
		//polygons[0].diffuse_I = 63;
		
		
		
		
		polygon3D[] tiles = new polygon3D[8];
		tiles[0] = new polygon3D(new vector[]{v[0], v[1], v[2], v[3]}, v[0], v[1], v[3], mainThread.textures[10], 1, 80, 5); 
		tiles[1] = new polygon3D(new vector[]{v[1], v[4], v[5], v[2]}, v[1], v[4], v[2], mainThread.textures[10], 0.2f, 80, 5); 
		tiles[2] = new polygon3D(new vector[]{v[7], v[6], v[9], v[8]}, v[7], v[6], v[8], mainThread.textures[10], 1, 80, 5); 
		tiles[3] = new polygon3D(new vector[]{v[8], v[11], v[10], v[7]}, v[8], v[11], v[7], mainThread.textures[10], 0.2f, 80, 5); 
		tiles[4] = new polygon3D(new vector[]{v[12], v[13], v[14], v[15]}, v[12], v[13], v[15], mainThread.textures[10], 40f, 1, 5); 
		tiles[5] = new polygon3D(new vector[]{v[15], v[14], v[17], v[16]}, v[15], v[14], v[16], mainThread.textures[10], 40f, 0.2f, 5);
		tiles[6] = new polygon3D(new vector[]{v[21], v[20], v[19], v[18]}, v[21], v[20], v[18], mainThread.textures[10], 40f, 1, 5); 
		tiles[7] = new polygon3D(new vector[]{v[20], v[21], v[22], v[23]}, v[20], v[21], v[23], mainThread.textures[10], 40f, 0.2f, 5);
		
		
		
		for(int i = 0; i < tiles.length; i++){
			sceneGraph.addMisc(tiles[i]);
			tiles[i].diffuse_I-=13;
		}
		
		for(int i = 0; i < polygons.length; i++)
			polygons[i].diffuse_I = 63 - (31 - polygons[i].diffuse_I)*2;
		for(int i = 0; i < tiles.length; i++){
			tiles[i].diffuse_I = 63 - (31 - tiles[i].diffuse_I)*2;
		}
		
		
	}
}
