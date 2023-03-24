package com.bit.mybatis.order;

import com.bit.order.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;


@Mapper
public interface OrderMapper {
    public ArrayList<String> getUserOrdersDeliveryStates(String memberIdx);

    public ArrayList<OrderDTO> getUserOrders(String memberIdx);
}
