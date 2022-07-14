package kopo.poly.service.impl;

import kopo.poly.dto.BoardDTO;
import kopo.poly.dto.ImgDTO;
import kopo.poly.persistance.mapper.IBoardMapper;
import kopo.poly.persistance.mapper.IImgMapper;
import kopo.poly.service.IImgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("ImgService")
public class ImgService implements IImgService {

    // mongoDB collection name
    private final String colNm = "ImgCollection";

    @Resource(name = "ImgMapper")
    private IImgMapper ImgMapper;

//파일 저장 경로
    @Override
    public int insertFilePath(ImgDTO fDTO) throws Exception {

        log.info(this.getClass().getName()+". insetfilePath Start!");

        int res =ImgMapper.insertFilePath(fDTO,colNm);

        log.info(this.getClass().getName()+". insetfilePath end!");

        return res;
    }



    @Override
    public ImgDTO getFilePath(String seq) throws Exception {
        log.info(this.getClass().getName()+"getsavePath Start!");

        ImgDTO fDTO = ImgMapper.getFilePath(seq,colNm);

        log.info(this.getClass().getName()+"getsavePath end!");
        return fDTO;
    }

    @Override
    public int deleteFilePath(BoardDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".deleteFilePath start!");

        int res = ImgMapper.deleteFilePath(pDTO, colNm);

        return res;
    }

    @Override
    public int updateFileUpload(ImgDTO fDTO) {
        log.info(this.getClass().getName() + ".updateFileUpload start!");

        int res = ImgMapper.updateFileUpload(fDTO, colNm);

        return res;
    }

}
