package core;

public class poolCeiling extends solidObject{

	public vector origin;
	
	public poolCeiling(vector origin){
		//define the origin point in object space
		this.origin = origin.myClone();
		start = origin.myClone();
		centre = origin.myClone();
		tempCentre = origin.myClone();
			
		//define axis in object space
		iDirection = new vector(0.25f/2,0,0);
		jDirection = new vector(0,0.25f/2,0);
		kDirection = new vector(0,0,0.25f/2);
		
		//create bounding box
		start = put(0, 0.015f, 0);
		makeBoundary(0.199f, 0.035f, 0.319f);
		
		start = put(0, -0.015f, 0);
				
		//create polygons 
		makePolygons();
		
		//drawBoundary = true;
	}
	
	public void makePolygons(){
		//create vertices 
		v = new vector[84];
		
		v[0] = put(-0.201f, -0.0202f, 0.32f);
		v[1] = put(-0.11f, -0.0202f, 0.32f);
		v[2] = put(-0.11f, -0.0202f, -0.32f);
		v[3] = put(-0.201f, -0.0202f, -0.32f);
		
		v[4] = put(0.201f, -0.0202f, 0.32f);
		v[5] = put(0.11f, -0.0202f, 0.32f);
		v[6] = put(0.11f, -0.0202f, -0.32f);
		v[7] = put(0.201f, -0.0202f, -0.32f);
		
		v[8] = put(0.115f, -0.0202f, 0.32f);
		v[9] = put(-0.115f, -0.0202f, 0.32f);
		v[10] = put(-0.115f, -0.0202f, 0.22f);
		v[11] = put(0.115f, -0.0202f, 0.22f);
		
		v[12] = put(-0.115f, -0.0202f, -0.32f);
		v[13] = put(0.115f, -0.0202f, -0.32f);
		v[14] = put(0.115f, -0.0202f, -0.22f);
		v[15] = put(-0.115f, -0.0202f, -0.22f);
		
		
		
		//create polygons
		polygons = new polygon3D[215];
		polygons[0] = new polygon3D(new vector[]{v[3],v[2],v[1],v[0]}, v[3], v[2], v[0], mainThread.textures[13], 2f, 12, 6 );
		polygons[1] = new polygon3D(new vector[]{v[4],v[5],v[6],v[7]}, v[4], v[5], v[7], mainThread.textures[13], 2f, 12, 6 );
		polygons[2] = new polygon3D(new vector[]{v[8],v[9],v[10],v[11]}, v[8], v[9], v[11], mainThread.textures[13], 6f, 2, 6 );
		polygons[3] = new polygon3D(new vector[]{v[12],v[13],v[14],v[15]}, v[12], v[13], v[15], mainThread.textures[13], 6f, 2, 6 );
		
		
		polygons[0].diffuse_I = 20;
		polygons[1].diffuse_I = 20;
		polygons[2].diffuse_I=20;
		polygons[3].diffuse_I=20;
		
		v[16] = put(0.11f, -0.019f, 0.22f);
		v[17] = put(-0.11f, -0.019f, 0.22f);
		v[18] = put(-0.11f, -0.022f, 0.22f);
		v[19] = put(0.11f, -0.022f, 0.22f);
		
		v[20] = put(-0.11f, -0.019f, -0.22f);
		v[21] = put(0.11f, -0.019f, -0.22f);
		v[22] = put(0.11f, -0.022f, -0.22f);
		v[23] = put(-0.11f, -0.022f, -0.22f);
		
		v[24] = put(-0.11f, -0.019f, 0.22f);
		v[25] = put(-0.11f, -0.019f, -0.22f);
		v[26] = put(-0.11f, -0.022f, -0.22f);
		v[27] = put(-0.11f, -0.022f, 0.22f);
		
		v[28] = put(0.11f, -0.019f, -0.22f);
		v[29] = put(0.11f, -0.019f, 0.22f);
		v[30] = put(0.11f, -0.022f, 0.22f);
		v[31] = put(0.11f, -0.022f, -0.22f);
		
		polygons[4] = new polygon3D(new vector[]{v[16],v[17],v[18],v[19]}, v[16], v[17], v[19], mainThread.textures[18], 24f, 0.1f, 5 );
		polygons[5] = new polygon3D(new vector[]{v[20],v[21],v[22],v[23]}, v[20], v[21], v[23], mainThread.textures[18], 24f, 0.1f, 5 );
		polygons[6] = new polygon3D(new vector[]{v[24],v[25],v[26],v[27]}, v[24], v[25], v[27], mainThread.textures[18], 32f, 0.1f, 5 );
		polygons[7] = new polygon3D(new vector[]{v[28],v[29],v[30],v[31]}, v[28], v[29], v[31], mainThread.textures[18], 32f, 0.1f, 5 );
		
		polygons[4].diffuse_I = 10;
		polygons[5].diffuse_I = 10;
		polygons[6].diffuse_I = 10;
		polygons[7].diffuse_I = 10;
		
		v[32] = put(0.1105f, -0.022f, 0.2204f);
		v[33] = put(-0.1105f, -0.022f, 0.2204f);
		v[34] = put(-0.1105f, -0.022f, 0.215f);
		v[35] = put(0.1105f, -0.022f, 0.215f);
		
		v[36] = put(-0.1105f, -0.022f, -0.2204f);
		v[37] = put(0.1105f, -0.022f, -0.2204f);
		v[38] = put(0.1105f, -0.022f, -0.215f);
		v[39] = put(-0.1105f, -0.022f, -0.215f);
		
		v[40] = put(0.105f, -0.022f, 0.215f);
		v[41] = put(0.105f, -0.022f, -0.215f);
		v[42] = put(0.1104f, -0.022f, -0.215f);
		v[43] = put(0.1104f, -0.022f, 0.215f);
		
		v[44] = put(-0.105f, -0.022f, -0.215f);
		v[45] = put(-0.105f, -0.022f, 0.215f);
		v[46] = put(-0.1104f, -0.022f, 0.215f);
		v[47] = put(-0.1104f, -0.022f, -0.215f);
		
		
		
		polygons[8] = new polygon3D(new vector[]{v[32],v[33],v[34],v[35]}, v[32], v[33], v[35], mainThread.textures[18], 12f, 0.4f, 5 );
		polygons[9] = new polygon3D(new vector[]{v[36],v[37],v[38],v[39]}, v[36], v[37], v[39], mainThread.textures[18], 12f, 0.4f, 5 );
		polygons[10] = new polygon3D(new vector[]{v[40],v[41],v[42],v[43]}, v[40], v[41], v[43], mainThread.textures[18], 24f, 0.4f, 5 );
		polygons[11] = new polygon3D(new vector[]{v[44],v[45],v[46],v[47]}, v[44], v[45], v[47], mainThread.textures[18], 24f, 0.4f, 5 );
		
		polygons[8].diffuse_I = 20;
		polygons[9].diffuse_I = 20;
		polygons[10].diffuse_I = 20;
		polygons[11].diffuse_I = 20;
		
		v[48] = put(-0.105f, 0.00f, 0.215f);
		v[49] = put(0.105f, 0.00f, 0.215f);
		v[50] = put(0.105f, -0.022f, 0.215f);
		v[51] = put(-0.105f, -0.022f, 0.215f);
		
		v[52] = put(0.105f, 0.00f, -0.215f);
		v[53] = put(-0.105f, 0.00f, -0.215f);
		v[54] = put(-0.105f, -0.022f, -0.215);
		v[55] = put(0.105f, -0.022f, -0.215f);
		
		v[56] = put(0.105f, 0.00f, 0.215f);
		v[57] = put(0.105f, 0.00f, -0.215f);
		v[58] = put(0.105f, -0.022f, -0.215f);
		v[59] = put(0.105f, -0.022f, 0.215f);
		
		v[60] = put(-0.105f, 0.00f, -0.215f);
		v[61] = put(-0.105f, 0.00f, 0.215f);
		v[62] = put(-0.105f, -0.022f, 0.215f);
		v[63] = put(-0.105f, -0.022f, -0.215f);
		
		
		
		polygons[12] = new polygon3D(new vector[]{v[48],v[49],v[50],v[51]}, v[48], v[49], v[51], mainThread.textures[18], 10f, 1f, 5 );
		polygons[13] = new polygon3D(new vector[]{v[52],v[53],v[54],v[55]}, v[52], v[53], v[55], mainThread.textures[18], 10f, 1f, 5 );
		polygons[14] = new polygon3D(new vector[]{v[56],v[57],v[58],v[59]}, v[56], v[57], v[59], mainThread.textures[18], 20f, 1f, 5 );
		polygons[15] = new polygon3D(new vector[]{v[60],v[61],v[62],v[63]}, v[60], v[61], v[63], mainThread.textures[18], 20f, 1f, 5 );
		
		polygons[12].diffuse_I = 32;
		polygons[13].diffuse_I = 32;
		polygons[14].diffuse_I = 32;
		polygons[15].diffuse_I = 32;
		
		v[64] = put(-0.105f, 0.005f, 0.215f);
		v[65] = put(0.105f, 0.005f, 0.215f);
		v[66] = put(0.105f, 0.00f, 0.215f);
		v[67] = put(-0.105f, 0.00f, 0.215f);
		
		v[68] = put(0.105f, 0.005f, -0.215f);
		v[69] = put(-0.105f, 0.005f, -0.215f);
		v[70] = put(-0.105f, 0, -0.215);
		v[71] = put(0.105f, 0, -0.215f);
		
		v[72] = put(0.105f, 0.005f, 0.215f);
		v[73] = put(0.105f, 0.005f, -0.215f);
		v[74] = put(0.105f, 0, -0.215f);
		v[75] = put(0.105f, 0f, 0.215f);
		
		v[76] = put(-0.105f, 0.005f, -0.215f);
		v[77] = put(-0.105f, 0.005f, 0.215f);
		v[78] = put(-0.105f, 0, 0.215f);
		v[79] = put(-0.105f, 0, -0.215f);
		
		polygons[16] = new polygon3D(new vector[]{v[64],v[65],v[66],v[67]}, v[64], v[65], v[67], mainThread.textures[19], 5f, 1f, 3 );
		polygons[17] = new polygon3D(new vector[]{v[68],v[69],v[70],v[71]}, v[68], v[69], v[71], mainThread.textures[19], 5f, 0.5f, 3 );
		polygons[18] = new polygon3D(new vector[]{v[72],v[73],v[74],v[75]}, v[72], v[73], v[75], mainThread.textures[19], 10f, 0.5f, 3 );
		polygons[19] = new polygon3D(new vector[]{v[76],v[77],v[78],v[79]}, v[76], v[77], v[79], mainThread.textures[19], 10f, 0.5f, 3 );
		
		polygons[16].diffuse_I = 22;
		polygons[17].diffuse_I = 22;
		polygons[18].diffuse_I = 22;
		polygons[19].diffuse_I = 22;
		
		
		
		for(int i = 0; i < 5; i++){
			int l = 36;
			
			v[0] = put(-0.125f, 0.005f, 0.215f);
			v[1] = put(0f, 0.057f, 0.215f);
			v[2] = put(0f, 0.049f, 0.215f);
			v[3] = put(-0.105f, 0.005f, 0.215f);
			polygons[20 + i * l] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0,0,0.215f), put(1,0,0.215f), put(0,1,0.215f), mainThread.textures[20], 30f, 30f, 5);
			
