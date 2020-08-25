package com.teso.AnaTransferserver.controller;

import com.teso.AnaTransferserver.bean.SignUpBean;
import com.teso.AnaTransferserver.bean.MapBean;
import com.teso.AnaTransferserver.bean.ResponseBean;
import com.teso.AnaTransferserver.config.JwtTokenProvider;
import com.teso.AnaTransferserver.converter.UserConverter;
import com.teso.AnaTransferserver.model.Role;
import com.teso.AnaTransferserver.model.User;
import com.teso.AnaTransferserver.repository.IUserRepository;
import com.teso.AnaTransferserver.server.CustomUserDetailsService;
import com.teso.AnaTransferserver.server.SequenceGeneratorService;
import com.teso.AnaTransferserver.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin()
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    IUserRepository users;

    @Autowired
    UserConverter userConverter;

    @Autowired
    private CustomUserDetailsService userService;


    @PostMapping("/login")
    public ResponseEntity<ResponseBean> setNotValidAccount(@RequestBody AuthBody data,@RequestHeader(value="Accept-Language") String acceptLanguage){
        MapBean mapBean = new MapBean();
        ResponseBean responseBean = new ResponseBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try{
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }


            data.setEmail(Util.trim(data.getEmail()));
            data.setPassword(Util.trim(data.getPassword()));

            String username = data.getEmail();
            User user = userService.findUserByEmail(data.getEmail());

            //chưa có tài khoản --> không cần set
            if(user ==null){
                responseBean.setAccountEmpty(acceptLanguage);
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.NOT_FOUND);
            }

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));

            String token = jwtTokenProvider.createToken(username, this.users.findByUserEmail(username).getRoles());

            mapBean.put("email",user.getUserEmail());
            mapBean.put("token",token);
            responseBean.setData(mapBean.getAll());
            responseBean.setLoginSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
        }catch (AuthenticationException e){
            responseBean.setNotValidAccount();
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/login/admin")
    public ResponseEntity<ResponseBean> loginAdmin(@RequestBody AuthBody data,@RequestHeader(value="Accept-Language") String acceptLanguage){
        MapBean mapBean = new MapBean();
        ResponseBean responseBean = new ResponseBean();
        try{
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            data.setEmail(Util.trim(data.getEmail()));
            data.setPassword(Util.trim(data.getPassword()));

            String username = data.getEmail();
            User user = userService.findUserByEmail(data.getEmail());

            //chưa có tài khoản --> không cần set
            if(user ==null){
                responseBean.setAccountEmpty(acceptLanguage);
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.NOT_FOUND);
            }

            List<Role> roleList = new ArrayList<>(user.getRoles());
            if(!roleList.get(0).getRole().equals("ADMIN")){
                responseBean.setAccountIsNotAdmin();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.NOT_FOUND);
            }

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));

            String token = jwtTokenProvider.createToken(username, this.users.findByUserEmail(username).getRoles());

            mapBean.put("email",user.getUserEmail());
            mapBean.put("token",token);
            responseBean.setData(mapBean.getAll());
            responseBean.setLoginSuccess(acceptLanguage);
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
        }catch (AuthenticationException e){
            responseBean.setNotValidAccount();
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping("/register")
    public ResponseEntity<ResponseBean> register(@RequestBody SignUpBean signUpBean,@RequestHeader(value="Accept-Language") String acceptLanguage){
        ResponseBean responseBean = new ResponseBean();
        MapBean map = new MapBean();
        acceptLanguage = acceptLanguage.toUpperCase();
        try {
            boolean checkLanguage = Util.checkLanguage(acceptLanguage);
            if(!checkLanguage){
                responseBean.setLanguageInvalid();
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            ArrayList errors = Util.listsErrorRegister(signUpBean.getEmail(),signUpBean.getPassword(),acceptLanguage);

            if(errors.size()>0){
                responseBean.setData(errors);
                responseBean.setBadRequest();;
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }

            User userExists = userService.findUserByEmail(signUpBean.getEmail());// trung email -> không cho đăng kí

            if(userExists != null){
                responseBean.setAccountExist(acceptLanguage);
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
            }else{
                signUpBean.setId(sequenceGenerator.generateSequence(User.SEQUENCE_NAME));
                User user = userConverter.converterUser(signUpBean);
                user.setUserId(sequenceGenerator.generateSequence(User.SEQUENCE_NAME));
                userService.saveNormalUser(user);
                responseBean.setData(user);
                responseBean.setRegisterSuccess(acceptLanguage);
                return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            responseBean.setBadRequest();
            return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
        }
    }
}
