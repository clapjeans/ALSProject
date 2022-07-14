package kopo.poly.service.impl;

import kopo.poly.dto.DicDTO;
import kopo.poly.persistance.mapper.IDicMapper;
import kopo.poly.service.IDicService;
import kopo.poly.vo.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
@Service("DicService")
public class DicService implements IDicService {

    // mongoDB collection name
    private final String colNm = "InfoCollection";

    @Resource(name = "DicMapper")
    private IDicMapper dicMapper;


    public List<DicDTO> getList(PageInfo paging, String SORTNM, DicDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".getNoticList start!");
        List<DicDTO> getTitlelist = dicMapper.getTitlelist(colNm, paging, SORTNM, pDTO);


        if (getTitlelist == null) {
            getTitlelist = new ArrayList<>();
        }

        log.info(this.getClass().getName() + ".getUserQuizTitleList end!");

        return getTitlelist;
    }

    //데이터 갯수가져오기
    @Override
    public int getListCount(String sortnm) throws Exception {
        log.info(this.getClass().getName() + ".getListCount Start");

        int listcount = dicMapper.getlistCount(colNm, sortnm);

        log.info(this.getClass().getName() + "getListCount End");
        return listcount;
    }

    @Override
    public List<DicDTO> getInfolist(String dicnm) {

        log.info(this.getClass().getName() + "getINFOList start");
        List<DicDTO> InfoList = dicMapper.getInfolist(colNm, dicnm);


        if (InfoList == null) {
            InfoList = new ArrayList<>();
        }
        log.info(this.getClass().getName() + "getINFOList end");

        return InfoList;
    }

    @Override
    public int getKeyCount(DicDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".getKeyCount Start");

        int listcount = dicMapper.getkeyCount(colNm,pDTO);

        log.info(this.getClass().getName() + "getKeyCount End");
        return listcount;
    }


}
