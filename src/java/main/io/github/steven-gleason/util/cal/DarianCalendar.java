package io.github.steven_gleason.util.cal;

import java.util.Calendar;

public class DarianCalendar extends Calendar
{

	// Fields
	public static final int SOL_OF_MONTH = DAY_OF_MONTH;
	public static final int SOL_OF_YEAR = DAY_OF_YEAR;
	public static final int SOL_OF_WEEK = DAY_OF_WEEK;
	public static final int SOL_OF_WEEK_IN_MONTH = DAY_OF_WEEK_IN_MONTH;
	public static final int HOUR_OF_SOL = HOUR_OF_DAY;

	// Eras


	// Months
	public static final int SAGITTARIUS = 0;
	public static final int DHANUS = 1;
	public static final int CAPRICORNUS = 2;
	public static final int MAKARA = 3;
	public static final int AQUARIUS = 4;
	public static final int KUMBHA = 5;
	public static final int PISCES = 6;
	public static final int MINA = 7;
	public static final int ARIES = 8;
	public static final int MESHA = 9;
	public static final int TAURUS = 10;
	public static final int RISHABHA = 11;
	public static final int GEMINI = 12;
	public static final int MITHUNA = 13;
	public static final int CANCER = 14;
	public static final int KARKA = 15;
	public static final int LEO = 16;
	public static final int SIMHA = 17;
	public static final int VIRGO = 18;
	public static final int KANYA = 19;
	public static final int LIBRA = 20;
	public static final int TULA = 21;
	public static final int SCORPIUS = 22;
	public static final int VRISHIKA = 23;

	// Days of Week (Sols)
	public static final int SOLIS = SUNDAY;
	public static final int LUNEA = MONDAY;
	public static final int MARTIS = TUESDAY;
	public static final int MERCURII = WEDNESDAY;
	public static final int JOVIS = THURSDAY;
	public static final int VENERIS = FRIDAY;
	public static final int SATURNI = SATURDAY;


	private static final int[] maximum = {
		/* ERA */ 1,
		/* YEAR */ Integer.MAX_VALUE,
		/* MONTH */ VRISHIKA ,
		/* WEEK_OF_YEAR */ 96,
		/* WEEK_OF_MONTH */ 4,
		/* SOL_OF_MONTH */ 28,
		/* SOL_OF_YEAR */ 669,
		/* SOL_OF_WEEK */ SATURNI,
		/* SOL_OF_WEEK_IN_MONTH */ 4,
		/* AM_PM */ PM,
		/* HOUR */ 11,
		/* HOUR_OF_SOL */ 23,
		/* MINUTE */ 59,
		/* SECOND */ 59,
		/* MILLISECOND */ 999,
		/* ZONE_OFFSET */ Integer.MAX_VALUE,
		/* DST_OFFSET */ Integer.MAX_VALUE
	};

	private static final int[] leastMax = {
		/* ERA */ 1,
		/* YEAR */ Integer.MAX_VALUE,
		/* MONTH */ VRISHIKA ,
		/* WEEK_OF_YEAR */ 96,
		/* WEEK_OF_MONTH */ 4,
		/* SOL_OF_MONTH */ 27,
		/* SOL_OF_YEAR */ 668,
		/* SOL_OF_WEEK */ VENERIS,
		/* SOL_OF_WEEK_IN_MONTH */ 4,
		/* AM_PM */ PM,
		/* HOUR */ 11,
		/* HOUR_OF_SOL */ 23,
		/* MINUTE */ 59,
		/* SECOND */ 59,
		/* MILLISECOND */ 999,
		/* ZONE_OFFSET */ Integer.MAX_VALUE,
		/* DST_OFFSET */ Integer.MAX_VALUE
	};

	private static final int[] minimum = {
		/* ERA */ 0,
		/* YEAR */ Integer.MIN_VALUE,
		/* MONTH */ SAGITTARIUS,
		/* WEEK_OF_YEAR */ 1,
		/* WEEK_OF_MONTH */ 1,
		/* SOL_OF_MONTH */ 1,
		/* SOL_OF_YEAR */ 1,
		/* SOL_OF_WEEK */ SOLIS,
		/* SOL_OF_WEEK_IN_MONTH */ 1,
		/* AM_PM */ AM,
		/* HOUR */ 0,
		/* HOUR_OF_SOL */ 0,
		/* MINUTE */ 0,
		/* SECOND */ 0,
		/* MILLISECOND */ 0,
		/* ZONE_OFFSET */ Integer.MIN_VALUE,
		/* DST_OFFSET */ Integer.MIN_VALUE
	};

