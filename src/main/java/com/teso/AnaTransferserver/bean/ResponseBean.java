package com.teso.AnaTransferserver.bean;


/*
HTTP has a few important verbs.
    POST - Create a new resource
    GET - Read a resource
    PUT - Update an existing resource
    DELETE - Delete a resource
HTTP also defines standard response codes.
    200 - SUCESS
    404 - RESOURCE NOT FOUND
    400 - BAD REQUEST
    201 - CREATED
    401 - UNAUTHORIZED
    415 - UNSUPPORTED TYPE - Representation not supported for the resource
    500 - SERVER ERROR
 */

import com.teso.AnaTransferserver.util.Constant;
import com.teso.AnaTransferserver.util.MessageEn;
import com.teso.AnaTransferserver.util.MessageVi;
import org.springframework.http.HttpStatus;

public class ResponseBean extends ResponseAbstractBean{

    public ResponseBean(){
        super();
    }

    public ResponseBean(String messageKey, String message, String status, Object data) {
       super(messageKey,message,status,data);
    }

    @Override
    public void setMessages(String messageKey,String message){
        this.messageKey = messageKey;
        this.message=message;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
        this.data = data;
    }

    @Override
    public void setData(Object object){
        this.data = object;
    }

    public void setAccountEmpty(String language){
        if(Constant.multipleLanguage[0].equals(language)){
            this.setMessages("msg.accountKhongTonTai", MessageVi.taiKhoanKhongTonTai);
        }else{
            this.setMessages("msg.accountEmpty", MessageEn.accountEmpty);
        }
        this.setStatus(Constant.statusLine[0]);
    }

    public void setAccountExist(String language){
        if(Constant.multipleLanguage[0].equals(language)){
            this.setMessages("msg.accountTonTai", MessageVi.taiKhoanTonTai);
        }else{
            this.setMessages("mgs.exist",MessageEn.accountExist);
        }

        this.setStatus(Constant.statusLine[0]);
    }

    public void setNotValidAccount() {
        this.setMessages("msg.notFound", "Invalid email/password supplied");
        this.setStatus(Constant.statusLine[0]);
    }

    public void setBadRequest() {
        this.setMessages("msg.badRequest", "Bad Request");
        this.setStatus(Constant.statusLine[0]);
    }

    public void setEnterAllRequiredFields() {
        this.setMessages("msg.pleaseEnterAllRequiredFields", "Please enter all required fields");
        this.setStatus(Constant.statusLine[0]);
    }

    public void setSuccess(String language) {
        if(Constant.multipleLanguage[0].equals(language)){
            this.setMessages("msg.doDulieuThanhCong", MessageVi.doDuLieuThanhCong);
        }else{
            this.setMessages("msg.pourSuccess", MessageEn.pourSucess);
        }
        this.setStatus(Constant.statusLine[1]);
    }

    public void setInsertSuccess(String language) {
        if(Constant.multipleLanguage[0].equals(language)){
            this.setMessages("msg.themThanhCong", MessageVi.themThanhCong);
        }else{
            this.setMessages("msg.insertSuccess", MessageEn.insertSuccess);
        }
        this.setStatus(Constant.statusLine[1]);
    }

    public void setUpdateSuccess(String language){
        if(Constant.multipleLanguage[0].equals(language)){
            this.setMessages("msg.updateThanhCong", MessageVi.capNhapThanhCong);
        }else{
            this.setMessages("msg.updateSuccess", MessageEn.updateSuccess);
        }
        this.setStatus(Constant.statusLine[1]);
    }

    public void setDeleteSuccess(String language){
        if(Constant.multipleLanguage[0].equals(language)){
            this.setMessages("msg.xoaThanhCong", MessageVi.xoaThanhCong);
        }else{
            this.setMessages("msg.deleteSuccess", MessageEn.deleteSuccess);
        }
        this.setStatus(Constant.statusLine[1]);
    }

    public void setIsExisting() {
        this.setMessages("msg.isExisting", "It is existing");
        this.setStatus(Constant.statusLine[0]);
    }

    public void setLoginSuccess(String language){
        if(Constant.multipleLanguage[0].equals(language)){
            this.setMessages("msg.dangNhapThanhCong", MessageVi.dangNhapThanhCong);
        }else{
            this.setMessages("msg.loginSuccess", MessageEn.loginSuccess);
        }
        this.setStatus(Constant.statusLine[1]);
    }

    public void setRegisterSuccess(String language){
        if(Constant.multipleLanguage[0].equals(language)){
            this.setMessages("msg.dangKiThanhCong", MessageVi.dangKiThanhCong);
        }else{
            this.setMessages("msg.registerSuccess", MessageEn.registerSuccess);
        }
        this.setStatus(Constant.statusLine[1]);
    }

    public void setValidateGmail(String language){
        if(Constant.multipleLanguage[0].equals(language)){
            this.setMessages("msg.gmailKhongHopLe", MessageVi.kiemTraGmail);
        }else{
            this.setMessages("msg.gmailInvalid", MessageEn.validateGmail);
        }

        this.setStatus(Constant.statusLine[0]);
    }

    public void setValidatePassword(String language){
        if(Constant.multipleLanguage[0].equals(language)){
            this.setMessages("msg.matKhauKhongHopLe", MessageVi.kiemTraMatKhau);
        }else{
            this.setMessages("msg.passwordInvalid", MessageEn.validatePassword);
        }

        this.setMessages("msg.validatePassword", "Minimum of 8 characters including Uppercase, Lowercase and Numbers");
        this.setStatus(Constant.statusLine[0]);
    }

    public void setAccountIsNotAdmin(){
        this.setMessages("msg.checkAccount", "Account is not Account admin");
        this.setStatus(Constant.statusLine[0]);
    }

    public void setLanguageInvalid(){
        this.setMessages("msg.language", "Language is invalid");
        this.setStatus(Constant.statusLine[0]);
    }

    public void setUpdatePasswordSuccess(String language){
        if(Constant.multipleLanguage[0].equals(language)){
            this.setMessages("msg.matKhauHopLe", MessageVi.capNhapMatKhau);
        }else{
            this.setMessages("msg.passwordValid", MessageEn.updatePassword
            );
        }
        this.setStatus(Constant.statusLine[0]);
    }

    public void setSendEmailSuccess(String language){
        if(Constant.multipleLanguage[0].equals(language)){
            this.setMessages("msg.guiEmailThanhCong", MessageVi.guiEmailThanhCong);
        }else{
            this.setMessages("msg.sendEmailSuccess", MessageEn.sendEmailSuccess);
        }
        this.setStatus(Constant.statusLine[0]);
    }
}
