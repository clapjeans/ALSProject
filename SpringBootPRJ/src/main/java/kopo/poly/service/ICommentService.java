package kopo.poly.service;

import kopo.poly.dto.CommetDTO;

import java.util.List;
import java.util.Map;

public interface ICommentService {

    //댓글 저장하기
    int insertComment(CommetDTO pDTO) throws Exception;

    //댓글가져오기
    List<CommetDTO> commentgetList(String seq)throws Exception;

    int deleteComment(CommetDTO pDTO)throws Exception;

    int getcount(String seq)throws Exception;

    int updateComment(CommetDTO pDTO)throws Exception;
}
