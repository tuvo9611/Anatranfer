package com.teso.AnaTransferserver.controller;

import com.teso.AnaTransferserver.bean.MapBean;
import com.teso.AnaTransferserver.bean.PromoteCodeBean;
import com.teso.AnaTransferserver.bean.ResponseBean;
import com.teso.AnaTransferserver.converter.PromoteCodeConverter;
import com.teso.AnaTransferserver.model.PromoteCode;
import com.teso.AnaTransferserver.repository.IPromoteCodeRepository;
import com.teso.AnaTransferserver.service.IPromoteCodeService;
import com.teso.AnaTransferserver.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin
@RequestMapping("/promotes")
@RestController
public class PromoteCodeController {
    private final static Logger logger = LoggerFactory.getLogger(PromoteCodeController.class);

    @Autowired
    PromoteCodeConverter promoteCodeConverter;

    @Autowired
    IPromoteCodeService promoteCodeService;

    @GetMapping("/listPromoteCode")
    public ResponseEntity<ResponseBean> getAllPromoteCode(@RequestHeader(value="Accept-Language") String acceptLanguage) {
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            List<PromoteCodeBean> results = promoteCodeService.findAll();
            responseBean.setData(results);
            responseBean.setSuccess(acceptLanguage);

            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/detailPromoteCode/{promoteCodeId}")
    private ResponseEntity<ResponseBean>getPromoteCodeById(@PathVariable Long promoteCodeId,@RequestHeader(value="Accept-Language") String acceptLanguage){
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            PromoteCodeBean promoteCode = promoteCodeService.findById(promoteCodeId);

            responseBean.setData(promoteCode);
            responseBean.setSuccess(acceptLanguage);

            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deletePromoteCode/{promoteCodeId}")
    public ResponseEntity<ResponseBean> deletePromoteCodeById(@PathVariable Long promoteCodeId,@RequestHeader(value="Accept-Language") String acceptLanguage) {
        ResponseBean responseBean = new ResponseBean();
        MapBean map = new MapBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            promoteCodeService.deleteById(promoteCodeId);
            responseBean.setDeleteSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/PromoteCode")
    private ResponseEntity<ResponseBean> createPromoteCode(@RequestBody PromoteCodeBean promoteCodeBean,@RequestHeader(value="Accept-Language") String acceptLanguage){
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            PromoteCodeBean promoteCode = promoteCodeService.save(promoteCodeBean);

            responseBean.setData(promoteCode);
            responseBean.setInsertSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/editPromoteCode")
    private ResponseEntity<ResponseBean> updatePromoteCode(@RequestBody PromoteCodeBean promoteCodeBean,@RequestHeader(value="Accept-Language") String acceptLanguage){
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            PromoteCodeBean promoteCode = promoteCodeService.save(promoteCodeBean);

            responseBean.setData(promoteCode);
            responseBean.setUpdateSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
        }
    }
}
