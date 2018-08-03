package com.core;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class Config {
	public static int ROWCOUNT = 6;//行数
	public static int COLCOUNT = 8;//列数
	public static int CARDTYPE = 7;//游戏中实际使用的牌数
	public static int TIMESET = 60;//游戏时间
	
	public static int CARDTYPESUM = 63;
	private static String RESPATH = "/com/res/";
	public static Image[] CARDIMAGES = new Image[CARDTYPESUM];
	public static Image BKIMAGE = new Image("/com/res/background.jpg");
	public static Image PAIMAGE = new Image("/com/res/Cloud.png");
	
	public static AudioClip BEGINSOUND = new AudioClip(Config.class.getResource("/com/res/startSound.wav").toString());
    public static AudioClip COMPLETESOUND = new AudioClip(Config.class.getResource("/com//res/winSound.wav").toString());
    public static AudioClip FAILSOUND = new AudioClip(Config.class.getResource("/com/res/failSound.mp3").toString());
    public static AudioClip PANGSOUND = new AudioClip(Config.class.getResource("/com/res/pangSound.mp3").toString());
    public static AudioClip READYSOUND = new AudioClip(Config.class.getResource("/com/res/readyGoSound.mp3").toString());
    public static AudioClip TIPSOUND = new AudioClip(Config.class.getResource("/com/res/TipSound.mp3").toString());
	
	public static double SHEIGHT;//程序窗口高
	public static double SWIDTH;//这个是窗口的宽度？？EN
	
	public static double GHEIGHT;//游戏画面高
	public static double GWIDTH;
	public static double PaneWidth;// 控制面板宽度
	public static double PaneHeight = GHEIGHT;// 控制面板高度
	
	public static double CARDHEIGHT;//
	public static double CARDWIDTH;//
	public static double xOffset;//横向偏移
	public static double yOffset;//纵向偏移
	
	static{
		for(int i=0;i<CARDTYPESUM;i++){			
			CARDIMAGES[i] = new Image(RESPATH+(i)+".png");
		}
		PaneWidth = 200;
		GHEIGHT = 540;	
		GWIDTH = 700;	
		xOffset = 30;
		yOffset = 30;
		SHEIGHT = GHEIGHT + 2*yOffset;
		SWIDTH = GWIDTH + 2*xOffset+PaneWidth;
		Config.init();
	}
	
	public static void init(){
		double tempheight=GHEIGHT/(ROWCOUNT+2);
		double tempwidth=GWIDTH/(COLCOUNT+2);
		if(tempwidth<tempheight){
			CARDWIDTH = tempwidth;
			CARDHEIGHT = tempwidth;
		}else{
			CARDHEIGHT = tempheight;
			CARDWIDTH = tempheight;
		}	
	}
		
}
 