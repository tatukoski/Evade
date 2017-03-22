package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import core.Game;
import core.Handler;
import core.ID;

public class Tower extends GameObject {

	private Handler handler;
	private Random ran = new Random();
	private Color col;

	public Tower(float x, float y, float radius, ID id, Handler handler) {
		super(x, y, radius, id);
		this.handler = handler;
		
		col = new Color(0, 255, 216);
		velX = 0;
		velY = 1;

		dmg = 20;
		
	}
	
	public void tick() {		
		x += velX;
		y += velY;
		
		if(y >= Game.HEIGHT) handler.removeObject(this);

		handler.addObject(new Trail(x, y, radius, ID.Trail, col, (int)radius*2, (int)radius*2, 0.04f, handler));
		
		int spawn = ran.nextInt(60);
		if(spawn == 0) {
			handler.addObject(new HorizontalEnemy(x, y, 8, ID.HorizontalEnemy, handler));

		}
	}

	public void render(Graphics g) {
		
		g.setColor(col);
		g.fillOval((int)x-(int)radius, (int)y-(int)radius, (int)radius*2, (int)radius*2);			
	}
}
