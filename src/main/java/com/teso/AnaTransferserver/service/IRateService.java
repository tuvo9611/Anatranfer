package com.teso.AnaTransferserver.service;

import com.teso.AnaTransferserver.bean.PromoteCodeBean;
import com.teso.AnaTransferserver.bean.RateBean;

import java.util.List;

public interface IRateService {
    public List<RateBean> findAll();
    public RateBean findById(Long id);
    public void deleteById(Long id);
    public RateBean save(RateBean rateBean);
}
