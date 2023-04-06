package core;
//The rasterizer class will draw any polygon into the screen buffer.
//The texture mapping methods will differ depends on the type of polygon,
//The universal formula for texture mapping is:
//               x = A dot W/C dot W
//               y = B dot W/C dot W
// where    A = V cross O  B = O cross U  C = U cross V,   
//          V, a vector representing the texture's y direction
//          U, a vector representing the texture's x direction
//          O, the origin of the texture 
//          W is the projection length of the texel on the clipping plane
//          x, y is the texture coordinate 
//
//Won't handle z-axis rotation.

public class rasterizer {
	public static int screen_w = mainThread.screen_w;
	public static int screen_h = mainThread.screen_h;
	public static int half_screen_w = mainThread.half_screen_w;
	public static int half_screen_h = mainThread.half_screen_h;
	public static int screen_pixel_count = mainThread.screen_pixel_count;
	
	//2 arrays that define the scan lines of the polygon
	public static int[] xLeft = new int[screen_h], xRight = new int[screen_h];
	
	//2 arrays that define the z depth across the polygon
	public static int[] zLeft = new int[screen_h], zRight = new int[screen_h];
	
	//2 arrays that define the reflections across the polygon
	public static vector[] RLeft = new vector[screen_h], RRight = new vector[screen_h];
	
	//a short array which represent zbuffer
	public static short[] zbuffer;
	
	public static int[] screen;
	
	
	//init Texture coordinate vectors
	public static vector 
	W = new vector(0,0,0),
	O = new vector(0,0,0), 
	V = new vector(0,0,0), 
	U = new vector(0,0,0), 
	A = new vector(0,0,0), 
	B = new vector(0,0,0), 
	C = new vector(0,0,0);
	
	
	//A pool of vectors which will be used for vector arithmetic
	public static vector 
		tempVector1 = new vector(0,0,0),
		tempVector2 = new vector(0,0,0),
		tempVector3 = new vector(0,0,0),
		tempVector4 = new vector(0,0,0);
	
	//the polygon that rasterizer is working on
	public static polygon3D poly;
	
	//these variables will represent their equivalents in the polygon3D class during rasterization
	public static vector[] tempVertex, vertex2D, reflections;
	public static int L, widthMask, heightMask, widthBits, diffuse_I;
	public static double A_offset, B_offset, C_offset;
	
	//the transparency level of the polygon. 
	public static int alpha;
	
	//the amount of vertex after clipping
	public static int visibleCount;
	
	//temporary variables that will be used in texture mapping
	public static double aDotW, bDotW, cDotW, cDotWInverse;
	public static int BigX, BigY, d_x, d_y, k, X1, Y1, BigDx, BigDy, dx, dy, X, Y, textureIndex, temp, temp1, temp2, r,g,b, scale, yOffset, xOffset, textureHeight, x_right, x_left, z_left, z_right, start, end;
	public static short I, variation, zDepth, zDepthAccuracy;
	public static vector dReflection, startReflection, endReflection;
	
	
	//initialize rasteriser 
	public static void init(){
		for(int i = 0; i < screen_h; i++ ){
			RLeft[i] = new vector(0,0,0);
			RRight[i] = new vector(0,0,0);
		}
		
		dReflection = new vector(0,0,0);
		startReflection = new vector(0,0,0);
		endReflection = new vector(0,0,0);
		screen = mainThread.screen;
	}
	
	
	
	//start rasterization
	public static void rasterize(polygon3D polygon){
		poly = polygon;
		L = poly.L;
		widthMask = poly.widthMask;
		heightMask = poly.heightMask;
		textureHeight = poly.textureHeight;
		widthBits = poly.widthBits;
		tempVertex = poly.tempVertex;
		vertex2D = poly.vertex2D;
		alpha = poly.alpha;
		zDepthAccuracy = 15;
		visibleCount = poly.visibleCount;
		
		
		
		//find the vectors which represent the texture coordinate
		if(poly.type != 7)
			findVectorOUV();
		

		//for different polygons, the texture mapping alogrithm will differ depend 
		//on the nature of the polygon in order to optimize rendering
		if(poly.type == 1){
			scanPolygon();
			renderSkybox();
		}else if(poly.type == 2){
			scanPolygon_Zbuffer();
			renderSoildPolygon_fog();
		}else if(poly.type == 3){
			scanPolygon_Zbuffer();
			renderBasic();
		}else if(poly.type == 4){
			scanPolygon_Zbuffer();
			renderWater();
		}else if(poly.type == 5){
			scanPolygon_Zbuffer();
			renderMipmap_compareZ();
		}else if(poly.type == 6){
			scanPolygon_Zbuffer();
			renderMipmap();
		}else if(poly.type == 7 || poly.type == 0){
			scanPolygon_Zbuffer();
			renderSoildPolygon();
		} else if(poly.type == 8){
			scanPolygon_Zbuffer();
			renderGlass1();
		} else if(poly.type == 9){
			scanPolygon_Zbuffer();
			renderMipmap_drawLightMap();
		}else if(poly.type == 10){
			scanPolygon_Zbuffer();
			renderMipmap_fog();
		}else if(poly.type == 11){
			scanPolygon_Zbuffer();
			renderFogVolume_outer();
		}else if(poly.type == 12){
			scanPolygon_Zbuffer();
			renderGlass2();
		}else if(poly.type == 13){
			scanPolygon_Zbuffer_environment();
			renderEnvironmentMappedPolygon();
		}else if(poly.type == 14){
			scanPolygon_Zbuffer_environment();
			renderEnvironmentMappedPolygon_fog();
		}else if(poly.type == 16){
			scanPolygon_Zbuffer();
			renderWater_fog();
		}else if(poly.type == 20){
			scanPolygon_Zbuffer();
			renderGlass3();
		}else if(poly.type == 24){
			scanPolygon_Zbuffer();
			renderGlass3_fog();
		}
	}
	
	//calculate O,U and V
	public static void findVectorOUV(){
		O.set(poly.origin);
		O.subtract(camera.position);
		O.rotate_XZ(camera.XZ_angle);
		O.rotate_YZ(camera.YZ_angle);

		U.set(poly.rightEnd);
		U.subtract(camera.position);
		U.rotate_XZ(camera.XZ_angle);
		U.rotate_YZ(camera.YZ_angle);

		V.set(poly.bottomEnd);
		V.subtract(camera.position);
		V.rotate_XZ(camera.XZ_angle);
		V.rotate_YZ(camera.YZ_angle);
		
		U.subtract(O);
		U.unit();
		U.scale(poly.textureScaleX);

		V.subtract(O);
		V.unit();
		V.scale(poly.textureScaleY);

		
		A.cross(V,O);
		B.cross(O,U);
		C.cross(U,V);
	}
		
	
	public static int ceil(float f) {
	    if (f > 0) {
	        return (int)f + 1;
	    }
	    else {
	       return (int)f;
	    }
	}
	
	public static void scanPolygon(){
		start = (screen_h-1);
		end = 0;
		int temp_ = 0;
		int g = 0;

		for(int i = 0; i < visibleCount; i++){
			vector v1 = vertex2D[i];
			vector v2;
			
			if(i == visibleCount -1 ){
				v2 = vertex2D[0];
			}else{
				v2 = vertex2D[i+1];
			}

			boolean downwards = false;

			//ensure v1.y < v2.y;
			if (v1.screenY> v2.screenY) {
				downwards = true;
				vector temp = v1;
				v1 = v2;
				v2 = temp;
			}
			float dy = v2.screenY - v1.screenY;
			
			// ignore horizontal lines
			if (dy == 0) {
				continue;
			}
			
			
			
			if(poly.withinViewScreen){
				int startY = Math.max(ceil(v1.screenY), 0);
				int endY = Math.min(ceil(v2.screenY) - 1, (screen_h-1));
				if(startY < start )
					start = startY;
				if(endY > end)
					end = endY;
				
				double dx = v2.screenX - v1.screenX;
				g = (int)(dx / dy *2048);
				temp_ = (int)(v1.screenX*2048);

				
				for (int y=startY; y<=endY; y++) {
					if(downwards)
						xLeft[y] = temp_ >> 11;
					else
						xRight[y] = (temp_ >> 11) + 1;
					temp_+=g;
				}
				
				continue;
			}

			int startY = Math.max(ceil(v1.screenY), 0);
			int endY = Math.min(ceil(v2.screenY) - 1, (screen_h-1));

			if(startY < start )
				start = startY;

			if(endY > end)
				end = endY;

			float dx = v2.screenX - v1.screenX;
			float gradient = dx / dy;

			int temp_x, x;
			temp_ = (int)((v1.screenX + (startY - v1.screenY) * gradient) *2048);
			// scan-convert this edge (line equation)
			g = (int)(gradient*2048);

			for (int y=startY; y<=endY; y++) {
				temp_x = temp_>>11;
				temp_+=g;
				// ensure x within view bounds
				x = Math.min(screen_w, Math.max(temp_x, 0));
				if(downwards){
					xLeft[y] = x;
				}else{
					if(x < screen_w && x > 0)
						x++;
					xRight[y] = x ;
				}
			}
		}

	}
	
	
	
	//convert a polygon to scan lines, also find z depth along edges
		public static void scanPolygon_Zbuffer(){
			start = (screen_h-1);
			end = 0; 
			int startX, g, startY, endY, temp_x, startZ, dz, dx;
			float gradient;
		
			for(int i = 0; i < visibleCount; i++){
				vector v1 = vertex2D[i];
				vector v2;
				
				if(i == visibleCount -1 ){
					v2 = vertex2D[0];
				}else{
					v2 = vertex2D[i+1];
				}

				boolean downwards = false;

				//ensure v1.y < v2.y;
				if (v1.screenY> v2.screenY) {
					downwards = true;
					vector temp = v1;
					v1 = v2;
					v2 = temp;
				}
				float dy = v2.screenY - v1.screenY;
				
				// ignore horizontal lines
				if (dy == 0) {
					
					continue;
				}
				
				
				

				startY = Math.max((int)(v1.screenY) + 1, 0);
				endY = Math.min((int)(v2.screenY), (screen_h-1));
				
				

				if(startY < start )
					start = startY;

				if(endY > end)
					end = endY;
					
			
				
				//calculate x increment along this edge
				gradient = (v2.screenX - v1.screenX)* 2048 /dy;
				startX = (int)((v1.screenX *2048) +  (startY - v1.screenY) * gradient);
				g = (int)(gradient);
				
				//calculate 1/z depth increment along this edge
				startZ = (int)(1048576/v1.z);
				dz = (int)((1048576/v2.z - startZ)/dy);
				startZ = (int)(startZ + (startY - v1.screenY)*dz);

				
				
				for (int y=startY; y<=endY; y++) {
					temp_x = startX>>11;
		
					if(downwards){
						xLeft[y] = temp_x;
						zLeft[y] = startZ;
					}else{
						xRight[y] = temp_x+1 ;
						zRight[y] = startZ;
					}
					startX+=g;
					startZ+=dz;			
				}
			}
			
			
			if(!poly.withinViewScreen){
				int x_left, x_right;
				boolean xLeftInView, xRightInView;
				for(int y = start; y <= end; y++){
					x_left = xLeft[y];
					x_right = xRight[y];
					
					xLeftInView = x_left >=0 && x_left < screen_w;
					xRightInView = x_right >0 && x_right <= screen_w;
					
					if(xLeftInView && xRightInView)
						continue;
					
					if(x_left >= screen_w  || x_right <= 0){
						xLeft[y] = 0;
						xRight[y] = 0;
						continue;
					}
					
					dx =  x_right - x_left;
					dz = zRight[y] - zLeft[y];
					
					
					if(!xLeftInView){
						xLeft[y] = 0;
						zLeft[y] = (zLeft[y] + dz /dx * (0 - x_left) ) ;
					}
					
					if(!xRightInView){
						xRight[y] = screen_w;
						zRight[y] = (zRight[y] - dz /dx * (x_right - screen_w));
					}
				}
			}
			
			
		}
	
