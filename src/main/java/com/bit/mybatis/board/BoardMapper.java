package com.bit.mybatis.board;

import com.bit.board.dto.BoardDTO;
import com.bit.board.dto.BoardRepDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface BoardMapper {
    public List<BoardDTO> selectAllBoardList(@Param("s") int start, @Param("e") int end);

    public int writeSave(BoardDTO boardDTO);

    public BoardDTO contentView(int writeNo);

    public void upHit(int writeNo);

    public int delete(int writeNo);

    public int modify(BoardDTO boardDTO);

    public void addReply(BoardRepDTO boardRepDTO);

    public List<BoardRepDTO> getRepList(int write_group);

    public int selectBoardCount();
}