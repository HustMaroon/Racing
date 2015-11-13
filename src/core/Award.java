package core;

import java.awt.Rectangle;
import java.awt.Toolkit;

public class Award extends CarObject {
	
	double Y;

public Award(int xPos){
		
		this.image = Toolkit.getDefaultToolkit().getImage("src/Image/life.gif");
		this.xPos = xPos;
		this.yPos = -200;
		Y = 0;
		this.width =30;
		this.height = 45;
		this.rect = new Rectangle(xPos,yPos,width,height);
	}
	
	public void update(){
		Y += .1;
		yPos = (int)Y;
		rect.y = yPos;
	}

}
