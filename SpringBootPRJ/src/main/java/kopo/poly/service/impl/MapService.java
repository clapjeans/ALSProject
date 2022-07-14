package kopo.poly.service.impl;

import kopo.poly.dto.MapDTO;
import kopo.poly.dto.SearchDTO;
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
    public List<MapDTO> getMapList(PageInfo paging, String gu, String dong, SearchDTO sDTO) throws Exception {
        log.info(this.getClass().getName() + ".getNoticList start!");

        List<MapDTO> getMapList = mapMapper.getMapList(paging,gu,dong,sDTO);


        if (getMapList == null) {
            getMapList = new ArrayList<>();
        }
        log.info(this.getClass().getName() + ".getUserQuizTitleList end!");
        return getMapList;
    }


    @Override
    public List<MapDTO> getMapListInfo(MapDTO mDTO ) throws Exception {

        log.info(this.getClass().getName()+"location List get ");

        List<MapDTO> rList = mapMapper.getMapInfoList(mDTO);


        if (rList == null) {
            rList = new ArrayList<>();
        }


        log.info(this.getClass().getName()+"location List get ");

        return  rList;
    }

    @Override
    public int getlistCount(SearchDTO sDTO,String gu) throws Exception {
        log.info(this.getClass().getName() + ".getlistCount Start");

        int listcount = mapMapper.getListCount(sDTO,gu);

        log.info(this.getClass().getName() + "getlistCount End");
        return listcount;
    }

    @Override
    public int getAllListCount() throws Exception {
        log.info(this.getClass().getName() + ".getAllListCount Start");

        int listcount = mapMapper.getAllListCount();

        log.info(this.getClass().getName() + "getAllListCount End");
        return listcount;
    }
}
