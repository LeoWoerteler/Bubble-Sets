package setvis;

import java.awt.geom.Point2D;

/**
 * Provides some vector helper functions. The words point and vector are used
 * interchangeable.
 * 
 * @author Joschi <josua.krause@googlemail.com>
 * 
 */
public final class VecUtil {

	private VecUtil() {
		// no constructor
	}

	/**
	 * Calculates the relative orientation of two vectors.
	 * 
	 * @param from
	 *            The starting point of the vector.
	 * @param to
	 *            The vector the orientation is calculated for.
	 * @param rel
	 *            The vector relative to the other.
	 * @return {@code > 0} if {@code rel} is left of {@code from -> to} and
	 *         {@code < 0} if {@code rel} is right of {@code from -> to}.
	 */
	public static int relTo(final Point2D from, final Point2D to,
			final Point2D rel) {
		return (int) Math.signum((to.getX() - from.getX())
				* (from.getY() - rel.getY()) - (rel.getX() - from.getX())
				* (from.getY() - to.getY()));
	}

	/**
	 * @param from
	 *            The starting point.
	 * @param to
	 *            The end point.
	 * @return Whether the vector {@code from -> to} is the null vector.
	 */
	public static boolean isNull(final Point2D from, final Point2D to) {
		return from.getX() == to.getX() && from.getY() == to.getY();
	}

	/**
	 * Adds two points.
	 * 
	 * @param a
	 *            The first point.
	 * @param b
	 *            The second point.
	 * @return The resulting point.
	 */
	public static Point2D addVec(final Point2D a, final Point2D b) {
		return new Point2D.Double(a.getX() + b.getX(), a.getY() + b.getY());
	}

	/**
	 * Multiplies a point with a scalar.
	 * 
	 * @param a
	 *            The point.
	 * @param scalar
	 *            The scalar.
	 * @return the scaled version.
	 */
	public static Point2D mulVec(final Point2D a, final double scalar) {
		return new Point2D.Double(a.getX() * scalar, a.getY() * scalar);
	}

	/**
	 * Subtracts {@code b} from {@code a}.
	 * 
	 * @param a
	 *            The minuend.
	 * @param b
	 *            The subtrahend.
	 * @return The difference.
	 */
	public static Point2D subVec(final Point2D a, final Point2D b) {
		return new Point2D.Double(a.getX() - b.getX(), a.getY() - b.getY());
	}

	/**
	 * Calculates the dot product of two vectors.
	 * 
	 * @param a
	 *            The first vector.
	 * @param b
	 *            The second vector.
	 * @return The dot product.
	 */
	public static double dot(final Point2D a, final Point2D b) {
		return a.getX() * b.getX() + a.getY() * b.getY();
	}

	/**
	 * Calculates the normalized dot product of two vectors.
	 * {@code a * b / (|a| * |b|)}
	 * 
	 * @param a
	 *            The first vector.
	 * @param b
	 *            The second vector.
	 * @return The normalized dot product.
	 */
	public static double dotNorm(final Point2D a, final Point2D b) {
		return dot(a, b) / (vecLength(a) * vecLength(b));
	}

	/**
	 * Generates a vector that forms an angle of {@code 90} degrees with the
	 * given vector.
	 * 
	 * @param a
	 *            The given vector.
	 * @return The orthogonal vector of the left side.
	 */
	public static Point2D getOrthoLeft(final Point2D a) {
		return new Point2D.Double(-a.getY(), a.getX());
	}

	/**
	 * Generates a vector that forms an angle of {@code -90} degrees with the
	 * given vector.
	 * 
	 * @param a
	 *            The given vector.
	 * @return The orthogonal vector of the right side.
	 */
	public static Point2D getOrthoRight(final Point2D a) {
		return new Point2D.Double(a.getY(), -a.getX());
	}

	/**
	 * Generates a vector pointing in the opposite direction.
	 * 
	 * @param v
	 *            The vector.
	 * @return The inverse vector.
	 */
	public static Point2D invVec(final Point2D v) {
		return mulVec(v, -1.0);
	}

	/**
	 * Calculates the length of a vector. This operation is relatively
	 * expensive. To compare two distances, use {@link #vecLengthSqr(Point2D)}.
	 * 
	 * @param v
	 *            The vector.
	 * @return The length of the vector.
	 */
	public static double vecLength(final Point2D v) {
		return Math.sqrt(vecLengthSqr(v));
	}

	/**
	 * Normalizes a vector, such that its length is {@code 1.0}.
	 * 
	 * @param v
	 *            The vector.
	 * @return A vector with the length {@code 1.0}.
	 */
	public static Point2D normVec(final Point2D v) {
		return mulVec(v, 1.0 / vecLength(v));
	}

	/**
	 * Calculates the squared length of a vector. This method is much cheaper
	 * than {@link #vecLength(Point2D)}.
	 * 
	 * @param v
	 *            The vector.
	 * @return The squared length.
	 */
	public static double vecLengthSqr(final Point2D v) {
		return dot(v, v);
	}

	/**
	 * Calculates the vector that lies between the given vectors
	 * {@code from - a} and {@code from b} with the given length.
	 * 
	 * @param from
	 *            The starting point of both vectors.
	 * @param a
	 *            The first end point.
	 * @param b
	 *            The second end point.
	 * @param len
	 *            The length of the result.
	 * @return The vector in the middle of the two.
	 */
	public static Point2D middleVec(final Point2D from, final Point2D a,
			final Point2D b, final double len) {
		return addVec(
				mulVec(normVec(middleVec(subVec(a, from), subVec(b, from))),
						len), from);
	}

	/**
	 * Calculates the vector that lies between the given vectors
	 * {@code from - a} and {@code from b}.
	 * 
	 * @param from
	 *            The starting point of both vectors.
	 * @param a
	 *            The first end point.
	 * @param b
	 *            The second end point.
	 * @return The vector in the middle of the two.
	 */
	public static Point2D middleVec(final Point2D from, final Point2D a,
			final Point2D b) {
		return addVec(middleVec(subVec(a, from), subVec(b, from)), from);
	}

	/**
	 * Calculates the vector in the middle of the two given vectors.
	 * 
	 * @param a
	 *            The first vector.
	 * @param b
	 *            The second vector.
	 * @return The middle vector.
	 */
	public static Point2D middleVec(final Point2D a, final Point2D b) {
		return mulVec(addVec(a, b), 0.5);
	}

}