	//convert a polygon to scan lines,  find z depth along edges, also interpolate reflection vectors.
		public static void scanPolygon_Zbuffer_environment(){
			start = (screen_h-1);
			end = 0; 
			int startX, g, startY, endY, temp_x, startZ, dz, dx;
			float gradient;
			reflections = poly.reflections;
		
			for(int i = 0; i < visibleCount; i++){
				vector v1 = vertex2D[i];
				vector v2;
				
				startReflection.set(reflections[i]);
				
				if(i == visibleCount -1 ){
					v2 = vertex2D[0];
					endReflection.set(reflections[0]);
				}else{
					v2 = vertex2D[i+1];
					endReflection.set(reflections[i+1]);
				}

				boolean downwards = false;

				//ensure v1.y < v2.y;
				if (v1.screenY> v2.screenY) {
					downwards = true;
					vector temp = v1;
					v1 = v2;
					v2 = temp;
					
					temp = startReflection;
					startReflection = endReflection;
					endReflection = temp;
					
				}
				float dy = v2.screenY - v1.screenY;
				
				// ignore horizontal lines
				if (dy == 0) {
				
					continue;
				}
				
				
				
				
					startY = Math.max(ceil(v1.screenY), 0);
					endY = Math.min(ceil(v2.screenY) - 1, (screen_h-1));

					if(startY < start )
						start = startY;

					if(endY > end)
						end = endY;
						
				
					
					//calculate x increment along this edge
					gradient = (v2.screenX - v1.screenX)* 2048 /dy;
					startX = (int)(v1.screenX * 2048 + (startY - v1.screenY) * gradient);
					g = (int)(gradient);
					
					//calculate 1/z depth increment along this edge
					startZ = (int)(1048576/v1.z);
					dz = (int)((1048576/v2.z - startZ)/dy);
					startZ = (int)(startZ + (startY - v1.screenY)*dz);
					
					//calculate the reflection vector increment for this scanline
					dReflection.set(endReflection);
					dReflection.subtract(startReflection);
					dReflection.scale(1f/dy);
					startReflection.add(dReflection, startY - v1.screenY);
					
					

					for (int y=startY; y<=endY; y++) {
						temp_x = startX>>11;
			
						if(downwards){
							xLeft[y] = temp_x;
							zLeft[y] = startZ;
							RLeft[y].set(startReflection);
						}else{
							xRight[y] = temp_x+1;
							zRight[y] = startZ;
							RRight[y].set(startReflection);
						}
						startX+=g;
						startZ+=dz;	
						startReflection.add(dReflection);
					}
				}
			
			
			if(!poly.withinViewScreen){
				int x_left, x_right;
				boolean xLeftInView, xRightInView;
				for(int y = start; y <= end; y++){
					x_left = xLeft[y];
					x_right = xRight[y];
					
					xLeftInView = x_left >=0 && x_left < screen_w;
					xRightInView = x_right >0 && x_right <= screen_w;
					
					if(xLeftInView && xRightInView)
						continue;
					
					if(x_left >= screen_w  || x_right <= 0){
						xLeft[y] = 0;
						xRight[y] = 0;
						continue;
					}
					
					dx =  x_right - x_left;
					dz = zRight[y] - zLeft[y];
					dReflection.set(RRight[y]);
					dReflection.subtract(RLeft[y]);
					
					
					if(!xLeftInView){
						xLeft[y] = 0;
						zLeft[y] = (zLeft[y] + dz /dx * (0 - x_left));
						dReflection.scale(1f/dx * (0 - x_left));
						RLeft[y].add(dReflection);
						
					}
					
					if(!xRightInView){
						xRight[y] = screen_w;
						zRight[y] = (zRight[y] - dz /dx * (x_right - screen_w));
						dReflection.scale(1f/dx * (x_right - screen_w));
						RRight[y].subtract(dReflection);
					}
				}
			}
			
		
		}
	
	//Texture mapper for rendering basic polygons 
	public static void renderBasic(){
		
		int[] Texture = poly.myTexture.Texture; 
		diffuse_I = poly.diffuse_I;
		int[] colorTable = gameData.colorTable[diffuse_I];
		zbuffer = mainThread.zBuffer; 
	
		A_offset = A.x*16;
		B_offset = B.x*16;
		C_offset = C.x*16;
		
		double Aoffset,Boffset,Coffset;
		
		
		
		
		int dz, dx, z;
		
		for(int i = start; i <= end; i++){
			dx = xRight[i] - xLeft[i];
			if(dx == 0)
				continue;
			
			W.set(xLeft[i]-half_screen_w, -i + half_screen_h, vector.Z_length);
			aDotW = A.dot(W);
			bDotW = B.dot(W);
			cDotW = C.dot(W);
			
			//find the texture coordinate for the start pixel of the scanline
			cDotWInverse = 1/cDotW;
			X = (int)(aDotW*cDotWInverse);
			Y = (int)(bDotW*cDotWInverse);
			X1 = X;
			Y1 = Y;
			
			int temp = gameData.screenTable[i];
			int index;
			
			z_left = zLeft[i] ;
			z_right = zRight[i] ;
			dz = (z_right - z_left)/dx;
		
			
			for(int j = xLeft[i]; j < xRight[i]; j+=16){
				X = X1;
				Y = Y1;
				
				index = j + temp;
				if(xRight[i] - j > 15){
					//find the correct texture coordinate every 16 pixels.
					//Use the interpolation values for  pixels in between.
					aDotW+=A_offset;
					bDotW+=B_offset;
					cDotW+=C_offset;
					cDotWInverse = 1/cDotW;
					X1 = (int)(aDotW*cDotWInverse);
					Y1 = (int)(bDotW*cDotWInverse);
					dx = X1 - X;
					dy = Y1 - Y;
					
					
					for( k = 16, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++, z_left+=dz){
						z = z_left >> zDepthAccuracy;
						if(zbuffer[index] < z){
							zbuffer[index] = (short)z;
							textureIndex = (((d_x>>4) + X)&widthMask) + ((((d_y>>4) + Y)&heightMask)<<widthBits);
							screen[index] = colorTable[Texture[textureIndex]];
						}
					}
					
					continue;
				}
				
				int offset = xRight[i] - j;
				Aoffset = A.x*offset;
				Boffset = B.x*offset;
				Coffset = C.x*offset;
	
				aDotW+=Aoffset;
				bDotW+=Boffset;
				cDotW+=Coffset;
				cDotWInverse = 1/cDotW;
				X1 = (int)(aDotW*cDotWInverse);
				Y1 = (int)(bDotW*cDotWInverse);
				dx = X1 - X;
				dy = Y1 - Y;
				

				for( k = offset, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++, z_left+=dz){
					z = z_left >> zDepthAccuracy;
					if(zbuffer[index] < z){
						zbuffer[index] = (short)z;
						textureIndex = (((d_x/offset) + X)&widthMask) + ((((d_y/offset) + Y)&heightMask)<<widthBits);
						screen[index] = colorTable[Texture[textureIndex]];
					}
				}
				
				break;
			}
		}
	}
	
	//Texture mapper for rendering  polygons with mipmaping enabled 
	//The level of mipmap depends on how far away the texel is  from the view point,
	//The z value of each texel is interpolated  between vertices. 
	public static void renderMipmap(){
		int[][] mipmap = poly.myTexture.mipmap; 
		diffuse_I = poly.diffuse_I;
		int[]colorTable = gameData.colorTable[diffuse_I];
		zbuffer = mainThread.zBuffer; 
		int dz, dx, z;
		
		int mipmapIndex = 0;
		int widthBitsMipmap;
		int widthMaskMipmap;
		int heightMaskMipmap;
		
		
	
		A_offset = A.x*16;
		B_offset = B.x*16;
		C_offset = C.x*16;
		
		double Aoffset,Boffset,Coffset;
		
		
		
		

		//if the polygon is perpendicular to the x-z plane (in world coordinate)
		//the z depth will be constant along each scanline;
		if(poly.faceVerticalPolygon){
			for(int i = start; i <= end; i++){
				x_left=xLeft[i];
				x_right=xRight[i];
				dx = x_right - x_left;
				if(dx == 0)
					continue;
				
				W.set(x_left-half_screen_w, -i + half_screen_h, vector.Z_length);
				aDotW = A.dot(W);
				bDotW = B.dot(W);
				cDotW = C.dot(W);
				
				//find the texture coordinate for the start pixel of the scanline
				cDotWInverse = 1/cDotW;
				X = (int)(aDotW*cDotWInverse);
				Y = (int)(bDotW*cDotWInverse);
				X1 = X;
				Y1 = Y;
				
				int temp = gameData.screenTable[i];
				int index;
				
				z_left = zLeft[i] ;
				
				if(z_left > 209715200)
					mipmapIndex = 0;
				else if(z_left > 104857600)
					mipmapIndex = 1;
				else if(z_left > 52428800)
					mipmapIndex = 2;
				else if(z_left > 26214400)
					mipmapIndex = 3;
				else
					mipmapIndex = 4;
				
				
				widthBitsMipmap = widthBits - mipmapIndex;
				widthMaskMipmap = ((widthMask + 1) >> mipmapIndex) - 1;
				heightMaskMipmap = ((heightMask + 1) >> mipmapIndex) - 1;
				
				zDepth = (short)(z_left >> zDepthAccuracy);
				
				for(int j = x_left; j < x_right; j+=16){
					X = X1;
					Y = Y1;
					
					index = j + temp;
					if(x_right - j > 15){
						//find the correct texture coordinate every 16 pixels.
						//Use the interpolation values for the  pixels in between.
						aDotW+=A_offset;
						bDotW+=B_offset;
						cDotW+=C_offset;
						cDotWInverse = 1/cDotW;
						X1 = (int)(aDotW*cDotWInverse);
						Y1 = (int)(bDotW*cDotWInverse);
						dx = X1 - X;
						dy = Y1 - Y;
						
					
						
						for( k = 16, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++){
							if(zbuffer[index] ==0){
								zbuffer[index] = zDepth;
								textureIndex = ((((d_x>>4) + X)>>mipmapIndex)&widthMaskMipmap) + (((((d_y>>4) + Y)>>mipmapIndex)&heightMaskMipmap)<<widthBitsMipmap);
								screen[index] = colorTable[mipmap[mipmapIndex][textureIndex]];
							}
						}
						continue;
					}
					
					int offset = x_right - j;
					Aoffset = A.x*offset;
					Boffset = B.x*offset;
					Coffset = C.x*offset;
		
					aDotW+=Aoffset;
					bDotW+=Boffset;
					cDotW+=Coffset;
					cDotWInverse = 1/cDotW;
					X1 = (int)(aDotW*cDotWInverse);
					Y1 = (int)(bDotW*cDotWInverse);
					dx = X1 - X;
					dy = Y1 - Y;
					
					for( k = offset, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++){
						if(zbuffer[index] == 0){
							zbuffer[index] = zDepth;
							textureIndex = ((((d_x/offset) + X)>>mipmapIndex)&widthMaskMipmap) + (((((d_y/offset) + Y)>>mipmapIndex)&heightMaskMipmap)<<widthBitsMipmap);
							screen[index] = colorTable[mipmap[mipmapIndex][textureIndex]];
						}
					}
					
					break;
				}
			}
				
		}
		
		for(int i = start; i <= end; i++){
			x_left=xLeft[i];
			x_right=xRight[i];
			dx = x_right - x_left;
			if(dx == 0)
				continue;
			
			W.set(x_left-half_screen_w, -i + half_screen_h, vector.Z_length);
			aDotW = A.dot(W);
			bDotW = B.dot(W);
			cDotW = C.dot(W);
			
			//find the texture coordinate for the start pixel of the scanline
			cDotWInverse = 1/cDotW;
			X = (int)(aDotW*cDotWInverse);
			Y = (int)(bDotW*cDotWInverse);
			X1 = X;
			Y1 = Y;
			
			int temp = gameData.screenTable[i];
			int index;
			
			z_left = zLeft[i] ;
			z_right = zRight[i] ;
			dz = (z_right - z_left)/dx;
			
			for(int j = x_left; j < x_right; j+=16){
				X = X1;
				Y = Y1;
				
				index = j + temp;
				if(x_right - j > 15){
					//find the correct texture coordinate every 16 pixels.
					//Use the interpolation values for the  pixels in between.
					aDotW+=A_offset;
					bDotW+=B_offset;
					cDotW+=C_offset;
					cDotWInverse = 1/cDotW;
					X1 = (int)(aDotW*cDotWInverse);
					Y1 = (int)(bDotW*cDotWInverse);
					dx = X1 - X;
					dy = Y1 - Y;
					
					if(z_left > 209715200)
						mipmapIndex = 0;
					else if(z_left > 104857600)
						mipmapIndex = 1;
					else if(z_left > 52428800)
						mipmapIndex = 2;
					else if(z_left > 26214400)
						mipmapIndex = 3;
					else
						mipmapIndex = 4;
					
					
					widthBitsMipmap = widthBits - mipmapIndex;
					widthMaskMipmap = ((widthMask + 1) >> mipmapIndex) - 1;
					heightMaskMipmap = ((heightMask + 1) >> mipmapIndex) - 1;
					
					for( k = 16, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++, z_left+=dz){
						if(zbuffer[index] ==0){
							z = z_left >> zDepthAccuracy;
							zbuffer[index] = (short)z;
							textureIndex = ((((d_x>>4) + X)>>mipmapIndex)&widthMaskMipmap) + (((((d_y>>4) + Y)>>mipmapIndex)&heightMaskMipmap)<<widthBitsMipmap);
							screen[index] = colorTable[mipmap[mipmapIndex][textureIndex]];
						}
					}
					continue;
				}
				
				int offset = x_right - j;
				Aoffset = A.x*offset;
				Boffset = B.x*offset;
				Coffset = C.x*offset;
	
				aDotW+=Aoffset;
				bDotW+=Boffset;
				cDotW+=Coffset;
				cDotWInverse = 1/cDotW;
				X1 = (int)(aDotW*cDotWInverse);
				Y1 = (int)(bDotW*cDotWInverse);
				dx = X1 - X;
				dy = Y1 - Y;
				
				if(z_left > 209715200)
					mipmapIndex = 0;
				else if(z_left > 104857600)
					mipmapIndex = 1;
				else if(z_left > 52428800)
					mipmapIndex = 2;
				else if(z_left > 26214400)
					mipmapIndex = 3;
				else
					mipmapIndex = 4;
				
				widthBitsMipmap = widthBits - mipmapIndex;
				widthMaskMipmap = ((widthMask + 1) >> mipmapIndex) - 1;
				heightMaskMipmap = ((heightMask + 1) >> mipmapIndex) - 1;
				
				
				for( k = offset, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++, z_left+=dz){
					if(zbuffer[index] == 0){
						z = z_left >> zDepthAccuracy;
						zbuffer[index] = (short)z;
						textureIndex = ((((d_x/offset) + X)>>mipmapIndex)&widthMaskMipmap) + (((((d_y/offset) + Y)>>mipmapIndex)&heightMaskMipmap)<<widthBitsMipmap);
						screen[index] = colorTable[mipmap[mipmapIndex][textureIndex]];
					}
				}
				
				break;
			}
		}
	}
	
