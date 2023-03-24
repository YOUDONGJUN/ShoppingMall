package com.bit.notice.controller;

import com.bit.member.session_name.MemberSessionName;
import com.bit.notice.dto.NoticeRepDTO;
import com.bit.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notice")
public class NoticeRepController implements MemberSessionName {
    @Autowired
    NoticeService noticeService;

    @PostMapping(value = "addReply", produces = "application/json; charset=utf-8")
    public void addReply(@RequestBody Map<String, Object> map, HttpSession session) {

        NoticeRepDTO noticeRepDTO = new NoticeRepDTO();
        noticeRepDTO.setNoticeWriterId((String) session.getAttribute(LOGIN));
        noticeRepDTO.setWrite_group(Integer.parseInt((String) map.get("notice_id")));
        noticeRepDTO.setNoticeTitle((String) map.get("notice_title"));
        noticeRepDTO.setNoticeContent((String) map.get("notice_content"));

        noticeService.addReply(noticeRepDTO);
    }

    @GetMapping(value = "replyData/{write_group}", produces = "application/json; charset=utf-8")
    public List<NoticeRepDTO> replyData(@PathVariable int write_group) {
        return noticeService.getRepList(write_group);
    }
}

