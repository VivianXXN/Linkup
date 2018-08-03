package com.beans;


import com.core.Config;
import com.core.GameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Card extends GameObject{
	
	private int cardNumber;
	private Point myPoint;
	private boolean isClicked = false;
	private boolean isTiped = false;

	public Point getMyPoint() {
		return myPoint;
	}

//	public void changeCardXY(int x,int y) {
//		this.myPoint.setX(x);
//		this.myPoint.setY(y);
//		this.setX(x*Config.CARDWIDTH);
//		this.setY(y*Config.CARDHEIGHT);
//	}

	public boolean isClicked() {
		return isClicked;
	}
	
	public void setTiped(boolean isTiped) {
		this.isTiped = isTiped;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Card(int cardNumber,int x,int y){
		this.cardNumber = cardNumber;
		this.myPoint = new Point(x, y);
		this.setX(x*Config.CARDWIDTH+Config.xOffset);
		this.setY(y*Config.CARDHEIGHT+Config.yOffset);
		this.setWidth(Config.CARDWIDTH);
		this.setHeight(Config.CARDHEIGHT);
	}

	@Override
	public void draw(GraphicsContext gc) {
		if(cardNumber == -1){
			//gc.setFill(Color.WHITE);
			//gc.fillRect(getX(), getY(), getWidth(), getHeight());
			return;
		}
		if(!isClicked && !isTiped){
			gc.drawImage(Config.CARDIMAGES[cardNumber], getX(), getY(), getWidth(), getHeight());			
		}else{
			Image rImage = Config.CARDIMAGES[cardNumber];
			PixelReader pixelReader = rImage.getPixelReader();  
	        // Create WritableImage  
			WritableImage wImage = new WritableImage((int)rImage.getWidth(),(int)rImage.getHeight());  
	        PixelWriter pixelWriter = wImage.getPixelWriter();
	        for(int y = 0; y < rImage.getHeight(); y++){  
	            for(int x = 0; x < rImage.getWidth(); x++){
	            	Color color = pixelReader.getColor(x, y);
	            	if(isClicked){
	            		color = color.invert();	            		
	            	}else if(isTiped){
	            		color = color.interpolate(Color.BLUE, 0.5);	
	            	}
	            	pixelWriter.setColor(x, y, color);
	            }
	        }
			gc.drawImage(wImage, getX(), getY(), getWidth(), getHeight());
		}
	}

	@Override
	public void update() {
		
	}

	@Override
	public void onMouseClicked(MouseEvent event) {
		if(event.getX()>this.getX() && event.getX()<this.getX()+getWidth() 
			&& event.getY()>this.getY() && event.getY()<this.getY()+getHeight() && this.cardNumber !=-1){
			isClicked = true;
		}else{
			isClicked = false;
			isTiped = false;
		}	
	}
	
	public void removeCard(){
		this.cardNumber = -1;
	}
	
}
