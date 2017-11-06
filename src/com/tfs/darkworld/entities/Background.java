package com.tfs.darkworld.entities;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import com.tfs.darkworld.res.CommonRasters;

public class Background extends GameEntity {

	private static int TOP_MOUNTAIN_OFFSET = 120;
	private static int TOP_FOREST_OFFSET = 295;
	private static int LEFT_COIN_OFFSET = 400;
	// minimalna visina na kojoj se iscrtava coin
	private static int TOP_COIN_OFFSET = 100;
	// y range u kome se iscrtavaju coini, dodaje se na bottom offset
	private static int COIN_HEIGHT_RANGE = 250;

	private static double SCALE_MOUNTAIN_SPEED = 0.5;
	private static double SCALE_SKY_SPEED = 0.2;

	// Array koji cuva dostupne tileove
	private ArrayList<BackgroundTile> forestTileArray;
	private ArrayList<BackgroundTile> mountainTileArray;

	// Lista pozadina koje se trenutno prikazuju
	private Queue<BackgroundTile> forestTileQueue;
	private Queue<BackgroundTile> mountainTileQueue;
	private Deque<Coin> coinQueue;

	private SkyTile skyTile;

	Random random;

	public Background(int sw, int sh) {
		super(0, 0, 0);

		mDX = 0.5;

		random = new Random();

		initTiles();
		initCoins();
	}

	/*
	 * Inicijalizujem niz koji cuva dva puta po tri razlicita tile-a za forest i
	 * mountain queuove
	 */
	private void initTiles() {

		skyTile = new SkyTile(SCALE_SKY_SPEED, mDX);

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

	private void initCoins() {
		coinQueue = new LinkedList<>();

		for (int i = 0; i < 10; i++) {
			coinQueue.add(
					new Coin((i + 1) * LEFT_COIN_OFFSET, random.nextInt(COIN_HEIGHT_RANGE) + TOP_COIN_OFFSET, 0.7));
		}
	}

	@Override
	public void update() {

		skyTile.update();

		updateForestTiles();
		updateMountainTiles();
		updateCoins();
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

	/* Ako se igrac intersectovao sa coinom, coin se biti ozanacen
	 * kao collected. updateCoins proverava da li je coin collectovan
	 * ili se nalazi van ekrana, ako jeste uklanja ga iz queua i dodaje
	 * u niz potrosenih. Na kraju metode prolazi kroz listu potrosenih
	 * i reciklira ih tako sto osvezi koordinate i doda ih na kraj queuea.
	 */
	private void updateCoins() {

		ArrayList<Coin> collected = new ArrayList<>();

		Iterator<Coin> iterator = coinQueue.iterator();

		while (iterator.hasNext()) {
			Coin coin = iterator.next();
			coin.update();
			if (coin.isCollected() || coin.mX < - 60) {
				collected.add(coin);
				iterator.remove();
			}
		}

		for (Coin coin : collected) {
			coin.mX = coinQueue.peekLast().mX + LEFT_COIN_OFFSET;
			coin.mY = random.nextInt(COIN_HEIGHT_RANGE) + TOP_COIN_OFFSET;
			coin.intersectionBody.updateIntersectionBody(coin.mX, coin.mY);
			coin.setCollected(false);
			coinQueue.add(coin);
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

		for (Coin coin : coinQueue) {
			coin.render(g, sw, sh);
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

	public void findIntersectionsWith(GameEntity gameEntity) {
		for (Coin coin : coinQueue) {
			gameEntity.intersect(coin);
		}
	}

	@Override
	public void intersect(GameEntity ge) {

	}
}
