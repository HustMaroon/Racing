package core;


import java.awt.Toolkit;

public class Road extends CarObject{
	
public Road(int yPos){
		
		this.image = Toolkit.getDefaultToolkit().getImage("src/Image/road.png");
		this.xPos = 0;
		this.yPos = yPos;
		X = xPos;
		Y = yPos;
		this.width = 300;
		this.height = 825;
	}

	public void update(){
		if(yPos == 825){
			yPos = -825;
		}else {
			Y += .2;
			yPos = (int)Y;
		}
	}
}
