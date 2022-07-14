package kopo.poly.controller;

import kopo.poly.dto.ChatDTO;
import kopo.poly.dto.DicDTO;
import kopo.poly.service.IWordService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class WordController {


    @Resource(name="WordService")
    private IWordService wordService;


    @ResponseBody
    @PostMapping(value = "word/analysis")
    public List <DicDTO>analysis(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".inputForm !");

        //분석할 문장
      // String text = "귤껍질가방분리수거";
       String text = CmmUtil.nvl(request.getParameter("text"));

        //신조어 및 새롭게 생겨난 가수 및 그룹명은 제대로 된 분석이 불가능합니다.
        // 새로운 명사 단어들은 어떻게 데이터를 처리해야 할까?? => 데이터사전의 주기적인 업데이트

        List<DicDTO>rList  = wordService.doWordAnalysis(text);

        log.info("rList "+rList);



        return rList;
    }


}
