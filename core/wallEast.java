package core;
public class wallEast extends solidObject{
	
	public wallEast(vector origin){
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
		v = new vector[108];
		
		v[0] = put(-0.004f, -0.016f, -0.34f);
		v[1] = put(-0.004f, -0.016f, -0.125f);
		v[2] = put(-0.004f, -0.043f, -0.125f);
		v[3] = put(-0.004f, -0.043f, -0.34f);
		
		v[4] = put(-0.004f, -0.016f, 0.085f);
		v[5] = put(-0.004f, -0.016f, 0.301f);
		v[6] = put(-0.004f, -0.043f, 0.301f);
		v[7] = put(-0.004f, -0.043f, 0.085f);
		
		v[8] = put(-0.004f, 0.0202f, -0.34f);
		v[9] = put(-0.004f, 0.0202f, -0.125f);
		v[10] = put(-0.004f, -0.016f, -0.125f);
		v[11] = put(-0.004f, -0.016f, -0.34f);
		
		v[12] = put(-0.004f, 0.0202f, 0.085f);
		v[13] = put(-0.004f, 0.0202f, 0.301f);
		v[14] = put(-0.004f, -0.016f, 0.301f);
		v[15] = put(-0.004f, -0.016f, 0.085f);
		
		v[16] = put(-0.004f, -0.016f, -0.03f);
		v[17] = put(-0.004f, -0.016f, -0.01f);
		v[18] = put(-0.004f, -0.043f, -0.01f);
		v[19] = put(-0.004f, -0.043f, -0.03f);
		
		v[20] = put(-0.004f,  0.0202f, -0.03f);
		v[21] = put(-0.004f,  0.0202f, -0.01f);
		v[22] = put(-0.004f, -0.016f, -0.01f);
		v[23] = put(-0.004f, -0.016f, -0.03f);
		
		v[24] = put(-0.004f, 0.0202f, -0.136f);
		v[25] = put(-0.004f, 0.0202f, 0.086f);
		v[26] = put(-0.004f, 0.013f, 0.086f);
		v[27] = put(-0.004f, 0.013f, -0.136f);
		
		v[28] = put(-0.00415f, 0.013f,-0.03f);
		v[29] = put(0.001f, 0.013f,-0.03f);
		v[30] = put(0.001f, -0.016f,-0.03f);
		v[31] = put(-0.00415f, -0.016f,-0.03f);
		
		v[32] = put(-0.00415f, 0.013f,0.085f);
		v[33] = put(0.001f, 0.013f,0.085f);
		v[34] = put(0.001f, -0.016f,0.085f);
		v[35] = put(-0.00415f, -0.016f,0.085f);
		
		v[36] = put(-0.00415f, -0.016f,-0.03f);
		v[37] = put(0.001f, -0.016f,-0.03f);
		v[38] = put(0.001f, -0.043,-0.03f);
		v[39] = put(-0.00415f, -0.043,-0.03f);
		
		v[40] = put(-0.00415f, -0.016f,0.085f);
		v[41] = put(0.001f, -0.016f,0.085f);
		v[42] = put(0.001f, -0.043,0.085f);
		v[43] = put(-0.00415f, -0.043,0.085f);
		
		v[44] = put(-0.00415f, 0.013f,-0.125f);
		v[45] = put(0.001f, 0.013f,-0.125f);
		v[46] = put(0.001f, -0.016f,-0.125f);
		v[47] = put(-0.00415f, -0.016f,-0.125f);
		
		v[48] = put(-0.00415f, 0.013f,-0.01f);
		v[49] = put(0.001f, 0.013f,-0.01f);
		v[50] = put(0.001f, -0.016f,-0.01f);
		v[51] = put(-0.00415f, -0.016f,-0.01f);
		
		v[52] = put(-0.00415f, -0.016f,-0.125f);
		v[53] = put(0.001f, -0.016f,-0.125f);
		v[54] = put(0.001f, -0.043,-0.125f);
		v[55] = put(-0.00415f, -0.043,-0.125f);
		
		v[56] = put(-0.00415f, -0.016f,-0.01f);
		v[57] = put(0.001f, -0.016f,-0.01f);
		v[58] = put(0.001f, -0.043,-0.01f);
		v[59] = put(-0.00415f, -0.043,-0.01f);
		
		v[60] = put(-0.0041, 0.013f,-0.136f);
		v[61] = put(0.001f, 0.013f,-0.136f);
		v[62] = put(0.001f, 0.013f,0.086f);
		v[63] = put(-0.0041, 0.013f,0.086f);
		
		v[64] = put(-0.007, -0.043,-0.34f);
		v[65] = put(0.001f, -0.043,-0.34f);
		v[66] = put(0.001f, -0.043,0.3f);
		v[67] = put(-0.007, -0.043,0.3f);
		
		
		
		polygons = new polygon3D[17];
		polygons[0] = new polygon3D(new vector[]{v[3],v[2],v[1],v[0]}, v[3], v[2], v[0], mainThread.textures[12], 8f, 1, 5 );
		polygons[1] = new polygon3D(new vector[]{v[7],v[6],v[5],v[4]}, v[7], v[6], v[4], mainThread.textures[12], 8f, 1, 5 );
		polygons[2] = new polygon3D(new vector[]{v[11],v[10],v[9],v[8]}, v[11], v[10], v[8], mainThread.textures[13], 7f, 1, 5 );
		polygons[3] = new polygon3D(new vector[]{v[15],v[14],v[13],v[12]}, v[15], v[14], v[12], mainThread.textures[13], 7f, 1, 5 );
		
		polygons[4] = new polygon3D(new vector[]{v[19],v[18],v[17],v[16]}, v[19], v[18], v[16], mainThread.textures[12], 0.6f, 1, 5 );
		polygons[5] = new polygon3D(new vector[]{v[23],v[22],v[21],v[20]}, v[23], v[22], v[20], mainThread.textures[13], 0.6f, 1, 5 );
		polygons[6] = new polygon3D(new vector[]{v[27],v[26],v[25],v[24]}, v[27], v[26], v[24], mainThread.textures[13], 8f, 0.2f, 5 );
		polygons[7] = new polygon3D(new vector[]{v[28],v[29],v[30],v[31]}, v[28], v[29], v[31], mainThread.textures[13], 0.2f, 1f, 5 );
		polygons[8] = new polygon3D(new vector[]{v[32],v[33],v[34],v[35]}, v[32], v[33], v[35], mainThread.textures[13], 0.2f, 1f, 5 );
		polygons[9] = new polygon3D(new vector[]{v[36],v[37],v[38],v[39]}, v[36], v[37], v[39], mainThread.textures[12], 0.2f, 1f, 5 );
		polygons[10] = new polygon3D(new vector[]{v[40],v[41],v[42],v[43]}, v[40], v[41], v[43], mainThread.textures[12], 0.2f, 1f, 5 );
		polygons[11] = new polygon3D(new vector[]{v[47],v[46],v[45],v[44]}, v[47], v[46], v[44], mainThread.textures[13], 0.2f, 1f, 5 );
		polygons[12] = new polygon3D(new vector[]{v[51],v[50],v[49],v[48]}, v[51], v[50], v[48], mainThread.textures[13], 0.2f, 1f, 5 );
		polygons[13] = new polygon3D(new vector[]{v[55],v[54],v[53],v[52]}, v[55], v[54], v[52], mainThread.textures[12], 0.2f, 1f, 5 );
		polygons[14] = new polygon3D(new vector[]{v[59],v[58],v[57],v[56]}, v[59], v[58], v[56], mainThread.textures[12], 0.2f, 1f, 5 );
		polygons[15] = new polygon3D(new vector[]{v[60],v[61],v[62],v[63]}, v[60], v[61], v[63], mainThread.textures[13], 0.2f, 7f, 5 );
		polygons[16] = new polygon3D(new vector[]{v[67],v[66],v[65],v[64]}, new vector(-0.025f, -0.0053749997f, 0.050375f), new vector(-0.016225f, -0.0053749997f, 0.050375f), new vector(-0.025f, -0.0053749997f, -0.010375001f), mainThread.textures[11], 1.5f, 10, 5 );
		
		

		
		
		polygons[0].diffuse_I = 20;
		polygons[1].diffuse_I = 20;
		polygons[2].diffuse_I = 26;
		polygons[3].diffuse_I = 26;
		polygons[4].diffuse_I = 20;
		polygons[5].diffuse_I = 26;
		polygons[6].diffuse_I = 26;
		polygons[7].diffuse_I = 18;
		polygons[8].diffuse_I = 18;
		polygons[9].diffuse_I = 13;
		polygons[10].diffuse_I = 13;
		polygons[11].diffuse_I = 18;
		polygons[12].diffuse_I = 18;
		polygons[13].diffuse_I = 13;
		polygons[14].diffuse_I = 13;
		polygons[16].diffuse_I = 28;
		
		v[68] = put(-0.0055f, -0.04f, -0.125f);
		v[69] = put(-0.0055f, -0.04f, -0.34f);
		v[70] = put(-0.0041f, -0.04f, -0.34f);
		v[71] = put(-0.0041f, -0.04f, -0.125f);
		
		v[72] = put(-0.0055f, -0.04f, 0.3f);
		v[73] = put(-0.0055f, -0.04f, 0.085f);
		v[74] = put(-0.0041f, -0.04f, 0.085f);
		v[75] = put(-0.0041f, -0.04f, 0.3f);
		
		v[76] = put(-0.0055f, -0.04f, 0.3f);
		v[77] = put(-0.0055f, -0.04f, 0.085f);
		v[78] = put(-0.0055f, -0.0435f, 0.085f);
		v[79] = put(-0.0055f, -0.0435f, 0.3f);
		
		v[80] = put(-0.0055f, -0.04f, -0.125);
		v[81] = put(-0.0055f, -0.04f, -0.34f);
		v[82] = put(-0.0055f, -0.0435f, -0.34f);
		v[83] = put(-0.0055f, -0.0435f, -0.125);
		
		v[84] = put(-0.0055f, -0.04f, -0.01);
		v[85] = put(-0.0055f, -0.04f, -0.03);
		v[86] = put(-0.0055f, -0.0435f, -0.03);
		v[87] = put(-0.0055f, -0.0435f, -0.01);
		
		v[88] = put(-0.0055f, -0.04f, -0.01);
		v[89] = put(-0.0041f, -0.04f, -0.01);
		v[90] = put(-0.0041f, -0.04f, -0.03);
		v[91] = put(-0.0055f, -0.04f, -0.03);
		
		v[92] = put(-0.0055f, -0.04f, -0.03);
		v[93] = put(-0.0041f, -0.04f, -0.03);
		v[94] = put(-0.0041f, -0.0435f, -0.03);
		v[95] = put(-0.0055f, -0.0435f, -0.03);
		
		v[96] = put(-0.0055f, -0.04f, 0.085f);
		v[97] = put(-0.0041f, -0.04f, 0.085f);
		v[98] = put(-0.0041f, -0.0435f, 0.085f);
		v[99] = put(-0.0055f, -0.0435f, 0.085f);
		
		v[100] = put(-0.0055f, -0.04f, -0.01);
		v[101] = put(-0.0041f, -0.04f, -0.01);
		v[102] = put(-0.0041f, -0.0435f, -0.01);
		v[103] = put(-0.0055f, -0.0435f, -0.01);
		
		v[104] = put(-0.0055f, -0.04f, -0.125);
		v[105] = put(-0.0041f, -0.04f, -0.125);
		v[106] = put(-0.0041f, -0.0435f, -0.125);
		v[107] = put(-0.0055f, -0.0435f, -0.125);
		
		
		
		polygon3D[] wallLower = new polygon3D[10];
		
		wallLower[0] = new polygon3D(new vector[]{v[71],v[70],v[69],v[68]}, v[71], v[70], v[68], mainThread.textures[17], 80f, 1, 5 );
		wallLower[1] = new polygon3D(new vector[]{v[75],v[74],v[73],v[72]}, v[75], v[74], v[72], mainThread.textures[17], 80f, 1, 5 );
		wallLower[2] = new polygon3D(new vector[]{v[76],v[77],v[78],v[79]}, v[76], v[77], v[79], mainThread.textures[17], 80f, 1, 5 );
		wallLower[3] = new polygon3D(new vector[]{v[80],v[81],v[82],v[83]}, v[80], v[81], v[83], mainThread.textures[17], 80f, 1, 5 );
		wallLower[4] = new polygon3D(new vector[]{v[84],v[85],v[86],v[87]}, v[84], v[85], v[87], mainThread.textures[17], 8f, 1, 5 );
		wallLower[5] = new polygon3D(new vector[]{v[88],v[89],v[90],v[91]}, v[88], v[89], v[91], mainThread.textures[17], 1f, 8, 5 );
		wallLower[6] = new polygon3D(new vector[]{v[92],v[93],v[94],v[95]}, v[92], v[93], v[95], mainThread.textures[17], 1f, 2, 5 );
		wallLower[7] = new polygon3D(new vector[]{v[96],v[97],v[98],v[99]}, v[96], v[97], v[99], mainThread.textures[17], 1f, 2, 5 );
		wallLower[8] = new polygon3D(new vector[]{v[103],v[102],v[101],v[100]}, v[103], v[102], v[100], mainThread.textures[17], 1f, 2, 5 );
		wallLower[9] = new polygon3D(new vector[]{v[107],v[106],v[105],v[104]}, v[107], v[106], v[104], mainThread.textures[17], 1f, 2, 5 );
		
		
		for(int i = 0; i < wallLower.length; i++){
			sceneGraph.addMisc(wallLower[i]);
		}
		wallLower[0].diffuse_I=13;
		wallLower[1].diffuse_I=13;
		wallLower[2].diffuse_I=20;
		wallLower[3].diffuse_I=20;
		wallLower[4].diffuse_I=20;
		wallLower[5].diffuse_I=13;
		wallLower[6].diffuse_I=8;
		wallLower[7].diffuse_I=8;
		wallLower[8].diffuse_I=8;
		wallLower[9].diffuse_I=8;
		
		for(int i = 0; i < polygons.length; i++)
			polygons[i].diffuse_I = 63 - (31 - polygons[i].diffuse_I)*2;
		
		for(int i = 0; i < wallLower.length; i++)
			wallLower[i].diffuse_I = 63 - (31 - wallLower[i].diffuse_I)*2;
		
		
	}

}
