package com.tradekarma.Misc;

public class Number {
    public static void main(String[]args){
        int[] numbers={10,11,21,3,41,5,61,17,81,19};
        int MaxNumber=numbers[0];
        int firstNumber;
        for(int i=0;i<numbers.length;i++){
            if(numbers[i]>MaxNumber){
              MaxNumber=numbers[i];
              
            }
     

        }
    System.out.println("Max Number is :"+MaxNumber);

    }}