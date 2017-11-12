package com.tfs.graphics.transformations.noise;

import java.awt.image.WritableRaster;

import com.tfs.graphics.callers.IProcessingInvoker;

public class NoiseInvoker implements IProcessingInvoker {
	private static final INoise DEFAULT_NOISE = new Noise();
	
	private INoise noise;
	private NoiseType noiseType;
	private float noiseIntensity;
	
	
	public NoiseInvoker(INoise noise, NoiseType noiseType, float noiseIntensity) {
		super();
		if (noise == null || noiseType == null || noiseIntensity < 0.0f || noiseIntensity > 1.0f) {
			throw new IllegalArgumentException();
		}
		this.noise = noise;
		this.noiseType = noiseType;
		this.noiseIntensity = noiseIntensity;
	}

	public NoiseInvoker(NoiseType noiseType, float noiseIntensity) {
		this(DEFAULT_NOISE, noiseType, noiseIntensity);
	}

	public NoiseInvoker() {
		this(DEFAULT_NOISE, NoiseType.MONOCHROME, 0.5f);
	}

	@Override
	public WritableRaster process(WritableRaster source) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		return noise.noise(source, noiseType, noiseIntensity);
	}

}
