package org.frcteam3539.BulldogLibrary;

import org.frcteam3539.BulldogLibrary.INIConfiguration.BBConstants;

public class Test extends BBConstants{
    
    public static void main(String[] args)
    {
        Test test = new Test("C:\\Users\\camco\\Desktop\\test.ini",true);
        test.save();
    }

    public Test(String fileName, boolean reloadable) {
        super(fileName, reloadable);
    }
    public static double doub = 1.0;
    public static double t = 2.0;
    public static boolean t2 = true;
    public static boolean t3 = false;
    public static int t4 = 3;
    public static String canName = "Canivore";
}
