package core;
public class saunaRoom extends solidObject{
	
	
	
	public saunaRoom(vector origin){
		//define the origin point in object space
		start = origin.myClone();
		start.add(0, -0.001f, -0.006f);
		
		centre = start.myClone();
		tempCentre = start.myClone();
			  
		//define axis in object space
		iDirection = new vector(0.25f/2,0,0);
		jDirection = new vector(0,0.25f/2,0);
		kDirection = new vector(0,0,0.25f/2);
		
		//create bounding box
		makeBoundary(0.04f, 0.035f, 0.06f);
		
		start.add(0, 0.001f, 0.006f);
				
		//create polygons 
		makePolygons();
		
		//drawBoundary = true;
	}
	
	public void makePolygons(){
		
		v = new vector[196];
		
		polygons = new polygon3D[373];
		
		//create vertices
		v[0]= put(-0.04, -0.043, 0.01);
		v[1]= put(0.04, -0.043, 0.01);
		v[2]= put(0.04, -0.043, -0.1);
		v[3]= put(-0.04, -0.043, -0.1);
		
		v[4] = put(-0.042, -0.016, 0);
		v[5] = put(-0.042, -0.016, -0.065);
		v[6] = put(-0.042, -0.043, -0.065);
		v[7] = put(-0.042, -0.043, 0);
		
		v[8] = put(-0.042, 0.015, 0);
		v[9] = put(-0.042, 0.015, -0.007);
		v[10] = put(-0.042, -0.0165, -0.007);
		v[11] = put(-0.042, -0.0165, 0);
		
		v[12] = put(-0.042, 0.015, -0.0065);
		v[13] = put(-0.042, 0.015, -0.1);
		v[14] = put(-0.042, 0.008, -0.1);
		v[15] = put(-0.042, 0.008, -0.0065);
		
		v[16] = put(-0.042, 0.0085, -0.0886);
		v[17] = put(-0.042, 0.0085, -0.1);
		v[18] = put(-0.042, -0.043, -0.1);
		v[19] = put(-0.042, -0.043, -0.0886);
		
		v[20] = put(-0.042, 0.0085, -0.0592);
		v[21] = put(-0.042, 0.0085, -0.065);
		v[22] = put(-0.042, -0.0165, -0.065);
		v[23] = put(-0.042, -0.0165, -0.0592);
		
		v[24] = put(-0.04207, 0.008,  -0.007);
		v[25] = put(-0.038, 0.008,  -0.007);
		v[26] = put(-0.038, -0.0165,  -0.007);
		v[27] = put(-0.04207, -0.0165,  -0.007);
		
		v[28] = put(-0.042, -0.01607,  -0.007);
		v[29] = put(-0.038, -0.01607,  -0.007);
		v[30] = put(-0.038, -0.01607,  -0.0592);
		v[31] = put(-0.042, -0.01607,  -0.0592);
		
		v[32] = put(-0.038, 0.008,  -0.007);
		v[33] = put(-0.04207, 0.008,  -0.007);
		v[34] = put(-0.042, 0.00808,  -0.0592);
		v[35] = put(-0.038, 0.00808,  -0.0592);
		
		v[36] = put(-0.038, 0.00808,  -0.0592);
		v[37] = put(-0.042, 0.00808,  -0.0592);
		v[38] = put(-0.042, -0.01607,  -0.0592);
		v[39] = put(-0.038, -0.01607,  -0.0592);
		
		v[40] = put(-0.04207, 0.00808, -0.065);
		v[41] = put(-0.038, 0.00808, -0.065);
		v[42] = put(-0.038, -0.043, -0.065);
		v[43] = put(-0.04207, -0.043, -0.065);
		
		v[44] = put(-0.038, 0.00808, -0.0886);
		v[45] = put(-0.04207, 0.00808, -0.0886);
		v[46] = put(-0.04207, -0.043, -0.0886);
		v[47] = put(-0.038, -0.043, -0.0886);

		v[48] = put(-0.038, 0.00805, -0.065);
		v[49] = put(-0.04207, 0.00805, -0.065);
		v[50] = put(-0.04207, 0.00805, -0.0886);
		v[51] = put(-0.038, 0.00805, -0.0886);
		
		
		
		
		//create polygons
	
		
		polygons[0] = new polygon3D(new vector[]{v[4],v[5],v[6],v[7]}, v[4], v[5], v[7], mainThread.textures[23], 1.75f, 1f, 5 );
		polygons[1] = new polygon3D(new vector[]{v[8],v[9],v[10],v[11]}, v[4], v[5], v[7], mainThread.textures[23], 1.75f, 1f, 5 );
		polygons[2] = new polygon3D(new vector[]{v[12],v[13],v[14],v[15]}, v[4], v[5], v[7], mainThread.textures[23], 1.75f, 1f, 5 );
		polygons[3] = new polygon3D(new vector[]{v[16],v[17],v[18],v[19]}, v[4], v[5], v[7], mainThread.textures[23], 1.75f, 1f, 5 );
		polygons[4] = new polygon3D(new vector[]{v[20],v[21],v[22],v[23]}, v[4], v[5], v[7], mainThread.textures[23], 1.75f, 1f, 5 );

		for(int i = 0; i < 5; i ++)
			polygons[i].diffuse_I = 31;
		
		
		
		polygons[5] = new polygon3D(new vector[]{v[24],v[25],v[26],v[27]}, v[24], v[25], v[27], mainThread.textures[23], 0.075f, 1f, 5 );
		polygons[5].diffuse_I = 14;
		
		polygons[6] = new polygon3D(new vector[]{v[28],v[29],v[30],v[31]}, v[28], v[29], v[31], mainThread.textures[23], 0.075f, 2f, 5 );
		polygons[6].diffuse_I = 20;
		
		polygons[7] = new polygon3D(new vector[]{v[32],v[33],v[34],v[35]}, v[32], v[33], v[35], mainThread.textures[23], 0.075f, 2f, 5 );
		polygons[7].diffuse_I = 5;
		
		polygons[8] = new polygon3D(new vector[]{v[36],v[37],v[38],v[39]}, v[36], v[37], v[39], mainThread.textures[23], 0.075f, 1f, 5 );
		polygons[8].diffuse_I = 14;
		
		polygons[9] = new polygon3D(new vector[]{v[40],v[41],v[42],v[43]}, v[40], v[41], v[43], mainThread.textures[23], 0.075f, 2f, 5 );
		polygons[9].diffuse_I = 14;
		
		polygons[10] = new polygon3D(new vector[]{v[44],v[45],v[46],v[47]}, v[44], v[45], v[47], mainThread.textures[23], 0.075f, 2f, 5 );
		polygons[10].diffuse_I = 14;
		
		polygons[11] = new polygon3D(new vector[]{v[48],v[49],v[50],v[51]}, v[48], v[49], v[51], mainThread.textures[23], 0.075f, 1f, 5 );
		polygons[11].diffuse_I = 5;
		
		v[52] = put(-0.041, 0.008, -0.065);
		v[53] = put(-0.041, 0.008, -0.0886);
		v[54] = put(-0.041, 0.004, -0.0886);
		v[55] = put(-0.041, 0.004, -0.065);
		
		v[56] = put(-0.041, 0.0041, -0.065);
		v[57] = put(-0.041, 0.0041, -0.069);
		v[58] = put(-0.041, -0.0392, -0.069);
		v[59] = put(-0.041, -0.0392, -0.065);
		
		v[60] = put(-0.041, 0.0041, -0.0846);
		v[61] = put(-0.041, 0.0041, -0.0886);
		v[62] = put(-0.041, -0.0392, -0.0886);
		v[63] = put(-0.041, -0.0392, -0.0846);
		
		v[64] = put(-0.041, -0.039, -0.065);
		v[65] = put(-0.041, -0.039, -0.0886);
		v[66] = put(-0.041, -0.043, -0.0886);
		v[67] = put(-0.041, -0.043, -0.065);
		
		v[68] = put(-0.041, -0.008, -0.0847);
		v[69] = put(-0.041, -0.015, -0.0847);
		v[70] = put(-0.041, -0.027, -0.0689);
		v[71] = put(-0.041, -0.020, -0.0689);
		
		
		v[72] = put(-0.039, 0.008, -0.065);
		v[73] = put(-0.039, 0.008, -0.0886);
		v[74] = put(-0.039, 0.004, -0.0886);
		v[75] = put(-0.039, 0.004, -0.065);
		
		v[76] = put(-0.039, 0.0041, -0.065);
		v[77] = put(-0.039, 0.0041, -0.069);
		v[78] = put(-0.039, -0.0392, -0.069);
		v[79] = put(-0.039, -0.0392, -0.065);
		
		v[80] = put(-0.039, 0.0041, -0.0846);
		v[81] = put(-0.039, 0.0041, -0.0886);
		v[82] = put(-0.039, -0.0392, -0.0886);
		v[83] = put(-0.039, -0.0392, -0.0846);
		
		v[84] = put(-0.039, -0.039, -0.065);
		v[85] = put(-0.039, -0.039, -0.0886);
		v[86] = put(-0.039, -0.043, -0.0886);
		v[87] = put(-0.039, -0.043, -0.065);
		
		v[88] = put(-0.039, -0.008, -0.0847);
		v[89] = put(-0.039, -0.015, -0.0847);
		v[90] = put(-0.039, -0.027, -0.0689);
		v[91] = put(-0.039, -0.020, -0.0689);
		
		
		polygons[12] = new polygon3D(new vector[]{v[52],v[53],v[54],v[55]}, v[52], v[53], v[55], mainThread.textures[24], 1f, 0.15f, 5 );
		polygons[13] = new polygon3D(new vector[]{v[56],v[57],v[58],v[59]}, v[52], v[53], v[55], mainThread.textures[24], 1f, 0.15f, 5 );
		polygons[14] = new polygon3D(new vector[]{v[60],v[61],v[62],v[63]}, v[52], v[53], v[55], mainThread.textures[24], 1f, 0.15f, 5 );
		polygons[15] = new polygon3D(new vector[]{v[64],v[65],v[66],v[67]}, v[52], v[53], v[55], mainThread.textures[24], 1f, 0.15f, 5 );
		polygons[16] = new polygon3D(new vector[]{v[68],v[69],v[70],v[71]}, v[52], v[53], v[55], mainThread.textures[24], 1f, 0.15f, 5 );
		
		for(int i = 12; i < 17; i ++)
			polygons[i].diffuse_I = 34;
		
		polygons[17] = new polygon3D(new vector[]{v[75],v[74],v[73],v[72]}, v[75], v[74], v[72], mainThread.textures[24], 1f, 0.15f, 5 );
		polygons[18] = new polygon3D(new vector[]{v[79],v[78],v[77],v[76]}, v[75], v[74], v[72], mainThread.textures[24], 1f, 0.15f, 5 );
		polygons[19] = new polygon3D(new vector[]{v[83],v[82],v[81],v[80]}, v[75], v[74], v[72], mainThread.textures[24], 1f, 0.15f, 5 );
		polygons[20] = new polygon3D(new vector[]{v[87],v[86],v[85],v[84]}, v[75], v[74], v[72], mainThread.textures[24], 1f, 0.15f, 5 );
		polygons[21] = new polygon3D(new vector[]{v[91],v[90],v[89],v[88]}, v[75], v[74], v[72], mainThread.textures[24], 1f, 0.15f, 5 );
		
		for(int i = 17; i < 22; i ++)
			polygons[i].diffuse_I = 0;
		
		
		v[92] = put(-0.041, 0.00405, -0.0847);
		v[93] = put(-0.039, 0.00405, -0.0847);
		v[94] = put(-0.039, 0.00405, -0.0689);
		v[95] = put(-0.041, 0.00405, -0.0689);
		
		v[96] = put(-0.041, 0.00405, -0.0689);
		v[97] = put(-0.039, 0.00405, -0.0689);
		v[98] = put(-0.039, -0.020, -0.0689);
		v[99] = put(-0.041, -0.020, -0.0689);
		
		v[100] = put(-0.039, 0.00405, -0.0847);
		v[101] = put(-0.041, 0.00405, -0.0847);
		v[102] = put(-0.041, -0.008, -0.0847);
		v[103] = put(-0.039, -0.008, -0.0847);
		
		v[104] = put(-0.039, -0.008, -0.0847);
		v[105] = put(-0.041, -0.008, -0.0847);
		v[106] = put(-0.041, -0.020, -0.0689);
		v[107] = put(-0.039, -0.020, -0.0689);
		
		v[108] = put(-0.039, -0.015, -0.0847);
		v[109] = put(-0.041, -0.015, -0.0847);
		v[110] = put(-0.041, -0.027, -0.0689);
		v[111] = put(-0.039, -0.027, -0.0689);
		
		v[112] = put(-0.039, -0.015, -0.0847);
		v[113] = put(-0.041, -0.015, -0.0847);
		v[114] = put(-0.041, -0.03905, -0.0847);
		v[115] = put(-0.039, -0.03905, -0.0847);
		
		v[116] = put(-0.041, -0.027, -0.0689);
		v[117] = put(-0.039, -0.027, -0.0689);
		v[118] = put(-0.039, -0.03905, -0.0689);
		v[119] = put(-0.041, -0.03905, -0.0689);
		
		v[120] = put(-0.041, -0.03905, -0.0689);
		v[121] = put(-0.039, -0.03905, -0.0689);
		v[122] = put(-0.039, -0.03905,-0.0847);
		v[123] = put(-0.041, -0.03905,-0.0847);
		
		
		polygons[22] = new polygon3D(new vector[]{v[92],v[93],v[94],v[95]}, v[92], v[93], v[95], mainThread.textures[24], 0.1f, 1f, 5 );
		polygons[22].diffuse_I = 10;
		
		polygons[23] = new polygon3D(new vector[]{v[96],v[97],v[98],v[99]}, v[96], v[97], v[99], mainThread.textures[24], 0.1f, 1f, 5 );
		polygons[23].diffuse_I = 18;
		
		polygons[24] = new polygon3D(new vector[]{v[100],v[101],v[102],v[103]}, v[100], v[101], v[103], mainThread.textures[24], 0.1f, 1f, 5 );
		polygons[24].diffuse_I = 18;
		
		polygons[25] = new polygon3D(new vector[]{v[104],v[105],v[106],v[107]}, v[104], v[105], v[107], mainThread.textures[24], 0.1f, 1f, 5 );
		polygons[25].diffuse_I = 24;
		
		polygons[26] = new polygon3D(new vector[]{v[111],v[110],v[109],v[108]}, v[111], v[110], v[108], mainThread.textures[24], 0.1f, 1f, 5 );
		polygons[26].diffuse_I = 8;
		
		polygons[27] = new polygon3D(new vector[]{v[112],v[113],v[114],v[115]}, v[112], v[113], v[115], mainThread.textures[24], 0.1f, 1f, 5 );
		polygons[27].diffuse_I = 18;
		
		polygons[28] = new polygon3D(new vector[]{v[116],v[117],v[118],v[119]}, v[116], v[117], v[119], mainThread.textures[24], 0.1f, 1f, 5 );
		polygons[28].diffuse_I = 18;
		
		polygons[29] = new polygon3D(new vector[]{v[120],v[121],v[122],v[123]}, v[120], v[121], v[123], mainThread.textures[24], 0.1f, 1f, 5 );
		polygons[29].diffuse_I = 24;
		
		v[124] = put(-0.042, -0.012, -0.0658);
		v[125] = put(-0.042, -0.012, -0.0682);
		v[126] = put(-0.042, -0.02, -0.0682);
		v[127] = put(-0.042, -0.02, -0.0658);
		
		v[128] = put(-0.042, -0.012, -0.0682);
		v[129] = put(-0.042, -0.012, -0.0658);
		v[130] = put(-0.041, -0.012, -0.0664);
		v[131] = put(-0.041, -0.012, -0.0676);
		
		v[132] = put(-0.042, -0.02, -0.0682);
		v[133] = put(-0.042, -0.012, -0.0682);
		v[134] = put(-0.041, -0.012, -0.0676);
		v[135] = put(-0.041, -0.02, -0.0676);
		
		v[136] = put(-0.042, -0.012, -0.0658);
		v[137] = put(-0.042, -0.02, -0.0658);
		v[138] = put(-0.041, -0.02, -0.0664);
		v[139] = put(-0.041, -0.012, -0.0664);
		
		v[140] = put(-0.042, -0.02, -0.0658);
		v[141] = put(-0.042, -0.02, -0.0682);
		v[142] = put(-0.041, -0.02, -0.0676);
		v[143] = put(-0.041, -0.02, -0.0664);
		
		
		v[144] = put(-0.04,  0.00405, -0.0689);
		v[145] = put(-0.04,  0.00405, -0.0847);
		v[146] = put(-0.04,  -0.03905, -0.0847);
		v[147] = put(-0.04,  -0.03905, -0.0689);
		
		v[148] = put(-0.04,  0.00405, -0.0689);
		v[149] = put(-0.04,  0.00405, -0.0847);
		v[150] = put(-0.04,  -0.03905, -0.0847);
		v[151] = put(-0.04,  -0.03905, -0.0689);
		
		v[152] = put(-0.0405, 0.008, -0.007);
		v[153] = put(-0.0405, 0.008, -0.0592);
		v[154] = put(-0.0405,-0.0165, -0.0592);
		v[155] = put(-0.0405,-0.0165, -0.007);
		
		v[156] = put(-0.0395, 0.008, -0.0592);
		v[157] = put(-0.0395, 0.008, -0.007);
		v[158] = put(-0.0395,-0.0165, -0.007);
		v[159] = put(-0.0395,-0.0165, -0.0592);
		
		
		polygons[30] = new polygon3D(new vector[]{v[124],v[125],v[126],v[127]}, v[124], v[125], v[127], mainThread.textures[22], 0.5f, 1f, 5 );
		polygons[30].diffuse_I = 32;
		
		polygons[31] = new polygon3D(new vector[]{v[128],v[129],v[130],v[131]}, v[128], v[129], v[131], mainThread.textures[22], 0.5f, 1f, 5 );
		polygons[31].diffuse_I = 20;
		
		polygons[32] = new polygon3D(new vector[]{v[132],v[133],v[134],v[135]}, v[132], v[133], v[135], mainThread.textures[22], 0.5f, 1f, 5 );
		polygons[32].diffuse_I = 15;
		
		polygons[33] = new polygon3D(new vector[]{v[136],v[137],v[138],v[139]}, v[136], v[137], v[139], mainThread.textures[22], 0.5f, 1f, 5 );
		polygons[33].diffuse_I = 15;
		
		polygons[34] = new polygon3D(new vector[]{v[140],v[141],v[142],v[143]}, v[140], v[141], v[143], mainThread.textures[22], 0.5f, 1f, 5 );
		polygons[34].diffuse_I = 8;
		
		
		polygon3D[] glass = new polygon3D[4];
		glass[0] = new polygon3D(new vector[]{v[144],v[145],v[146],v[147]}, v[144], v[145], v[147], mainThread.textures[34],2f, 6f, 12 );
		glass[0] .diffuse_I = 41;
		
		glass[1] = new polygon3D(new vector[]{v[151],v[150],v[149],v[148]}, v[149], v[148], v[150], mainThread.textures[34], 2f,6f, 12 );
		glass[1] .diffuse_I = 41;
		
		glass[2] = new polygon3D(new vector[]{v[152],v[153],v[154],v[155]}, v[152], v[153], v[155], mainThread.textures[26], 7f,3f, 12 );
		glass[2] .diffuse_I = 21;
		
		glass[3] = new polygon3D(new vector[]{v[156],v[157],v[158],v[159]}, v[156], v[157], v[159], mainThread.textures[26], 7f,3f, 12 );
		glass[3] .diffuse_I = 21;
		
		sceneGraph.addTransparent(glass[0] );
		sceneGraph.addTransparent(glass[1] );
		sceneGraph.addTransparent(glass[2] );
		sceneGraph.addTransparent(glass[3] );
		
		float y1 = 0.0005f;
		float y2 = -0.0005f;
		
		
		v[160] = put(-0.0415, y1, -0.007);
		v[161] = put(-0.0415, y1, -0.0592);
		v[162] = put(-0.0415, y2, -0.0592);
		v[163] = put(-0.0415, y2, -0.007);
		
		v[164] = put(-0.0385, y1, -0.0592);
		v[165] = put(-0.0385, y1, -0.007);
		v[166] = put(-0.0385, y2, -0.007);
		v[167] = put(-0.0385, y2, -0.0592);
		
		v[168] = put(-0.0415, y1, -0.007);
		v[169] = put(-0.0385, y1, -0.007);
		v[170] = put(-0.0385, y1, -0.0592);
		v[171] = put(-0.0415, y1, -0.0592);
		
		v[172] = put(-0.0385, y2, -0.007);
		v[173] = put(-0.0415, y2, -0.007);
		v[174] = put(-0.0415, y2, -0.0592);
		v[175] = put(-0.0385, y2, -0.0592);
		
		
		
		polygons[35] = new polygon3D(new vector[]{v[160],v[161],v[162],v[163]}, v[163], v[160], v[162], mainThread.textures[23], 0.03f, 2f, 5 );
		polygons[35].diffuse_I = 31;
		
		polygons[36] = new polygon3D(new vector[]{v[164],v[165],v[166],v[167]}, v[167], v[164], v[166], mainThread.textures[23], 0.03f, 2f, 5 );
		polygons[36].diffuse_I = 5;
		
		polygons[37] = new polygon3D(new vector[]{v[168],v[169],v[170],v[171]}, v[168], v[169], v[171], mainThread.textures[23], 0.06f, 2f, 5 );
		polygons[37].diffuse_I = 24;
		
		polygons[38] = new polygon3D(new vector[]{v[172],v[173],v[174],v[175]}, v[172], v[173], v[175], mainThread.textures[23], 0.06f, 2f, 5 );
		polygons[38].diffuse_I = 5;
		
		float y3 = -0.008f;
		float y4 = -0.009f;
		
		v[176] = put(-0.0415, y3, -0.007);
		v[177] = put(-0.0415, y3, -0.0592);
		v[178] = put(-0.0415, y4, -0.0592);
		v[179] = put(-0.0415, y4, -0.007);
		
		v[180] = put(-0.0385, y3, -0.0592);
		v[181] = put(-0.0385, y3, -0.007);
		v[182] = put(-0.0385, y4, -0.007);
		v[183] = put(-0.0385, y4, -0.0592);
		
		v[184] = put(-0.0415, y3, -0.007);
		v[185] = put(-0.0385, y3, -0.007);
		v[186] = put(-0.0385, y3, -0.0592);
		v[187] = put(-0.0415, y3, -0.0592);
		
		v[188] = put(-0.0385, y4, -0.007);
		v[189] = put(-0.0415, y4, -0.007);
		v[190] = put(-0.0415, y4, -0.0592);
		v[191] = put(-0.0385, y4, -0.0592);
		
		
		
		polygons[39] = new polygon3D(new vector[]{v[176],v[177],v[178],v[179]}, v[179], v[176], v[178], mainThread.textures[23], 0.06f, 2f, 5 );
		polygons[39].diffuse_I = 31;
		
		polygons[40] = new polygon3D(new vector[]{v[180],v[181],v[182],v[183]}, v[183], v[180], v[182], mainThread.textures[23], 0.06f, 2f, 5 );
		polygons[40].diffuse_I = 5;
		
		polygons[41] = new polygon3D(new vector[]{v[184],v[185],v[186],v[187]}, v[184], v[185], v[187], mainThread.textures[23], 0.06f, 2f, 5 );
		polygons[41].diffuse_I = 24;
		
		polygons[42] = new polygon3D(new vector[]{v[188],v[189],v[190],v[191]}, v[188], v[189], v[191], mainThread.textures[23], 0.06f, 2f, 5 );
		polygons[42].diffuse_I = 5;
		
		
		float z = -0.0140f;
		for(int i = 0; i < 6; i ++, z-=0.0075f){
			v[176] = put(-0.04152, 0.008, z);
			v[177] = put(-0.04152, 0.008, z-0.001);
			v[178] = put(-0.04152, -0.016, z-0.001);
			v[179] = put(-0.04152, -0.016, z);
			
			v[180] = put(-0.03852, 0.008, z-0.001);
			v[181] = put(-0.03852, 0.008, z);
			v[182] = put(-0.03852, -0.016, z);
			v[183] = put(-0.03852, -0.016, z-0.001);
			
			v[184] = put(-0.04152, 0.008, z-0.001);
			v[185] = put(-0.03852, 0.008, z-0.001);
			v[186] = put(-0.03852, -0.016, z-0.001);
			v[187] = put(-0.04152, -0.016, z-0.001);
			
			v[188] = put(-0.03852, 0.008, z);
			v[189] = put(-0.04152, 0.008, z);
			v[190] = put(-0.04152, -0.016, z);
			v[191] = put(-0.03852, -0.016, z);
			
			
			
			
			polygons[43+i*4] = new polygon3D(new vector[]{v[176],v[177],v[178],v[179]}, v[176], v[177], v[179], mainThread.textures[23], 0.06f, 2f, 5 );
			polygons[43+i*4].diffuse_I = 31;
			
			polygons[44 + i*4] = new polygon3D(new vector[]{v[180],v[181],v[182],v[183]}, v[180], v[181], v[183], mainThread.textures[23], 0.06f, 2f, 5 );
			polygons[44 + i*4].diffuse_I = 5;
			
			polygons[45 + i * 4] = new polygon3D(new vector[]{v[184],v[185],v[186],v[187]}, v[184], v[185], v[187], mainThread.textures[23], 0.03f, 2f, 5 );
			polygons[45 + i * 4].diffuse_I = 20;
			
			polygons[46 + i * 4] = new polygon3D(new vector[]{v[188],v[189],v[190],v[191]}, v[188], v[189], v[191], mainThread.textures[23], 0.03f, 2f, 5 );
			polygons[46 + i * 4].diffuse_I = 20;
			
		}
		
		float x1 = -0.038f;
		float x2 = -0.039f;
		
		v[124] = put(x1, -0.012, -0.0682);
		v[125] = put(x1, -0.012, -0.0658);
		v[126] = put(x1, -0.02, -0.0658);
		v[127] = put(x1, -0.02, -0.0682);
		
		v[128] = put(x1, -0.012, -0.0658);
		v[129] = put(x1, -0.012, -0.0682);
		v[130] = put(x2, -0.012, -0.0676);
		v[131] = put(x2, -0.012, -0.0664);
		
		v[132] = put(x1, -0.012, -0.0682);
		v[133] = put(x1, -0.02, -0.0682);
		v[134] = put(x2, -0.02, -0.0676);
		v[135] = put(x2, -0.012, -0.0676);
		
		v[136] = put(x1, -0.02, -0.0658);
		v[137] = put(x1, -0.012, -0.0658);
		v[138] = put(x2, -0.012, -0.0664);
		v[139] = put(x2, -0.02, -0.0664);
		
		v[140] = put(x1, -0.02, -0.0682);
		v[141] = put(x1, -0.02, -0.0658);
		v[142] = put(x2, -0.02, -0.0664);
		v[143] = put(x2, -0.02, -0.0676);
		
		
		polygons[67] = new polygon3D(new vector[]{v[124],v[125],v[126],v[127]}, v[124], v[125], v[127], mainThread.textures[22], 0.5f, 1f, 5 );
		polygons[67].diffuse_I = 6;
		
		polygons[68] = new polygon3D(new vector[]{v[128],v[129],v[130],v[131]}, v[128], v[129], v[131], mainThread.textures[22], 0.5f, 1f, 5 );
		polygons[68].diffuse_I = 16;
		
		polygons[69] = new polygon3D(new vector[]{v[132],v[133],v[134],v[135]}, v[132], v[133], v[135], mainThread.textures[22], 0.5f, 1f, 5 );
		polygons[69].diffuse_I = 20;
		
		polygons[70] = new polygon3D(new vector[]{v[136],v[137],v[138],v[139]}, v[136], v[137], v[139], mainThread.textures[22], 0.5f, 1f, 5 );
		polygons[70].diffuse_I = 10;
		
		polygons[71] = new polygon3D(new vector[]{v[140],v[141],v[142],v[143]}, v[140], v[141], v[143], mainThread.textures[22], 0.5f, 1f, 5 );
		polygons[71].diffuse_I = 4;
		
		v[4] = put(-0.038, -0.016, -0.06519);
		v[5] = put(-0.038, -0.016, 0.01);
		v[6] = put(-0.038, -0.043, 0.01);
		v[7] = put(-0.038, -0.043, -0.06519);
		
		v[8] = put(-0.038, 0.015, -0.007);
		v[9] = put(-0.038, 0.015, 0.01);
		v[10] = put(-0.038, -0.0165, 0.01);
		v[11] = put(-0.038, -0.0165, -0.007);
		
		v[12] = put(-0.038, 0.015, -0.1);
		v[13] = put(-0.038, 0.015, -0.0065);
		v[14] = put(-0.038, 0.008, -0.0065);
		v[15] = put(-0.038, 0.008, -0.1);
		
		v[16] = put(-0.038, 0.0085, -0.1);
		v[17] = put(-0.038, 0.0085, -0.0884);
		v[18] = put(-0.038, -0.043, -0.0884);
		v[19] = put(-0.038, -0.043, -0.1);
		
		v[20] = put(-0.038, 0.0085, -0.06519);
		v[21] = put(-0.038, 0.0085, -0.0592);
		v[22] = put(-0.038, -0.0165, -0.0592);
		v[23] = put(-0.038, -0.0165, -0.06519);
		
		
		polygons[72] = new polygon3D(new vector[]{v[4],v[5],v[6],v[7]}, v[4], v[5], v[7], mainThread.textures[23], 1.8f, 1f, 5 );
		polygons[73] = new polygon3D(new vector[]{v[8],v[9],v[10],v[11]}, v[4], v[5], v[7], mainThread.textures[23], 1.8f, 1f, 5 );
		polygons[74] = new polygon3D(new vector[]{v[12],v[13],v[14],v[15]}, v[4], v[5], v[7], mainThread.textures[23], 1.8f, 1f, 5 );
		polygons[75] = new polygon3D(new vector[]{v[16],v[17],v[18],v[19]}, v[4], v[5], v[7], mainThread.textures[23], 1.8f, 1f, 5 );
		polygons[76] = new polygon3D(new vector[]{v[20],v[21],v[22],v[23]}, v[4], v[5], v[7], mainThread.textures[23], 1.8f, 1f, 5 );

		for(int i = 72; i < 77; i ++)
			polygons[i].diffuse_I = 0;
		
		v[20] = put(-0.0388, 0.015, 0.01);
		v[21] = put(0.04, 0.015, 0.01);
		v[22] = put(0.04, -0.029, 0.01);
		v[23] = put(-0.0388, -0.029, 0.01);
		polygons[polygons.length -3] = new polygon3D(new vector[]{v[20],v[21],v[22],v[23]}, v[20], v[21], v[23], mainThread.textures[23], 2.2f, 1.5f, 9 );
		polygons[polygons.length -3].lightMapTextureIndex = 0;
		polygons[polygons.length -3].max_dx = 4096;
		
		v[30] = put(-0.0388, -0.029, 0.01);
		v[31] = put(0.04, -0.029, 0.01);
		v[32] = put(0.04, -0.0432, 0.01);
		v[33] = put(-0.0388, -0.0432, 0.01);
		polygons[polygons.length -8] = new polygon3D(new vector[]{v[30],v[31],v[32],v[33]}, v[20], v[21], v[23], mainThread.textures[23], 2.2f, 1.5f, 5 );
		polygons[polygons.length -8].diffuse_I = 0;
		
		v[20] = put(0.035, 0.0151, -0.1);
		v[21] = put(-0.0388, 0.0151, -0.1);
		v[22] = put(-0.0388, -0.0432, -0.1);
		v[23] = put(0.035, -0.0432, -0.1);
		
		polygons[polygons.length -2] = new polygon3D(new vector[]{v[20],v[21],v[22],v[23]},  put(0.031, 0.015, -0.1), v[21], put(0.031, -0.0432, -0.1), mainThread.textures[23], 1.95f, 1.5f, 9 );
		polygons[polygons.length -2].lightMapTextureIndex = 0;
		polygons[polygons.length -2].max_dx = 4096;
		
		v[4] = put(0.0401, 0.0151, -0.1);
		v[5] = put(0.0349, 0.0151, -0.1);
		v[6] = put(0.0349, -0.0432, -0.1);
		v[7] = put(0.0401, -0.0432, -0.1);
		polygons[polygons.length -6] = new polygon3D(new vector[]{v[4],v[5],v[6],v[7]},  put(0.031, 0.015, -0.1), v[21], put(0.031, -0.0432, -0.1), mainThread.textures[23], 1.95f, 1.5f, 5 );
		polygons[polygons.length -6].diffuse_I = 0;
		
		
		v[20] = put(0.04, 0.015, 0.01);
		v[21] = put(-0.0388, 0.015, 0.01);
		v[22] = put(-0.0388, 0.015, -0.1);
		v[23] = put(0.04, 0.015, -0.1);
		
		polygons[polygons.length -4] = new polygon3D(new vector[]{v[20],v[21],v[22],v[23]}, v[20], v[21], v[23], mainThread.textures[23], 2.2f, 3f, 6 );
		polygons[polygons.length -4].diffuse_I = 4;
		
		v[20] = put(0.04, 0.015, 0.01);
		v[21] = put(0.04, 0.015, -0.1);
		v[22] = put(0.04, -0.029, -0.1);
		v[23] = put(0.04, -0.029, 0.01);
		
		polygons[polygons.length -5] = new polygon3D(new vector[]{v[20],v[21],v[22],v[23]}, put(0.04, 0.015, 0.004), v[21], put(0.04, -0.0432, 0.004), mainThread.textures[23], 3.2f, 1.5f, 9 );
		polygons[polygons.length -5].lightMapTextureIndex = 0;
		polygons[polygons.length -5].max_dx = 4096;
		
		v[8] = put(0.04, -0.029, 0.01);
		v[9] = put(0.04, -0.029, -0.1);
		v[10] = put(0.04, -0.0432, -0.1);
		v[11] = put(0.04, -0.0432, 0.01);
		polygons[polygons.length -7] = new polygon3D(new vector[]{v[8],v[9],v[10],v[11]}, put(0.04, 0.015, 0.004), v[21], put(0.04, -0.0432, 0.004), mainThread.textures[23], 3.2f, 1.5f, 5 );
		polygons[polygons.length -7].diffuse_I = 0;
		
		
		
		polygons[polygons.length -1] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[22], 3f, 2.5f, 6 );
		polygons[polygons.length -1].diffuse_I = 10;
		
