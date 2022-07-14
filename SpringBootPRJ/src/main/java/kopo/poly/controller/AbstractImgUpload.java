package kopo.poly.controller;

import kopo.poly.dto.BoardDTO;
import kopo.poly.dto.ImgDTO;
import kopo.poly.service.IBoardService;
import kopo.poly.service.IImgService;
import kopo.poly.service.IS3Service;
import kopo.poly.service.impl.S3Service;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import kopo.poly.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
public abstract  class AbstractImgUpload  {

    /*

     * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨)
     */
    //이미지 서비스 활용
    @Resource(name = "S3Service")
    private IS3Service s3Service;

    //유저 서비스 활용
    @Resource(name = "ImgService")
    private IImgService imgService;

    protected int ImgUpload(HttpSession session, ModelMap model, MultipartFile mf,String num) throws Exception {

        //제대로 갔는지 확인하기위함
         int result = 0 ;

        String user_email = CmmUtil.nvl((String) session.getAttribute("SS_USER_EMAIL"));

        //객체를 담기위함
        ImgDTO fDTO = new ImgDTO();

        // 임의로 정의된 파일명을 원래대로 만들어주기 위한 목적
        String originalFileName = mf.getOriginalFilename();

        // 파일 확장자 가져오기
        String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1)
                .toLowerCase();
        // 이미지 파일만 실행되도록 함
        if (ext.equals("jpeg") || ext.equals("jpg") || ext.equals("gif") || ext.equals("png")) {

            String save_file_name = user_email + "/" + originalFileName;
            String save_sfile_name = user_email + "/" + "s_" + originalFileName;

            String imgurl = s3Service.upload(mf,user_email,ext);
            String thumnailurl = s3Service.thumnailurl(mf, user_email,ext);

            fDTO.setSave_file_path(imgurl);// 저장되는 경로
            fDTO.setSave_file_name(CmmUtil.nvl(save_file_name));// 저장되는 파일명
            fDTO.setOrg_file_name(originalFileName);// 원래 파일명
            fDTO.setUser_email(CmmUtil.nvl(user_email)); //등록자 이메일
            fDTO.setReg_dt(DateUtil.getDateTime("yyyy-MM-dd-hh:mm:ss")); //저장날짜
            fDTO.setSave_thumfile_path(thumnailurl); //썸네일 저장 장소
            fDTO.setSave_sfile_name(CmmUtil.nvl(save_sfile_name));// 저장되는 썸네일 파일명
            fDTO.setSeq(CmmUtil.nvl(num));// 저장되는 썸네일 파일명
            result = imgService.insertFilePath(fDTO);

            log.info("result : " + result);

        }
      return result;
    }

    protected int ImgUpdateUpload(HttpSession session, ModelMap model, MultipartFile mf,String seq) throws Exception {

        //제대로 갔는지 확인하기위함
        int result = 0 ;

        String user_email = CmmUtil.nvl((String) session.getAttribute("SS_USER_EMAIL"));

        //객체를 담기위함
        ImgDTO fDTO = new ImgDTO();

        // 임의로 정의된 파일명을 원래대로 만들어주기 위한 목적
        String originalFileName = mf.getOriginalFilename();

        // 파일 확장자 가져오기
        String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1)
                .toLowerCase();
        // 이미지 파일만 실행되도록 함
        if (ext.equals("jpeg") || ext.equals("jpg") || ext.equals("gif") || ext.equals("png")) {

            String save_file_name = user_email + "/" + originalFileName;
            String save_sfile_name = user_email + "/" + "s_" + originalFileName;

            String imgurl = s3Service.upload(mf, user_email,ext);
            String thumnailurl = s3Service.thumnailurl(mf, user_email,ext);

            fDTO.setSave_file_path(imgurl);// 저장되는 경로
            fDTO.setSave_file_name(CmmUtil.nvl(save_file_name));// 저장되는 파일명
            fDTO.setOrg_file_name(originalFileName);// 원래 파일명
            fDTO.setUser_email(CmmUtil.nvl(user_email)); //등록자 이메일
            fDTO.setReg_dt(DateUtil.getDateTime("yyyy-MM-dd-hh:mm:ss")); //저장날짜
            fDTO.setSave_thumfile_path(thumnailurl); //썸네일 저장 장소
            fDTO.setSave_sfile_name(CmmUtil.nvl(save_sfile_name));// 저장되는 썸네일 파일명
            fDTO.setSeq(seq); // 시퀀스
            
            
            result = imgService.updateFileUpload(fDTO);

            log.info("result : " + result);

        }
        return result;
    }



}
