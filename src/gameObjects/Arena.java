package gameObjects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.Random;

import core.Game;
import core.HUD;
import core.Handler;
import core.ID;
import effects.DamageEffect;

public class Arena extends GameObject {

	private Handler handler;
	private double colGreen = 255;
	
	Random ran = new Random();
	
	public Arena(float x, float y, float radius, ID id, Handler handler) {
		super(x, y, radius, id);
		
		this.handler = handler;
		
		velX = (ran.nextInt(4 - 2) + 2);
		velY = (ran.nextInt(4 - 2) + 2);

	}
	
	public void tick() {		
		x += velX;
		y += velY;
		
		colGreen = Game.clamp((float)colGreen, (float)0, (float)255); //This in game!!
		
		radius -= 0.05;
		
		radius = Game.clamp(radius, 48, 450);
		
		if(y <= radius + 5 || y >= Game.HEIGHT-radius-34) velY *= -1;
		if(x <= radius + 5 || x >= Game.WIDTH-radius-10) velX *= -1;
		
		insideArena();
	}

	
	private void insideArena(){
		for(int i = 0; i < handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);
			
			//Distance between the player and arena
			double distance =  Math.pow((x - tempObject.getX()) * (x - tempObject.getX()) + (y - tempObject.getY()) * (y - tempObject.getY()), 0.5);
			if(tempObject.getId() == ID.Player){
				//Check if player is inside the arena
				if(tempObject.getRadius() >= radius && distance <= (tempObject.getRadius()-radius)){
				}
				//Check if arena is inside player
				else if(radius >= tempObject.getRadius() && distance <= (radius - tempObject.getRadius())){
					colGreen = 255;
				}	
				//
				else if(distance > (radius*2 + tempObject.getRadius())){
					
				}
				//Check if Player is outside arena
				else{	
					HUD.HEALTH -= 0.5;
					colGreen = 100;
				}
			}
		}
	}

	public void render(Graphics g) {
		Graphics2D g2 =(Graphics2D)g;
		
		g.setColor(new Color(75, (int) colGreen,0));
		float thickness = 3;
		Stroke oldStroke = g2.getStroke();
		g2.setStroke(new BasicStroke(thickness));
		g.drawOval((int)x-(int)radius, (int)y-(int)radius, (int)radius*2, (int)radius*2);
		g2.setStroke(oldStroke);
	}
	
	public void setColor(){
		
	}

}