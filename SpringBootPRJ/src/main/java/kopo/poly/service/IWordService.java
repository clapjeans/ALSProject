package kopo.poly.service;

import kopo.poly.dto.ChatDTO;
import kopo.poly.dto.DicDTO;

import java.util.List;
import java.util.Map;

public interface IWordService {

    //자연어 처리 - 형태소 분석 (명사만 추출하기)
    List<String> doWordNouns(String text) throws Exception;

    //빈도수 분석(단어별 출현 빈도수)
    Map<String, Integer> doWordCount(List<String> pList) throws Exception;

    //분석할 문장의 자연어 처리  수행
    List<DicDTO> doWordAnalysis(String text) throws Exception;


}
