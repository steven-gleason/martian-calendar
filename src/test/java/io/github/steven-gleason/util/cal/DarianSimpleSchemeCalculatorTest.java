package io.github.steven_gleason.util.cal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DarianSimpleSchemeCalculatorTest
{

	private static final long SOL = 24 * 60 * 60 * 1000L;

	private DarianSimpleSchemeCalculator testSubject;

	@Before
	public void setUp()
	{
		testSubject = new DarianSimpleSchemeCalculator();
	}

	//////// Test isLeapYear ////////

	@Test
	public void testCommonYearsOnEvens()
	{
		assertFalse(isSimpleLeap(-504));
		assertFalse(isSimpleLeap(-116));
		assertFalse(isSimpleLeap(-102));
		assertFalse(isSimpleLeap(-12));
		assertFalse(isSimpleLeap(-2));
		assertFalse(isSimpleLeap(2));
		assertFalse(isSimpleLeap(12));
		assertFalse(isSimpleLeap(102));
		assertFalse(isSimpleLeap(116));
		assertFalse(isSimpleLeap(504));
	}

	@Test
	public void testLeapYearsOnOdds()
	{
		assertTrue(isSimpleLeap(-14124343));
		assertTrue(isSimpleLeap(-505));
		assertTrue(isSimpleLeap(-501));
		assertTrue(isSimpleLeap(-499));
		assertTrue(isSimpleLeap(-113));
		assertTrue(isSimpleLeap(-111));
		assertTrue(isSimpleLeap(-109));
		assertTrue(isSimpleLeap(-103));
		assertTrue(isSimpleLeap(-101));
		assertTrue(isSimpleLeap(-99));
		assertTrue(isSimpleLeap(-13));
		assertTrue(isSimpleLeap(-11));
		assertTrue(isSimpleLeap(-9));
		assertTrue(isSimpleLeap(-3));
		assertTrue(isSimpleLeap(-1));
		assertTrue(isSimpleLeap(1));
		assertTrue(isSimpleLeap(3));
		assertTrue(isSimpleLeap(9));
		assertTrue(isSimpleLeap(11));
		assertTrue(isSimpleLeap(13));
		assertTrue(isSimpleLeap(99));
		assertTrue(isSimpleLeap(101));
		assertTrue(isSimpleLeap(103));
		assertTrue(isSimpleLeap(109));
		assertTrue(isSimpleLeap(111));
		assertTrue(isSimpleLeap(113));
		assertTrue(isSimpleLeap(499));
		assertTrue(isSimpleLeap(501));
		assertTrue(isSimpleLeap(505));
		assertTrue(isSimpleLeap(14124343));
	}

	@Test
	public void testLeapYearsEvery10()
	{
		assertTrue(isSimpleLeap(-2130));
		assertTrue(isSimpleLeap(-110));
		assertTrue(isSimpleLeap(-50));
		assertTrue(isSimpleLeap(-10));
		assertTrue(isSimpleLeap(10));
		assertTrue(isSimpleLeap(50));
		assertTrue(isSimpleLeap(110));
		assertTrue(isSimpleLeap(2130));
	}

	@Test
	public void testCommonYearsEvery100()
	{
		assertFalse(isSimpleLeap(-1700));
		assertFalse(isSimpleLeap(-200));
		assertFalse(isSimpleLeap(-100));
		assertFalse(isSimpleLeap(100));
		assertFalse(isSimpleLeap(200));
		assertFalse(isSimpleLeap(1700));
	}

	@Test
	public void testLeapYearsEvery500()
	{
		assertTrue(isSimpleLeap(-30000));
		assertTrue(isSimpleLeap(-1000));
		assertTrue(isSimpleLeap(-500));
		assertTrue(isSimpleLeap(0));
		assertTrue(isSimpleLeap(500));
		assertTrue(isSimpleLeap(1000));
		assertTrue(isSimpleLeap(30000));
	}

	private boolean isSimpleLeap(int year)
	{
		return testSubject.isLeapYear(year);
	}


	//////// Test calculateYear ////////

	@Test
	public void testCalculateYear()
	{
		//assertEquals(-2, simpleYear(-669 * SOL));
		assertEquals(-1, simpleYear(-668 * SOL));
		assertEquals(-1, simpleYear(-1L));
		assertEquals(0, simpleYear(0L)); // beginning of sol 1
		assertEquals(0, simpleYear(600 * SOL)); // bg of sol 601
		assertEquals(0, simpleYear(668 * SOL)); // bg of sol 669
		assertEquals(0, simpleYear(669 * SOL - 1)); // end of sol 669
		assertEquals(1, simpleYear(669 * SOL)); // bg of sol 670
		assertEquals(1, simpleYear(670 * SOL)); // bg of sol 671
		assertEquals(59, simpleYear(40000 * SOL));
		assertEquals(8, simpleYear(500000000000L));
		assertEquals(1846, simpleYear(1234567 * SOL));
	}

	private int simpleYear(Long millis)
	{
		return testSubject.calculateYear(millis);
	}


	//////// Test calculateTime ////////

	@Test
	public void testCalculateTime()
	{
		assertEquals(0, calculateTime(0));
		assertEquals(57801600000L, calculateTime(1));
		assertEquals(669 * SOL, calculateTime(1));
		assertEquals(3408220800000L, calculateTime(59));
	}

	private long calculateTime(int year)
	{
		return testSubject.calculateTime(year);
	}
}
