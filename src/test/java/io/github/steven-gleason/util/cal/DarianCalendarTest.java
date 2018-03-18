package io.github.steven_gleason.util.cal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DarianCalendarTest
{

	private static final long SOL = 24 * 60 * 60 * 1000L;

	private DarianCalendar testSubject;

	@Before
	public void setUp()
	{
		testSubject = new DarianCalendar();
	}

	@Test
	public void testEpochDateFields()
	{
		testSubject.setTimeInMillis(0L);

		assertEquals(1, testSubject.get(DarianCalendar.ERA));
		assertEquals(0, testSubject.get(DarianCalendar.YEAR));
		assertEquals(DarianCalendar.SAGITTARIUS, testSubject.get(DarianCalendar.MONTH));
		assertEquals(1, testSubject.get(DarianCalendar.WEEK_OF_YEAR));
		assertEquals(1, testSubject.get(DarianCalendar.WEEK_OF_MONTH));
		assertEquals(1, testSubject.get(DarianCalendar.SOL_OF_MONTH));
		assertEquals(1, testSubject.get(DarianCalendar.SOL_OF_YEAR));
		assertEquals(DarianCalendar.SOLIS, testSubject.get(DarianCalendar.SOL_OF_WEEK));
		//assertEquals(1, testSubject.get(DarianCalendar.SOL_OF_WEEK_IN_MONTH));
	}

	@Test
	public void testEpochTimeFields()
	{
		testSubject.setTimeInMillis(0L);

		assertEquals(DarianCalendar.AM, testSubject.get(DarianCalendar.AM_PM));
		assertEquals(0, testSubject.get(DarianCalendar.HOUR));
		assertEquals(0, testSubject.get(DarianCalendar.HOUR_OF_SOL));
		assertEquals(0, testSubject.get(DarianCalendar.MINUTE));
		assertEquals(0, testSubject.get(DarianCalendar.SECOND));
		assertEquals(0, testSubject.get(DarianCalendar.MILLISECOND));
		assertEquals(0, testSubject.get(DarianCalendar.ZONE_OFFSET));
		assertEquals(0, testSubject.get(DarianCalendar.DST_OFFSET));
	}
}
