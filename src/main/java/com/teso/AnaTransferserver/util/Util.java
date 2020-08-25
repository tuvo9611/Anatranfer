package com.teso.AnaTransferserver.util;

import com.teso.AnaTransferserver.bean.ResponseBean;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static String trim(String val){
        if(val != null && !val.trim().equals("")){
            return val.trim();
        }
        return null;
    }

    public static boolean checkValiatePassword(String str){
        Pattern p = Pattern.compile(Constant.validatePassword);

        if(str == null) return false;

        Matcher m = p.matcher(str);

        return m.matches();
    }

    public static boolean checkValiateGmail(String str){
        Pattern p = Pattern.compile(Constant.validateGmail);

        if(str == null) return false;

        Matcher m = p.matcher(str);

        return m.matches();
    }

    public static ArrayList listsErrorRegister(String email, String password,String language){
        ArrayList errors = new ArrayList();

        if(Constant.multipleLanguage[0].equals(language)){
            if(!Util.checkValiateGmail(email)){
                errors.add(MessageVi.kiemTraGmail);
            }
            if(!Util.checkValiatePassword(password)){
                errors.add(MessageVi.kiemTraMatKhau);
            }
        }else {
            if (!Util.checkValiateGmail(email)) {
                errors.add(MessageEn.validateGmail);
            }
            if (!Util.checkValiatePassword(password)) {
                errors.add(MessageEn.validatePassword);
            }
        }
        return errors;
    }

    public static boolean checkLanguage(String acceptLanguage){
        for(String language: Constant.multipleLanguage){
            if (language.equals(acceptLanguage)) {
                return true;
            }
        }
        return false;
    }
}
