package com.pcg.utils;

public class CheckRunDuration {
	public static void String()
	{
		final long start = System.nanoTime();
		///Embed code in here///
		final long end = System.nanoTime();

		System.out.println("Took: " + ((end - start) / 1000000) + "ms");
	}
}
