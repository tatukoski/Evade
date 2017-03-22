package core;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;

public class HUD {
	
	public static float HEALTH = 100;
	private float greenValue = 255f;
	
	public static int score = 0;

	public static int bounds = 0;
	private int level = 1;
	
	public void tick(){
		
		HEALTH = Game.clamp(HEALTH, 0, 100 + (bounds/2));
		greenValue = HEALTH * 2;
		greenValue = Game.clamp(greenValue, 0, 255); //This in game!!
		score++;
	}
	
	 public void render(Graphics g) {
		 g.setColor(Color.gray);
		 g.fillRect(15, 15, 200 + bounds, 32);
		 g.setColor(new Color(75, (int) greenValue, 0));
		 g.fillRect(15, 15, (int) HEALTH * 2, 32);
		 g.setColor(Color.white);
		 g.drawRect(15, 15, 200 + bounds, 32);
		 
		 g.drawString("Highscore " + Game.highscore, 15, 64);
		 g.drawString("Score: " + score, 15, 80);
		 g.drawString("Level: " + level, 15, 96);
	 }
	 
	 public void setScore(int score) {
		 this.score = score;
	 }
	 public int getScore() {
		 return score;
	 }
	 
	 public int getLevel(){
		 return level;
	 }
	 
	 public void setLevel(int level){
		 this.level = level;
	 }
}
 