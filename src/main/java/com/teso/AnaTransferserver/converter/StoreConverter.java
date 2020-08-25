package com.teso.AnaTransferserver.converter;
import com.teso.AnaTransferserver.bean.StoreBean;
import com.teso.AnaTransferserver.model.Store;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StoreConverter {
    public StoreBean convertBean(Store store){
        ModelMapper modelMapper = new ModelMapper();
        StoreBean storeBean =  modelMapper.map(store,StoreBean.class);
        return storeBean;
    }
    public Store convertModel(StoreBean storeBean){
        ModelMapper modelMapper = new ModelMapper();
        Store store = modelMapper.map(storeBean,Store.class);
        return store;
    }
}
