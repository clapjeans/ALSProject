package kopo.poly.persistance.mapper.mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import kopo.poly.dto.MongoDTO;
import kopo.poly.persistance.mapper.IMongoMapper;
import kopo.poly.persistance.mapper.comm.AbstractMongoDBCommon;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component("MongoMapper")
public class MongoMapper extends AbstractMongoDBCommon implements IMongoMapper
{


    @Override
    public int insertData(MongoDTO pDTO, String colNm) throws Exception {
        log.info(this.getClass().getName()+"insertDat Start");

        int res = 0;

        //데이터를 저장할 컬렉션 생성
        super.createCollection(colNm);

        //저장할 컬렉션 객체 생성
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        //DTO를 Map 데이터 타입으로 변경한뒤 변경된 Map 데이터 타입을 Documet로 변경하기
        col.insertOne(new Document(new ObjectMapper().convertValue(pDTO, Map.class)));

        res = 1;

        log.info(this.getClass().getName()+"insertData End!");
        return res;
    }
}
