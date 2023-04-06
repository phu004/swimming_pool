package core;
//the texture dimension should only be power of 2
import java.awt.image.PixelGrabber;
import java.awt.*;

public class texture {
	//holds the pixel data in 16bits color format
	public int[] Texture;
	
	//hold mipmaps of the original texture
	public int[][] mipmap;
	
	//hold gamma correction values for generating mipmap
	public double gammaCorrectionValue = 1;
	
	
	//holds the pixel data in 24bits color format
	//public int[] texture_24bits;
	
	//holds distortion information on the texture for each texel
	public byte[][] displacementMap;
	
	//hold brightness information on the texture for each texel
	public byte[] lightMap;
	
	//hold the pixel data for cube maps
	public int[][] CUBE_MAP_POSITIVE_X;
	public int[][] CUBE_MAP_NEGATIVE_X;
	public int[][] CUBE_MAP_POSITIVE_Y;
	public int[][] CUBE_MAP_NEGATIVE_Y;
	public int[][] CUBE_MAP_POSITIVE_Z;
	public int[][] CUBE_MAP_NEGATIVE_Z;
	
	//dimension of the texture
	public int height, width, heightMask, widthMask, widthBits, heightBits;
	
	//temporary array for holding image pixels
	public static int[]TextureTemp;
	
	
	//produce a basic texture
	public texture(Image img, int widthBits , int heightBits, String type){
		init(img, widthBits,heightBits);
		
		//sky box texture
		if(type.equals("skybox")){
			Texture = new int[width*height];
			for(int i = 0; i < width*height; i ++){
				Texture[i] = TextureTemp[i];
			}
		}
		
		//lightmap texture
		if(type.equals("lightmap")){
			lightMap = new byte[width*height];
			for(int i = 0; i < width*height; i ++){
				lightMap[i] = (byte)((TextureTemp[i] & 255)/2);
			
				if(lightMap[i] > 2){
					int I = (int)(lightMap[i] *1.5);
					
					if(I > 127)
						I = 127;
					lightMap[i] = (byte)I;
				}
				
				
			}
			
		}
		//free memory
		TextureTemp = null;
		img = null;
	}
	
	
	//produce texture with displacement map
	public texture(Image img, Image distortion, int widthBits, int heightBits, double distortionDepth, String type){
		//load texture
		
		PixelGrabber pg;
		
		if(img != null)
			pg = init(img, widthBits,heightBits);
		else{
			this.widthBits = widthBits;
			this.heightBits = heightBits;

			height = (int)Math.pow(2, heightBits);
			width = (int)Math.pow(2, widthBits);
			
			heightMask = height -1;
			widthMask = width - 1;
			
			TextureTemp = new int[width*height];
		}
		
		//load displacementMap image 
		
		if(type.equals("displacementMap")){
			pg = new PixelGrabber(distortion, 0, 0, width, height, TextureTemp, 0, width);
			try {
				pg.grabPixels();
			}catch(Exception e){
				System.out.println(e);
			}
		}

		
		
		//generate distortion map
		displacementMap = new byte[height*width][2];
		for(int j = 0; j < height; j++){
			for(int i = 0; i < width; i++){
				if(i != 0 && j != 0 && i != (width-1) && j != (height-1)){
					int index = i + j*width;
					//find the color gradient of the distortion image in x direction 
					displacementMap[index][0] = (byte)(((TextureTemp[index +1]&0xff) - (TextureTemp[index -1]&0xff))*distortionDepth);
					
					//find the color gradient of the distortion image in y direction 
					displacementMap[index][1] = (byte)(((TextureTemp[index + width]&0xff) - (TextureTemp[index - width]&0xff))*distortionDepth);
				}
			}
		}
		
		//free memory
		TextureTemp = null;
		img = null;
		distortion = null;
		pg = null;
	}
	
	
	//produce  texture with mipmap
	public texture(int mipmapLevel, Image img, int widthBits , int heightBits, String type, double gammaCorrectionValue){
		init(img, widthBits,heightBits);
		//create mipmap
		if(type.equals("mipmap")){
			mipmap = new int[mipmapLevel][];
			
		
			
			//assign the first mipmap to the original image
			mipmap[0] = Texture;
			int w = width;
			int h = height;
			
			
			for(int i = 1; i < mipmapLevel; i++){
				//each mipmap is one-fourth the total area of the previous one
				w/=2;
				h/=2;
				if(w == 0 || h ==0){
					//Minimum size of mipmap is 1 x 1
					mipmap[i] = new int[1];
					mipmap[i][0] = mipmap[i-1][0];
				}else{
					mipmap[i] = new int[w*h];
					for(int j = 0; j < h; j++){
						for(int k = 0; k < w; k++){
							int index1 = k*2 + j*2*w*2;
							int index2 = index1+1;
							int index3 = k*2 + (j*2+1)*w*2;
							int index4 = index3+1;
							
							int r1 = (mipmap[i-1][index1]& 63488) >> 11;
							int r2 = (mipmap[i-1][index2]& 63488) >> 11;
							int r3 = (mipmap[i-1][index3]& 63488) >> 11;
							int r4 = (mipmap[i-1][index4]& 63488) >> 11;
			
							int g1 = (mipmap[i-1][index1] & 2016) >> 5;
							int g2 = (mipmap[i-1][index2] & 2016) >> 5;
							int g3 = (mipmap[i-1][index3] & 2016) >> 5;
							int g4 = (mipmap[i-1][index4] & 2016) >> 5;
							
							int b1 = mipmap[i-1][index1] & 31;
							int b2 = mipmap[i-1][index2] & 31;
							int b3 = mipmap[i-1][index3] & 31;
							int b4 = mipmap[i-1][index4] & 31;
							
							int r_average = (int)((r1 + r2 + r3 + r4)*gammaCorrectionValue/4);
							int g_average = (int)((g1 + g2 + g3 + g4)*gammaCorrectionValue*0.99/4);    //Darken the green channel a little bit since we are using 5-6-5 16bits model
							int b_average = (int)((b1 + b2 + b3 + b4)*gammaCorrectionValue/4);
						
							//write to texture memory
							mipmap[i][k + j*w] = r_average <<11 | g_average << 5 | b_average;
						}
					}
				
				}
			}		
		}
		
		//free memory
		TextureTemp = null;
		img = null;
	}
	
