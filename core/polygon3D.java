package core;
import java.awt.*;

public class polygon3D {
	//The vertex of the polygon with the respect of the world/camera coordinate
	public vector[] vertex3D, tempVertex;
	
	//The vertex of the polygon after clipping
	public vector[] vertex2D;
	
	//per vertex normal vectors
	public vector[] normals;
	
	//per vertex reflection vectors
	public vector[] reflections;
	
	//the normal of the polygon with the respect of the world/camera coordinate
	public vector realNormal, normal;
	
	//the centre of the polygon with the respect of the world/camera coordinate
	public vector realCentre, centre;
	
	//The number of vertex
	public int L;
	
	// A rough 2D boundary of the polygon on the screen
	public Rectangle bound;
	public float xMin, yMin, xMax, yMax;
	
	//whether the polygon is completely bounded by the screen
	public boolean withinViewScreen;
	
	//These 3 vectors map the 3 corners of the texture to the world coordinate
	public vector origin, rightEnd, bottomEnd;
	
	//The texture that bonds to this polygon
	public texture myTexture;
	
	//Information about the texture
	public int textureWidth, textureHeight, heightMask, widthMask, widthBits, heightBits;
	
	//The size of one texel
	public float textureScaleX, textureScaleY;
	
	//the default index of top and bottom scanline of the polygon
	public int start = mainThread.screen_h-1, end = 0;
	
	//some variables involved in texture mapping
	//sorry if the names dont make any sense
	public double A_offset, B_offset, C_offset;
	
	//the alpha value of the polygon
	public int alpha = -1;
		
	//the number of times texture repeats itself along the polygon
	public float scaleX, scaleY;
	
	//A pool of vectors which will be used for vector arithmetic
	public static vector 
		tempVector1 = new vector(0,0,0),
		tempVector2 = new vector(0,0,0),
		tempVector3 = new vector(0,0,0),
		tempVector4 = new vector(0,0,0);
	
	//This variable indicates whether the polygon is parallel to the X-Z plane in world coordinate.
	public boolean faceVerticalPolygon;
	
	//whether the polygon is visible
	public boolean visible;
	
	//number of vertices are behind of the clip plane
	public int  numberOfVerticesBehindClipPlane;
	
	//the amount of vertex after clipping
	public  int visibleCount;
	
	//type of the polygon
	public int type; 
	
	//the diffuse/ambient intensity of this polygon
	public int diffuse_I;
	public int Ambient_I = 28;  //the default ambient intensity is 28
	public int reflectance = 20;
	
	
	//default light source
	public vector lightSource = lightSources.ls;
	
	//the color of polygon if it is defined as soild 
	public int color;
	
	//light map texture  for this polygon
	public int lightMapTextureIndex;
	
	//max texel change rate in x direction;
	public int max_dx = 512;
	
	//define fog which the polygon is enclosed in
	public fog fogVolume;

	

	
	
