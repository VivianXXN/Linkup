package com.operation;

import com.beans.Point;

public interface CardGroupOpr {
	public int[][] getRandomGroup(int colcount,int rowcount);
	public boolean isLink(Point p1,Point p2,int cardmap[][]);
	public void refreshMap(int[][] map);
}
