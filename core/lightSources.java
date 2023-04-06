package core;

//stores  directions of light from several light sources
public class lightSources {
	
	public static vector ls = new vector(0, -1, 0);
	
	public static void init(){
		ls.unit();
	}
}
