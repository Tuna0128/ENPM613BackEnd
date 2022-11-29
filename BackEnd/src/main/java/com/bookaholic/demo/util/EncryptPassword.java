package com.bookaholic.demo.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class EncryptPassword {
	
	public static String hashPassword(String password, String salt) {

        String hashedPassword = null;
        byte[] salts = salt.getBytes(StandardCharsets.UTF_8);
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salts);
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }

    public static String generateSalt() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salts = new byte[16];
        random.nextBytes(salts);
        String salt = new String(salts, StandardCharsets.UTF_8);
        return salt;
    }

//    public static void main(String[] args) throws NoSuchAlgorithmException {
//
//        // same salt should be passed
//    	  String password = "123456";
//        String salt = generateSalt();
//        System.out.println("salt: " + salt);
//        String hashedPassword = hashPassword(password, salt);
//        String hashedPassword2 = hashPassword(password, salt);
//        System.out.println("hashedPassword: " + hashedPassword);
//        System.out.println("hashedPassword2: " + hashedPassword2);
    
//        String password1 = getSecurePassword("Password", salt);
//        String password2 = getSecurePassword("Password", salt);
//        System.out.println(" Password 1 -> " + password1);
//        System.out.println(" Password 2 -> " + password2);
//        if (password1.equals(password2)) {
//            System.out.println("passwords are equal");
//        }
//    }


}
