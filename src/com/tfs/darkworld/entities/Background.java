package com.tfs.darkworld.entities;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import com.tfs.darkworld.res.CommonRasters;

public class Background extends GameEntity {

	private static int TOP_MOUNTAIN_OFFSET = 120;
	private static int TOP_FOREST_OFFSET = 295;

	private static double SCALE_MOUNTAIN_SPEED = 0.5;
	private static double SCALE_SKY_SPEED = 0.2;

	// Array koji cuva dostupne tileove
	private ArrayList<BackgroundTile> forestTileArray;
	private ArrayList<BackgroundTile> mountainTileArray;

	// Lista pozadina koje se trenutno prikazuju
	private Queue<BackgroundTile> forestTileQueue;
	private Queue<BackgroundTile> mountainTileQueue;

	private SkyTile skyTile;

	Random random;

	public Background(int sw, int sh) {
		super(0, 0, 0);

		mDX = 0.5;

		random = new Random();

		initTiles();
	}

	/*
	 * Inicijalizujem niz koji cuva dva puta po tri razlicita tile-a za forest i
	 * mountain queuove
	 */
	private void initTiles() {

		skyTile = new SkyTile(SCALE_SKY_SPEED);

		forestTileArray = new ArrayList<>();
		mountainTileArray = new ArrayList<>();

		forestTileQueue = new LinkedList<>();
		mountainTileQueue = new LinkedList<>();

		for (int i = 0; i < 6; i++) {
			BackgroundTile tile = new BackgroundTile(CommonRasters.getMountainBackground().getWidth(),
					CommonRasters.getMountainBackground().getHeight(), CommonRasters.getMountainBackground(),
					SCALE_MOUNTAIN_SPEED, TOP_MOUNTAIN_OFFSET, mDX);
			mountainTileArray.add(tile);
		}

		for (int i = 0; i < 6; i++) {

			BackgroundTile tile = new BackgroundTile(CommonRasters.getForestBackground().getWidth(),
					CommonRasters.getForestBackground().getHeight(), CommonRasters.getForestBackground(), 1,
					TOP_FOREST_OFFSET, mDX);
			forestTileArray.add(tile);
		}

		addFirstTiles();
	}

	/*
	 * popunim queuove sa po dva pocetna tile-a koja nasumicno odaberem prvi ima
	 * koordinatu x, drugi x + duzina tile-a da bi se prikazali jedan za drguim
	 */
	private void addFirstTiles() {

		System.out.println("Dodajem prve tileove");

		BackgroundTile tile = mountainTileArray.get(random.nextInt(4));
		mountainTileQueue.add(tile);
		mountainTileArray.remove(tile);

		tile = mountainTileArray.get(random.nextInt(4));
		tile.mX += tile.mWidth;
		mountainTileQueue.add(tile);
		mountainTileArray.remove(tile);

		tile = forestTileArray.get(random.nextInt(4));
		forestTileQueue.add(tile);
		forestTileArray.remove(tile);

		tile = forestTileArray.get(random.nextInt(4));
		tile.mX += tile.mWidth;
		forestTileQueue.add(tile);
		forestTileArray.remove(tile);
	}

	@Override
	public void update() {

		skyTile.update();
		
		updateForestTiles();
		updateMountainTiles();
	}

	private void updateMountainTiles() {

		for (BackgroundTile backgroundTile : mountainTileQueue) {
			backgroundTile.update();
		}

		if (mountainTileQueue.peek().mX + mountainTileQueue.peek().mWidth < 0) {

			System.out.println("\nPLANINA OSVEZENA\n");

			BackgroundTile oldTile = mountainTileQueue.poll();
			BackgroundTile newTile = mountainTileArray.get(random.nextInt(4));
			mountainTileArray.remove(newTile);
			newTile.mX = mountainTileQueue.peek().mX + mountainTileQueue.peek().mWidth;
			newTile.mDX = mountainTileQueue.peek().mDX;
			mountainTileQueue.add(newTile);
			mountainTileArray.add(oldTile);
		}
	}

	private void updateForestTiles() {

		for (BackgroundTile backgroundTile : forestTileQueue) {
			backgroundTile.update();
		}

		if (forestTileQueue.peek().mX + forestTileQueue.peek().mWidth < 0) {

			BackgroundTile oldTile = forestTileQueue.poll();
			BackgroundTile newTile = forestTileArray.get(random.nextInt(4));
			forestTileArray.remove(newTile);
			newTile.mX = forestTileQueue.peek().mX + forestTileQueue.peek().mWidth;
			newTile.mDX = forestTileQueue.peek().mDX;
			forestTileQueue.add(newTile);
			forestTileArray.add(oldTile);
		}
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {

		skyTile.render(g, sw, sh);

		for (BackgroundTile backgroundTile : mountainTileQueue) {
			backgroundTile.render(g, sw, sh);
		}

		for (BackgroundTile backgroundTile : forestTileQueue) {
			backgroundTile.render(g, sw, sh);
		}
	}

	@Override
	public void setDX(double mDX) {
		super.setDX(mDX);

		skyTile.setDX(mDX);

		for (BackgroundTile backgroundTile : forestTileQueue) {
			backgroundTile.setDX(mDX);
		}

		for (BackgroundTile backgroundTile : mountainTileQueue) {
			backgroundTile.setDX(mDX);
		}
	}

	@Override
	public void intersect(GameEntity ge) {
		// TODO Auto-generated method stub
	}
}
