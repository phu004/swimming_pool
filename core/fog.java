package core;

//fog volume defined by cuboid boundary
public class fog {
	
	//color of the fog
	public int color;
	
	//this value define how fast objects fade in the fog
	public float fadeRate;
	
	//center point of the fog volume
	public vector start;
	
	public vector fogCenter;
	
	//the reference axis in world coordinate
	public vector iDirection, jDirection, kDirection;
	
	//cuboid boundary of the fog
	public polygon3D[] boundary;
	
	public float outsideVolumeFadeRate = 0.1f;
	
	public boolean cameraInside;
	public vector temp = new vector(0,0,0);
	
	public int[] offScreenBuffer;

	
	public fog(vector start, vector iDirection, vector jDirection, vector kDirection, float l, float h, float w, int color, float fadeRate, int[] offScreenBuffer){
		this.start = start;
		this.iDirection = iDirection;
		this.jDirection = jDirection;
		this.kDirection = kDirection;
		
		fogCenter = start.myClone();
		
		this.offScreenBuffer = offScreenBuffer;
		
		boundary = new polygon3D[6];
		vector[] front = new vector[]{put(l, h, w), put(-l, h, w), put(-l, -h, w), put(l, -h, w)};
		boundary[0] = new polygon3D(front, front[0], front[1], front[3], null, 0,0,11);
		vector[] right = new vector[]{put(l, h, -w), put(l, h, w), put(l, -h, w), put(l, -h, -w)};
		boundary[1] = new polygon3D(right, right[0], right[1], right[3], null, 0,0,11);
		vector[] back = new vector[]{put(-l, h, -w), put(l, h, -w), put(l, -h, -w), put(-l, -h, -w)};
		boundary[2] = new polygon3D(back, back[0], back[1], back[3], null, 0,0,11);
		vector[] left = new vector[]{put(-l, h, w), put(-l, h, -w), put(-l, -h, -w), put(-l, -h, w)};
		boundary[3] = new polygon3D(left, left[0], left[1], left[3], null, 0,0,11);
		vector[] top = new vector[]{put(-l, h, w), put(l, h, w), put(l, h, -w), put(-l, h, -w)};
		boundary[4] = new polygon3D(top, top[0], top[1], top[0], null, 0,0,11);
		vector[] buttom = new vector[]{put(-l, -h, -w), put(l, -h, -w), put(l, -h, w), put(-l, -h, w)};
		boundary[5] = new polygon3D(buttom, buttom[0], buttom[1], buttom[3], null, 0,0,11);
		
		this.color = color;
		this.fadeRate = fadeRate;
		
		for(int i = 0; i < boundary.length; i++)
			boundary[i].fogVolume = this;
	
		
	}
	
	/*public fog(vector start, vector iDirection, vector jDirection, vector kDirection, int color, float fadeRate, int[] offScreenBuffer,fog secondaryFogVolume){
		this.start = start;
		this.iDirection = iDirection.myClone();
		this.jDirection = jDirection.myClone();
		this.kDirection = kDirection.myClone();
		
		fogCenter = start.myClone();
		
		this.offScreenBuffer = offScreenBuffer;
		
		boundary = new polygon3D[52];
		
		
		double theta = Math.PI/12;
		vector[] v1 = new vector[24];
		vector[] v2 = new vector[24];
		double r1 = 0.0003;
		double r2 = 0.003;
		double h1 = -0.01;
		
		for(int i = 0; i < 24; i++){
			v1[i] = put(r1*Math.cos(i*theta), 0.005,  r1*Math.sin(i*theta));
		}
		
		for(int i = 0; i < 24; i++){
			v2[i] = put(r2*Math.cos(i*theta), h1, r2*Math.sin(i*theta));
		}
		
		for(int i = 0; i < 24; i ++){
			boundary[i] = new polygon3D(new vector[]{v1[i], v1[(i+1)%24], v2[(i+1)%24], v2[i]}, v1[1], v1[2], v1[3], null, 0,0,11);
		}
		
		vector[] v3 = new vector[24];
		for(int i = 0; i < 24; i++)
			v3[i] = v1[24-1-i];
		
		boundary[24] = new polygon3D(v3, v3[1], v3[2], v3[3], null, 0,0,11);
		boundary[25] = new polygon3D(v2, v2[1], v2[2], v2[3], null, 0,0,11);
		
		
		
		for(int i = boundary.length/2; i < boundary.length; i++){
			polygon3D outerBoundary = boundary[i - boundary.length/2];
			vector[] outerBoundaryVertices = outerBoundary.vertex3D;
			int numberOfVertices = outerBoundaryVertices.length;
			vector[] innerBoundaryVertices = new vector[numberOfVertices];
			for(int j = 0; j < numberOfVertices; j++)
			
				innerBoundaryVertices[numberOfVertices-1-j] = outerBoundaryVertices[j];
			boundary[i] = new polygon3D(innerBoundaryVertices, innerBoundaryVertices[0], innerBoundaryVertices[1], innerBoundaryVertices[3], null, 0,0,13);
		}
		
		
		
		
		for(int i = 0; i < boundary.length; i++){
			boundary[i].fogVolume = this;
			boundary[i].secondaryFogVolume = secondaryFogVolume;
		}
	
		
		
		this.color = color;
		this.fadeRate = fadeRate;
		
		
	}*/
	
	public void update(){
		
		fogCenter.set(start);
		fogCenter.subtract(camera.position);
		fogCenter.rotate_XZ(camera.XZ_angle);
		fogCenter.rotate_YZ(camera.YZ_angle);
		
		
		for(int i = 0; i < boundary.length; i++){
			
			
			boundary[i].update();
		}
		
		cameraInside = true;
		for(int i = 0; i < 6; i++){
			boundary[i].centre.set(boundary[i].realCentre);
			boundary[i].centre.subtract(camera.position);
			boundary[i].centre.rotate_XZ(camera.XZ_angle);
			boundary[i].centre.rotate_YZ(camera.YZ_angle);
		
			boundary[i].normal.set(boundary[i].realNormal);
			boundary[i].normal.rotate_XZ(camera.XZ_angle);
			boundary[i].normal.rotate_YZ(camera.YZ_angle);
			temp.reset();
			temp.subtract(boundary[i].centre);
			if(temp.dot(boundary[i].normal) > 0){
				cameraInside = false;
				break;
			}
		}
	}
	
	//create a arbitrary vertex
	public  vector put(double i, double j, double k){
		vector temp = start.myClone();
		temp.add(iDirection, (float)i);
		temp.add(jDirection, (float)j);
		temp.add(kDirection, (float)k);
		return temp;
	}
	
	
	public void draw(){
		for(int i = 0; i < boundary.length; i++){
			boundary[i].draw();
		}
	}
}
