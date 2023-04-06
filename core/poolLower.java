package core;
public class poolLower extends solidObject{
	
	polygon3D poolSurface1;
	polygon3D poolSurface2;
	
	fog underwaterFog;
	
	public poolLower(vector origin){
		//define the origin point in object space
		start = origin.myClone();
		centre = origin.myClone();
		tempCentre = origin.myClone();
		
	
		
		//define axis in object space
		iDirection = new vector(0.25f/2,0,0);
		jDirection = new vector(0,0.25f/2,0);
		kDirection = new vector(0,0,0.25f/2);
		
		//define underwater fog 
		underwaterFog = new fog(put(0,0.12,0), iDirection, jDirection, kDirection, 0.12f, 0.030f, 0.23f, 0x22a1b0, 16386318f, mainThread.offScreen);
		
		
		//create bounding box
		makeBoundary(0.12f, 0.1549f, 0.23f);
				
		//create polygons
		makePolygons();
		
		
		//drawBoundary = true;
	}
	
	public void makePolygons(){
		//create vertices
		v = new vector[40];
		
		v[0] = put(-0.12, 0.09, 0.20);
		v[1] = put(0.12, 0.09, 0.20);
		v[2] = put(0.12,0.12, 0.04);
		v[3] = put(-0.12, 0.12, 0.04);
		
		v[4] = put(-0.12, 0.09, 0.23);
		v[5] = put(0.12, 0.09, 0.23);
		
		v[6] = put(-0.12, 0.12, -0.23);
		v[7] = put(0.12, 0.12, -0.23);
		
		v[8] = put(-0.12, 0.15, 0.23);
		v[9] = put(0.12, 0.15, 0.23);
		v[10] = put(0.12,0.15, -0.23);
		v[11] = put(-0.12, 0.15, -0.23);
		
		v[12] = put(-0.12, 0.155, -0.23);
		v[13] = put(-0.12, 0.155, 0.23);
		v[14] = put(-0.12, 0.15, 0.23);
		v[15] = put(-0.12, 0.15, -0.23);
		
		v[16] = put(0.12, 0.155, -0.23);
		v[17] = put(0.12, 0.155, 0.23);
		v[18] = put(0.12, 0.15, 0.23);
		v[19] = put(0.12, 0.15, -0.23);
		
		v[20] = put(0.12, 0.09, 0.23);
		v[21] = put(-0.12, 0.09, 0.23);
		
		v[22] = put(0.12, 0.119, -0.2302);
		v[23] = put(0.12, 0.119, 0.2305);
		
		v[24] = put(-0.12, 0.119, 0.2305);
		v[25] = put(-0.12, 0.119, -0.2301);
		
		v[26] = put(-0.1205,0.12, -0.2302);
		v[27] = put(0.1205,0.12, -0.2302);
		v[28] = put(0.1205,0.12, -0.20);
		v[29] = put(-0.1205, 0.12, -0.20);
		
		v[30] = put(-0.1205, 0.12, 0.0404);
		v[31] = put(0.1205, 0.12, 0.0404);
		
		v[32] = put(0.12, 0.121, 0.2305);
		v[33] = put(0.12, 0.12, 0.04);
		v[34] = put(0.12, 0.09, 0.20);
		v[35] = put(0.12, 0.09, 0.23);
		
		v[36] = put(-0.12, 0.09, 0.23);
		v[37] = put(-0.12, 0.09, 0.20);
		v[38] = put(-0.12, 0.12, 0.04);
		v[39] = put(-0.12, 0.121, 0.2305);
		
		
		
		
		
		//create polygons
		polygons = new polygon3D[14 + 26*22 * 5 + (24*10 + 24*3)*4];
		int l = polygons.length -1;
		
		
		
		polygons[0] = new polygon3D(new vector[]{v[0], v[1], v[2], v[3]}, v[2], v[3], v[1], mainThread.textures[6], 6, 4f, 10); 
		polygons[l-8] = new polygon3D(new vector[]{v[4], v[5], v[1], v[0]}, v[1], v[0], v[5], mainThread.textures[7], 6, 0.75f, 10); 
		polygons[l-9] = new polygon3D(new vector[]{v[32], v[33], v[34], v[35]}, v[17], v[16], put(0.12, 0.12, 0.23), mainThread.textures[9], 23f, 1.7f, 10);
		polygons[l-10] = new polygon3D(new vector[]{v[36], v[37], v[38], v[39]}, v[12], v[13], put(-0.12, 0.12, -0.23), mainThread.textures[9], 23f, 1.7f, 10);
		
		polygons[l-11] = new polygon3D(new vector[]{v[29], v[28], v[27], v[26]}, put(-0.12, 0.12, -0.20), put(0.12,0.12, -0.20), put(-0.12, 0.12, -0.23), mainThread.textures[7], 6, 0.75f, 10); 
		polygons[l-12] = new polygon3D(new vector[]{v[28], v[29], v[30], v[31]}, put(0.12,0.12, -0.20), put(-0.12, 0.12, -0.20) , put(0.12, 0.12, 0.04), mainThread.textures[6], 6, 6f, 10); 
		
		
		polygons[l-0] = new polygon3D(new vector[]{v[12], v[13], v[14], v[15]}, v[12], v[13], put(-0.12, 0.12, -0.23), mainThread.textures[9], 23f, 1.7f, 6); 
		polygons[l-1] = new polygon3D(new vector[]{v[17], v[16], v[19], v[18]}, v[17], v[16], put(0.12, 0.12, 0.23), mainThread.textures[9], 23f, 1.7f, 6);
		polygons[l-2] = new polygon3D(new vector[]{v[13], v[17], v[18], v[14]}, v[13], v[17], put(-0.12, 0.12, 0.23), mainThread.textures[9], 12, 1.7f, 6); 
		polygons[l-3] = new polygon3D(new vector[]{v[16], v[12], v[15], v[19]}, v[16], v[12], put(0.12, 0.12, -0.23), mainThread.textures[9], 12, 1.7f, 6); 
		
		polygons[l-4] = new polygon3D(new vector[]{v[19], v[15], v[6], v[7]}, v[16], v[12], put(0.12, 0.12, -0.23), mainThread.textures[9], 12, 1.7f, 10); 
		polygons[l-5] = new polygon3D(new vector[]{v[14], v[18], v[20], v[21]}, v[13], v[17], put(-0.12, 0.12, 0.23), mainThread.textures[9], 12, 1.7f, 10);
		polygons[l-6] = new polygon3D(new vector[]{v[18], v[19], v[22], v[23]}, v[17], v[16], put(0.12, 0.12, 0.23), mainThread.textures[9], 23f, 1.7f, 10);
		polygons[l-7] = new polygon3D(new vector[]{v[15], v[14], v[24], v[25]}, v[12], v[13], put(-0.12, 0.12, -0.23), mainThread.textures[9], 23f, 1.7f, 10);
		
		
		

		poolSurface1 = new polygon3D(new vector[]{v[8], v[9], v[10], v[11]}, v[8], v[9], v[11], mainThread.textures[8], 11, 11, 4); 
		poolSurface2 = new polygon3D(new vector[]{v[11], v[10], v[9], v[8]}, v[11], v[10], v[8], mainThread.textures[8], 11, 11, 16); 
		poolSurface1.color = 0x205060;
		poolSurface2.color = 0x205060;
		poolSurface1.fogVolume = underwaterFog;
		poolSurface2.fogVolume = underwaterFog;
		sceneGraph.addTransparent(poolSurface1);
		sceneGraph.addTransparent(poolSurface2);
		
		polygons[0].diffuse_I = 25;
		polygons[l-8].diffuse_I = 25;
		polygons[l-9].diffuse_I = 18;
		polygons[l-10].diffuse_I = 18;
		polygons[l-11].diffuse_I = 25;
		polygons[l-12].diffuse_I = 25;
		
		polygons[l-0].diffuse_I = 25;
		polygons[l-1].diffuse_I = 25;
		polygons[l-2].diffuse_I = 23;
		polygons[l-3].diffuse_I = 23;
		
		polygons[l-4].diffuse_I = 18;
		polygons[l-5].diffuse_I = 18;
		polygons[l-6].diffuse_I = 20;
		polygons[l-7].diffuse_I = 20;
		

		
		//create lane rope sections
		int index = 1;
		index = createLaneRope(0, index);
		index = createLaneRope(0.04, index);
		index = createLaneRope(0.08, index);
		index = createLaneRope(-0.04, index);
		index = createLaneRope(-0.08, index);
		
	
		
		
		//continue pool ladder
		start.set(-0.014481835f, -0.004450623f, 0.04f);
		iDirection.set(-0.125f, 0, 0.0f);
		jDirection.set(0, -0.125f, 0);
		kDirection.set(0, 0, 0.125f);
		start = put(0,0.0144, 0);
		createPoolLadder(2861, 35, 1);
		
		start.set(0.014481835f, -0.004450623f, 0.04f);
		iDirection.set(-0.125f, 0, 0.0f);
		jDirection.set(0, -0.125f, 0);
		kDirection.set(0, 0, 0.125f);
		start = put(0,0.0144, 0);
		createPoolLadder(2861 + 24*10 + 24*3, 36, 0);
		
		start.set(0.014481835f, -0.004450623f, -0.002f);
		iDirection.set(-0.125f, 0, 0.0f);
		jDirection.set(0, -0.125f, 0);
		kDirection.set(0, 0, 0.125f);
		start = put(0,0.0144, 0);
		createPoolLadder(2861 + (24*10 + 24*3)*2, 37, 0);
		
		start.set(-0.014481835f, -0.004450623f, -0.002f);
		iDirection.set(-0.125f, 0, 0.0f);
		jDirection.set(0, -0.125f, 0);
		kDirection.set(0, 0, 0.125f);
		start = put(0,0.0144, 0);
		createPoolLadder(2861 + (24*10 + 24*3)*3, 38, 1);


	
		
		for(int i = 0; i < polygons.length; i++){
			polygons[i].fogVolume = underwaterFog;
		}
		
		sortPolygons();
		
		for(int i = 0; i < polygons.length; i++)
			polygons[i].diffuse_I = 63 - (31 - polygons[i].diffuse_I)*2;
		
	}
	
