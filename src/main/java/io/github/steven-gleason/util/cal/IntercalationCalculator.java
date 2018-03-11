package io.github.steven_gleason.util.cal;

public interface IntercalationCalculator
{

	boolean isLeapYear(int year);

	/**
	 * @return The year in which this moment occurs
	 */
	int calculateYear(long millis);

	/**
	 * @return Number of milliseconds from the epoch to the beginning of the given year
	 */
	long calculateTime(int year);
}