	//same texture mapper as the Mipmap renderer, except this one compare pixel's z values against zbuffer
	public static void renderMipmap_compareZ(){
		int[][] mipmap = poly.myTexture.mipmap; 
		diffuse_I = poly.diffuse_I;
		int[]colorTable = gameData.colorTable[diffuse_I];
		zbuffer = mainThread.zBuffer; 
		int dz, dx, z;
		
		int mipmapIndex = 0;
		int widthBitsMipmap;
		int widthMaskMipmap;
		int heightMaskMipmap;
		
		
	
		A_offset = A.x*16;
		B_offset = B.x*16;
		C_offset = C.x*16;
		
		double Aoffset,Boffset,Coffset;
		
		
		
				
		for(int i = start; i <= end; i++){
			x_left=xLeft[i];
			x_right=xRight[i];
			dx = x_right - x_left;
			if(dx == 0)
				continue;
			
			W.set(x_left-half_screen_w, -i + half_screen_h, vector.Z_length);
			aDotW = A.dot(W);
			bDotW = B.dot(W);
			cDotW = C.dot(W);
			
			//find the texture coordinate for the start pixel of the scanline
			cDotWInverse = 1/cDotW;
			X = (int)(aDotW*cDotWInverse);
			Y = (int)(bDotW*cDotWInverse);
			X1 = X;
			Y1 = Y;
			
			int temp = gameData.screenTable[i];
			int index;
			
			z_left = zLeft[i] ;
			z_right = zRight[i] ;
			dz = (z_right - z_left)/dx;
			
			for(int j = x_left; j < x_right; j+=16){
				X = X1;
				Y = Y1;
				
				index = j + temp;
				if(x_right - j > 15){
					//find the correct texture coordinate every 16 pixels.
					//Use the interpolation values for the  pixels in between.
					aDotW+=A_offset;
					bDotW+=B_offset;
					cDotW+=C_offset;
					cDotWInverse = 1/cDotW;
					X1 = (int)(aDotW*cDotWInverse);
					Y1 = (int)(bDotW*cDotWInverse);
					dx = X1 - X;
					dy = Y1 - Y;
					
					if(z_left > 209715200)
						mipmapIndex = 0;
					else if(z_left > 104857600)
						mipmapIndex = 1;
					else if(z_left > 52428800)
						mipmapIndex = 2;
					else if(z_left > 26214400)
						mipmapIndex = 3;
					else
						mipmapIndex = 4;
					
					
					widthBitsMipmap = widthBits - mipmapIndex;
					widthMaskMipmap = ((widthMask + 1) >> mipmapIndex) - 1;
					heightMaskMipmap = ((heightMask + 1) >> mipmapIndex) - 1;
					
					for( k = 16, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++, z_left+=dz){
						z = z_left >> zDepthAccuracy;
						if(zbuffer[index] < z){
							zbuffer[index] = (short)z;
							textureIndex = ((((d_x>>4) + X)>>mipmapIndex)&widthMaskMipmap) + (((((d_y>>4) + Y)>>mipmapIndex)&heightMaskMipmap)<<widthBitsMipmap);
							screen[index] = colorTable[mipmap[mipmapIndex][textureIndex]];
						}
					}
					continue;
				}
				
				int offset = x_right - j;
				Aoffset = A.x*offset;
				Boffset = B.x*offset;
				Coffset = C.x*offset;
	
				aDotW+=Aoffset;
				bDotW+=Boffset;
				cDotW+=Coffset;
				cDotWInverse = 1/cDotW;
				X1 = (int)(aDotW*cDotWInverse);
				Y1 = (int)(bDotW*cDotWInverse);
				dx = X1 - X;
				dy = Y1 - Y;
				
				if(z_left > 209715200)
					mipmapIndex = 0;
				else if(z_left > 104857600)
					mipmapIndex = 1;
				else if(z_left > 52428800)
					mipmapIndex = 2;
				else if(z_left > 26214400)
					mipmapIndex = 3;
				else
					mipmapIndex = 4;
				
				widthBitsMipmap = widthBits - mipmapIndex;
				widthMaskMipmap = ((widthMask + 1) >> mipmapIndex) - 1;
				heightMaskMipmap = ((heightMask + 1) >> mipmapIndex) - 1;
				
				
				for( k = offset, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++, z_left+=dz){
					z = z_left >> zDepthAccuracy;
					if(zbuffer[index] < z){
						zbuffer[index] = (short)z;
						textureIndex = ((((d_x/offset) + X)>>mipmapIndex)&widthMaskMipmap) + (((((d_y/offset) + Y)>>mipmapIndex)&heightMaskMipmap)<<widthBitsMipmap);
						screen[index] = colorTable[mipmap[mipmapIndex][textureIndex]];
					}
				}
				
				break;
			}
		}
	}
	