	private boolean extendedIntercalculation = false;

	@Override
	public void add(int field, int amount)
	{
	}

	@Override
	protected void computeFields()
	{
	}

	@Override
	protected void computeTime()
	{
	}

/*optional
	@Override
	public int getActualMaximum(int field)
	{
		int max;
		switch (field)
		{
			case SOL_OF_MONTH:
				max = daysInMonth();
				break;

			case SOL_OF_YEAR:
				max = isLeapYear() ? maximum[YEAR] : leastMax[YEAR];
				break;

			case SOL_OF_WEEK:
				max = maxSolOfWeek();
				break;

			default:
				max = maximum[field];
		}

		return max;
	}
*/

/*optional
	@Override
	public int getActualMinimum(int field)
	{
		return minimum[field];
	}
*/

	@Override
	public int getGreatestMinimum(int field)
	{
		return minimum[field];
	}

	@Override
	public int getLeastMaximum(int field)
	{
		return leastMax[field];
	}

	@Override
	public int getMaximum(int field)
	{
		return maximum[field];
	}

	@Override
	public int getMinimum(int field)
	{
		return minimum[field];
	}

	@Override
	public void roll(int field, boolean up)
	{
		if (up)
		{
			rollUp(field);
		}
		else
		{
			rollDown(field);
		}
		rollDownSubFields(field);
	}

	private void rollUp(int field)
	{
		int currentVal = get(field);
		if (currentVal < getLeastMaximum(field) || currentVal < getActualMaximum(field))
		{
			set(field, currentVal + 1);
		}
		else
		{
			set(field, getActualMinimum(field));
		}
	}

	private void rollDown(int field)
	{
	}

	private void rollDownSubFields(int field)
	{
		if (field == YEAR && get(MONTH) == VRISHIKA && get(SOL_OF_MONTH) == 28 && !isLeapYear(get(YEAR)))
		{
			roll(SOL_OF_MONTH, false);
		}


	}

/*optional - recommended
	@Override
	public void roll(int field, int amount)
	{
	}
*/

	public boolean isExtendedIntercalculation()
	{
		return extendedIntercalculation;
	}

	public void setExtendedIntercalculation(boolean extended)
	{
		extendedIntercalculation = extended;
	}

	private int daysInMonth()
	{
		return daysInMonth(get(MONTH), get(YEAR));
	}

	private int daysInMonth(int month, int year)
	{
		int days;

		if ((month == VRISHIKA && isLeapYear(year))
				|| (month + 1) % 6 != 0)
		{
			days = 28;
		}
		else
		{
			days = 27;
		}

		return days;
	}

	private boolean isLeapYear()
	{
		return isLeapYear(get(YEAR));
	}

	private boolean isLeapYear(int year)
	{
		boolean leap;

		if (extendedIntercalculation && 0 <= year && year <= 10000)
		{
			leap = isExtendedSchemeLeapYear(year);
		}
		else
		{
			leap = year % 500 == 0 || (isSimpleLeapYear(year) && year % 100 != 0);
		}
		
		return leap;
	}

	private boolean isExtendedSchemeLeapYear(int year)
	{
		if (0 <= year && year <= 2000)
		{
			leap = (year % 1000 == 0) || (isSimpleLeapYear(year) && year % 100 != 0);
		}
		else if (2001 <= year && year <= 4800)
		{
			leap = isSimpleLeapYear(year) && year % 150 != 0;
		}
		else if (4801 <= year && year <= 6800)
		{
			leap = isSimpleLeapYear(year) && year % 200 != 0;
		}
		else if (6801 <= year && year <= 8400)
		{
			leap = isSimpleLeapYear(year) && year % 300 != 0;
		}
		else if (8401 <= year && year <= 10000)
		{
			leap = isSimpleLeapYear(year) && year % 600 != 0;
		}
		else
		{
			throw new IllegalArgumentException("Extended Intercalculation is not defined for years before 0 or after 10000. Year is: " + year);
		}

		return leap;
	}

	/**
	 * odd years and multiples of 10
	 **/
	private boolean isSimpleLeapYear(int year)
	{
		return (year % 2 != 0) || (year % 10 == 0);
	}

	private int maxSolOfWeek()
	{
		int maxSol;

		if (get(WEEK_OF_MONTH) == 4 && daysInMonth() == 27)
		{
			maxSol = VENERIS;
		}
		else
		{
			maxSol = SATURNI;
		}

		return maxSol;
	}

}
