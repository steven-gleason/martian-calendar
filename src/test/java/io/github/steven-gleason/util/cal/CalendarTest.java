package io.github.steven_gleason.util.cal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.TimeZone;

public class CalendarTest
{

	private static final long DAY = 24 * 60 * 60 * 1000L;

	private Calendar testSubject;

	@Before
	public void setUp()
	{
		testSubject = Calendar.getInstance();
		testSubject.setTimeZone(TimeZone.getTimeZone("UTC"));
	}

	@Test
	public void testEpochDateFields()
	{
		testSubject.setTimeInMillis(0L);

		assertEquals(1, testSubject.get(Calendar.ERA));
		assertEquals(1970, testSubject.get(Calendar.YEAR));
		assertEquals(Calendar.JANUARY, testSubject.get(Calendar.MONTH));
		assertEquals(1, testSubject.get(Calendar.WEEK_OF_YEAR));
		assertEquals(1, testSubject.get(Calendar.WEEK_OF_MONTH));
		assertEquals(1, testSubject.get(Calendar.DAY_OF_MONTH));
		assertEquals(1, testSubject.get(Calendar.DAY_OF_YEAR));
		assertEquals(Calendar.THURSDAY, testSubject.get(Calendar.DAY_OF_WEEK));
		assertEquals(1, testSubject.get(DarianCalendar.DAY_OF_WEEK_IN_MONTH));
	}

	@Test
	public void testEpochTimeFields()
	{
		testSubject.setTimeInMillis(0L);

		assertEquals(Calendar.AM, testSubject.get(Calendar.AM_PM));
		assertEquals(0, testSubject.get(Calendar.HOUR));
		assertEquals(0, testSubject.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, testSubject.get(Calendar.MINUTE));
		assertEquals(0, testSubject.get(Calendar.SECOND));
		assertEquals(0, testSubject.get(Calendar.MILLISECOND));
		assertEquals(0, testSubject.get(Calendar.ZONE_OFFSET));
		assertEquals(0, testSubject.get(Calendar.DST_OFFSET));
	}

	@Test
	public void testZoneOffset()
	{
		testSubject.setTimeInMillis(0L);

		testSubject.set(Calendar.ZONE_OFFSET, 20);
		assertEquals(-20, testSubject.getTimeInMillis());
		assertEquals(0, testSubject.get(Calendar.MILLISECOND));
		assertEquals(20, testSubject.get(Calendar.ZONE_OFFSET));
	}
}
