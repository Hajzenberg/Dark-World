package com.tfs.darkworld.loading.tasks;

import java.awt.image.WritableRaster;

import com.tfs.darkworld.loading.IProgressListener;
import com.tfs.darkworld.loading.ITaskListener;
import com.tfs.darkworld.loading.SkyGenerator;
import com.tfs.darkworld.loading.transformations.DarkTransformation;
import com.tfs.darkworld.loading.transformations.LightTransformation;
import com.tfs.darkworld.loading.transformations.ScalingTransformation;
import com.tfs.darkworld.res.CommonRasters;
import com.tfs.graphics.transformations.manager.Transformation;

import rafgfxlib.Util;


public class BackgroundGeneratingTask implements ITask{
	private Transformation lightBTrasnf;
	private Transformation darkBTransf;
	private Transformation scaleTransform;
	private SkyGenerator skyGenerator;
	
	public BackgroundGeneratingTask() {
		
		lightBTrasnf = new LightTransformation();
		
		darkBTransf = new DarkTransformation();
		
		scaleTransform = new ScalingTransformation();
		
		skyGenerator = new SkyGenerator();
		
	}
	

	@Override
	public void doTask(IProgressListener progressListener, ITaskListener taskListener) {
		
		// zbir progresa mora da bude 100!!!!
//		progressListener.updateProgress(100);
		
		taskListener.updateTaskName("Generating sky...");
		WritableRaster bg = skyGenerator.generateSky();
		progressListener.updateProgress(40);

		taskListener.updateTaskName("Scaling sky...");
		bg = scaleTransform.process(bg);
		progressListener.updateProgress(10);
		
		taskListener.updateTaskName("Applying filters...");
		WritableRaster lightSky = lightBTrasnf.process(bg);
		progressListener.updateProgress(20);
		
		WritableRaster darkSky = darkBTransf.process(bg);
		progressListener.updateProgress(20);
		
		taskListener.updateTaskName("Storing resources...");
		CommonRasters.setDarkSky(Util.rasterToImage(darkSky));
		CommonRasters.setLightSky(Util.rasterToImage(lightSky));
		
		progressListener.updateProgress(10);
		
	}
	
}
