package com.tradekarma.Misc;

import java.util.Random;

public class RandomThreeDigit {
    public static void main(String[] args) {
        Random rand = new Random();
        
        // rand.nextInt(900) generates numbers from 0 to 899
        // Adding + 100 shifts the range to 100 to 999
        int threeDigitNum = rand.nextInt(900)+100 ;
        
        System.out.println("Random 3-digit number: " + threeDigitNum);
    }
}