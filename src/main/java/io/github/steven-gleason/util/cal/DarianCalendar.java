package io.github.steven_gleason.util.cal;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Stack;

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


	private static final long MILLIS_IN_SOL = 1000 * 60 * 60 * 24;

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

	private boolean extendedIntercalation = false;
	private LinkedHashSet<Integer> fieldChangeHistory = new LinkedHashSet<>();

	@Override
	public void add(int field, int amount)
	{
	}

	@Override
	protected void computeFields()
	{

		set(ERA, time < 0 ? 0 : 1);

		int year = calculateYear();

		set(YEAR, year);
		//set(SOL_OF_YEAR, sol);

		// sort out the rest of the dates from Year & Day of Year.

		// set time fields

	}

	@Override
	protected void computeTime()
	{
		if (!isLenient())
		{
			validateFields();
		}
	}

/*optional
	@Override
	public int getActualMaximum(int field)
	{
		int max;
		switch (field)
		{
			case SOL_OF_MONTH:
				max = solsInMonth();
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
	
	@Override
	public void set(int field, int value)
	{
		fieldChangeHistory.remove(field);
		fieldChangeHistory.add(field);
		super.set(field, value);
	}

	public boolean isExtendedIntercalation()
	{
		return extendedIntercalation;
	}

	public void setExtendedIntercalation(boolean extended)
	{
		extendedIntercalation = extended;
	}

	private int solsInMonth()
	{
		return solsInMonth(internalGet(MONTH), internalGet(YEAR));
	}

	int solsInMonth(int month, int year)
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

		if (extendedIntercalation)
		{
			leap = DarianYearCalculator.isExtendedSchemeLeapYear(year);
		}
		else
		{
			leap = DarianYearCalculator.isSimpleSchemeLeapYear(year);
		}
		
		return leap;
	}

	private int calculateYear()
	{
		return calculateYear(time);
	}

	private int calculateYear(long millis)
	{
		int year;

		if (extendedIntercalation)
		{
			year = DarianYearCalculator.calculateExtendedSchemeYear(millis);
		}
		else
		{
			year = DarianYearCalculator.calculateSimpleSchemeYear(millis);
		}

		return year;
	}

	private int maxSolOfWeek()
	{
		int maxSol;

		if (get(WEEK_OF_MONTH) == 4 && solsInMonth() == 27)
		{
			maxSol = VENERIS;
		}
		else
		{
			maxSol = SATURNI;
		}

		return maxSol;
	}

	private int solOfYearFromChangeHistory()
	{
		Stack<Integer> changeStack = new Stack<>();

		// use a stack to get the newest first
		for (Integer field : fieldChangeHistory)
		{
			changeStack.push(field);
		}

		boolean[] newestFields = new boolean[FIELD_COUNT];
		for (Integer field : changeStack)
		{
			newestFields[field] = true;
			Integer sol = solOfYearFromChangeHistory(newestFields);
			if (sol != null)
			{
				return sol;
			}
		}

		// insufficient data
		return 0;
	}

	/**
	 * pre: YEAR is set
	 */
	private Integer solOfYearFromChangeHistory(boolean[] newest)
	{
		Integer sol = null;
		if (newest[MONTH] && newest[SOL_OF_MONTH])
		{
		}
		else if (newest[MONTH] && newest[WEEK_OF_MONTH] && newest[SOL_OF_WEEK])
		{
		}
		else if (newest[MONTH] && newest[SOL_OF_WEEK_IN_MONTH] && newest[SOL_OF_WEEK])
		{
		}
		else if (newest[SOL_OF_YEAR])
		{
			sol = internalGet(SOL_OF_YEAR);
		}
		else if (newest[SOL_OF_WEEK] && newest[WEEK_OF_YEAR])
		{
		}
		return sol;
	}

	/**
	 * Not Lenient
	 * Throw errors for fields out of range
	 */
	private void validateFields()
	{
		boolean valid = true;

		for (int i = 0; i < FIELD_COUNT; ++i)
		{
			if (isSet(i))
			{
				validateField(i);
			}
		}
	}

	private void validateField(int field)
	{
		int val = internalGet(field);
		if (val < getMinimum(field) || val > getMaximum(field))
		{
			throw new IllegalArgumentException();
		}

		// need to consider Month (and leap year) for Sol fields
	}

	/**
	 * special cases for SOL_OF_YEAR
	 * - not leap day on a common year
	 */
	private void validateSolOfYear(int sol, int year)
	{
		if (sol > getLeastMaximum(SOL_OF_YEAR)
				&& !isLeapYear())
		{
			throw new IllegalArgumentException("SOL_OF_YEAR is the Leap Sol, but it is the common year " + year);
		}
	}

	/**
	 * special cases for SOL_OF_MONTH
	 * - not Sol 28 in a 27 Sol Month
	 */
	private void validateSolOfMonth(int sol, int month, int year)
	{
		//if (sol == 28 && 
	}
}
