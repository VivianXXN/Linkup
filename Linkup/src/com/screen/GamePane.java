package com.screen;

import com.beans.Background;
//import com.beans.Card;
import com.beans.CardGroup;
import com.beans.Path;
import com.core.Config;
import com.core.GameScreen;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class GamePane extends GameScreen {
	private CardGroup myCardGroup = CardGroup.getInstance();
	private Background myBackground = Background.getInstance();
	private int cardNumber;
	private int Combo;
	private int maxCombo;
	private Path myPath = Path.getInstance();
	private int Gametime;
	private String Information;
	private Timeline timeline;
	private KeyFrame keyFrame;
	{
		int duration = 1000;
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		keyFrame = new KeyFrame(Duration.millis(duration), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(mGameState != GameState.GAME_WIN){
					if(Gametime==0){
						if(mGameState != GameState.GAME_OVER){
							Config.FAILSOUND.play();
						}
						mGameState = GameState.GAME_OVER;					
					}else {
						Gametime--;	
						Information = Integer.toString(Gametime);
						
					}
				}
			}
		});
		timeline.getKeyFrames().add(keyFrame);
		timeline.play();
	}
	
	public GamePane(double width, double height) {
		super(width, height);
		Config.READYSOUND.play();
		Gametime = Config.TIMESET;
		addObject(myBackground);
		addObject(myCardGroup);
		addObject(myPath);
		start();
		mGameState = GameState.GAME_START;
	}
	
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.AQUAMARINE);
		if(mGameState == GameState.GAME_OVER){
			Information = "      Game Over!!!";
			gc.setFont(new Font("Arial", 40));
			gc.fillText(Information, 2*Config.xOffset, 2*Config.yOffset);
			return ;
		}else{			
			super.draw(gc);
			gc.setFont(new Font("Arial", 40));
			gc.fillText(Information, 2*Config.xOffset, 2*Config.yOffset);
		}
		cardNumber=myCardGroup.getNumber();
		if(cardNumber==0){
			mGameState=GameState.GAME_WIN;
			Information = "      You Win!!!";
			myPath.getRoad().clear();
		}
		
		Combo=myCardGroup.getCombo();
		gc.setFont(new Font("Arial", 30));
		gc.fillText("Combo:"+Combo, Config.PaneWidth+Config.GWIDTH-5*Config.xOffset, Config.SHEIGHT-4*Config.yOffset);
		maxCombo=myCardGroup.getMaxcombo();
		gc.setFont(new Font("Arial", 30));
		gc.fillText("maxCombo:"+maxCombo, Config.PaneWidth+Config.GWIDTH-5*Config.xOffset,Config.SHEIGHT-2*Config.yOffset);
	}


	public void upSetGroup() {
		Config.BEGINSOUND.play();
		myCardGroup.upSetMap();
		myPath.getRoad().clear();
	}
	
	public void reSetMap() {
		myCardGroup.reSetMap();	
		Config.READYSOUND.play();
		mGameState = GameState.GAME_START;
		Gametime = Config.TIMESET;
		myPath.reset();
	}
	
	public void searchCouple() {
		Config.TIPSOUND.play();		
		myCardGroup.searchCouple();
		myPath.getRoad().clear();
	}

}
