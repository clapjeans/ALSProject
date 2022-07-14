package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * lombok은 코딩을 줄이기 위해 @어노테이션을 통한 자동 코드 완성기능임
 * @Getter => getter 함수를 작성하지 않았지만, 자동 생성
 * @Setter => setter 함수를 작성하지 않았지만, 자동 생성
 */
@Getter
@Setter
public class BoardDTO {


	private String title; // 제목
	private String contents; // 글 내용
	private String user_email; // 작성자
	private String read_cnt; // 조회수
	private String reg_dt; // 등록일
	private String chg_dt; // 등록 변경일
	private String category; //카테고리
	private String user_name; //등록자 이름
	private String seq;// 썸네일  파일경로 저장


}
