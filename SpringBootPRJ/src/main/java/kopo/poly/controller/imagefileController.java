package kopo.poly.controller;

import kopo.poly.dto.ImgDTO;
import kopo.poly.service.IImgService;
import kopo.poly.service.IS3Service;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import kopo.poly.util.EncryptUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class imagefileController {


    //유저 서비스 활용
    @Resource(name = "ImgService")
    private IImgService imgService;

    //이미지 서비스 활용
    @Resource(name = "S3Service")
    private IS3Service s3Service;


    ///ajax로 파일받아 json으로 값 넘기기
    @ResponseBody
    @PostMapping(value = "/imageupload")
    public Map<String, String> AjaxImageUpload(HttpServletRequest request, HttpSession session, HttpServletResponse response,
                                               @RequestParam(value = "fileUplod") MultipartFile mf,
                                               @RequestParam(value = "save_file_name", required = false) String prev_file_name,
                                               @RequestParam(value = "save_sile_name", required = false) String prev_sfile_name) throws Exception {

        log.info(this.getClass().getName() + "Image upload Ajax OK");
        log.info(this.getClass().getName() + prev_file_name); //전에 저장한 파일이름
        log.info(this.getClass().getName() + prev_sfile_name); //전에 저장한 썸네일 파일이름

        Map<String, String> rMap = new HashMap<>(); ///Ajax로 값을 보내기위함

        // 이미지 파일 저장하는 사용자 ID저장
        String user_id = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID"));
        //EncryptUtil.encAES128CBC(CmmUtil.nvl((String) session.getAttribute("SS_USER_ID")));

        // 임의로 정의된 파일명을 원래대로 만들어주기 위한 목적
        String originalFileName = mf.getOriginalFilename();


        // 파일 확장자 가져오기
        String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1)
                .toLowerCase();

        // 이미지 파일만 실행되도록 함
        if (ext.equals("jpeg") || ext.equals("jpg") || ext.equals("gif") || ext.equals("png")) {

            String save_file_name = user_id + "/" + originalFileName;
            String save_sfile_name = user_id + "/" + "s_" + originalFileName;

            String imgurl = s3Service.upload(mf, user_id,ext);
            String thumnailurl = s3Service.thumnailurl(mf, user_id,ext);

            //파일 삭제
//            if (!prev_file_name.equals("")&&!prev_sfile_name.equals("")) {
//                log.info(this.getClass().getName() + "파일삭제 시작");
//                log.info(prev_file_name);
//                log.info(prev_sfile_name);
//                int res = s3Service.fileDelete(prev_file_name); //파일삭제
//                 s3Service.sfileDelete(prev_sfile_name); //썸네일 파일 삭제
//                log.info("res가 1이면 파일삭제 성공입니다" +res);
//            }

            ImgDTO fDTO = new ImgDTO();

            fDTO.setSave_file_path(imgurl);// 저장되는 경로
            fDTO.setSave_file_name(CmmUtil.nvl(save_file_name));// 저장되는 파일명
            fDTO.setOrg_file_name(originalFileName);// 원래 파일명
            fDTO.setExt(ext);  // 확장자명
//            fDTO.setUser_id(EncryptUtil.encAES128CBC(CmmUtil.nvl(user_id))); //프로필 ID
         //   fDTO.setChg_id(user_id); // 회원 ID
           // fDTO.setChg_dt(DateUtil.getDateTime("yyyy-MM-dd-hh:mm:ss")); //저장날짜
            fDTO.setSave_thumfile_path(thumnailurl); //썸네일 저장 장소
            fDTO.setSave_sfile_name(CmmUtil.nvl(save_sfile_name));// 저장되는 썸네일 파일명

            int result = imgService.insertFilePath(fDTO);


            // 업로드 되는 파일을 서버에 저장
            log.info("res:" + result);

            rMap.put("saveFileName", save_file_name); //아작스로 파일 이름 전달
            rMap.put("save_sfile_name", save_sfile_name); //아작스로 파일 이름 전달
        }
        return rMap;
    }

}

