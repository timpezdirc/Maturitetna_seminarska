package program;

import java.awt.*;
import java.util.*;

public class Zogica extends Rectangle {
	
	Random random;
	int xHitrost;
	int yHitrost;
	int zacetnaHitrost = 2;
	
	Zogica(int x, int y, int sirina, int visina){
		
		super(x, y, sirina, visina);
		random = new Random();
		int randomXSmer = random.nextInt(2);
		if(randomXSmer == 0)
			randomXSmer--;
		dolociXSmer(randomXSmer * zacetnaHitrost);
		
		int randomYSmer = random.nextInt(2);
		if(randomYSmer == 0)
			randomYSmer--;
		dolociYSmer(randomYSmer * zacetnaHitrost);
		
	}
	
	public void dolociXSmer(int randomXSmer) {
		
		xHitrost = randomXSmer;
		
	}
	
	public void dolociYSmer(int randomYSmer) {
		
		yHitrost = randomYSmer;
		
	}
	
	public void premakni() {
		
		x += xHitrost;
		y += yHitrost;
		
	}
	
	public void narisi(Graphics g) {
		
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
		
	}

}