	//same texture mapper as the Mipmap renderer, it also render the polygon under the effect of volumetric fog
	public static void renderMipmap_fog(){
		int[][] mipmap = poly.myTexture.mipmap;
		int[] offScreen = poly.fogVolume.offScreenBuffer;
		diffuse_I = poly.diffuse_I;
		int[]colorTable = gameData.colorTable[diffuse_I];
		zbuffer = mainThread.zBuffer; 
		int dz, dx, z;
		int d = 0;
		boolean renderEdge = false;
		boolean cameraInside = poly.fogVolume.cameraInside;
		float outsideVolumeFadeRate = poly.fogVolume.outsideVolumeFadeRate;
		
		int textureColor, r,g,b, r_,g_,b_;
		int color = poly.fogVolume.color;
		float fadeRate = poly.fogVolume.fadeRate;
		
		r_ = (color & 0xff0000) >>16;
		g_ = (color & 0x00ff00) >> 8;
		b_ = color & 0x0000ff;
		
		int mipmapIndex = 0;
		int widthBitsMipmap;
		int widthMaskMipmap;
		int heightMaskMipmap;
		
		
	
		A_offset = A.x*16;
		B_offset = B.x*16;
		C_offset = C.x*16;
		
		double Aoffset,Boffset,Coffset;
		
		
		
				
		for(int i = start; i <= end; i++){
			x_left=xLeft[i];
			x_right=xRight[i];
			dx = x_right - x_left;
			if(dx == 0)
				continue;
			
			W.set(x_left-half_screen_w, -i + half_screen_h, vector.Z_length);
			aDotW = A.dot(W);
			bDotW = B.dot(W);
			cDotW = C.dot(W);
			
			//find the texture coordinate for the start pixel of the scanline
			cDotWInverse = 1/cDotW;
			X = (int)(aDotW*cDotWInverse);
			Y = (int)(bDotW*cDotWInverse);
			X1 = X;
			Y1 = Y;
			
			int temp = gameData.screenTable[i];
			int index;
			
			z_left = zLeft[i] ;
			z_right = zRight[i] ;
			dz = (z_right - z_left)/dx;
			
			
			for(int j = x_left; j < x_right; j+=16){
				X = X1;
				Y = Y1;
				
				index = j + temp;
				if(x_right - j > 15){
					//find the correct texture coordinate every 16 pixels.
					//Use the interpolation values for the  pixels in between.
					aDotW+=A_offset;
					bDotW+=B_offset;
					cDotW+=C_offset;
					cDotWInverse = 1/cDotW;
					X1 = (int)(aDotW*cDotWInverse);
					Y1 = (int)(bDotW*cDotWInverse);
					dx = X1 - X;
					dy = Y1 - Y;
					
					if(z_left > 209715200)
						mipmapIndex = 0;
					else if(z_left > 104857600)
						mipmapIndex = 1;
					else if(z_left > 52428800)
						mipmapIndex = 2;
					else if(z_left > 26214400)
						mipmapIndex = 3;
					else
						mipmapIndex = 4;
					
				
					if(j - x_left > 31 && x_right - j > 64){
						if(cameraInside || z_left > offScreen[index]){
							d = (int)(1024*Math.sqrt(fadeRate/z_left));
						}else{
							
							d =(int)(1024*(outsideVolumeFadeRate + (float)Math.sqrt(fadeRate/z_left - fadeRate/offScreen[index])));		
							
						}
						renderEdge = false;
					}else
						renderEdge = true;
					
				
					widthBitsMipmap = widthBits - mipmapIndex;
					widthMaskMipmap = ((widthMask + 1) >> mipmapIndex) - 1;
					heightMaskMipmap = ((heightMask + 1) >> mipmapIndex) - 1;
					
					for( k = 16, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++, z_left+=dz){
						z = z_left >> zDepthAccuracy;
						if(zbuffer[index] < z){							
							if(renderEdge){
								if(cameraInside || z_left > offScreen[index]){
									d = (int)(1024*Math.sqrt(fadeRate/z_left));
								}else{
									
									d =(int)(1024*(outsideVolumeFadeRate + (float)Math.sqrt(fadeRate/z_left - fadeRate/offScreen[index])));		
								}
							}
							zbuffer[index] = (short)z;
							textureIndex = ((((d_x>>4) + X)>>mipmapIndex)&widthMaskMipmap) + (((((d_y>>4) + Y)>>mipmapIndex)&heightMaskMipmap)<<widthBitsMipmap);
							textureColor = colorTable[mipmap[mipmapIndex][textureIndex]];
							r = (textureColor & 0xff0000) >> 16;
							g = (textureColor & 0x00ff00) >> 8;
							b = textureColor & 0x0000ff;
						
							screen[index] = ((r + (((r_ -r)*d)>>10))<<16) + ((g + (((g_ -g)*d)>>10))<<8) +  (b + (((b_ -b)*d)>>10));
							
						}
					}
					continue;
				}
				
				int offset = x_right - j;
				Aoffset = A.x*offset;
				Boffset = B.x*offset;
				Coffset = C.x*offset;
	
				aDotW+=Aoffset;
				bDotW+=Boffset;
				cDotW+=Coffset;
				cDotWInverse = 1/cDotW;
				X1 = (int)(aDotW*cDotWInverse);
				Y1 = (int)(bDotW*cDotWInverse);
				dx = X1 - X;
				dy = Y1 - Y;
				
				if(z_left > 209715200)
					mipmapIndex = 0;
				else if(z_left > 104857600)
					mipmapIndex = 1;
				else if(z_left > 52428800)
					mipmapIndex = 2;
				else if(z_left > 26214400)
					mipmapIndex = 3;
				else
					mipmapIndex = 4;
				
				
				
				widthBitsMipmap = widthBits - mipmapIndex;
				widthMaskMipmap = ((widthMask + 1) >> mipmapIndex) - 1;
				heightMaskMipmap = ((heightMask + 1) >> mipmapIndex) - 1;
				
				
				for( k = offset, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++, z_left+=dz){
					
					z = z_left >> zDepthAccuracy;
					if(zbuffer[index] < z){
						
						if(cameraInside || z_left > offScreen[index]){
							d = (int)(1024*Math.sqrt(fadeRate/z_left));
						}else{
							
							d =(int)(1024*(outsideVolumeFadeRate + (float)Math.sqrt(fadeRate/z_left - fadeRate/offScreen[index])));		
						}
						zbuffer[index] = (short)z;
						textureIndex = ((((d_x/offset) + X)>>mipmapIndex)&widthMaskMipmap) + (((((d_y/offset) + Y)>>mipmapIndex)&heightMaskMipmap)<<widthBitsMipmap);
						textureColor = colorTable[mipmap[mipmapIndex][textureIndex]];
						r = (textureColor & 0xff0000) >> 16;
						g = (textureColor & 0x00ff00) >> 8;
						b = textureColor & 0x0000ff;
					
						screen[index] = ((r + (((r_ -r)*d)>>10))<<16) + ((g + (((g_ -g)*d)>>10))<<8) +  (b + (((b_ -b)*d)>>10));
					}
				}
				
				break;
			}
		}
	}	

	//same texture mapper as the Mipmap renderer, it also apply light map to the texture
	public static void renderMipmap_drawLightMap(){
		int[][] mipmap = poly.myTexture.mipmap; 
		diffuse_I = poly.diffuse_I;
		int[][] colorTable = gameData.colorTable;
		zbuffer = mainThread.zBuffer; 
		int dz, dx, z, x1, y1;
		
		int mipmapIndex = 0;
		int widthBitsMipmap;
		int widthMaskMipmap;
		int heightMaskMipmap;
		int max_dx = poly.max_dx;
		
		
		
		byte[] lightmap = mainThread.lightMapTextures[poly.lightMapTextureIndex]; 
		
		int widthBitsLightmap = mainThread.lightMapTexturesInfo[poly.lightMapTextureIndex][0];
		int widthMaskLightmap = mainThread.lightMapTexturesInfo[poly.lightMapTextureIndex][1];
		int heightMaskLightmap = mainThread.lightMapTexturesInfo[poly.lightMapTextureIndex][2];
		
		byte brightness;
	
		A_offset = A.x*16;
		B_offset = B.x*16;
		C_offset = C.x*16;
		
		double Aoffset,Boffset,Coffset;
		
		
		
		
		
		for(int i = start; i <= end; i++){
			x_left=xLeft[i];
			x_right=xRight[i];
			dx = x_right - x_left;
			if(dx == 0)
				continue;
			
			W.set(x_left-half_screen_w, -i + half_screen_h, vector.Z_length);
			aDotW = A.dot(W);
			bDotW = B.dot(W);
			cDotW = C.dot(W);
			
			//find the texture coordinate for the start pixel of the scanline
			cDotWInverse = 1/cDotW;
			X = (int)(aDotW*cDotWInverse);
			Y = (int)(bDotW*cDotWInverse);
			X1 = X;
			Y1 = Y;
			
			int temp = gameData.screenTable[i];
			int index;
			
			z_left = zLeft[i] ;
			z_right = zRight[i] ;
			dz = (z_right - z_left)/dx;
			
			for(int j = x_left; j < x_right; j+=16){
				X = X1;
				Y = Y1;
				
				index = j + temp;
				if(x_right - j > 15){
					//find the correct texture coordinate every 16 pixels.
					//Use the interpolation values for the  pixels in between.
					aDotW+=A_offset;
					bDotW+=B_offset;
					cDotW+=C_offset;
					cDotWInverse = 1/cDotW;
					X1 = (int)(aDotW*cDotWInverse);
					Y1 = (int)(bDotW*cDotWInverse);
					dx = X1 - X;
					dy = Y1 - Y;
					
					if(z_left > 209715200)
						mipmapIndex = 0;
					else if(z_left > 104857600)
						mipmapIndex = 1;
					else if(z_left > 52428800)
						mipmapIndex = 2;
					else if(z_left > 26214400)
						mipmapIndex = 3;
					else
						mipmapIndex = 4;
					
					
					widthBitsMipmap = widthBits - mipmapIndex;
					widthMaskMipmap = ((widthMask + 1) >> mipmapIndex) - 1;
					heightMaskMipmap = ((heightMask + 1) >> mipmapIndex) - 1;
					
			
					for( k = 16, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++, z_left+=dz){
						z = z_left >> zDepthAccuracy;
						if(zbuffer[index] < z){
							zbuffer[index] = (short)z;
							x1 = (d_x>>4) + X;
							y1 = (d_y>>4) + Y;
						
							textureIndex = ((x1>>mipmapIndex)&widthMaskMipmap) + (((y1>>mipmapIndex)&heightMaskMipmap)<<widthBitsMipmap);
							brightness = lightmap[(x1&widthMaskLightmap) + ((y1&heightMaskLightmap)<<widthBitsLightmap)];
							screen[index] = colorTable[brightness][mipmap[mipmapIndex][textureIndex]];
						}
					}
					continue;
				}
				
				int offset = x_right - j;
				Aoffset = A.x*offset;
				Boffset = B.x*offset;
				Coffset = C.x*offset;
	
				aDotW+=Aoffset;
				bDotW+=Boffset;
				cDotW+=Coffset;
				cDotWInverse = 1/cDotW;
				X1 = (int)(aDotW*cDotWInverse);
				Y1 = (int)(bDotW*cDotWInverse);
				dx = X1 - X;
				dy = Y1 - Y;
				
				if(z_left > 209715200)
					mipmapIndex = 0;
				else if(z_left > 104857600)
					mipmapIndex = 1;
				else if(z_left > 52428800)
					mipmapIndex = 2;
				else if(z_left > 26214400)
					mipmapIndex = 3;
				else
					mipmapIndex = 4;
				
				widthBitsMipmap = widthBits - mipmapIndex;
				widthMaskMipmap = ((widthMask + 1) >> mipmapIndex) - 1;
				heightMaskMipmap = ((heightMask + 1) >> mipmapIndex) - 1;
				
				
				for( k = offset, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++, z_left+=dz){
					z = z_left >> zDepthAccuracy;
					if(zbuffer[index] < z){
						
						x1 = (d_x/offset) + X;
						y1 = (d_y/offset) + Y;
						if(dx > max_dx){
							continue;
							
						}
						
						zbuffer[index] = (short)z;
						
						textureIndex = ((x1>>mipmapIndex)&widthMaskMipmap) + (((y1>>mipmapIndex)&heightMaskMipmap)<<widthBitsMipmap);
						brightness = lightmap[(x1&widthMaskLightmap) + ((y1&heightMaskLightmap)<<widthBitsLightmap)];
						screen[index] = colorTable[brightness][mipmap[mipmapIndex][textureIndex]];
						
					}
				}
				
				break;
			}
		}
		}
	
	//Texture mapper for rendering polygons that distort the image behind it.
	//the level of distortion now depends on the distance from the view point
	public static void renderWater(){
		
		byte[][] displacementMap =  poly.myTexture.displacementMap;
		int[] offScreen = mainThread.offScreen;
		int distortedIndex = 0;
		zbuffer = mainThread.zBuffer; 
		int dz, dx, z;
		int color = poly.color;
		int distortionLevel;
		
		A_offset = A.x*16;
		B_offset = B.x*16;
		C_offset = C.x*16;
		
		double Aoffset,Boffset,Coffset;
		
		
		
		
		//if the polygon is perpendicular to the x-z plane (in world coordinate)
		//the z depth will be constant along a scanline As the result, 
		//only 2 perspective corrections are needed for every scanline.
		
		byte x_index = 0;
		byte y_index = 1;
		byte xDirection = 1;
		byte yDirection = 1;
		if((camera.XZ_angle > 45 && camera.XZ_angle < 135) || (camera.XZ_angle > 225 && camera.XZ_angle < 315)){
			x_index = 1;
			y_index = 0;
		}
		
		if((camera.XZ_angle > 45 && camera.XZ_angle < 135) || (camera.XZ_angle >= 135 && camera.XZ_angle <= 225)){
			xDirection = -1;
			yDirection = -1;
		}
	
		for(int i = start; i <= end; i++){
			x_left=xLeft[i];
			x_right=xRight[i];
			dx = x_right - x_left;
			if(dx == 0)
				continue;
					
			z_left = zLeft[i] ;
			z_right = zRight[i] ;
			dz = (z_right - z_left)/dx;
					
			W.set(x_left-half_screen_w, -i + half_screen_h, vector.Z_length);
			aDotW = A.dot(W);
			bDotW = B.dot(W);
			cDotW = C.dot(W);
					
			//find the texture coordinate for the start pixel of the scanline
			cDotWInverse = 1/cDotW;
			X = (int)(aDotW*cDotWInverse);
			Y = (int)(bDotW*cDotWInverse);
			X1 = X;
			Y1 = Y;
					
			int temp = gameData.screenTable[i];
			int index;
					
			int offset = x_right - x_left;
			index = x_left + temp;
			Aoffset = A.x*offset;
			Boffset = B.x*offset;
			Coffset = C.x*offset;
			
			aDotW+=Aoffset;
			bDotW+=Boffset;
			cDotW+=Coffset;
			cDotWInverse = 1/cDotW;
			X1 = (int)(aDotW*cDotWInverse);
			Y1 = (int)(bDotW*cDotWInverse);
			dx = X1 - X;
			dy = Y1 - Y;
					
			if(z_left > 52428800)
				distortionLevel = 0;
			else 
				distortionLevel = 1;
						
			zDepth = (short)(z_left >> zDepthAccuracy);
					
			for( k = offset, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++){
				if(zbuffer[index] < zDepth){
					textureIndex = (((d_x/offset) + X)&widthMask) + ((((d_y/offset) + Y)&heightMask)<<widthBits);
					distortedIndex = (index + xDirection*((displacementMap[textureIndex][x_index] >> distortionLevel)) + yDirection*screen_w*((displacementMap[textureIndex][y_index] >> distortionLevel)));
					if(distortedIndex < 0 || distortedIndex >= screen_pixel_count)
						distortedIndex = index;
					
					if( zDepth < zbuffer[distortedIndex]){
						offScreen[index] = screen[index];
					}else
						offScreen[index] = screen[distortedIndex];
					
				}else{
					offScreen[index] = screen[index];
				}
			}
						
		}
	
		
		//copy the content of the offscreen buffer to screen buffer.
		for(int i = start; i <= end; i++){
			int offset = xRight[i] - xLeft[i];
			temp = gameData.screenTable[i];
			int index = xLeft[i] + temp;
			for(k = offset; k >0; k--, index++){
				screen[index] = offScreen[index];
				
			}
		}
	}
	
