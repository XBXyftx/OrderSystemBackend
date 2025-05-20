package org.xbxyftx.ordersystembackend.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Long userId;
    private String dishInfo;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getDishInfo() { return dishInfo; }
    public void setDishInfo(String dishInfo) { this.dishInfo = dishInfo; }
} 