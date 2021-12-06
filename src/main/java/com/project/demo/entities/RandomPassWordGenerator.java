package com.project.demo.entities;

import java.util.Random;

import org.springframework.stereotype.Component;
@Component("RandomPassword")
public class RandomPassWordGenerator {
	public  String generateRandomPassword(int len) {
		String chars = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%&";
		Random rand = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		return sb.toString();
	}
}