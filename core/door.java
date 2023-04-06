package core;

//3d model: door in the wall to the back of the main hall
public class door extends solidObject{
	
	float scale = 0.025f;
	
	public door(vector centre){
		//centre point of the model (in world coordinate)
		start = centre.myClone();
		this.centre = start.myClone();
		tempCentre = start.myClone();
		
		
		//define reference axis (in world coordinate)
		iDirection = new vector(1*scale,0,0);
		jDirection = new vector(0,1*scale*1.12f,0);
		kDirection = new vector(0,0,1*scale);
		
		iDirection.rotate_XZ(270);
		
		kDirection.rotate_XZ(270);
		
		//create a rough 3D cuboid boundary for this model.
		makeBoundary(0.1f, 0.125f, 0.105f);
		
		//create polygons
		makePolygons();
		
		//drawBoundary = true;
	}
	
	//all the polygon vertices  are hard coded here
	public void makePolygons(){
		polygons = new polygon3D[58];
		vector[] t;
		
		t = new vector[]{put(0.1, 0.125, 0.1), put(0.1, 0.125, 0.085), put(0.1, -0.125, 0.085), put(0.1, -0.125, 0.1)};
		polygons[4] = new polygon3D(t, put(0,0,0), put(0,0,0), put(0,0,0), null, 0,0,-1); 
		
		t = new vector[]{put(0.1, 0.125, 0.1), put(0.1, 0.125, 0.085), put(0.1, -0.125, 0.085), put(0.1, -0.125, 0.1)};
		polygons[13] = new polygon3D(t, put(0,0,0), put(0,0,0), put(0,0,0), null, 0,0,-1); 
		
		t = new vector[]{put(0.1, 0.125, 0.1), put(0.1, 0.125, 0.085), put(0.1, -0.125, 0.085), put(0.1, -0.125, 0.1)};
		polygons[2] = new polygon3D(t, put(0,0,0), put(0,0,0), put(0,0,0), null, 0,0,-1); 
		
		t = new vector[]{put(0.1, 0.125, 0.1), put(0.1, 0.125, 0.085), put(0.1, -0.125, 0.085), put(0.1, -0.125, 0.1)};
		polygons[3] = new polygon3D(t, put(0,0,0), put(0,0,0), put(0,0,0), null, 0,0,-1); 
		
		vector temp = new vector(0,0,0);
		int angle = 270;
		
		temp.set(0, 0,-0.015f*1*scale);
		temp.rotate_XZ(angle);
		start.add(temp);
		
		t = new vector[]{put(0.0851, 0.11, 0.095), put(0.0149, 0.11, 0.095), put(0.0149, -0.05, 0.095), put(0.0851, -0.05, 0.095)};
		polygons[0] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[14], 1f,1f, -1); 
		
