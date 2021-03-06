/**
 * 
 */
package setvis.shape;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import setvis.SetOutline;

/**
 * Creates direct shapes for the vertices generated by
 * {@link SetOutline#createOutline(Rectangle2D[], Rectangle2D[])}.
 * 
 * @author Joschi <josua.krause@googlemail.com>
 * 
 */
public class PolygonShapeCreator extends AbstractShapeCreator {

	/**
	 * Creates a new {@link PolygonShapeCreator} with the given set outline
	 * creator.
	 * 
	 * @param outline
	 *            The set outline generator.
	 */
	public PolygonShapeCreator(final SetOutline outline) {
		super(outline);
	}

	@Override
	protected Shape convertToShape(final Point2D[] points) {
		final GeneralPath res = new GeneralPath();
		boolean first = true;
		for (final Point2D pos : points) {
			final double x = pos.getX();
			final double y = pos.getY();
			if (first) {
				res.moveTo(x, y);
				first = false;
			} else {
				res.lineTo(x, y);
			}
		}
		if (!first) {
			res.closePath();
		}
		return res;
	}

}
