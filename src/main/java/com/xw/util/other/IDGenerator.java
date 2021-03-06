package com.xw.util.other;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IDGenerator {

    private static long tmpID = 0;

    private static boolean tmpIDlocked = false;

    public synchronized static long getId() {
        long ltime = 0;
        while (true)
        {
            if(tmpIDlocked == false)
            {
                tmpIDlocked = true;
                //当前：（年、月、日、时、分、秒、毫秒）*10000
                ltime = Long.valueOf(new SimpleDateFormat("yyMMddhhmmssSSS").format(new Date()).toString()) * 10000;
                if(tmpID < ltime)
                {
                    tmpID = ltime;
                }
                else
                {
                    tmpID = tmpID + 1;
                    ltime = tmpID;
                }
                tmpIDlocked = false;
                return ltime;
            }
        }
    }
    public static void main(String[] args) {
    	System.out.println(getId());
	}
}
