package com.knmz.entity;

//import com.alibaba.fastjson.annotation.JSONField;
//import com.alibaba.fastjson.serializer.ToStringSerializer;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Order
 *
 * @author zl
 * @date 2019/11/12 10:11
 */
@Data
@TableName("knmz_order")
public class Order {

    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
//    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    private String title;

    private Long productId;

    private Integer productPriceSn;

    private String productPrice;

    private Integer channel;

    private Integer orderNumber;

    private Integer refundNumber;

    private BigDecimal price;

    private BigDecimal amount;

    private BigDecimal discount;

    private BigDecimal shipAmount;

    private String currency;

    private Integer payMode;

    private String paymentType;

    private LocalDateTime payDate;

    private Long batchId;

    private String userAccount;

    private String userPhone;

    private String userEmail;

    private String userName;

    private String userNick;

    private String userLogo;

    private String shipCity;

    private String shipAddress;

    private String invoiceHeader;

    private String invoiceContent;

    private String remark;

    private String note;

    private Long shareNews;

    private String shipNo;

    private String shipCompany;

    private String shipper;

    private LocalDateTime shipDate;

    private Integer status;

    private String ip;

    private String ipCity;

    private String useragent;

    private String ertTxhash;

    private Integer ertBlockStatus;

    private String ertToken;

    private Boolean isTestnet;

    private Integer deleted;

    private String createBy;

    private LocalDateTime createDate;

    private String updateBy;

    private LocalDateTime updateDate;

}
