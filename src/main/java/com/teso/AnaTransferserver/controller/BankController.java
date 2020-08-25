package com.teso.AnaTransferserver.controller;

import com.teso.AnaTransferserver.bean.BankBean;
import com.teso.AnaTransferserver.bean.MapBean;
import com.teso.AnaTransferserver.bean.ResponseBean;
import com.teso.AnaTransferserver.converter.BankConverter;
import com.teso.AnaTransferserver.model.Bank;
import com.teso.AnaTransferserver.repository.IBankRepository;
import com.teso.AnaTransferserver.service.IBankService;
import com.teso.AnaTransferserver.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/banks")
public class BankController {

    private final static Logger logger = LoggerFactory.getLogger(BankController.class);

    @Autowired
    IBankService bankService;

    @Autowired
    BankConverter bankConverter;

    @GetMapping("/listBank")
    public ResponseEntity<ResponseBean> getAllBank(@RequestHeader(value="Accept-Language") String acceptLanguage) {
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();

        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            List<BankBean> results = bankService.findAll();
            responseBean.setData(results);
            responseBean.setSuccess(acceptLanguage);

            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/detailBank/{bankId}")
    public ResponseEntity<ResponseBean> getBankById(@PathVariable Long bankId,@RequestHeader(value="Accept-Language") String acceptLanguage) {
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            BankBean bank = bankService.findById(bankId);

            responseBean.setData(bank);
            responseBean.setSuccess(acceptLanguage);

            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/deleteBank/{bankId}")
    public ResponseEntity<ResponseBean> deleteBank(@PathVariable Long bankId,@RequestHeader(value="Accept-Language") String acceptLanguage) {
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        MapBean map = new MapBean();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            bankService.deleteById(bankId);
            responseBean.setDeleteSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/Bank")
    public ResponseEntity<ResponseBean> createBank(@RequestBody BankBean bankBean,@RequestHeader(value="Accept-Language") String acceptLanguage){
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            BankBean bankBeanData = bankService.save(bankBean);

            responseBean.setData(bankBeanData);
            responseBean.setInsertSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/editBank")
    public ResponseEntity<ResponseBean> updateBank(@RequestBody BankBean bankBean,@RequestHeader(value="Accept-Language") String acceptLanguage){
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            BankBean bankBeanData = bankService.save(bankBean);

            responseBean.setData(bankBeanData);
            responseBean.setUpdateSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
        }
    }
}
