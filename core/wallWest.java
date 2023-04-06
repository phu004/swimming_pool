package core;
public class wallWest extends solidObject{
	
	public wallWest(vector origin){
		//define the origin point in object space
		start = origin.myClone();
		centre = origin.myClone();
		tempCentre = origin.myClone();
			
		//define axis in object space
		iDirection = new vector(0.25f/2,0,0);
		jDirection = new vector(0,0.25f/2,0);
		kDirection = new vector(0,0,0.25f/2);
		
		//create bounding box
		makeBoundary(0.004f, 0.043f, 0.34f);
				
		//create polygons 
		makePolygons();
		
		//drawBoundary = true;
	}
	
	public void makePolygons(){
		//create vertices
		v = new vector[68];
		
		v[0] = put(0.004f, -0.016f, -0.34f);
		v[1] = put(0.004f, -0.016f, -0.06f);
		v[2] = put(0.004f, -0.043f, -0.06f);
		v[3] = put(0.004f, -0.043f, -0.34f);
		
		
		v[4] = put(0.004f, -0.016f, 0.02f);
		v[5] = put(0.004f, -0.016f, 0.3f);
		v[6] = put(0.004f, -0.043f, 0.3f);
		v[7] = put(0.004f, -0.043f, 0.02f);
		
		v[8] = put(0.004f, 0.02f, -0.34f);
		v[9] = put(0.004f, 0.02f, -0.06f);
		v[10] = put(0.004f, -0.016f, -0.06f);
		v[11] = put(0.004f, -0.016f, -0.34f);
		

		v[12] = put(0.004f, 0.02f, 0.02f);
		v[13] = put(0.004f,  0.02f, 0.3f);
		v[14] = put(0.004f, -0.016f, 0.3f);
		v[15] = put(0.004f, -0.016f, 0.02f);
		
		v[16] = put(0.004f, 0.02f, -0.061f);
		v[17] = put(0.004f, 0.02f, 0.021f);
		v[18] = put(0.004f, 0.013f, 0.021f);
		v[19] = put(0.004f, 0.013f, -0.061f);
		
		v[20] = put(0.004f, -0.016f, -0.06f);
		v[21] = put(-0.001f, -0.016f, -0.06f);
		v[22] = put(-0.001f, -0.043f, -0.06f);
		v[23] = put(0.004f, -0.043f, -0.06f);
		
		v[24] = put(0.0043f, 0.013f, -0.06f);
		v[25] = put(-0.001f, 0.013f, -0.06f);
		v[26] = put(-0.001f, -0.016f, -0.06f);
		v[27] = put(0.0043f, -0.016f, -0.06f);
		
		v[28] = put(0.0043f, 0.013f, -0.06f);
		v[29] = put(0.0043f, 0.013f,  0.0201f);
		v[30] = put(-0.001f, 0.013f,  0.0201f);
		v[31] = put(-0.001f, 0.013f,  -0.06f);
		
		v[32] = put(-0.001f, 0.013f,  0.0201f);
		v[33] = put(0.004f, 0.013f,  0.0201f);
		v[34] = put(0.004f, -0.016f,  0.0201f);
		v[35] = put(-0.001f, -0.016f,  0.0201f);

		v[36] = put(-0.001f, -0.016f,  0.0201f);
		v[37] = put(0.004f, -0.016f,  0.0201f);
		v[38] = put(0.004f, -0.043f,  0.0201f);
		v[39] = put(-0.001f, -0.043f,  0.0201f);
		
		
		v[40] = put(-0.001f, -0.043f,  0.0426f);
		v[41] = put(0.006f, -0.043f,  0.0426f);
		v[42] = put(0.006f, -0.043f,  -0.078f);
		v[43] = put(-0.001f, -0.043f,  -0.078f);
		
		
		
		//create polygons
		polygons = new polygon3D[11];
		polygons[0] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[12], 11f, 1, 5 );
		polygons[1] = new polygon3D(new vector[]{v[4],v[5],v[6],v[7]}, v[4], v[5], v[7], mainThread.textures[12], 11f, 1, 5 );
		polygons[2] = new polygon3D(new vector[]{v[8],v[9],v[10],v[11]}, v[8], v[9], v[11], mainThread.textures[13], 8f, 1, 5 );
		polygons[3] = new polygon3D(new vector[]{v[12],v[13],v[14],v[15]}, v[12], v[13], v[15], mainThread.textures[13], 8f, 1, 5 );
		polygons[4] = new polygon3D(new vector[]{v[16],v[17],v[18],v[19]}, v[16], v[17], v[19], mainThread.textures[13], 2f, 0.2f, 5 );
		polygons[5] = new polygon3D(new vector[]{v[20],v[21],v[22],v[23]}, v[20], v[21], v[23], mainThread.textures[12], 0.1f, 1f, 5 );
		polygons[6] = new polygon3D(new vector[]{v[24],v[25],v[26],v[27]}, v[24], v[25], v[27], mainThread.textures[13], 0.1f, 1f, 5 );
		polygons[7] = new polygon3D(new vector[]{v[28],v[29],v[30],v[31]}, v[28], v[29], v[31], mainThread.textures[13], 1.5f, 0.1f, 5 );
		polygons[8] = new polygon3D(new vector[]{v[32],v[33],v[34],v[35]}, v[32], v[33], v[35], mainThread.textures[13], 0.1f, 1f, 5 );
		polygons[9] = new polygon3D(new vector[]{v[36],v[37],v[38],v[39]}, v[36], v[37], v[39], mainThread.textures[12], 0.1f, 1f, 5 );
		polygons[10] = new polygon3D(new vector[]{v[40],v[41],v[42],v[43]},  new vector(-0.025f, -0.0053749997f, 0.050375f), new vector(-0.016225f, -0.0053749997f, 0.050375f), new vector(-0.025f, -0.0053749997f, -0.010375001f), mainThread.textures[11], 1.5f, 10, 6 );
		
	
		
		
		polygons[0].diffuse_I = 20;
		polygons[1].diffuse_I = 20;
		polygons[2].diffuse_I = 26;
		polygons[3].diffuse_I = 26;
		polygons[4].diffuse_I = 26;
		polygons[5].diffuse_I = 26;
		polygons[6].diffuse_I = 31;
		polygons[7].diffuse_I = 20;
		polygons[8].diffuse_I = 31;
		polygons[9].diffuse_I = 26;
		polygons[10].diffuse_I = 27;
		
