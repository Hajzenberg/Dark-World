package com.tfs.darkworld.res.fonts;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class Fonts {

	private static final Font SERIF_FONT = new Font("serif", Font.PLAIN, 24);

	private ConcurrentHashMap<String, Font> mFonts;

	public Fonts() {
		mFonts = new ConcurrentHashMap<>();

		loadFonts();
	}

	private void loadFonts() {
		try {

			InputStream fileName = getClass().getResourceAsStream("PhantomFingers.ttf");

			Font font = Font.createFont(Font.TRUETYPE_FONT, fileName);

			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

			ge.registerFont(font);

			Font f2 = new Font("Phantom Fingers", Font.PLAIN, 48);

			mFonts.put("Phantom Fingers", f2);
		} catch (Exception ex) {
			System.out.println("Error loading font");
		}
	}

	public Font getFont(String name) {

		Font font = mFonts.get(name);

		if (font == null) {
			return SERIF_FONT;
		} else {
			return font;
		}
	}

}
