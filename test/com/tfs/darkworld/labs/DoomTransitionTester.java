package com.tfs.darkworld.labs;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import rafgfxlib.GameFrame;
import rafgfxlib.Util;

public class DoomTransitionTester extends GameFrame {

	private static int COLUMNS = 100;

	private int columnWidth;
	private double maxDev = 200;
	private double maxDiff = 90;
	private double fallSpeed = 7;

	private BufferedImage logo;
	private BufferedImage background;

	/*
	 * Sadrzi podslike slike na kojoj se vrsi tranzicija Ima ih onoliko koliko
	 * je definisano u parametru COLUMNS
	 */
	private ArrayList<BufferedImage> columns;
	/*
	 * Sadrzi offsete koji se moraju potrositi pre nego sto krenemo da menjamo
	 * visinu na kojoj iscrtavamo sliku
	 */
	private double[] offsets;
	/* Sadrzi visine iscrtavanja za svaku sliku u nizu columns */
	private double[] heights;

	boolean done = true;

	public DoomTransitionTester() {
		super("Doom", 800, 600);

		columnWidth = 800 / COLUMNS;

		logo = Util.loadImage("design/doom_logo.jpg");
		background = Util.loadImage("design/doom_bg.jpg");

		columns = new ArrayList<>();
		offsets = new double[COLUMNS];
		heights = new double[COLUMNS];

		for (int i = 0; i < COLUMNS; i++) {
			columns.add(logo.getSubimage(i * columnWidth, 0, columnWidth, 600));
		}

		prepareOffsets();

		setUpdateRate(60);
		startThread();
	}

	public static void main(String[] args) {
		new DoomTransitionTester().initGameWindow();
	}

	@Override
	public void handleWindowInit() {

	}

	@Override
	public void handleWindowDestroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		g.drawImage(background, 0, 0, null);

		for (int i = 0; i < COLUMNS; i++) {
			g.drawImage(columns.get(i), i * columnWidth, (int) heights[i], null);
			// g.drawRect(i*columnWidth, 0, columnWidth, 600);
		}
	}

	@Override
	public void update() {
		melt();

	}

	@Override
	public void handleMouseDown(int x, int y, GFMouseButton button) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleMouseUp(int x, int y, GFMouseButton button) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleMouseMove(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleKeyDown(int keyCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleKeyUp(int keyCode) {
		// TODO Auto-generated method stub

	}

	private void prepareOffsets() {
		for (int i = 0; i < COLUMNS; i++) {
			/*
			 * obradjujemo prvu kolonu kao specijalni slucaj jer se offset
			 * trenutnog racuna prema offsetu prethodnika
			 */
			if (i == 0) {
				offsets[i] = -Math.random() * maxDev;
			} else {
				offsets[i] = offsets[i - 1] + Math.random() * maxDiff - maxDiff / 2;
			}

			/*
			 * Normalizujemo offsete tako da nijedan nije veci od 0, odnosno
			 * prisutan na ekranu u vreme iscrtavanja i da nije veci od
			 * maksimalne dozvoljene devijacije
			 */

			if (offsets[i] > 0) {
				offsets[i] = 0;
			} else if (offsets[i] < -maxDev) {
				offsets[i] = -maxDev;
			}
		}

		for (int i = 0; i < COLUMNS; i++) {
			//System.out.println(offsets[i]);
		}
	}

	private void melt() {

		for (int i = 0; i < COLUMNS; i++) {
			done = true;

			/*
			 * Dokle god ne potrosimo offset, odnosno dok ne postane veci od
			 * nule (na pocetku su svi setovani na vrednosti >= 0)/ * visina
			 * iscrtavanja slike je 0 (default vrednostu heights array-u)
			 * 
			 * Kada vrednost offseta padne ispod nule, pocinjemo da menjamo
			 * visinu na kojoj iscrtavamo sliku
			 * 
			 * Kada sve vrednosti u nizu budu vece od visine ekrana + mali
			 * offset znamo da se tranzicija izvrsila
			 */

			if (offsets[i] <= 0) {
				offsets[i] += fallSpeed;
				done = false;
			} else {
				heights[i] += fallSpeed;
				done = (heights[i] > 620) ? true : false;
			}
		}

		if (done) {
			System.out.println("DONE");
		}
	}

}
