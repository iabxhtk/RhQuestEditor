package org.yologn.RhQuestEditor;

public class Util {
    public enum EDITOR_TYPE
    {
        TITLE, NORMAL
    }
    public static byte boolToByte(boolean check) {
        if (check) {
            return 1;
        } else
            return 0;
    }
    public  static byte[] stringToBt(String str, int arrLength) {
        if(str == null){
            str="";
        }
        byte[] byteArray = new byte[arrLength];
        byte[] src = str.getBytes();
        System.arraycopy(src, 0, byteArray, 0, str.length());
        return byteArray;
    }
    public static boolean byteToBool(byte num) {
        return num == 1;
    }
}
