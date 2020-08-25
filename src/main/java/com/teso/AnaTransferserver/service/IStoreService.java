package com.teso.AnaTransferserver.service;

import com.teso.AnaTransferserver.bean.PaymentBean;
import com.teso.AnaTransferserver.bean.StoreBean;

import java.util.List;

public interface IStoreService {
    public List<StoreBean> findAll();
    public StoreBean findById(Long id);
    public void deleteById(Long id);
    public StoreBean save(StoreBean storeBean);
}
