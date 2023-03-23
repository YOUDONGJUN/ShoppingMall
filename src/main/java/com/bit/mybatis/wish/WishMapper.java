package com.bit.mybatis.wish;


import com.bit.wish.dto.WishDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface WishMapper {
	public ArrayList<WishDTO> getUserWishes(String memberIdx);
	public void deleteUserWishes(String wishId);
}
