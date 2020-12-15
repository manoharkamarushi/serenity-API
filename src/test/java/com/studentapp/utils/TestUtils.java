package com.studentapp.utils;

import java.util.Random;

public class TestUtils {

	public static String getRandomValue() {
		Random random = new Random();
		int randmInt = random.nextInt(10000);
		return Integer.toString(randmInt);

	}

}
