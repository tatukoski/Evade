package core;

import java.awt.Graphics;
import java.util.LinkedList;

import gameObjects.GameObject;
import gameObjects.Player;

public class Handler {

	public float speed = 5;
	public float dif = 1;
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		for(int i = 0; i <object.size(); i++){
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
			dif += 0.00001;
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void clearEnemies() {
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			if(tempObject.getId() == ID.Player)
			{
				object.clear();
				if(Game.gameState != Game.STATE.End)
				
				addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), 16, ID.Player, this));
			}
		}
	}
	
	public void clear() {
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.VerticalEnemy || tempObject.getId() == ID.HorizontalEnemy)
			{
				removeObject(tempObject);
				
			}
		}
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
}
