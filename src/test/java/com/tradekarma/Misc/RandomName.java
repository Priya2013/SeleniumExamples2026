package com.tradekarma.Misc;

import java.util.Random;

import javax.xml.stream.events.Characters;

public class RandomName {
     public static void main(String[]args){
        String characters = "abcdefghijklmnopqrstuvwxyz";
        int count=6;
        Random rand = new Random(); // <-- Added Random instance
        StringBuilder sb= new StringBuilder();
        for(int i=0;i<count;i++){
            int randomIndex=rand.nextInt(characters.length());
           System.out.println("RandomIN : "+randomIndex);
           char randomChar=characters.charAt(randomIndex);
            System.out.println("randomChar : "+randomChar);
        //    int randomIndex = rand.nextInt(characters.length());
        //    char randomChar=characters.charAt(randomIndex).
        //     if(i==0){
        //         sb.append(Character.toUpperCase(randomChar));
        //     }else {
        //         sb.append(randomChar);
        // }
     }
    System.out.println("Generated Random Name: " + sb.toString());
}
}