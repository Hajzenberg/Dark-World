package com.tfs.graphics.callers;

import java.awt.image.WritableRaster;

/**
 * 
 * Interface that represent invoker of transformation algorithms
 * 
 * @author Corax
 *
 */
public interface IProcessingInvoker {
	/**
	 * This method do transformation on copy of forwarded raster
	 * @param source
	 * @return returns transformed copy of raster
	 */
	public WritableRaster process(WritableRaster source);
}