	//same as water renderer, but it also apply the underwater fog effect 
	public static void renderWater_fog(){
		
		byte[][] displacementMap =  poly.myTexture.displacementMap;
		int[] offScreen = poly.fogVolume.offScreenBuffer;
		int distortedIndex = 0;
		zbuffer = mainThread.zBuffer; 
		int dz, dx;
		
		int d = 0;
		int textureColor, r,g,b, r_,g_,b_;
		int color = 0x22a1b0; 
		r_ = (color & 0xff0000) >>16;
		g_ = (color & 0x00ff00) >> 8;
		b_ = color & 0x0000ff;
		
		A_offset = A.x*16;
		B_offset = B.x*16;
		C_offset = C.x*16;
		
		double Aoffset,Boffset,Coffset;
		
		
		
		
		//if the polygon is perpendicular to the x-z plane (in world coordinate)
		//the z depth will be constant along a scanline As the result, 
		//only 2 perspective corrections are needed for every scanline.
		
		byte x_index = 0;
		byte y_index = 1;
		byte xDirection = 1;
		byte yDirection = 1;
		if((camera.XZ_angle > 45 && camera.XZ_angle < 135) || (camera.XZ_angle > 225 && camera.XZ_angle < 315)){
			x_index = 1;
			y_index = 0;
		}
		
		if((camera.XZ_angle > 45 && camera.XZ_angle < 135) || (camera.XZ_angle >= 135 && camera.XZ_angle <= 225)){
			xDirection = -1;
			yDirection = -1;
		}
	
		for(int i = start; i <= end; i++){
			x_left=xLeft[i];
			x_right=xRight[i];
			dx = x_right - x_left;
			if(dx == 0)
				continue;
					
			z_left = zLeft[i] ;
			z_right = zRight[i] ;
			dz = (z_right - z_left)/dx;
					
			W.set(x_left-half_screen_w, -i + half_screen_h, vector.Z_length);
			aDotW = A.dot(W);
			bDotW = B.dot(W);
			cDotW = C.dot(W);
					
			//find the texture coordinate for the start pixel of the scanline
			cDotWInverse = 1/cDotW;
			X = (int)(aDotW*cDotWInverse);
			Y = (int)(bDotW*cDotWInverse);
			X1 = X;
			Y1 = Y;
					
			int temp = gameData.screenTable[i];
			int index;
					
			int offset = x_right - x_left;
			index = x_left + temp;
			Aoffset = A.x*offset;
			Boffset = B.x*offset;
			Coffset = C.x*offset;
			
			aDotW+=Aoffset;
			bDotW+=Boffset;
			cDotW+=Coffset;
			cDotWInverse = 1/cDotW;
			X1 = (int)(aDotW*cDotWInverse);
			Y1 = (int)(bDotW*cDotWInverse);
			dx = X1 - X;
			dy = Y1 - Y;
					
					
			d = (int)(1024*Math.sqrt(20386318f/z_left));
			
			if(d > 1024)
				d = 1024;
			
			zDepth = (short)(z_left >> zDepthAccuracy);
			
			for( k = offset, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++){
				if(zbuffer[index] < zDepth){
					textureIndex = (((d_x/offset) + X)&widthMask) + ((((d_y/offset) + Y)&heightMask)<<widthBits);
					distortedIndex = (index + xDirection*(displacementMap[textureIndex][x_index]) + yDirection*screen_w*(displacementMap[textureIndex][y_index]));
					if(distortedIndex < 0 || distortedIndex >= screen_pixel_count)
						distortedIndex = index;
					
					
					textureColor = screen[distortedIndex];
					r = (textureColor & 0xff0000) >> 16;
					g = (textureColor & 0x00ff00) >> 8;
					b = textureColor & 0x0000ff;
					offScreen[index] = ((r + (((r_ -r)*d)>>10))<<16) + ((g + (((g_ -g)*d)>>10))<<8) +  (b + (((b_ -b)*d)>>10));
				}else{
					offScreen[index] = screen[index];
				}
			}
						
		}
	
		
		//copy the content of the offscreen buffer to screen buffer.
		for(int i = start; i <= end; i++){
			int offset = xRight[i] - xLeft[i];
			temp = gameData.screenTable[i];
			int index = xLeft[i] + temp;
			for(k = offset; k >0; k--, index++){
				screen[index] = offScreen[index];
				
			}
		}
	}
	
	//Texture mapper for rendering sky maps.
	public static void renderSkybox(){
		
		int[] Texture = poly.myTexture.Texture; 
		zbuffer = mainThread.zBuffer; 
		diffuse_I = poly.diffuse_I;
	
		A_offset = A.x*16;
		B_offset = B.x*16;
		C_offset = C.x*16;
		
		double Aoffset,Boffset,Coffset;
		
		
		
		
		for(int i = start; i <= end; i++){
			x_left = xLeft[i];
			x_right = xRight[i];
			if(x_right - x_left == 0)
				continue;
			
			W.set(x_left-half_screen_w, -i + half_screen_h, vector.Z_length);
			aDotW = A.dot(W);
			bDotW = B.dot(W);
			cDotW = C.dot(W);
			
			//find the texture coordinate for the start pixel of the scanline
			cDotWInverse = 1/cDotW;
			X = (int)(aDotW*cDotWInverse);
			Y = (int)(bDotW*cDotWInverse);
			X1 = X;
			Y1 = Y;
			
			int temp = gameData.screenTable[i];
			int index;
			
			
			for(int j = x_left; j <= x_right; j+=16){
				X = X1;
				Y = Y1;
				
				index = j + temp;
				if(x_right - j > 15){
					//find the correct texture coordinate every 16 pixels.
					//Use the interpolation values for the rest pixels.
					aDotW+=A_offset;
					bDotW+=B_offset;
					cDotW+=C_offset;
					cDotWInverse = 1/cDotW;
					X1 = (int)(aDotW*cDotWInverse);
					Y1 = (int)(bDotW*cDotWInverse);
					dx = X1 - X;
					dy = Y1 - Y;
					
					
					for( k = 16, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++){
						if(zbuffer[index] == 0){
							X1 = (d_x>>4) + X;
							Y1 = (d_y>>4) + Y;
						
							if(X1 > 511){
								X1 = 511;
							}
							if(X1 < 1)
								X1 = 0;
							if(Y1 >511)
								Y1 = 511;
							if(Y1 < 0)
								Y1 = 0;
							
							
							screen[index] = Texture[X1 + (Y1<<widthBits)];
						}
					}
					
					continue;
				}
				
				
				int offset = x_right - j;
				Aoffset = A.x*offset;
				Boffset = B.x*offset;
				Coffset = C.x*offset;
	
				aDotW+=Aoffset;
				bDotW+=Boffset;
				cDotW+=Coffset;
				cDotWInverse = 1/cDotW;
				X1 = (int)(aDotW*cDotWInverse);
				Y1 = (int)(bDotW*cDotWInverse);
				dx = X1 - X;
				dy = Y1 - Y;
				
				for( k = offset, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++){
					if(zbuffer[index] == 0){
						X1 = (d_x/offset) + X;
						Y1 = (d_y/offset) + Y;
					
						if(X1 > 511)
							X1 = 511;
						if(X1 < 1)
							X1 = 0;
						if(Y1 >511)
							Y1 = 511;
						if(Y1 < 0)
							Y1 = 0;
						
						
						screen[index] = Texture[X1 + (Y1<<widthBits)];
					}
				}
				break;
			}
		}
	}
	
	//rendering polygon that has a soild color
	public static void renderSoildPolygon(){
		int soildColor = gameData.colorTable[poly.diffuse_I][poly.color];
		zbuffer = mainThread.zBuffer; 
		int dz, dx, z;
		
		
		
		
		
		for(int i = start; i <= end; i++){
			int temp = gameData.screenTable[i];
				
			x_left = xLeft[i] + temp;
			x_right = xRight[i] + temp;
			dx = x_right - x_left;
			if(dx == 0)
				continue;
			
			z_left = zLeft[i] ;
			z_right = zRight[i] ;
			dz = (z_right - z_left)/dx;
		
			for(int j = x_left; j < x_right; j++, z_left+=dz){
				z = z_left >> zDepthAccuracy;
				if(zbuffer[j] < z){
					screen[j] = soildColor;
					zbuffer[j] = (short)z;
				}
			}
		}
	}
	
	//render solid color polygon that under fog effect
	public static void renderSoildPolygon_fog(){
		int soildColor = gameData.colorTable[poly.diffuse_I][poly.color];
		zbuffer = mainThread.zBuffer; 
		int dz, dx, z;
		float d = 0;
		int[] offScreen = poly.fogVolume.offScreenBuffer;
		boolean cameraInside = poly.fogVolume.cameraInside;
		int r,g,b, r_,g_,b_;
		r = (soildColor & 0xff0000) >> 16;
		g = (soildColor & 0x00ff00) >> 8;
		b = soildColor & 0x0000ff;
		int color = poly.fogVolume.color;
		float fadeRate = poly.fogVolume.fadeRate;
		r_ = ((color & 0xff0000) >>16) - r;
		g_ = ((color & 0x00ff00) >> 8) - g;
		b_ = (color & 0x0000ff) - b;
		
		
		
		
		for(int i = start; i <= end; i++){
			int temp = gameData.screenTable[i];
				
			x_left = xLeft[i] + temp;
			x_right = xRight[i] + temp;
			dx = x_right - x_left;
			if(dx == 0)
				continue;
			
			z_left = zLeft[i] ;
			z_right = zRight[i] ;
			dz = (z_right - z_left)/dx;
		
			for(int j = x_left; j < x_right; j++, z_left+=dz){
				z = z_left >> zDepthAccuracy;
				if(zbuffer[j] < z){
					if(cameraInside){
						d = 0.05f + (float)Math.sqrt(fadeRate/z_left);
						screen[j] = ((int)(r+r_*d)  << 16) + ((int)(g+g_*d) << 8) + (int)(b+b_*d);
					}else{
						if( z_left >= offScreen[j]){
							screen[j] = soildColor;
						}else{
							d = 0.4f + (float)Math.sqrt(fadeRate/z_left - fadeRate/offScreen[j]);
							if(d > 1)
								d = 1;
							screen[j] = ((int)(r+r_*d)  << 16) + ((int)(g+g_*d) << 8) + (int)(b+b_*d);
						}
		
					}
					zbuffer[j] = (short)z;
				}
			}
		}
	}
	