	//Constuctor of the polygon class, it will only accept convex polygons	
	public polygon3D(vector[] vertex3D, vector origin,  vector  rightEnd, vector bottomEnd,  texture myTexture, float scaleX, float scaleY, int type){
		this.type = type;
		this.vertex3D = vertex3D;
		this.myTexture = myTexture;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		L = vertex3D.length;
		bound = new Rectangle(0,0,0,0);
		diffuse_I = 31;
		
		//set the tempVertex to the vertex3D
		tempVertex = new vector[L];
		for(int i = 0; i < L; i++){
			tempVertex[i] = new vector(0,0,0);
			tempVertex[i].set(vertex3D[i]);
		}
		
		//find normal vector of the polygon (in world coordinate)
		tempVector1.set(tempVertex[1]);
		tempVector1.subtract(tempVertex[0]);
		tempVector2.set(tempVertex[2]);
		tempVector2.subtract(tempVertex[1]);
		realNormal = tempVector1.cross(tempVector2);
		realNormal.unit();
		normal = new vector(0,0,0);
		normal.set(realNormal);
		
		//check whether this polygon is parallel to the X-Z plane		
		if(Math.abs(normal.y) > 0.999999)
			faceVerticalPolygon = true;
		else
			faceVerticalPolygon = false;
		
		//find centre of the polygon (in world coordinate)
		realCentre = new vector(0,0,0);
		for(int i = 0; i < tempVertex.length; i++)
			realCentre.add(tempVertex[i]);
		realCentre.scale(1.0f/tempVertex.length);
		centre = new vector(0,0,0);
		centre.set(realCentre);
		
		if(origin != null){
			this.origin = origin.myClone();
			this.rightEnd = rightEnd.myClone();
			this.bottomEnd = bottomEnd.myClone();
		}
		
		//get the texture information if the polygon is bonded with a texture
		if(myTexture != null){
			
			textureWidth = myTexture.width;
			textureHeight = myTexture.height;
			heightMask = myTexture.heightMask;
			widthMask = myTexture.widthMask;
			widthBits = myTexture.widthBits;
			heightBits = myTexture.heightBits;
			//bigHeight = textureHeight << 21;
		
			//find the size of one texel in the world coordinate
			tempVector1.set(origin);
			tempVector1.subtract(rightEnd);
			float l = tempVector1.getLength();
			textureScaleX = l/myTexture.width;

			tempVector1.set(origin);
			tempVector1.subtract(bottomEnd);
			l = tempVector1.getLength();
			textureScaleY = l/myTexture.height;
			
			textureScaleX = textureScaleX/scaleX;
			textureScaleY = textureScaleY/scaleY;
		}
		
		//init vertex2D, notice that the size of vertex2D is bigger than vertex3D, because after clipping
		//it is possilbe to generate one more vertex for the polygon.
		vertex2D = new vector[L+1];
		for(int i = 0; i < L+1; i++)
			vertex2D[i] = new vector(0,0,0);
		
		//for the same reason, the size of reflection vectors array is L + 1
		reflections = new vector[L+1];
		for(int i = 0; i < L+1; i++)
			reflections[i] = new vector(0,0,0);
		
		//find the initial diffuse intensity of this polygon
		findDiffuse();
	}
	
