package com.tfs.darkworld.loading;

import java.awt.image.WritableRaster;

import com.corax.graphics.generators.gradientperlin.GradientPerlinGenerator;

import rafgfxlib.Util;

public class SkyGenerator {
	private GradientPerlinGenerator gpGenerator;
	private int[][] gradient;

	public SkyGenerator() {
		super();
		this.gpGenerator = new GradientPerlinGenerator();
		gradient = Util.imageToGradient(Util.loadImage("design/skyGradient.png"));
	}

	public WritableRaster generateSky() {
		return gpGenerator.generateGradientPerlin(12, 0.7f, gradient);
	}

}
