package program;

import java.awt.*;
import javax.swing.*;

public class OknoIgre extends JFrame {
	
	IgralnaPlosca plosca;
	
	OknoIgre() {
		
		plosca = new IgralnaPlosca();
		this.setTitle("Pong");
		this.add(plosca);
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}

}
