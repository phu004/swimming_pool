package core;

//3D Java Swimming Pool  
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.awt.image.*;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.ImageIO;


public class mainThread extends JFrame implements KeyListener, ActionListener{

	public static int[] screen;
	public static int[] screen2;
	public static short[] zBuffer;
	public static int[] offScreen;
	public static BufferedImage doubleBuffer;
	public static BufferedImage doubleBuffer2;
	public static BufferedImage bf;
	
	public static Ticker t;
	public static int frameInterval;
	
	public static int frameIndex;
	public static long lastDraw;
	public static int sleepTime;
	public static int framePerSecond, cpuUsage;
	public static double thisTime, lastTime;
	
	public static texture[] textures;
	public static byte[][] lightMapTextures;
	public static int[][] lightMapTexturesInfo;
	public static camera Camera;
	public static int polygonCount;
	public static antialiasFilter AF;
	public static JPanel panel;
	
	public static int screen_w = 1024;
	public static int screen_h = 682;
	public static int half_screen_w = screen_w/2;
	public static int half_screen_h = screen_h/2;
	public static int screen_pixel_count = screen_w * screen_h;
	
	
	public mainThread(){
		setTitle("Swimming Pool by phu004");
		panel= (JPanel) this.getContentPane();
		panel.setPreferredSize(new Dimension(screen_w, screen_h));
		panel.setMinimumSize(new Dimension(screen_w,screen_h));
		panel.setLayout(null);     
		
		setResizable(false); 
		pack();
		setVisible(true);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		
		//position camera at 0,0,0
		Camera = new camera(new vector(0,-0.002f,-0.017f));
		
		//load resource if the applet is loaded for the first time. (i.e refresh browser will not cause the applet to load resources again)
		if(textures == null){
			//create screen buffer
			doubleBuffer =  new BufferedImage(screen_w, screen_h, BufferedImage.TYPE_INT_RGB);
			DataBuffer dest = doubleBuffer.getRaster().getDataBuffer();
			screen = ((DataBufferInt)dest).getData();
			
			doubleBuffer2 =  new BufferedImage(screen_w, screen_h, BufferedImage.TYPE_INT_RGB);
			DataBuffer dest2 = doubleBuffer2.getRaster().getDataBuffer();
			screen2 = ((DataBufferInt)dest2).getData();
			
			
			//create depth buffer
			zBuffer = new short[screen_w*screen_h];
			
			//create off screen buffer 
			offScreen = new int[screen_w*screen_h];
		
			
			//Create look up tables
			gameData.makeData();
			
			
			//init rasterizer
			rasterizer.init();
			
			//init antialias filter
			antialiasFilter.init();
			
			
			//init light source
			lightSources.init();
	
			
			
			//Load textures
			lightMapTextures = new byte[18][];
			lightMapTexturesInfo = new int[18][3];

			String imageFolder = "";
			textures = new texture[39];
			
		
			imageFolder = "../images/";
			try{
			textures[0] = new texture(ImageIO.read(getClass().getResource(imageFolder + "sky1.jpg")), 9, 9, "skybox");
			
			textures[1] = new texture(ImageIO.read(getClass().getResource(imageFolder + "sky2.jpg")), 9, 9, "skybox");
			textures[2] = new texture(ImageIO.read(getClass().getResource(imageFolder + "sky3.jpg")), 9, 9, "skybox");
			textures[3] = new texture(ImageIO.read(getClass().getResource(imageFolder + "sky4.jpg")), 9, 9, "skybox");
			textures[4] = new texture(ImageIO.read(getClass().getResource(imageFolder + "sky5.jpg")), 9, 9, "skybox");
			textures[5] = new texture(ImageIO.read(getClass().getResource(imageFolder + "sky6.jpg")), 9, 9, "skybox");
			
			textures[6] = new texture(8, ImageIO.read(getClass().getResource(imageFolder + "1.jpg")), 9, 9, "mipmap", 1);
			textures[7] = new texture(8, ImageIO.read(getClass().getResource(imageFolder + "2.jpg")), 9, 9, "mipmap", 1);
			
			textures[8] = new texture(null, ImageIO.read(getClass().getResource(imageFolder + "3.jpg")), 7, 7, 0.8, "displacementMap");
			textures[9] = new texture(8, ImageIO.read(getClass().getResource(imageFolder + "4.jpg")), 8, 8, "mipmap", 1.03);
			textures[10] = new texture(8, ImageIO.read(getClass().getResource(imageFolder + "5.jpg")), 7, 7, "mipmap", 1.03);
			
			textures[11] = new texture(8, ImageIO.read(getClass().getResource(imageFolder + "6.jpg")), 9, 9, "mipmap",1.03);
		
			textures[12] = new texture(8, ImageIO.read(getClass().getResource(imageFolder + "7.jpg")), 9, 9, "mipmap",1.03);
			textures[13] = new texture(8, ImageIO.read(getClass().getResource(imageFolder + "8.jpg")), 9, 9, "mipmap", 1.0188);
			textures[14] = new texture(ImageIO.read(getClass().getResource(imageFolder + "9.jpg")), ImageIO.read(getClass().getResource(imageFolder + "11.jpg")), 8, 9, 3, "displacementMap");
			textures[15] = new texture(ImageIO.read(getClass().getResource(imageFolder + "10.jpg")), 7, 7, "basic");
			textures[16] = new texture(ImageIO.read(getClass().getResource(imageFolder + "12.jpg")), 7, 7, "basic");
			textures[17] = new texture(8, ImageIO.read(getClass().getResource( imageFolder + "13.jpg")), 6, 6, "mipmap", 1.03);
			textures[18] = new texture(8, ImageIO.read(getClass().getResource( imageFolder + "14.jpg")), 8, 8, "mipmap", 1.02);
			textures[19] = new texture(ImageIO.read(getClass().getResource( imageFolder + "15.jpg")), 8, 4, "basic");
			textures[20] = new texture(8, ImageIO.read(getClass().getResource( imageFolder + "16.jpg")), 9, 9, "mipmap", 1.03);
			textures[21] = new texture(ImageIO.read(getClass().getResource( imageFolder + "17.jpg")), ImageIO.read(getClass().getResource( imageFolder + "18.jpg")), 7, 7, 0.3, "displacementMap");
			textures[22] = new texture(8, ImageIO.read(getClass().getResource( imageFolder + "19.jpg")), 8, 9, "mipmap", 1.07);
			textures[23] = new texture(8, ImageIO.read(getClass().getResource( imageFolder + "20.jpg")), 9, 9, "mipmap", 1.03);
			textures[24] = new texture(8, ImageIO.read(getClass().getResource( imageFolder + "21.jpg")), 8, 8, "mipmap", 1.03);
			
			textures[25] = new texture(ImageIO.read(getClass().getResource( imageFolder + "17.jpg")), ImageIO.read(getClass().getResource( imageFolder + "18.jpg")), 7, 7, 0.3, "displacementMap");
			
			textures[26] = new texture(ImageIO.read(getClass().getResource( imageFolder + "23.jpg")), ImageIO.read(getClass().getResource( imageFolder + "22.jpg")), 8, 8, 0.8, "displacementMap");
			
			textures[27] = new texture(ImageIO.read(getClass().getResource( imageFolder + "24.jpg")), 9, 10, "lightmap");
			lightMapTextures[0] = textures[27].lightMap;
			lightMapTexturesInfo[0][0] = textures[27].widthBits;
			lightMapTexturesInfo[0][1] = textures[27].widthMask;
			lightMapTexturesInfo[0][2] = textures[27].heightMask;
			
			textures[28] = new texture(8, ImageIO.read(getClass().getResource( imageFolder + "25.jpg")), 8, 8, "mipmap", 1);
			textures[29] = new texture(8, ImageIO.read(getClass().getResource( imageFolder + "26.jpg")), 8, 8, "mipmap", 1.03);
			textures[30] = new texture(ImageIO.read(getClass().getResource( imageFolder + "27.jpg")), 8, 8, "lightmap");
			lightMapTextures[1] = textures[30].lightMap;
			lightMapTexturesInfo[1][0] = textures[30].widthBits;
			lightMapTexturesInfo[1][1] = textures[30].widthMask;
			lightMapTexturesInfo[1][2] = textures[30].heightMask;
			
			textures[31] = new texture(8, ImageIO.read(getClass().getResource( imageFolder + "28.jpg")), 8, 8, "mipmap", 1);
			textures[32] = new texture(8, ImageIO.read(getClass().getResource( imageFolder + "29.jpg")), 8, 7, "mipmap", 1);
			textures[33] = new texture(ImageIO.read(getClass().getResource( imageFolder + "17.jpg")), ImageIO.read(getClass().getResource( imageFolder + "18.jpg")), 7, 7, 0.1, "displacementMap");
			textures[34] = new texture(ImageIO.read(getClass().getResource( imageFolder + "17.jpg")), ImageIO.read(getClass().getResource( imageFolder + "18.jpg")), 7, 7, 0, "displacementMap");
			
			
			textures[35] = new texture(ImageIO.read(getClass().getResource( imageFolder + "30.jpg")),
									   ImageIO.read(getClass().getResource( imageFolder + "31.jpg")),
									   ImageIO.read(getClass().getResource( imageFolder + "32.jpg")),
									   ImageIO.read(getClass().getResource( imageFolder + "33.jpg")),
									   ImageIO.read(getClass().getResource( imageFolder + "34.jpg")),
									   ImageIO.read(getClass().getResource( imageFolder + "35.jpg")),
									   8, 8, "cubeMap");
			textures[36] = new texture(ImageIO.read(getClass().getResource( imageFolder + "36.jpg")),
					   ImageIO.read(getClass().getResource( imageFolder + "37.jpg")),
					   ImageIO.read(getClass().getResource( imageFolder + "38.jpg")),
					   ImageIO.read(getClass().getResource( imageFolder + "39.jpg")),
					   ImageIO.read(getClass().getResource( imageFolder + "40.jpg")),
					   ImageIO.read(getClass().getResource( imageFolder + "41.jpg")),
					   8, 8, "cubeMap");
			textures[37] = new texture(ImageIO.read(getClass().getResource( imageFolder + "42.jpg")),
					   ImageIO.read(getClass().getResource( imageFolder + "43.jpg")),
					   ImageIO.read(getClass().getResource( imageFolder + "44.jpg")),
					   ImageIO.read(getClass().getResource( imageFolder + "45.jpg")),
					   ImageIO.read(getClass().getResource( imageFolder + "46.jpg")),
					   ImageIO.read(getClass().getResource( imageFolder + "47.jpg")),
					   8, 8, "cubeMap");
			textures[38] = new texture(ImageIO.read(getClass().getResource( imageFolder + "48.jpg")),
					   ImageIO.read(getClass().getResource( imageFolder + "49.jpg")),
					   ImageIO.read(getClass().getResource( imageFolder + "50.jpg")),
					   ImageIO.read(getClass().getResource( imageFolder + "51.jpg")),
					   ImageIO.read(getClass().getResource( imageFolder + "52.jpg")),
					   ImageIO.read(getClass().getResource( imageFolder + "53.jpg")),
					   8, 8, "cubeMap");
			
					                  
			} catch(Exception e){System.out.println(e);}	
			//create scene
			sceneGraph.createScene();
			
			
			frameIndex = 0;
			frameInterval = 28;
			lastDraw = 0;
		}
		
		
		
		//Add key handler
		addKeyListener(this);
		requestFocus();
		
		//Add ticker
		t = new Ticker(0);
		t.addActionListener(this);
		t.start();
		
		//start a daemon thread which will sleep for the duration of the applet.
		Thread   dt   =   new   Thread(new   DaemonThread() );
        dt.setDaemon(true);
        dt.start(); 
        
        //start another thread to handle antialiasing
		AF = new antialiasFilter();
		Thread antialiasThread = new Thread(AF);
		antialiasThread.start();
  
	}
	
