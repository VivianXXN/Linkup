package com.core;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public interface GameEvent {
	/**
	 * ��ʼ���¼�
	 * 
	 */
	public void init();

	/**
	 * Ĭ�ϰ��������¼�
	 * 
	 * @param event
	 */
	public void onKeyPressed(KeyEvent event);

	/**
	 * Ĭ�ϰ����ͷ��¼�
	 * 
	 * @param event
	 */
	public void onKeyReleased(KeyEvent event);

	/**
	 * Ĭ������ƶ��¼�
	 * 
	 * @param event
	 */
	public void onMouseMoved(MouseEvent event);
	
	public void onMouseClicked(MouseEvent event);
	
	public void draw(GraphicsContext gc);

	public void update();
}
