package com.an;

public class mysplit {
    public static void main(String agrs[]){
       String test="2019 01 01 00  -170  -252 10349   220    30     8 -9999 -9999";
       String a[]=test.split(" ");
       for(int i=0;a.length>i;i++){
           System.out.println(i+" "+a[i]);
       }
    }
}
