package com.beans;

import java.util.Random;

import com.core.Config;
import com.core.GameObject;
import com.operation.CardGroupOpr;
import com.operation.CardGroupOprImpl;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class CardGroup extends GameObject{
	
	private static CardGroup instance = new CardGroup();
	private CardGroupOpr cardGroupOpr = new CardGroupOprImpl();
	private Card[] cards;
	private int[][] cardMap;
	private int number;
	private int combo;
	private int maxcombo;
	private long t1;
	private long lastt;
	private int radint;
		
	public static CardGroup getInstance() {
		return instance;
	}

	private CardGroup() {
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		for(int i = 0; i<cards.length;i++){
			cards[i].draw(gc);
		}
	}	


	public int getNumber() {
		return number;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
	public int getCombo() {
		return combo;
	}

	public int getMaxcombo() {
		return maxcombo;
	}

	@Override
	public void init() {
		super.init();
		number=Config.ROWCOUNT*Config.COLCOUNT;	
		combo=0;
		maxcombo=0;
		lastt=-1;
		cards = new Card[(Config.ROWCOUNT+2)*(Config.COLCOUNT+2)];
		Random rad = new Random();
		radint = rad.nextInt(Config.CARDTYPESUM-Config.CARDTYPE);	
		cardMap = cardGroupOpr.getRandomGroup(Config.COLCOUNT, Config.ROWCOUNT);
		for (int i = 0; i < cardMap.length; i++) {
			for (int j = 0; j < cardMap[0].length; j++) {
				if(i == 0 || j==0 || i == cardMap.length-1 || j == cardMap[0].length-1){
					cards[j*cardMap.length+i] = new Card(cardMap[i][j], i, j);
				}else{
					cards[j*cardMap.length+i] = new Card(cardMap[i][j]+radint, i, j);
				}
			}
		}
	}

	@Override
	public void onMouseClicked(MouseEvent event) {
		Point p1 = null;
		int p1_cardnumber = -2;
		for(int i = 0; i<cards.length;i++){
			if (cards[i].isClicked()) {
				p1_cardnumber = cards[i].getCardNumber();
				p1 = new Point(i%cardMap.length, i/cardMap.length);
			}
			cards[i].onMouseClicked(event);
		}
		Point p2 = null;
		for(int i = 0; i<cards.length;i++){
			if (cards[i].isClicked()) {								
				if (p1_cardnumber != cards[i].getCardNumber()) {
					return ;
				}
				p2 = new Point(i%cardMap.length, i/cardMap.length);
			}
		}
		if(cardGroupOpr.isLink(p1, p2, cardMap)&&!(p1.getX()==p2.getX()&&p1.getY()==p2.getY())){
			t1 = System.currentTimeMillis();
			if(lastt!=-1&&t1-lastt<=1500){
				combo++;
			}else{
				combo=0;
			}
			if(maxcombo<combo){
				maxcombo=combo;
			}
			Config.PANGSOUND.play();
			cards[p1.getX()+p1.getY()*cardMap.length].removeCard();
			cardMap[p1.getX()][p1.getY()] = -1;
			cards[p2.getX()+p2.getY()*cardMap.length].removeCard();
			cardMap[p2.getX()][p2.getY()] = -1;
			number-=2;
			if(number==0){
				Config.COMPLETESOUND.play();
			}
			lastt=t1;
		}
	}
	
	public void upSetMap(){
		combo=0;
		cardGroupOpr.refreshMap(cardMap);
		for (int i = 0; i < cardMap.length; i++) {
			for (int j = 0; j < cardMap[0].length; j++) {
				if(cardMap[i][j]==-1){
					cards[j*cardMap.length+i].setCardNumber(cardMap[i][j]);
				}else{
					cards[j*cardMap.length+i].setCardNumber(cardMap[i][j]+radint);
				}				
			}
		}
	}
	
	public void reSetMap(){
		this.init();
	}
	
	public boolean searchCouple(){
		int p1_x,p1_y,p2_x,p2_y;
		for (int i = 0; i < cards.length; i++) {
			p1_x=i%cardMap.length;
			p1_y=i/cardMap.length;
			if(cardMap[p1_x][p1_y]!=-1){
				for (int j = i+1; j < cards.length; j++) {
					p2_x=j%cardMap.length;
					p2_y=j/cardMap.length;
					if(cardMap[p2_x][p2_y]!=-1 && cardMap[p1_x][p1_y]==cardMap[p2_x][p2_y]){
						if(cardGroupOpr.isLink(new Point(p1_x, p1_y), new Point(p2_x, p2_y), cardMap)){
							cards[p1_y*cardMap.length+p1_x].setTiped(true);
							cards[p2_y*cardMap.length+p2_x].setTiped(true);
							
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	

}
