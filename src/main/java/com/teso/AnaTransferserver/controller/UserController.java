package com.teso.AnaTransferserver.controller;


import com.teso.AnaTransferserver.bean.AddPin;
import com.teso.AnaTransferserver.bean.ResponseBean;
import com.teso.AnaTransferserver.bean.StoreBean;
import com.teso.AnaTransferserver.bean.UserBean;
import com.teso.AnaTransferserver.config.JwtTokenProvider;
import com.teso.AnaTransferserver.converter.UserConverter;
import com.teso.AnaTransferserver.model.*;
import com.teso.AnaTransferserver.repository.IRecipientRepository;
import com.teso.AnaTransferserver.repository.IUserRepository;
import com.teso.AnaTransferserver.server.CustomUserDetailsService;
import com.teso.AnaTransferserver.server.EmailSenderService;
import com.teso.AnaTransferserver.server.fileService.FileServiceImp;
import com.teso.AnaTransferserver.service.IRecipientService;
import com.teso.AnaTransferserver.service.IUserService;
import com.teso.AnaTransferserver.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.security.Principal;

import java.util.*;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    IRecipientRepository recipientRepository;

    @Autowired
    IUserRepository users;

    @Autowired
    IUserService userService;

    @Autowired
    IRecipientService recipientService;

    @Autowired
    UserConverter userConverter;

    @GetMapping("/getString")
    public String getString(){
        return "hellooooooooooooooooooooooooooo";
    }

