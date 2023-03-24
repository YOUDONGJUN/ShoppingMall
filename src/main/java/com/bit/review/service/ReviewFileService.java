package com.bit.review.service;

import com.bit.message.MessageDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class ReviewFileService {
    public static final String IMAGE_REPO = "D:\\image_repo";


    public String getMessage(int num, HttpServletRequest request) {
        String message = null; //num = mapper.reviewSave(rDto);
        if (num == 1) {
            message = "<script>alert('새 상품평을 성공적으로 추가 했습니다');";
            //com.bit.message += "location.href='/root/com.bit.board/boardAllList';</script>";
            message +=
                    "location.href='" + request.getContextPath() + "/myPage/readOrders';</script>";
        } else {
            message = "<script>alert('새 상품평 등록에 실패했습니다.');";
            //com.bit.message += "location.href='/root/com.bit.board/writeForm';</script>";
            message +=
                    "location.href='" + request.getContextPath() + "/myPage/readOrders';</script>";
        }
        return message;
    }


    public String saveFile(MultipartFile file) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss-");
        Calendar calendar = Calendar.getInstance();
        String sysFileName =
                sdf.format(calendar.getTime()) + file.getOriginalFilename();
        File saveFile = new File(IMAGE_REPO + "/" + sysFileName);
        try {
            file.transferTo(saveFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sysFileName;
    }

    public void deleteImage(String imageFileName) {
        File file = new File(IMAGE_REPO + "/" + imageFileName);
        file.delete();
    }


    public String getMessage(MessageDTO dto) {
        String message = null;
        String path = dto.getRequest().getContextPath();
        if (dto.getResult() == 1) {
            message = "<script>alert('" + dto.getSuccessMessage() + "');";
            message += "location.href='" + path + dto.getSuccessURL() + "'</script>";
        } else {
            message = "<script>alert('" + dto.getFailMessage() + "');";
            message += "location.href='" + path + dto.getFailURL() + "'</script>";
        }
        return message;
    }
}
