package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import core.Game;
import core.Handler;
import core.ID;

public class VerticalEnemy extends GameObject {

	private Handler handler;
	private Random ran = new Random();
	
	private Color col;

	public VerticalEnemy(float x, float y, float radius, ID id, Handler handler) {
		super(x, y, radius, id);
		this.handler = handler;
		
		this.handler = handler;
		
		velX = 0;
		velY = ran.nextInt(12 - 8) + 8;
		
		col = new Color(224, 157, 2);
		dmg = 4;
		
	}
	
	public void tick() {		
		x += velX;
		y += velY;
		
		if(y <= radius + 5 || y >= Game.HEIGHT-radius-34) velY *= -1;
		if(x <= radius + 5 || x >= Game.WIDTH-radius-10) velX *= -1;
		
		velX = Game.clamp(velX, -12, 12);
		handler.addObject(new Trail(x, y, radius, ID.Trail, col, (int)radius*2, (int)radius*2, 0.04f, handler));
	}

	public void render(Graphics g) {
		
		g.setColor(col);
		g.fillOval((int)x-(int)radius, (int)y-(int)radius, (int)radius*2, (int)radius*2);			
	}
}
