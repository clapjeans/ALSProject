package kopo.poly.service.impl;

import kopo.poly.persistance.mapper.IDicMapper;
import kopo.poly.persistance.mapper.IMapMapper;
import kopo.poly.service.IMapService;
import kopo.poly.vo.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
@Service("MapService")
public class MapService implements IMapService {


    @Resource(name = "MapMapper")
    private IMapMapper mapMapper;


    //지도 리스트 가져오는법
    @Override
    public List<Map<String, Object>> getMapList(PageInfo paging, String gu, String dong, Map<String, String> pMap) {
        log.info(this.getClass().getName() + ".getNoticList start!");

        List<Map<String,Object>> getMapList = mapMapper.getMapList(paging,gu,dong,pMap);


        if (getMapList == null) {
            getMapList = new ArrayList<>();
        }
        log.info(this.getClass().getName() + ".getUserQuizTitleList end!");
        return getMapList;
    }


    @Override
    public List<Map<String, String>> getMapListInfo(Map<String, String> pMap) {

        log.info(this.getClass().getName()+"location List get ");

        List<Map<String,String>> rList = mapMapper.getMapInfoList(pMap);


        if (rList == null) {
            rList = new ArrayList<>();
        }


        log.info(this.getClass().getName()+"location List get ");

        return  rList;
    }
}