	//render outer side of fog volume to  offscreen buffer
	public static void renderFogVolume_outer(){
		
		
		int[] offScreen = poly.fogVolume.offScreenBuffer;
		int dz;
		
		for(int i = start; i <= end; i++){
			int temp = gameData.screenTable[i];
			x_left = xLeft[i] + temp;
			x_right = xRight[i] + temp;
			dx = x_right - x_left;
			if(dx == 0)
				continue;
			
			z_left = zLeft[i] ;
			z_right = zRight[i] ;
			dz = (z_right - z_left)/dx;
		
			for(int j = x_left; j < x_right; j++, z_left+=dz){
				offScreen[j] = z_left ;
			}
		}
	}
	

	//Texture mapper for rendering polygons that distort the image behind it.
	//The distortion will only apply when the target pixel's  z depth = 0; 
	public static void renderGlass1(){
		int[] offScreen = mainThread.offScreen;
		int[] Texture = poly.myTexture.Texture; 
		diffuse_I = poly.diffuse_I;
		int[] colorTable = gameData.colorTable[diffuse_I];
		byte[][] displacementMap =  poly.myTexture.displacementMap;
		int distortedIndex = 0;
		zbuffer = mainThread.zBuffer; 
		int dz, dx, z;
	
		A_offset = A.x*16;
		B_offset = B.x*16;
		C_offset = C.x*16;
		
		double Aoffset,Boffset,Coffset;
		
		
		
		
		for(int i = start; i <= end; i++){
			x_left=xLeft[i];
			x_right=xRight[i];
			dx = x_right - x_left;
			if(dx == 0)
				continue;
			
			W.set(x_left-half_screen_w, -i + half_screen_h, vector.Z_length);
			aDotW = A.dot(W);
			bDotW = B.dot(W);
			cDotW = C.dot(W);
			
			//find the texture coordinate for the start pixel of the scanline
			cDotWInverse = 1/cDotW;
			X = (int)(aDotW*cDotWInverse);
			Y = (int)(bDotW*cDotWInverse);
			X1 = X;
			Y1 = Y;
			
			int temp = gameData.screenTable[i];
			int index;
			
			z_left = zLeft[i] ;
			z_right = zRight[i] ;
			dz = (z_right - z_left)/dx;
			
			for(int j = x_left; j < x_right; j+=16){
				X = X1;
				Y = Y1;
				
				index = j + temp;
				if(x_right - j > 15){
					//find the correct texture coordinate every 16 pixels.
					//Use the interpolation values for the rest pixels.
					aDotW+=A_offset;
					bDotW+=B_offset;
					cDotW+=C_offset;
					cDotWInverse = 1/cDotW;
					X1 = (int)(aDotW*cDotWInverse);
					Y1 = (int)(bDotW*cDotWInverse);
					dx = X1 - X;
					dy = Y1 - Y;
					
				
					for( k = 16, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++, z_left+=dz){
						z = z_left >> zDepthAccuracy;
						
						if(zbuffer[index] < z){
							textureIndex = (((d_x>>4) + X)&widthMask) + ((((d_y>>4) + Y)&heightMask)<<widthBits);
						
							distortedIndex = index + displacementMap[textureIndex][0] + screen_w*displacementMap[textureIndex][1];
							if(distortedIndex < 0 || distortedIndex >= screen_pixel_count)
								distortedIndex = index;
							
							if(zbuffer[distortedIndex] == 0){
								temp1 = (screen[distortedIndex]&0xFEFEFE)>>1;
								temp2= (colorTable[Texture[textureIndex]]&0xFEFEFE)>>1;
							}else{
								temp1 = (screen[index]&0xFEFEFE)>>1;
								temp2= (colorTable[Texture[textureIndex]]&0xFEFEFE)>>1;	
							}
							offScreen[index] = temp1 + temp2;
						}else{
							offScreen[index] = screen[index];
						}
						
					}
				
					continue;
				}
				
				int offset = x_right - j;
				Aoffset = A.x*offset;
				Boffset = B.x*offset;
				Coffset = C.x*offset;
	
				aDotW+=Aoffset;
				bDotW+=Boffset;
				cDotW+=Coffset;
				cDotWInverse = 1/cDotW;
				X1 = (int)(aDotW*cDotWInverse);
				Y1 = (int)(bDotW*cDotWInverse);
				dx = X1 - X;
				dy = Y1 - Y;
				
				
				for( k = offset, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++, z_left+=dz){
					z = z_left >> zDepthAccuracy;
					
					if(zbuffer[index] < z){
						textureIndex = (((d_x/offset) + X)&widthMask) + ((((d_y/offset) + Y)&heightMask)<<widthBits);
						
						distortedIndex = index + displacementMap[textureIndex][0] + screen_w*displacementMap[textureIndex][1];
						if(distortedIndex < 0 || distortedIndex >= screen_pixel_count)
							distortedIndex = index;
						if(zbuffer[distortedIndex] == 0){
							temp1 = (screen[distortedIndex]&0xFEFEFE)>>1;
							temp2= (colorTable[Texture[textureIndex]]&0xFEFEFE)>>1;
						}else{
							temp1 = (screen[index]&0xFEFEFE)>>1;
							temp2= (colorTable[Texture[textureIndex]]&0xFEFEFE)>>1;	
						}
						offScreen[index] = temp1 + temp2;
					}else{
						offScreen[index] = screen[index];
					}
				}
				
				break;
			}
		}
		
		
		//copy the content of the offscreen buffer to screen buffer.
		for(int i = start; i <= end; i++){
			int offset = xRight[i] - xLeft[i];
			temp = gameData.screenTable[i];
			int index = xLeft[i] + temp;
			for(k = offset; k >0; k--, index++){
				screen[index] = offScreen[index];
				
			}
		}
	}
	
	//Texture mapper for rendering polygons that distort the image behind it.
	//The distortion will only apply for pixels with z depth smaller than the target pixel
	//Alpha value is hard coded to 50%
	public static void renderGlass2(){
		int[] offScreen = mainThread.offScreen;
		int[] Texture = poly.myTexture.Texture; 
		diffuse_I = poly.diffuse_I;
		int[] colorTable = gameData.colorTable[diffuse_I];
		byte[][] displacementMap =  poly.myTexture.displacementMap;
		int distortedIndex = 0;
		zbuffer = mainThread.zBuffer; 
		int dz, dx, z, distortionLevel;
	
		A_offset = A.x*16;
		B_offset = B.x*16;
		C_offset = C.x*16;
		
		double Aoffset,Boffset,Coffset;
		
		
		
		
		for(int i = start; i <= end; i++){
			x_left=xLeft[i];
			x_right=xRight[i];
			dx = x_right - x_left;
			if(dx == 0)
				continue;
			
			W.set(x_left-half_screen_w, -i + half_screen_h, vector.Z_length);
			aDotW = A.dot(W);
			bDotW = B.dot(W);
			cDotW = C.dot(W);
			
			//find the texture coordinate for the start pixel of the scanline
			cDotWInverse = 1/cDotW;
			X = (int)(aDotW*cDotWInverse);
			Y = (int)(bDotW*cDotWInverse);
			X1 = X;
			Y1 = Y;
			
			int temp = gameData.screenTable[i];
			int index;
			
			z_left = zLeft[i] ;
			z_right = zRight[i] ;
			dz = (z_right - z_left)/dx;
			
			if(z_left > 209715200)
				distortionLevel = 0;
			else if(z_left > 104857600)
				distortionLevel = 0;
			else if(z_left > 52428800)
				distortionLevel = 0;
			else if(z_left > 26214400)
				distortionLevel = 1;
			else
				distortionLevel = 2;
			
			for(int j = x_left; j < x_right; j+=16){
				X = X1;
				Y = Y1;
				
				index = j + temp;
				if(x_right - j > 15){
					//find the correct texture coordinate every 16 pixels.
					//Use the interpolation values for the rest pixels.
					aDotW+=A_offset;
					bDotW+=B_offset;
					cDotW+=C_offset;
					cDotWInverse = 1/cDotW;
					X1 = (int)(aDotW*cDotWInverse);
					Y1 = (int)(bDotW*cDotWInverse);
					dx = X1 - X;
					dy = Y1 - Y;
					
				
					for( k = 16, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++, z_left+=dz){
						z = z_left >> zDepthAccuracy;
						
						if(zbuffer[index] < z){
							textureIndex = (((d_x>>4) + X)&widthMask) + ((((d_y>>4) + Y)&heightMask)<<widthBits);
						
							distortedIndex = index + (displacementMap[textureIndex][0] >> distortionLevel) + screen_w*(displacementMap[textureIndex][1] >> distortionLevel);
							if(distortedIndex < 0 || distortedIndex >= screen_pixel_count || z < zbuffer[distortedIndex])
								distortedIndex = index;
							
							temp1 = (screen[distortedIndex]&0xFEFEFE)>>1;
							temp2= (colorTable[Texture[textureIndex]]&0xFEFEFE)>>1;
							
							offScreen[index] = temp1 + temp2;
						}else{
							offScreen[index] = screen[index];
						}
						
					}
				
					continue;
				}
				
				int offset = x_right - j;
				Aoffset = A.x*offset;
				Boffset = B.x*offset;
				Coffset = C.x*offset;
	
				aDotW+=Aoffset;
				bDotW+=Boffset;
				cDotW+=Coffset;
				cDotWInverse = 1/cDotW;
				X1 = (int)(aDotW*cDotWInverse);
				Y1 = (int)(bDotW*cDotWInverse);
				dx = X1 - X;
				dy = Y1 - Y;
				
				
				for( k = offset, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++, z_left+=dz){
					z = z_left >> zDepthAccuracy;
					
					if(zbuffer[index] < z){
						textureIndex = (((d_x/offset) + X)&widthMask) + ((((d_y/offset) + Y)&heightMask)<<widthBits);
						
						distortedIndex = index + (displacementMap[textureIndex][0] >> distortionLevel) + screen_w*(displacementMap[textureIndex][1] >> distortionLevel);
						if(distortedIndex < 0 || distortedIndex >= screen_pixel_count || z < zbuffer[distortedIndex])
							distortedIndex = index;
						
						temp1 = (screen[distortedIndex]&0xFEFEFE)>>1;
						temp2= (colorTable[Texture[textureIndex]]&0xFEFEFE)>>1;
						
						offScreen[index] = temp1 + temp2;
					}else{
						offScreen[index] = screen[index];
					}
				}
				
				break;
			}
		}
		
		
		//copy the content of the offscreen buffer to screen buffer.
		for(int i = start; i <= end; i++){
			int offset = xRight[i] - xLeft[i];
			temp = gameData.screenTable[i];
			int index = xLeft[i] + temp;
			for(k = offset; k >0; k--, index++){
				screen[index] = offScreen[index];
				
			}
		}
	}
	
	
	//Same as glass renderer 2, except it handles arbitrary alpha values
	public static void renderGlass3(){
		int[] offScreen = mainThread.offScreen;
		int[] Texture = poly.myTexture.Texture; 
		diffuse_I = poly.diffuse_I;
		int[] colorTable = gameData.colorTable[diffuse_I];
		byte[][] displacementMap =  poly.myTexture.displacementMap;
		int distortedIndex = 0;
		zbuffer = mainThread.zBuffer; 
		int dz, dx, z;
		int distortionLevel = 0;
	
		
		A_offset = A.x*16;
		B_offset = B.x*16;
		C_offset = C.x*16;
		
		double Aoffset,Boffset,Coffset;
		
		
		
		
		for(int i = start; i <= end; i++){
			x_left=xLeft[i];
			x_right=xRight[i];
			dx = x_right - x_left;
			if(dx == 0)
				continue;
			
			W.set(x_left-half_screen_w, -i + half_screen_h, vector.Z_length);
			aDotW = A.dot(W);
			bDotW = B.dot(W);
			cDotW = C.dot(W);
			
			//find the texture coordinate for the start pixel of the scanline
			cDotWInverse = 1/cDotW;
			X = (int)(aDotW*cDotWInverse);
			Y = (int)(bDotW*cDotWInverse);
			X1 = X;
			Y1 = Y;
			
			int temp = gameData.screenTable[i];
			int index;
			
			z_left = zLeft[i] ;
			z_right = zRight[i] ;
			dz = (z_right - z_left)/dx;
			
			if(z_left > 209715200)
				distortionLevel = 1;
			else if(z_left > 104857600)
				distortionLevel = 1;
			else if(z_left > 52428800)
				distortionLevel = 2;
			else if(z_left > 26214400)
				distortionLevel = 3;
			else
				distortionLevel = 4;
			
			for(int j = x_left; j < x_right; j+=16){
				X = X1;
				Y = Y1;
				
				index = j + temp;
				if(x_right - j > 15){
					//find the correct texture coordinate every 16 pixels.
					//Use the interpolation values for the rest pixels.
					aDotW+=A_offset;
					bDotW+=B_offset;
					cDotW+=C_offset;
					cDotWInverse = 1/cDotW;
					X1 = (int)(aDotW*cDotWInverse);
					Y1 = (int)(bDotW*cDotWInverse);
					dx = X1 - X;
					dy = Y1 - Y;
					
					
					
					
					for( k = 16, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++, z_left+=dz){
						z = z_left >> zDepthAccuracy;
						
						if(zbuffer[index] < z){
							textureIndex = (((d_x>>4) + X)&widthMask) + ((((d_y>>4) + Y)&heightMask)<<widthBits);
						
							distortedIndex = index + (displacementMap[textureIndex][0]/distortionLevel) + screen_w*(displacementMap[textureIndex][1]/distortionLevel);
							if(distortedIndex < 0 || distortedIndex >= screen_pixel_count || z < zbuffer[distortedIndex])
								distortedIndex = index;
							
							
							
							temp1 = screen[distortedIndex];
							r = (((temp1&0xff0000) * alpha) >>16) >> 8;
							g = (((temp1&0xff00) * alpha) >> 8) >> 8;
							b = ((temp1&0xff) * alpha) >> 8;
							temp2= (colorTable[Texture[textureIndex]]&0xFEFEFE)>>1;
							
							offScreen[index] = ((r<<16)|(g<<8)|b) + temp2;
						}else{
							offScreen[index] = screen[index];
						}
						
					}
				
					continue;
				}
				
				int offset = x_right - j;
				Aoffset = A.x*offset;
				Boffset = B.x*offset;
				Coffset = C.x*offset;
	
				aDotW+=Aoffset;
				bDotW+=Boffset;
				cDotW+=Coffset;
				cDotWInverse = 1/cDotW;
				X1 = (int)(aDotW*cDotWInverse);
				Y1 = (int)(bDotW*cDotWInverse);
				dx = X1 - X;
				dy = Y1 - Y;
				
				
				for( k = offset, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++, z_left+=dz){
					z = z_left >> zDepthAccuracy;
					
					if(zbuffer[index] < z){
						
						temp1 = ((d_x/offset) + X)&widthMask;
						textureIndex = temp1 + ((((d_y/offset) + Y)&heightMask)<<widthBits);
						
						temp2 = displacementMap[textureIndex][0]/distortionLevel ;
						
						
						distortedIndex = index + temp2 + screen_w*(displacementMap[textureIndex][1]/distortionLevel);
						if(distortedIndex < 0 || distortedIndex >= screen_pixel_count || z < zbuffer[distortedIndex])
							distortedIndex = index;
						
						
						
						temp1 = screen[distortedIndex];
						r = (((temp1&0xff0000) * alpha) >>16) >> 8;
						g = (((temp1&0xff00) * alpha) >> 8) >> 8;
						b = ((temp1&0xff) * alpha) >> 8;
						temp2= (colorTable[Texture[textureIndex]]&0xFEFEFE)>>1;
						
						offScreen[index] = ((r<<16)|(g<<8)|b) + temp2;
					}else{
						offScreen[index] = screen[index];
					}
				}
				
				break;
			}
		}
		
		
		//copy the content of the offscreen buffer to screen buffer.
		for(int i = start; i <= end; i++){
			int offset = xRight[i] - xLeft[i];
			temp = gameData.screenTable[i];
			int index = xLeft[i] + temp;
			for(k = offset; k >0; k--, index++){
				screen[index] = offScreen[index];
				
			}
		}
		
	}
	