	private void createPoolLadder(int index, int textureIndex, int direction){
		double theta = Math.PI/12;
		vector normal;
		int faceLeft = 0;
		int faceRight = 1;
		int angle = 0;
		
		vector s = start.myClone();
		vector i_ = iDirection.myClone();
		vector j_ = jDirection.myClone();
		vector k_ = kDirection.myClone();
		
		vector[] v1 = new vector[24];
		vector[] v2 = new vector[24];
		
		for(int j = 0; j < 2; j++){
			if(j == 1){
				index += (24*5);
				start.set(s);
				iDirection.set(i_);
				jDirection.set(j_);
				kDirection.set(k_);
				start = put(0,0, 0.015f);
			}
			
			for(int i = 0; i < 24; i++){
				v1[i] = put(0.0006*Math.cos(i*theta),0.016,  0.0006*Math.sin(i*theta));
			}
			
			for(int i = 0; i < 24; i++){
				v2[i] = put(0.0006*Math.cos(i*theta), 0,  0.0006*Math.sin(i*theta));
			}
			
			for(int i = 0; i < 24; i ++){
				polygons[i + index] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, new vector(0,0,0), new vector(0,0,0), new vector(0,0,0), mainThread.textures[textureIndex],0,0,14);
				polygons[i + index].normals = new vector[4];
			
				
				normal = polygons[i+index].vertex3D[0].myClone();
				normal.subtract(put(0, 0.016, 0));
				normal.unit();
				polygons[i+index].normals[0] = normal;
				
				normal = polygons[i+index].vertex3D[1].myClone();
				normal.subtract(put(0, 0.016, 0));
				normal.unit();
				polygons[i+index].normals[1] = normal;
				
				normal = polygons[i+index].vertex3D[2].myClone();
				normal.subtract(put(0, 0, 0));
				normal.unit();
				polygons[i+index].normals[2] = normal;
				
				normal = polygons[i+index].vertex3D[3].myClone();
				normal.subtract(put(0, 0, 0));
				normal.unit();
				polygons[i+index].normals[3] = normal;
			}
			
			start = put(0, 0.016, 0);
			
			if(direction == faceLeft)
				angle = 15;
			if(direction == faceRight)
				angle = 345;
			
			iDirection.rotate_XY(angle);
			jDirection.rotate_XY(angle);
			kDirection.rotate_XY(angle);
			
			
			for(int i = 0; i < 24; i++){
				v2[i] = v1[i].myClone();
			}
			
			for(int i = 0; i < 24; i++){
				v1[i] = put(0.0006*Math.cos(i*theta),0.001,  0.0006*Math.sin(i*theta));
			}
			
			for(int i = 0; i < 24; i ++){
				polygons[i + 24 + index] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, new vector(0,0,0), new vector(0,0,0), new vector(0,0,0), mainThread.textures[textureIndex],0,0,14);
				polygons[i + 24 + index].normals = new vector[polygons[i].vertex3D.length];
				normal = polygons[i + 24 +index].vertex3D[0].myClone();
				normal.subtract(put(0, 0.001, 0));
				normal.unit();
				polygons[i + 24 +index].normals[0] = normal;
				normal = polygons[i + 24+index].vertex3D[1].myClone();
				normal.subtract(put(0, 0.001, 0));
				normal.unit();
				polygons[i + 24 +index].normals[1] = normal;
				polygons[i + 24 +index].normals[2] = polygons[i  +index].normals[1].myClone();
				polygons[i + 24 +index].normals[3] = polygons[i + index].normals[0].myClone();
			}
			
