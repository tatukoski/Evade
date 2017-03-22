package core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.JOptionPane;

import gameObjects.Arena;
import gameObjects.BasicEnemy;
import gameObjects.Player;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1550691097823471818L;
	
	public static final int WIDTH = 1280, HEIGHT = WIDTH /12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	public static boolean paused = false;
	
	private Random r;
	private Handler handler;
	private Spawn spawn;
	private PowerUpGenerator generator;
	private HUD hud;
	private Menu menu;
	
	//Highscore
	public static String highscore = "";
	
	public enum STATE {
		
		Menu(),
		Select(),
		Help(),
		Shop(),
		Game(),
		End();
	};
	
	public static STATE gameState = STATE.Menu;
	
	public Game() {
		
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		
		AudioPlayer.load();
		
		new Window(WIDTH, HEIGHT, "Evade", this);
				
		spawn = new Spawn(handler, hud, this);
		generator = new PowerUpGenerator(handler, hud, this);
		r = new Random();
		
		if(gameState == STATE.Game){
			handler.dif = 1;
			handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, 16, ID.Player, handler));	
			handler.addObject(new BasicEnemy(r.nextInt((Game.WIDTH- 90 - 90) + 90), r.nextInt((Game.HEIGHT- 90 - 90)+ 90), 8, ID.BasicEnemy, handler));
			handler.addObject(new Arena(WIDTH/2, HEIGHT/2-20, 450, ID.Arena, handler));
			
			AudioPlayer.getMusic("gamemusic").loop();
			
		}
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
		
		if(highscore.equals(""))
		{
			highscore = this.GetHighscore();
		}
	}
	
	public synchronized void stop() {
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.nanoTime();
		while (running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
		}
		stop();
	}
	
	private void tick() {
		
		if(gameState == STATE.Game)
		{
			if(!paused)
			{
				hud.tick();
				handler.tick();
				spawn.tick();
				generator.tick();
				
				if(HUD.HEALTH <= 0){
					HUD.HEALTH = 100;
					gameState = STATE.End;
						
					handler.clearEnemies();
					AudioPlayer.getMusic("gamemusic").stop();
					}
			}
		
			
			
		}else if (gameState == STATE.Menu || gameState == STATE.Help){
			menu.tick();
			handler.tick();
		
		}else if (gameState == STATE.End){
			menu.tick();
			handler.tick();
			CheckScore();
		}
		
		
	}
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		if(paused){
			g.setColor(Color.white);
			g.drawString("PAUSED", 100, 100);
		}
		
		
		
		if(gameState == STATE.Game)
		{
			hud.render(g);
			
		}else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End){
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public String GetHighscore(){ 
		
		FileReader readFile = null;
		BufferedReader reader = null;
		try
		{
			readFile = new FileReader("highscores.dat");
			reader = new BufferedReader(readFile);
			return reader.readLine();
		}
		
		catch (Exception e)
		{
			return "Kala-Bot:0";
		}
		finally
		{
			try{
				if(reader != null)
					reader.close();
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public void CheckScore(){
		
		if(hud.score > Integer.parseInt((highscore.split(":")[1]))) 
		{
			String name = JOptionPane.showInputDialog("New highscore! What is your name?");
			highscore = name + ":" + hud.score;
			
			File scoreFile = new File("highscores.dat");
			if(!scoreFile.exists())
			{
				try {
					scoreFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		FileWriter writeFile = null;
		BufferedWriter writer = null;
		try
		{
			writeFile = new FileWriter(scoreFile);
			writer = new BufferedWriter(writeFile);
			writer.write(this.highscore);
		}
		catch (Exception e)
		{
			//Errors
		}
		finally
		{
			try
			{
				if(writer != null)
					writer.close();
			}
			catch (Exception e) {}
			}
		}
	}
	
	public static float clamp(float var, float min, float max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	public static void main(String[] args) {
		new Game();
	}

}