	//Texture mapper  for rendering Translucent polygons (immersed in fog)
	public static void renderGlass3_fog(){
		int[] screen = mainThread.screen;
		int[] offScreen = mainThread.offScreen;
	
		diffuse_I = poly.diffuse_I;
		
		byte[][] displacementMap =  poly.myTexture.displacementMap;
		int distortedIndex = 0;
		zbuffer = mainThread.zBuffer; 
		int dz, dx, z;
		
		int d = 0;
		boolean renderEdge = false;
		
		int r,g,b, r_,g_,b_;
		int color = poly.fogVolume.color;
		float fadeRate = poly.fogVolume.fadeRate;
		
		r_ = (color & 0xff0000) >> 16;
		g_ = (color & 0x00ff00) >> 8;
		b_ = color & 0x0000ff;
		
		
		A_offset = A.x*16;
		B_offset = B.x*16;
		C_offset = C.x*16;
			
		double Aoffset,Boffset,Coffset;
			
		
		
			
		for(int i = start; i <= end; i++){
			x_left=xLeft[i];
			x_right=xRight[i];
				dx = x_right - x_left;
				if(dx == 0)
					continue;
				
				W.set(x_left-half_screen_w, -i + half_screen_h, vector.Z_length);
				aDotW = A.dot(W);
				bDotW = B.dot(W);
				cDotW = C.dot(W);
				
				//find the texture coordinate for the start pixel of the scanline
				cDotWInverse = 1/cDotW;
				X = (int)(aDotW*cDotWInverse);
				Y = (int)(bDotW*cDotWInverse);
				X1 = X;
				Y1 = Y;
				
				int temp = gameData.screenTable[i];
				int index;
				
				z_left = zLeft[i] ;
				z_right = zRight[i] ;
				dz = (z_right - z_left)/dx;
				
				for(int j = x_left; j < x_right; j+=16){
					X = X1;
					Y = Y1;
					
					index = j + temp;
					if(x_right - j > 15){
						//find the correct texture coordinate every 16 pixels.
						//Use the interpolation values for the rest pixels.
						aDotW+=A_offset;
						bDotW+=B_offset;
						cDotW+=C_offset;
						cDotWInverse = 1/cDotW;
						X1 = (int)(aDotW*cDotWInverse);
						Y1 = (int)(bDotW*cDotWInverse);
						dx = X1 - X;
						dy = Y1 - Y;
						
						if(j - x_left > 31 && x_right - j > 64){
							//if(cameraInside || z_left > offScreen[index]){
								d = (int)(1024*Math.sqrt(fadeRate/z_left));
							
								if(d > 950)
									d = 950;
								//}else{
								
							//	d = outsideVolumeFadeRate + (float)Math.sqrt(fadeRate/z_left - fadeRate/offScreen[index]);		
								
							//}
							renderEdge = false;
						}else
							renderEdge = true;
						
					
						for( k = 16, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++, z_left+=dz){
							z = z_left >> zDepthAccuracy;
							
							if(zbuffer[index] < z){
								if(renderEdge){
									//if(cameraInside || z_left > offScreen[index]){
									d = (int)(1024*Math.sqrt(fadeRate/z_left));
									
									if(d > 950)
										d = 950;
										//}else{
										
									//	d = outsideVolumeFadeRate + (float)Math.sqrt(fadeRate/z_left - fadeRate/offScreen[index]);
									//}
								}
								
								
								textureIndex = (((d_x>>4) + X)&widthMask) + ((((d_y>>4) + Y)&heightMask)<<widthBits);
							
								distortedIndex = index + displacementMap[textureIndex][0] + screen_w*displacementMap[textureIndex][1];
								if(distortedIndex < 0 || distortedIndex >= screen_pixel_count)
									distortedIndex = index;
								
								
								
								temp1 = screen[distortedIndex];
								r = (temp1&0xff0000) >>16;
								g = (temp1&0xff00) >> 8;
								b = (temp1&0xff);
								
								
								
								offScreen[index] = ((r + (((r_ -r)*d)>>10))<<16) + ((g + (((g_ -g)*d)>>10))<<8) +  (b + (((b_ -b)*d)>>10));
								
							}else{
								offScreen[index] = screen[index];
							}
							
						}
					
						continue;
					}
					
					int offset = x_right - j;
					Aoffset = A.x*offset;
					Boffset = B.x*offset;
					Coffset = C.x*offset;
		
					aDotW+=Aoffset;
					bDotW+=Boffset;
					cDotW+=Coffset;
					cDotWInverse = 1/cDotW;
					X1 = (int)(aDotW*cDotWInverse);
					Y1 = (int)(bDotW*cDotWInverse);
					dx = X1 - X;
					dy = Y1 - Y;
					
					
					for( k = offset, d_x = 0, d_y = 0; k >0; k--, d_x+=dx, d_y+=dy, index++, z_left+=dz){
						z = z_left >> zDepthAccuracy;
						
						if(zbuffer[index] < z){
							
							//if(cameraInside || z_left > offScreen[index]){
							d = (int)(1024*Math.sqrt(fadeRate/z_left));
							
							if(d > 950)
								d = 950;
								//}else{
								
							//	d = outsideVolumeFadeRate + (float)Math.sqrt(fadeRate/z_left - fadeRate/offScreen[index]);
							//}
							
							
							textureIndex = (((d_x/offset) + X)&widthMask) + ((((d_y/offset) + Y)&heightMask)<<widthBits);
							
							temp2 = displacementMap[textureIndex][0];
							
							
							distortedIndex = index + temp2 + screen_w*displacementMap[textureIndex][1];
							if(distortedIndex < 0 || distortedIndex >= screen_pixel_count)
								distortedIndex = index;
							
							
							
							temp1 = screen[distortedIndex];
							r = (temp1&0xff0000) >>16;
							g = (temp1&0xff00) >> 8;
							b = (temp1&0xff);
							
							
							offScreen[index] = ((r + (((r_ -r)*d)>>10))<<16) + ((g + (((g_ -g)*d)>>10))<<8) +  (b + (((b_ -b)*d)>>10));
							
						}else{
							offScreen[index] = screen[index];
						}
					}
					
					break;
				}
			}
			
			
			//copy the content of the offscreen buffer to screen buffer.
			for(int i = start; i <= end; i++){
				int offset = xRight[i] - xLeft[i];
				temp = gameData.screenTable[i];
				int index = xLeft[i] + temp;
				for(k = offset; k >0; k--, index++){
					screen[index] = offScreen[index];
					
				}
			}
			
		}
	
