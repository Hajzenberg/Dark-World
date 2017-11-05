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

	// Lista pozadina koje se trenutno apdejtuju
	private Queue<BackgroundTile> forestTileQueue;
	private Queue<BackgroundTile> mountainTileQueue;

	Random random;

	// Alfa offset vrednosti za nebo
	private float deltaAlphaOffset = 0.001f;
	private float alphaOffset = 1f;

	public Background(int sw, int sh) {
		super(0, 0, 0);

		mDX = 0.4;

		random = new Random();

		initTiles();
	}

	/*
	 * Inicijalizujem niz koji cuva tri razlicita tile-a za forest i mountain
	 * queuove
	 */
	private void initTiles() {

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
		
		BackgroundTile tile1 = mountainTileArray.get(random.nextInt(3));
		mountainTileQueue.add(tile1);
		mountainTileArray.remove(tile1);
		System.out.println("prvi m " + tile1.mX);

		BackgroundTile tile2 = mountainTileArray.get(random.nextInt(3));
		tile2.mX += tile1.mWidth;
		mountainTileQueue.add(tile2);
		mountainTileArray.remove(tile2);
		System.out.println("drugi m " + tile2.mX);
		
		BackgroundTile tile3 = forestTileArray.get(random.nextInt(3));
		forestTileQueue.add(tile3);
		forestTileArray.remove(tile3);
		System.out.println("prvi f " + tile3.mX);
		
		BackgroundTile tile4 = forestTileArray.get(random.nextInt(3));
		tile4.mX += tile3.mWidth;
		forestTileQueue.add(tile4);
		forestTileArray.remove(tile4);
		System.out.println("drugi f " + tile4.mX);
	}

	@Override
	public void update() {
		// cuvam ovo jos zbog neba koje se iscrtava ovde kroz drawimage
		mX -= mDX;

		// if (alphaOffset >= 1 || alphaOffset <= 0) {
		// deltaAlphaOffset = -1f * deltaAlphaOffset;
		// }
		//
		// alphaOffset = alphaOffset + deltaAlphaOffset;

		updateBackgroundTiles();
	}

	private void updateBackgroundTiles() {

		// System.out.println(forestTileQueue.peek().mX +
		// forestTileQueue.peek().mWidth);

		for (BackgroundTile backgroundTile : mountainTileQueue) {
			backgroundTile.update();
			//System.out.println(backgroundTile.mX);
		}

		for (BackgroundTile backgroundTile : forestTileQueue) {
			backgroundTile.update();
			//System.out.println(backgroundTile.mX);
		}

		// Proveravamo za forest
		if (mountainTileQueue.peek().mX + mountainTileQueue.peek().mWidth < 0) {

			System.out.println("PLANINA OSVEZENA");

			BackgroundTile tmp = mountainTileQueue.poll();
			BackgroundTile tile = forestTileArray.get(random.nextInt(3));
			tile.mX = mountainTileQueue.peek().mX + mountainTileQueue.peek().mWidth;
			tile.mDX = mDX;
			
			mountainTileQueue.add(tile);
			mountainTileArray.add(tmp);
		}
		
		if (forestTileQueue.peek().mX + forestTileQueue.peek().mWidth < 0) {

			System.out.println("SUMA OSVEZENA");

			BackgroundTile tmp = forestTileQueue.poll();
			BackgroundTile tile = forestTileArray.get(random.nextInt(3));
			tile.mX = mountainTileQueue.peek().mX + mountainTileQueue.peek().mWidth;
			tile.mDX = mDX;

			forestTileQueue.add(tile);
			forestTileArray.add(tmp);
		}
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {

		// float[] scales = { 1f, 1f, 1f, mAlphaOffset };
		// float[] offsets = new float[4];
		// RescaleOp rop = new RescaleOp(scales, offsets, null);

		g.drawImage(CommonRasters.getLightSky(), (int) (SCALE_SKY_SPEED * mX), (int) (mY), null);

		for (BackgroundTile backgroundTile : mountainTileQueue) {
			backgroundTile.render(g, sw, sh);
		}

		for (BackgroundTile backgroundTile : forestTileQueue) {
			backgroundTile.render(g, sw, sh);
		}

		// g.drawImage(mountainTileArray.get(0).getImage(), (int)
		// (SCALE_MOUNTAIN_SPEED * mX),
		// (int) mY + TOP_MOUNTAIN_OFFSET, null);
		// g.drawImage(forestTileArray.get(0).getImage(), (int) (mX), (int) mY +
		// TOP_FOREST_OFFSET, null);
	}

	@Override
	public void setDX(double mDX) {
		super.setDX(mDX);

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
