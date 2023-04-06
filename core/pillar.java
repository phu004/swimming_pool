package core;

public class pillar extends solidObject{

	public pillar(vector origin){
		start = origin.myClone();
		centre = origin.myClone();
		tempCentre = origin.myClone();
		
		//define axis in object space
		iDirection = new vector(0.25f/2,0,0);
		jDirection = new vector(0,0.25f/2,0);
		kDirection = new vector(0,0,0.25f/2);
		
		//create bounding box
		makeBoundary(0.005f, 0.029f, 0.005f);
		
		//create polygons 
		makePolygons();
		
		//drawBoundary = true;
		
		enableOcclusionCulling = false;
	}
	
	public void makePolygons(){
		polygons = new polygon3D[96];
		
		double theta = Math.PI/12;
		
		vector[] v1 = new vector[24];
		vector[] v2 = new vector[24];
		
		for(int i = 0; i < 24; i++){
			v1[i] = put(0.004*Math.cos(i*theta),0.032,  0.004*Math.sin(i*theta));
		}
		
		for(int i = 0; i < 24; i++){
			v2[i] = put(0.004*Math.cos(i*theta), -0.003, 0.004*Math.sin(i*theta));
		}
		
		//calculate intensity per vertex
		float[] intensity  = new float[25];
		vector lightDirection = new vector(0,0,0);
		vector vertexNormal = new vector(0,0,0);
		vector pillarCenter = put(0,0.032,0);
		for(int i = 0; i < 24; i++){
			vertexNormal.set(v1[i]);
			vertexNormal.subtract(pillarCenter);
			vertexNormal.unit();
			lightDirection.set(0,0,0.02f);
			lightDirection.subtract(v1[i]);
			lightDirection.unit();
			intensity[i] = (19 + vertexNormal.dot(lightDirection)*16)*2;
			
		}
		intensity[24] = intensity[0];
		
		
		
		//create  lightmap1 
		byte[] lightmap = new byte[512 * 2];
		for(int i = 0; i < 512; i++){
			float  intensitySpecturmIndex = (float)i/(512f/24);
			float I1 = intensity[(int)intensitySpecturmIndex];
			float I2 = intensity[(int)intensitySpecturmIndex + 1];
			lightmap[i] = (byte)(I1 + (intensitySpecturmIndex - (int)intensitySpecturmIndex)*(I2 - I1));

			lightmap[i + 512] = lightmap[i];
		}
		
		int lightmapIndex1 = 0;
		
		for(int i = 2; i < mainThread.lightMapTextures.length; i++){
			if(mainThread.lightMapTextures[i] == null){
				mainThread.lightMapTextures[i] = lightmap;
				mainThread.lightMapTexturesInfo[i] = new int[]{9, 511, 1};
				lightmapIndex1 = i;
				break;
			}
		}
		
		byte[] lightmap2 = new byte[512 * 2];
		for(int i = 0; i < lightmap2.length; i++)
			lightmap2[i] = (byte)(lightmap[i] -5);
		
		int lightmapIndex2 = 0;
		
		for(int i = 2; i < mainThread.lightMapTextures.length; i++){
			if(mainThread.lightMapTextures[i] == null){
				mainThread.lightMapTextures[i] = lightmap2;
				mainThread.lightMapTexturesInfo[i] = new int[]{9, 511, 1};
				lightmapIndex2 = i;
				break;
			}
		}
		
		
		
		
		for(int i = 0; i < 24; i ++){
			vector t = v1[(i+1)%24].myClone();
			t.subtract(v1[i]);
			
			vector t1 = v1[i].myClone();
			t1.add(t, -i);
						
			vector t2 = v1[(i+1)%24].myClone();
			t2.add(t, -i);

			vector t3 = v2[i].myClone();
			t3.add(t, -i);
			
			
			polygons[i] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, t1, t2, t3, mainThread.textures[13], 1f/24,1.5f,9);
			polygons[i].lightMapTextureIndex = lightmapIndex1;
			
		
			
		}
		
		for(int i = 0; i < 24; i++){
			v1[i] = put(0.004*Math.cos(i*theta),-0.003,  0.004*Math.sin(i*theta));
		}
		
		for(int i = 0; i < 24; i++){
			v2[i] = put(0.004*Math.cos(i*theta), -0.0295, 0.004*Math.sin(i*theta));
		}
		
		for(int i = 0; i < 24; i ++){
			vector t = v1[(i+1)%24].myClone();
			t.subtract(v1[i]);
			
			vector t1 = v1[i].myClone();
			t1.add(t, -i);
						
			vector t2 = v1[(i+1)%24].myClone();
			t2.add(t, -i);

			vector t3 = v2[i].myClone();
			t3.add(t, -i);
			
			
			polygons[i + 24] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, t1, t2, t3, mainThread.textures[12], 1f/24,1f,9);
			polygons[i + 24].lightMapTextureIndex = lightmapIndex1;
		}
		
		for(int i = 0; i < 24; i++){
			v1[i] = put(0.005*Math.cos(i*theta),-0.0295,  0.005*Math.sin(i*theta));
		}
		
		for(int i = 0; i < 24; i++){
			v2[i] = put(0.005*Math.cos(i*theta), -0.031, 0.005*Math.sin(i*theta));
		}
		
		for(int i = 0; i < 24; i ++){
			vector t = v1[(i+1)%24].myClone();
			t.subtract(v1[i]);
			
			vector t1 = v1[i].myClone();
			t1.add(t, -i);
						
			vector t2 = v1[(i+1)%24].myClone();
			t2.add(t, -i);

			vector t3 = v2[i].myClone();
			t3.add(t, -i);
			
			
			polygons[i + 48] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, t1, t2, t3, mainThread.textures[17], 1f/3,1f,9);
			polygons[i + 48].lightMapTextureIndex = lightmapIndex1;
		}
		
		for(int i = 0; i < 24; i++){
			v1[i] = put(0.004*Math.cos(i*theta),-0.0295,  0.004*Math.sin(i*theta));
		}
		
		for(int i = 0; i < 24; i++){
			v2[i] = put(0.005*Math.cos(i*theta),-0.0295,  0.005*Math.sin(i*theta));
		}
		
		for(int i = 0; i < 24; i ++){
			vector t = v1[(i+1)%24].myClone();
			t.subtract(v1[i]);
			
			vector t1 = v1[i].myClone();
			t1.add(t, -i);
						
			vector t2 = v1[(i+1)%24].myClone();
			t2.add(t, -i);

			vector t3 = v2[i].myClone();
			t3.add(t, -i);
			
			
			polygons[i + 72] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, t1, t2, t3, mainThread.textures[17], 1f/3,1f,9);
			polygons[i + 72].lightMapTextureIndex = lightmapIndex2;
			//vector temp = polygons[i].realCentre.myClone();		
			//polygons[i + 72].diffuse_I =(int)intensity[i];
		}
		
		
		for(int i = 0; i < polygons.length; i++)
			polygons[i].max_dx = 64;
		
		
		lightSources.ls.set(0,-1,0);
	
		
	}
	
	
}
