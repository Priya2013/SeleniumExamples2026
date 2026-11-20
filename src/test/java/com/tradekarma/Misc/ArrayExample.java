package com.tradekarma.Misc;

public class ArrayExample {
    public static void main(String[]args){
    String characters = "abcdefghijklmnopqrstuvwxyz";
    if(characters.contains("y")){
           System.out.println("Y found");
    }
    for(int i=0;i<characters.length();i++){
        if(characters.charAt(i)==('g')){
            System.out.print("G found");
            break;
        }
    }
}
}