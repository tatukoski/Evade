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

public class Player extends GameObject {

	Random r = new Random();
	Handler handler;
	
	public Player(float x, float y, float radius, ID id, Handler handler) {
		super(x, y, radius, id);
		this.handler = handler;
		
		handler.speed = 5;
		
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 18, Game.WIDTH - 25);
		y = Game.clamp(y,18, Game.HEIGHT - 47);
		
		handler.addObject(new Trail((float)x, (float)y, (float)radius, ID.Trail, Color.white, 32, 32, 0.1f, handler));
		
		collision();
	}
	

	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);
			//Distance between player and the enemy calculated
			double distance =  Math.pow((x - tempObject.getX()) * (x - tempObject.getX()) + (y - tempObject.getY()) * (y - tempObject.getY()), 0.5);

			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.VerticalEnemy || tempObject.getId() == ID.Tower || tempObject.getId() == ID.HorizontalEnemy){
			
				//Check if Player is inside enemy
				if(tempObject.getRadius() >= radius && distance <= (tempObject.getRadius()-radius)){
					
					AudioPlayer.getSound("explosion").play();
					HUD.HEALTH -= tempObject.getDmg();
					handler.addObject(new DamageEffect((float)x, (float)y, 3000, ID.DamageEffect, 6000, 6000, 0.1f, handler));
					handler.removeObject(tempObject);
					}
				//Check if enemy is inside Player
				else if(radius >= tempObject.getRadius() && distance <= (radius - tempObject.getRadius())){
				}	
				//Check if Player is outside the arena
				else if(distance > (radius + tempObject.getRadius())){
				}
				//Check if Player Collides with the enemy
				else{	
					AudioPlayer.getSound("explosion").play();
					HUD.HEALTH -= tempObject.getDmg();
					handler.addObject(new DamageEffect((float)x, (float)y, 3000, ID.DamageEffect, 6000, 6000, 0.1f, handler));
					handler.removeObject(tempObject);
				}
			}
		}
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.white);
		g.fillOval((int)x -(int)radius, (int)y-(int)radius, (int) radius*2, (int)radius*2);
	}
}
