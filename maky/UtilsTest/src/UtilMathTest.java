import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.Test;

import com.maky.math.UtilMath;
import com.maky.math.UtilMath.MathSegment;
import com.maky.math.UtilMath.MathPoint;

public class UtilMathTest {

	@Test
	public void test() {
		UtilMath util = new UtilMath();
		MathPoint point1 = util.new MathPoint(1, 0);		
		MathPoint point2 = util.new MathPoint(0, 0);

		assertTrue(point1.distanceTo(point2) == 1f);

		MathPoint point3 = util.new MathPoint(0.5f, 2.5f);
		MathSegment line1 = util.new MathSegment(point1, point2);
		float distance = line1.distanceTo(point3);
		
		assertTrue(2.5f == distance);
	}

}
