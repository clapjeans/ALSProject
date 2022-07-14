package kopo.poly.dto;

import lombok.Data;

@Data
public class CommetDTO {

    private String user_name; //댓글 쓴사람
    private String user_email; //  댓글 쓴 사람 이메일
    private String comments; //댓글 내용
    private String reg_dt; //등록날짜
    private String boardseq; // 게시물 시퀀스
    private String commentseq; // 댓글 시퀀스
}
