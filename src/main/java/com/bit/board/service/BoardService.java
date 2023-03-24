package com.bit.board.service;

import com.bit.board.dto.BoardDTO;
import com.bit.board.dto.BoardRepDTO;
import com.bit.member.session_name.MemberSessionName;
import com.bit.message.MessageDTO;
import com.bit.mybatis.board.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardMapper boardMapper;

    public void selectAllBoardList(Model model, int num) {
        int allCount = boardMapper.selectBoardCount(); // 글 총 개수 얻어오기
        int pageLetter = 3; //한 페이지에 3개의 글 표현
        int repeat = allCount / pageLetter;
        if (allCount % pageLetter != 0) {
            repeat += 1;
        }
        int end = num * pageLetter;
        int start = end + 1 - pageLetter;
        model.addAttribute("repeat", repeat);
        model.addAttribute("boardList", boardMapper.selectAllBoardList(start, end));
    }


    public String writeSave(MultipartHttpServletRequest mul,
                            HttpServletRequest request) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle(mul.getParameter("title"));
        boardDTO.setContent(mul.getParameter("content"));
        HttpSession session = request.getSession();
        boardDTO.setId((String) session.getAttribute(MemberSessionName.LOGIN));

        MultipartFile file = mul.getFile("image_file_name");

        BoardFileService bfs = new BoardFileService();

        if (file.isEmpty()) { // 파일이 비워있으면 true
            boardDTO.setImageFileName("nan");
        } else { //파일이 존재하는 경우
            boardDTO.setImageFileName(bfs.saveFile(file));
        }
		/*
		int result = mapper.writeSave(dto);
		String com.bit.message = bfs.getMessage(result, request);
		return com.bit.message;
		*/
        return bfs.getMessage(boardMapper.writeSave(boardDTO), request);
    }


    public void contentView(int writeNo, Model model) {
        model.addAttribute("personalData", boardMapper.contentView(writeNo));
        upHit(writeNo);
    }

    private void upHit(int writeNo) {
        boardMapper.upHit(writeNo);
    }


    public String boardDelete(int writeNo, String imageFileName, HttpServletRequest request) {
        BoardFileService bfs = new BoardFileService();
        int result = boardMapper.delete(writeNo);

        MessageDTO messageDTO = new MessageDTO();

        if (result == 1) {//DB삭제 성공
            bfs.deleteImage(imageFileName);
        }
        messageDTO.setRequest(request);
        messageDTO.setResult(result);
        messageDTO.setSuccessMessage("성공적으로 삭제 되었습니다");
        messageDTO.setSuccessURL("/board/boardAllList");
        messageDTO.setFailMessage("삭제 중 문제가 발생하였습니다");
        messageDTO.setFailURL("/board/contentView");

        return bfs.getMessage(messageDTO);
    }

    public String modify(MultipartHttpServletRequest mul, HttpServletRequest request) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setWriteNo(Integer.parseInt(mul.getParameter("writeNo")));
        boardDTO.setTitle(mul.getParameter("title"));
        boardDTO.setContent(mul.getParameter("content"));

        MultipartFile file = mul.getFile("imageFileName");
        BoardFileService bfs = new BoardFileService();

        if (file.isEmpty()) { // 이미지 변경 되지 않았음
            boardDTO.setImageFileName(mul.getParameter("originFileName"));
        } else { // 이미지 변경 되었음.
            boardDTO.setImageFileName(bfs.saveFile(file));
            bfs.deleteImage(mul.getParameter("originFileName"));
        }
        int result = boardMapper.modify(boardDTO);

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setResult(result);
        messageDTO.setRequest(request);
        messageDTO.setSuccessMessage("성공적으로 수정되었습니다");
        messageDTO.setSuccessURL("/board/boardAllList");
        messageDTO.setFailMessage("수정 중 문제 발생!!!");
        messageDTO.setFailURL("/board/modify_form");

        return bfs.getMessage(messageDTO);
    }

    public void addReply(BoardRepDTO boardRepDTO) {
        boardMapper.addReply(boardRepDTO);
    }

    public List<BoardRepDTO> getRepList(int write_group) {
        return boardMapper.getRepList(write_group);
    }

}