			start = put(0, 0.001, 0);
			
			if(direction == faceLeft)
				angle = 20;
			if(direction == faceRight)
				angle = 340;
			
			iDirection.rotate_XY(angle);
			jDirection.rotate_XY(angle);
			kDirection.rotate_XY(angle);
			
			
			for(int i = 0; i < 24; i++){
				v2[i] = v1[i].myClone();
			}
			
			for(int i = 0; i < 24; i++){
				v1[i] = put(0.0006*Math.cos(i*theta),0.001,  0.0006*Math.sin(i*theta));
			}
			
			for(int i = 0; i < 24; i ++){
				polygons[i + 48 + index] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, new vector(0,0,0), new vector(0,0,0), new vector(0,0,0), mainThread.textures[textureIndex],0,0,14);
				polygons[i + 48 + index].normals = new vector[polygons[i].vertex3D.length];
				normal = polygons[i + 48 +index].vertex3D[0].myClone();
				normal.subtract(put(0, 0.001, 0));
				normal.unit();
				polygons[i + 48 +index].normals[0] = normal;
				normal = polygons[i + 48+index].vertex3D[1].myClone();
				normal.subtract(put(0, 0.001, 0));
				normal.unit();
				polygons[i + 48 +index].normals[1] = normal;
				polygons[i + 48 +index].normals[2] = polygons[i + 24  +index].normals[1].myClone();
				polygons[i + 48 +index].normals[3] = polygons[i + 24 + index].normals[0].myClone();
			}
			
			start = put(0, 0.001, 0);
			
			if(direction == faceLeft)
				angle = 25;
			if(direction == faceRight)
				angle = 335;
			
			iDirection.rotate_XY(angle);
			jDirection.rotate_XY(angle);
			kDirection.rotate_XY(angle);
			
			
			for(int i = 0; i < 24; i++){
				v2[i] = v1[i].myClone();
			}
			
			for(int i = 0; i < 24; i++){
				v1[i] = put(0.0006*Math.cos(i*theta),0.002,  0.0006*Math.sin(i*theta));
			}
			
			for(int i = 0; i < 24; i ++){
				polygons[i + 72 + index] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, new vector(0,0,0), new vector(0,0,0), new vector(0,0,0), mainThread.textures[textureIndex],0,0,14);
				polygons[i + 72 + index].normals = new vector[polygons[i].vertex3D.length];
				normal = polygons[i + 72 +index].vertex3D[0].myClone();
				normal.subtract(put(0, 0.002, 0));
				normal.unit();
				polygons[i + 72 +index].normals[0] = normal;
				normal = polygons[i + 72+index].vertex3D[1].myClone();
				normal.subtract(put(0, 0.002  , 0));
				normal.unit();
				polygons[i + 72 +index].normals[1] = normal;
				polygons[i + 72 +index].normals[2] = polygons[i + 48  +index].normals[1].myClone();
				polygons[i + 72 +index].normals[3] = polygons[i + 48 + index].normals[0].myClone();
			}
			
			start = put(0, 0.002, 0);
			
			if(direction == faceLeft)
				angle = 28;
			if(direction == faceRight)
				angle = 332;
			
			iDirection.rotate_XY(angle);
			jDirection.rotate_XY(angle);
			kDirection.rotate_XY(angle);
			
			for(int i = 0; i < 24; i++){
				v2[i] = v1[i].myClone();
			}
			
			for(int i = 0; i < 24; i++){
				v1[i] = put(0.0006*Math.cos(i*theta),0.0018,  0.0006*Math.sin(i*theta));
			}
			
			for(int i = 0; i < 24; i ++){
				polygons[i + 96 + index] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, new vector(0,0,0), new vector(0,0,0), new vector(0,0,0), mainThread.textures[textureIndex],0,0,14);
				polygons[i + 96 + index].normals = new vector[polygons[i].vertex3D.length];
				normal = polygons[i + 96 +index].vertex3D[0].myClone();
				normal.subtract(put(0, 0.0018, 0));
				normal.unit();
				polygons[i + 96 +index].normals[0] = normal;
				normal = polygons[i + 96+index].vertex3D[1].myClone();
				normal.subtract(put(0, 0.0018  , 0));
				normal.unit();
				polygons[i + 96 +index].normals[1] = normal;
				polygons[i + 96 +index].normals[2] = polygons[i + 72  +index].normals[1].myClone();
				polygons[i + 96 +index].normals[3] = polygons[i + 72 + index].normals[0].myClone();
			}
		}
		
		index += (24*5);
		start.set(s);
		iDirection.set(i_);
		jDirection.set(j_);
		kDirection.set(k_);
		start = put(0,-0.005, 0);
		
		for(int j = 0; j < 3; j++){
			start = put(0,0.007, 0);
			for(int i = 0; i < 24; i++){
				v1[i] = put(0.0005*Math.sin(i*theta),0.0005*Math.cos(i*theta), 0.015);
			}
			
			for(int i = 0; i < 24; i++){
				v2[i] = put(0.0005*Math.sin(i*theta), 0.0005*Math.cos(i*theta), 0);
			}
			
			for(int i = 0; i < 24; i++){
				polygons[i + index] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, new vector(0,0,0), new vector(0,0,0), new vector(0,0,0), mainThread.textures[textureIndex],0,0,14);
				polygons[i + index].normals = new vector[4];
			
				
				normal = polygons[i+index].vertex3D[0].myClone();
				normal.subtract(put(0, 0, 0.015));
				normal.unit();
				polygons[i+index].normals[0] = normal;
				
				normal = polygons[i+index].vertex3D[1].myClone();
				normal.subtract(put(0, 0, 0.015));
				normal.unit();
				polygons[i+index].normals[1] = normal;
				
				normal = polygons[i+index].vertex3D[2].myClone();
				normal.subtract(put(0, 0, 0));
				normal.unit();
				polygons[i+index].normals[2] = normal;
				
				normal = polygons[i+index].vertex3D[3].myClone();
				normal.subtract(put(0, 0, 0));
				normal.unit();
				polygons[i+index].normals[3] = normal;
			}
			index+=24;
		}
		
		
		
	}
	
	private int createLaneRope(double x, int index){
		polygon3D[] poly = createSingleLaneRopeSection(put(x,0.1493,-0.23), 0x000000, 0.0001, 0.46);
		for(int i = 0; i < poly.length; i++, index++)
			polygons[index] = poly[i];
		
		poly = createSingleLaneRopeSection(put(x,0.1495,-0.228), 0xa62629, 0.001, 0.02);
		for(int i = 0; i < poly.length; i++, index++)
			polygons[index] = poly[i];
		
		
		for(int j = 0; j < 19; j++){
			int color = 0;
			
			if(j%2 == 0)
				color = 0xa2a7ab;
			else
				color = 0x2390;
			
			poly = createSingleLaneRopeSection(put(x,0.1495,-0.206 + j * 0.022), color, 0.001, 0.02);
			for(int i = 0; i < poly.length; i++, index++)
				polygons[index] = poly[i];
		}
		
		poly = createSingleLaneRopeSection(put(x,0.1495,-0.184 + 18 * 0.022), 0xa62629, 0.001, 0.017);
		for(int i = 0; i < poly.length; i++, index++)
			polygons[index] = poly[i];
		
		return index;
	}
	
	private polygon3D[] createSingleLaneRopeSection(vector startingPoint, int color, double r, double length){
		polygon3D[] poly = new polygon3D[26];
		
		vector tmp = start.myClone();
		start.set(startingPoint);
		
		double theta = Math.PI/12;
		vector[] v1 = new vector[24];
		vector[] v2 = new vector[24];
		
		for(int i = 0; i < 24; i++){
			v1[i] = put(-r*Math.cos(i*theta), r*Math.sin(i*theta), 0);
		}
		
		for(int i = 0; i < 24; i++){
			v2[i] = put(r*Math.cos(i*theta), r*Math.sin(i*theta), length);
		}
		
		poly[0] = new polygon3D(v1, v1[0], v1[1], v1[3], null, 1f, 1f, 2);
		poly[1] = new polygon3D(v2, v2[0], v2[1], v2[3], null, 1f, 1f, 2);
		
		for(int i = 0; i < 24; i ++){
			poly[2 + i] = new polygon3D(new vector[]{v2[(35-i)%24],v2[(34 -i)%24],v1[(i+1)%24],v1[i]}, v1[0], v1[1], v2[0], null, 1,1,2);
		}
		
		int r_ = (color & 0x00ff0000)>>16;
		int g_ = (color & 0x0000ff00)>>8;
		int b_ = (color & 0x000000ff);
		r_ = r_/8;
		g_ = g_/4;
		b_ = b_/8;
		color = ((int)r_ <<11 | (int)g_ << 5 | (int)b_);
		
		for(int i = 0; i < poly.length; i++){
			poly[i].color = color;
			poly[i].reflectance = 30;
			poly[i].findDiffuse();
			poly[i].diffuse_I +=6;
		}
		
		start.set(tmp);
		
		return poly;
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
		underwaterFog.update();
		
		//update polygons
		for(int i = 0; i < polygons.length; i++){
			polygons[i].update();
		}
		
		
		
		
		
		poolSurface1.origin.add(0.00001f,0,0.000f);
		poolSurface1.rightEnd.add(0.00001f,0,0.000f);
		poolSurface1.bottomEnd.add(0.00001f,0,0.000f);
		poolSurface2.origin.add(0.00001f,0,0.000f);
		poolSurface2.rightEnd.add(0.00001f,0,0.000f);
		poolSurface2.bottomEnd.add(0.00001f,0,0.000f);
		
		//draw fog volume to off screen buffer;
		underwaterFog.draw();
		
		draw();
	}
}
