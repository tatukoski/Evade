package powerUps;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import core.AudioPlayer;
import core.Game;
import core.HUD;
import core.Handler;
import core.ID;
import effects.ScoreEffect;
import gameObjects.GameObject;
import gameObjects.Trail;



public class Score extends GameObject {
	
	Random ran = new Random();
	private double timer;
	private Handler handler;
	private int randomScore = ran.nextInt(2000 - 500) + 500;
	private Color col;

	public Score(float x, float y, float radius, ID id, Handler handler) {
		super(x, y, radius, id);
		this.handler = handler;
		
		velX = 0;
		velY = ran.nextInt(5) + 1;
		
		timer = 10000;
	}

	public void tick() {
		
		x += velX;
		y += velY;
		
		if(y >= Game.HEIGHT) handler.removeObject(this);
		col = new Color(ran.nextInt(255 - 220) + 220, ran.nextInt(255 - 200) + 200, ran.nextInt(20 - 0) + 0);
		handler.addObject(new Trail((float)x, (float)y, (float)radius, ID.Trail, col, 16, 16, 0.1f, handler));
		
		pickUp();
		
		lottery();
	}
	
	private void lottery(){
		//Randomize the amount of score for every pickup
		if(timer >= 0) timer--;
		else{
			randomScore = ran.nextInt(2000 - 500) + 500;
			timer = 100;
		}
	}
	
	private void pickUp() {
		for(int i = 0; i < handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);

			if(tempObject.getId() == ID.Player){
				
				//Distance between player and the enemy calculated
				double distance =  Math.pow((x - tempObject.getX()) * (x - tempObject.getX()) + (y - tempObject.getY()) * (y - tempObject.getY()), 0.5);
				//Check if Player is inside enemy
				if(tempObject.getRadius() >= radius && distance <= (tempObject.getRadius()-radius)){
					
					AudioPlayer.getSound("coin").play();
					HUD.score += randomScore;
					handler.addObject(new ScoreEffect((float)x, (float)y, 3000, ID.ScoreEffect, 6000, 6000, 0.1f, handler));
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
					
					AudioPlayer.getSound("coin").play();
					HUD.score += randomScore;
					handler.addObject(new ScoreEffect((float)x, (float)y, 3000, ID.ScoreEffect, 6000, 6000, 0.1f, handler));
					handler.removeObject(this);
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(col);
		g.fillOval((int)x -(int)radius, (int)y-(int)radius, (int) radius*2, (int)radius*2);
		g.drawString("SCORE + " + randomScore, (int)x + 10, (int)y-20);
		
	}

}