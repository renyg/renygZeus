package com.shining3d.zeus.client.dto;

import com.shining3d.common.dto.BaseDto;

/**
 * 订单的供应商
 *
 * @author renyg
 */
public class DeviceSupplierDto extends BaseDto {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 供应商名称，唯一
     */
    private String name;
    /**
     * 供应商下面的设备数量
     */
    private Integer countDevice;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCountDevice() {
        return countDevice;
    }

    public void setCountDevice(Integer countDevice) {
        this.countDevice = countDevice;
    }
}
