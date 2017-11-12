package com.tfs.graphics.transformations.manager;

import java.awt.image.WritableRaster;
import java.util.LinkedList;

import com.tfs.graphics.callers.IProcessingInvoker;

/**
 * Transformation contains multiple TransformationInvokers,
 * and is itself TransformationInvoker.
 * 
 * @author Corax
 *
 */
public class Transformation implements IProcessingInvoker {
	
	/**
	 * List of transformations 
	 */
	private LinkedList<IProcessingInvoker> transformationList;
	
	private ITransformationListener transformationListener;
	
	public Transformation setTransformationListener(ITransformationListener transformationListener) {
		this.transformationListener = transformationListener;
		return this;
	}
	
	public ITransformationListener getTransformationListener() {
		return transformationListener;
	}
	
	public Transformation() {
		transformationList = new LinkedList<>();
	}
	
	/**
	 * Add transformation invoker
	 * @param transformation
	 * @return returns itself
	 */
	public Transformation addTransformation(IProcessingInvoker transformation) {
		if (transformation == null) {
			throw new IllegalArgumentException();
		}
		transformationList.add(transformation);
		return this;
	}
	
	/**
	 * Removes transformation
	 * @param transformationInvoker
	 * @return
	 */
	public Transformation removeTransformation(IProcessingInvoker transformationInvoker) {
		transformationList.remove(transformationInvoker);
		return this;
	}
	
	@Override
	public WritableRaster process(WritableRaster source) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		WritableRaster midRaster = source;
		LinkedList<IProcessingInvoker> safeCopy = new LinkedList<>(transformationList);
		for (IProcessingInvoker transformationInvoker : safeCopy) {
			midRaster = transformationInvoker.process(midRaster);
			if(transformationListener != null) { 
				transformationListener.action(transformationInvoker, midRaster);
			}
		}
		return midRaster;
	}
}
