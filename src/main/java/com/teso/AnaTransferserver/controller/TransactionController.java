package com.teso.AnaTransferserver.controller;

import com.teso.AnaTransferserver.bean.ResponseBean;
import com.teso.AnaTransferserver.bean.TransactionBean;
import com.teso.AnaTransferserver.converter.TransactionConverter;
import com.teso.AnaTransferserver.model.Transaction;
import com.teso.AnaTransferserver.model.User;
import com.teso.AnaTransferserver.repository.ITransactionRepository;
import com.teso.AnaTransferserver.repository.IUserRepository;
import com.teso.AnaTransferserver.server.transactionService.TransactionServersImp;
import com.teso.AnaTransferserver.service.ITransactionService;
import com.teso.AnaTransferserver.service.IUserService;
import com.teso.AnaTransferserver.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(Transaction.class);

    private TransactionServersImp transactionServers;

    @Autowired
    ITransactionRepository transactionRepository;

    @Autowired
    ITransactionService transactionService;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IUserService userService;

    @Autowired
    TransactionConverter transactionConverter;

    @PatchMapping("/transaction/confirm/{id}")
    public void confirm(@PathVariable("id") Long id) {
        transactionServers.confirm(id);
    }

    @PatchMapping("/transaction/payment/{id}")
    public void payment(@PathVariable("id") Long id) {
        transactionServers.payment(id);
    }

    @PatchMapping("/transaction/complete/{id}")
    public void complete(@PathVariable("id") Long id) {
        transactionServers.complete(id);
    }

    @PatchMapping("/transaction/cancel/{id}")
    public void cancel(@PathVariable("id") Long id) {
        transactionServers.cancel(id);
    }

    //    @GetMapping("/listTransaction")
//    public List<Transaction> getAllTransaction(){
//        return transactionRepository.findAll();
//    }
    @GetMapping("/listTransaction")
    public ResponseEntity<ResponseBean> getAllTransaction(@RequestHeader(value="Accept-Language") String acceptLanguage) {
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            List<TransactionBean> transactions = transactionService.findAll();
            responseBean.setData(transactions);
            responseBean.setSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
        }
    }

    //    @GetMapping("/detailTransaction/{transactionId}")
//    public Optional<Transaction> getTransactionById(@PathVariable Long transactionId){
//        return transactionRepository.findById(transactionId);
//    }
    @GetMapping("/detailTransaction/{transactionId}")
    public ResponseEntity<ResponseBean> getTransactionById(@PathVariable Long transactionId,@RequestHeader(value="Accept-Language") String acceptLanguage) {
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            TransactionBean transaction = transactionService.findById(transactionId);
            responseBean.setData(transaction);
            responseBean.setSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/editTransaction")
    public ResponseEntity<ResponseBean> editTransaction(@RequestBody TransactionBean transactionBean,@RequestHeader(value="Accept-Language") String acceptLanguage) {
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            TransactionBean transaction = transactionService.save(transactionBean);
            responseBean.setData(transaction);
            responseBean.setSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/Transaction")
    public ResponseEntity<ResponseBean> createTransaction(@RequestBody TransactionBean transactionBean,@RequestHeader(value="Accept-Language") String acceptLanguage) {
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }
//
//            Transaction transaction = transactionConverter.convertModel(transactionBean);
//            transactionRepository.insert(transaction);
            TransactionBean transaction = transactionService.save((transactionBean));

            responseBean.setData(transaction);
            responseBean.setSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listTransaction/user")
    public ResponseEntity<ResponseBean> getListTransactionByUser(Principal principal,@RequestHeader(value="Accept-Language") String acceptLanguage) {
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            User user = userRepository.findByUserEmail(principal.getName());
            List<Transaction> transactions = transactionRepository.findByUserSend(user);
            responseBean.setData(transactions);
            responseBean.setSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
        }
    }
}
