package effects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import core.Game;
import core.Handler;
import core.ID;
import gameObjects.GameObject;

public class ClearEffect extends GameObject {

	private float alpha = 1;
	private float life;
	private Color col;
	private Game game;
	private Handler handler;
	private int width, height;
	
	public ClearEffect(float x, float y, float radius, ID id, int width, int height, float life, Handler handler) {
		super(x, y, radius, id);
		this.handler = handler;
		this.width = width;
		this.height = height;
		this.life =  life;
		col = new Color(126, 17, 173);
	}

	public void tick() {

		if (alpha > life) {
			alpha -= (life - 0.08f);
		}else handler.removeObject(this);
		
	}

	public void render(Graphics g) {
		Graphics2D g2 =(Graphics2D)g;
		Font fnt4 = new Font("arial", 1, 200);
	
		g2.setComposite(makeTransparent(alpha));
		
		g.setColor(col);
		g.fillOval((int)x-(int)radius, (int)y-(int)radius, width, height);
		g.drawString("BLAST", Game.WIDTH/2, Game.HEIGHT/2);
		
		g2.setComposite(makeTransparent(1));
	}
	
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}
}