	//produce cube map texture
	public texture(Image img1, Image img2, Image img3, Image img4, Image img5,Image img6, int widthBits , int heightBits, String type){
		this.widthBits = widthBits;
		this.heightBits = heightBits;

		height = (int)Math.pow(2, heightBits);
		width = (int)Math.pow(2, widthBits);
		
		heightMask = height -1;
		widthMask = width - 1;
		
		CUBE_MAP_POSITIVE_X = new int[6][height * width];
		CUBE_MAP_NEGATIVE_X = new int[6][height * width];
		CUBE_MAP_POSITIVE_Y = new int[6][height * width];
		CUBE_MAP_NEGATIVE_Y = new int[6][height * width];
		CUBE_MAP_POSITIVE_Z = new int[6][height * width];
		CUBE_MAP_NEGATIVE_Z = new int[6][height * width];
		
		PixelGrabber pg;
		
		pg = new PixelGrabber(img1, 0, 0, width, height, CUBE_MAP_POSITIVE_X[0], 0, width);
		try {
			pg.grabPixels();
		}catch(Exception e){
			System.out.println(e);
		}
		
		pg = new PixelGrabber(img2, 0, 0, width, height, CUBE_MAP_NEGATIVE_X[0], 0, width);
		try {
			pg.grabPixels();
		}catch(Exception e){
			System.out.println(e);
		}
		
		pg = new PixelGrabber(img3, 0, 0, width, height, CUBE_MAP_POSITIVE_Y[0], 0, width);
		try {
			pg.grabPixels();
		}catch(Exception e){
			System.out.println(e);
		}
		
		pg = new PixelGrabber(img4, 0, 0, width, height, CUBE_MAP_NEGATIVE_Y[0], 0, width);
		try {
			pg.grabPixels();
		}catch(Exception e){
			System.out.println(e);
		}
		
		pg = new PixelGrabber(img5, 0, 0, width, height, CUBE_MAP_POSITIVE_Z[0], 0, width);
		try {
			pg.grabPixels();
		}catch(Exception e){
			System.out.println(e);
		}
		
		pg = new PixelGrabber(img6, 0, 0, width, height, CUBE_MAP_NEGATIVE_Z[0], 0, width);
		try {
			pg.grabPixels();
		}catch(Exception e){
			System.out.println(e);
		}
		
		pg = null;
		
		addGrayAndComputeMiniMap(CUBE_MAP_POSITIVE_X);
		addGrayAndComputeMiniMap(CUBE_MAP_NEGATIVE_X);
		addGrayAndComputeMiniMap(CUBE_MAP_POSITIVE_Y);
		addGrayAndComputeMiniMap(CUBE_MAP_NEGATIVE_Y);
		addGrayAndComputeMiniMap(CUBE_MAP_POSITIVE_Z);
		addGrayAndComputeMiniMap(CUBE_MAP_NEGATIVE_Z);
	}
	
