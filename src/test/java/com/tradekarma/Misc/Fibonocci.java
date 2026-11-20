package com.tradekarma.Misc;

public class Fibonocci {
      public static void main(String[]args){
        int n = 10; // Number of terms to print
        int t1 = 0, t2 = 1;
        for(int i=0;i<=n;i++){
           int sum=t1+t2;
           System.out.println(sum);
          t1 = t2;
            System.out.println(t1);
            t2 = sum;
              System.out.println(t2);
      }
}
}