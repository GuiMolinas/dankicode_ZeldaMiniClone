package zeldaminiclone;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	
	public static BufferedImage spritesheet;
	
	//criando um array de imagens - animacao
	public static BufferedImage [] player_front;
	
	//criando um array de imagens - inimigo - 
	public static BufferedImage [] enemy_front;
	
	//64,97
	
	//carregando imagem
	public Spritesheet() {
		//carregando imagem do arquivo
		try {
			spritesheet = ImageIO.read(getClass().getResource("/spritesheet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//número de animacoes do player
		player_front = new BufferedImage[2];
		
		//número de animacoes do inimigo
		enemy_front = new BufferedImage[2];
		
		//localizacao sprite 1 player
		player_front[0] = Spritesheet.getSprite(0, 11, 16, 16);
		
		//localizacao sprite 2 player
		player_front[1] = Spritesheet.getSprite(16, 11, 16, 16);
		
		//localizacao sprite 1 inimigo
		enemy_front[0] = Spritesheet.getSprite(64, 97, 16, 16);
				
		//localizacao sprite 2 inimigo
		enemy_front[1] = Spritesheet.getSprite(64+16, 97, 16, 16);
	}
	
	//Método para subimagens - a partir de coordenadas
	public static BufferedImage getSprite(int x, int y, int width, int height) {
		return spritesheet.getSubimage(x, y, width, height);
	}

}
