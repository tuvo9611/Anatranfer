package com.teso.AnaTransferserver.service;

import com.teso.AnaTransferserver.bean.RecipientBean;

import java.util.List;

public interface IRecipientService {
    public List<RecipientBean> findAll();
    public RecipientBean findById(Long id);
    public RecipientBean save(RecipientBean recipientBean);
}
