package core;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {

	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void load(){
		
		try {
			
			musicMap.put("gamemusic", new Music("res/gamemusic.ogg"));
			
			soundMap.put("select", new Sound("res/select.ogg"));
			soundMap.put("coin", new Sound("res/coin.ogg"));
			soundMap.put("multiplier", new Sound("res/multiplier.ogg"));
			soundMap.put("powerup", new Sound("res/powerup.ogg"));
			soundMap.put("explosion", new Sound("res/explosion.ogg"));
			soundMap.put("speedup", new Sound("res/speedup.ogg"));
			soundMap.put("blast", new Sound("res/blast.ogg"));
			soundMap.put("bullet", new Sound("res/bullet.ogg"));
			soundMap.put("health", new Sound("res/health.ogg"));
		
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static Music getMusic(String key){
		return musicMap.get(key);
	}
	
	public static Sound getSound(String key){
		return soundMap.get(key);
	}
}
