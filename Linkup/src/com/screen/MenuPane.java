package com.screen;

import com.core.Config;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MenuPane{
	private Stage stage = new Stage();
	TextField tf1;
	TextField tf2;
	TextField tf3;
	public MenuPane(){
		ToggleGroup group = new ToggleGroup();
		StackPane pane = new StackPane();
		RadioButton bt1 = new RadioButton("初级");
		bt1.setToggleGroup(group);
		bt1.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
		RadioButton bt2 = new RadioButton("中级");
		bt2.setToggleGroup(group);
		bt2.setSelected(true);
		bt2.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
		RadioButton bt3 = new RadioButton("高级");
		bt3.setToggleGroup(group);
		bt3.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
		RadioButton bt4 = new RadioButton("自定义");
		bt4.setToggleGroup(group);
		bt4.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
		
		Button bt5 = new Button("确认");
		GridPane g = new GridPane();
		g.setAlignment(Pos.CENTER);
		g.setVgap(10);
		g.setHgap(10);
		g.add(new Label("行数"), 0, 0);
		g.add(new Label("列数"), 0, 1);
		g.add(new Label("方块数量"), 0, 2);
			
		g.add(tf1 = new TextField(), 1, 0);
		g.add(tf2 = new TextField(), 1, 1);
		g.add(tf3 = new TextField(), 1, 2);
		
	    VBox vbox = new VBox();
	    vbox.setSpacing(10);
	    vbox.setAlignment(Pos.CENTER);
	    vbox.getChildren().addAll(bt1,bt2,bt3,bt4,g,bt5);
			
	    pane.getChildren().add(vbox);
		bt5.setOnAction(e->{
			if(group.getSelectedToggle()==bt1){
				Config.ROWCOUNT=4;
				Config.COLCOUNT=6;
				Config.CARDTYPE=5;
			}else if(group.getSelectedToggle()==bt2){
				Config.ROWCOUNT=6;
				Config.COLCOUNT=8;
				Config.CARDTYPE=7;
			}else if(group.getSelectedToggle()==bt3){
				Config.ROWCOUNT=8;
				Config.COLCOUNT=12;
				Config.CARDTYPE=10;
			}else if(group.getSelectedToggle()==bt4){
				int row = Integer.valueOf(tf1.getText());
				int col = Integer.valueOf(tf2.getText());
				int number = Integer.valueOf(tf3.getText());
				if(row%2==1&&col%2==1){
					Alert a = new Alert(AlertType.WARNING);
					a.setTitle("警告");
					a.setHeaderText(null);
					a.setContentText("请输入至少一个偶数作为行列数");
					a.showAndWait();
				}else if(number>=63||number<=0){
					Alert a = new Alert(AlertType.WARNING);
					a.setTitle("警告");
					a.setHeaderText(null);
					a.setContentText("请输入1-62区间中的数字");
					a.showAndWait();
				}else{
					Config.ROWCOUNT=row;
					Config.COLCOUNT=col;
					Config.CARDTYPE=number;
				}
				
			}			
			stage.close();
		});	
			
		Scene scene = new Scene(pane, 300, 250);
		scene.getStylesheets().add("/com/screen/application1.css");
		stage.setTitle("修改难度");
		stage.setScene(scene);
		stage.showAndWait();
		
	}
	
}
