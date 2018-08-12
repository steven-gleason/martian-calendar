package io.github.steven_gleason.util.cal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class CalendarTest extends GregorianCalendar
{

	private static final long DAY = 24 * 60 * 60 * 1000L;

	private Calendar testSubject;

	@Before
	public void setUp()
	{
		testSubject = getInstance();
		testSubject.setTimeZone(TimeZone.getTimeZone("UTC"));
	}

	@Test
	public void testEpochDateFields()
	{
		testSubject.setTimeInMillis(0L);

		assertEquals(AD, testSubject.get(ERA));
		assertEquals(1970, testSubject.get(YEAR));
		assertEquals(JANUARY, testSubject.get(MONTH));
		assertEquals(1, testSubject.get(WEEK_OF_YEAR));
		assertEquals(1, testSubject.get(WEEK_OF_MONTH));
		assertEquals(1, testSubject.get(DAY_OF_MONTH));
		assertEquals(1, testSubject.get(DAY_OF_YEAR));
		assertEquals(THURSDAY, testSubject.get(DAY_OF_WEEK));
		assertEquals(1, testSubject.get(DAY_OF_WEEK_IN_MONTH));
	}

	@Test
	public void testEpochTimeFields()
	{
		testSubject.setTimeInMillis(0L);

		assertEquals(AM, testSubject.get(AM_PM));
		assertEquals(0, testSubject.get(HOUR));
		assertEquals(0, testSubject.get(HOUR_OF_DAY));
		assertEquals(0, testSubject.get(MINUTE));
		assertEquals(0, testSubject.get(SECOND));
		assertEquals(0, testSubject.get(MILLISECOND));
		assertEquals(0, testSubject.get(ZONE_OFFSET));
		assertEquals(0, testSubject.get(DST_OFFSET));
	}

	@Test
	public void testZoneOffset()
	{
		testSubject.setTimeInMillis(0L);
		testSubject.set(ZONE_OFFSET, 20);

		assertEquals(-20, testSubject.getTimeInMillis());
		assertEquals(0, testSubject.get(MILLISECOND));
		assertEquals(20, testSubject.get(ZONE_OFFSET));
		assertEquals(1, testSubject.get(ERA));
	}

	@Test
	public void testNegativeTime()
	{
		testSubject.setTimeInMillis(-1L);

		assertEquals(AD, testSubject.get(ERA));
		assertEquals(1969, testSubject.get(YEAR));
		assertEquals(DECEMBER, testSubject.get(MONTH));
		assertEquals(1, testSubject.get(WEEK_OF_YEAR)); /* Same week */
		assertEquals(5, testSubject.get(WEEK_OF_MONTH));
		assertEquals(31, testSubject.get(DAY_OF_MONTH));
		assertEquals(365, testSubject.get(DAY_OF_YEAR));
		assertEquals(WEDNESDAY, testSubject.get(DAY_OF_WEEK));
		assertEquals(5, testSubject.get(DAY_OF_WEEK_IN_MONTH));

		assertEquals(PM, testSubject.get(AM_PM));
		assertEquals(11, testSubject.get(HOUR));
		assertEquals(23, testSubject.get(HOUR_OF_DAY));
		assertEquals(59, testSubject.get(MINUTE));
		assertEquals(59, testSubject.get(SECOND));
		assertEquals(999, testSubject.get(MILLISECOND));
		assertEquals(0, testSubject.get(ZONE_OFFSET));
		assertEquals(0, testSubject.get(DST_OFFSET));
	}

	@Test
	public void testNegativeYear()
	{
		testSubject.setTimeInMillis(0L);
		testSubject.set(YEAR, -1);

		// -1 AD becomes 2 BC
		assertEquals(BC, testSubject.get(ERA));
		assertEquals(2, testSubject.get(YEAR));
		assertEquals(JANUARY, testSubject.get(MONTH));
		assertEquals(1, testSubject.get(WEEK_OF_YEAR));
		assertEquals(1, testSubject.get(WEEK_OF_MONTH));
		assertEquals(1, testSubject.get(DAY_OF_MONTH));
		assertEquals(1, testSubject.get(DAY_OF_YEAR));
		assertEquals(WEDNESDAY, testSubject.get(DAY_OF_WEEK)); // just is
		assertEquals(1, testSubject.get(DAY_OF_WEEK_IN_MONTH));

		assertEquals(AM, testSubject.get(AM_PM));
		assertEquals(0, testSubject.get(HOUR));
		assertEquals(0, testSubject.get(HOUR_OF_DAY));
		assertEquals(0, testSubject.get(MINUTE));
		assertEquals(0, testSubject.get(SECOND));
		assertEquals(0, testSubject.get(MILLISECOND));
		assertEquals(0, testSubject.get(ZONE_OFFSET));
		assertEquals(0, testSubject.get(DST_OFFSET));
	}

	@Test
	public void testSetYearFromBC()
	{
		testSubject.set(YEAR, -1);
		assertEquals(BC, testSubject.get(ERA));
		testSubject.set(YEAR, 1);

		assertEquals(BC, testSubject.get(ERA));
		assertEquals(1, testSubject.get(YEAR));
	}

	@Test
	public void testSetNegativeYearFromBC()
	{
		testSubject.set(YEAR, -1);
		assertEquals(BC, testSubject.get(ERA));
		testSubject.set(YEAR, -1);

		assertEquals(AD, testSubject.get(ERA));
		assertEquals(2, testSubject.get(YEAR));
		// -1 BC becomes 2 AD
	}

	@Test
	public void testSetYMD()
	{
		testSubject.set(2018, AUGUST, 11);

		assertEquals(AD, testSubject.get(ERA));
		assertEquals(2018, testSubject.get(YEAR));
		assertEquals(AUGUST, testSubject.get(MONTH));
		assertEquals(32, testSubject.get(WEEK_OF_YEAR));
		assertEquals(2, testSubject.get(WEEK_OF_MONTH));
		assertEquals(11, testSubject.get(DAY_OF_MONTH));
		assertEquals(223, testSubject.get(DAY_OF_YEAR));
		assertEquals(SATURDAY, testSubject.get(DAY_OF_WEEK));
		assertEquals(2, testSubject.get(DAY_OF_WEEK_IN_MONTH));
	}

	@Test
	public void testFieldResolution_Y_DoM_DoY_M()
	{
		testSubject.set(YEAR, 2018);
		testSubject.set(DAY_OF_MONTH, 11);
		testSubject.set(DAY_OF_YEAR, 220); // the 8th of August
		testSubject.set(MONTH, AUGUST);

		assertEquals(AD, testSubject.get(ERA));
		assertEquals(2018, testSubject.get(YEAR));
		assertEquals(AUGUST, testSubject.get(MONTH));
		assertEquals(32, testSubject.get(WEEK_OF_YEAR));
		assertEquals(2, testSubject.get(WEEK_OF_MONTH));
		assertEquals(8, testSubject.get(DAY_OF_MONTH));
		assertEquals(220, testSubject.get(DAY_OF_YEAR));
		assertEquals(WEDNESDAY, testSubject.get(DAY_OF_WEEK));
		assertEquals(2, testSubject.get(DAY_OF_WEEK_IN_MONTH));
		// Although YEAR + MONTH + DAY_OF_MONTH were set,
		// YEAR + DAY_OF_YEAR was set more recently that DAY_OF_MONTH
	}

	@Test
	public void testFieldResolution_Y_DoY_DoM_M()
	{
		testSubject.set(YEAR, 2018);
		testSubject.set(DAY_OF_YEAR, 220); // the 8th of August
		testSubject.set(DAY_OF_MONTH, 11);
		testSubject.set(MONTH, AUGUST);

		assertEquals(AD, testSubject.get(ERA));
		assertEquals(2018, testSubject.get(YEAR));
		assertEquals(AUGUST, testSubject.get(MONTH));
		assertEquals(32, testSubject.get(WEEK_OF_YEAR));
		assertEquals(2, testSubject.get(WEEK_OF_MONTH));
		assertEquals(11, testSubject.get(DAY_OF_MONTH));
		assertEquals(223, testSubject.get(DAY_OF_YEAR));
		assertEquals(SATURDAY, testSubject.get(DAY_OF_WEEK));
		assertEquals(2, testSubject.get(DAY_OF_WEEK_IN_MONTH));
		// YEAR + MONTH + DAY_OF_MONTH were all set more recently than
		// YEAR + DAY_OF_YEAR
	}

	@Test
	public void testFieldResolution_HoD()
	{
		testSubject.set(HOUR_OF_DAY, 14);

		assertEquals(PM, testSubject.get(AM_PM));
		assertEquals(2, testSubject.get(HOUR));
		assertEquals(14, testSubject.get(HOUR_OF_DAY));
	}

	@Test
	public void testFieldResolution_HoD_AMPM()
	{
		testSubject.set(HOUR_OF_DAY, 14);
		testSubject.set(AM_PM, AM);

		// This doesn't seem to follow spec
		//assertEquals(PM, testSubject.get(AM_PM));
		//assertEquals(2, testSubject.get(HOUR));
		//assertEquals(14, testSubject.get(HOUR_OF_DAY));
		assertEquals(AM, testSubject.get(AM_PM));
		assertEquals(0, testSubject.get(HOUR));
		assertEquals(0, testSubject.get(HOUR_OF_DAY));
	}

	@Test
	public void testFieldResolutionAfterGet_HoD_AMPM()
	{
		testSubject.setTimeInMillis(0L);
		assertEquals(0, testSubject.get(HOUR));
		testSubject.set(HOUR_OF_DAY, 14);
		testSubject.set(AM_PM, AM);

		// This doesn't seem to follow spec
		//assertEquals(PM, testSubject.get(AM_PM));
		//assertEquals(2, testSubject.get(HOUR));
		//assertEquals(14, testSubject.get(HOUR_OF_DAY));
		assertEquals(AM, testSubject.get(AM_PM));
		assertEquals(0, testSubject.get(HOUR));
		assertEquals(0, testSubject.get(HOUR_OF_DAY));
	}

	@Test
	public void testFieldResolution_HoD_get_AMPM()
	{
		testSubject.set(HOUR_OF_DAY, 14);
		assertEquals(2, testSubject.get(HOUR));
		testSubject.set(AM_PM, AM);

		assertEquals(AM, testSubject.get(AM_PM));
		assertEquals(2, testSubject.get(HOUR));
		assertEquals(2, testSubject.get(HOUR_OF_DAY));
	}

	@Test
	public void testFieldResolution_HoD_H()
	{
		testSubject.set(HOUR_OF_DAY, 14);
		testSubject.set(HOUR, 5);

		// This doesn't seem to follow spec
		//assertEquals(PM, testSubject.get(AM_PM));
		//assertEquals(2, testSubject.get(HOUR));
		//assertEquals(14, testSubject.get(HOUR_OF_DAY));
		assertEquals(AM, testSubject.get(AM_PM));
		assertEquals(5, testSubject.get(HOUR));
		assertEquals(5, testSubject.get(HOUR_OF_DAY));
	}

	@Test
	public void testFieldResolution_AMPM_HoD_H()
	{
		testSubject.set(AM_PM, AM);
		testSubject.set(HOUR_OF_DAY, 14);
		testSubject.set(HOUR, 5);

		// This doesn't seem to follow spec
		//assertEquals(PM, testSubject.get(AM_PM));
		//assertEquals(2, testSubject.get(HOUR));
		//assertEquals(14, testSubject.get(HOUR_OF_DAY));
		assertEquals(AM, testSubject.get(AM_PM));
		assertEquals(5, testSubject.get(HOUR));
		assertEquals(5, testSubject.get(HOUR_OF_DAY));
	}

	@Test
	public void testFieldResolution_H_AMPM()
	{
		testSubject.set(HOUR, 7);
		testSubject.set(AM_PM, PM);

		assertEquals(PM, testSubject.get(AM_PM));
		assertEquals(7, testSubject.get(HOUR));
		assertEquals(19, testSubject.get(HOUR_OF_DAY));
	}

	@Test
	public void testFieldResolution_H_AMPM_HoD()
	{
		testSubject.set(HOUR, 7);
		testSubject.set(AM_PM, PM);
		testSubject.set(HOUR_OF_DAY, 3);

		assertEquals(AM, testSubject.get(AM_PM));
		assertEquals(3, testSubject.get(HOUR));
		assertEquals(3, testSubject.get(HOUR_OF_DAY));
	}

}
