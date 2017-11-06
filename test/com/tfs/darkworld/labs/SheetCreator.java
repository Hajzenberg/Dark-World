package com.tfs.darkworld.labs;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

import rafgfxlib.Util;

/* Alat koji omogucuje kreiranje chracter sheeta kombinovanjem slika koje predstavljaju
 * zasebne frejmove animacija , tako da olaksava manipulaciju sprite sheetom u igri.
 * Alat iscitava sve slike iz zadatog foldera i rasporedjuje ih u kolone na sheetu
 * na osnovu prefiksa koji je jednocifreni integer i pocinje od 1.
 * Parametri su dimenzije slika u folderu, putanja do direktorijuma i putanja do sheeta.
 * Podrzan je rad samo sa slikama iste velicine
 */

public class SheetCreator {

	private static final int FRAME_WIDTH = 60;
	private static final int FRAME_HEIGHT = 60;
	private static final String PATH_TO_DIR = "design/coin";
	private static final String PATH_TO_FINAL_IMAGE = "design/coin.png";
	

	public static void main(String[] args) {

		// lista koja cuva sve putanje iz zadatog direktorijuma
		ArrayList<Path> filePaths = new ArrayList<>();

		// dodamo sve pathove u listu
		try {
			filePaths.addAll(
					Files.walk(Paths.get(PATH_TO_DIR)).filter(Files::isRegularFile).collect(Collectors.toList()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * Sada od liste svih pathova pravimo listu listi koje sadrze
		 * pathove samo jedne animacije. Grupisemo ih.
		 */

		char lastStartingChar = '.';
		char currentStartingChar = '.';
		ArrayList<ArrayList<Path>> spritePaths = new ArrayList<>();
		int i = -1;
		int maxLen = 1;
		int tempLen = 1;

		/*
		 * Iteriramo kroz listu pathova i proveravamo kada se menja ime fajla, u
		 * tom trenutku dodajemo novu listu u listu pathova i u nju dodajemo sve
		 * frejmove tekuce animacije
		 */
		for (Path path : filePaths) {

			currentStartingChar = path.getName(path.getNameCount() - 1).toString().charAt(0);

			// System.out.println(path);
			// System.out.println(currentStartingChar + " " + lastStartingChar);
			// System.out.println(i);

			if (currentStartingChar != lastStartingChar) {
				i++;
				spritePaths.add(new ArrayList<Path>());
				maxLen = (tempLen < maxLen) ? maxLen : tempLen;
				tempLen = 0;
			}

			spritePaths.get(i).add(path);
			lastStartingChar = currentStartingChar;
			tempLen++;
		}

		// Za poslednji prolaz nece uci u if, pa kontrolisemo posle petlje
		maxLen = (tempLen < maxLen) ? maxLen : tempLen;

		// System.out.println(spritePaths.size());
		// System.out.println(maxLen);

		for (int j = 0; j < spritePaths.size(); j++) {
			System.out.println(spritePaths.get(j).toString());
		}

		// sirina konacne slike mora biti jednaka broju frejmova najduze
		// animacije, a visina ukupnom broju animacija
		WritableRaster dest = Util.createRaster(FRAME_WIDTH * maxLen, spritePaths.size() * FRAME_HEIGHT, true);

		int[] rgb = new int[4];

		for (int k = 0; k < spritePaths.size(); k++) {
			for (int l = 0; l < spritePaths.get(k).size(); l++) {
				// System.out.println(spritePaths.get(k).get(l).toString());
				BufferedImage img = Util.loadImage(spritePaths.get(k).get(l).toString());
				WritableRaster src = img.getRaster();

				// System.out.println(src.getWidth()+ " "+src.getHeight());

				for (int m = 0; m < src.getWidth(); m++) {
					for (int n = 0; n < src.getHeight(); n++) {
						// System.out.println("u piksel " + (k*FRAME_WIDTH + m)
						// + " " + (l*FRAME_WIDTH + n)+ " upisujem piksel sa "+
						// m +" "+ n);
						// int r = k*FRAME_WIDTH + m;
						// int t = l*FRAME_WIDTH + n;

						dest.setPixel(l * FRAME_WIDTH + m, k * FRAME_WIDTH + n, src.getPixel(m, n, rgb));
					}
				}

			}
			System.out.println(k + 1 + ". PASS");
		}

		System.out.println("DONE");

		BufferedImage done = Util.rasterToImage(dest);

		Util.saveImage(done, PATH_TO_FINAL_IMAGE);

	}

}
