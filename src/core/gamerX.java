package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.in;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

class AddClient implements Runnable{
    Socket client;
    public AddClient(Socket client){
        this.client = client;
    }
    
    public void run(){
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        }catch(IOException e) {
            System.out.println("Accept failed: 4444");
            System.exit(-1);
        }
        
    }
}
public class gamerX extends JFrame implements KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Player PL;
        Player2 PL2;
	Road R1,R2;
	Graphics graphics;
	Image image;
	CopyOnWriteArrayList<Enemy> enemies = new CopyOnWriteArrayList<Enemy>();
	CopyOnWriteArrayList<Road> roads = new CopyOnWriteArrayList<Road>();
	CopyOnWriteArrayList<Award> awards = new CopyOnWriteArrayList<Award>();
	
	Random R;
	JButton B;
	
	long counter,ardCounter;
	int r,r2;
	boolean PL_up,PL_down,PL_left,PL_right,collide;
        boolean PL2_up, PL2_down, PL2_left, PL2_right, collide2;
        GUI W;
	
	public gamerX(){
		this.setTitle("GamerX");
		this.setSize(825, 825);
		this.setResizable(false);
		this.setLocation(400, 0);
		this.setBackground(Color.black);
		addKeyListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PL = new Player();
                PL2 = new Player2();
		
		R1 = new Road(0);
		roads.add(R1);
		R2 = new Road(-825);
		roads.add(R2);
		
		R = new Random();
		this.setVisible(true);
		PL_up=PL_down=PL_left=PL_right=collide=false;
                PL2_up=PL2_down=PL2_left=PL2_right=collide2=false;
		counter = ardCounter = 0;
	}
	
	public void paint(Graphics g){
			
			image = createImage(getWidth(),getHeight());
			graphics = image.getGraphics();
			
			paintComponent(graphics);
			g.drawImage(image, 0,0, null);
			if(collide){
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//PL.xPos = PL.rect.x = 50;
				//PL.yPos = PL.rect.y = 250;
				counter = 5;
				enemies.removeAll(enemies);
				collide = false;
			}
		
			repaint();
			
	}
	
	private void paintComponent(Graphics g) {
		
		if(PL.life > -1){
			for(Road X : roads){
				X.update();
				X.draw(g);
				if(X.yPos == this.getHeight()){
					R2 = new Road(-825);
					roads.add(R2);
					roads.remove(X);
				}
			}
			
			g.setColor(Color.WHITE);
			g.drawLine(300, 0, 300, 825);
			g.drawLine(301, 0, 301, 825);
			//g.drawString("Points", 321, 100);
			//g.drawString(PL.point.toString(), 321, 115);
			//g.drawString("Life", 321, 280);
			//g.drawString(PL.life.toString(), 321, 295);
			PL.update(this);
			PL.draw(g);
                        PL2.update(this);
                        PL2.draw(g);
			r = (int)R.nextInt(4)*50;
                        r2 = (int)R.nextInt(4)*60;
			
			counter++;
			ardCounter++;
			if(counter == 1350){
				if(ardCounter < 40000){
					Enemy E = new Enemy(r);
					enemies.add(E);
                                        Enemy E2 = new Enemy(r2);
                                        enemies.add(E2);
				}else {
					Award E = new Award(r);
					awards.add(E);	
					ardCounter = 0;
				}
				counter = 0;
			}
			for(CarObject X : enemies){
				X.update();
				X.draw(g);
                                if(X.yPos == this.getHeight()){
					enemies.remove(X);
					PL.point += 10;
				}else if(X.rect.intersects(PL.rect)){
					
					g.drawString("BOOM", 321, 180);
					collide = true;
					PL.life--;
				}
			}
			
			/*for(CarObject X : awards){
				X.update();
				X.draw(g);
				if(X.yPos == this.getHeight()){
					awards.remove(X);
					PL.point += 10;
				}else if(X.rect.intersects(PL.rect)){
					
					awards.remove(X);
					PL.life++;
				}
			}*/
			
		}else{
			//g.setColor(Color.GREEN);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("src/Image/Back.gif"), 0, 100, 275, 200,null);
			g.drawString("Game over", 107, 240);
			//g.drawString("Total Point", 107, 255);
			//g.drawString(PL.point.toString(), 127, 270);
                    this.setVisible(false);
                    new GUI("  Game Over",String.format(" Total points : %d", PL.point)).setVisible(true);
		}
	}

	/*public static void main(String args[]){
		new gamerX();
	}*/

	
	
	
	
	@Override
	public void keyPressed(KeyEvent K) {
		
		if(K.getKeyCode() == KeyEvent.VK_UP){
			PL_up = true;
                        
		}else if(K.getKeyCode() == KeyEvent.VK_DOWN){
			PL_down = true;
		}else if(K.getKeyCode() == KeyEvent.VK_LEFT){
			PL_left = true;
		}else if(K.getKeyCode() == KeyEvent.VK_RIGHT){
			PL_right = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent K) {
		
		if(K.getKeyCode() == KeyEvent.VK_UP){
			PL_up = false;
		}else if(K.getKeyCode() == KeyEvent.VK_DOWN){
			PL_down = false;
		}else if(K.getKeyCode() == KeyEvent.VK_LEFT){
			PL_left = false;
		}else if(K.getKeyCode() == KeyEvent.VK_RIGHT){
			PL_right = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent K) {
		
	}
        
        

}
