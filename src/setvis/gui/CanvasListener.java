/**
 * 
 */
package setvis.gui;

import setvis.SetOutline;
import setvis.shape.AbstractShapeCreator;

/**
 * A canvas listener gets notified each time something changes on the canvas.
 * 
 * @author Joschi <josua.krause@googlemail.com>
 * 
 */
public interface CanvasListener {

	/**
	 * Indicates that one of the generators ({@link AbstractShapeCreator} or
	 * {@link SetOutline}) has changed.
	 */
	static int GENERATORS = 0x01;

	/**
	 * Indicates that the translation of the scene representing the canvas has
	 * changed.
	 */
	static int TRANSLATION = 0x02;

	/**
	 * Indicates that the number or selection of the groups has changed.
	 */
	static int GROUPS = 0x04;

	/**
	 * Indicates that an item has changed. Either it was created, deleted or
	 * moved.
	 */
	static int ITEMS = 0x08;

	/**
	 * Indicates that the current rectangle size has changed.
	 */
	static int RECT_SIZE = 0x10;

	/**
	 * Indicates that all things may have changed.
	 */
	static int ALL = -1;

	/**
	 * Is automatically called when the canvas changes. This method may be
	 * called multiple times for the same event, so be sure to re-check if
	 * something actually has been changed.
	 * 
	 * @param changed
	 *            What parameter has been changed. This is an bitwise-or
	 *            combination of the bit-masks defined in {@link CanvasListener}
	 *            .
	 */
	void canvasChanged(int changed);

}