	//update this polygon based on camera movement in each frame
	public void update(){		
		
		//back face culling
		tempVector1.set(camera.position);
		tempVector1.subtract(realCentre);
		if(tempVector1.dot(realNormal) <= 0){
			visible = false;
			
			return;
		}
		
		//frustum culling
		if(realNormal.dot(camera.view_Direction) > 0.7){
			visible = false;
			return;
		
		}
		
		
		//update vertex according to camera orientation
		float x = 0,y = 0, z = 0, 
		camX = camera.position.x, camY = camera.position.y, camZ = camera.position.z,
		sinXZ = gameData.sin[camera.XZ_angle], 
		cosXZ = gameData.cos[camera.XZ_angle],
		sinYZ = gameData.sin[camera.YZ_angle], 
		cosYZ = gameData.cos[camera.YZ_angle];
		
		for(int i = 0; i < L; i++){
			//shifting
			x = vertex3D[i].x - camX;
		 	y = vertex3D[i].y - camY;
			z = vertex3D[i].z - camZ;
			
			//rotating
			tempVertex[i].x = cosXZ*x - sinXZ*z;
			tempVertex[i].z = sinXZ*x + cosXZ*z;
			
			z = tempVertex[i].z;
			
			tempVertex[i].y = cosYZ*y - sinYZ*z;
			tempVertex[i].z = sinYZ*y + cosYZ*z;
		}
		
		//find the number of vertices that are behind  clip plane
		numberOfVerticesBehindClipPlane = 0;
		for(int i = 0; i < L; i++){
			if(tempVertex[i].z <= 0.001){
				numberOfVerticesBehindClipPlane++;
			}
		}
		
		//if all vertices
		if(numberOfVerticesBehindClipPlane == L){
			visible = false;
			return;
		}
		
		if(numberOfVerticesBehindClipPlane ==0){
			visibleCount = L;
			
			if(type == 13 || type == 14){
				for(int i =0; i < L; i ++){
					vertex2D[i].set(tempVertex[i]);
					vertex2D[i].updateLocation();
					tempVector1.set(camera.position);
					
					tempVector2.set(normals[i]);
					tempVector1.subtract(vertex3D[i]);
					
					tempVector1.unit();
					tempVector2.scale(tempVector1.dot(tempVector2)*2);
					tempVector2.subtract(tempVector1);
					reflections[i].set(tempVector2);
				}
			}else{
				for(int i =0; i < L; i ++){
					vertex2D[i].set(tempVertex[i]);
					vertex2D[i].updateLocation();
				}
			}
		}else{
			if(type == 13 || type ==14){
				findClipping_environmentMappedPolygon();
			}else{
				findClipping();
			}
		}
		
	
		//Find A rough 2D rectangular boundary of the polygon.
		xMax = vertex2D[0].screenX;
		xMin = xMax;
		yMax = vertex2D[0].screenY;
		yMin = yMax;
		for(int i = 1; i < visibleCount; i++){
			xMax = Math.max(xMax, vertex2D[i].screenX);
			xMin = Math.min(xMin, vertex2D[i].screenX);
			yMax = Math.max(yMax, vertex2D[i].screenY);
			yMin = Math.min(yMin, vertex2D[i].screenY);
		}
		bound.setLocation((int)xMin,(int)yMin);
		bound.setSize((int)(xMax-xMin + 2), (int)(yMax-yMin + 1));
		
		//Test whether the rectangle intersects the screen.
		visible = camera.screen.intersects(bound);
		

		
		if(visible){
			//update normal and center for boundary polygons
			if(type %4 ==0){
				//find normal vector (in camera coordinate)
				//the vector is calculated from the cross product of 2 neighbor edges.
				//since only the direction is important, it will not be normalized
				normal.set(realNormal);
				normal.rotate_XZ(camera.XZ_angle);
				normal.rotate_YZ(camera.YZ_angle);
				
				//find centre (in camera coordinate)
				//the vector is calculated by averaging all the vertex of the polygon
				centre.reset();
				for(int i = 0; i < L; i++)
					centre.add(tempVertex[i]);
				centre.scale(1.0f/L);
			}
			
			//test whether the polygon is completely inside the screen area
			withinViewScreen = camera.screen.contains(xMin, yMin) && camera.screen.contains(xMax, yMax);
		}
	}
	
	
	
	
	//find diffuse intensity of this polygon
	public void findDiffuse(){
		//skybox have fixed diffuse intensity 
		if(type == 1){
			return;
		}
		
		//calculate the diffuse intensity from the light source	
		double I = realNormal.dot(-lightSource.x, -lightSource.y, -lightSource.z);
		diffuse_I = Ambient_I + (int)(I*reflectance);

	}
	
	//clipping
	public  void findClipping(){
		visibleCount = 0;
		//the clipping algorithm iterate through all the vertex of the polygons, if it finds
		//a vertex which is behind the clipping plane(z = 0.001), then generate 2 new vertex on the
		//clipping plane
		
		for(int i = 0; i < L; i++){
			if(tempVertex[i].z >= 0.001){
				vertex2D[visibleCount].set(tempVertex[i]);
				vertex2D[visibleCount].updateLocation();
				visibleCount++;
			} else{
				int index = (i+L - 1)%L;
				if(tempVertex[index].z >= 0.001005){
					approximatePoint(visibleCount, tempVertex[i], tempVertex[index]);
					visibleCount++;
				}
				index = (i+1)%L;
				if(tempVertex[index].z >= 0.001005){
					approximatePoint(visibleCount, tempVertex[i], tempVertex[index]);
					visibleCount++;
				}
			}
		}
	}
	
