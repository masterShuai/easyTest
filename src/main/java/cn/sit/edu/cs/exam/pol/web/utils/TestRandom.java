package cn.sit.edu.cs.exam.pol.web.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by lautner on 8/11/15.
 */
public class TestRandom {
    public static Random r = new Random();

    public static String getRandom(){
        long num = Math.abs(r.nextLong() % 10000000000L);
        String s = String.valueOf(num);
        for(int i = 0; i < 10-s.length(); i++){
            s = "0" + s;
        }

        return s;
    }

    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        long chongfucount = 0;
        for(int i = 0; i < 1000000; i++){
            String s = getRandom();
            if(set.contains(s)){
                System.out.println("chongfule");
                chongfucount ++;
            }else{
                set.add(s);
            }

//if(i%100000 == 0){
//    try {
//        Thread.sleep(5000);
//    } catch (InterruptedException e) {
//        e.printStackTrace();
//    }
//}
        }
        System.out.println(chongfucount);
        System.out.println(set.size());
    }
}