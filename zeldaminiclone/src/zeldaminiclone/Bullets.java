package zeldaminiclone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullets extends Rectangle {
	
	//direção
	public int dir = 1;
	
	public int speed = 8;
	
	//destruir bala, a cada 60 ticks - 1 segundo
	public int frames = 0;
	
	public Bullets(int x, int y, int dir) {
		super(x+16,y+16,10,10);
		this.dir = dir;
	}
	
	public void tick() {
		x+=speed*dir;
		frames++;
		//destruindo bala depois de 1 ssegundo
		if(frames == 60) {
			//destruindo balas
			Player.bullets.remove(this);
			return;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(255, 204, 0));
		g.fillOval(x, y, width, height);
	}

}
