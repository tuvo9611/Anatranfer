package com.teso.AnaTransferserver.converter;

import com.teso.AnaTransferserver.bean.BankBean;
import com.teso.AnaTransferserver.bean.SignUpBean;
import com.teso.AnaTransferserver.bean.UserBean;
import com.teso.AnaTransferserver.model.Bank;
import com.teso.AnaTransferserver.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User converterUser(SignUpBean signUpBean){
        User user = new User();
        user.setUserEmail(signUpBean.getEmail());
        user.setUserPassword(signUpBean.getPassword());
        user.setUserFullName(signUpBean.getFullname());
        user.setUserCountry(signUpBean.getCountry());
        return user;
    }

    public UserBean convertBean(User user){
        ModelMapper modelMapper = new ModelMapper();
        UserBean userBean =  modelMapper.map(user,UserBean.class);
        return userBean;
    }

    public User convertModel(UserBean userBean){
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userBean,User.class);
        return user;
    }
}

