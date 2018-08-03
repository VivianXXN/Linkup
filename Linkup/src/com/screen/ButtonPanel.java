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
		
		Button begin = createButton("������Ϸ");//��ʼ��ť
		begin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ButtonPanel.this.canvas.reSetMap();
			}
		});
		
		Button refresh = createButton("ˢ����Ϸ");//ˢ�°�ť
		refresh.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ButtonPanel.this.canvas.upSetGroup();
			}
		});
		
		Button tip = createButton("��ʾ��Ϸ");//��ʾ��ť
		tip.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ButtonPanel.this.canvas.searchCouple();
			}
		});
		
		Button out = createButton("�˳���Ϸ");//�˳���ť
		setAlignment(Pos.CENTER);

		out.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
		
		Button change = createButton("�޸��Ѷ�");//�޸��ѶȰ�ť
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

	private Button createButton(String text) {  //��ťͳһ����
		// TODO Auto-generated method stub
		final Button button=new Button(text);
		button.setTranslateY(Config.yOffset);
		button.setFont(new Font("Times Roman",15));
		button.setPrefSize(100, 50);//��ť�Ĵ�С
		setAlignment(Pos.CENTER_LEFT);
		setPrefSize(Config.PaneWidth,Config.PaneHeight);
		setMargin(button, new Insets(20));//��ť֮������
		setStyle("-fx-base: #1E90FF;");
		
		final DropShadow shadow = new DropShadow();
		//�������밴ťʱ�����Ӱ��Ч
		button.addEventHandler(MouseEvent.MOUSE_ENTERED,new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				button.setEffect(shadow); 
			}  
	    });  
	    //������뿪��ťʱ�Ƴ���ӰЧ��
	    button.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>() {  
	            @Override 
	            public void handle(MouseEvent e) {  
	            	button.setEffect(null);  
	            }  
	    });
		return button;
	}

}
