package com.bit.mybatis.review;

import com.bit.review.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {
    public int reviewSave(ReviewDTO dto);

}
