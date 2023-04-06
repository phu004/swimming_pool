package core;
public class steamRoom extends solidObject{
	
	public fog steamFog;
	
	public steamRoom(vector origin){
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
				
		//define underwater fog 
		steamFog = new fog(put(0,-0.01,-0.05), iDirection, jDirection, kDirection, 0.0404f, 0.034f, 0.0605f, 0x888888, 65386318f, mainThread.offScreen);
		steamFog.outsideVolumeFadeRate = 0;
		
		
		//create polygons 
		makePolygons();
		
		//drawBoundary = true;
	}
	
	public void makePolygons(){
		polygons = new polygon3D[175];
		
		v = new vector[8];
		
		//create vertices
		v[0]= put(-0.04, -0.043, 0.01);
		v[1]= put(0.04, -0.043, 0.01);
		v[2]= put(0.04, -0.043, -0.1);
		v[3]= put(-0.04, -0.043, -0.1);
		polygons[polygons.length -1] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[22], 3f, 2.5f, 10 );
		polygons[polygons.length -1].diffuse_I = 15;

		
		v[0] = put(-0.04, 0.015, 0.01);
		v[1] = put(0.04, 0.015, 0.01);
		v[2] = put(0.04, -0.043, 0.01);
		v[3] = put(-0.04, -0.043, 0.01);
		polygons[polygons.length -2] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[23], 2f, 1.5f, 10 );
		polygons[polygons.length -2].diffuse_I = 7;
		
		
		v[0] = put(-0.04, -0.043, -0.1);
		v[1] = put(0.04, -0.043, -0.1);
		v[2] = put(0.04, 0.015, -0.1);
		v[3] = put(-0.04, 0.015, -0.1);
		
		polygons[polygons.length -3] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[23], 2f, 1.5f, 10);
		polygons[polygons.length -3].diffuse_I = 7;
		
		v[0]= put(-0.04, 0.015, -0.1);
		v[1]= put(0.04, 0.015, -0.1);
		v[2]= put(0.04, 0.015, 0.01);
		v[3]= put(-0.04, 0.015, 0.01);
		polygons[polygons.length -4] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[23], 2f,3f, 10);
		polygons[polygons.length -4].diffuse_I = 5;
		
		v[0] = put(0.04, 0.015, 0.01);
		v[1] = put(0.04, 0.015, -0.1);
		v[2] = put(0.04, -0.043, -0.1);
		v[3] = put(0.04, -0.043, 0.01);
		polygons[polygons.length -5] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[23], 3f,1.5f, 10);
		polygons[polygons.length -5].diffuse_I = 3;
		
		for(int i = polygons.length -5; i < polygons.length; i++){
			
			polygons[i].fogVolume = steamFog;
		}
		
		
		
		v[0] = put(-0.042, 0.015, 0.01);
		v[1] = put(-0.042, 0.015, -0.0);
		v[2] = put(-0.042,-0.043, -0.0);
		v[3] = put(-0.042,-0.043, 0.01);
		polygons[0] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[23], 0.28f,1.5f, 5);
		
		v[4] = put(-0.042, 0.015, 0.0);
		v[5] = put(-0.042, 0.015, -0.09);
		v[6] = put(-0.042, 0.008, -0.09);
		v[7] = put(-0.042, 0.008, 0.0);
		polygons[1] = new polygon3D(new vector[]{v[4],v[5],v[6],v[7]}, v[0], v[1], v[3], mainThread.textures[23], 0.28f,1.5f, 5);
		
		for(int i = 0; i < 2; i++)
			polygons[i].diffuse_I = 31;
		
		v[0] = put(-0.042, 0.008, 0.0);
		v[1] = put(-0.04, 0.008, 0.0);
		v[2] = put(-0.04, -0.043, 0.0);
		v[3] = put(-0.042, -0.043, 0.0);
		polygons[2] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 5);
		polygons[2].diffuse_I = 16;
		
		v[4] = put(-0.04, 0.008, 0.0);
		v[5] = put(-0.038, 0.008, 0.0);
		v[6] = put(-0.038, -0.043, 0.0);
		v[7] = put(-0.040, -0.043, 0.0);
		polygons[3] = new polygon3D(new vector[]{v[4],v[5],v[6],v[7]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 10);
		polygons[3].diffuse_I = 10;
		polygons[3].fogVolume = steamFog;
		
		v[0] = put(-0.042, 0.008, -0.083); //-0.0236
		v[1] = put(-0.04, 0.008, -0.083);
		v[2] = put(-0.04, 0.008, 0);
		v[3] = put(-0.042, 0.008, 0);
		polygons[4] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 5);
		polygons[4].diffuse_I = 8;
		
		v[4] = put(-0.04, 0.008, -0.083);
		v[5] = put(-0.038, 0.008, -0.083);
		v[6] = put(-0.038, 0.008, 0);
		v[7] = put(-0.04, 0.008, 0);
		polygons[5] = new polygon3D(new vector[]{v[4],v[5],v[6],v[7]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 10);
		polygons[5].diffuse_I = 6;
		polygons[5].fogVolume = steamFog;
		
		v[0] = put(-0.042, 0.0081, -0.083);
		v[1] = put(-0.042, 0.0081, -0.09);
		v[2] = put(-0.042, -0.043, -0.09);
		v[3] = put(-0.042, -0.043, -0.083);
		polygons[6] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(-0.042, 0.015, 0.01), put(-0.042, 0.015, -0.0), put(-0.042,-0.043, 0.01), mainThread.textures[23], 0.28f,1.5f, 5);
		polygons[6].diffuse_I = 31;
		
		v[0] = put(-0.040, 0.0081, -0.083);
		v[1] = put(-0.042, 0.0081, -0.083);
		v[2] = put(-0.042, -0.043, -0.083);
		v[3] = put(-0.040, -0.043, -0.083);
		polygons[7] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 5);
		polygons[7].diffuse_I = 16;
		
		v[4] = put(-0.038, 0.0081, -0.083);
		v[5] = put(-0.04, 0.0081, -0.083);
		v[6] = put(-0.04, -0.043, -0.083);
		v[7] = put(-0.038, -0.043, -0.083);
		polygons[8] = new polygon3D(new vector[]{v[4],v[5],v[6],v[7]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 10);
		polygons[8].diffuse_I = 10;
		polygons[8].fogVolume = steamFog;
		
		v[0] = put(-0.04, 0.008, -0.0236);
		v[1] = put(-0.042, 0.008, -0.0236);
		v[2] = put(-0.042, -0.043, -0.0236);
		v[3] = put(-0.04, -0.043, -0.0236);
		polygons[9] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 5);
		polygons[9].diffuse_I = 16;
		
		v[4] = put(-0.038, 0.008, -0.0236);
		v[5] = put(-0.04, 0.008, -0.0236);
		v[6] = put(-0.04, -0.043, -0.0236);
		v[7] = put(-0.038, -0.043, -0.0236);
		polygons[10] = new polygon3D(new vector[]{v[4],v[5],v[6],v[7]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 10);
		polygons[10].diffuse_I = 10;
		polygons[10].fogVolume = steamFog;
		
		v[0] = put(-0.042, 0.0081, -0.0236);
		v[1] = put(-0.042, 0.0081, -0.0286);
		v[2] = put(-0.042, -0.043, -0.0286);
		v[3] = put(-0.042, -0.043, -0.0236);
		polygons[11] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(-0.042, 0.015, 0.01), put(-0.042, 0.015, -0.0), put(-0.042,-0.043, 0.01), mainThread.textures[23], 0.28f,1.5f, 5);
		polygons[11].diffuse_I = 31;
		
		v[0] = put(-0.04, -0.043, -0.0286);
		v[1] = put(-0.042, -0.043, -0.0286);
		v[2] = put(-0.042, 0.008, -0.0286);
		v[3] = put(-0.04, 0.008, -0.0286);
		polygons[12] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 5);
		polygons[12].diffuse_I = 16;
		
		v[4] = put(-0.038, -0.043, -0.0286);
		v[5] = put(-0.04, -0.043, -0.0286);
		v[6] = put(-0.04, 0.008, -0.0286);
		v[7] = put(-0.038, 0.008, -0.0286);
		polygons[13] = new polygon3D(new vector[]{v[4],v[5],v[6],v[7]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 10);
		polygons[13].diffuse_I = 10;
		polygons[13].fogVolume = steamFog;
		
		v[0] = put(-0.038, -0.043, -0.0236);
		v[1] = put(-0.038, -0.043, -0.0286);
		v[2] = put(-0.038, 0.008, -0.0286);
		v[3] = put(-0.038, 0.008   , -0.0236);
		polygons[14] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(-0.038, 0.015, 0.01), put(-0.038, 0.015, -0.0), put(-0.038,-0.043, 0.01), mainThread.textures[23], 0.28f,1.5f, 10);
		polygons[14].diffuse_I = 10;
		polygons[14].fogVolume = steamFog;
		
		v[0] = put(-0.038, -0.043, -0.083);
		v[1] = put(-0.038, -0.043, -0.1);
		v[2] = put(-0.038, 0.0081, -0.1);
		v[3] = put(-0.038, 0.0081, -0.083);
		polygons[15] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(-0.038, 0.015, 0.01), put(-0.038, 0.015, -0.0), put(-0.038,-0.043, 0.01), mainThread.textures[23], 0.28f,1.5f, 10);
		polygons[15].diffuse_I = 10;
		polygons[15].fogVolume = steamFog;
		

		v[0] = put(-0.038,-0.043, 0.01);
		v[1] = put(-0.038,-0.043, -0.0);
		v[2] = put(-0.038, 0.008, -0.0);
		v[3] = put(-0.038, 0.008, 0.01);
		polygons[16] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(-0.038, 0.015, 0.01), put(-0.038, 0.015, -0.0), put(-0.038,-0.043, 0.01), mainThread.textures[23], 0.28f,1.5f, 10);
		polygons[16].diffuse_I = 10;
		polygons[16].fogVolume = steamFog;
		
		v[4] = put(-0.038, 0.0079, 0.011);
		v[5] = put(-0.038, 0.0079, -0.1);
		v[6] = put(-0.038, 0.015, -0.1);
		v[7] = put(-0.038, 0.015, 0.011);
		polygons[17] = new polygon3D(new vector[]{v[4],v[5],v[6],v[7]},put(-0.038, 0.015, 0.01), put(-0.038, 0.015, -0.0), put(-0.038,-0.043, 0.01), mainThread.textures[23], 0.28f,1.5f, 10);
		polygons[17].diffuse_I = 10;
		polygons[17].fogVolume = steamFog;
		
		v[0] = put(-0.042, -0.038, -0.0285);
		v[1] = put(-0.042, -0.038, -0.084);
		v[2] = put(-0.042, -0.043, -0.084);
		v[3] = put(-0.042, -0.043, -0.0285);
		polygons[18] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(-0.042, 0.015, 0.01), put(-0.042, 0.015, -0.0), put(-0.042,-0.043, 0.01), mainThread.textures[23], 0.28f,1.5f, 5);
		polygons[18].diffuse_I = 31;
		
		v[0] = put(-0.038, -0.043, -0.0285);
		v[1] = put(-0.038, -0.043, -0.084);
		v[2] = put(-0.038, -0.038, -0.084);
		v[3] = put(-0.038, -0.038, -0.0285);
		polygons[19] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(-0.038, 0.015, 0.01), put(-0.038, 0.015, -0.0), put(-0.038,-0.043, 0.01), mainThread.textures[23], 0.28f,1.5f, 10);
		polygons[19].diffuse_I = 10;
		polygons[19].fogVolume = steamFog;
		
		v[0] = put(-0.042, -0.038, -0.0285);
		v[1] = put(-0.04, -0.038, -0.0285);
		v[2] = put(-0.04, -0.038, -0.084);
		v[3] = put(-0.042, -0.038, -0.084);
		polygons[20] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 5);
		polygons[20].diffuse_I = 16;
		
		v[4] = put(-0.04, -0.038, -0.0285);
		v[5] = put(-0.038, -0.038, -0.0285);
		v[6] = put(-0.038, -0.038, -0.084);
		v[7] = put(-0.04, -0.038, -0.084);
		polygons[21] = new polygon3D(new vector[]{v[4],v[5],v[6],v[7]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 10);
		polygons[21].diffuse_I = 10;
		polygons[21].fogVolume = steamFog;
		
		v[0] = put(-0.0405, 0.008, -0.0285);
		v[1] = put(-0.0405, 0.008, -0.084);
		v[2] = put(-0.0405, -0.038, -0.084);
		v[3] = put(-0.0405, -0.038, -0.0285);
		polygon3D glass1 = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[25], 4f,3f, 20);
		glass1.diffuse_I = 0;
		glass1.alpha = 200;
		sceneGraph.addTransparent(glass1);
		
		v[0] = put(-0.0395, -0.038, -0.0285);
		v[1] = put(-0.0395, -0.038, -0.084);
		v[2] = put(-0.0395, 0.008, -0.084);
		v[3] = put(-0.0395, 0.008, -0.0285);
		polygon3D glass2 = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[33], 4f,3f, 24);
		glass2.diffuse_I = 0;
		glass2.alpha = 220;
		glass2.fogVolume = steamFog;
		sceneGraph.addTransparent(glass2);
		
		
		
		start = put(0,0,0.001);
		
		v[0] = put(-0.0415, 0.008, -0.047);
		v[1] = put(-0.0415, 0.008, -0.0485);
		v[2] = put(-0.0415, -0.038, -0.0485);
		v[3] = put(-0.0415, -0.038, -0.047);
		polygons[22] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 5);
		polygons[22].diffuse_I = 31;
		
		v[0] = put(-0.0415, 0.008, -0.0485);
		v[1] = put(-0.04, 0.008, -0.0485);
		v[2] = put(-0.04, -0.038, -0.0485);
		v[3] = put(-0.0415, -0.038, -0.0485);
		polygons[23] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 5);
		polygons[23].diffuse_I = 16;
		
		v[0] = put(-0.04, 0.008, -0.0485);
		v[1] = put(-0.0385, 0.008, -0.0485);
		v[2] = put(-0.0385, -0.038, -0.0485);
		v[3] = put(-0.04, -0.038, -0.0485);
		polygons[24] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 10);
		polygons[24].diffuse_I = 10;
		polygons[24].fogVolume = steamFog;
		
		v[0] = put(-0.0415, -0.038, -0.047);
		v[1] = put(-0.04, -0.038, -0.047);
		v[2] = put(-0.04, 0.008, -0.047);
		v[3] = put(-0.0415, 0.008, -0.047);	
		polygons[25] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 5);
		polygons[25].diffuse_I = 16;
		
		v[0] = put(-0.04, -0.038, -0.047);
		v[1] = put(-0.0385, -0.038, -0.047);
		v[2] = put(-0.0385, 0.008, -0.047);
		v[3] = put(-0.04, 0.008, -0.047);
		polygons[26] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 10);
		polygons[26].diffuse_I = 10;
		polygons[26].fogVolume = steamFog;
		
		v[0] = put(-0.0385, -0.038, -0.047);
		v[1] = put(-0.0385, -0.038, -0.0485);
		v[2] = put(-0.0385, 0.008, -0.0485);
		v[3] = put(-0.0385, 0.008, -0.047);
		polygons[27] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 10);
		polygons[27].diffuse_I = 10;
		polygons[27].fogVolume = steamFog;
		
		
		start = put(0,0,-0.019);
		v[0] = put(-0.0415, 0.008, -0.047);
		v[1] = put(-0.0415, 0.008, -0.0485);
		v[2] = put(-0.0415, -0.038, -0.0485);
		v[3] = put(-0.0415, -0.038, -0.047);
		polygons[28] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 5);
		polygons[28].diffuse_I = 31;
		
		v[0] = put(-0.0415, 0.008, -0.0485);
		v[1] = put(-0.04, 0.008, -0.0485);
		v[2] = put(-0.04, -0.038, -0.0485);
		v[3] = put(-0.0415, -0.038, -0.0485);
		polygons[29] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 5);
		polygons[29].diffuse_I = 16;
		
		v[0] = put(-0.04, 0.008, -0.0485);
		v[1] = put(-0.0385, 0.008, -0.0485);
		v[2] = put(-0.0385, -0.038, -0.0485);
		v[3] = put(-0.04, -0.038, -0.0485);
		polygons[30] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 10);
		polygons[30].diffuse_I = 10;
		polygons[30].fogVolume = steamFog;
		
		v[0] = put(-0.0415, -0.038, -0.047);
		v[1] = put(-0.04, -0.038, -0.047);
		v[2] = put(-0.04, 0.008, -0.047);
		v[3] = put(-0.0415, 0.008, -0.047);	
		polygons[31] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 5);
		polygons[31].diffuse_I = 16;
		
		v[0] = put(-0.04, -0.038, -0.047);
		v[1] = put(-0.0385, -0.038, -0.047);
		v[2] = put(-0.0385, 0.008, -0.047);
		v[3] = put(-0.04, 0.008, -0.047);
		polygons[32] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 10);
		polygons[32].diffuse_I = 10;
		polygons[32].fogVolume = steamFog;
		
		v[0] = put(-0.0385, -0.038, -0.047);
		v[1] = put(-0.0385, -0.038, -0.0485);
		v[2] = put(-0.0385, 0.008, -0.0485);
		v[3] = put(-0.0385, 0.008, -0.047);
		polygons[33] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[23], 0.04f,1.5f, 10);
		polygons[33].diffuse_I = 10;
		polygons[33].fogVolume = steamFog;
		
		
		
		start = put(0,0,0.018);
		
		start = put(0,0,0.065);
		
		v[0] = put(-0.041, 0.008, -0.065);
		v[1] = put(-0.041, 0.008, -0.0886);
		v[2] = put(-0.041, 0.004, -0.0886);
		v[3] = put(-0.041, 0.004, -0.065);
		polygons[34] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[24], 1f, 0.15f, 5 );
		
		v[4] = put(-0.041, 0.0041, -0.065);
		v[5] = put(-0.041, 0.0041, -0.069);
		v[6] = put(-0.041, -0.0392, -0.069);
		v[7] = put(-0.041, -0.0392, -0.065);
		polygons[35] = new polygon3D(new vector[]{v[4],v[5],v[6],v[7]}, v[0], v[1], v[3], mainThread.textures[24], 1f, 0.15f, 5 );
		
		v[4] = put(-0.041, 0.0041, -0.0846);
		v[5] = put(-0.041, 0.0041, -0.0886);
		v[6] = put(-0.041, -0.0392, -0.0886);
		v[7] = put(-0.041, -0.0392, -0.0846);
		polygons[36] = new polygon3D(new vector[]{v[4],v[5],v[6],v[7]}, v[0], v[1], v[3], mainThread.textures[24], 1f, 0.15f, 5 );
		
		v[4] = put(-0.041, -0.039, -0.065);
		v[5] = put(-0.041, -0.039, -0.0886);
		v[6] = put(-0.041, -0.043, -0.0886);
		v[7] = put(-0.041, -0.043, -0.065);
		polygons[37] = new polygon3D(new vector[]{v[4],v[5],v[6],v[7]}, v[0], v[1], v[3], mainThread.textures[24], 1f, 0.15f, 5 );
			
		v[4] = put(-0.041, -0.015, -0.0689); 
		v[5] = put(-0.041, -0.008, -0.0689); 
		v[6] = put(-0.041, -0.020, -0.0847);
		v[7] = put(-0.041, -0.027, -0.0847);
		polygons[38] = new polygon3D(new vector[]{v[4],v[5],v[6],v[7]}, v[0], v[1], v[3], mainThread.textures[24], 1f, 0.15f, 5 );
		
		for(int i = 34; i < 39; i++)
			polygons[i].diffuse_I = 34;
		
		
		
		v[0] = put(-0.039, 0.00395, -0.065);
		v[1] = put(-0.039, 0.00395, -0.0888);
		v[2] = put(-0.039, 0.008, -0.0888);
		v[3] = put(-0.039, 0.008, -0.065);
		polygons[39] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[24], 1f, 0.15f, 10 );
		polygons[39].diffuse_I = 10;
		polygons[39].fogVolume = steamFog;
		
		v[4] = put(-0.039, -0.0392, -0.065);
		v[5] = put(-0.039, -0.0392, -0.069);
		v[6] = put(-0.039, 0.0041, -0.069);
		v[7] = put(-0.039, 0.0041, -0.065);
		polygons[40] = new polygon3D(new vector[]{v[4],v[5],v[6],v[7]}, v[0], v[1], v[3], mainThread.textures[24], 1f, 0.15f, 10 );
		polygons[40].diffuse_I = 10;
		polygons[40].fogVolume = steamFog;
		
		v[4] = put(-0.039, -0.0392, -0.0845);
		v[5] = put(-0.039, -0.0392, -0.0888);
		v[6] = put(-0.039, 0.0041, -0.0888);
		v[7] = put(-0.039, 0.0041, -0.0845);
		polygons[41] = new polygon3D(new vector[]{v[4],v[5],v[6],v[7]}, v[0], v[1], v[3], mainThread.textures[24], 1f, 0.15f, 10 );
		polygons[41].diffuse_I = 10;
		polygons[41].fogVolume = steamFog;
		
		v[4] = put(-0.039, -0.043, -0.065);
		v[5] = put(-0.039, -0.043, -0.0888);
		v[6] = put(-0.039, -0.039, -0.0888);
		v[7] = put(-0.039, -0.039, -0.065);
		polygons[42] = new polygon3D(new vector[]{v[4],v[5],v[6],v[7]}, v[0], v[1], v[3], mainThread.textures[24], 1f, 0.15f, 10 );
		polygons[42].diffuse_I = 10;
		polygons[42].fogVolume = steamFog;
			
		v[4] = put(-0.039, -0.0271, -0.0847);
		v[5] = put(-0.039, -0.020, -0.0847);
		v[6] = put(-0.039, -0.008, -0.0689); 
		v[7] = put(-0.039, -0.0151, -0.0689); 
		polygons[43] = new polygon3D(new vector[]{v[4],v[5],v[6],v[7]}, v[0], v[1], v[3], mainThread.textures[24], 1f, 0.15f, 10 );
		polygons[43].diffuse_I = 10;
		polygons[43].fogVolume = steamFog;
		
		
		v[0] = put(-0.04101, 0.004, -0.069);
		v[1] = put(-0.04, 0.004, -0.069);
		v[2] = put(-0.04,  -0.008, -0.069);
		v[3] = put(-0.04101,  -0.008, -0.069);
		polygons[44] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[24], 0.05f, 1f, 5 );
		polygons[44].diffuse_I = 20;
		
		v[0] = put(-0.0411,-0.015, -0.069);
		v[1] = put(-0.04, -0.015, -0.069);
		v[2] = put(-0.04,  -0.039, -0.069);
		v[3] = put(-0.0411,  -0.039, -0.069);
		polygons[45] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[24], 0.05f, 1f, 5 );
		polygons[45].diffuse_I = 20;
		
		v[0] = put(-0.04, 0.004, -0.069);
		v[1] = put(-0.03895, 0.004, -0.069);
		v[2] = put(-0.03895,  -0.008, -0.069);
		v[3] = put(-0.04,  -0.008, -0.069);
		polygons[46] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[24], 0.05f, 1f, 10 );
		polygons[46].diffuse_I = 18;
		polygons[46].fogVolume = steamFog;
		
		v[0] = put(-0.04,-0.015, -0.069);
		v[1] = put(-0.03889, -0.015, -0.069);
		v[2] = put(-0.03889,  -0.039, -0.069);
		v[3] = put(-0.04,  -0.039, -0.069);
		polygons[47] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[24], 0.05f, 1f, 10 );
		polygons[47].diffuse_I = 18;
		polygons[47].fogVolume = steamFog;
		
		v[0] = put(-0.04101, 0.004, -0.0847);
		v[1] = put(-0.04, 0.004, -0.0847);
		v[2] = put(-0.04, 0.004, -0.069);
		v[3] = put(-0.04101, 0.004, -0.069);
		polygons[48] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[24], 0.05f, 1f, 5 );
		polygons[48].diffuse_I = 12;
		
		v[0] = put(-0.04, 0.004, -0.0847);
		v[1] = put(-0.03895, 0.004, -0.0847);
		v[2] = put(-0.03895, 0.004, -0.069);
		v[3] = put(-0.04, 0.004, -0.069);
		polygons[49] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[24], 0.05f, 1f, 10 );
		polygons[49].diffuse_I = 12;
		polygons[49].fogVolume = steamFog;
		
		
		v[0] = put(-0.0411, -0.039,  -0.069);
		v[1] = put(-0.04, -0.039,  -0.069);
		v[2] = put(-0.04, -0.039,  -0.0847);
		v[3] = put(-0.0411, -0.039,  -0.0847);
		polygons[50] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[24], 0.05f, 1f, 5 );
		polygons[50].diffuse_I = 25;
		
		v[0] = put(-0.04, -0.039,  -0.069);
		v[1] = put(-0.03889, -0.039,  -0.069);
		v[2] = put(-0.03889, -0.039,  -0.0847);
		v[3] = put(-0.04, -0.039,  -0.0847);
		polygons[51] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[24], 0.05f, 1f, 10 );
		polygons[51].diffuse_I = 25;
		polygons[51].fogVolume = steamFog;
		
		v[0] = put(-0.04, -0.027, -0.08456);
		v[1] = put(-0.0411, -0.027, -0.08456);
		v[2] = put(-0.0411, -0.039, -0.08456);
		v[3] = put(-0.04, -0.039, -0.08456);
		polygons[52] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[24], 0.05f, 1f, 5 );
		polygons[52].diffuse_I = 20;
		
		v[0] = put(-0.03889, -0.027, -0.08456);
		v[1] = put(-0.04, -0.027, -0.08456);
		v[2] = put(-0.04, -0.039, -0.08456);
		v[3] = put(-0.03889, -0.039, -0.08456);
		polygons[53] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[24], 0.05f, 1f, 10 );
		polygons[53].diffuse_I = 20;
		polygons[53].fogVolume = steamFog;
		
		v[0] = put(-0.04,  -0.0151, -0.069);
		v[1] = put(-0.0411,  -0.0151, -0.069);
		v[2] = put(-0.0411,  -0.027, -0.08456);
		v[3] = put(-0.04,  -0.027, -0.08456);
		polygons[54] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[24], 0.05f, 1f, 5 );
		polygons[54].diffuse_I = 10;
	
		v[0] = put(-0.03889,  -0.0151, -0.069);
		v[1] = put(-0.04,  -0.0151, -0.069);
		v[2] = put(-0.04,  -0.027, -0.08456);
		v[3] = put(-0.03889,  -0.027, -0.08456);
		polygons[55] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[24], 0.05f, 1f, 10 );
		polygons[55].diffuse_I = 8;
		polygons[55].fogVolume = steamFog;
		
		v[0] = put(-0.04, 0.004, -0.08456);
		v[1] = put(-0.0411, 0.004, -0.08456);
		v[2] = put(-0.0411, -0.020, -0.08456);
		v[3] = put(-0.04, -0.020, -0.08456);
		polygons[56] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[24], 0.05f, 1f, 5 );
		polygons[56].diffuse_I = 25;
		
		v[0] = put(-0.03889, 0.004, -0.08456);
		v[1] = put(-0.04, 0.004, -0.08456);
		v[2] = put(-0.04, -0.020, -0.08456);
		v[3] = put(-0.03889, -0.020, -0.08456);
		polygons[57] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[24], 0.05f, 1f, 10 );
		polygons[57].diffuse_I = 20;
		polygons[57].fogVolume = steamFog;
		
		v[0] = put(-0.0411, -0.008, -0.069);
		v[1] = put(-0.04, -0.008, -0.069);
		v[2] = put(-0.04, -0.020, -0.08456);
		v[3] = put(-0.0411, -0.020, -0.08456);
		polygons[58] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[24], 0.05f, 1f, 5 );
		polygons[58].diffuse_I = 30;
		
		v[0] = put(-0.04, -0.008, -0.069);
		v[1] = put(-0.03889, -0.008, -0.069);
		v[2] = put(-0.03889, -0.020, -0.08456);
		v[3] = put(-0.04, -0.020, -0.08456);
		polygons[59] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[24], 0.05f, 1f, 10 );
		polygons[59].diffuse_I = 27;
		polygons[59].fogVolume = steamFog;
		
		v[0] = put(-0.0402,  0.00405, -0.0689);
		v[1] = put(-0.0402,  0.00405, -0.0847);
		v[2] = put(-0.0402,  -0.03905, -0.0847);
		v[3] = put(-0.0402,  -0.03905, -0.0689);
		
		v[0] = put(-0.0398,  0.00405, -0.0689);
		v[1] = put(-0.0398,  0.00405, -0.0847);
		v[2] = put(-0.0398,  -0.03905, -0.0847);
		v[3] = put(-0.0398,  -0.03905, -0.0689);
		
		polygon3D glass3 = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[34],2f, 6f, 20);
		glass3.diffuse_I = 0;
		glass3.alpha = 200;
		
		
		polygon3D glass4 = new polygon3D(new vector[]{v[3],v[2],v[1],v[0]}, v[3], v[2], v[0], mainThread.textures[34], 2f,6f, 24 );
		glass4.diffuse_I = 0;
		glass4.alpha = 200;
		glass4.fogVolume = steamFog;
		
		sceneGraph.addTransparent(glass3);
		sceneGraph.addTransparent(glass4);
		
		start = put(0,0,-0.0196);
		
		v[0] = put(-0.042, -0.012, -0.0658);
		v[1] = put(-0.042, -0.012, -0.0682);
		v[2] = put(-0.042, -0.02, -0.0682);
		v[3] = put(-0.042, -0.02, -0.0658);
		polygons[60] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[22], 0.5f, 1f, 5 );
		polygons[60].diffuse_I = 32;
		
		v[0] = put(-0.042, -0.012, -0.0682);
		v[1] = put(-0.042, -0.012, -0.0658);
		v[2] = put(-0.041, -0.012, -0.0664);
		v[3] = put(-0.041, -0.012, -0.0676);
		polygons[61] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[22], 0.5f, 1f, 5 );
		polygons[61].diffuse_I = 20;
		
		v[0] = put(-0.042, -0.02, -0.0682);
		v[1] = put(-0.042, -0.012, -0.0682);
		v[2] = put(-0.041, -0.012, -0.0676);
		v[3] = put(-0.041, -0.02, -0.0676);
		polygons[62] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[22], 0.5f, 1f, 5 );
		polygons[62].diffuse_I = 15;
		
		v[0] = put(-0.042, -0.012, -0.0658);
		v[1] = put(-0.042, -0.02, -0.0658);
		v[2] = put(-0.041, -0.02, -0.0664);
		v[3] = put(-0.041, -0.012, -0.0664);
		polygons[63] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[22], 0.5f, 1f, 5 );
		polygons[63].diffuse_I = 15;
		
		v[0] = put(-0.042, -0.02, -0.0658);
		v[1] = put(-0.042, -0.02, -0.0682);
		v[2] = put(-0.041, -0.02, -0.0676);
		v[3] = put(-0.041, -0.02, -0.0664);
		polygons[64] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[22], 0.5f, 1f, 5 );
		polygons[64].diffuse_I = 8;
		
		float x1 = -0.038f;
		float x2 = -0.039f;
		
		v[0] = put(x1, -0.012, -0.0682);
		v[1] = put(x1, -0.012, -0.0658);
		v[2] = put(x1, -0.02, -0.0658);
		v[3] = put(x1, -0.02, -0.0682);
		polygons[65] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[22], 0.5f, 1f, 10 );
		polygons[65].diffuse_I = 8;
		polygons[65].fogVolume = steamFog;
		
		v[0] = put(x1, -0.012, -0.0658);
		v[1] = put(x1, -0.012, -0.0682);
		v[2] = put(x2, -0.012, -0.0676);
		v[3] = put(x2, -0.012, -0.0664);
		polygons[66] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[22], 0.5f, 1f, 10 );
		polygons[66].diffuse_I = 18;
		polygons[66].fogVolume = steamFog;
		
		v[0] = put(x1, -0.012, -0.0682);
		v[1] = put(x1, -0.02, -0.0682);
		v[2] = put(x2, -0.02, -0.0676);
		v[3] = put(x2, -0.012, -0.0676);
		polygons[67] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[22], 0.5f, 1f, 10 );
		polygons[67].diffuse_I = 22;
		polygons[67].fogVolume = steamFog;
		
		v[0] = put(x1, -0.02, -0.0658);
		v[1] = put(x1, -0.012, -0.0658);
		v[2] = put(x2, -0.012, -0.0664);
		v[3] = put(x2, -0.02, -0.0664);
		polygons[68] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[22], 0.5f, 1f, 10 );
		polygons[68].diffuse_I = 12;
		polygons[68].fogVolume = steamFog;
		
		v[0] = put(x1, -0.02, -0.0682);
		v[1] = put(x1, -0.02, -0.0658);
		v[2] = put(x2, -0.02, -0.0664);
		v[3] = put(x2, -0.02, -0.0676);
		polygons[69] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[22], 0.5f, 1f, 10 );
		polygons[69].diffuse_I = 6;
		polygons[69].fogVolume = steamFog;
		
		start = put(0,0,0.0196);
		start = put(0,0,-0.065);
		
		vector temp = start.myClone();
		start = put(0, -0.001, 0);
		
		for(int i = 0; i < 8; i++){
			v[0]= put(0.008, -0.03, 0.01);
			v[1]= put(0.008, -0.03, -0.1);
			v[2]= put(0.004, -0.03, -0.1);
			v[3]= put(0.004, -0.03, 0.01);
			polygons[70 + i*4] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0.022 - i*(0.002f/3 + 0.004), -0.03, 0.01), put(0.022 - i*(0.002f/3 + 0.004), -0.03, -0.1), v[3]= put(0.004 - i*(0.002f/3 + 0.004), -0.03, 0.01), mainThread.textures[29], 6f, 1f, 10);
			polygons[70 + i*4].diffuse_I = 12;
			polygons[70+ i*4].fogVolume = steamFog;
			
			
			v[0] = put(0.004, -0.03, 0.01);
			v[1] = put(0.004, -0.03, -0.1);
			v[2] = put(0.004, -0.032, -0.1);
			v[3] = put(0.004, -0.032, 0.01);
			polygons[71 + i*4] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[29], 6f, 0.2f, 10);
			if(i%4 ==0)
				polygons[71 + i*4].diffuse_I = 20;
			else
				polygons[71 + i*4].diffuse_I = 5;
			polygons[71+ i*4].fogVolume = steamFog;
			
			v[0] = put(0.008, -0.032, 0.01);
			v[1] = put(0.008, -0.032, -0.1);
			v[2] = put(0.008, -0.03, -0.1);
			v[3] = put(0.008, -0.03, 0.01);
			polygons[72 + i*4] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[29], 6f, 0.2f, 10);
			polygons[72 + i*4].diffuse_I = 3;
			polygons[72+ i*4].fogVolume = steamFog;
			
			v[0]= put(0.004, -0.032, 0.01);
			v[1]= put(0.004, -0.032, -0.1);
			v[2]= put(0.008, -0.032, -0.1);
			v[3]= put(0.008, -0.032, 0.01);
			polygons[73 + i*4] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, put(0.022 - i*(0.002f/3 + 0.004), -0.032, 0.01), put(0.022 - i*(0.002f/3 + 0.004), -0.032, -0.1), v[3]= put(0.004 - i*(0.002f/3 + 0.004), -0.032, 0.01), mainThread.textures[29], 6f, 1f, 10);
			polygons[73 + i*4].diffuse_I = 7;
			polygons[73+ i*4].fogVolume = steamFog;
			
			if(i != 3)
				start = put(0.002f/3 + 0.004, 0,0);
			else{
				start = put(0.004, 0.012,0);
			}
		}
		
		
		start.set(temp);
		
		start = put(0, -0.001, 0);
		
		for(int i = 0; i < 8; i++){
			float h = 0;
			if(i >= 4)
				h = 0.012f;
			
			v[0] = put(0.004, -0.0319, -0.0825);
			v[1] = put(0.004, -0.042, -0.0825);
			v[2] = put(0.004, -0.042, -0.08);
			v[3] = put(0.004, -0.0319, -0.08);
			polygons[102 + i*8] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[29], 1f, 0.2f, 10);
			polygons[102 + i*8].diffuse_I = 20;
			polygons[102+ i*8].fogVolume = steamFog;
			
			v[0] = put(0.0065, -0.0319, -0.08);
			v[1] = put(0.0065, -0.042 - h, -0.08);
			v[2] = put(0.0065, -0.042 - h, -0.0825);
			v[3] = put(0.0065, -0.0319, -0.0825);
			polygons[103 + i*8] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[29], 1f, 0.2f, 10);
			polygons[103 + i*8].diffuse_I = 5;
			polygons[103 + i*8].fogVolume = steamFog;
			
			v[0] = put(0.004, -0.0319, -0.08);
			v[1] = put(0.004, -0.042 - h, -0.08);
			v[2] = put(0.0065, -0.042 - h, -0.08);
			v[3] = put(0.0065, -0.0319, -0.08);
			polygons[104 + i*8] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[29], 1f, 0.2f, 10);
			polygons[104 + i*8].diffuse_I = 10;
			polygons[104+ i*8].fogVolume = steamFog;
			
			v[0] = put(0.0065, -0.0319, -0.0825);
			v[1] = put(0.0065, -0.042 - h, -0.0825);
			v[2] = put(0.004, -0.042 - h, -0.0825);
			v[3] = put(0.004, -0.0319, -0.0825);
			polygons[105 + i*8] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[29], 1f, 0.2f, 10);
			polygons[105 + i*8].diffuse_I = 10;
			polygons[105+ i*8].fogVolume = steamFog;
			
			v[0] = put(0.0065, -0.032, -0.0825);
			v[1] = put(0.022, -0.032, -0.0825);
			v[2] = put(0.022, -0.034, -0.0825);
			v[3] = put(0.0065, -0.034, -0.0825);
			polygons[106 + i*8] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[29], 1f, 0.2f, 10);
			polygons[106 + i*8].diffuse_I = 10;
			polygons[106+ i*8].fogVolume = steamFog;
			
			v[0] = put(0.0065, -0.034, -0.08);
			v[1] = put(0.022, -0.034, -0.08);
			v[2] = put(0.022, -0.032, -0.08);
			v[3] = put(0.0065, -0.032, -0.08);
			polygons[107 + i*8] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[29], 1f, 0.2f, 10);
			polygons[107 + i*8].diffuse_I = 10;
			polygons[107+ i*8].fogVolume = steamFog;
			
			v[0] = put(0.0065, -0.034, -0.0825);
			v[1] = put(0.022, -0.034, -0.0825);
			v[2] = put(0.022, -0.034, -0.08);
			v[3] = put(0.0065, -0.034, -0.08);
			polygons[108 + i*8] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[29], 1f, 0.2f, 10);
			polygons[108 + i*8].diffuse_I = 7;
			polygons[108+ i*8].fogVolume = steamFog;
			
			v[0] = put(0.0065, -0.032, -0.08);
			v[1] = put(0.022, -0.032, -0.08);
			v[2] = put(0.022, -0.032, -0.0825);
			v[3] = put(0.0065, -0.032, -0.0825);
			polygons[109 + i*8] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[29], 1f, 0.2f, 10);
			polygons[109 + i*8].diffuse_I = 5;
			polygons[109+ i*8].fogVolume = steamFog;
			
			if(i == 3){
				start = put(0.018, 0.012, -0.072);
			}else{
				start = put(0, 0, 0.024);
			}
		}
		
		start.set(temp);
		
		start = put(0, -0.001, 0);
		start = put(0.018, 0, 0);
		
		for(int i = 0; i<4; i++){
			v[0] = put(0.004, -0.0319, -0.0825);
			v[1] = put(0.004, -0.042, -0.0825);
			v[2] = put(0.004, -0.042, -0.08);
			v[3] = put(0.004, -0.0319, -0.08);
			polygons[166 + i] = new polygon3D(new vector[]{v[0],v[1],v[2],v[3]}, v[0], v[1], v[3], mainThread.textures[29], 1f, 0.2f, 10);
			polygons[166 + i].diffuse_I = 10;
			polygons[166+ i].fogVolume = steamFog;
			
		
			start = put(0, 0, 0.024);
			
		}
		
		for(int i = 0; i < polygons.length; i++)
			polygons[i].diffuse_I = 63 - (31 - polygons[i].diffuse_I)*2;
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
			
			//update fog
			steamFog.update();
			
			//testFog.update();
			
			//update polygons
			for(int i = 0; i < polygons.length; i++){
				polygons[i].update();
			}
			
			
		
			
			//draw fog volume to off screen buffer;
			steamFog.draw();
			
			draw();
			
			
		}

}
