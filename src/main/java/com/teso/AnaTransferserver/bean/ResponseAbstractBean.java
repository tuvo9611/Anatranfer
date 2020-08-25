package com.teso.AnaTransferserver.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ResponseAbstractBean {
    protected String messageKey = null;
    protected String message = null;

    protected String status;
    protected Object data;

    public abstract void setMessages(String messageKey,String message);
    public abstract void setStatus(String status);
    public abstract void setData(Object object);
}