	public static void renderEnvironmentMappedPolygon(){
		zbuffer = mainThread.zBuffer; 
		int dz, dx, z, w, h, minimapLevel;
		w = 0;
		h = 0;
		minimapLevel = 0;
		
		float d = poly.vertex2D[0].z;
		
		if(d < 0.004){
			minimapLevel = 0;
			w = poly.myTexture.width;
			h = poly.myTexture.height;
		}else if(d < 0.008){
			minimapLevel = 1;
			w = poly.myTexture.width/2;
			h = poly.myTexture.height/2;
		}else if(d < 0.016){
			minimapLevel = 2;
			w = poly.myTexture.width/4;
			h = poly.myTexture.height/4;
		}else if(d < 0.032){
			minimapLevel = 3;
			w = poly.myTexture.width/8;
			h = poly.myTexture.height/8;
		}else if(d < 0.064){
			minimapLevel = 4;
			w = poly.myTexture.width/16;
			h = poly.myTexture.height/16;
		}else if(d < 0.128){
			minimapLevel = 5;
			w = poly.myTexture.width/32;
			h = poly.myTexture.height/32;
		}
		
		
		int halfWidth = w/2;
		int halfHeight = h/2;
		int totalSize = w * h;
		if(totalSize == 0)
			totalSize = 1;
		
		int[] CUBE_MAP_POSITIVE_X = poly.myTexture.CUBE_MAP_POSITIVE_X[minimapLevel];
		int[] CUBE_MAP_NEGATIVE_X = poly.myTexture.CUBE_MAP_NEGATIVE_X[minimapLevel];
		int[] CUBE_MAP_POSITIVE_Y = poly.myTexture.CUBE_MAP_POSITIVE_Y[minimapLevel];
		int[] CUBE_MAP_NEGATIVE_Y = poly.myTexture.CUBE_MAP_NEGATIVE_Y[minimapLevel];
		int[] CUBE_MAP_POSITIVE_Z = poly.myTexture.CUBE_MAP_POSITIVE_Z[minimapLevel];
		int[] CUBE_MAP_NEGATIVE_Z = poly.myTexture.CUBE_MAP_NEGATIVE_Z[minimapLevel];
		
		
		
		
		
		
		for(int i = start; i <= end; i++){
			int temp = gameData.screenTable[i];
				
			x_left = xLeft[i] + temp;
			x_right = xRight[i] + temp;
			dx = x_right - x_left;
			if(dx == 0)
				continue;
			
			z_left = zLeft[i] ;
			z_right = zRight[i];
			dz = (z_right - z_left)/dx;
			
			startReflection.set(RLeft[i]);
			endReflection.set(RRight[i]);
			dReflection.set(endReflection);
			dReflection.subtract(startReflection);
			dReflection.scale(1f/dx);
		
			for(int j = x_left; j < x_right; j++, z_left+=dz, startReflection.add(dReflection)){
				z = z_left >> zDepthAccuracy;
				if(zbuffer[j] < z){
					float x_abs  = Math.abs(startReflection.x);
					float y_abs  = Math.abs(startReflection.y);
					float z_abs  = Math.abs(startReflection.z);
					
					/* from http://www.nvidia.com/object/cube_map_ogl_tutorial.html
				    major axis
				    direction     target                              sc     tc    ma
				    ----------    ---------------------------------   ---    ---   ---
				     +rx          GL_TEXTURE_CUBE_MAP_POSITIVE_X_EXT   -rz    -ry   rx
				     -rx          GL_TEXTURE_CUBE_MAP_NEGATIVE_X_EXT   +rz    -ry   rx
				     +ry          GL_TEXTURE_CUBE_MAP_POSITIVE_Y_EXT   +rx    +rz   ry
				     -ry          GL_TEXTURE_CUBE_MAP_NEGATIVE_Y_EXT   +rx    -rz   ry
				     +rz          GL_TEXTURE_CUBE_MAP_POSITIVE_Z_EXT   +rx    -ry   rz
				     -rz          GL_TEXTURE_CUBE_MAP_NEGATIVE_Z_EXT   -rx    -ry   rz

				    Using the sc, tc, and ma determined by the major axis direction as specified in the table above, an updated (s,t) is calculated as follows

				    s   =   ( sc/|ma| + 1 ) / 2
				    t   =   ( tc/|ma| + 1 ) / 2
					*/
					
					
					if(x_abs >= y_abs && x_abs>= z_abs){
						
						if(startReflection.x >= 0){
							float X = (-startReflection.z/x_abs + 1) * halfWidth;
							float Y = (-startReflection.y/x_abs +1) * halfHeight;
				            screen[j] = CUBE_MAP_POSITIVE_X[((int)X + (int)Y*halfWidth*2)%totalSize];
						}else{
							float X = (startReflection.z/x_abs + 1) * halfWidth;
							float Y = (-startReflection.y/x_abs +1) * halfHeight;
				            screen[j] = CUBE_MAP_NEGATIVE_X[((int)X + (int)Y*halfWidth*2)%totalSize];
						}
					}else if(y_abs >= x_abs && y_abs>= z_abs){
						
						if(startReflection.y >= 0){
							float X = (startReflection.x/y_abs + 1) * halfWidth;
							float Y = (startReflection.z/y_abs +1) * halfHeight;
				            screen[j] = CUBE_MAP_POSITIVE_Y[((int)X + (int)Y*halfWidth*2)%totalSize];
						}else{
							float X = (startReflection.x/y_abs + 1) * halfWidth;
							float Y = (-startReflection.z/y_abs +1) * halfHeight;
				            screen[j] = CUBE_MAP_NEGATIVE_Y[((int)X + (int)Y*halfWidth*2)%totalSize];
						}
					}else{
						
						if(startReflection.z >= 0){
							float X = (startReflection.x/z_abs + 1) * halfWidth;
							float Y = (-startReflection.y/z_abs +1) * halfHeight;
				            screen[j] = CUBE_MAP_POSITIVE_Z[((int)X + (int)Y*halfWidth*2)%totalSize];
						}else{
							float X = (-startReflection.x/z_abs + 1) * halfWidth;
							float Y = (-startReflection.y/z_abs +1) * halfHeight;
				            screen[j] = CUBE_MAP_NEGATIVE_Z[((int)X + (int)Y*halfWidth*2)%totalSize];
						}
					}
					zbuffer[j] = (short)z;
				}
			}
		}
	}
	
	
	//render environment mapped polygon under the effect of fog
	public static void renderEnvironmentMappedPolygon_fog(){
		zbuffer = mainThread.zBuffer; 
		int[] offScreen = poly.fogVolume.offScreenBuffer;
		boolean cameraInside = poly.fogVolume.cameraInside;
		float outsideVolumeFadeRate = poly.fogVolume.outsideVolumeFadeRate;
		int textureColor, r,g,b, r_,g_,b_;
		int color = poly.fogVolume.color;
		float fadeRate = poly.fogVolume.fadeRate*0.8f;
		
		r_ = (color & 0xff0000) >>16;
		g_ = (color & 0x00ff00) >> 8;
		b_ = color & 0x0000ff;
		
		
		int dz, dx, z, w, h, minimapLevel;
		w = 0;
		h = 0;
		minimapLevel = 0;
		
		float d = poly.vertex2D[0].z;
		
		if(d < 0.004){
			minimapLevel = 0;
			w = poly.myTexture.width;
			h = poly.myTexture.height;
		}else if(d < 0.008){
			minimapLevel = 1;
			w = poly.myTexture.width/2;
			h = poly.myTexture.height/2;
		}else if(d < 0.016){
			minimapLevel = 2;
			w = poly.myTexture.width/4;
			h = poly.myTexture.height/4;
		}else if(d < 0.032){
			minimapLevel = 3;
			w = poly.myTexture.width/8;
			h = poly.myTexture.height/8;
		}else if(d < 0.064){
			minimapLevel = 4;
			w = poly.myTexture.width/16;
			h = poly.myTexture.height/16;
		}else if(d < 0.128){
			minimapLevel = 5;
			w = poly.myTexture.width/32;
			h = poly.myTexture.height/32;
		}
		
		
		int halfWidth = w/2;
		int halfHeight = h/2;
		int totalSize = w * h;
		if(totalSize == 0)
			totalSize = 1;
		
		int[] CUBE_MAP_POSITIVE_X = poly.myTexture.CUBE_MAP_POSITIVE_X[minimapLevel];
		int[] CUBE_MAP_NEGATIVE_X = poly.myTexture.CUBE_MAP_NEGATIVE_X[minimapLevel];
		int[] CUBE_MAP_POSITIVE_Y = poly.myTexture.CUBE_MAP_POSITIVE_Y[minimapLevel];
		int[] CUBE_MAP_NEGATIVE_Y = poly.myTexture.CUBE_MAP_NEGATIVE_Y[minimapLevel];
		int[] CUBE_MAP_POSITIVE_Z = poly.myTexture.CUBE_MAP_POSITIVE_Z[minimapLevel];
		int[] CUBE_MAP_NEGATIVE_Z = poly.myTexture.CUBE_MAP_NEGATIVE_Z[minimapLevel];
		
		
		
		
		
		
		for(int i = start; i <= end; i++){
			int temp = gameData.screenTable[i];
				
			x_left = xLeft[i] + temp;
			x_right = xRight[i] + temp;
			dx = x_right - x_left;
			if(dx == 0)
				continue;
			
			z_left = zLeft[i] ;
			z_right = zRight[i];
			dz = (z_right - z_left)/dx;
			
			startReflection.set(RLeft[i]);
			endReflection.set(RRight[i]);
			dReflection.set(endReflection);
			dReflection.subtract(startReflection);
			dReflection.scale(1f/dx);
		
			for(int j = x_left; j < x_right; j++, z_left+=dz, startReflection.add(dReflection)){
				z = z_left >> zDepthAccuracy;
				if(zbuffer[j] < z){
					float x_abs  = Math.abs(startReflection.x);
					float y_abs  = Math.abs(startReflection.y);
					float z_abs  = Math.abs(startReflection.z);
					
					if(x_abs >= y_abs && x_abs>= z_abs){
						
						if(startReflection.x >= 0){
							float X = (-startReflection.z/x_abs + 1) * halfWidth;
							float Y = (-startReflection.y/x_abs +1) * halfHeight;
							textureColor = CUBE_MAP_POSITIVE_X[((int)X + (int)Y*halfWidth*2)%totalSize];
						}else{
							float X = (startReflection.z/x_abs + 1) * halfWidth;
							float Y = (-startReflection.y/x_abs +1) * halfHeight;
							textureColor = CUBE_MAP_NEGATIVE_X[((int)X + (int)Y*halfWidth*2)%totalSize];
						}
					}else if(y_abs >= x_abs && y_abs>= z_abs){
						
						if(startReflection.y >= 0){
							float X = (startReflection.x/y_abs + 1) * halfWidth;
							float Y = (startReflection.z/y_abs +1) * halfHeight;
							textureColor = CUBE_MAP_POSITIVE_Y[((int)X + (int)Y*halfWidth*2)%totalSize];
						}else{
							float X = (startReflection.x/y_abs + 1) * halfWidth;
							float Y = (-startReflection.z/y_abs +1) * halfHeight;
							textureColor = CUBE_MAP_NEGATIVE_Y[((int)X + (int)Y*halfWidth*2)%totalSize];
						}
					}else{
						
						if(startReflection.z >= 0){
							float X = (startReflection.x/z_abs + 1) * halfWidth;
							float Y = (-startReflection.y/z_abs +1) * halfHeight;
							textureColor = CUBE_MAP_POSITIVE_Z[((int)X + (int)Y*halfWidth*2)%totalSize];
						}else{
							float X = (-startReflection.x/z_abs + 1) * halfWidth;
							float Y = (-startReflection.y/z_abs +1) * halfHeight;
							textureColor = CUBE_MAP_NEGATIVE_Z[((int)X + (int)Y*halfWidth*2)%totalSize];
						}
					}
					
					if(cameraInside || z_left > offScreen[j]){
						d = 0.15f + (float)Math.sqrt(fadeRate/z_left);
					}else{
						
						d = 0.15f + outsideVolumeFadeRate + (float)Math.sqrt(fadeRate/z_left - fadeRate/offScreen[j]);
					}
					if(d > 1)
						d = 1;
					
					r = (textureColor & 0xff0000) >> 16;
					g = (textureColor & 0x00ff00) >> 8;
					b = textureColor & 0x0000ff;
					r+= (r_-r)*d;
					g+= (g_-g)*d;
					b+= (b_-b)*d;
					screen[j] = ((int)(r*0.98) << 16) + ((int)(g*0.98) << 8) + (int)(b*0.98);
					
					zbuffer[j] = (short)z;
				}
			}
		}
	}
	
	
}