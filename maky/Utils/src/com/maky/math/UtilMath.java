package com.maky.math;

public class UtilMath {

	public class MathPoint {
		private final float x, y;

		public MathPoint(float x, float y) {
			this.x = x;
			this.y = y;
		}
		
		/**
		 * Scalar production (this, point)
		 * @param point
		 * @return
		 */
		public float dot(MathPoint point) {
			return x * point.x + y * point.y;
		}
		
		public float norm() {
			return (float)Math.sqrt(this.dot(this));
		}
		
		/**
		 * Returns new point = this - point
		 * @return
		 */
		public MathPoint minus(MathPoint point) {
			return new MathPoint(x - point.x, y - point.y);
		}
		
		/**
		 * Distance from this point to parameter point
		 * @param point
		 * @return
		 */
		public float distanceTo(MathPoint point) {
			return this.minus(point).norm();
		}

		/**
		 * Multiply vector on by scalar
		 * @param scalar
		 * @return
		 */
		public MathPoint multiplyBy(float scalar) {
			return new MathPoint(x * scalar, y * scalar);
		}
		
		public MathPoint add(MathPoint point) {
			return new MathPoint(x + point.x, y + point.y);
		}
	}

	public class MathSegment {
		private final MathPoint pointA;
		private final MathPoint pointB;

		public MathSegment(MathPoint pointA, MathPoint pointB) {
			this.pointA = pointA;
			this.pointB = pointB;
		}
		
		public float distanceTo(MathPoint point) {
			MathPoint v = pointB.minus(pointA);
		    MathPoint w = point.minus(pointA);
		    float c1 = w.dot(v);
		    if ( c1 <= 0 )
		        return point.distanceTo(pointA);

		    float c2 = v.dot(v);
		    if ( c2 <= c1 )
		        return point.distanceTo(pointB);

		    float b = c1 / c2;
		    MathPoint Pb = pointA.add(v.multiplyBy(b));
		    return point.distanceTo(Pb);
		}
	}
	
	public class MathCircle {
		private final MathPoint center;
		private final float radius;
		
		public MathCircle(MathPoint centerPoint, float radius) {
			this.center = centerPoint;
			this.radius = radius;
		}
	}
	
	public class MathPolygon {
		private final MathSegment[] segments;
		
		public MathPolygon(MathSegment[] segments) {
			this.segments = segments;
		}
	}
}
