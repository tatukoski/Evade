package gameObjects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import core.Handler;
import core.ID;

public class Trail extends GameObject {

	private float alpha = 1;
	private float life;
	
	private Handler handler;
	private Color color;
	
	private int width, height;
	
	public Trail(float x, float y, float radius, ID id, Color color, int width, int height, float life, Handler handler) {
		super(x, y, radius, id);
		this.handler = handler;
		this.color = color;
		this.width = width;
		this.height = height;
		this.life =  life;
	}

	public void tick() {

		if (alpha > life) {
			alpha -= (life - 0.0001f);
		}else handler.removeObject(this);
		
	}

	public void render(Graphics g) {
		Graphics2D g2 =(Graphics2D)g;
		
		g2.setComposite(makeTransparent(alpha));
		
		g.setColor(color);
		g.fillOval((int)x-(int)radius, (int)y-(int)radius, width, height);
		
		g2.setComposite(makeTransparent(1));	
	}
	
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}
}