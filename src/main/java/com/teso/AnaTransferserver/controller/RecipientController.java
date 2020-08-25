package com.teso.AnaTransferserver.controller;

import com.teso.AnaTransferserver.bean.RecipientBean;
import com.teso.AnaTransferserver.bean.ResponseBean;
import com.teso.AnaTransferserver.converter.RecipientConverter;
import com.teso.AnaTransferserver.model.Recipient;
import com.teso.AnaTransferserver.repository.IRecipientRepository;
import com.teso.AnaTransferserver.server.CustomUserDetailsService;
import com.teso.AnaTransferserver.service.IRecipientService;
import com.teso.AnaTransferserver.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recipients")
public class RecipientController {
    private final static Logger logger = LoggerFactory.getLogger(BankController.class);

    @Autowired
    IRecipientRepository recipientRepository;

    @Autowired
    IRecipientService recipientService;

    @Autowired
    RecipientConverter recipientConverter;

    @Autowired
    CustomUserDetailsService userService;

    @GetMapping("/listRecipient")
    public ResponseEntity<ResponseBean> getAllListRecipient(@RequestHeader(value="Accept-Language") String acceptLanguage) {
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            List<RecipientBean> result = recipientService.findAll();
            responseBean.setData(result);
            responseBean.setSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/detailRecipient/{idRecipient}")
    public ResponseEntity<ResponseBean> getRecipientById(@PathVariable Long idRecipient,@RequestHeader(value="Accept-Language") String acceptLanguage) {
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }
            RecipientBean recipient = recipientService.findById(idRecipient);
            responseBean.setData(recipient);
            responseBean.setSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/Recipient")
    public ResponseEntity<ResponseBean> createRecipient(@RequestBody RecipientBean recipientBean,@RequestHeader(value="Accept-Language") String acceptLanguage){
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

//            Optional<Recipient> recipientData = recipientRepository.findById(recipientBean.getRecipientId());
//            Recipient recipientCovert = recipientConverter.convertModel(recipientBean);
//
//            if(recipientData.isPresent()){
//                responseBean.setAccountExist(acceptLanguage);
//                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
//            }

            RecipientBean recipientBeanData = recipientService.findById(recipientBean.getRecipientId());
            if(recipientBeanData != null){
                responseBean.setAccountExist(acceptLanguage);
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            RecipientBean recipient = recipientService.save(recipientBean);

            responseBean.setData(recipient);
            responseBean.setInsertSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);

        }catch (Exception e){
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/addRecipient")
//    public ResponseEntity<ResponseBean> addRecipient(@RequestBody RecipientBean recipientBean){
//        ResponseBean responseBean = new ResponseBean();
//        try {
//            Recipient recipient = recipientConverter.convertModel(recipientBean);
//            recipientRepository.save(recipient);
//
//            responseBean.setData(recipient);
//            responseBean.setSuccess();
//            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
//
//        }catch (Exception e){
//            logger.error(e.getMessage());
//            responseBean.setBadRequest();
//            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
//        }
//
//    }
}
