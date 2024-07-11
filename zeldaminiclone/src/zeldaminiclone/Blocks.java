package zeldaminiclone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Blocks extends Rectangle {

	public Blocks (int x, int y) {
		super(x, y, 32, 32);
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(153,102,0));
		g.fillRect(x, y, width, height);
		g.setColor(new Color(102,51,0));
		g.drawRect(x, y, width, height);;
	}
}
