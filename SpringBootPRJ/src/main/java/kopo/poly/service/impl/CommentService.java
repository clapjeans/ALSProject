package kopo.poly.service.impl;

import kopo.poly.dto.CommetDTO;
import kopo.poly.persistance.mapper.ICommentMapper;
import kopo.poly.persistance.mapper.IImgMapper;
import kopo.poly.service.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service("CommentService")
public class CommentService implements ICommentService {

    // mongoDB collection name
    private final String colNm = "ComCollection";

    @Resource(name = "CommentMapper")
    private ICommentMapper commentMapper;

    //댓글 저장하기
    @Override
    public int insertComment(CommetDTO pDTO)throws Exception {

        int res = commentMapper.insertComment(pDTO,colNm);

        return res;
    }

    //댓글 목록 가져오기
    @Override
    public List<CommetDTO> commentgetList(String seq) throws Exception {

        List<CommetDTO>rList  = commentMapper.commentGetList(seq,colNm);

        log.info("rList"+rList);

        return rList;
    }

    @Override
    public int deleteComment(CommetDTO pDTO) throws Exception {
        int res = commentMapper.deleteComment(pDTO,colNm);

        return res;
    }

    @Override
    public int getcount(String seq) throws Exception {
        log.info("commentCount get Start! ");
        return commentMapper.getCommetncount(seq,colNm);
    }

    //댓글 수정하기
    @Override
    public int updateComment(CommetDTO pDTO) throws Exception {

        log.info(this.getClass().getName()+"updateComment get Start! ");

        int res = commentMapper.updateComment(pDTO,colNm);
        return res;
    }
}
