package kopo.poly.service.impl;


import kopo.poly.dto.MongoDTO;
import kopo.poly.persistance.mapper.IMongoMapper;
import kopo.poly.service.IMongoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("MongoService")
public class MongoService implements IMongoService {

    @Resource(name="MongoMapper")
    private IMongoMapper mongoMapper;

    @Override
    public void mongoTest() throws Exception {

        log.info(this.getClass().getName()+".mongoTest Start!");

        String colNm = "MONGODB_TEST";

        MongoDTO pDTO = new MongoDTO();
        pDTO.setAddr("서울");
        pDTO.setEmail("DdDD@naver");
        pDTO.setUser_nm("dkdkdkdk");

        //mongoDB 데이터 저장하기
        mongoMapper.insertData(pDTO,colNm);

        //로그찍기
        log.info(this.getClass().getName()+".mongoTest End!");

    }
}
