package com.teso.AnaTransferserver.service;

import com.teso.AnaTransferserver.bean.PaymentBean;
import com.teso.AnaTransferserver.bean.PromoteCodeBean;

import java.util.List;

public interface IPromoteCodeService {
    public List<PromoteCodeBean> findAll();
    public PromoteCodeBean findById(Long id);
    public void deleteById(Long id);
    public PromoteCodeBean save(PromoteCodeBean promoteCodeBean);
}