		polygon3D glass2 = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[14], 1f,1f, 8); 
		glass2.diffuse_I = 0;
		sceneGraph.addTransparent(glass2);
		
		t = new vector[]{put(0.085, -0.05, 0.095), put(0.015, -0.05, 0.095), put(0.015, -0.05, 0.101), put(0.085, -0.05, 0.101)};
		polygons[5] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[15], 4f,1f, 3); 
		polygons[5].diffuse_I = 40;
		
		t = new vector[]{put(0.085, 0.11, 0.101), put(0.085, 0.11, 0.095), put(0.085, -0.05, 0.095), put(0.085, -0.05, 0.101)};
		polygons[6] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[15], 0.2f,6f, 3); 
		polygons[6].diffuse_I = 35;
		
		t = new vector[]{put(0.015, 0.11, 0.095), put(0.015, 0.11, 0.101), put(0.015, -0.05, 0.101), put(0.015, -0.05, 0.095)};
		polygons[7] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[15], 0.2f,6f, 3); 
		polygons[7].diffuse_I = 35;
		
		t = new vector[]{put(0.085, 0.11, 0.101), put(0.015, 0.11, 0.101), put(0.015, 0.11, 0.095), put(0.085, 0.11, 0.095)};
		polygons[8] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[15], 4f,1f, 3); 
		polygons[8].diffuse_I = 30;
		
		t = new vector[]{put(0.101, 0.126, 0.1), put(0.085, 0.126, 0.1), put(0.085, -0.126, 0.1), put(0.101, -0.126,0.1)};
		polygons[9] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[15], 1f,10f, 3); 
		polygons[9].diffuse_I = 20;
		
		t = new vector[]{put(0.015, 0.126, 0.1), put(0, 0.126, 0.1), put(0, -0.126, 0.1), put(0.015, -0.126,0.1)};
		polygons[10] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[15], 1f,10f, 3); 
		polygons[10].diffuse_I = 20;
		
		t = new vector[]{put(0.086, 0.126, 0.1), put(0.014, 0.126, 0.1), put(0.014, 0.11, 0.1), put(0.086, 0.11, 0.1)};
		polygons[11] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[15], 5f,1f, 3); 
		polygons[11].diffuse_I = 20;
		
		t = new vector[]{put(0.086, -0.05, 0.1), put(0.014, -0.05, 0.1), put(0.014, -0.126, 0.1), put(0.086, -0.126, 0.1)};
		polygons[12] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[15], 4f,3f, 3); 
		polygons[12].diffuse_I = 20;
		
		temp.set(-0.1f*1*scale, 0,0);
		temp.rotate_XZ(angle);
		start.add(temp);
		
		t = new vector[]{put(0.085, 0.11, 0.095), put(0.015, 0.11, 0.095), put(0.015, -0.05, 0.095), put(0.085, -0.05, 0.095)};
		polygons[1] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[14], 1f,1f, -1); 
		
		
		polygon3D glass1 = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[14], 1f,1f, 8); 
		glass1.diffuse_I = 0;
		sceneGraph.addTransparent(glass1);
		
		t = new vector[]{put(0.085, -0.05, 0.095), put(0.015, -0.05, 0.095), put(0.015, -0.05, 0.101), put(0.085, -0.05, 0.101)};
		polygons[14] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[15], 4f,1f, 3); 
		polygons[14].diffuse_I = 40;
		
		t = new vector[]{put(0.085, 0.11, 0.101), put(0.085, 0.11, 0.095), put(0.085, -0.05, 0.095), put(0.085, -0.05, 0.101)};
		polygons[15] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[15], 0.2f,6f, 3); 
		polygons[15].diffuse_I = 35;
		
		t = new vector[]{put(0.015, 0.11, 0.095), put(0.015, 0.11, 0.101), put(0.015, -0.05, 0.101), put(0.015, -0.05, 0.095)};
		polygons[16] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[15], 0.2f,6f, 3); 
		polygons[16].diffuse_I = 35;
		
		t = new vector[]{put(0.085, 0.11, 0.101), put(0.015, 0.11, 0.101), put(0.015, 0.11, 0.095), put(0.085, 0.11, 0.095)};
		polygons[17] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[15], 4f,1f, 3); 
		polygons[17].diffuse_I = 30;
		
		t = new vector[]{put(0.101, 0.126, 0.1), put(0.085, 0.126, 0.1), put(0.085, -0.126, 0.1), put(0.101, -0.126,0.1)};
		polygons[18] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[15], 1f,10f, 3); 
		polygons[18].diffuse_I = 20;
		
		t = new vector[]{put(0.015, 0.126, 0.1), put(0, 0.126, 0.1), put(0, -0.126, 0.1), put(0.015, -0.126,0.1)};
		polygons[19] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[15], 1f,10f, 3); 
		polygons[19].diffuse_I = 20;
		
		t = new vector[]{put(0.086, 0.126, 0.1), put(0.014, 0.126, 0.1), put(0.014, 0.11, 0.1), put(0.086, 0.11, 0.1)};
		polygons[20] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[15], 5f,1f, 3); 
		polygons[20].diffuse_I = 20;
		
		t = new vector[]{put(0.086, -0.05, 0.1), put(0.014, -0.05, 0.1), put(0.014, -0.126, 0.1), put(0.086, -0.126, 0.1)};
		polygons[21] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[15], 4f,3f, 3); 
		polygons[21].diffuse_I = 20;
		
		
		temp.set(0, 0.01f*1*scale,0.0002f*1*scale);
		temp.rotate_XZ(angle);
		start.add(temp);
		
		
		temp.set(0.102f*1*scale, 0,0);
		temp.rotate_XZ(angle);
		start.add(temp);
		
		t = new vector[]{put(0.01, 0.015, 0.1011), put(0.004, 0.015, 0.1011), put(0.004, 0.015, 0.105), put(0.01, 0.015, 0.105)};
		polygons[22] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[22].diffuse_I = 20;
		
		t = new vector[]{put(0.01, 0.015, 0.1011), put(0.01, 0.015, 0.105), put(0.01, 0.012, 0.105), put(0.01, 0.012, 0.1011)};
		polygons[23] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[23].diffuse_I = 18;
		
		t = new vector[]{put(0.004, 0.015, 0.105), put(0.004, 0.015, 0.1011), put(0.004, 0.012, 0.1011), put(0.004, 0.012, 0.105)};
		polygons[24] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[24].diffuse_I = 18;
		
		t = new vector[]{put(0.01, 0.012, 0.105), put(0.004, 0.012, 0.105), put(0.004, 0.012, 0.1011), put(0.01, 0.012, 0.1011)};
		polygons[25] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[25].diffuse_I = 16;
		
		
		temp.set(0, -0.04f*1*scale,0);
		temp.rotate_XZ(angle);
		start.add(temp);
		
		t = new vector[]{put(0.01, 0.015, 0.1011), put(0.004, 0.015, 0.1011), put(0.004, 0.015, 0.105), put(0.01, 0.015, 0.105)};
		polygons[26] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[26].diffuse_I = 20;
		
		t = new vector[]{put(0.01, 0.015, 0.1011), put(0.01, 0.015, 0.105), put(0.01, 0.012, 0.105), put(0.01, 0.012, 0.1011)};
		polygons[27] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[27].diffuse_I = 18;
		
		t = new vector[]{put(0.004, 0.015, 0.105), put(0.004, 0.015, 0.1011), put(0.004, 0.012, 0.1011), put(0.004, 0.012, 0.105)};
		polygons[28] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[28].diffuse_I = 18;
		
		t = new vector[]{put(0.01, 0.012, 0.105), put(0.004, 0.012, 0.105), put(0.004, 0.012, 0.1011), put(0.01, 0.012, 0.1011)};
		polygons[29] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[29].diffuse_I = 16;
		
		
		temp.set(0, 0.04f*1*scale,0);
		temp.rotate_XZ(angle);
		start.add(temp);
		
		double z = 0.107;
		double z2 = 0.105;
		double y1 = 0.025;
		double y2 = 0.017;
		double y3 = -0.029;
		double y4 = -0.037;
		double x1 = 0.01;
		double x2 = 0.009;
		double x3 = 0.005;
		double x4 = 0.004;
		t = new vector[]{put(x2, y1, z), put(x3, y1, z), put(x4, y2, z), put(x4, y3, z), put(x3, y4, z), put(x2, y4,z), put(x1, y3,z), put(x1,y2,z)};
		polygons[30] = new polygon3D(t, t[0], t[1], t[5], mainThread.textures[16], 1f,1f, 3); 
		polygons[30].diffuse_I = 20;
		
		t = new vector[]{put(x1,y2,z2), put(x1, y3,z2), put(x2, y4,z2), put(x3, y4, z2), put(x4, y3, z2), put(x4, y2, z2), put(x3, y1, z2), put(x2, y1, z2)};
		polygons[31] = new polygon3D(t, t[0], t[1], t[5], mainThread.textures[16], 1f,1f, 3); 
		polygons[31].diffuse_I = 20;
		
		t = new vector[]{ put(x3, y1, z), put(x2, y1, z), put(x2, y1, z2), put(x3, y1, z2)};
		polygons[32] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[32].diffuse_I = 16;
		
		t = new vector[]{ put(x4, y2, z), put(x3, y1, z), put(x3, y1, z2), put(x4, y2, z2)};
		polygons[33] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[33].diffuse_I = 16;
		
		t = new vector[]{ put(x4, y3, z), put(x4, y2, z), put(x4, y2, z2), put(x4, y3, z2)};
		polygons[34] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[34].diffuse_I = 14;
		
		t = new vector[]{ put(x3, y4, z), put(x4, y3, z), put(x4, y3, z2), put(x3, y4, z2)};
		polygons[35] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[35].diffuse_I = 14;
		
		t = new vector[]{ put(x2, y4,z), put(x3, y4, z), put(x3, y4, z2), put(x2, y4,z2)};
		polygons[36] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[36].diffuse_I = 12;
		
		t = new vector[]{ put(x1, y3,z), put(x2, y4,z), put(x2, y4,z2), put(x1, y3,z2)};
		polygons[37] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[37].diffuse_I = 14;
		
		t = new vector[]{ put(x1,y2,z), put(x1, y3,z), put(x1, y3,z2), put(x1,y2,z2)};
		polygons[38] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[38].diffuse_I = 14;
		
		t = new vector[]{ put(x2, y1, z), put(x1,y2,z), put(x1,y2,z2), put(x2, y1, z2)};
		polygons[39] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[39].diffuse_I = 14;
		
		temp.set(-0.017f*1*scale,0,0);
		temp.rotate_XZ(angle);
		start.add(temp);
		
		t = new vector[]{put(0.01, 0.015, 0.1011), put(0.004, 0.015, 0.1011), put(0.004, 0.015, 0.105), put(0.01, 0.015, 0.105)};
		polygons[40] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[40].diffuse_I = 20;
		
		t = new vector[]{put(0.01, 0.015, 0.1011), put(0.01, 0.015, 0.105), put(0.01, 0.012, 0.105), put(0.01, 0.012, 0.1011)};
		polygons[41] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[41].diffuse_I = 18;
		
		t = new vector[]{put(0.004, 0.015, 0.105), put(0.004, 0.015, 0.1011), put(0.004, 0.012, 0.1011), put(0.004, 0.012, 0.105)};
		polygons[42] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[42].diffuse_I = 18;
		
		t = new vector[]{put(0.01, 0.012, 0.105), put(0.004, 0.012, 0.105), put(0.004, 0.012, 0.1011), put(0.01, 0.012, 0.1011)};
		polygons[43] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[43].diffuse_I = 16;
		
		
		temp.set(0, -0.04f*1*scale,0);
		temp.rotate_XZ(angle);
		start.add(temp);
		
		t = new vector[]{put(0.01, 0.015, 0.1011), put(0.004, 0.015, 0.1011), put(0.004, 0.015, 0.105), put(0.01, 0.015, 0.105)};
		polygons[44] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[44].diffuse_I = 20;
		
		t = new vector[]{put(0.01, 0.015, 0.1011), put(0.01, 0.015, 0.105), put(0.01, 0.012, 0.105), put(0.01, 0.012, 0.1011)};
		polygons[45] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[45].diffuse_I = 18;
		
		t = new vector[]{put(0.004, 0.015, 0.105), put(0.004, 0.015, 0.1011), put(0.004, 0.012, 0.1011), put(0.004, 0.012, 0.105)};
		polygons[46] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[46].diffuse_I = 18;
		
		t = new vector[]{put(0.01, 0.012, 0.105), put(0.004, 0.012, 0.105), put(0.004, 0.012, 0.1011), put(0.01, 0.012, 0.1011)};
		polygons[47] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[47].diffuse_I = 16;
		
		temp.set(0, 0.04f*1*scale,0);
		temp.rotate_XZ(angle);
		start.add(temp);
		
		t = new vector[]{put(x2, y1, z), put(x3, y1, z), put(x4, y2, z), put(x4, y3, z), put(x3, y4, z), put(x2, y4,z), put(x1, y3,z), put(x1,y2,z)};
		polygons[48] = new polygon3D(t, t[0], t[1], t[5], mainThread.textures[16], 1f,1f, 3); 
		polygons[48].diffuse_I = 20;
		
		t = new vector[]{put(x1,y2,z2), put(x1, y3,z2), put(x2, y4,z2), put(x3, y4, z2), put(x4, y3, z2), put(x4, y2, z2), put(x3, y1, z2), put(x2, y1, z2)};
		polygons[49] = new polygon3D(t, t[0], t[1], t[5], mainThread.textures[16], 1f,1f, 3); 
		polygons[49].diffuse_I = 20;
		
		t = new vector[]{ put(x3, y1, z), put(x2, y1, z), put(x2, y1, z2), put(x3, y1, z2)};
		polygons[50] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[50].diffuse_I = 16;
		
		t = new vector[]{ put(x4, y2, z), put(x3, y1, z), put(x3, y1, z2), put(x4, y2, z2)};
		polygons[51] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[51].diffuse_I = 16;
		
		t = new vector[]{ put(x4, y3, z), put(x4, y2, z), put(x4, y2, z2), put(x4, y3, z2)};
		polygons[52] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[52].diffuse_I = 14;
		
		t = new vector[]{ put(x3, y4, z), put(x4, y3, z), put(x4, y3, z2), put(x3, y4, z2)};
		polygons[53] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[53].diffuse_I = 14;
		
		t = new vector[]{ put(x2, y4,z), put(x3, y4, z), put(x3, y4, z2), put(x2, y4,z2)};
		polygons[54] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[54].diffuse_I = 12;
		
		t = new vector[]{ put(x1, y3,z), put(x2, y4,z), put(x2, y4,z2), put(x1, y3,z2)};
		polygons[55] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[55].diffuse_I = 14;
		
		t = new vector[]{ put(x1,y2,z), put(x1, y3,z), put(x1, y3,z2), put(x1,y2,z2)};
		polygons[56] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[56].diffuse_I = 14;
		
		t = new vector[]{ put(x2, y1, z), put(x1,y2,z), put(x1,y2,z2), put(x2, y1, z2)};
		polygons[57] = new polygon3D(t, t[0], t[1], t[3], mainThread.textures[16], 1f,1f, 3); 
		polygons[57].diffuse_I = 14;
		
		
		
	
		for(int i = 0; i < polygons.length; i++){
			if(polygons[i].type == 3)
				polygons[i].diffuse_I-=4;
		}
		
		for(int i = 0; i < polygons.length; i++)
			polygons[i].diffuse_I = 63 - (31 - polygons[i].diffuse_I)*2;
		

		
	}
	


}
