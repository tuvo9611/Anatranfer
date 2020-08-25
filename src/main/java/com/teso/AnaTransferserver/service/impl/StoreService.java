package com.teso.AnaTransferserver.service.impl;

import com.teso.AnaTransferserver.bean.RateBean;
import com.teso.AnaTransferserver.bean.StoreBean;
import com.teso.AnaTransferserver.converter.StoreConverter;
import com.teso.AnaTransferserver.model.Rate;
import com.teso.AnaTransferserver.model.Store;
import com.teso.AnaTransferserver.repository.IStoreRepository;
import com.teso.AnaTransferserver.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreService implements IStoreService {
    @Autowired
    IStoreRepository storeRepository;

    @Autowired
    StoreConverter storeConverter;

    @Override
    public List<StoreBean> findAll() {
        try {
            List<Store> stores= storeRepository.findAll();
            List<StoreBean> results = stores.stream()
                    .map(item -> storeConverter.convertBean(item)).collect(Collectors.toList());
            return results;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public StoreBean findById(Long id) {
        try{
            Store store = new Store();
            Optional<Store> storeData = storeRepository.findById(id);
            if(storeData.isPresent()){
                store = storeData.get();
            }
            return storeConverter.convertBean(store);
        }catch (Exception e){
            e.printStackTrace();
            return new StoreBean();
        }
    }

    @Override
    public void deleteById(Long id) {
        try{
            storeRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public StoreBean save(StoreBean storeBean) {
        try{
            Store store = storeConverter.convertModel(storeBean);
            storeRepository.save(store);
            return storeConverter.convertBean(store);
        }catch (Exception e){
            e.printStackTrace();
            return new StoreBean();
        }
    }
}