		v[44] = put(0.0055f, -0.04f, -0.34f);
		v[45] = put(0.0055f, -0.04f, -0.06f);
		v[46] = put(0.0055f, -0.0435f, -0.06f);
		v[47] = put(0.0055f, -0.0435f, -0.34f);
		
		v[48] = put(0.0055f, -0.04f, -0.06f);
		v[49] = put(0.0055f, -0.04f, -0.34f);
		v[50] = put(0.004f, -0.04f, -0.34f);
		v[51] = put(0.004f, -0.04f, -0.06f);
		
		v[52] = put(0.0055f, -0.04f, 0.0198f);
		v[53] = put(0.0055f, -0.04f, 0.3f);
		v[54] = put(0.0055f, -0.0435f, 0.3f);
		v[55] = put(0.0055f, -0.0435f, 0.0198f);
		
		v[56] = put(0.0055f, -0.04f, 0.3f);
		v[57] = put(0.0055f, -0.04f, 0.0198f);
		v[58] = put(0.004f, -0.04f, 0.0198f);
		v[59] = put(0.004f, -0.04f, 0.3f);
		
		v[60] = put(0.0055f, -0.04f, -0.06f);
		v[61] = put(0.004f, -0.04f, -0.06f);
		v[62] = put(0.004f, -0.0435f, -0.06f);
		v[63] = put(0.0055f, -0.0435f, -0.06f);
		
		v[64] = put(0.004f, -0.04f, 0.0198f);
		v[65] = put(0.0055f, -0.04f, 0.0198f);
		v[66] = put(0.0055f, -0.0435f, 0.0198f);
		v[67] = put(0.004f, -0.0435f, 0.0198f);
		
		
		
		
		polygon3D[] wallLower = new polygon3D[6];
		wallLower[0] = new polygon3D(new vector[]{v[44],v[45],v[46],v[47]}, v[44], v[45], v[47], mainThread.textures[17], 120f, 2, 5 );
		wallLower[1] = new polygon3D(new vector[]{v[48],v[49],v[50],v[51]}, v[48], v[49], v[51], mainThread.textures[17], 120f, 1, 5 );
		wallLower[2] = new polygon3D(new vector[]{v[52],v[53],v[54],v[55]}, v[52], v[53], v[55], mainThread.textures[17], 120f, 2, 5 );
		wallLower[3] = new polygon3D(new vector[]{v[56],v[57],v[58],v[59]}, v[56], v[57], v[59], mainThread.textures[17], 120f, 1, 5 );
		wallLower[4] = new polygon3D(new vector[]{v[60],v[61],v[62],v[63]}, v[60], v[61], v[63], mainThread.textures[17], 1f, 2, 5 );
		wallLower[5] = new polygon3D(new vector[]{v[64],v[65],v[66],v[67]}, v[64], v[65], v[67], mainThread.textures[17], 1f, 2, 5 );
		
		
		for(int i = 0; i < wallLower.length; i++){
			sceneGraph.addMisc(wallLower[i]);
		}
		wallLower[0].diffuse_I=20;
		wallLower[1].diffuse_I=13;
		wallLower[2].diffuse_I=20;
		wallLower[3].diffuse_I=13;
		wallLower[4].diffuse_I=25;
		wallLower[5].diffuse_I=25;
		
		for(int i = 0; i < polygons.length; i++)
			polygons[i].diffuse_I = 63 - (31 - polygons[i].diffuse_I)*2;
		
		for(int i = 0; i < wallLower.length; i++)
			wallLower[i].diffuse_I = 63 - (31 - wallLower[i].diffuse_I)*2;
	
	}
}
	
	
