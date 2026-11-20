package com.tradekarma.Misc;
import java.util.Set;
import java.util.HashSet;
public class DuplicateValue {
    

    public static void main(String[]args){
    String characters = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
    Set<Character> set= new HashSet<>();
    int count=0;
    char []ch= characters.toCharArray();
    for(char c:ch){
        if(!set.add(c)){
            count++;
         System.out.println(c+":"+count);
        }
    }

    
    }}