		double theta = Math.PI/12;
		
		vector[] v1 = new vector[24];
		vector[] v2 = new vector[24];
		
		vector temp = start.myClone();
		start = put(-0.0215, 0, 0.007);
		
		for(int i = 0; i < 24; i++){
			v1[i] = put(0.0008*Math.cos(i*theta),0.015,  0.0008*Math.sin(i*theta));
		}
		
		for(int i = 0; i < 24; i++){
			v2[i] = put(0.0008*Math.cos(i*theta), 0.014, 0.0008*Math.sin(i*theta));
		}
		
		for(int i = 0; i < 24; i ++){
			polygons[i+ 77] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, v1[0], v1[0], v1[0], null,1,1,7);
			polygons[i+77].color = createColor(5,5,5);
		}
		
	
		polygons[101] = new polygon3D(v2, put(-0.00192, 0.014, -0.0018), put(0.00008, 0.014, -0.0018), put(-0.00192, 0.014, 0.0002), mainThread.textures[28], 0.55f,0.55f, 6);
		polygons[101].diffuse_I = 50;
		
	
		start = put(0.0357, 0, 0);
		v1 = new vector[24];
		v2 = new vector[24];
		for(int i = 0; i < 24; i++){
			v1[i] = put(0.0008*Math.cos(i*theta),0.015,  0.0008*Math.sin(i*theta));
		}
		for(int i = 0; i < 24; i++){
			v2[i] = put(0.0008*Math.cos(i*theta), 0.014, 0.0008*Math.sin(i*theta));
		}
		for(int i = 0; i < 24; i ++){
			polygons[i+ 102] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, v1[0], v1[0], v1[0], null,1,1,7);
			polygons[i+ 102].color = createColor(5,5,5);
		}
		polygons[126] = new polygon3D(v2, put(-0.00192, 0.014, -0.0018), put(0.00008, 0.014, -0.0018), put(-0.00192, 0.014, 0.0002), mainThread.textures[28], 0.55f,0.55f, 6);
		polygons[126].diffuse_I = 50;
		
		
		start = put(0.0227, 0, -0.019);
		v1 = new vector[24];
		v2 = new vector[24];
		for(int i = 0; i < 24; i++){
			v1[i] = put(0.0008*Math.cos(i*theta),0.015,  0.0008*Math.sin(i*theta));
		}
		for(int i = 0; i < 24; i++){
			v2[i] = put(0.0008*Math.cos(i*theta), 0.014, 0.0008*Math.sin(i*theta));
		}
		for(int i = 0; i < 24; i ++){
			polygons[i+ 127] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, v1[0], v1[0], v1[0], null,1,1,7);
			polygons[i+ 127].color = createColor(5,5,5);
		}
		polygons[151] = new polygon3D(v2, put(-0.00192, 0.014, -0.0018), put(0.00008, 0.014, -0.0018), put(-0.00192, 0.014, 0.0002), mainThread.textures[28], 0.55f,0.55f, 6);
		polygons[151].diffuse_I = 50;
		
		start = put(0, 0, -0.032);
		v1 = new vector[24];
		v2 = new vector[24];
		for(int i = 0; i < 24; i++){
			v1[i] = put(0.0008*Math.cos(i*theta),0.015,  0.0008*Math.sin(i*theta));
		}
		for(int i = 0; i < 24; i++){
			v2[i] = put(0.0008*Math.cos(i*theta), 0.014, 0.0008*Math.sin(i*theta));
		}
		for(int i = 0; i < 24; i ++){
			polygons[i+ 152] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, v1[0], v1[0], v1[0], null,1,1,7);
			polygons[i+ 152].color = createColor(5,5,5);
		}
		polygons[176] = new polygon3D(v2, put(-0.00192, 0.014, -0.0018), put(0.00008, 0.014, -0.0018), put(-0.00192, 0.014, 0.0002), mainThread.textures[28], 0.55f,0.55f, 6);
		polygons[176].diffuse_I = 50;
		
		start = put(0, 0, -0.033);
		v1 = new vector[24];
		v2 = new vector[24];
		for(int i = 0; i < 24; i++){
			v1[i] = put(0.0008*Math.cos(i*theta),0.015,  0.0008*Math.sin(i*theta));
		}
		for(int i = 0; i < 24; i++){
			v2[i] = put(0.0008*Math.cos(i*theta), 0.014, 0.0008*Math.sin(i*theta));
		}
		for(int i = 0; i < 24; i ++){
			polygons[i+ 177] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, v1[0], v1[0], v1[0], null,1,1,7);
			polygons[i+ 177].color = createColor(5,5,5);
		}
		polygons[201] = new polygon3D(v2, put(-0.00192, 0.014, -0.0018), put(0.00008, 0.014, -0.0018), put(-0.00192, 0.014, 0.0002), mainThread.textures[28], 0.55f,0.55f, 6);
		polygons[201].diffuse_I = 50;
		
		start = put(-0.023, 0, -0.0198);
		v1 = new vector[24];
		v2 = new vector[24];
		for(int i = 0; i < 24; i++){
			v1[i] = put(0.0008*Math.cos(i*theta),0.015,  0.0008*Math.sin(i*theta));
		}
		for(int i = 0; i < 24; i++){
			v2[i] = put(0.0008*Math.cos(i*theta), 0.014, 0.0008*Math.sin(i*theta));
		}
		for(int i = 0; i < 24; i ++){
			polygons[i+ 202] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, v1[0], v1[0], v1[0], null,1,1,7);
			polygons[i+ 202].color = createColor(5,5,5);
		}
		polygons[226] = new polygon3D(v2, put(-0.00192, 0.014, -0.0018), put(0.00008, 0.014, -0.0018), put(-0.00192, 0.014, 0.0002), mainThread.textures[28], 0.55f,0.55f, 6);
		polygons[226].diffuse_I = 50;
	
		start = put(-0.036, 0, 0);
		v1 = new vector[24];
		v2 = new vector[24];
		for(int i = 0; i < 24; i++){
			v1[i] = put(0.0008*Math.cos(i*theta),0.015,  0.0008*Math.sin(i*theta));
		}
		for(int i = 0; i < 24; i++){
			v2[i] = put(0.0008*Math.cos(i*theta), 0.014, 0.0008*Math.sin(i*theta));
		}
		for(int i = 0; i < 24; i ++){
			polygons[i+ 227] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, v1[0], v1[0], v1[0], null,1,1,7);
			polygons[i+ 227].color = createColor(5,5,5);
		}
		polygons[251] = new polygon3D(v2, put(-0.00192, 0.014, -0.0018), put(0.00008, 0.014, -0.0018), put(-0.00192, 0.014, 0.0002), mainThread.textures[28], 0.55f,0.55f, 6);
		polygons[251].diffuse_I = 50;
	
		
		start = temp.myClone();
		
		v[0] = put(0.04, -0.028, 0.01);
		v[1] = put(0.04, -0.028,-0.095);
		v[2] = put(0.036, -0.028, -0.095);
		v[3] = put(0.036, -0.028, 0.006);
		polygons[252] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0.04, -0.028, 0.004), put(0.04, -0.028, -0.1), put(0.02, -0.028, 0.004), mainThread.textures[29], 3.2f, 0.8f, 9);
		polygons[252].lightMapTextureIndex = 1;
		
		v[0] = put(0.04, -0.028, -0.095);
		v[1] = put(0.04, -0.028, -0.1);
		v[2] = put(0.036, -0.028, -0.1);
		v[3] = put(0.036, -0.028, -0.095);
		polygons[253] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0.04, -0.028, 0.004), put(0.04, -0.028, -0.1), put(0.02, -0.028, 0.004), mainThread.textures[29], 3.2f, 0.8f, 5);
		polygons[253].diffuse_I = 14;
		
		
		v[0] = put(0.0354, -0.028, 0.0054);
		v[1] = put(0.0354, -0.028, -0.1);
		v[2] = put(0.0314, -0.028, -0.1);
		v[3] = put(0.0314, -0.028, 0.0014);
		polygons[254] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0.04, -0.028, 0.004), put(0.04, -0.028, -0.1), put(0.02, -0.028, 0.004), mainThread.textures[29], 3.2f, 0.8f, 9);
		polygons[254].lightMapTextureIndex = 1;
		
		v[0] = put(0.0308, -0.028, 0.0008);
		v[1] = put(0.0308, -0.028, -0.1);
		v[2] = put(0.0268, -0.028, -0.1);
		v[3] = put(0.0268, -0.028, -0.0032);
		polygons[255] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0.04, -0.028, 0.004), put(0.04, -0.028, -0.1), put(0.02, -0.028, 0.004), mainThread.textures[29], 3.2f, 0.8f, 9);
		polygons[255].lightMapTextureIndex = 1;
		
		v[0] = put(0.0262, -0.028, -0.0038);
		v[1] = put(0.0262, -0.028, -0.1);
		v[2] = put(0.0222, -0.028, -0.1);
		v[3] = put(0.0222, -0.028, -0.0078);
		polygons[256] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0.04, -0.028, 0.004), put(0.04, -0.028, -0.1), put(0.02, -0.028, 0.004), mainThread.textures[29], 3.2f, 0.8f, 9);
		polygons[256].lightMapTextureIndex = 1;
		
		v[0] = put(-0.0388, -0.028, 0.01);
		v[1] = put(0.04, -0.028, 0.01);
		v[2] = put(0.036, -0.028, 0.006);
		v[3] = put(-0.0388, -0.028,  0.006);
		polygons[257] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(-0.0388, -0.028, 0.01), put(0.04, -0.028, 0.01), put(-0.0388, -0.028,  -0.0078), mainThread.textures[29], 2.2f, 0.8f, 9);
		polygons[257].lightMapTextureIndex = 1;
		
		v[0] = put(-0.0388, -0.028, 0.0054);
		v[1] = put(0.0354, -0.028, 0.0054);
		v[2] = put(0.0314, -0.028, 0.0014);
		v[3] = put(-0.0388, -0.028, 0.0014);
		polygons[258] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(-0.0388, -0.028, 0.01), put(0.04, -0.028, 0.01), put(-0.0388, -0.028,  -0.0078), mainThread.textures[29], 2.2f, 0.8f, 9);
		polygons[258].lightMapTextureIndex = 1;
		
		v[0] = put(-0.0388, -0.028, 0.0008);
		v[1] =put(0.0308, -0.028, 0.0008);
		v[2] = put(0.0268, -0.028, -0.0032);
		v[3] = put(-0.0388, -0.028, -0.0032);
		polygons[259] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(-0.0388, -0.028, 0.01), put(0.04, -0.028, 0.01), put(-0.0388, -0.028,  -0.0078), mainThread.textures[29], 2.2f, 0.8f, 9);
		polygons[259].lightMapTextureIndex = 1;
		
		v[0] = put(-0.0388, -0.028, -0.0038);
		v[1] =put(0.0262, -0.028, -0.0038);
		v[2] = put(0.0222, -0.028, -0.0078);
		v[3] = put(-0.0388, -0.028, -0.0078);
		polygons[260] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(-0.0388, -0.028, 0.01), put(0.04, -0.028, 0.01), put(-0.0388, -0.028,  -0.0078), mainThread.textures[29], 2.2f, 0.8f, 9);
		polygons[260].lightMapTextureIndex = 1;
		
		v[0] = put(-0.0388, -0.028, -0.0078);
		v[1] = put(0.0222, -0.028, -0.0078);
		v[2] = put(0.0222, -0.03, -0.0078);
		v[3] = put(-0.0388, -0.03, -0.0078);
		polygons[261] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[29], 2.2f, 0.2f, 5);
		polygons[261].diffuse_I = 8;
		
		
		v[0] = put(-0.0388, -0.03,  0.006);
		v[1] = put(0.036, -0.03, 0.006);
		v[2] = put(0.04, -0.03, 0.01);
		v[3] = put(-0.0388, -0.03, 0.01);
		polygons[262] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(-0.0388, -0.03, 0.01), put(0.04, -0.03, 0.01), put(-0.0388, -0.03,  -0.0078), mainThread.textures[29], 2.2f, 0.8f, 5);
		polygons[262].diffuse_I = 0;
		
		v[0] = put(-0.0388, -0.03, 0.0014);
		v[1] = put(0.0314, -0.03, 0.0014);
		v[2] = put(0.0354, -0.03, 0.0054);
		v[3] = put(-0.0388, -0.03, 0.0054);
		polygons[263] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(-0.0388, -0.03, 0.01), put(0.04, -0.03, 0.01), put(-0.0388, -0.03,  -0.0078), mainThread.textures[29], 2.2f, 0.8f, 5);
		polygons[263].diffuse_I = 0;
		
		v[0] = put(-0.0388, -0.03, -0.0032);
		v[1] = put(0.0268, -0.03, -0.0032);
		v[2] =put(0.0308, -0.03, 0.0008);
		v[3] = put(-0.0388, -0.03, 0.0008);
		polygons[264] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(-0.0388, -0.03, 0.01), put(0.04, -0.03, 0.01), put(-0.0388, -0.03,  -0.0078), mainThread.textures[29], 2.2f, 0.8f, 5);
		polygons[264].diffuse_I = 0;
		
		v[0] = put(-0.0388, -0.03, -0.0078);
		v[1] = put(0.0222, -0.03, -0.0078);
		v[2] =put(0.0262, -0.03, -0.0038);
		v[3] = put(-0.0388, -0.03, -0.0038);
		polygons[265] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(-0.0388, -0.03, 0.01), put(0.04, -0.03, 0.01), put(-0.0388, -0.03,  -0.0078), mainThread.textures[29], 2.2f, 0.8f, 5);
		polygons[265].diffuse_I = 0;
		
		v[0] = put(-0.0388, -0.028, -0.0032);
		v[1] = put(0.0268, -0.028, -0.0032);
		v[2] = put(0.0268, -0.03, -0.0032);
		v[3] = put(-0.0388, -0.03, -0.0032);
		polygons[266] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[29], 2.2f, 0.2f, 5);
		polygons[266].diffuse_I = 5;
		
		v[0] = put(-0.0388, -0.028, 0.0014);
		v[1] = put(0.0314, -0.028, 0.0014);
		v[2] = put(0.0314, -0.03, 0.0014);
		v[3] = put(-0.0388, -0.03, 0.0014);
		polygons[267] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[29], 2.2f, 0.2f, 5);
		polygons[267].diffuse_I = 10;
		
		v[0] = put(-0.0388, -0.028, 0.006);
		v[1] = put(0.036, -0.028, 0.006);
		v[2] = put(0.036, -0.03, 0.006);
		v[3] = put(-0.0388, -0.03, 0.006);
		polygons[268] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(-0.0388, -0.027, 0.006), put(0.036, -0.027, 0.006), v[3], mainThread.textures[29], 2.1f, 0.2f, 9);
		polygons[268].lightMapTextureIndex = 1;
		
		v[0] = put(-0.0388, -0.03, 0.0054);
		v[1] = put(0.0354, -0.03, 0.0054);
		v[2] = put(0.0354, -0.028, 0.0054);
		v[3] = put(-0.0388, -0.028, 0.0054);
		
		polygons[269] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]},  put(0.036, -0.027, 0.0054), put(-0.0388, -0.027, 0.0054), put(0.036, -0.03, 0.0054), mainThread.textures[29], 2.1f, 0.2f, 5);
		polygons[269].diffuse_I = 15;
		
		v[3] = put(-0.0388, -0.028, 0.0008);
		v[2] = put(0.0308, -0.028, 0.0008);
		v[1] = put(0.0308, -0.03, 0.0008);
		v[0] = put(-0.0388, -0.03, 0.0008);
		
		polygons[270] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]},  v[0],v[1],v[3], mainThread.textures[29], 2.1f, 0.2f, 5);
		polygons[270].diffuse_I = 10;
		
		v[3] = put(-0.0388, -0.028, -0.0038);
		v[2] = put(0.0262, -0.028, -0.0038);
		v[1] = put(0.0262, -0.03, -0.0038);
		v[0] = put(-0.0388, -0.03, -0.0038);
		
		polygons[271] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]},  v[0],v[1],v[3], mainThread.textures[29], 2.1f, 0.2f, 5);
		polygons[271].diffuse_I = 5;
		
		
		
		v[0] = put(0.036, -0.03, 0.006);
		v[1] = put(0.036, -0.03, -0.1);
		v[2] = put(0.04, -0.03,-0.1);
		v[3] = put(0.04, -0.03, 0.01);
		polygons[272] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0.04, -0.03, 0.004), put(0.04, -0.03, -0.1), put(0.02, -0.03, 0.004), mainThread.textures[29], 3.2f, 0.8f, 5);
		polygons[272].diffuse_I = 0;
		
		
		v[0] = put(0.0314, -0.03, 0.0014);
		v[1] = put(0.0314, -0.03, -0.1);
		v[2] = put(0.0354, -0.03, -0.1);
		v[3] = put(0.0354, -0.03, 0.0054);
		polygons[273] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0.04, -0.03, 0.004), put(0.04, -0.03, -0.1), put(0.02, -0.03, 0.004), mainThread.textures[29], 3.2f, 0.8f, 5);
		polygons[273].diffuse_I = 0;
		
		v[0] = put(0.0268, -0.03, -0.0032);
		v[1] = put(0.0268, -0.03, -0.1);
		v[2] = put(0.0308, -0.03, -0.1);
		v[3] = put(0.0308, -0.03, 0.0008);
		polygons[274] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0.04, -0.03, 0.004), put(0.04, -0.03, -0.1), put(0.02, -0.03, 0.004), mainThread.textures[29], 3.2f, 0.8f, 5);
		polygons[274].diffuse_I = 0;
		
		v[0] = put(0.0222, -0.03, -0.0078);
		v[1] = put(0.0222, -0.03, -0.1);
		v[2] = put(0.0262, -0.03, -0.1);
		v[3] = put(0.0262, -0.03, -0.0038);
		polygons[275] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0.04, -0.03, 0.004), put(0.04, -0.03, -0.1), put(0.02, -0.03, 0.004), mainThread.textures[29], 3.2f, 0.8f, 5);
		polygons[275].diffuse_I = 0;
		
		v[0] = put(0.036, -0.028, 0.006);
		v[1] = put(0.036, -0.028, -0.1);
		v[2] = put(0.036, -0.03, -0.1);
		v[3] = put(0.036, -0.03, 0.006);
		polygons[276] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0.036, -0.024, 0.006), put(0.036, -0.024, -0.1), v[3], mainThread.textures[29], 3.2f, 0.3f, 9);
		polygons[276].lightMapTextureIndex = 1;
		
		v[0] = put(0.0314, -0.028, 0.0014);
		v[1] = put(0.0314, -0.028, -0.1);
		v[2] = put(0.0314, -0.03, -0.1);
		v[3] = put(0.0314, -0.03, 0.0014);
		polygons[277] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 3.2f, 0.2f, 5);
		polygons[277].diffuse_I = 10;
		
		v[0] = put(0.0268, -0.028, -0.0032);
		v[1] = put(0.0268, -0.028, -0.1);
		v[2] = put(0.0268, -0.03, -0.1);
		v[3] = put(0.0268, -0.03, -0.0032);
		polygons[278] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 3.2f, 0.2f, 5);
		polygons[278].diffuse_I = 8;
		
		v[0] = put(0.0222, -0.028, -0.0078);
		v[1] = put(0.0222, -0.028, -0.1);
		v[2] = put(0.0222, -0.03, -0.1);
		v[3] = put(0.0222, -0.03, -0.0078);
		polygons[279] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 3.2f, 0.2f, 5);
		polygons[279].diffuse_I = 8;
		
		v[0] = put(0.0262, -0.028,  -0.1);
		v[1] = put(0.0262, -0.028,  -0.0038);
		v[2] = put(0.0262, -0.03,  -0.0038);
		v[3] = put(0.0262, -0.03,  -0.1);
		polygons[280] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 3.2f, 0.2f, 5);
		polygons[280].diffuse_I = 5;
		
		v[0] = put(0.0308, -0.028,  -0.1);
		v[1] = put(0.0308, -0.028,  0.0008);
		v[2] = put(0.0308, -0.03,  0.0008);
		v[3] = put(0.0308, -0.03,  -0.1);
		polygons[281] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 3.2f, 0.2f, 5);
		polygons[281].diffuse_I = 10;
		
		v[0] = put(0.0354, -0.028,  -0.1);
		v[1] = put(0.0354, -0.028,  0.0054);
		v[2] = put(0.0354, -0.03,  0.0054);
		v[3] = put(0.0354, -0.03,  -0.1);
		polygons[282] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 3.2f, 0.2f, 5);
		polygons[282].diffuse_I = 15;
		
		v[0] = put(0.0222, -0.031, -0.005);
		v[1] = put(0.0222, -0.031, -0.1);
		v[2] = put(0.0222, -0.036, -0.1);
		v[3] = put(0.0222, -0.036, -0.005);
		polygons[283] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 3.2f, 0.3f, 5);
		polygons[283].diffuse_I = 8;
		
		v[0] = put(0.0222, -0.037, -0.005);
		v[1] = put(0.0222, -0.037, -0.1);
		v[2] = put(0.0222, -0.042, -0.1);
		v[3] = put(0.0222, -0.042, -0.005);
		polygons[284] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 3.2f, 0.3f, 5);
		polygons[284].diffuse_I = 8;
		
	
		v[0] = put(0.0222, -0.031, -0.1);
		v[1] = put(0.0222, -0.031, -0.005);
		v[2] = put(0.0237, -0.031, -0.005);
		v[3] = put(0.0237, -0.031, -0.1);
		polygons[285] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 3.2f, 0.1f, 5);
		polygons[285].diffuse_I = 2;
		
		v[0] = put(0.0222, -0.037, -0.1);
		v[1] = put(0.0222, -0.037, -0.005);
		v[2] = put(0.0237, -0.037, -0.005);
		v[3] = put(0.0237, -0.037, -0.1);
		polygons[286] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 3.2f, 0.1f, 5);
		polygons[286].diffuse_I = 2;
		
		v[0] = put(0.0237, -0.036, -0.005);
		v[1] = put(0.0237, -0.036, -0.1);
		v[2] = put(0.0237, -0.031, -0.1);
		v[3] = put(0.0237, -0.031, -0.005);
		polygons[287] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 3.2f, 0.3f, 5);
		polygons[287].diffuse_I = 0;
		
		v[0] = put(0.0237, -0.042, -0.005);
		v[1] = put(0.0237, -0.042, -0.1);
		v[2] = put(0.0237, -0.037, -0.1);
		v[3] = put(0.0237, -0.037, -0.005);
		polygons[288] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 3.2f, 0.3f, 5);
		polygons[288].diffuse_I = 0;
		
		v[0] = put(0.0237, -0.036, -0.1);
		v[1] = put(0.0237, -0.036, -0.005);
		v[2] = put(0.0222, -0.036, -0.005);
		v[3] = put(0.0222, -0.036, -0.1);
		polygons[289] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 3.2f, 0.3f, 5);
		polygons[289].diffuse_I = 0;
		
		v[0] = put(0.0237, -0.042, -0.1);
		v[1] = put(0.0237, -0.042, -0.005);
		v[2] = put(0.0222, -0.042, -0.005);
		v[3] = put(0.0222, -0.042, -0.1);
		polygons[290] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 3.2f, 0.3f, 5);
		polygons[290].diffuse_I = 0;
		
		v[0] = put(0.0237, -0.031, -0.005);
		v[1] = put(0.0222, -0.031, -0.005);
		v[2] = put(0.0222, -0.036, -0.005);
		v[3] = put(0.0237, -0.036, -0.005);
		polygons[291] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 0.1f, 0.3f, 5);
		polygons[291].diffuse_I = 0;
		
		v[0] = put(0.0237, -0.037, -0.005);
		v[1] = put(0.0222, -0.037, -0.005);
		v[2] = put(0.0222, -0.042, -0.005);
		v[3] = put(0.0237, -0.042, -0.005);
		polygons[292] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 0.1f, 0.3f, 5);
		polygons[292].diffuse_I = 0;
		
		v[0] = put(-0.0388, -0.031, -0.0078);
		v[1] = put(0.0222, -0.031, -0.0078);
		v[2] = put(0.0222, -0.036, -0.0078);
		v[3] = put(-0.0388, -0.036, -0.0078);
		polygons[293] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 2.2f, 0.3f, 5);
		polygons[293].diffuse_I = 8;
		
		v[0] = put(-0.0388, -0.037, -0.0078);
		v[1] = put(0.0222, -0.037, -0.0078);
		v[2] = put(0.0222, -0.042, -0.0078);
		v[3] = put(-0.0388, -0.042, -0.0078);
		polygons[294] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 2.2f, 0.3f, 5);
		polygons[294].diffuse_I = 8;
		
		v[0] = put(-0.0388, -0.036, -0.0063);
		v[1] = put(0.0222, -0.036, -0.0063);
		v[2] = put(0.0222, -0.031, -0.0063);
		v[3] = put(-0.0388, -0.031, -0.0063);
		polygons[295] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 2.2f, 0.3f, 5);
		polygons[295].diffuse_I = 0;
		
		v[0] = put(-0.0388, -0.042, -0.0063);
		v[1] = put(0.0222, -0.042, -0.0063);
		v[2] = put(0.0222, -0.037, -0.0063);
		v[3] = put(-0.0388, -0.037, -0.0063);
		polygons[296] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 2.2f, 0.3f, 5);
		polygons[296].diffuse_I = 0;
		
		v[0] = put(-0.0388, -0.031, -0.0063);
		v[1] = put(0.0222, -0.031, -0.0063);
		v[2] = put(0.0222, -0.031, -0.0078);
		v[3] = put(-0.0388, -0.031, -0.0078);
		polygons[297] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 2.2f, 0.1f, 5);
		polygons[297].diffuse_I = 0;
		
		v[0] = put(-0.0388, -0.037, -0.0063);
		v[1] = put(0.0222, -0.037, -0.0063);
		v[2] = put(0.0222, -0.037, -0.0078);
		v[3] = put(-0.0388, -0.037, -0.0078);
		polygons[298] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 2.2f, 0.1f, 5);
		polygons[298].diffuse_I = 0;
		
		v[0] = put(-0.0388, -0.036, -0.0078);
		v[1] = put(0.0222, -0.036, -0.0078);
		v[2] = put(0.0222, -0.036, -0.0063);
		v[3] = put(-0.0388, -0.036, -0.0063);
		polygons[299] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 2.2f, 0.1f, 5);
		polygons[299].diffuse_I = 0;
		
		v[0] = put(-0.0388, -0.042, -0.0078);
		v[1] = put(0.0222, -0.042, -0.0078);
		v[2] = put(0.0222, -0.042, -0.0063);
		v[3] = put(-0.0388, -0.042, -0.0063);
		polygons[300] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 2.2f, 0.1f, 5);
		polygons[300].diffuse_I = 0;
		
		
		temp = start.myClone();
		
		v[0] = put(-0.024, -0.03, -0.0063);
		v[1] = put(-0.02, -0.03, -0.0063);
		v[2] = put(-0.02, -0.042, -0.0063);
		v[3] = put(-0.024, -0.042, -0.0063);
		polygons[301] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[29], 1f, 0.3f, 5);
		polygons[301].diffuse_I = 2;
		
		v[0] = put(-0.02, -0.03, -0.0063);
		v[1] = put(-0.02, -0.03, -0.0048);
		v[2] = put(-0.02, -0.042, -0.0048);
		v[3] = put(-0.02, -0.042, -0.0063);
		polygons[302] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[29], 1f, 0.1f, 5);
		polygons[302].diffuse_I = 0;
		
		v[0] = put(-0.024, -0.042, -0.0063);
		v[1] = put(-0.024, -0.042, -0.0048);
		v[2] = put(-0.024, -0.03, -0.0048);
		v[3] = put(-0.024, -0.03, -0.0063);
		polygons[303] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[29], 1f, 0.1f, 5);
		polygons[303].diffuse_I = 0;
		
		v[0] = put(-0.024, -0.042, -0.0048);
		v[1] = put(-0.02, -0.042, -0.0048);
		v[2] = put(-0.02, -0.03, -0.0048);
		v[3] = put(-0.024, -0.03, -0.0048);
		polygons[304] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[29], 1f, 0.3f, 5);
		polygons[304].diffuse_I = 0;
		
		v[0] = put(-0.024, -0.0315, -0.0048);
		v[1] = put(-0.02, -0.0315, -0.0048);
		v[2] = put(-0.02, -0.0315, 0.01);
		v[3] = put(-0.024, -0.0315, 0.01);
		polygons[305] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[29], 1f, 0.3f, 5);
		polygons[305].diffuse_I = 0;
		
		v[0] = put(-0.024, -0.03, 0.01);
		v[1] = put(-0.02, -0.03, 0.01);
		v[2] = put(-0.02, -0.03, -0.0048);
		v[3] = put(-0.024, -0.03, -0.0048);
		polygons[306] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[29], 1f, 0.3f, 5);
		polygons[306].diffuse_I = 10;
		
		v[0] = put(-0.02, -0.03, -0.0048);
		v[1] = put(-0.02, -0.03, 0.01);
		v[2] = put(-0.02, -0.0315, 0.01);
		v[3] = put(-0.02, -0.0315, -0.0048);
		polygons[307] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 1f, 0.1f, 5);
		polygons[307].diffuse_I = 2;
		
		v[0] = put(-0.024, -0.0315, -0.0048);
		v[1] = put(-0.024, -0.0315, 0.01);
		v[2] = put(-0.024, -0.03, 0.01);
		v[3] = put(-0.024, -0.03, -0.0048);
		polygons[308] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 1f, 0.1f, 5);
		polygons[308].diffuse_I = 2;
		
		start = put(0.033, 0, 0);
		
		v[0] = put(-0.024, -0.03, -0.0063);
		v[1] = put(-0.02, -0.03, -0.0063);
		v[2] = put(-0.02, -0.042, -0.0063);
		v[3] = put(-0.024, -0.042, -0.0063);
		polygons[309] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[29], 1f, 0.3f, 5);
		polygons[309].diffuse_I = 2;
		
		v[0] = put(-0.02, -0.03, -0.0063);
		v[1] = put(-0.02, -0.03, -0.0048);
		v[2] = put(-0.02, -0.042, -0.0048);
		v[3] = put(-0.02, -0.042, -0.0063);
		polygons[310] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[29], 1f, 0.1f, 5);
		polygons[310].diffuse_I = 0;
		
		v[0] = put(-0.024, -0.042, -0.0063);
		v[1] = put(-0.024, -0.042, -0.0048);
		v[2] = put(-0.024, -0.03, -0.0048);
		v[3] = put(-0.024, -0.03, -0.0063);
		polygons[311] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[29], 1f, 0.1f, 5);
		polygons[311].diffuse_I = 0;
		
		v[0] = put(-0.024, -0.042, -0.0048);
		v[1] = put(-0.02, -0.042, -0.0048);
		v[2] = put(-0.02, -0.03, -0.0048);
		v[3] = put(-0.024, -0.03, -0.0048);
		polygons[312] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[29], 1f, 0.3f, 5);
		polygons[312].diffuse_I = 0;
		
		v[0] = put(-0.024, -0.0315, -0.0048);
		v[1] = put(-0.02, -0.0315, -0.0048);
		v[2] = put(-0.02, -0.0315, 0.01);
		v[3] = put(-0.024, -0.0315, 0.01);
		polygons[313] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[29], 1f, 0.3f, 5);
		polygons[313].diffuse_I = 0;
		
		v[0] = put(-0.024, -0.03, 0.01);
		v[1] = put(-0.02, -0.03, 0.01);
		v[2] = put(-0.02, -0.03, -0.0048);
		v[3] = put(-0.024, -0.03, -0.0048);
		polygons[314] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[29], 1f, 0.3f, 5);
		polygons[314].diffuse_I = 10;
		
		v[0] = put(-0.02, -0.03, -0.0048);
		v[1] = put(-0.02, -0.03, 0.01);
		v[2] = put(-0.02, -0.0315, 0.01);
		v[3] = put(-0.02, -0.0315, -0.0048);
		polygons[315] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 1f, 0.1f, 5);
		polygons[315].diffuse_I = 2;
		
		v[0] = put(-0.024, -0.0315, -0.0048);
		v[1] = put(-0.024, -0.0315, 0.01);
		v[2] = put(-0.024, -0.03, 0.01);
		v[3] = put(-0.024, -0.03, -0.0048);
		polygons[316] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[29], 1f, 0.1f, 5);
		polygons[316].diffuse_I = 2;
		
		start = temp.myClone();
		
		float dz = 0.022f;
		for(int i = 0; i < 4; i++){
			v[0] = put(0.0237, -0.03, -0.084 + dz*i);
			v[1] = put(0.0237, -0.03, -0.088 + dz*i);
			v[2] = put(0.0237, -0.042, -0.088 + dz*i);
			v[3] = put(0.0237, -0.042, -0.084 + dz*i);
			polygons[317 + i*8] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[29], 1f, 0.3f, 5);
			polygons[317 + i*8].diffuse_I = 0;
			
			v[0] = put(0.0252, -0.042, -0.084 + dz*i);
			v[1] = put(0.0252, -0.042, -0.088 + dz*i);
			v[2] = put(0.0252, -0.03, -0.088 + dz*i);
			v[3] = put(0.0252, -0.03, -0.084 + dz*i);
			polygons[318 + i*8] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[29], 1f, 0.3f, 5);
			polygons[318 + i*8].diffuse_I = 0;
			
			v[0] = put(0.0237, -0.03, -0.088 + dz*i);
			v[1] = put(0.0252, -0.03, -0.088 + dz*i);
			v[2] = put(0.0252, -0.042, -0.088 + dz*i);
			v[3] = put(0.0237, -0.042, -0.088 + dz*i);
			polygons[319 + i*8] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[29], 1f, 0.1f, 5);
			polygons[319 + i*8].diffuse_I = 0;
			
			v[0] = put(0.0237, -0.042, -0.084 + dz*i);
			v[1] = put(0.0252, -0.042, -0.084 + dz*i);
			v[2] = put(0.0252, -0.03, -0.084 + dz*i);
			v[3] = put(0.0237, -0.03, -0.084 + dz*i);
			polygons[320 + i*8] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[29], 1f, 0.1f, 5);
			polygons[320 + i*8].diffuse_I = 0;
			
			v[0] = put(0.04, -0.03, -0.084 + dz*i);
			v[1] = put(0.04, -0.03, -0.088 + dz*i);
			v[2] = put(0.0252, -0.03, -0.088 + dz*i);
			v[3] = put(0.0252, -0.03, -0.084 + dz*i);
			polygons[321 + i*8] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[29], 1f, 0.3f, 5);
			polygons[321 + i*8].diffuse_I = 10;
			
			v[0] = put(0.0252, -0.0315, -0.084 + dz*i);
			v[1] = put(0.0252, -0.0315, -0.088 + dz*i);
			v[2] = put(0.04, -0.0315, -0.088 + dz*i);
			v[3] = put(0.04, -0.0315, -0.084 + dz*i);
			polygons[322 + i*8] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[29], 1f, 0.3f, 5);
			polygons[322 + i*8].diffuse_I = 0;
			
			v[0] = put(0.0252, -0.0315, -0.088 + dz*i);
			v[1] = put(0.0252, -0.03, -0.088 + dz*i);
			v[2] = put(0.04, -0.03, -0.088 + dz*i);
			v[3] = put(0.04, -0.0315, -0.088 + dz*i);
			polygons[323 + i*8] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[29], 1f, 0.1f, 5);
			polygons[323 + i*8].diffuse_I = 0;
			
			v[0] = put(0.04, -0.0315, -0.084 + dz*i);
			v[1] = put(0.04, -0.03, -0.084 + dz*i);
			v[2] = put(0.0252, -0.03, -0.084 + dz*i);
			v[3] = put(0.0252, -0.0315, -0.084 + dz*i);
			polygons[324 + i*8] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[29], 1f, 0.1f, 5);
			polygons[324 + i*8].diffuse_I = 0;
			
			
			v[0] = put(-0.0388,  -0.021, -0.05);
			v[1] = put(-0.0248,  -0.021, -0.05);
			v[2] = put(-0.0248,  -0.043, -0.05);
			v[3] = put(-0.0388,  -0.043, -0.05);
			polygons[349] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[20], 0.5f, 1f, 5);
			polygons[349].diffuse_I = 10;
			
			v[0] = put(-0.0248,  -0.021, -0.05);
			v[1] = put(-0.0248,  -0.021,  -0.025);
			v[2] = put(-0.0248,  -0.043,  -0.025);
			v[3] = put(-0.0248,  -0.043,  -0.05);
			polygons[350] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[20], 1f, 1f, 5);
			polygons[350].diffuse_I = 5;
			
			v[0] = put(-0.0248,  -0.021,  -0.025);
			v[1] = put(-0.0388,  -0.021,  -0.025);
			v[2] = put(-0.0388,  -0.043,  -0.025);
			v[3] = put(-0.0248,  -0.043,  -0.025);
			polygons[351] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[20], 0.5f, 1f, 5);
			polygons[351].diffuse_I = 8;
			
			v[0] = put(-0.026, -0.021, -0.025);
			v[1] = put(-0.0248, -0.021, -0.025);
			v[2] = put(-0.0248, -0.021, -0.05);
			v[3] = put(-0.026, -0.021, -0.05);
			polygons[352] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[20], 1f, 0.1f, 5);
			polygons[352].diffuse_I = 15;
			
			v[0] = put(-0.0388, -0.021, -0.025);
			v[1] = put(-0.0368, -0.021, -0.025);
			v[2] = put(-0.0368, -0.021, -0.05);
			v[3] = put(-0.0388, -0.021, -0.05);
			polygons[353] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[20], 1f, 0.1f, 5);
			polygons[353].diffuse_I = 13;
			
			v[0] = put(-0.0368, -0.021, -0.05);
			v[1] = put(-0.0368, -0.021, -0.0488);
			v[2] = put(-0.026, -0.021, -0.0488);
			v[3] = put(-0.026, -0.021, -0.05);
			polygons[354] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[20], 0.5f, 0.1f, 5);
			polygons[354].diffuse_I = 14;
			
			v[0] = put(-0.0368, -0.021, -0.0262);
			v[1] = put(-0.0368, -0.021, -0.025);
			v[2] = put(-0.026, -0.021, -0.025);
			v[3] = put(-0.026, -0.021, -0.0262);
			polygons[355] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[20], 0.5f, 0.1f, 5);
			polygons[355].diffuse_I = 14;
			
			v[0] = put(-0.027, -0.021, -0.0262);
			v[1] = put(-0.026, -0.021, -0.0262);
			v[2] = put(-0.026, -0.021, -0.0488);
			v[3] = put(-0.027, -0.021, -0.0488);
			polygons[356] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[31], 2f, 0.1f, 5);
			polygons[356].diffuse_I = 6;
			
			v[0] = put(-0.0368, -0.021, -0.0262);
			v[1] = put(-0.0358, -0.021, -0.0262);
			v[2] = put(-0.0358, -0.021, -0.0488);
			v[3] = put(-0.0368, -0.021, -0.0488);
			polygons[357] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[31], 2f, 0.1f, 5);
			polygons[357].diffuse_I = 4;
			
			v[0] = put(-0.0358, -0.021, -0.0488);
			v[1] = put(-0.0358, -0.021, -0.0478);
			v[2] = put(-0.027, -0.021, -0.0478);
			v[3] = put(-0.027, -0.021, -0.0488);
			polygons[358] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[31], 2f, 0.1f, 5);
			polygons[358].diffuse_I = 5;
			
			v[0] = put(-0.0358, -0.021, -0.0272);
			v[1] = put(-0.0358, -0.021, -0.0262);
			v[2] = put(-0.027, -0.021, -0.0262);
			v[3] = put(-0.027, -0.021, -0.0272);
			polygons[359] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[31], 2f, 0.1f, 5);
			polygons[359].diffuse_I = 5;
			
			v[0] = put(-0.0358, -0.021, -0.0272);
			v[1] = put(-0.027, -0.021, -0.0272);
			v[2] = put(-0.027, -0.023, -0.0272);
			v[3] = put(-0.0358, -0.023, -0.0272);
			polygons[360] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[31], 1f, 0.2f, 5);
			polygons[360].diffuse_I = 0;
			
			v[0] = put(-0.0358, -0.023, -0.0478);
			v[1] = put(-0.027, -0.023, -0.0478);
			v[2] = put(-0.027, -0.021, -0.0478);
			v[3] = put(-0.0358, -0.021, -0.0478);
			polygons[361] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[31], 1f, 0.2f, 5);
			polygons[361].diffuse_I = 0;
			
			v[0] = put(-0.027, -0.021, -0.0262);
			v[1] = put(-0.027, -0.021, -0.0488);
			v[2] = put(-0.027, -0.023, -0.0488);
			v[3] = put(-0.027, -0.023, -0.0262);
			polygons[362] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[31], 1f, 0.2f, 5);
			polygons[362].diffuse_I = 0;
			
			v[0] = put(-0.0358, -0.023, -0.0262);
			v[1] = put(-0.0358, -0.023, -0.0488);
			v[2] = put(-0.0358, -0.021, -0.0488);
			v[3] = put(-0.0358, -0.021, -0.0262);
			polygons[363] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0],v[1], v[3], mainThread.textures[31], 1f, 0.2f, 5);
			polygons[363].diffuse_I = 0;
			
			v[0] = put(-0.0358, -0.023, -0.0272);
			v[1] = put(-0.027, -0.023, -0.0272);
			v[2] = put(-0.027, -0.023, -0.0478);
			v[3] = put(-0.0358, -0.023, -0.0478);
			polygons[364] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[1],v[2], v[0], mainThread.textures[32], 1f, 1f, 5);
			polygons[364].diffuse_I =0 ;
			
			
		}

		for(int i = 0; i < polygons.length; i++)
			polygons[i].diffuse_I = 63 - (31 - polygons[i].diffuse_I)*2;
	}
}
