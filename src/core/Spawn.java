package core;

import java.util.Random;

import gameObjects.BasicEnemy;
import gameObjects.HorizontalEnemy;
import gameObjects.SmartEnemy;
import gameObjects.Tower;
import gameObjects.Tower2;
import gameObjects.VerticalEnemy;

public class Spawn {

	private Handler handler;
	private Game game;
	private HUD hud;
	private Random ran = new Random();
	
	private int scoreKeep = 0;
	private int timer;
	private int bonusEnemyNr;
	
	public Spawn(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		
		timer = 300;
	}
	
	private void lottery(){
		
		bonusEnemyNr = ran.nextInt(4);
		}
	
	private void addEnemy(){
		
		if(bonusEnemyNr == 0){
			handler.addObject(new BasicEnemy(ran.nextInt(Game.WIDTH-50), ran.nextInt(Game.HEIGHT-50), 8, ID.BasicEnemy, handler));
		}else if (bonusEnemyNr == 1){
			handler.addObject(new HorizontalEnemy(ran.nextInt(Game.WIDTH-50), ran.nextInt(Game.HEIGHT-50), 8, ID.HorizontalEnemy, handler));
		}else if (bonusEnemyNr == 2){
			handler.addObject(new VerticalEnemy(ran.nextInt(Game.WIDTH-50), ran.nextInt(Game.HEIGHT-50), 8, ID.VerticalEnemy, handler));
		}else if (bonusEnemyNr == 3){
			handler.addObject(new SmartEnemy(ran.nextInt(Game.WIDTH-50), ran.nextInt(Game.HEIGHT-50), 8, ID.SmartEnemy, handler));
		}
	}
		
	public void tick() {
		
		lottery();
		if (timer >= 0){
			timer--;
		}else{ 
			addEnemy();
			timer = ran.nextInt(300 - 200) + 200;;
		}
		
		scoreKeep++;
		
		if (scoreKeep >= 350) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel()+ 1 );
					
			if (hud.getLevel() == 5) {
				handler.addObject(new Tower(ran.nextInt(Game.WIDTH-50), 48, 36, ID.Tower, handler));
			}else if (hud.getLevel() == 10){
				handler.addObject(new Tower2(48, ran.nextInt(Game.HEIGHT-50), 36, ID.Tower, handler));
			}else if (hud.getLevel() == 15){
				handler.addObject(new Tower(ran.nextInt(Game.WIDTH-50), 48, 36, ID.Tower, handler));
			}else if (hud.getLevel() == 25){
				handler.addObject(new Tower2(48, ran.nextInt(Game.HEIGHT-50), 36, ID.Tower, handler));
			}else if (hud.getLevel() == 30){
				handler.addObject(new Tower(ran.nextInt(Game.WIDTH-50), 48, 36, ID.Tower, handler));
			}else if (hud.getLevel() == 35){
				handler.addObject(new Tower(ran.nextInt(Game.WIDTH-50), 48, 36, ID.Tower, handler));
			}else if (hud.getLevel() == 40){
				handler.addObject(new Tower2(48, ran.nextInt(Game.HEIGHT-50), 36, ID.Tower, handler));
			}else if (hud.getLevel() == 45){
				handler.addObject(new Tower(ran.nextInt(Game.WIDTH-50), 48, 36, ID.Tower, handler));
		}
	}
	}
}
