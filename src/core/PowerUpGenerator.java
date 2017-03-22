package core;

import java.util.Random;

import powerUps.Clear;
import powerUps.Heal;
import powerUps.Multiplier;
import powerUps.Score;
import powerUps.SpeedUp;

public class PowerUpGenerator {

	private Handler handler;
	private HUD hud;
	private Game game;
	private Random r = new Random();
	private double timer;
	private int powerUpNumber;
	
	
	public PowerUpGenerator(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		
		timer = 385;
	}
	
	public void tick() {
		
		lottery();
		if (timer >= 0) timer--;
		else {
			createPowerUp();
			timer = r.nextInt(400 - 200) + 200;
			}
		}
	
	private void lottery(){
		
		powerUpNumber = r.nextInt(6);
		}
	
	private void createPowerUp(){
			
		if(powerUpNumber == 0){
				handler.addObject(new Heal(r.nextInt(Game.WIDTH-75), 10, 8, ID.Heal, handler));
		}else if (powerUpNumber == 1){
				handler.addObject(new SpeedUp(r.nextInt(Game.WIDTH-75), 10, 8, ID.SpeedUp, handler));
		}else if (powerUpNumber == 2){
				handler.addObject(new Multiplier(r.nextInt(Game.WIDTH-75), 10, 8, ID.Multiplier, handler));
		}else if (powerUpNumber == 4){
			handler.addObject(new Clear(r.nextInt(Game.WIDTH-75), 10, 8, ID.Clear, handler));
		}else if (powerUpNumber == 5){
			handler.addObject(new Score(r.nextInt(Game.WIDTH-75), 10, 8, ID.Score, handler));
		}
	}
}

