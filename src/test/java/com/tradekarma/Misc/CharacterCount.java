package com.tradekarma.Misc;
import java.util.Map;
import java.util.HashMap;
public class CharacterCount {
  public static void main(String[]args){
    String name = "Priyaranjan";  
    char[]ch=name.toCharArray();
    Map<Character,Integer> map= new HashMap<>();
    for(char c:ch)
        {
            map.put(c,map.getOrDefault(c,0)+1);

    }
    System.out.println(map);
    for(Map.Entry<Character,Integer>entry:map.entrySet()){
      System.out.println(entry.getKey()+" : "+entry.getValue());
    }

}}