//    @GetMapping("/listUser")
//    public List<User> getAllUser() {
//        return userRepository.findAll();
//    }

    @GetMapping("/listUser")
    public ResponseEntity<ResponseBean> getAllUser(@RequestHeader(value="Accept-Language") String acceptLanguage) {
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            List<UserBean> result = userService.findAll();

            responseBean.setData(result);
            responseBean.setSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/detailUser/{userId}")
//    public Optional<User> getUserById(@PathVariable Long userId) {
//        return userRepository.findById(userId);
//    }
    @GetMapping("/detailUser/{userId}")
    public ResponseEntity<ResponseBean> getAllUser(@PathVariable Long userId,@RequestHeader(value="Accept-Language") String acceptLanguage) {
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try{
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }
            UserBean user = userService.findById(userId);
            responseBean.setData(user);
            responseBean.setSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
        }catch (Exception e) {
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addPinCode")
    public ResponseEntity<ResponseBean> addPinCode(@RequestBody AddPin data, @RequestHeader(value="Accept-Language") String acceptLanguage) {
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }
            User user = customUserDetailsService.findUserByEmail(data.getEmail());
            user.setPinCode(data.getPin());
            user.setEnable(true);

//            userRepository.save(user);
            UserBean userBean = userService.save(userConverter.convertBean(user));

            responseBean.setData(userBean);
            responseBean.setUpdateSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/editUser")
    public ResponseEntity<ResponseBean> updateUser(@RequestBody UpdateProfile updateProfile, Principal principal,@RequestHeader(value="Accept-Language") String acceptLanguage) {
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            User userCurrent = customUserDetailsService.findUserByEmail(principal.getName());
            if (userCurrent == null) {
                responseBean.setAccountEmpty(acceptLanguage);
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            userCurrent.setAddress(updateProfile.getAddress());
            userCurrent.setPhone(updateProfile.getMobiphone());
            userCurrent.setUserCountry(updateProfile.getUserCountry());

            UserBean userBean = userService.save(userConverter.convertBean(userCurrent));

//            userRepository.save(userCurrent);
            responseBean.setData(userBean);
            responseBean.setUpdateSuccess(acceptLanguage);

            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/editPassword")
    public ResponseEntity<ResponseBean> changePassword(@RequestBody ChangePassword data, Principal principal,@RequestHeader(value="Accept-Language") String acceptLanguage) {
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            boolean checkPassword = Util.checkValiatePassword(data.getNewPassword());
            if(!checkPassword){
                responseBean.setValidatePassword(acceptLanguage);
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }


            User userExist = customUserDetailsService.findUserByEmail(principal.getName());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userExist.getUserEmail(), data.getPassword()));

            userExist.setUserPassword(bCryptPasswordEncoder.encode(data.getNewPassword()));
//            userExist.setUserPassword(data.getNewPassword());
            userRepository.save(userExist);
            System.out.println(userExist);

            responseBean.setData(userExist);
            responseBean.setUpdatePasswordSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
        } catch (AuthenticationException e) {
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
        }

    }

//    @PostMapping("/forgottenPassword/{email}")
//    public ResponseEntity forgottenPassword(@PathVariable("email") String email, HttpServletRequest request, Principal principal) {
//        Map<Object, Object> model = new HashMap<>();
//            User user = customUserDetailsService.findUserByEmail(email);
//            String token = jwtTokenProvider.createToken(user.getUserEmail(), this.users.findByUserEmail(user.getUserEmail()).getRoles());
//            SimpleMailMessage mailMessage = new SimpleMailMessage();
//            mailMessage.setTo(user.getUserEmail());
//            mailMessage.setSubject("Complete Password Reset!");
//            mailMessage.setFrom("lknshopmail@gmail.com");
//            mailMessage.setText("To complete the password reset process, please click here: "
//                    + "http://localhost:4200/users/changePassword?token=" + token); // gửi cho front end sao để 8081
//
//            emailSenderService.sendEmail(mailMessage);
//            model.put("code", "3002");
//            model.put("message", "Check your email for reset the link");
//            return ok(model);
//    }

    @PostMapping("/forgottenPassword/{email}")
    public ResponseEntity<ResponseBean> forgottenPassword(@PathVariable("email") String email, HttpServletRequest request, Principal principal,@RequestHeader(value="Accept-Language") String acceptLanguage) {
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            UserBean userBean = userService.findByUserEmail(email);
            String token = jwtTokenProvider.createToken(userBean.getUserEmail(), this.users.findByUserEmail(userBean.getUserEmail()).getRoles());
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(userBean.getUserEmail());
            mailMessage.setSubject("Complete Password Reset!");
            mailMessage.setFrom("lknshopmail@gmail.com");
            mailMessage.setText("To complete the password reset process, please click here: "
                    + "http://localhost:4200/users/changePassword?token=" + token); // gửi cho front end sao để 8081
            emailSenderService.sendEmail(mailMessage);
            responseBean.setSendEmailSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
        }
    }

    private static String UPLOAD_DIR = "./uploads";

    @RequestMapping(value = "/files/uploadFile", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            String fileName = file.getOriginalFilename();
            String path = request.getServletContext().getRealPath("") + UPLOAD_DIR + File.separator + fileName;
            fileServiceImp.saveFile(file.getInputStream(), path);
            return fileName;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/receipients/{receipientid}")
    public ResponseEntity<ResponseBean> userDailAddReceipient(@PathVariable Long receipientid,Principal principal,@RequestHeader(value="Accept-Language") String acceptLanguage){
        ResponseBean responseBean = new ResponseBean();
        try {
            User user = customUserDetailsService.findUserByEmail(principal.getName());
            if (user == null) {
                responseBean.setBadRequest();
                return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
            }else{
                Optional<Recipient> recipientData = recipientRepository.findById(receipientid);
                List<Recipient> recipients = user.getMyRecipients();
                List<Recipient> data = new ArrayList<>();

                for(Recipient recipient : recipients){
                    data.add(recipient);
                }

                if(recipientData.isPresent()){
                    data.add(recipientData.get());
                    user.setMyRecipients(data);
                    userRepository.save(user);

                    responseBean.setData(user);
                    responseBean.setSuccess(acceptLanguage);

                }
            }
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
        }

    }
}
