package io.github.steven_gleason.util.cal;

class DarianYearCalculator
{
	private static final long MILLIS_IN_SOL = 1000 * 60 * 60 * 24;

	private DarianYearCalculator() throws Exception
	{
		throw new Exception("static class DarianYearCalculator");
	}

	private static int calcLeapYearsFrom0To2000()
	{
		int THOUSAND_YEAR_LEAPS = 3;
		int ODD_YEARS = 1000;
		int MULTS_OF_10 = 201;
		int SIMPLE_LEAPS = ODD_YEARS + MULTS_OF_10;
		int MULTS_OF_100 = 21;

		return THOUSAND_YEAR_LEAPS + SIMPLE_LEAPS - MULTS_OF_100;
	}

	private static int calcLeapYearsFrom2001To4800()
	{
		int YEARS = 4800 - 2000;
		int ODD_YEARS = YEARS / 2;
		int MULTS_OF_10 = YEARS / 10;
		int SIMPLE_LEAPS = ODD_YEARS + MULTS_OF_10;
		int MULTS_OF_150 = 4800 / 150 - (2000 / 150);

		return SIMPLE_LEAPS - MULTS_OF_150;
	}

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

	static boolean isExtendedSchemeLeapYear(int year)
	{
		boolean leap;

		if (year < 0 || 10000 < year)
		{
			// Extended Intercalation is not defined for years before 0 or after 10000.
			leap = isSimpleSchemeLeapYear(year);
		}
		else if (0 <= year && year <= 2000)
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
			throw new IllegalArgumentException("No logic defined for year: " + year);
		}

		return leap;
	}

	static boolean isSimpleSchemeLeapYear(int year)
	{
			return year % 500 == 0 || (isSimpleLeapYear(year) && year % 100 != 0);
	}

	/**
	 * odd years and multiples of 10
	 **/
	private static boolean isSimpleLeapYear(int year)
	{
		return (year % 2 != 0) || (year % 10 == 0);
	}

	static int calculateExtendedSchemeYear(long millis)
	{
		return 0;
	}

	static int calculateSimpleSchemeYear(long millis)
	{
		long millisInCommonYear = 668 * MILLIS_IN_SOL;
		long millisInLeapYear = millisInCommonYear + MILLIS_IN_SOL;
		//long millisInAverageYear = 668.5991 * MILLIS_IN_SOL;
		//int approxYear = time / millisInAverageYear;
		//int leapYearsInExtendedScheme = 
		//long millisInExtendedScheme = 10000 * millisInCommonYear + leapYearsInExtendedScheme * MILIS_IN_SOL;

			return 0;
	}

}
