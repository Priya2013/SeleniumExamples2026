package com.tradekarma.Misc;
import java.util.Random;
public class RandomPhone {
    public static void main(String[]args){
        int[] numbers={0,1,2,3,4,5,6,7,8,9};
        int PhoneNumber=10;
        Random rand=new Random();
        StringBuilder sb= new StringBuilder();
        for(int i=0;i<PhoneNumber;i++){
            int digit=rand.nextInt(10);
            sb.append(digit);

        }
        String randomNum=sb.toString();
        System.out.println("Random Phone Number: " + randomNum);



        // Random rand= new Random();
        // int PhoneLength=10;
        // StringBuilder sb= new StringBuilder();

        // for(int i=0;i<PhoneLength;i++){
        //  int digit=rand.nextInt(10);
        //  sb.append(digit);
        // }
        //  String randomPhoneNumber = sb.toString();
        // System.out.println("Random Phone Number: " + randomPhoneNumber);
    }
    
}
