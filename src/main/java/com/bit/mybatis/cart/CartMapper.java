package com.bit.mybatis.cart;

import com.bit.cart.dto.CartDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartMapper {
    public int insertUserCart(CartDTO dto);
}
