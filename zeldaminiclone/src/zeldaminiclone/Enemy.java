package zeldaminiclone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Rectangle - sistema de colisão, x, y; 
public class Enemy extends Rectangle{

	//Variáveis de controle
	public int spd = 2; //velocoddae do player
	public int right = 1, up = 0, down = 0, left = 0; //direções
	
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
	
	public Enemy(int x, int y) {
		//Posição altura e largura
		super(x,y,32,32);
	}
	
	public void perseguirPlayer() {
		Player p = Game.player;
		//se o inimigo estiver atrás do player e colisao
		if(x < p.x && World.isFree(x+spd, y)) {
			//aleatorizando movimento - número de 0 a 100, caso for menor que 50, se move
			if(new Random().nextInt(100) < 50) {
				//ele vai para frente
				x += spd;
			}
		}
		
		//se estiver para frente, vai para trás
		else if(x > p.x && World.isFree(x-spd, y)) {
			//aleatorizando movimento 
			if(new Random().nextInt(100) < 50) {
				x-=spd;
			}
		}
		
		//se o inimigo estiver atrás do player
		if(y < p.y && World.isFree(x, y+spd)) {
			//aleatorizando movimento 
			if(new Random().nextInt(100) < 50) {
				//ele vai para frente
				y += spd;
			}
	    }
				
		//se estiver para frente, vai para trás
		else if(y > p.y && World.isFree(x, y-spd)) {
			//aleatorizando movimento 
			if(new Random().nextInt(100) < 50) {
				y-=spd;
			}
		}
	}
	
	//aqui fica a lógica
	public void tick() {
		
		boolean moved = true;
		
		perseguirPlayer();
		
		//Se estiver se movendo, toca a animacao
		if(moved) {
			curFrames++;
			if(curFrames == targetFrames) {
				curFrames = 0;
				curAnimation++;
				//Validacao para número de animacoes
				if(curAnimation == Spritesheet.enemy_front.length) {
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
		g.drawImage(Spritesheet.enemy_front[curAnimation], x,y,32,32,null);
		
		//renderizando bala
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
	}
}
