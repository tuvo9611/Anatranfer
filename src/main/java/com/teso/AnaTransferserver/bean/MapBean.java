package com.teso.AnaTransferserver.bean;

import java.util.HashMap;

public class MapBean {
    HashMap<String, Object> map = new HashMap<String, Object>();
    public MapBean(){}

    public void put(String key,Object value){
       map.put(key,value);
    }

    public HashMap<String, Object> getAll(){
        return this.map;
    }
}
