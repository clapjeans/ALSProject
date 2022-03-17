package kopo.poly.service.impl;

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


    @Override
    public List<Map<String, Object>> getList(PageInfo paging, String SORTNM) throws Exception {
        log.info(this.getClass().getName() + ".getNoticList start!");
        List<Map<String,Object>> getTitle = dicMapper.getTitlelist(colNm,paging,SORTNM);


        if (getTitle == null) {
            getTitle = new ArrayList<>();
        }

        log.info(this.getClass().getName() + ".getUserQuizTitleList end!");

        return getTitle;
    }
    //데이터 갯수가져오기
    @Override
    public int getListCount(String sortnm) throws Exception {
        log.info(this.getClass().getName()+".updateUserWord Start");

        int listcount = dicMapper.getlistCount(colNm,sortnm);

        log.info(this.getClass().getName()+"updateUserWord End");
        return listcount;
    }

    @Override
    public List<Map<String, String>> getInfolist(String dicnm) {

        log.info(this.getClass().getName()+"getINFOList start");
        List<Map<String,String>> InfoList = dicMapper.getTitlelist(colNm,dicnm);


        if (InfoList == null) {
            InfoList = new ArrayList<>();
        }
        log.info(this.getClass().getName()+"getINFOList end");

        return InfoList;
    }


}
