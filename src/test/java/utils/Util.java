package utils;

public class Util {

    public static void waiFor_(int seconds){
        try {
            Thread.sleep(seconds*1000);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
