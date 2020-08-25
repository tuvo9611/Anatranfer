package com.teso.AnaTransferserver.service.impl;

import com.teso.AnaTransferserver.bean.UserBean;
import com.teso.AnaTransferserver.converter.UserConverter;
import com.teso.AnaTransferserver.model.User;
import com.teso.AnaTransferserver.repository.IUserRepository;
import com.teso.AnaTransferserver.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public List<UserBean> findAll() {
        try {
            List<User> users = userRepository.findAll();
            List<UserBean> results = users.stream()
                    .map(item -> userConverter.convertBean(item)).collect(Collectors.toList());
            return results;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public UserBean findById(Long id) {
        try{
            User user = new User();
            Optional<User> userData = userRepository.findById(id);
            if(userData.isPresent()){
                user = userData.get();
            }
            return userConverter.convertBean(user);
        }catch (Exception e){
            e.printStackTrace();
            return new UserBean();
        }
    }

    @Override
    public void deleteById(Long id) {
        try{
            userRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public UserBean save(UserBean userBean) {
        try{
            User user = userConverter.convertModel(userBean);
            userRepository.save(user);
            return userConverter.convertBean(user);
        }catch (Exception e){
            e.printStackTrace();
            return new UserBean();
        }
    }

    @Override
    public UserBean findByUserEmail(String email) {
        try{
            User user = userRepository.findByUserEmail(email);
            return userConverter.convertBean(user);
        }catch (Exception e){
            e.printStackTrace();
            return new UserBean();
        }
    }
}
