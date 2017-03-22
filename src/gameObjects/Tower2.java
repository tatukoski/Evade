package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import core.Game;
import core.Handler;
import core.ID;

public class Tower2 extends GameObject {

	private Handler handler;
	private Random ran = new Random();
	private Color col;

	public Tower2(float x, float y, float radius, ID id, Handler handler) {
		super(x, y, radius, id);
		this.handler = handler;
		
		col = new Color(255, 110, 0);
		velX = 1;
		velY = 0;

		dmg = 20;
		
	}
	
	public void tick() {		
		x += velX;
		y += velY;
		
		if(x >= Game.WIDTH) handler.removeObject(this);

		handler.addObject(new Trail(x, y, radius, ID.Trail, col, (int)radius*2, (int)radius*2, 0.04f, handler));
		
		int spawn = ran.nextInt(60);
		if(spawn == 0) {
			handler.addObject(new VerticalEnemy(x, y, 8, ID.VerticalEnemy, handler));

		}
	}

	public void render(Graphics g) {
		
		g.setColor(col);
		g.fillOval((int)x-(int)radius, (int)y-(int)radius, (int)radius*2, (int)radius*2);			
	}
}
