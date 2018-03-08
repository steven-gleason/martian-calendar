package io.github.steven_gleason.util.cal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DarianYearCalculatorTest
{

	@Test
	public void testSimpleSchemeLeapYears()
	{
		assertTrue(isSimpleLeap(0));
		assertTrue(isSimpleLeap(1));
		assertFalse(isSimpleLeap(2));
		assertTrue(isSimpleLeap(3));

		assertTrue(isSimpleLeap(9));
		assertTrue(isSimpleLeap(10));
		assertTrue(isSimpleLeap(11));
		assertFalse(isSimpleLeap(12));
		assertTrue(isSimpleLeap(13));

		assertTrue(isSimpleLeap(99));
		assertFalse(isSimpleLeap(100));
		assertTrue(isSimpleLeap(101));
		assertFalse(isSimpleLeap(102));
		assertTrue(isSimpleLeap(103));

		assertTrue(isSimpleLeap(109));
		assertTrue(isSimpleLeap(110));
		assertTrue(isSimpleLeap(111));
		assertFalse(isSimpleLeap(112));
		assertTrue(isSimpleLeap(113));

		assertTrue(isSimpleLeap(499));
		assertTrue(isSimpleLeap(500));
		assertTrue(isSimpleLeap(501));
		assertFalse(isSimpleLeap(502));
	}

	@Test
	public void testNegativeSimpleSchemeLeapYears()
	{
		assertTrue(isSimpleLeap(-1));
		assertFalse(isSimpleLeap(-2));
		assertTrue(isSimpleLeap(-3));

		assertTrue(isSimpleLeap(-9));
		assertTrue(isSimpleLeap(-10));
		assertTrue(isSimpleLeap(-11));
		assertFalse(isSimpleLeap(-12));
		assertTrue(isSimpleLeap(-13));

		assertTrue(isSimpleLeap(-99));
		assertFalse(isSimpleLeap(-100));
		assertTrue(isSimpleLeap(-101));
		assertFalse(isSimpleLeap(-102));
		assertTrue(isSimpleLeap(-103));

		assertTrue(isSimpleLeap(-109));
		assertTrue(isSimpleLeap(-110));
		assertTrue(isSimpleLeap(-111));
		assertFalse(isSimpleLeap(-112));
		assertTrue(isSimpleLeap(-113));

		assertTrue(isSimpleLeap(-499));
		assertTrue(isSimpleLeap(-500));
		assertTrue(isSimpleLeap(-501));
		assertFalse(isSimpleLeap(-502));
	}

	private boolean isSimpleLeap(int year)
	{
		return DarianYearCalculator.isSimpleSchemeLeapYear(year);
	}
}
