package com.tfs.graphics.transformations.waves;

import java.awt.image.WritableRaster;

import com.tfs.graphics.callers.IProcessingInvoker;

public class WavesInvoker implements IProcessingInvoker {
	public static final IWaves DEFAULT_WAVES_METHOD = new Waves();
	
	private IWaves waves;
	private float size;
	private float power;
	
	
	public WavesInvoker(IWaves waves, float size, float power) {
		super();
		if (waves == null) {
			throw new IllegalArgumentException();
		}
		this.waves = waves;
		this.size = size;
		this.power = power;
	}


	public WavesInvoker(float size, float power) {
		this(DEFAULT_WAVES_METHOD, size, power);
	}

	@Override
	public WritableRaster process(WritableRaster source) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		return waves.wave(source, power, size);
	}

}
