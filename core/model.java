package core;

public interface model {
	//update the model, perform AI actions if there is any
	public void update();
	
	//get a rough 3D boundary of the model in camera coordinate
	public polygon3D[] getBoundary();
	
	//get centre of this model in camera coordinate
	public vector getCentre();
	
	//get centre of this model in world coordinate
	public vector getRealCentre();
	
	//draw the polygons of the model
	public void draw();
	
	//whether this model is visible from camera position
	public boolean getVisibility();
	
	//whether the camera is inside the model's bounding box
	public boolean testCameraisInside();
	
}