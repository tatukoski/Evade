package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import core.Game;
import core.Handler;
import core.ID;

public class HorizontalEnemy extends GameObject {

	private Handler handler;
	private Random ran = new Random();
	
	private Color col;

	public HorizontalEnemy(float x, float y, float radius, ID id, Handler handler) {
		super(x, y, radius, id);
		this.handler = handler;
		
		this.handler = handler;
		
		velX = ran.nextInt(12 - -12) + -12;
		velY = 0;
		
		col = new Color(12, 201, 211);
		dmg = 4;
	}
	
	public void tick() {		
		x += velX;
		y += velY;
		
		if(y <= radius + 5 || y >= Game.HEIGHT-radius-34) velY *= -1;
		if(x <= radius + 5 || x >= Game.WIDTH-radius-10) velX *= -1;
		
		if(velX <= 4 && velX >= -4) velX += 8;
		
		handler.addObject(new Trail(x, y, radius, ID.Trail, col, (int)radius*2, (int)radius*2, 0.04f, handler));
	}

	public void render(Graphics g) {
		
		g.setColor(col);
		g.fillOval((int)x-(int)radius, (int)y-(int)radius, (int)radius*2, (int)radius*2);			
	}
}
