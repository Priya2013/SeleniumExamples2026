package com.tradekarma.Misc;

public class Palindrom {
    public static void main(String[] args) {
        String name = "RadaR";
        char[] ch = name.toCharArray();
        
        boolean isPalindrome = true;
        
        // Use a single for loop with two variables: i (moving forward) and j (moving backward)
        for (int i = 0, j = ch.length - 1; i < j; i++, j--) {
            System.out.println("Comparing " + ch[i] + " and " + ch[j]);
            
            if (ch[i] != ch[j]) {
                isPalindrome = false;
                break; // Mismatch found, stop checking immediately
            }
        }
        
        if (isPalindrome) {
            System.out.println(name + " is a Palindrome!");
        } else {
            System.out.println(name + " is NOT a Palindrome.");
        }
    }
}