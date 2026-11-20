package com.tradekarma.Misc;

public class MiniMumValue {
    
public static void main(String[] args) {
        int[] numbers = {45, 12, 89, 3, 23, 78};
        int minNumber=numbers[0];
        for(int i=0;i<=numbers.length-1;i++){
            if(numbers[i]<minNumber){
                minNumber=numbers[i];

            }

        }
        System.out.println(minNumber);

}}
