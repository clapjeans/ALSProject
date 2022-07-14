package kopo.poly.persistance.mapper;

import kopo.poly.dto.CommetDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


public interface ICommentMapper {

    //댓글 컬렉션에 저장하기
    int insertComment(CommetDTO pDTO, String colNm)throws Exception;

    //댓글 가져오기
    List<CommetDTO> commentGetList(String seq, String colNm)throws Exception;

    //댓글삭제하기
    int deleteComment(CommetDTO pDTO, String colNm)throws Exception;

    //댓글 카운트 가져오기
    int getCommetncount(String seq,String colNm) throws Exception;


    //댓글 수정하기
    int updateComment(CommetDTO pDTO,String colNm)throws  Exception;
}
