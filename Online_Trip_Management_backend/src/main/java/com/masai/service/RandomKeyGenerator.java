package com.masai.service;

import java.util.Random;

/**
 * 
 * @author Aman_Maurya
 * 
 * This class is user to generate random key based on requirement
 */
public class RandomKeyGenerator {
	private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static Random random = new Random();

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
