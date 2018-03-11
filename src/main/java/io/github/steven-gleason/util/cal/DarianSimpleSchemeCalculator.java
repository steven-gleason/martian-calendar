package io.github.steven_gleason.util.cal;

class DarianSimpleSchemeCalculator implements IntercalationCalculator
{
	private static final long MILLIS_IN_SOL = 1000L * 60 * 60 * 24;

	@Override
	public boolean isLeapYear(int year)
	{
			return year % 500 == 0 || (isSimpleLeapYear(year) && year % 100 != 0);
	}

	@Override
	public int calculateYear(long millis)
	{
		// TODO: smarter

		// TODO if (millis < 0)

		int year = 0;
		while (calculateTime(year) < millis)
		{
			++year;
		}

		while (calculateTime(year) > millis)
		{
			--year;
		}

		return year;
	}

	/**
	 * Number of martian milliseconds from the epoch to the beginning of the given year
	 */
	@Override
	public long calculateTime(int year)
	{
		long milliSum;

		if (year < 0)
		{
			milliSum = -calculatePositiveTime(-year);
		}
		else
		{
			milliSum = calculatePositiveTime(year);
		}

		return milliSum;
	}

	private long calculatePositiveTime(int year)
	{
		// TODO: smarter
		long millisInCommonYear = 668 * MILLIS_IN_SOL;
		long millisInLeapYear = millisInCommonYear + MILLIS_IN_SOL;
		long milliSum = 0;
		int yearCount = 0;

		while (yearCount++ < year)
		{
			if (isLeapYear(yearCount))
			{
				milliSum += millisInLeapYear;
			}
			else
			{
				milliSum += millisInCommonYear;
			}
		}

		return milliSum;
	}

	/**
	 * odd years and multiples of 10
	 **/
	private static boolean isSimpleLeapYear(int year)
	{
		return (year % 2 != 0) || (year % 10 == 0);
	}

/*
	private static int calcLeapYears(int startYear, int endYear, int nonLeapMultiples)
	{
		int YEARS = endYear - startYear + 1;
		int ODD_YEARS = YEARS / 2;
		if (startYear % 2 == 1 && endYear % 2 == 1)
		{
			ODD_YEARS++;
		}

		int MULTS_OF_10 = YEARS / 10;
		int SIMPLE_LEAPS = ODD_YEARS + MULTS_OF_10;
		int MULTS_OF_150 = 4800 / 150 - (2000 / 150);

		return SIMPLE_LEAPS - MULTS_OF_150;
	}
*/

}
