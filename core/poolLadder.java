package core;
public class poolLadder extends solidObject{
	
	public int textureIndex;
	public int faceLeft = 0;
	public int faceRight = 1;
	public int direction;
	
	public poolLadder(vector origin, int textureIndex, int direction){
		//define the origin point in object space
		start = origin.myClone();
		centre = origin.myClone();
		tempCentre = origin.myClone();
		this.textureIndex = textureIndex;
			
		//define axis in object space
		iDirection = new vector(0.25f/2,0,0);
		jDirection = new vector(0,0.25f/2,0);
		kDirection = new vector(0,0,0.25f/2);
		
		this.direction = direction;
		
		//create bounding box
		makeBoundary(0.01f, 0.03f, 0.018f);
				
		//create polygons 
		makePolygons();
		
		//drawBoundary = true;
		
		enableOcclusionCulling = false;
	}
	
	public void makePolygons(){
		polygons = new polygon3D[192*2];
		
		
		double theta = Math.PI/12;
		
		vector normal;
		
		int angle = 0;
		
		int index = 0;
		
		vector[] v1 = new vector[24];
		vector[] v2 = new vector[24];
		
		vector s = start.myClone();
		vector i_ = iDirection.myClone();
		vector j_ = jDirection.myClone();
		vector k_ = kDirection.myClone();
		
		for(int j = 0; j < 2; j++){
			if(j == 1){
				index = 192;
				start.set(s);
				iDirection.set(i_);
				jDirection.set(j_);
				kDirection.set(k_);
				start = put(0,0, 0.015f);
			}
			
			
			for(int i = 0; i < 24; i++){
				v1[i] = put(0.0006*Math.cos(i*theta),0.008,  0.0006*Math.sin(i*theta));
			}
			
			for(int i = 0; i < 24; i++){
				v2[i] = put(0.0006*Math.cos(i*theta), -0.01, 0.0006*Math.sin(i*theta));
			}
			
			
			for(int i = 0; i < 24; i ++){
				polygons[i + index] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, new vector(0,0,0), new vector(0,0,0), new vector(0,0,0), mainThread.textures[textureIndex],0,0,13);
				polygons[i + index].normals = new vector[4];
				
				normal = polygons[i+index].vertex3D[0].myClone();
				normal.subtract(put(0, 0.008, 0));
				normal.unit();
				polygons[i+index].normals[0] = normal;
				
				normal = polygons[i+index].vertex3D[1].myClone();
				normal.subtract(put(0, 0.008, 0));
				normal.unit();
				polygons[i+index].normals[1] = normal;
				
				normal = polygons[i+index].vertex3D[2].myClone();
				normal.subtract(put(0, -0.01, 0));
				normal.unit();
				polygons[i+index].normals[2] = normal;
				
				normal = polygons[i+index].vertex3D[3].myClone();
				normal.subtract(put(0, -0.01, 0));
				normal.unit();
				polygons[i+index].normals[3] = normal;
			}
			
			start = put(0, 0.008, 0);
			
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
				v1[i] = put(0.0006*Math.cos(i*theta),0.002,  0.0006*Math.sin(i*theta));
			}
			
			for(int i = 0; i < 24; i ++){
				polygons[i + 24 + index] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, new vector(0,0,0), new vector(0,0,0), new vector(0,0,0), mainThread.textures[textureIndex],0,0,13);
				polygons[i + 24 + index].normals = new vector[4];
				
				normal = polygons[i + 24 +index].vertex3D[0].myClone();
				normal.subtract(put(0, 0.002, 0));
				normal.unit();
				polygons[i + 24 +index].normals[0] = normal;
				
