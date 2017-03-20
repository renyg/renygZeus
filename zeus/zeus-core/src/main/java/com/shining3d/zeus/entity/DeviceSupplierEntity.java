package com.shining3d.zeus.entity;

/**
 * 订单的供应商
 * @author renyg
 *
 */
public class DeviceSupplierEntity extends BaseEntity{

    /**
     * 
     */
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}