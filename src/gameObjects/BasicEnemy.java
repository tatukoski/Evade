package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import core.Game;
import core.Handler;
import core.ID;

public class BasicEnemy extends GameObject {

	private Handler handler;
	private Random ran = new Random();
	
	private Color col;

	public BasicEnemy(float x, float y, float radius, ID id, Handler handler) {
		super(x, y, radius, id);
		this.handler = handler;
		
		velX = ran.nextInt(8 - 4) + 4;
		velY = ran.nextInt(8 - 4) + 4;
		
		col = new Color(220, 29, 249);
		dmg = 5;
		
	}
	
	public void tick() {		
		x += velX;
		y += velY;
		
		if(y <= radius + 5 || y >= Game.HEIGHT-radius-34) velY *= -1;
		if(x <= radius + 5 || x >= Game.WIDTH-radius-10) velX *= -1;

		handler.addObject(new Trail(x, y, radius, ID.Trail, col, (int)radius*2, (int)radius*2, 0.04f, handler));
	}

	public void render(Graphics g) {
		
		g.setColor(col);
		g.fillOval((int)x-(int)radius, (int)y-(int)radius, (int)radius*2, (int)radius*2);			
	}
}
