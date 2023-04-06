package core;

//determine the drawing orders for polygons and models
public class geometry {
	
	public static vector temp = new vector(0,0,0);
	
	//determine which 3d model should be rendered first based on their geometry locations
	public final static boolean compareModels(model a, model b){
	
		if(a.testCameraisInside() == true)
			return true;
		
		if(b.testCameraisInside() == true)
			return false;
		
		
		polygon3D[] aPolygons = a.getBoundary();
		polygon3D[] bPolygons = b.getBoundary();
		
		vector normal;
		

		//if there exist a back facing polygon p from the bounding box of object a,  such that the entire bounding box of object b is on the front side of p,
		//then object a is in front of object b.
		boolean AIsInfrontOfB = false;
		for(int i = 0; i < 6; i ++){
			temp.set(camera.position);
			temp.subtract(aPolygons[i].realCentre);
			if(temp.dot(aPolygons[i].realNormal) <= 0){
				normal = aPolygons[i].normal;
				AIsInfrontOfB = true;
				for(int j = 0; j < 6; j++){
					temp.set(bPolygons[j].centre);
					temp.subtract(aPolygons[i].centre);
					if(temp.dot(normal) < 0){
						AIsInfrontOfB = false;
						break;
					}
				}
				if(AIsInfrontOfB){
					
					
					
					return true;
				}
			}
		}
		
		/*boolean BIsInfrontOfA = false;
		for(int i = 0; i < 6; i ++){
			if(bPolygons[i].visible == false){
				normal = bPolygons[i].normal;
				BIsInfrontOfA = true;
				for(int j = 0; j < 6; j++){
					temp.set(aPolygons[j].centre);
					temp.subtract(bPolygons[i].centre);
					if(temp.dot(normal) < 0){
						BIsInfrontOfA = false;
						break;
					}
				}
				if(BIsInfrontOfA){
					
					break;
				}
			}
		}
		
		if(AIsInfrontOfB && !BIsInfrontOfA)
			return true;
		*/
		
		
		return false;
	}

	
	
	//determine which polygon should be rendered first based on their geometry locations
	public static boolean comparePolygons(polygon3D a, polygon3D b){
		if(!a.visible)
			return false;
		if(!b.visible)
			return true;

	
		if(a.tempVertex[0].z < b.tempVertex[0].z && a.tempVertex[1].z < b.tempVertex[1].z && a.tempVertex[2].z < b.tempVertex[2].z && a.tempVertex[3].z < b.tempVertex[3].z)
			return true;

		vector tempVector = new vector(0,0,0);
		
		boolean aIsAboveB = false;
		boolean bIsAboveA = false;

		boolean inside = true;
		for(int i = 0; i < b.tempVertex.length; i++){
			tempVector.set(b.tempVertex[i]);
			tempVector.subtract(a.centre);
			tempVector.unit();

			if(tempVector.dot(a.normal) > 0.0001 ){
				inside = false;
				break;
			}

		}
		if(inside)
			aIsAboveB = true;

		if(!aIsAboveB){
			inside = true;
			for(int i = 0; i <a.tempVertex.length; i++){
				tempVector.set(a.tempVertex[i]);
				tempVector.subtract(b.centre);
				tempVector.unit();

				if(tempVector.dot(b.normal) < -0.0001 ){
					inside = false;
					break;
				}
			}

			if(inside)
				aIsAboveB = true;
		}
		

		
		if(aIsAboveB)
			return true;

		return false;
	}
}
