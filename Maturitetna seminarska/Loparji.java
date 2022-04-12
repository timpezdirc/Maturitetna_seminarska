package program;

import java.awt.*;
import java.awt.event.*;

public class Loparji extends Rectangle {
	
	int id;
	int yHitrost;
	int hitrost = 10;
	
	Loparji(int x, int y, int SIRINA_LOPARJA, int VISINA_LOPARJA, int id) {
		
		super(x, y, SIRINA_LOPARJA, VISINA_LOPARJA);
		this.id = id;
		
	}
	
	public void pritisnjenaTipka(KeyEvent e) {
		
		switch(id) {
		case 1:
			if(e.getKeyCode() == KeyEvent.VK_W) {
				dolociYSmer(-hitrost);
			}
			
			if(e.getKeyCode() == KeyEvent.VK_S) {
				dolociYSmer(hitrost);
			}
			break;
			
		case 2:
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				dolociYSmer(-hitrost);
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				dolociYSmer(hitrost);
			}
			break;
			
		}
	}
	
	public void spuscenaTipka(KeyEvent e) {
		
		switch(id) {
		case 1:
			if(e.getKeyCode() == KeyEvent.VK_W) {
				dolociYSmer(0);
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				dolociYSmer(0);
			}
			break;
			
		case 2:
			if(e.getKeyCode( )== KeyEvent.VK_UP) {
				dolociYSmer(0);
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				dolociYSmer(0);
			}
			break;
			
		}
	}
	
	public void dolociYSmer(int ySmer) {
		
		yHitrost = ySmer;
		
	}
	
	public void premakni() {
		
		y = y + yHitrost;
		
	}
	
	public void narisi(Graphics g) {
		
		if(id==1)
			g.setColor(Color.blue);
		else
			g.setColor(Color.red);
		g.fillRect(x, y, width, height);
		
	}

}
