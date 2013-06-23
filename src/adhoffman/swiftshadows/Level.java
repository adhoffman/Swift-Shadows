package adhoffman.swiftshadows;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Level {

	private TiledMap map;
	private String levelFilePath;
	private LevelTitle levelTitle;

	public Level(LevelTitle levelTitle) throws SlickException {
		this.levelTitle = levelTitle;
		createMap();
	}

	private void createMap() throws SlickException {
		map = new TiledMap(levelFilePath + levelTitle + ".tmx");
	}

	public TiledMap getMap() {
		return map;
	}

}
