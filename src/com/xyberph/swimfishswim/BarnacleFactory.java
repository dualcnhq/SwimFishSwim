package com.xyberph.swimfishswim;

import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;
import org.andengine.util.adt.pool.GenericPool;

public class BarnacleFactory {
	
	private static final BarnacleFactory INSTANCE = new BarnacleFactory();
	
	private BarnacleFactory() {	}
	
	GenericPool<Barnacle> pool;

	int nextX;
	int nextY;
	int dy;
	
	final int dx = 300;
	
	final int maxY = 500;
	final int minY = 300;
	
	public static final BarnacleFactory getInstance() {
		return INSTANCE;
	}

	public void create(final PhysicsWorld physics) {
		reset();
		pool = new GenericPool<Barnacle>(3) {
			@Override
			protected Barnacle onAllocatePoolItem() {
				Barnacle p = new Barnacle(0, 0, ResourceManager.getInstance().pillarRegion, 
							ResourceManager.getInstance().vbom, physics);
				return p;
			}
		};
	}
	
	public Barnacle next() {
		Barnacle p = pool.obtainPoolItem();
		p.setPosition(nextX, nextY);
		
		p.getScoreSensor().setTransform(nextX / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, 
				nextY / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, 0);
		
		p.getPillarUpBody().setTransform(nextX / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, 
				(nextY + p.getPillarShift()) / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, 0);
		
		p.getPillarDownBody().setTransform(nextX / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, 
				(nextY - p.getPillarShift()) / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, 0);	
		
		p.getScoreSensor().setActive(true);
		p.getPillarUpBody().setActive(true);
		p.getPillarDownBody().setActive(true);
		
		nextX += dx;
		nextY += dy;
		
		if (nextY == maxY || nextY == minY) {
			dy = -dy;
		}
		
		return p;
	}
	
	public void recycle(Barnacle p) {
		p.detachSelf();
		p.getScoreSensor().setActive(false);
		p.getPillarUpBody().setActive(false);
		p.getPillarDownBody().setActive(false);		
		p.getScoreSensor().setTransform(-1000, -1000, 0);
		p.getPillarUpBody().setTransform(-1000, -1000, 0);
		p.getPillarDownBody().setTransform(-1000, -1000, 0);
		pool.recyclePoolItem(p);
	}
	
	public void reset() {
		nextX = 650;
		nextY = 350;
		dy = 50;
	}

}