				normal = polygons[i + 24+index].vertex3D[1].myClone();
				normal.subtract(put(0, 0.002, 0));
				normal.unit();
				polygons[i + 24 +index].normals[1] = normal;
				
				
				polygons[i + 24 +index].normals[2] = polygons[i +index].normals[1].myClone();
				
				
				polygons[i + 24 +index].normals[3] = polygons[i +index].normals[0].myClone();
			}
			
			start = put(0, 0.002, 0);
			
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
				polygons[i + 48 + index] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, new vector(0,0,0), new vector(0,0,0), new vector(0,0,0), mainThread.textures[textureIndex],0,0,13);
				polygons[i + 48 + index].normals = new vector[polygons[i].vertex3D.length];
				
				normal = polygons[i + 48 +index].vertex3D[0].myClone();
				normal.subtract(put(0, 0.002, 0));
				normal.unit();
				polygons[i + 48 +index].normals[0] = normal;
				
				normal = polygons[i + 48+index].vertex3D[1].myClone();
				normal.subtract(put(0, 0.002, 0));
				normal.unit();
				polygons[i + 48 +index].normals[1] = normal;
				
				
				polygons[i + 48 +index].normals[2] = polygons[i + 24 +index].normals[1].myClone();
				
				
				polygons[i + 48 +index].normals[3] = polygons[i + 24 +index].normals[0].myClone();
			}
			
			start = put(0, 0.002, 0);
			
			if(direction == faceLeft)
				angle = 35;
			if(direction == faceRight)
				angle = 325;
			
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
				polygons[i + 72 + index] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, new vector(0,0,0), new vector(0,0,0), new vector(0,0,0), mainThread.textures[textureIndex],0,0,13);
				polygons[i + 72 + index].normals = new vector[polygons[i].vertex3D.length];
				
				normal = polygons[i + 72 +index].vertex3D[0].myClone();
				normal.subtract(put(0, 0.002, 0));
				normal.unit();
				polygons[i + 72 +index].normals[0] = normal;
				
				normal = polygons[i + 72+index].vertex3D[1].myClone();
				normal.subtract(put(0, 0.002, 0));
				normal.unit();
				polygons[i + 72 +index].normals[1] = normal;
				
				
				polygons[i + 72 +index].normals[2] = polygons[i + 48 +index].normals[1].myClone();
				
				
				polygons[i + 72 +index].normals[3] = polygons[i + 48 +index].normals[0].myClone();
			}
			
			start = put(0, 0.002, 0);
			
			
			if(direction == faceLeft)
				angle = 35;
			if(direction == faceRight)
				angle = 325;
			
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
				polygons[i + 96 + index] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, new vector(0,0,0), new vector(0,0,0), new vector(0,0,0), mainThread.textures[textureIndex],0,0,13);
				polygons[i + 96 + index].normals = new vector[polygons[i].vertex3D.length];
				
				normal = polygons[i + 96 +index].vertex3D[0].myClone();
				normal.subtract(put(0, 0.002, 0));
				normal.unit();
				polygons[i + 96 +index].normals[0] = normal;
				
				normal = polygons[i + 96+index].vertex3D[1].myClone();
				normal.subtract(put(0, 0.002, 0));
				normal.unit();
				polygons[i + 96 +index].normals[1] = normal;
				
				
				polygons[i + 96 +index].normals[2] = polygons[i + 72 +index].normals[1].myClone();
				
				
				polygons[i + 96 +index].normals[3] = polygons[i + 72 +index].normals[0].myClone();
			}
			
			start = put(0, 0.002, 0);
			

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
				polygons[i + 120 + index] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, new vector(0,0,0), new vector(0,0,0), new vector(0,0,0), mainThread.textures[textureIndex],0,0,13);
				polygons[i + 120 + index].normals = new vector[polygons[i].vertex3D.length];
				
				normal = polygons[i + 120 +index].vertex3D[0].myClone();
				normal.subtract(put(0, 0.002, 0));
				normal.unit();
				polygons[i + 120 +index].normals[0] = normal;
				
				normal = polygons[i + 120+index].vertex3D[1].myClone();
				normal.subtract(put(0, 0.002, 0));
				normal.unit();
				polygons[i + 120 +index].normals[1] = normal;
				
				
				polygons[i + 120 +index].normals[2] = polygons[i + 96 +index].normals[1].myClone();
				
				
				polygons[i + 120 +index].normals[3] = polygons[i + 96 +index].normals[0].myClone();
			}
			
			start = put(0, 0.002, 0);
			
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
				v1[i] = put(0.0006*Math.cos(i*theta),0.014,  0.0006*Math.sin(i*theta));
			}
			
			for(int i = 0; i < 24; i ++){
				polygons[i + 144 + index] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, new vector(0,0,0), new vector(0,0,0), new vector(0,0,0), mainThread.textures[textureIndex],0,0,13);
				polygons[i + 144 + index].normals = new vector[polygons[i].vertex3D.length];
				
				normal = polygons[i + 144 +index].vertex3D[0].myClone();
				normal.subtract(put(0, 0.014, 0));
				normal.unit();
				polygons[i + 144 +index].normals[0] = normal;
				
				normal = polygons[i + 144+index].vertex3D[1].myClone();
				normal.subtract(put(0, 0.014, 0));
				normal.unit();
				polygons[i + 144 +index].normals[1] = normal;
				
				
				polygons[i + 144 +index].normals[2] = polygons[i + 120 +index].normals[1].myClone();
				
				
				polygons[i + 144 +index].normals[3] = polygons[i + 120 +index].normals[0].myClone();
			}
			
			start = put(0, 0.014, 0);
			
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
				v1[i] = put(0.0006*Math.cos(i*theta),0.0144,  0.0006*Math.sin(i*theta));
			}
			
			for(int i = 0; i < 24; i ++){
				polygons[i + 168 + index] = new polygon3D(new vector[]{v1[i],v1[(i+1)%24],v2[(i+1)%24],v2[i]}, new vector(0,0,0), new vector(0,0,0), new vector(0,0,0), mainThread.textures[textureIndex],0,0,13);
				polygons[i + 168 + index].normals = new vector[polygons[i].vertex3D.length];
				
				normal = polygons[i + 168 +index].vertex3D[0].myClone();
				normal.subtract(put(0, 0.0144, 0));
				normal.unit();
				polygons[i + 168 +index].normals[0] = normal;
				
				normal = polygons[i + 168+index].vertex3D[1].myClone();
				normal.subtract(put(0, 0.0144, 0));
				normal.unit();
				polygons[i + 168 +index].normals[1] = normal;
				
				
				polygons[i + 168 +index].normals[2] = polygons[i + 144 +index].normals[1].myClone();
				
				
				polygons[i + 168 +index].normals[3] = polygons[i + 144 +index].normals[0].myClone();
			}
		}
		
		
	}
	
	
	
	
}
