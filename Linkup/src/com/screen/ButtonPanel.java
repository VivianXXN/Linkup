package com.screen;

import com.core.Config;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ButtonPanel extends VBox {
	private GamePane canvas;
	
	public ButtonPanel(final GamePane canvas) {
		this.canvas = canvas;
		
		Button begin = createButton("重新游戏");//开始按钮
		begin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ButtonPanel.this.canvas.reSetMap();
			}
		});
		
		Button refresh = createButton("刷新游戏");//刷新按钮
		refresh.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ButtonPanel.this.canvas.upSetGroup();
			}
		});
		
		Button tip = createButton("提示游戏");//提示按钮
		tip.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ButtonPanel.this.canvas.searchCouple();
			}
		});
		
		Button out = createButton("退出游戏");//退出按钮
		setAlignment(Pos.CENTER);

		out.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
		
		Button change = createButton("修改难度");//修改难度按钮
		change.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MenuPane mp = new MenuPane();
				Config.init();
				ButtonPanel.this.canvas.reSetMap();
			}
		});
		getChildren().addAll(begin, refresh, tip, out,change);
	}

	private Button createButton(String text) {  //按钮统一设置
		// TODO Auto-generated method stub
		final Button button=new Button(text);
		button.setTranslateY(Config.yOffset);
		button.setFont(new Font("Times Roman",15));
		button.setPrefSize(100, 50);//按钮的大小
		setAlignment(Pos.CENTER_LEFT);
		setPrefSize(Config.PaneWidth,Config.PaneHeight);
		setMargin(button, new Insets(20));//按钮之间恶距离
		setStyle("-fx-base: #1E90FF;");
		
		final DropShadow shadow = new DropShadow();
		//当鼠标进入按钮时添加阴影特效
		button.addEventHandler(MouseEvent.MOUSE_ENTERED,new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				button.setEffect(shadow); 
			}  
	    });  
	    //当鼠标离开按钮时移除阴影效果
	    button.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {  
	            @Override 
	            public void handle(MouseEvent e) {  
	            	button.setEffect(null);  
	            }  
	    });
		return button;
	}

}
