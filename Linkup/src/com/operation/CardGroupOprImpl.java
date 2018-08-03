package com.operation;


import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import com.beans.Path;
import com.beans.Point;
import com.core.Config;

public class CardGroupOprImpl implements CardGroupOpr {
	int[][] vi = new int [100][100];
	int[][] dir ={{1,0},{-1,0},{0,1},{0,-1}};
    Stack<Point> road = new Stack<Point>();
	int ci,flag,si,sj,ei,ej;
		
	@Override
	public int[][] getRandomGroup(int colcount, int rowcount) {
		int[][] cardmap = new int[colcount+2][rowcount+2];
		boolean flag = false;
		int cardtype = 0;
		for (int i = 0; i < cardmap.length; i++) {
			for (int j = 0; j < cardmap[0].length; j++) {
				if(i==0 || j==0 || i==cardmap.length-1 || j==cardmap[0].length-1){
					cardmap[i][j] = -1;
				}else{
					cardmap[i][j] = cardtype;
					if(flag){
						cardtype = (cardtype+1)%Config.CARDTYPE;
					}
					flag^=true;
				}
			}
		}
		refreshMap(cardmap);
		return cardmap;
	}

	@Override
	public boolean isLink(Point p1, Point p2, int[][] cardmap) {
		flag=0;
		if(p1 == null || p2 == null||(p1.getY()==p2.getY()&&p1.getX()==p2.getX())){
			return false;
		}
		sj = p1.getY();
        si = p1.getX();
        ej = p2.getY();
        ei = p2.getX();
		
        for (int i = 0; i < cardmap[0].length ; i++) {
			for (int j = 0; j < cardmap.length; j++) {				
				vi[j][i] = -1;
			}
		}
    	road.clear();
    	road.push(p1);
        for(int i=0;i<4;i++){
        	dfs(si+dir[i][0],sj+dir[i][1],i,0,cardmap);
        }           
        if(flag==1){       	
        	road.push(p2);
        	Path.getInstance().setRoad(road);
        	Path.getInstance().setShowAll(false);
        	Path.getInstance().setRoadPoint(0);
            return true;
        }
		return false;
	}
	
	public void refreshMap(int[][] a){
		ArrayList<Point> list = new ArrayList<Point>();
		for (int i = 1; i < a.length-1; i++) {
			for (int j = 1; j < a[0].length-1; j++) {
				if(a[i][j]!=-1){
					list.add(new Point(i, j));
				}
			}
		}
		//每一位随机与其他位置交换一次
		Random random = new Random();
		for (int i = 0; i < list.size(); i++) {
			int randomIndex = random.nextInt(list.size());
			int temp = a[list.get(i).getX()][list.get(i).getY()];
			a[list.get(i).getX()][list.get(i).getY()] = a[list.get(randomIndex).getX()][list.get(randomIndex).getY()];
			a[list.get(randomIndex).getX()][list.get(randomIndex).getY()] = temp;
		}		
	}
		
	public boolean dfs(int si,int sj,int d,int x,int[][] cardmap)
	{
	    if((si==ei&&sj==ej&&x<=2 )||flag==1){
	    	flag=1;
	        return true;
	    }
	    if(si<0||si>=cardmap.length||sj<0||sj>=cardmap[0].length||x>2||(x>vi[si][sj]&&vi[si][sj]!=-1)||cardmap[si][sj]>=0){
	        return false;
	    }	    	
	    else {
	    	road.push(new Point(si,sj));
	        vi[si][sj]=x;
	        for(int i=0;i<4;i++)
	        {
	            if(i==d){
	            	if(dfs(si+dir[i][0],sj+dir[i][1],i,x,cardmap)){
	            		return true;
	            	}
	            }
	            else{
	            	if(dfs(si+dir[i][0],sj+dir[i][1],i,x+1,cardmap)){
	            		return true;
	            	}
	            }	            
	        }
	        road.pop();
	        cardmap[si][sj]=-1;
	    }
	    return false;
	}

}