	private void addGrayAndComputeMiniMap(int[][] pixels){
		int w = width;
		int h = height;
		
		for(int i = 0; i < height * width; i++){
			int r_ = 110;
			int g_ = 110;
			int b_ = 110;
			
			int r = (pixels[0][i] & 0xff0000) >> 16;
			int g = (pixels[0][i] & 0xff00) >> 8;
			int b = (pixels[0][i] & 0xff);
			
			r = (int)(r + (r_-r)*0.5);
			g = (int)(g + (g_-g)*0.5);
			b = (int)(b + (b_-b)*0.5);
			
			pixels[0][i] = b + (g << 8) + (r << 16);
		}
		
		for(int i = 1; i < 6; i++){
			//each mipmap is one-fourth the total area of the previous one
			w/=2;
			h/=2;
		
			pixels[i] = new int[w*h];
			for(int j = 0; j < h; j++){
				for(int k = 0; k < w; k++){
					int index1 = k*2 + j*2*w*2;
					int index2 = index1+1;
					int index3 = k*2 + (j*2+1)*w*2;
					int index4 = index3+1;
					
					int r1 = (pixels[i-1][index1]& 0xff0000) >> 16;
					int r2 = (pixels[i-1][index2]& 0xff0000) >> 16;
					int r3 = (pixels[i-1][index3]& 0xff0000) >> 16;
					int r4 = (pixels[i-1][index4]& 0xff0000) >> 16;
	
					int g1 = (pixels[i-1][index1] & 0xff00) >> 8;
					int g2 = (pixels[i-1][index2] & 0xff00) >> 8;
					int g3 = (pixels[i-1][index3] & 0xff00) >> 8;
					int g4 = (pixels[i-1][index4] & 0xff00) >> 8;
					
					int b1 = pixels[i-1][index1] & 0xff;
					int b2 = pixels[i-1][index2] & 0xff;
					int b3 = pixels[i-1][index3] & 0xff;
					int b4 = pixels[i-1][index4] & 0xff;
					
					int r_average = (int)((r1 + r2 + r3 + r4)*gammaCorrectionValue/4);
					int g_average = (int)((g1 + g2 + g3 + g4)*gammaCorrectionValue/4);    
					int b_average = (int)((b1 + b2 + b3 + b4)*gammaCorrectionValue/4);
				
					//write to texture memory
					pixels[i][k + j*w] = r_average <<16 | g_average << 8 | b_average;
				}
			}
			
			
		}	
	}
	
	//Initialization 
	private PixelGrabber init(Image img, int widthBits , int heightBits){
		this.widthBits = widthBits;
		this.heightBits = heightBits;

		height = (int)Math.pow(2, heightBits);
		width = (int)Math.pow(2, widthBits);
		
		heightMask = height -1;
		widthMask = width - 1;
		
		TextureTemp = new int[width*height];
		
		//load texture image and store it as an array of int
		PixelGrabber pg = new PixelGrabber(img, 0, 0, width, height, TextureTemp, 0, width);
		try {
			pg.grabPixels();
		}catch(Exception e){
			System.out.println(e);
		}
		
		Texture = new int[width*height];
		//convert the color depth of the texture from 24bits to 15bits
		float r, g, b;
		for(int i = 0; i < width*height; i ++){
			r = (TextureTemp[i] & 0x00ff0000)>>16;
			g = (TextureTemp[i] & 0x0000ff00)>>8;
			b = (TextureTemp[i] & 0x000000ff);
			r = r/8;
			g = g/4;
			b = b/8;
			Texture[i] = (int)r <<11 | (int)g << 5 | (int)b;
		}
		
		img = null;
		
		return pg;
	}
}
