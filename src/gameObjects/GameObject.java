package gameObjects;

import java.awt.Graphics;
import java.awt.Point;

import core.ID;

public abstract class GameObject {

	protected float x;
	protected float y;
	protected ID id;
	protected float velX, velY;
	protected float radius;
	protected Point point;
	protected int dmg;
	
	public GameObject(float x, float y, float radius,  ID id) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.id = id;
	}

	public abstract void tick();
	public abstract void render(Graphics g);


	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public double getArea(){
		return Math.PI * radius * radius;
	}
	
	public double getPerimeter(){
		return 2 * Math.PI * radius;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	
	
}