	//find the approximate projection point on the clipping plane
	public  void approximatePoint(int index, vector behindPoint, vector frontPoint){
		tempVector1.set(frontPoint.x - behindPoint.x, frontPoint.y - behindPoint.y, frontPoint.z - behindPoint.z);
		tempVector1.scale((frontPoint.z- 0.001f)/tempVector1.z);
		vertex2D[index].set(frontPoint.x, frontPoint.y, frontPoint.z);
		vertex2D[index].subtract(tempVector1);
		vertex2D[index].updateLocation();
	}
	
	//clipping for environment mapped polygons, the need special treatment because the reflection vector need to be recalculated after clipping
	public void findClipping_environmentMappedPolygon(){
		visibleCount = 0;
		
		for(int i = 0; i < L; i++){
			if(tempVertex[i].z >= 0.001){
				vertex2D[visibleCount].set(tempVertex[i]);
				vertex2D[visibleCount].updateLocation();
				tempVector1.set(camera.position);
				tempVector2.set(normals[i]);
				tempVector1.subtract(vertex3D[i]);
				tempVector1.unit();
				tempVector2.scale(tempVector1.dot(tempVector2)*2);
				tempVector2.subtract(tempVector1);
				reflections[visibleCount].set(tempVector2);
				visibleCount++;
			} else{
				int index = (i+L - 1)%L;
				if(tempVertex[index].z >= 0.001005){
					approximatePoint_environmentMappedPolygon(visibleCount, i, index );
					visibleCount++;
				}
				index = (i+1)%L;
				if(tempVertex[index].z >= 0.001005){
					approximatePoint_environmentMappedPolygon(visibleCount, i, index);
					visibleCount++;
				}
			}
		}
	}
	
	public void approximatePoint_environmentMappedPolygon(int index, int behind,  int front){
		//calculate the reflection vectors for the vertices behind and in font of the clip plane
		vector reflectionVectorBehind, reflectionVectorFront;
		
		tempVector1.set(camera.position);
		tempVector2.set(normals[behind]);
		tempVector1.subtract(vertex3D[behind]);
		tempVector1.unit();
		tempVector2.scale(tempVector1.dot(tempVector2)*2);
		tempVector2.subtract(tempVector1);
		tempVector3.set(tempVector2);
		reflectionVectorBehind = tempVector3;
		
		tempVector1.set(camera.position);
		tempVector2.set(normals[front]);
		tempVector1.subtract(vertex3D[front]);
		tempVector1.unit();
		tempVector2.scale(tempVector1.dot(tempVector2)*2);
		tempVector2.subtract(tempVector1);
		tempVector4.set(tempVector2);
		reflectionVectorFront = tempVector4;
		
		
		//do normal clipping for the vertex
		vector behindPoint = tempVertex[behind];
		vector frontPoint = tempVertex[front];
		tempVector1.set(frontPoint.x - behindPoint.x, frontPoint.y - behindPoint.y, frontPoint.z - behindPoint.z);
		tempVector1.scale((frontPoint.z- 0.001f)/tempVector1.z);
		vertex2D[index].set(frontPoint.x, frontPoint.y, frontPoint.z);
		vertex2D[index].subtract(tempVector1);
		vertex2D[index].updateLocation();
		
		//find the reflection vector on the clipping plane
		tempVector1.set(reflectionVectorFront);
		tempVector1.subtract(reflectionVectorBehind);
		tempVector1.scale((0.001f - behindPoint.z)/(frontPoint.z - behindPoint.z));
		reflections[index].set(reflectionVectorBehind);
		reflections[index].add(tempVector1);
	}
	
	
	
	//find the normal vector of this polygon in world coordinate
	/*public void findRealNormal(){
		tempVector1.set(vertex3D[1]);
		tempVector1.subtract(vertex3D[0]);
		tempVector2.set(vertex3D[2]);
		tempVector2.subtract(vertex3D[1]);
		realNormal = tempVector1.cross(tempVector2);
		realNormal.unit();
	}*/
	
	public void draw(){
		//send this polygon to rasterizer
		if(visible){
			mainThread.polygonCount++;
			rasterizer.rasterize(this);
		}
	}
}
