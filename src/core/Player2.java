package core;

import java.awt.Rectangle;
import java.awt.Toolkit;

public class Player2 extends CarObject{
	
	Integer life;
	Integer point;	
public Player2(){
		
		this.image = Toolkit.getDefaultToolkit().getImage("src/Image/car_P.gif");
		this.xPos = 25;
		this.yPos = 750;
		X = xPos;
		Y = yPos;
		this.width = 40;
		this.height = 70;
		this.rect = new Rectangle(xPos+2,yPos+2,width-4,height-4);
		this.life = 1;
		this.point = 0;
	}
//PL_up=PL_down=PL_left=PL_right

	public void update(gamerX P){
		if((P.PL2_up == true)&&(yPos>26)){
			yPos = (int)(Y -= .6);
			rect.y = yPos;
		}else if((P.PL2_down == true)&&(yPos<755)){
			yPos = (int)(Y += .6);
			rect.y = yPos;
		}else if((P.PL2_left == true)&&(xPos>-1)){
			xPos = (int)(X -= .6);
			rect.x = xPos;
		}else if((P.PL2_right == true)&&(xPos<260)){
			xPos = (int)(X += .6);
			rect.x = xPos;
		}
	}

}
