/**
 * Copyright (C) 2013 Martin Varga <android@kul.is>
 */
package com.xyberph.swimfishswim;

import org.andengine.extension.physics.box2d.PhysicsFactory;

import com.badlogic.gdx.physics.box2d.FixtureDef;

public class Constants {

	public static final int CW = 1024;
	public static final int CH = 800;
	public static final int FPS_LIMIT = 60;
	
	
	public final static String BODY_WALL = "wall";
	public final static String BODY_ACTOR = "actor";
	public final static String BODY_SENSOR = "sensor";
	
	public static final FixtureDef FISH_FIXTURE = PhysicsFactory.createFixtureDef(1f, 0f, 1f, false);
	public static final FixtureDef WALL_FIXTURE = PhysicsFactory.createFixtureDef(1f, 0f, 1f, false);
	public static final FixtureDef CEILLING_FIXTURE = PhysicsFactory.createFixtureDef(1f, 0f, 0f, false);
	public static final FixtureDef SENSOR_FIXTURE = PhysicsFactory.createFixtureDef(1f, 0f, 1f, true);
	
	public static final float SPEED_X = 6;
	public static final float GRAVITY = -20;
	public static final float SPEED_Y = 12;
	
	public static final String KEY_HISCORE = "high score";
}