	//This method is called every time the ticker ticks 
	public void actionPerformed(ActionEvent e){	
		//cap frame rate to around 25.
		frameIndex++;
		
		polygonCount = 0;
		
		
		
	
		 
		//Clears the z-buffer. All depth values are set to 0.
		clearDepthBuffer();
		
		//update camera
		Camera.update();
				
		//update and draw scene 
		rasterizer.screen = mainThread.screen;
		sceneGraph.updateAndDraw();
		
	
		

		//copy the screen buffer to video memory
		if(this.getGraphics() != null && AF!= null){
		

			//wait till aa filter finishes
			while(AF.isFiltering){
				
				try {
					Thread.sleep(1);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			//apply antialias filter
			if(antialiasFilter.AA_level == 1){
				antialiasFilter.currentScreen = screen;
				synchronized(AF) {
				
					AF.notify();
			    }
			}
			
		
			if(frameIndex %2 == 0){
				bf = doubleBuffer;
				paintComponent(panel.getGraphics());
				
			}else if(frameIndex != 1){
				bf= doubleBuffer2;
				paintComponent(panel.getGraphics());
			}
			
			
			
			
			
			
			int[] s;
			s = screen;
			screen = screen2;
			screen2 = s;
			
			//calculate frame rate
			if(frameIndex%30==0){
				double thisTime = System.currentTimeMillis();
				framePerSecond = (int)(1000/((thisTime - lastTime)/30));
				lastTime = thisTime;
			}
			sleepTime = 0;
			while(System.currentTimeMillis()-lastDraw<frameInterval){
				try {
					Thread.sleep(1);
					sleepTime++;
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			lastDraw=System.currentTimeMillis();
		}
		
	}
	
	
	
	public void paintComponent(Graphics g){		
		
		
		//copy the pixel information to the video memory
		Graphics2D g2 =(Graphics2D)bf.getGraphics(); //(Graphics2D)g;
		
		
		//display polygon count and frame rate
		g2.setColor(Color.BLACK);
		g2.drawString("FPS: " + framePerSecond + "   "  +  "Polygons: "  + polygonCount *2 + "    " + "Thread Sleep: " + sleepTime +  "ms    ", 5, 15);
	
		g.drawImage(bf, 0, 0, this);
		
		
	
	}
	
	public void clearDepthBuffer(){
		zBuffer[0] = 0;
		for(int i = 1; i < screen_pixel_count; i+=i)
			System.arraycopy(zBuffer, 0, zBuffer, i, screen_pixel_count - i >= i ? i : screen_pixel_count - i);
	}
		


	//read keyboard inputs
	public void keyPressed(KeyEvent e){
		if(e.getKeyChar() == 'w' || e.getKeyChar() == 'W')
			camera.MOVE_FORWARD = true;
		else if(e.getKeyChar() == 's' || e.getKeyChar() == 'S')
			camera.MOVE_BACKWARD = true;
		else if(e.getKeyChar() == 'a' || e.getKeyChar() == 'A')
			camera.SLIDE_LEFT = true;
		else if(e.getKeyChar() == 'd' || e.getKeyChar() == 'D')
			camera.SLIDE_RIGHT = true;


		if(e.getKeyCode() == KeyEvent.VK_UP)
			camera.UP_TYPED= true;
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
			camera.DOWN_TYPED = true;
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
			camera.LEFT_TYPED = true;
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			camera.RIGHT_TYPED = true;
		
	
		
	}
	
	public void keyReleased(KeyEvent e){
		if(e.getKeyChar() == 'w' || e.getKeyChar() == 'W')
			camera.MOVE_FORWARD = false;
		else if(e.getKeyChar() == 's' || e.getKeyChar() == 'S')
			camera.MOVE_BACKWARD = false;
		else if(e.getKeyChar() == 'a' || e.getKeyChar() == 'A')
			camera.SLIDE_LEFT = false;
		else if(e.getKeyChar() == 'd' || e.getKeyChar() == 'D')
			camera.SLIDE_RIGHT = false;

		if(e.getKeyCode() == KeyEvent.VK_UP)
			camera.UP_TYPED= false;
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
			camera.DOWN_TYPED = false;
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
			camera.LEFT_TYPED = false;
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			camera.RIGHT_TYPED = false;
		
		

	}
	

	//unused method
	public final void keyTyped(KeyEvent e){}
	public final void update(Graphics g){}
}