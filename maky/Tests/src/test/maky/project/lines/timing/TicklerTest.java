package test.maky.project.lines.timing;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.maky.util.timing.IUpdateble;
import com.maky.util.timing.Tickler;

public class TicklerTest extends TestCase {

	public void testTickler() {
		int delay = 40;
		int period = 100;
		int number = 10;
		final List counter = new ArrayList();
		counter.add(new Integer(0));
		IUpdateble testListener = new IUpdateble() {
			public void tick() {
				int value = ((Integer) counter.get(0)).intValue();
				counter.clear();
				value++;
				counter.add(new Integer(value));
			}
		};
		Tickler tickler = new Tickler();
		tickler.start(delay, period);
		tickler.addComponent(testListener);
		try {
			Thread.sleep(period * number + delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tickler.stop();
		tickler.removeComponent(testListener);
		int value = ((Integer) counter.get(0)).intValue();
		assertEquals(number, value);
	}
}