			v[0] = put(0.125f, 0.005f, 0.215f);
			v[1] = put(0f, 0.057f, 0.215f);
			v[2] = put(0f, 0.049f, 0.215f);
			v[3] = put(0.105f, 0.005f, 0.215f);
			polygons[21 + i * l] = new polygon3D(new vector[]{v[3],v[2],v[1],v[0]}, put(0,0,0.215f), put(1,0,0.215f), put(0,1,0.215f), mainThread.textures[20], 30f, 30f, 5);
			
			v[0] = put(0f, 0.049f, 0.215f);
			v[1] = put(0f, 0.049f, 0.219f);
			v[2] = put(-0.105f, 0.005f, 0.219f);
			v[3] = put(-0.105f, 0.005f, 0.215f);
			polygons[22 + i * l] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[20], 0.3f, 2f, 5);
			
			v[0] = put(0f, 0.049f, 0.215f);
			v[1] = put(0f, 0.049f, 0.219f);
			v[2] = put(0.105f, 0.005f, 0.219f);
			v[3] = put(0.105f, 0.005f, 0.215f);
			polygons[23 + i * l] = new polygon3D(new vector[]{v[3],v[2],v[1],v[0]}, v[3], v[2], v[0], mainThread.textures[20], 0.3f, 2f, 5);
			
			v[0] = put(0.125f, 0.005f, 0.219);
			v[1] = put(0f, 0.057f, 0.219);
			v[2] = put(0f, 0.049f, 0.219);
			v[3] = put(0.105f, 0.005f, 0.219);
			polygons[24 + i * l] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0,0,0.219), put(1,0,0.219), put(0,1,0.219), mainThread.textures[20], 30f, 30f, 5);
			
			v[0] = put(-0.125f, 0.005f, 0.219);
			v[1] = put(0f, 0.057f, 0.219);
			v[2] = put(0f, 0.049f, 0.219);
			v[3] = put(-0.105f, 0.005f, 0.219);
			polygons[25 + i * l] = new polygon3D(new vector[]{v[3],v[2],v[1],v[0]}, put(0,0,0.219), put(1,0,0.219), put(0,1,0.219), mainThread.textures[20], 30f, 30f, 5);
			
			v[0] = put(-0.002, 0.049, 0.215);
			v[1] = put(0.002, 0.049, 0.215);
			v[2] = put(0.002, 0.005f, 0.215);
			v[3] = put(-0.002, 0.005f, 0.215);
			polygons[26 + i * l] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0.31,0,0.215f), put(1.31,0,0.215f), put(0.31,1,0.215f), mainThread.textures[20], 30f, 30f, 5);
			
			v[0] = put(0.002, 0.049, 0.219);
			v[1] = put(-0.002, 0.049, 0.219);
			v[2] = put(-0.002, 0.005f, 0.219);
			v[3] = put(0.002, 0.005f, 0.219);
			polygons[27 + i * l] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0.31,0,0.219f), put(1.31,0,0.219f), put(0.31,1,0.219f), mainThread.textures[20], 30f, 30f, 5);
			
			v[0] = put(0.002, 0.049, 0.215);
			v[1] = put(0.002, 0.049, 0.219);
			v[2] = put(0.002, 0.005f, 0.219);
			v[3] = put(0.002, 0.005f, 0.215);
			polygons[28 + i * l] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[20], 0.1f, 1f, 5);
			polygons[28 + i * l].diffuse_I-=4;
			
			v[0] = put(-0.002, 0.049, 0.215);
			v[1] = put(-0.002, 0.049, 0.219);
			v[2] = put(-0.002, 0.005f, 0.219);
			v[3] = put(-0.002, 0.005f, 0.215);
			polygons[29 + i * l] = new polygon3D(new vector[]{v[3],v[2],v[1],v[0]}, v[3], v[2], v[0], mainThread.textures[20], 0.1f, 1f, 5);
			polygons[29 + i * l].diffuse_I-=4;
			
			start = put(-0.055,0, 0);
			
			v[0] = put(-0.002, 0.028, 0.215);
			v[1] = put(0.002, 0.028, 0.215);
			v[2] = put(0.002, 0.005f, 0.215);
			v[3] = put(-0.002, 0.005f, 0.215);
			polygons[30 + i * l] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0.31,0,0.215f), put(1.31,0,0.215f), put(0.31,1,0.215f), mainThread.textures[20], 30f, 30f, 5);
			
			v[0] = put(0.002, 0.028, 0.219);
			v[1] = put(-0.002, 0.028, 0.219);
			v[2] = put(-0.002, 0.005f, 0.219);
			v[3] = put(0.002, 0.005f, 0.219);
			polygons[31 + i * l] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0.31,0,0.219f), put(1.31,0,0.219f), put(0.31,1,0.219f), mainThread.textures[20], 30f, 30f, 5);
			
			v[0] = put(0.002, 0.028, 0.215);
			v[1] = put(0.002, 0.028, 0.219);
			v[2] = put(0.002, 0.005f, 0.219);
			v[3] = put(0.002, 0.005f, 0.215);
			polygons[32 + i * l] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[20], 0.1f, 1f, 5);
			polygons[32 + i * l].diffuse_I-=4;
			
			v[0] = put(-0.002, 0.028, 0.215);
			v[1] = put(-0.002, 0.028, 0.219);
			v[2] = put(-0.002, 0.005f, 0.219);
			v[3] = put(-0.002, 0.005f, 0.215);
			polygons[33 + i * l] = new polygon3D(new vector[]{v[3],v[2],v[1],v[0]}, v[3], v[2], v[0], mainThread.textures[20], 0.1f, 1f, 5);
			polygons[33 + i * l].diffuse_I-=4;
			
			start = put(0.11,0, 0);
			
			v[0] = put(-0.002, 0.028, 0.215);
			v[1] = put(0.002, 0.028, 0.215);
			v[2] = put(0.002, 0.005f, 0.215);
			v[3] = put(-0.002, 0.005f, 0.215);
			polygons[34 + i * l] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0.31,0,0.215f), put(1.31,0,0.215f), put(0.31,1,0.215f), mainThread.textures[20], 30f, 30f, 5);
			
			v[0] = put(0.002, 0.028, 0.219);
			v[1] = put(-0.002, 0.028, 0.219);
			v[2] = put(-0.002, 0.005f, 0.219);
			v[3] = put(0.002, 0.005f, 0.219);
			polygons[35 + i * l] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0.31,0,0.219f), put(1.31,0,0.219f), put(0.31,1,0.219f), mainThread.textures[20], 30f, 30f, 5);
			
			v[0] = put(0.002, 0.028, 0.215);
			v[1] = put(0.002, 0.028, 0.219);
			v[2] = put(0.002, 0.005f, 0.219);
			v[3] = put(0.002, 0.005f, 0.215);
			polygons[36 + i * l] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[20], 0.1f, 1f, 5);
			polygons[36 + i * l].diffuse_I-=4;
			
			v[0] = put(-0.002, 0.028, 0.215);
			v[1] = put(-0.002, 0.028, 0.219);
			v[2] = put(-0.002, 0.005f, 0.219);
			v[3] = put(-0.002, 0.005f, 0.215);
			polygons[37 + i * l] = new polygon3D(new vector[]{v[3],v[2],v[1],v[0]}, v[3], v[2], v[0], mainThread.textures[20], 0.1f, 1f, 5);
			polygons[37 + i * l].diffuse_I-=4;
			
			start = put(-0.055,0, 0);
			
			if(i == 4){
				start = put(0,0, -0.1085);
				break;
			}
			
			start = put(0,0, -0.026);
			
			v[0] = put(-0.125f, 0.005f, 0.216f);
			v[1] = put(0f, 0.057f, 0.216f);
			v[2] = put(0f, 0.055f, 0.216f);
			v[3] = put(-0.117f, 0.005f, 0.216f);
			polygons[38 + i * l] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0,0,0.215f), put(1,0,0.215f), put(0,1,0.215f), mainThread.textures[20], 30f, 30f, 5);
			
			v[0] = put(0.125f, 0.005f, 0.216f);
			v[1] = put(0f, 0.057f, 0.216f);
			v[2] = put(0f, 0.055f, 0.216f);
			v[3] = put(0.117f, 0.005f, 0.216f);
			polygons[39 + i * l] = new polygon3D(new vector[]{v[3],v[2],v[1],v[0]}, put(0,0,0.215f), put(1,0,0.215f), put(0,1,0.215f), mainThread.textures[20], 30f, 30f, 5);
			
			v[0] = put(0f, 0.055f, 0.216f);
			v[1] = put(0f, 0.055f, 0.217f);
			v[2] = put(-0.117f, 0.005f, 0.217f);
			v[3] = put(-0.117f, 0.005f, 0.216f);
			polygons[40 + i * l] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[20], 0.3f, 2f, 5);
			
			v[0] = put(0f, 0.055f, 0.216f);
			v[1] = put(0f, 0.055f, 0.217f);
			v[2] = put(0.117f, 0.005f, 0.217f);
			v[3] = put(0.117f, 0.005f, 0.216f);
			polygons[41 + i * l] = new polygon3D(new vector[]{v[3],v[2],v[1],v[0]}, v[3], v[2], v[0], mainThread.textures[20], 0.3f, 2f, 5);
			
			v[0] = put(0.125f, 0.005f, 0.217);
			v[1] = put(0f, 0.057f, 0.217);
			v[2] = put(0f, 0.055f, 0.217);
			v[3] = put(0.117f, 0.005f, 0.217);
			polygons[42 + i * l] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0,0,0.219), put(1,0,0.219), put(0,1,0.219), mainThread.textures[20], 30f, 30f, 5);
			
			v[0] = put(-0.125f, 0.005f, 0.217);
			v[1] = put(0f, 0.057f, 0.217);
			v[2] = put(0f, 0.055f, 0.217);
			v[3] = put(-0.117f, 0.005f, 0.217);
			polygons[43 + i * l] = new polygon3D(new vector[]{v[3],v[2],v[1],v[0]}, put(0,0,0.219), put(1,0,0.219), put(0,1,0.219), mainThread.textures[20], 30f, 30f, 5);
			
			start = put(0,0, -0.026);
			
			v[0] = put(-0.125f, 0.005f, 0.216f);
			v[1] = put(0f, 0.057f, 0.216f);
			v[2] = put(0f, 0.055f, 0.216f);
			v[3] = put(-0.117f, 0.005f, 0.216f);
			polygons[44 + i * l] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0,0,0.215f), put(1,0,0.215f), put(0,1,0.215f), mainThread.textures[20], 30f, 30f, 5);
			
			v[0] = put(0.125f, 0.005f, 0.216f);
			v[1] = put(0f, 0.057f, 0.216f);
			v[2] = put(0f, 0.055f, 0.216f);
			v[3] = put(0.117f, 0.005f, 0.216f);
			polygons[45 + i * l] = new polygon3D(new vector[]{v[3],v[2],v[1],v[0]}, put(0,0,0.215f), put(1,0,0.215f), put(0,1,0.215f), mainThread.textures[20], 30f, 30f, 5);
			
			v[0] = put(0f, 0.055f, 0.216f);
			v[1] = put(0f, 0.055f, 0.217f);
			v[2] = put(-0.117f, 0.005f, 0.217f);
			v[3] = put(-0.117f, 0.005f, 0.216f);
			polygons[46 + i * l] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[20], 0.3f, 2f, 5);
			
			v[0] = put(0f, 0.055f, 0.216f);
			v[1] = put(0f, 0.055f, 0.217f);
			v[2] = put(0.117f, 0.005f, 0.217f);
			v[3] = put(0.117f, 0.005f, 0.216f);
			polygons[47 + i * l] = new polygon3D(new vector[]{v[3],v[2],v[1],v[0]}, v[3], v[2], v[0], mainThread.textures[20], 0.3f, 2f, 5);
			
			v[0] = put(0.125f, 0.005f, 0.217);
			v[1] = put(0f, 0.057f, 0.217);
			v[2] = put(0f, 0.055f, 0.217);
			v[3] = put(0.117f, 0.005f, 0.217);
			polygons[48 + i * l] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0,0,0.219), put(1,0,0.219), put(0,1,0.219), mainThread.textures[20], 30f, 30f, 5);
			
			v[0] = put(-0.125f, 0.005f, 0.217);
			v[1] = put(0f, 0.057f, 0.217);
			v[2] = put(0f, 0.055f, 0.217);
			v[3] = put(-0.117f, 0.005f, 0.217);
			polygons[49 + i * l] = new polygon3D(new vector[]{v[3],v[2],v[1],v[0]}, put(0,0,0.219), put(1,0,0.219), put(0,1,0.219), mainThread.textures[20], 30f, 30f, 5);
			
			start = put(0,0, -0.026);
			
			v[0] = put(-0.125f, 0.005f, 0.216f);
			v[1] = put(0f, 0.057f, 0.216f);
			v[2] = put(0f, 0.055f, 0.216f);
			v[3] = put(-0.117f, 0.005f, 0.216f);
			polygons[50 + i * l] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0,0,0.215f), put(1,0,0.215f), put(0,1,0.215f), mainThread.textures[20], 30f, 30f, 5);
			
			v[0] = put(0.125f, 0.005f, 0.216f);
			v[1] = put(0f, 0.057f, 0.216f);
			v[2] = put(0f, 0.055f, 0.216f);
			v[3] = put(0.117f, 0.005f, 0.216f);
			polygons[51 + i * l] = new polygon3D(new vector[]{v[3],v[2],v[1],v[0]}, put(0,0,0.215f), put(1,0,0.215f), put(0,1,0.215f), mainThread.textures[20], 30f, 30f, 5);
			
			v[0] = put(0f, 0.055f, 0.216f);
			v[1] = put(0f, 0.055f, 0.217f);
			v[2] = put(-0.117f, 0.005f, 0.217f);
			v[3] = put(-0.117f, 0.005f, 0.216f);
			polygons[52 + i * l] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[20], 0.3f, 2f, 5);
			
			v[0] = put(0f, 0.055f, 0.216f);
			v[1] = put(0f, 0.055f, 0.217f);
			v[2] = put(0.117f, 0.005f, 0.217f);
			v[3] = put(0.117f, 0.005f, 0.216f);
			polygons[53 + i * l] = new polygon3D(new vector[]{v[3],v[2],v[1],v[0]}, v[3], v[2], v[0], mainThread.textures[20], 0.3f, 2f, 5);
			
			v[0] = put(0.125f, 0.005f, 0.217);
			v[1] = put(0f, 0.057f, 0.217);
			v[2] = put(0f, 0.055f, 0.217);
			v[3] = put(0.117f, 0.005f, 0.217);
			polygons[54 + i * l] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0,0,0.219), put(1,0,0.219), put(0,1,0.219), mainThread.textures[20], 30f, 30f, 5);
			
			v[0] = put(-0.125f, 0.005f, 0.217);
			v[1] = put(0f, 0.057f, 0.217);
			v[2] = put(0f, 0.055f, 0.217);
			v[3] = put(-0.117f, 0.005f, 0.217);
			polygons[55 + i * l] = new polygon3D(new vector[]{v[3],v[2],v[1],v[0]}, put(0,0,0.219), put(1,0,0.219), put(0,1,0.219), mainThread.textures[20], 30f, 30f, 5);	
			
			start = put(0,0, 0.078);
			start = put(0,0, -0.1085);
		}
		
		
		start = origin.myClone();
		v[0] = put(-0.115f, 0.005f, 0.1065);
		v[1] = put(0.115f, 0.005f, 0.1065);
		v[2] = put(0.115f, -0.003f, 0.1065);
		v[3] = put(-0.115f, -0.003f, 0.1065);
		polygons[182] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[20], 4f, 0.2f, 5);
		
		v[0] = put(0.115f, 0.005f, 0.1105);
		v[1] = put(-0.115f, 0.005f, 0.1105);
		v[2] = put(-0.115f, -0.003f, 0.1105);
		v[3] = put(0.115f, -0.003f, 0.1105);
		polygons[183] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[20], 4f, 0.2f, 5);
		
		
		v[0] = put(0.115f, -0.003f, 0.1065);
		v[1] = put(0.115f, -0.003f, 0.1105);
		v[2] = put(-0.115f, -0.003f, 0.1105);
		v[3] = put(-0.115f, -0.003f, 0.1065);
		polygons[184] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 4f, 0.1f, 5);
		
		v[0] = put(-0.115f, 0.005f, 0.1065);
		v[1] = put(-0.115f, 0.005f, 0.1105);
		v[2] = put(0.115f, 0.005f, 0.1105);
		v[3] = put(0.115f, 0.005f, 0.1065);
		polygons[185] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 4f, 0.1f, 5);
		
		
		start = put(0,0, -0.1085);
		
		v[0] = put(-0.115f, 0.005f, 0.1065);
		v[1] = put(0.115f, 0.005f, 0.1065);
		v[2] = put(0.115f, -0.003f, 0.1065);
		v[3] = put(-0.115f, -0.003f, 0.1065);
		polygons[186] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[20], 4f, 0.2f, 5);
		
		v[0] = put(0.115f, 0.005f, 0.1105);
		v[1] = put(-0.115f, 0.005f, 0.1105);
		v[2] = put(-0.115f, -0.003f, 0.1105);
		v[3] = put(0.115f, -0.003f, 0.1105);
		polygons[187] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[20], 4f, 0.2f, 5);
		
		
		v[0] = put(0.115f, -0.003f, 0.1065);
		v[1] = put(0.115f, -0.003f, 0.1105);
		v[2] = put(-0.115f, -0.003f, 0.1105);
		v[3] = put(-0.115f, -0.003f, 0.1065);
		polygons[188] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 4f, 0.1f, 5);
		
		v[0] = put(-0.115f, 0.005f, 0.1065);
		v[1] = put(-0.115f, 0.005f, 0.1105);
		v[2] = put(0.115f, 0.005f, 0.1105);
		v[3] = put(0.115f, 0.005f, 0.1065);
		polygons[189] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 4f, 0.1f, 5);
		
		start = put(0,0, -0.1085);
		
		v[0] = put(-0.115f, 0.005f, 0.1065);
		v[1] = put(0.115f, 0.005f, 0.1065);
		v[2] = put(0.115f, -0.003f, 0.1065);
		v[3] = put(-0.115f, -0.003f, 0.1065);
		polygons[190] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[20], 4f, 0.2f, 5);
		
		v[0] = put(0.115f, 0.005f, 0.1105);
		v[1] = put(-0.115f, 0.005f, 0.1105);
		v[2] = put(-0.115f, -0.003f, 0.1105);
		v[3] = put(0.115f, -0.003f, 0.1105);
		polygons[191] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[20], 4f, 0.2f, 5);
		
		
		v[0] = put(0.115f, -0.003f, 0.1065);
		v[1] = put(0.115f, -0.003f, 0.1105);
		v[2] = put(-0.115f, -0.003f, 0.1105);
		v[3] = put(-0.115f, -0.003f, 0.1065);
		polygons[192] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 4f, 0.1f, 5);
		
		v[0] = put(-0.115f, 0.005f, 0.1065);
		v[1] = put(-0.115f, 0.005f, 0.1105);
		v[2] = put(0.115f, 0.005f, 0.1105);
		v[3] = put(0.115f, 0.005f, 0.1065);
		polygons[193] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 4f, 0.1f, 5);
		
		start.set(origin);
		v[0] = put(-0.002, 0.048, -0.216);
		v[1] = put(0.002, 0.048, -0.216);
		v[2] = put(0.002, 0.048, 0.216);
		v[3] = put(-0.002, 0.048, 0.216);
		polygons[194] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 4f, 0.1f, 5);
		
		v[0] = put(0.002, 0.056, -0.216);
		v[1] = put(0.002, 0.056, 0.216);
		v[2] = put(0.002, 0.048, 0.216);
		v[3] = put(0.002, 0.048, -0.216);
		polygons[195] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 0.1f, 4f, 5);
		
		v[0] = put(-0.002, 0.056, 0.216);
		v[1] = put(-0.002, 0.056, -0.216);
		v[2] = put(-0.002, 0.048, -0.216);
		v[3] = put(-0.002, 0.048, 0.216);
		polygons[196] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 0.1f, 4f, 5);
		
		
		start = put(-0.03, -0.006, 0);
		v[0] = put(-0.0005, 0.048, -0.216);
		v[1] = put(0.0005, 0.048, -0.216);
		v[2] = put(0.0005, 0.048, 0.216);
		v[3] = put(-0.0005, 0.048, 0.216);
		polygons[197] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 4f, 0.1f, 5);
		
		v[0] = put(0.0005, 0.050, -0.216);
		v[1] = put(0.0005, 0.050, 0.216);
		v[2] = put(0.0005, 0.048, 0.216);
		v[3] = put(0.0005, 0.048, -0.216);
		polygons[198] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 0.1f, 4f, 5);
		
		v[0] = put(-0.0005, 0.050, 0.216);
		v[1] = put(-0.0005, 0.050, -0.216);
		v[2] = put(-0.0005, 0.048, -0.216);
		v[3] = put(-0.0005, 0.048, 0.216);
		polygons[199] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 0.1f, 4f, 5);
		
		start = put(-0.025, -0.011, 0);
		v[0] = put(-0.0005, 0.048, -0.216);
		v[1] = put(0.0005, 0.048, -0.216);
		v[2] = put(0.0005, 0.048, 0.216);
		v[3] = put(-0.0005, 0.048, 0.216);
		polygons[200] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 4f, 0.1f, 5);
		
		v[0] = put(0.0005, 0.050, -0.216);
		v[1] = put(0.0005, 0.050, 0.216);
		v[2] = put(0.0005, 0.048, 0.216);
		v[3] = put(0.0005, 0.048, -0.216);
		polygons[201] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 0.1f, 4f, 5);
		
		v[0] = put(-0.0005, 0.050, 0.216);
		v[1] = put(-0.0005, 0.050, -0.216);
		v[2] = put(-0.0005, 0.048, -0.216);
		v[3] = put(-0.0005, 0.048, 0.216);
		polygons[202] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 0.1f, 4f, 5);
		
		start = put(-0.027, -0.011, 0);
		v[0] = put(-0.0005, 0.048, -0.216);
		v[1] = put(0.0005, 0.048, -0.216);
		v[2] = put(0.0005, 0.048, 0.216);
		v[3] = put(-0.0005, 0.048, 0.216);
		polygons[203] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 4f, 0.1f, 5);
		
		v[0] = put(0.0005, 0.050, -0.216);
		v[1] = put(0.0005, 0.050, 0.216);
		v[2] = put(0.0005, 0.048, 0.216);
		v[3] = put(0.0005, 0.048, -0.216);
		polygons[204] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 0.1f, 4f, 5);
		
		v[0] = put(-0.0005, 0.050, 0.216);
		v[1] = put(-0.0005, 0.050, -0.216);
		v[2] = put(-0.0005, 0.048, -0.216);
		v[3] = put(-0.0005, 0.048, 0.216);
		polygons[205] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 0.1f, 4f, 5);
		
		
		start.set(origin);
		start = put(0.03, -0.006, 0);
		v[0] = put(-0.0005, 0.048, -0.216);
		v[1] = put(0.0005, 0.048, -0.216);
		v[2] = put(0.0005, 0.048, 0.216);
		v[3] = put(-0.0005, 0.048, 0.216);
		polygons[206] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 4f, 0.1f, 5);
		
		v[0] = put(0.0005, 0.050, -0.216);
		v[1] = put(0.0005, 0.050, 0.216);
		v[2] = put(0.0005, 0.048, 0.216);
		v[3] = put(0.0005, 0.048, -0.216);
		polygons[207] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 0.1f, 4f, 5);
		
		v[0] = put(-0.0005, 0.050, 0.216);
		v[1] = put(-0.0005, 0.050, -0.216);
		v[2] = put(-0.0005, 0.048, -0.216);
		v[3] = put(-0.0005, 0.048, 0.216);
		polygons[208] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 0.1f, 4f, 5);
		
		start = put(0.025, -0.011, 0);
		v[0] = put(-0.0005, 0.048, -0.216);
		v[1] = put(0.0005, 0.048, -0.216);
		v[2] = put(0.0005, 0.048, 0.216);
		v[3] = put(-0.0005, 0.048, 0.216);
		polygons[209] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 4f, 0.1f, 5);
		
		v[0] = put(0.0005, 0.050, -0.216);
		v[1] = put(0.0005, 0.050, 0.216);
		v[2] = put(0.0005, 0.048, 0.216);
		v[3] = put(0.0005, 0.048, -0.216);
		polygons[210] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 0.1f, 4f, 5);
		
		v[0] = put(-0.0005, 0.050, 0.216);
		v[1] = put(-0.0005, 0.050, -0.216);
		v[2] = put(-0.0005, 0.048, -0.216);
		v[3] = put(-0.0005, 0.048, 0.216);
		polygons[211] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 0.1f, 4f, 5);
		
		start = put(0.027, -0.011, 0);
		v[0] = put(-0.0005, 0.048, -0.216);
		v[1] = put(0.0005, 0.048, -0.216);
		v[2] = put(0.0005, 0.048, 0.216);
		v[3] = put(-0.0005, 0.048, 0.216);
		polygons[212] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 4f, 0.1f, 5);
		
		v[0] = put(0.0005, 0.050, -0.216);
		v[1] = put(0.0005, 0.050, 0.216);
		v[2] = put(0.0005, 0.048, 0.216);
		v[3] = put(0.0005, 0.048, -0.216);
		polygons[213] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 0.1f, 4f, 5);
		
		v[0] = put(-0.0005, 0.050, 0.216);
		v[1] = put(-0.0005, 0.050, -0.216);
		v[2] = put(-0.0005, 0.048, -0.216);
		v[3] = put(-0.0005, 0.048, 0.216);
		polygons[214] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[20], 0.1f, 4f, 5);
		
		start.set(origin);
		v[0] = put(0f, 0.057f, -0.215f);
		v[1] = put(0f, 0.057f, 0.215f);
		v[2] = put(-0.125f, 0.005f, 0.215f);
		v[3] = put(-0.125f, 0.005f, -0.215f);
		
		for(int i = 0; i < polygons.length; i++)
			polygons[i].diffuse_I = 63 - (31 - polygons[i].diffuse_I)*2;
		
		
		polygon3D roofLeft =  new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[21], 6f, 12f, 8);
		roofLeft.diffuse_I = 127;
		sceneGraph.addTransparent(roofLeft);
		
		
		v[0] = put(0f, 0.057f, 0.215f);
		v[1] = put(0f, 0.057f, -0.215f);
		v[2] = put(0.125f, 0.005f, -0.215f);
		v[3] = put(0.125f, 0.005f, 0.215f);
		
		polygon3D roofRight =  new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1], v[2], v[0], mainThread.textures[21], 6f, 12f, 8);
		roofRight.diffuse_I = 127;
		sceneGraph.addTransparent(roofRight);
		
		
	}
	
}
