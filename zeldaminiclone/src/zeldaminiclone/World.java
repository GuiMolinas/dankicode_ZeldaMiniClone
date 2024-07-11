//Renderiza tudo no nosso mundo, como inimigos

package zeldaminiclone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class World {
	
	//Lista para adicionar vários objetos
	public static List<Blocks> blocos = new ArrayList<Blocks>();
	
	//método construtor
	public World() {
		//Criando blocos - 480 / 32 = 15
		for(int xx = 0; xx < 15*2; xx++) {
			//blocos na horizontal - parte de cima
			blocos.add(new Blocks(xx * 32,0));
		}
		
		for(int xx = 0; xx < 15*2; xx++) {
			//blocos na horizontal - parte de baixo
			blocos.add(new Blocks(xx * 32,480-32));
		}
		
		//blocos na vertical
		for(int yy = 0; yy < 15*2; yy++) {
			blocos.add(new Blocks(0,yy * 32));
		}
		
		//blocos na vertical
		for(int yy = 0; yy < 15*2; yy++) {
			blocos.add(new Blocks(640-32,yy * 32));
		}
	}
	
	//Colisões
	public static boolean isFree(int x, int y) {
		for(int i = 0; i < blocos.size(); i ++) {
			Blocks blocoAtual = blocos.get(i);
			//Verifica colisao - Simulando player
			if(blocoAtual.intersects(new Rectangle(x,y,32,32))) {
				//está colidindo
				return false;
				
			}
		}
		
		return true;
	}
	
	public void render (Graphics g) {
		//renderiza bloco, independente da quantidade que ter
		for(int i = 0; i < blocos.size(); i ++) {
			blocos.get(i).render(g);
		}
	}

}
