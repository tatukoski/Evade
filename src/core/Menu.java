package core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import core.Game.STATE;
import gameObjects.Arena;
import gameObjects.BasicEnemy;
import gameObjects.Player;

public class Menu extends MouseAdapter{

	
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler, HUD hud){
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e){
		
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu){
			
			//Play Button
			if(mouseOver(mx, my, 515, 510, 200, 64)) { 
				game.gameState = STATE.Game;
				handler.dif = 1;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, 16, ID.Player, handler));	
				handler.clearEnemies();
				handler.addObject(new BasicEnemy(r.nextInt((Game.WIDTH-90 - 90) + 90), r.nextInt((Game.HEIGHT- 90 - 90)+ 90), 8, ID.BasicEnemy, handler));
				handler.addObject(new Arena(Game.WIDTH/2, Game.HEIGHT/2-20, 450, ID.Arena, handler));
				
				AudioPlayer.getSound("select").play();
				AudioPlayer.getMusic("gamemusic").loop();
				return;
				
			}
			//Help Button
			if(mouseOver(mx, my, 515, 610, 200, 64)) { 
				game.gameState = STATE.Help;
				AudioPlayer.getSound("select").play();
				return;
			}
	
			//Quit Button
			if(mouseOver(mx, my, 515, 710, 200, 64)) { 
				System.exit(1);
			}
		}
		
			//Back Button
			if(mouseOver(mx, my, 210, 350, 200, 64)) { 
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("select").play();
				return;
			}
		
			//Back Button for Help
			if(game.gameState == STATE.Help){
				if(mouseOver(mx, my, 530, 600, 200, 64)){
					game.gameState = STATE.Menu;
					AudioPlayer.getSound("select").play();
					return;
				}
			}
		
			//Try Again Button
			if(game.gameState == STATE.End){
				if(mouseOver(mx, my, 530, 700, 200, 64)){
					game.gameState = STATE.Menu;
					AudioPlayer.getSound("select").play();
					hud.setLevel(1);
					hud.setScore(0);
				}
			}
		}
	
	
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		if(game.gameState == STATE.Menu ){
		
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 20);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Evade", 535, 250);
		
		g.setFont(fnt2);
		g.drawRect(515, 510, 200, 64);
		g.drawString("Play", 590, 550);
		
		g.drawRect(515, 610, 200, 64);
		g.drawString("Help", 590, 650);
		
		g.drawRect(515, 710, 200, 64);
		g.drawString("Quit", 590, 750);
		
	}else if (game.gameState == STATE.Help){
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 20);
		Font fnt3 = new Font("arial", 1, 20);
			
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("HELP", 560, 200);
			
		g.setFont(fnt3);
		g.drawString("Stay inside the green circle and survive for as long as you can.", 330, 350);
		g.drawString("Controls:", 330, 390);
		g.drawString("W - UP", 330, 440);
		g.drawString("A - LEFT", 330, 470);
		g.drawString("S - DOWN", 330, 500);
		g.drawString("D - RIGHT", 330, 530);
		
		g.setFont(fnt2);
		g.setColor(Color.white);
		g.drawRect(530, 600, 200, 64);
		g.drawString("Back", 605, 640);
	}
	else if (game.gameState == STATE.End){
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 20);
		Font fnt3 = new Font("arial", 1, 15);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Game Over", 500, 250);
		
		g.setFont(fnt3);
		g.drawString("Your Score: " + hud.getScore(), 570, 300);
	
		g.setFont(fnt2);
		g.drawRect(530, 700, 200, 64);
		g.drawString("Try Again", 585, 740);

		}
	}
}
