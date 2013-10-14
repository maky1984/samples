package com.maky;

import java.util.ArrayList;
import java.util.List;

public class CollisionDetection {

	private List<ICollisionObject> objects;
	
	public CollisionDetection() {
		objects = new ArrayList<ICollisionObject>();
	}
	
	public void addObject(ICollisionObject object) {
		synchronized(objects) {
			objects.add(object);
		}
	}
	
	public void removeObject(ICollisionObject object) {
		synchronized(objects) {
			objects.remove(object);
		}
	}
	
	private boolean intersect(ICollisionObject obj1, ICollisionObject obj2) {
		return intersectSphere(obj1, obj2);
	}
	
	private boolean intersectSphere(ICollisionObject obj1, ICollisionObject obj2) {
		int dx = obj1.getX() - obj2.getX();
		int dy = obj1.getY() - obj2.getY();
		int dz = obj1.getZ() - obj2.getZ();
		double distance = Math.sqrt(dx*dx + dy*dy + dz*dz);
		return distance < (obj1.getRadius() + obj2.getRadius());
	}
	
	public ICollisionObject check(ICollisionObject object) {
		ICollisionObject[] objs;
		synchronized(objects) {
			objs = objects.toArray(new ICollisionObject[objects.size()]);
		}
		ICollisionObject result = null;
		for (ICollisionObject target: objs) {
			if (intersect(target, object)) {
				result = object;
				break;
			}
		}
		return result;
	}
	
}
