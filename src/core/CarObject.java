package core;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class CarObject {
	
	Image image;
	Rectangle rect;
	int xPos,yPos,width,height;
	double X,Y;
	
	public void draw(Graphics g){
		g.drawImage(image, xPos, yPos, width, height, null);
	}
	
	public void update(){
	
	}
}
