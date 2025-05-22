package org.xbxyftx.ordersystembackend.dto;

import lombok.Data;

@Data
public class OrderDTO {
    // 订单用户ID
    private Long userId;
    // 菜品信息
    private String dishInfo;

    // 获取订单用户ID
    public Long getUserId() { return userId; }
    // 设置订单用户ID
    public void setUserId(Long userId) { this.userId = userId; }
    
    // 获取菜品信息
    public String getDishInfo() { return dishInfo; }
    // 设置菜品信息
    public void setDishInfo(String dishInfo) { this.dishInfo = dishInfo; }
} 