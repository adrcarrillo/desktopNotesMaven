package com.arn.desktopnotes;

public class Greeting {

    public static String getGreeting(int x) {
        String greeting = new String();
        if (6<=x&&x<=11) {
            greeting = "\nGood morning!\n";
        } else if (12<=x&&x<=18) {
            greeting = "\nGood afternoon!\n";
        } else if (19<=x&&x<=22) {
            greeting = "\nGood night!\n";
        } else {
            greeting = "\nGo to sleep!\n";
        }
        return greeting;
    }
    
}
