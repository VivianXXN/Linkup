package com.core;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public interface GameEvent {
	/**
	 * 初始化事件
	 * 
	 */
	public void init();

	/**
	 * 默认按键按下事件
	 * 
	 * @param event
	 */
	public void onKeyPressed(KeyEvent event);

	/**
	 * 默认按键释放事件
	 * 
	 * @param event
	 */
	public void onKeyReleased(KeyEvent event);

	/**
	 * 默认鼠标移动事件
	 * 
	 * @param event
	 */
	public void onMouseMoved(MouseEvent event);
	
	public void onMouseClicked(MouseEvent event);
	
	public void draw(GraphicsContext gc);

	public void update();
}
