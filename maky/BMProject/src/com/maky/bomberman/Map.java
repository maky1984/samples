package com.maky.bomberman;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.media.opengl.GL2;

import com.maky.App;

public class Map {

	private final int size;
	private final int cellW, cellH;
	protected final MapCell[][] map;
	
	public Map(String filename) throws IOException {
		map = loadMap(filename);
		this.size = map.length;
		this.cellW = App.VIRTUAL_WIDTH/size;
		this.cellH = App.VIRTUAL_HEIGHT/size;
	}
	
	public Map(int size) {
		this.size = size;
		map = createMap(size, null);
		this.cellW = App.VIRTUAL_WIDTH/size;
		this.cellH = App.VIRTUAL_HEIGHT/size;
	}
	
	private static MapCell[][] createMap(int size, int[][] types) {
		int cellW = App.VIRTUAL_WIDTH/size;
		int cellH = App.VIRTUAL_HEIGHT/size;
		MapCell[][] map = new MapCell[size][size];
		for (int i=0;i<map.length;i++) {
			for (int j=0; j<map[i].length;j++) {
				if (types == null) {
					map[i][j] = new MapCell(cellW*i, cellH*j, cellW, cellH);
				} else {
					map[i][j] = new MapCell(cellW*i, cellH*j, cellW, cellH, types[i][j]);
				}
			}
		}
		return map;
	}
	
	public int getCellH() {
		return cellH;
	}
	
	public int getCellW() {
		return cellW;
	}
	
	public void set(int i, int j, MapCell cell) {
		map[i][j] = cell;
	}
	
	public MapCell get(int i, int j) {
		return map[i][j];
	}
	
	public MapCell remove(int i, int j) {
		MapCell cell = get(i, j);
		set(i, j, null);
		return cell;
	}
	
	private MapCell next(MapCell cell, int dx, int dy) {
		int nx = cell.getX() / cellW + dx;
		int ny = cell.getY() / cellH + dy;
		if (nx >= 0 && ny >= 0 &&  nx < map.length && ny < map[nx].length) {
			return map[nx][ny];
		}
		return null;
	}
	
	public MapCell rightFrom(MapCell cell) {
		return next(cell, 1, 0);
	}
	
	public MapCell leftFrom(MapCell cell) {
		return next(cell, -1, 0);
	}
	
	public MapCell upFrom(MapCell cell) {
		return next(cell, 0, -1);
	}
	
	public MapCell downFrom(MapCell cell) {
		return next(cell, 0, 1);
	}
	
	public boolean hasCollision(int i, int j) {
		if (map[i][j].getType() != MapCell.TYPE_GROUND) {
			return true;
		}
		return false;
	}

	public void render(GL2 gl2) {
		for (int i=0;i<map.length;i++) {
			for (int j=0;j<map[i].length;j++) {
				map[i][j].render(gl2);
			}
		}
	}

	public void update() {
		for (int i=0;i<map.length;i++) {
			for (int j=0;j<map[i].length;j++) {
				map[i][j].update();
			}
		}
	}
	
	public void saveMap(String filename) {
		File file = new File(filename);
		try {
			DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
			out.writeInt(size);
			for (int i=0;i<map.length;i++) {
				for (int j=0;j<map[i].length;j++) {
					out.writeInt(map[i][j].getType());
				}
			}
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static MapCell[][] loadMap(String filename) throws IOException {
		File file = new File(filename);
		DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
		int size = in.readInt();
		int[][] types = new int[size][size];
		for (int i=0;i<types.length;i++) {
			for (int j=0;j<types[i].length;j++) {
				types[i][j] = in.readInt();
			}
		}
		return createMap(size, types);
	}
}
