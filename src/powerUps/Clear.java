package powerUps;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import core.AudioPlayer;
import core.Game;
import core.HUD;
import core.Handler;
import core.ID;
import effects.ClearEffect;
import effects.DamageEffect;
import gameObjects.GameObject;
import gameObjects.Trail;

public class Clear extends GameObject {
	
	Random ran = new Random();
	private Handler handler;
	private HUD hud;
	private Color col;

	public Clear(float x, float y, float radius, ID id, Handler handler) {
		super(x, y, radius, id);
		this.handler = handler;

		velX = 0;
		velY = ran.nextInt(5) + 1;
	}

	public void tick() {
		
		x += velX;
		y += velY;
		col = new Color(ran.nextInt(150 - 50) + 50, ran.nextInt(50 - 0) + 0, ran.nextInt(255 - 200) + 200);
		if(y >= Game.HEIGHT) handler.removeObject(this);
		
		handler.addObject(new Trail((float)x, (float)y, (float)radius, ID.Trail, col, 16, 16, 0.1f, handler));
		
		pickUp();
	}
	
	private void pickUp() {
		for(int i = 0; i < handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);

			if(tempObject.getId() == ID.Player){
				
				//Distance between player and the enemy calculated
				double distance =  Math.pow((x - tempObject.getX()) * (x - tempObject.getX()) + (y - tempObject.getY()) * (y - tempObject.getY()), 0.5);
				//Check if Player is inside enemy
				if(tempObject.getRadius() >= radius && distance <= (tempObject.getRadius()-radius)){
					
						AudioPlayer.getSound("blast").play();
						handler.addObject(new ClearEffect((float)x, (float)y, 3000, ID.ClearEffect, 6000, 6000, 0.1f, handler));
						handler.clear();
						handler.removeObject(this);
				}
				
				//Check if enemy is inside Player
				else if(radius >= tempObject.getRadius() && distance <= (radius - tempObject.getRadius())){
				}	
				//Check if Player is outside the arena
				else if(distance > (radius + tempObject.getRadius())){
				}
				//Check if Player Collides with the enemy
				else{	
					
						AudioPlayer.getSound("blast").play();
						handler.addObject(new ClearEffect((float)x, (float)y, 3000, ID.ClearEffect, 6000, 6000, 0.1f, handler));
						handler.clear();
						handler.removeObject(this);
					}
				}
			}
		}

	public void render(Graphics g) {
		g.setColor(col);
		g.fillOval((int)x -(int)radius, (int)y-(int)radius, (int) radius*2, (int)radius*2);
		g.drawString("BLAST!", (int)x + 10, (int)y-20);
		
	}

}