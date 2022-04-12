package program;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class IgralnaPlosca extends JPanel implements Runnable {
	
	static final int SIRINA_IGRE = 1000;
	static final int VISINA_IGRE = (int)(SIRINA_IGRE * (0.5555));
	static final Dimension VELIKOST_ZASLONA = new Dimension(SIRINA_IGRE, VISINA_IGRE);
	static final int PREMER_ZOGICE = 20;
	static final int SIRINA_LOPARJA = 25;
	static final int VISINA_LOPARJA = 100;
	Thread nitIgre;
	Image image;
	Graphics graphics;
	Random random;
	Loparji lopar1;
	Loparji lopar2;
	Zogica zogica;
	Rezultat rezultat;
	
	IgralnaPlosca() {
		
		noviLoparji();
		novaZogica();
		rezultat = new Rezultat(SIRINA_IGRE, VISINA_IGRE);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(VELIKOST_ZASLONA);
		
		nitIgre = new Thread(this);
		nitIgre.start();
		
	}
	
	public void novaZogica() {
		
		random = new Random();
		zogica = new Zogica((SIRINA_IGRE/2) - (PREMER_ZOGICE/2), random.nextInt(VISINA_IGRE - PREMER_ZOGICE), PREMER_ZOGICE, PREMER_ZOGICE);
		
	}
	
	public void noviLoparji() {
		
		lopar1 = new Loparji(0, (VISINA_IGRE/2) - (VISINA_LOPARJA/2), SIRINA_LOPARJA, VISINA_LOPARJA, 1);
		lopar2 = new Loparji(SIRINA_IGRE - SIRINA_LOPARJA, (VISINA_IGRE/2) - (VISINA_LOPARJA/2), SIRINA_LOPARJA, VISINA_LOPARJA, 2);
		
	}
	
	public void paint(Graphics g) {
		
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		narisi(graphics);
		g.drawImage(image, 0, 0, this);
		
	}
	
	public void narisi(Graphics g) {
		
		lopar1.narisi(g);
		lopar2.narisi(g);
		zogica.narisi(g);
		rezultat.narisi(g);
		Toolkit.getDefaultToolkit().sync();

	}
	
	public void premakni() {
		
		lopar1.premakni();
		lopar2.premakni();
		zogica.premakni();
		
	}
	
	public void preveriTrk() {
		
		//odboj zogice na vrhu in na dnu
		if(zogica.y <=0) {
			
			zogica.dolociYSmer(-zogica.yHitrost);
		}
		
		if(zogica.y >= VISINA_IGRE - PREMER_ZOGICE) {
			
			zogica.dolociYSmer(-zogica.yHitrost);
			
		}
		
		//odboj žogice iz loparjev
		if(zogica.intersects(lopar1)) {
			
			zogica.xHitrost = Math.abs(zogica.xHitrost);
			zogica.xHitrost++; //vecja tezavnost
			
			if(zogica.yHitrost > 0)
				zogica.yHitrost++;
			else
				zogica.yHitrost--;
			zogica.dolociXSmer(zogica.xHitrost);
			zogica.dolociYSmer(zogica.yHitrost);
			
		}
		
		if(zogica.intersects(lopar2)) {
			
			zogica.xHitrost = Math.abs(zogica.xHitrost);
			zogica.xHitrost++; //vecja tezavnost
			if(zogica.yHitrost > 0)
				zogica.yHitrost++;
			else
				zogica.yHitrost--;
			zogica.dolociXSmer(-zogica.xHitrost);
			zogica.dolociYSmer(zogica.yHitrost);
			
		}
		
		//ustavi loparje na robih okna
		if(lopar1.y <= 0)
			lopar1.y = 0;
		
		if(lopar1.y >= (VISINA_IGRE - VISINA_LOPARJA))
			lopar1.y = VISINA_IGRE - VISINA_LOPARJA;
		
		if(lopar2.y <= 0)
			lopar2.y = 0;
		
		if(lopar2.y >= (VISINA_IGRE - VISINA_LOPARJA))
			lopar2.y = VISINA_IGRE - VISINA_LOPARJA;
		
		//doda igralcu tocko in ustvari nove loparje in zogico
		if(zogica.x <= 0) {
			
			rezultat.igralec2++;
			noviLoparji();
			novaZogica();
			System.out.println("Igralec 2: " + rezultat.igralec2);
			
		}
		
		if(zogica.x >= SIRINA_IGRE - PREMER_ZOGICE) {
			
			rezultat.igralec1++;
			noviLoparji();
			novaZogica();
			System.out.println("Igralec 1: " + rezultat.igralec1);
			
		}
	}

	public void run() {
		
		//zanka igre
		long casTrajanja = System.nanoTime();
		double steviloTickov = 60.0;
		double ns = 1000000000 / steviloTickov;
		double delta = 0;
		while(true) {
			
			long zdaj = System.nanoTime();
			delta += (zdaj - casTrajanja)/ns;
			casTrajanja = zdaj;
			if(delta >=1) {
				
				premakni();
				preveriTrk();
				repaint();
				delta--;
				
			}
		}
	}
	
	public class AL extends KeyAdapter {
		
		public void keyPressed(KeyEvent e) {
			
			lopar1.pritisnjenaTipka(e);
			lopar2.pritisnjenaTipka(e);
			
		}
		
		public void keyReleased(KeyEvent e) {
			
			lopar1.spuscenaTipka(e);
			lopar2.spuscenaTipka(e);
			
		}
	}

}
