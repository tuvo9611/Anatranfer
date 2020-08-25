package com.teso.AnaTransferserver.service.impl;

import com.teso.AnaTransferserver.bean.PaymentBean;
import com.teso.AnaTransferserver.bean.PromoteCodeBean;
import com.teso.AnaTransferserver.converter.PromoteCodeConverter;
import com.teso.AnaTransferserver.model.Payment;
import com.teso.AnaTransferserver.model.PromoteCode;
import com.teso.AnaTransferserver.repository.IPromoteCodeRepository;
import com.teso.AnaTransferserver.service.IPromoteCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PromoteCodeService implements IPromoteCodeService {

    @Autowired
    private IPromoteCodeRepository promoteCodeRepository;

    @Autowired
    private PromoteCodeConverter promoteCodeConverter;

    @Override
    public List<PromoteCodeBean> findAll() {
        try {
            List<PromoteCode> payments = promoteCodeRepository.findAll();
            List<PromoteCodeBean> results = payments.stream()
                    .map(item -> promoteCodeConverter.convertBean(item)).collect(Collectors.toList());
            return results;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public PromoteCodeBean findById(Long id) {
        try{
            PromoteCode promoteCode = new PromoteCode();
            Optional<PromoteCode> promoteCodeData = promoteCodeRepository.findById(id);
            if(promoteCodeData.isPresent()){
                promoteCode = promoteCodeData.get();
            }
            return promoteCodeConverter.convertBean(promoteCode);
        }catch (Exception e){
            e.printStackTrace();
            return new PromoteCodeBean();
        }
    }

    @Override
    public void deleteById(Long id) {
        try{
            promoteCodeRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public PromoteCodeBean save(PromoteCodeBean promoteCodeBean) {
        try{
            PromoteCode promoteCode = promoteCodeConverter.convertModel(promoteCodeBean);
            promoteCodeRepository.save(promoteCode);
            return promoteCodeConverter.convertBean(promoteCode);
        }catch (Exception e){
            e.printStackTrace();
            return new PromoteCodeBean();
        }
    }
}
