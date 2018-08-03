package com.beans;

import java.util.Stack;

import com.core.Config;
import com.core.GameObject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

public class Path extends GameObject{
	private int roadPoint = 0; 
	private boolean showAll = false;
	private Path() {
	}
	private static Path instance = new Path();
	private Stack<Point> road = new Stack<Point>();
	
	public Stack<Point> getRoad() {
		return road;
	}
	public void setRoad(Stack<Point> road) {
		this.road = road;
	}
	public static Path getInstance() {
		return instance;
	}

	@Override
	public void draw(GraphicsContext gc) {		
		if(showAll){
			for (int i = 0; i < road.size(); i++) {
				gc.drawImage(Config.PAIMAGE,road.get(i).getX()*Config.CARDWIDTH+Config.CARDWIDTH/3+Config.xOffset, road.get(i).getY()*Config.CARDHEIGHT+Config.CARDHEIGHT/3+Config.yOffset
						, Config.CARDWIDTH/3, Config.CARDHEIGHT/3);	
			}
		}else{
			if(!road.isEmpty()){
				int i=roadPoint%road.size();
				gc.drawImage(Config.PAIMAGE,road.get(i).getX()*Config.CARDWIDTH+Config.CARDWIDTH/3+Config.xOffset, road.get(i).getY()*Config.CARDHEIGHT+Config.CARDHEIGHT/3+Config.yOffset
						, Config.CARDWIDTH/3, Config.CARDHEIGHT/3);	
			}
		}
	}
	
	@Override
	public void init() {
		super.init();
		showAll = false;
		roadPoint = 0;
		Timeline timeline = new Timeline();
		int duration = 100;
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(duration), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(!showAll){
					roadPoint++;
					if(roadPoint== road.size()){
						roadPoint = 0;
						showAll = true;
					}
				}
			}
		});	
		timeline.getKeyFrames().add(keyFrame);
		timeline.play();
	}
	
	

	public void setShowAll(boolean showAll) {
		this.showAll = showAll;
	}
	public void setRoadPoint(int roadPoint) {
		this.roadPoint = roadPoint;
	}
	
	@Override
	public void update() {
		
	}
	
	public void reset(){
		road.clear();
	}
}
