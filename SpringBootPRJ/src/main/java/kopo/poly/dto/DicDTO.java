package kopo.poly.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DicDTO {
    private String keyword; //검색어
    private String sort;  //분류조건
    private String dicnm; // ex 휴지 종류
    private String sortnm; // ex 가연성 불연성
    private String method; // 분리배출방법
    private String exp ; //부가설명
    private String care; //주의사항
}
