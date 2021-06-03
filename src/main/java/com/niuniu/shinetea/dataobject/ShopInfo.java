package com.niuniu.shinetea.dataobject;

import com.niuniu.shinetea.enums.ShopStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DynamicUpdate
@Data
@Proxy(lazy = false)
public class ShopInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shopId;

    private String shopName;

    private String longitude;

    private String latitude;

    private String address;

    private String addressDetail;

    private String phoneNumber;

    private String openTime;

    private String closeTime;

    private Integer isOpen = ShopStatusEnum.OPEN.getCode();

    private String shopRemark;

    /*public ShopInfo(String shopName, String longitude, String latitude, String address,
                    String addressDetail, String phoneNumber, String openTime, String closeTime) {
        this.shopName = shopName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.addressDetail = addressDetail;
        this.phoneNumber = phoneNumber;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public ShopInfo() {}*/
}
