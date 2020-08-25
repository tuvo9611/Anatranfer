package com.teso.AnaTransferserver.controller;

import com.teso.AnaTransferserver.bean.MapBean;
import com.teso.AnaTransferserver.bean.RateBean;
import com.teso.AnaTransferserver.bean.ResponseBean;
import com.teso.AnaTransferserver.converter.RateConverter;
import com.teso.AnaTransferserver.model.Rate;
import com.teso.AnaTransferserver.repository.IRateRepository;
import com.teso.AnaTransferserver.service.IRateService;
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
@RestController
@RequestMapping("/rates")
public class RateController {

    private static Logger logger = LoggerFactory.getLogger(RateController.class);

    @Autowired
    IRateService rateService;

    @Autowired
    RateConverter rateConverter;

    @GetMapping("/listRate")
    public ResponseEntity<ResponseBean> getAllRate(@RequestHeader(value="Accept-Language") String acceptLanguage) {
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();

        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
            }

            List<RateBean> results = rateService.findAll();
            responseBean.setData(results);
            responseBean.setSuccess(acceptLanguage);

            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/detailRate/{rateId}")
    public ResponseEntity<ResponseBean> getRateById(@PathVariable Long rateId,@RequestHeader(value="Accept-Language") String acceptLanguage) {
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            RateBean rate =rateService.findById(rateId);

            responseBean.setData(rate);
            responseBean.setSuccess(acceptLanguage);

            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteRate/{rateId}")
    public ResponseEntity<ResponseBean> deleteRate(@PathVariable Long rateId,@RequestHeader(value="Accept-Language") String acceptLanguage) {
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        MapBean map = new MapBean();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            rateService.deleteById(rateId);
            responseBean.setDeleteSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/Rate")
    public ResponseEntity<ResponseBean> createRate(@RequestBody RateBean rateBean, @RequestHeader(value="Accept-Language") String acceptLanguage){
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            RateBean rate = rateService.save(rateBean);

            responseBean.setData(rate);
            responseBean.setInsertSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/editRate")
    public ResponseEntity<ResponseBean> updateBank(@RequestBody RateBean rateBean,@RequestHeader(value="Accept-Language") String acceptLanguage){
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            RateBean rate = rateService.save(rateBean);

            responseBean.setData(rate);
            responseBean.setUpdateSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
        }
    }
}
