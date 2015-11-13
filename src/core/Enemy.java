package core;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

public class Enemy extends CarObject {
        int arr[]={90,80,70};
        Random R = new Random();
	int r = (int)R.nextInt(2);
	double Y;

public Enemy(int xPos){
		
		this.image = Toolkit.getDefaultToolkit().getImage("src/Image/car_E.gif");
		this.xPos = xPos;
		this.yPos = -200;
		Y = 0;
		this.width = 40;
		this.height = 70;
		this.rect = new Rectangle(xPos,yPos,width,height);
	}
	
	public void update(){
		Y += 0.9;
		yPos = (int)Y;
		rect.y = yPos;
	}

}
