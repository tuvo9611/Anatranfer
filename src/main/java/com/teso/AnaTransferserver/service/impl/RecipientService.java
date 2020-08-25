package com.teso.AnaTransferserver.service.impl;

import com.teso.AnaTransferserver.bean.RateBean;
import com.teso.AnaTransferserver.bean.RecipientBean;
import com.teso.AnaTransferserver.converter.RecipientConverter;
import com.teso.AnaTransferserver.model.Rate;
import com.teso.AnaTransferserver.model.Recipient;
import com.teso.AnaTransferserver.repository.IRateRepository;
import com.teso.AnaTransferserver.repository.IRecipientRepository;
import com.teso.AnaTransferserver.service.IRecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipientService implements IRecipientService {

    @Autowired
    private IRecipientRepository recipientRepository;

    @Autowired
    private RecipientConverter recipientConverter;

    @Override
    public List<RecipientBean> findAll() {
        try {
            List<Recipient> recipients = recipientRepository.findAll();
            List<RecipientBean> results = recipients.stream()
                    .map(item -> recipientConverter.convertBean(item)).collect(Collectors.toList());
            return results;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public RecipientBean findById(Long id) {
        try{
            Recipient recipient = new Recipient();
            Optional<Recipient> recipientData = recipientRepository.findById(id);
            if(recipientData.isPresent()){
                recipient = recipientData.get();
            }
            return recipientConverter.convertBean(recipient);
        }catch (Exception e){
            e.printStackTrace();
            return new RecipientBean();
        }
    }

    @Override
    public RecipientBean save(RecipientBean recipientBean) {
        try{
            Recipient recipient = recipientConverter.convertModel(recipientBean);
            recipientRepository.save(recipient);
            return recipientConverter.convertBean(recipient);
        }catch (Exception e){
            e.printStackTrace();
            return new RecipientBean();
        }
    }
}
