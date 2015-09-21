package com.mobilesaaj.pokergame.Utils;

/**
 * Created by neeraj.singh on 06/09/15.
 */
public class StringUtils {
    public static boolean isNullOrEmpty(String string){
        return string==null || string.equals("") || string.equalsIgnoreCase("null") || string.isEmpty() || string.length()==0 ;
    }
}
