package powerUps;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import core.AudioPlayer;
import core.Game;
import core.HUD;
import core.Handler;
import core.ID;
import effects.HealEffect;
import gameObjects.GameObject;
import gameObjects.Trail;

public class Heal extends GameObject {
	
	Random ran = new Random();
	Handler handler;
	private Color col;

	public Heal(float x, float y, float radius, ID id, Handler handler) {
		super(x, y, radius, id);
		this.handler = handler;
		
		velX = 0;
		velY = ran.nextInt(4) + 1;
	}

	public void tick() {
		
		x += velX;
		y += velY;
		
		if(y >= Game.HEIGHT) handler.removeObject(this);
		col = new Color(ran.nextInt(255 - 235) + 235, ran.nextInt(255 - 235) + 235, ran.nextInt(255 - 235) + 235);
		handler.addObject(new Trail((float)x, (float)y, (float)radius, ID.Trail, col, 16, 16, 0.1f, handler));
		
		playerPickUp();
	}
	
	private void playerPickUp() {
		for(int i = 0; i < handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);

			if(tempObject.getId() == ID.Player){
				
				//Distance between player and the enemy calculated
				double distance =  Math.pow((x - tempObject.getX()) * (x - tempObject.getX()) + (y - tempObject.getY()) * (y - tempObject.getY()), 0.5);
				//Check if Player is inside the power-up
				if(tempObject.getRadius() >= radius && distance <= (tempObject.getRadius()-radius)){
					
					AudioPlayer.getSound("health").play();
					HUD.bounds += 20;
					HUD.HEALTH = (100 + (HUD.bounds/2));
					handler.addObject(new HealEffect((float)x, (float)y, 3000, ID.HealEffect, 6000, 6000, 0.1f, handler));
					handler.removeObject(this);

				}
				//Check if enemy is inside Player
				else if(radius >= tempObject.getRadius() && distance <= (radius - tempObject.getRadius())){
				}	
				//Check if Player is outside the arena
				else if(distance > (radius + tempObject.getRadius())){
				}
				//Check if Player collides with the power-up
				else{	

					AudioPlayer.getSound("health").play();
					HUD.bounds += 20;
					HUD.HEALTH = (100 + (HUD.bounds/2));
					handler.addObject(new HealEffect((float)x, (float)y, 3000, ID.HealEffect, 6000, 6000, 0.1f, handler));			
					handler.removeObject(this);
					}
				       
					}
				}
			}

	public void render(Graphics g) {
		g.setColor(col);
		g.fillOval((int)x -(int)radius, (int)y-(int)radius, (int) radius*2, (int)radius*2);
		g.drawString("HEALTH", (int)x + 10, (int)y-20);
		
	}

}