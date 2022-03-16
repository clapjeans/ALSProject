package kopo.poly.service.impl;

import kopo.poly.persistance.mapper.IDicMapper;
import kopo.poly.service.IDicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("DicService")
@Slf4j
public class DicService implements IDicService {

    // mongoDB collection name
    private final String colNm = "InfoCollection";

    @Resource(name = "DicMapper")
    private IDicMapper dicMapper;

    @Transactional
    @Override
    public List<Map<String, Object>> getList() throws Exception {
        log.info(this.getClass().getName() + ".getNoticList start!");
        List<Map<String,Object>> getTitle = dicMapper.getTitlelist(colNm);


        if (getTitle == null) {
            getTitle = new ArrayList<>();
        }

        log.info(this.getClass().getName() + ".getUserQuizTitleList end!");

        return getTitle;
    }
}
