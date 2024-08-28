package com.example.posbackend.bo;

import com.example.posbackend.dto.OrderDetailDTO;

import java.util.ArrayList;

public interface OrderDetailBo {
    ArrayList<OrderDetailDTO> getAllOrderDetails();
    boolean saveOrderDetail(OrderDetailDTO orderDetailDTO);
}
