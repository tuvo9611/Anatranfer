package com.teso.AnaTransferserver.util;

public class Constant {
    public final static String[] statusLine = {"FAIL","SUCCESS"};
    public final static String[] multipleLanguage = {"VI","EN"};

    public final static String validatePassword = "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=\\S+$).{8,}$";

    public final static String validateGmail = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
}
