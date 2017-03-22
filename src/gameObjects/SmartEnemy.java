package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import core.AudioPlayer;
import core.Game;
import core.HUD;
import core.Handler;
import core.ID;
import effects.DamageEffect;
import effects.SpeedUpEffect;

public class SmartEnemy extends GameObject {

	private Handler handler;
	private GameObject player;
	private Random ran = new Random();
	private Color col;
	
	public SmartEnemy(float x, float y, float radius, ID id, Handler handler) {
		super(x, y, radius, id);
		this.handler = handler;
		
		for(int i = 0; i < handler.object.size(); i++){
			if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
			
			col = new Color(255, 25, 0);
			dmg = 10;
		}
	}
	
	public void tick() {		
		
		x += velX;
		y += velY;
		
		double diffX = x - player.getX() - 16;
		double diffY = y - player.getY() - 16;
		double distance = Math.sqrt((x - player.getX())*(x - player.getX()) + (y - player.getY()) * (y - player.getY()));
		
		
		velX = (float) ((-1/distance) * diffX);
		velY = (float) ((-1/distance) * diffY);
		
		handler.addObject(new Trail(x, y, radius, ID.Trail, col, (int)radius*2, (int)radius*2, 0.04f, handler));
	}

	public void render(Graphics g) {
		
		g.setColor(col);
		g.fillOval((int)x-(int)radius, (int)y-(int)radius, (int)radius*2, (int)radius*2);	
	}
}
