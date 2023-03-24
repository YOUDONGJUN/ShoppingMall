package com.bit.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.bit.board.dto.BoardRepDTO;
import com.bit.board.service.BoardService;
import com.bit.member.session_name.MemberSessionName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/board")
public class BoardRepController implements MemberSessionName {
    @Autowired
    BoardService boardService;

    @PostMapping(value = "addReply", produces = "application/json; charset=utf-8")
    public void addReply(@RequestBody Map<String, Object> map, HttpSession session) {

        BoardRepDTO boardRepDTO = new BoardRepDTO();
        boardRepDTO.setId((String) session.getAttribute(LOGIN));
        boardRepDTO.setWrite_group(Integer.parseInt((String) map.get("write_no")));
        boardRepDTO.setTitle((String) map.get("title"));
        boardRepDTO.setContent((String) map.get("content"));

        boardService.addReply(boardRepDTO);
    }

    @GetMapping(value = "replyData/{write_group}", produces = "application/json; charset=utf-8")
    public List<BoardRepDTO> replyData(@PathVariable int write_group) {
        return boardService.getRepList(write_group);
    }
}
