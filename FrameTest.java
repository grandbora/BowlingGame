package bowling;

import static org.junit.Assert.*;
import org.junit.Test;

public class FrameTest {

	@Test
	public void testScoreNoThrows() {
		Frame f = new Frame();
		assertEquals(0, f.getScore());
	}

	@Test
	public void testAddOneThrow() {
		Frame f = new Frame();
		f.add(5);
		assertEquals(5, f.getScore());
	}

}