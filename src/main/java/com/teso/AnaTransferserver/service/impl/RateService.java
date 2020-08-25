package com.teso.AnaTransferserver.service.impl;

import com.teso.AnaTransferserver.bean.PaymentBean;
import com.teso.AnaTransferserver.bean.RateBean;
import com.teso.AnaTransferserver.converter.RateConverter;
import com.teso.AnaTransferserver.model.Payment;
import com.teso.AnaTransferserver.model.Rate;
import com.teso.AnaTransferserver.repository.IRateRepository;
import com.teso.AnaTransferserver.service.IRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RateService implements IRateService {

    @Autowired
    IRateRepository rateRepository;

    @Autowired
    RateConverter rateConverter;

    @Override
    public List<RateBean> findAll() {
        try {
            List<Rate> rates = rateRepository.findAll();
            List<RateBean> results = rates.stream()
                    .map(item -> rateConverter.convertBean(item)).collect(Collectors.toList());
            return results;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public RateBean findById(Long id) {
        try{
            Rate rate = new Rate();
            Optional<Rate> rateData = rateRepository.findById(id);
            if(rateData.isPresent()){
                rate = rateData.get();
            }
            return rateConverter.convertBean(rate);
        }catch (Exception e){
            e.printStackTrace();
            return new RateBean();
        }
    }

    @Override
    public void deleteById(Long id) {
        try{
            rateRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public RateBean save(RateBean rateBean) {
        try{
            Rate rate = rateConverter.convertModel(rateBean);
            rateRepository.save(rate);
            return rateConverter.convertBean(rate);
        }catch (Exception e){
            e.printStackTrace();
            return new RateBean();
        }
    }
}
