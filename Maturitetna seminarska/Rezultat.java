package program;

import java.awt.*;

public class Rezultat extends Rectangle {
	
	static int SIRINA_IGRE;
	static int VISINA_IGRE;
	int igralec1;
	int igralec2;
	
	Rezultat(int SIRINA_IGRE, int VISINA_IGRE) {
		
		Rezultat.SIRINA_IGRE = SIRINA_IGRE;
		Rezultat.VISINA_IGRE = VISINA_IGRE;
		
	}
	
	public void narisi(Graphics g) {
		
		g.setColor(Color.white);
		g.setFont(new Font("Consolas", Font.PLAIN, 60));
		
		g.drawLine(SIRINA_IGRE/2, 0, SIRINA_IGRE/2, VISINA_IGRE);
		
		g.drawString(String.valueOf(igralec1/10 ) + String.valueOf(igralec1 % 10), (SIRINA_IGRE/2) - 85, 50);
		g.drawString(String.valueOf(igralec2/10) + String.valueOf(igralec2 % 10), (SIRINA_IGRE/2) + 20, 50);
		
	}

}
