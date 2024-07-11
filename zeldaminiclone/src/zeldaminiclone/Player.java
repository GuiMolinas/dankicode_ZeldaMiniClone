package zeldaminiclone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

//Rectangle - sistema de colisão, x, y; 
public class Player extends Rectangle{

	//Variáveis de controle
	public int spd = 4; //velocoddae do player
	public boolean right, up, down, left; //direções
	
	//criando animações
	public int curAnimation = 0;
	
	//Quanto maior o valor do target, mais devagar vai ser a animação
	public int curFrames = 0, targetFrames = 15;
	
	//criando lista para entidade tiro
	public static List<Bullets> bullets = new ArrayList<Bullets>();
	
	//Player está atirando
	public boolean shoot = false;
	
	//Direção da bala - ultima posicao que o player andou
	public int dir = 1;
	
	public Player(int x, int y) {
		//Posição altura e largura
		super(x,y,32,32);
	}
	
	//aqui fica a lógica
	public void tick() {
		
		boolean moved = false;
		
		//verificando caminho (verifica antes de colidir)
		if(right && World.isFree(x + spd, y)) {
			x += spd;
			moved = true;
			//cria bala
			dir = 1;
		}
		
		else if (left && World.isFree(x - spd, y)) {
			x -= spd;
			moved = true;
			//cria bala
			dir = -1;
		}
		
		if(up && World.isFree(x, y-spd)) {
			y -= spd;
			moved = true;
		}
		
		else if (down && World.isFree(x, y+spd)) {
			y += spd;
			moved = true;
		}
		
		//Se estiver se movendo, toca a animacao
		if(moved) {
			curFrames++;
			if(curFrames == targetFrames) {
				curFrames = 0;
				curAnimation++;
				//Validacao para número de animacoes
				if(curAnimation == Spritesheet.player_front.length) {
					curAnimation = 0;
				}
		}
		}
		
		if(shoot) {
			//atirar quando presionado
			shoot = false;
			//criando bala
			bullets.add(new Bullets(x,y,dir));
		}
		
		//atualizando balas, percorrendo lista
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).tick();
		}
	}
	
	//renderizando player
	public void render(Graphics g) {
		//g.setColor(Color.blue);
		//g.fillRect(x, y, width, height);
		
		//Importando sprite
		g.drawImage(Spritesheet.player_front[curAnimation], x,y,32,32,null);
		
		//renderizando bala
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
	}
}
