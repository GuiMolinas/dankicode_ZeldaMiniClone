package zeldaminiclone;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

//KeyListener - Controles; Runnable - Executar (run)
public class Game extends Canvas implements Runnable, KeyListener{
	
	//ALTURA E LARGURA DA TELA
	public static int WIDTH = 640, HEIGHT = 480;
	
	//adicionando scale para janela
	public static int SCALE = 3;
	
	//chamando classe player
	public static Player player;
	
	//Instanciando Classe World
	public World world;
	
	//lista de inimigos
	public List<Enemy> enemys = new ArrayList<Enemy>();
	
	public Game() {
		//Adiciona eventos de teclado
		this.addKeyListener(this);
		//redimensionando janela do jogo
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		//Importando sprite
		new Spritesheet();
		
		//criando player - Posicao
		player = new Player(32,32);
		
		world = new World();
		
		//adicionando inimigo
		enemys.add(new Enemy(32,32));
	}
	
	//Responsável pela lógica do jogo - movimentação, colissão...
	public void tick() {
		player.tick();
		
		for(int i = 0; i < enemys.size(); i++) {
			enemys.get(i).tick();
		}
	}
	
	//Renderizando o jogo
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		//Se meu BufferStrategy não existir, ele otimiza a parte gráfica
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		//Seta uma cor para fundo
		g.setColor(new Color(0,135,13));
		//Cria um retângulo na tela
		g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		
		//render player
		player.render(g);
		
		//renderizando inimigos
		for(int i = 0; i < enemys.size(); i++) {
			enemys.get(i).render(g);
		}
		
		world.render(g);
		
		bs.show();
	}
	
	
	public static void main(String [] args) {
		Game game = new Game();
		
		//Criando uma janela em java
		JFrame frame = new JFrame();
		
		//Adiciona jogo na janela
		frame.add(game);
		
		//Título do jogo
		frame.setTitle("Queridinho Adventures");
		
		//Empacotando ações anteriores, calculando tamanho certo
		frame.pack();
		
		//Deixand janela centralizada
		frame.setLocationRelativeTo(null);
		
		//Quando fechar janela, fecha jogo
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Ver a janela
		frame.setVisible(true);
		
		//Game looping - ficar chamando a janela para renderizar
		new Thread(game).start();
	}

	//aqui roda o jogo
	@Override
	public void run() {
		
		//Loop do game - onde ações vão ocorrer
		while (true) {
			tick();
			render();
			//Jogo a 60FPS - vai tentar fazer o try
			try {
				Thread.sleep(1000/60);
			//Se não, vai dar erro
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	//Tecla pressionada
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//Se tecla para a direita
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}
		//Se tecla para a esquerda
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}
		
		//Se tecla para a cima
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
		}
		
		//Se tecla para a baixo
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
		}
		
		//tecla para atirar
		if(e.getKeyCode() == KeyEvent.VK_Z) {
			player.shoot = true;
		}
		
	}

	@Override
	//Tecla solta
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		//Se tecla para a direita
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}
		//Se tecla para a esquerda
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
				
		//Se tecla para a cima
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
		}
				
		//Se tecla para a baixo
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
		}
				
		
	}

}
