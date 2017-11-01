package com.tfs.darkworld.labs;

import java.awt.image.WritableRaster;

import com.tfs.darkworld.loading.SkyGenerator;
import com.tfs.darkworld.loading.transformations.DarkTransformation;
import com.tfs.darkworld.loading.transformations.LightTransformation;
import com.tfs.darkworld.loading.transformations.ScalingTransformation;

import rafgfxlib.ImageViewer;
import rafgfxlib.Util;

public class DarkBackgroundTest {
	public static void main(String[] args) {
		
		WritableRaster bg = new SkyGenerator().generateSky();
		bg = new ScalingTransformation().process(bg);
		
		WritableRaster light = new LightTransformation().process(bg);
		
		WritableRaster dark = new DarkTransformation().process(bg);
		
		ImageViewer.showImageWindow(Util.rasterToImage(light),"dark world");
		ImageViewer.showImageWindow(Util.rasterToImage(dark),"dark world");
		
		
	}
}
