package com.bit.notice.service;

import com.bit.member.session_name.MemberSessionName;
import com.bit.message.MessageDTO;
import com.bit.mybatis.notice.NoticeMapper;
import com.bit.notice.dto.NoticeDTO;
import com.bit.notice.dto.NoticeRepDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class NoticeService {

//    private final String NAMESPACE = "mapper.NoticeMapper";
    @Autowired
    NoticeMapper noticeMapper;
//    private SqlSession session;

    public void selectAllNoticeList(Model model, int num) { //num페이지 번호
        int allCount = noticeMapper.selectNoticeCount(); //글 총 개수 얻어오기
        int pageLetter = 5; //한 페이지에 10개의 글 표현
        int repeat = allCount / pageLetter; //몫
        if (allCount % pageLetter != 0) {// 0과 같으면 페이지수가 딱 떨어져서 그대로 두겠다.
            repeat += 1;
        }
        int end = num * pageLetter;
        int start = end + 1 - pageLetter;
        model.addAttribute("repeat", repeat);
        model.addAttribute("noticeList", noticeMapper.selectAllNoticeList(start, end));
    }


    public String writeSave(MultipartHttpServletRequest mul,
                            HttpServletRequest request) {
        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setNoticeTitle(mul.getParameter("notice_title"));
        noticeDTO.setNoticeContent(mul.getParameter("notice_content"));
        HttpSession session = request.getSession();
        noticeDTO.setNoticeWriterId((String) session.getAttribute(MemberSessionName.LOGIN));

        MultipartFile file = mul.getFile("notice_image_file_name");

        NoticeFileService nfs = new NoticeFileService();

        if (file.isEmpty()) { // 파일이 비워있으면 true
            noticeDTO.setNoticeImageFileName("nan");
        } else { //파일이 존재하는 경우
            noticeDTO.setNoticeImageFileName(nfs.saveFile(file));
        }
        return nfs.getMessage(noticeMapper.writeSave(noticeDTO), request);
    }


    public void contentView(int noticeId, Model model) {
        model.addAttribute("personalData", noticeMapper.contentView(noticeId));
        noticeViews(noticeId);
    }

    private void noticeViews(int noticeId) {
        noticeMapper.noticeViews(noticeId);
    }


    public String modify(MultipartHttpServletRequest mul, HttpServletRequest request) {
        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setNoticeId(Integer.parseInt(mul.getParameter("notice_id")));
        noticeDTO.setNoticeTitle(mul.getParameter("notice_title"));
        noticeDTO.setNoticeContent(mul.getParameter("notice_content"));

        MultipartFile file = mul.getFile("notice_image_file_name");
        NoticeFileService nfs = new NoticeFileService();

        if (file.isEmpty()) {
            noticeDTO.setNoticeImageFileName(mul.getParameter("originFileName"));
        } else {
            noticeDTO.setNoticeImageFileName(nfs.saveFile(file));
            nfs.deleteImage(mul.getParameter("originFileName"));

        }
        int result = noticeMapper.modify(noticeDTO);

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setResult(result);
        messageDTO.setRequest(request);
        messageDTO.setSuccessMessage("성공적으로 수정 되었습니다");
        messageDTO.setSuccessURL("/notice/noticeAllList");
        messageDTO.setFailMessage("수정 중 문제가 발생하였습니다");
        messageDTO.setFailURL("/notice/modify_form");

        return nfs.getMessage(messageDTO);
    }


    public void addReply(NoticeRepDTO noticeRepDTO) {
        noticeMapper.addReply(noticeRepDTO);
    }

    public List<NoticeRepDTO> getRepList(int write_group) {
        return noticeMapper.getRepList(write_group);
    }


    public String noticeDelete(int noticeId, String noticeImageFileName, HttpServletRequest request) {
        NoticeFileService nfs = new NoticeFileService();
        int result = noticeMapper.delete(noticeId);

        MessageDTO messageDTO = new MessageDTO();

        if (result == 1) {
            nfs.deleteImage(noticeImageFileName);
        }
        messageDTO.setRequest(request);
        messageDTO.setResult(result);
        messageDTO.setSuccessMessage("성공적으로 삭제 되었습니다");
        messageDTO.setSuccessURL("/notice/noticeAllList");
        messageDTO.setFailMessage("삭제 중 문제가 발생하였습니다");
        messageDTO.setFailURL("/notice/contentView");

        return nfs.getMessage(messageDTO);
    }

}