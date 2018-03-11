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
	public void testEpochFields()
	{
		testSubject.setTimeInMillis(0L);

		assertEquals(1, testSubject.get(DarianCalendar.ERA));
		assertEquals(0, testSubject.get(DarianCalendar.YEAR));
		assertEquals(DarianCalendar.SAGITTARIUS, testSubject.get(DarianCalendar.MONTH));
		//assertEquals(1, testSubject.get(DarianCalendar.SOL_OF_YEAR));
		//assertEquals(1, testSubject.get(DarianCalendar.SOL_OF_MONTH));


	}
}
