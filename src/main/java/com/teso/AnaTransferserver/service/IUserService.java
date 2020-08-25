package com.teso.AnaTransferserver.service;

import com.teso.AnaTransferserver.bean.StoreBean;
import com.teso.AnaTransferserver.bean.UserBean;
import com.teso.AnaTransferserver.model.User;

import java.util.List;

public interface IUserService {
    public List<UserBean> findAll();
    public UserBean findById(Long id);
    public void deleteById(Long id);
    public UserBean save(UserBean userBean);
    public UserBean findByUserEmail(String email);
}
