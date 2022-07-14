package kopo.poly.service.impl;

import kopo.poly.dto.BoardDTO;
import kopo.poly.persistance.mapper.IBoardMapper;
import kopo.poly.persistance.mapper.IDicMapper;
import kopo.poly.service.IBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.bson.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Service("BoardService")
public class BoardService implements IBoardService {


    // mongoDB collection name
    private final String colNm = "BoardCollection"; //게시판

    @Resource(name = "BoardMapper")
    private IBoardMapper boardMapper;


    @Override
    public List<Document> getNoticeList() throws Exception {

        log.info(this.getClass().getName() + ".getNoticeList start!");

        return boardMapper.getNoticeList(colNm);

    }


    @Override
    public int InsertNoticeInfo(BoardDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".InsertNoticeInfo start!");

       int res = boardMapper.InsertNoticeInfo(pDTO,colNm);
       return  res;
    }


    @Override
    public BoardDTO getNoticeInfo(BoardDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".getNoticeInfo start!");

        // 상세보기 할때마다, 조회수 증가하기
     //   log.info("Update ReadCNT");
      //  boardMapper.updateNoticeReadCnt(pDTO);

        return boardMapper.getNoticeInfo(pDTO,colNm);

    }


    @Override
    public int updateNoticeInfo(BoardDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".updateNoticeInfo start!");

       int res =  boardMapper.updateNoticeInfo(pDTO,colNm);

       return  res;

    }


    @Override
    public int deleteNoticeInfo(BoardDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".deleteNoticeInfo start!");

        int res = boardMapper.deleteNoticeInfo(pDTO, colNm);

        return res;
    }

}
