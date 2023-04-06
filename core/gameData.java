package core;
//Store useful arithmetic data for the game engine such as 
//Cos/Sin look up table, color palette, etc...
public class gameData {
	public static int[] random;
	public static int randomIndex;
	public static float[] sin;  
	public static float[] cos;
	public static int[][] colorTable, colorTableTemp;
	public static int[] screenTable;
	public static float[] sqrts;
	
	public static void makeData(){
		//Make a screen table, so a pixel index can be retrived quickly
		screenTable = new int[mainThread.screen_h];
		for(int i = 0; i < mainThread.screen_h; i++)
			screenTable[i] = mainThread.screen_w*i;
		
		//Make random number table
		random = new int[1000];
		for(int i = 0; i < 1000; i ++)
			random[i] = (int)(Math.random()*100);
		
		
		//Make sin and cos look up tables
		sin = new float[361];
		cos = new float[361];
		for(int i = 0; i < 361; i ++){
			sin[i] = (float)Math.sin(Math.PI*i/180);
			cos[i] = (float)Math.cos(Math.PI*i/180);
		}
		
		//make color palette.
		//The main color palette has 32768 (15bits) different colors with 128 different intensity levels, 
		//the default intensity is at level 31 .
		
		if(colorTable == null)
			colorTable = new int[128][65536];
		if(colorTableTemp == null)
			colorTableTemp = new int[65536][128];
		
		double r, g, b, dr, dg, db;
		int r_, g_, b_;
		
		for(int i = 0; i < 65536; i++){
			r = (double)((i & 63488) >> 11)*8;
			g = (double)((i & 2016) >> 5)*4;
			b = (double)((i & 31))*8;
			
			dr = r*0.55/64;
			dg = g*0.55/64;
			db = b*0.55/64;
			
			
			
			//calculated the intensity from lvl 0 ~ 63
		    for(int j = 0; j < 64; j++){
				r_ = (int)(r-dr*j);
				g_ = (int)(g-dg*j);
				b_ = (int)(b-db*j);
				
				
				colorTableTemp[i][63 - j] = b_ + (g_<<8)+ (r_<<16);
			}
		    
			
		    
		    dr = r*0.5/64;
			dg = g*0.5/64;
			db = b*0.5/64;
			
		
		    
			double d = (dr + dg + db)/3;
			
		    //calculated the intensity from lvl 64 ~ 127
		    for(int j = 1; j <= 64; j++){
				r_ = (int)(r+d*j);
				g_ = (int)(g+d*j);
				b_ = (int)(b+d*j);
				if(r_ > 255)
					r_ = 255;
				if(g_ > 255)
					g_ = 255;
				if(b_ > 255)
					b_ = 255;
			
				
				colorTableTemp[i][63 + j] = b_ + (g_<<8)+ (r_<<16);
			}
		}
		
		for(int i = 0; i < 128; i++){
			for(int j = 0; j <65536; j++ )
				colorTable[i][j] = colorTableTemp[j][i];
		}
		
		//free memory used by creating color table
		colorTableTemp = null;
		
		//create square root look up table
		sqrts = new float[32768];
		sqrts[0] = 1f;
		for(int i = 1; i < 32768; i++){
			sqrts[i] = (float)Math.sqrt(i)/40;
			if(sqrts[i] < 0.05f)
				sqrts[i] = 10000000;
			sqrts[i] = 1f/sqrts[i];
		}
		
		System.gc();
		
	}
	
	//get a random number
	public static int getRandom(){
		randomIndex++;
		if(randomIndex >= 1000)
			randomIndex=0;
		return random[randomIndex];
		
	}
	
	
	
	//It frees the data stored when the applet is finished
	public static void destory(){
		random = null;
		sin = null;  
		cos = null;
		colorTable = null;
		screenTable = null;
	}
}
