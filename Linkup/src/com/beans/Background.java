package com.beans;

import com.core.Config;
import com.core.GameObject;

import javafx.scene.canvas.GraphicsContext;

public class Background extends GameObject {
	
	private Background() {
	}
	private static Background instance = new Background();
	
	public static Background getInstance() {
		return instance;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(Config.BKIMAGE, 0, 0, Config.SWIDTH,Config.SHEIGHT);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
