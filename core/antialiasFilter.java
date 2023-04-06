package core;

public class antialiasFilter implements Runnable{

	public static int[] currentScreen;
	
	
	
	public boolean isFiltering;
	
	public static int AA_level;
	
	
	
	public static void init(){
		currentScreen = mainThread.screen;
		
		
		AA_level = 1;
	}
	
	public void run(){
		while(true){
			synchronized (this) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//not working :(
			//applyBoxFilter();
		}
	}
	
	
	

	
	public  void applyBoxFilter(){
		isFiltering = true;
		
		
		int index,rowIndex;	
		int r1, r2, r3, r4, 
		g1, g2, g3, g4,
		b1,b2,b3,b4, 
		r_average, g_average, b_average, 
		color1, color2, color3, color4, color5, color6, color7, color, r, g, b;
		
		
		
	
		
		boolean equalOnX_rightEnd = false;
		boolean equalOnY_rightEnd = false;	
		boolean equalOnX_leftEnd = false;
	
		for(int i =478; i > 1; i--){
			int counter = 0;
			for(int j = 1; j < 639; j++){
				index = j+ i*640;
				color = currentScreen[index];
				color1 = currentScreen[index + 1];
				color2 = currentScreen[index + 640];
				
				r = (color&0xff0000) >>16;
				r1 = (color1&0xff0000) >> 16;
				r2 = (color2&0xff0000) >> 16;
				g = (color&0xff00) >>8;
				g1 = (color1&0xff00) >> 8;
				g2 = (color2&0xff00) >> 8;
				b = color&0xff;
				b1 = color1&0xff;
				b2 = color2&0xff;
				
					
				equalOnX_rightEnd = Math.abs(r - r1) < 64 &&  Math.abs(g - g1) < 64 &&  Math.abs(b - b1) < 64;
				
				equalOnY_rightEnd = Math.abs(r - r2) < 64 &&  Math.abs(g - g2) < 64 &&  Math.abs(b - b2) < 64;
				
				
						  
				if(equalOnX_rightEnd && !equalOnY_rightEnd){
					
				
					counter++;
				}else {
					if(counter >= 6 && counter < 512){
						
						
						
						if(!equalOnX_rightEnd && !equalOnY_rightEnd){
							color3 = currentScreen[index - counter -1];
							r3 = (color3&0xff0000) >> 16;
							g3 = (color3&0xff00) >> 8;
							b3 = color3&0xff;
							equalOnX_leftEnd = Math.abs(r - r3) < 64 &&  Math.abs(g - g3) < 64 &&  Math.abs(b - b3) < 64;
							
							
							
							
							if(equalOnX_leftEnd){
								for(int k = index + 640 - counter, c = 0; c < counter; c++, k++){
									color1 = currentScreen[k-640];
									color2 = currentScreen[k];
									
									r1 = (((color2&0xff0000)>>16) * c  + ((color1&0xff0000)>>16) * (counter -c)) /counter;
									g1 = (((color2&0xff00)>>8) * c  + ((color1&0xff00)>>8) * (counter -c)) /counter;
									b1 = ((color2&0xff) * c  + (color1&0xff) * (counter -c)) /counter;
									currentScreen[k] = r1 << 16 | g1 << 8 | b1;
								}
							}else{
								for(int k = index + 640 - counter, c = 0; c < counter; c++, k++){
							
								color1 = currentScreen[k];
								color2 = currentScreen[k -640];
								r1 = (((color1&0xff0000)>>16) + ((color2&0xff0000)>>16))>>1;
								g1 = (((color1&0xff00)>>8) + ((color2&0xff00)>>8))>>1;
								b1 = ((color1&0xff) + (color2&0xff))>>1;
								
								currentScreen[k -640] = r1 << 16 | g1 << 8 | b1;
								}
							}
							
						}else if((equalOnX_rightEnd && equalOnY_rightEnd)){
							for(int k = index + 640 - counter, c = 0; c < counter; c++, k++){
								color1 = currentScreen[k-640];
								color2 = currentScreen[k];
								
								r1 = (((color1&0xff0000)>>16) * c  + ((color2&0xff0000)>>16) * (counter -c)) /counter;
								g1 = (((color1&0xff00)>>8) * c  + ((color2&0xff00)>>8) * (counter -c)) /counter;
								b1 = ((color1&0xff) * c  + (color2&0xff) * (counter -c)) /counter;
							
								
								currentScreen[k] =  r1 << 16 | g1 << 8 | b1;
							}
					
							
						}else if(!equalOnX_rightEnd && !equalOnY_rightEnd){
							
							
						}
						
						
					}
					counter = 0;
				}
			}
		}
	
	
		
		boolean equalOnY_bottomEnd = false;
		boolean equalOnX_bottomEnd = false;
		boolean equalOnY_topEnd = false;
	
		for(int i =639; i > 1; i--){
			int counter = 0;
			for(int j = 1; j < 479; j++){
				index = i+ j*640;
				color = currentScreen[index];
				color1 = currentScreen[index + 640];
				color2 = currentScreen[index + 1];
				
				r = (color&0xff0000) >>16;
				r1 = (color1&0xff0000) >> 16;
				r2 = (color2&0xff0000) >> 16;
				g = (color&0xff00) >>8;
				g1 = (color1&0xff00) >> 8;
				g2 = (color2&0xff00) >> 8;
				b = color&0xff;
				b1 = color1&0xff;
				b2 = color2&0xff;
				
					
				equalOnY_bottomEnd = Math.abs(r - r1) < 48 &&  Math.abs(g - g1) < 48 &&  Math.abs(b - b1) < 48;
				
				equalOnX_bottomEnd = Math.abs(r - r2) < 48 &&  Math.abs(g - g2) < 48 &&  Math.abs(b - b2) < 48;
				
				
						  
				
				
				if(equalOnY_bottomEnd && !equalOnX_bottomEnd){
		
					
					counter++;
				}else {
					if(counter >= 6 && counter < 256){
						
						
						if(!equalOnY_bottomEnd && !equalOnX_bottomEnd ){
							color3 = currentScreen[index - counter* 640 - 640];
							r3 = (color3&0xff0000) >> 16;
							g3 = (color3&0xff00) >> 8;
							b3 = color3&0xff;
							equalOnY_topEnd = Math.abs(r - r3) < 48 &&  Math.abs(g - g3) < 48 &&  Math.abs(b - b3) < 48;
						
							
							
							if(equalOnY_topEnd){
								for(int k = index - counter*640 + 1, c = 0; c < counter; c++, k+=640){
									color1 = currentScreen[k-1];
									color2 = currentScreen[k];
									
									r1 = (((color2&0xff0000)>>16) * c  + ((color1&0xff0000)>>16) * (counter -c)) /counter;
									g1 = (((color2&0xff00)>>8) * c  + ((color1&0xff00)>>8) * (counter -c)) /counter;
									b1 = ((color2&0xff) * c  + (color1&0xff) * (counter -c)) /counter;
									
									currentScreen[k] = r1 << 16 | g1 << 8 | b1;
								}
							}else {
								for(int k = index - counter*640 + 1, c = 0; c < counter; c++, k+=640){
									color1 = currentScreen[k];
									color2 = currentScreen[k -1];
									r1 = (((color1&0xff0000)>>16) + ((color2&0xff0000)>>16))>>1;
									g1 = (((color1&0xff00)>>8) + ((color2&0xff00)>>8))>>1;
									b1 = ((color1&0xff) + (color2&0xff))>>1;
									
									currentScreen[k -1] = r1 << 16 | g1 << 8 | b1;
								}
							}
							
						}else if((equalOnY_bottomEnd && equalOnX_bottomEnd)){
							for(int k = index - counter*640 + 1, c = 0; c < counter; c++, k+=640){
								color1 = currentScreen[k- 1];
								color2 = currentScreen[k];
								
								r1 = (((color1&0xff0000)>>16) * c  + ((color2&0xff0000)>>16) * (counter -c)) /counter;
								g1 = (((color1&0xff00)>>8) * c  + ((color2&0xff00)>>8) * (counter -c)) /counter;
								b1 = ((color1&0xff) * c  + (color2&0xff) * (counter -c)) /counter;
								
								currentScreen[k] = r1 << 16 | g1 << 8 | b1;
							}
						}
						
					}
					counter = 0;
				}
			}
		}
		
		
		
		
		color1 = currentScreen[2+2*640];
		color = currentScreen[1+2*640];
		r1 = (color1& 0xff0000) >> 16;
		r = (color& 0xff0000) >> 16;
		g1 = (color1 & 0xff00) >> 8;
		g = (color & 0xff00) >> 8;
		b1 = color1 & 0xff;
		b = color & 0xff;
		
		
		for(int i = 2; i < 479; i++){
			rowIndex = i*640;
			for(int j = -1; j < 638; j++){
				index = j + rowIndex;
				
				color2 = color;
				color = color1;
				color1 = currentScreen[index+1];
				
				
				r2 = r;
				r = r1;
				r1 = (color1& 0xff0000) >> 16;
			
				
				g2=g;
				g = g1;
				g1 = (color1 & 0xff00) >> 8;
			
				
				b2=b;
				b = b1;
				b1 = color1 & 0xff;
				
				
				color5 = (g1 + g2 )>>1;
				color6 = (r1 + r2 )>>1;
				color7 = (b1 + b2 )>>1;
				
				if(Math.abs(color5 -g) < 48 && Math.abs(color6 -r) < 48 && Math.abs(color7 -b) < 48)
					continue;
				
				color3 = currentScreen[index+640];
				color4 = currentScreen[index-640];
				g3 = (color3 & 0xff00) >> 8;
				g4 = (color4 & 0xff00) >> 8;
				r3 = (color3& 0xff0000) >> 16;
				r4 = (color4& 0xff0000) >> 16;
				b3 = color3 & 0xff;
				b4 = color4 & 0xff;
				
				r_average = (color6 +  + r3 + r4 + (r))>>2;
				g_average = (color5 + g3 + g4 +  (g))>>2;    
				b_average = (color7  + b3 + b4 + (b))>>2;
				
			
				
				currentScreen[index] = r_average <<16 | g_average << 8 | b_average;
				
			}
		}

		
		
			
		
		
		isFiltering = false;
	}
			
	
	
	
	

}
