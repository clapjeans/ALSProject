package kopo.poly.persistance.mapper.mongo;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import kopo.poly.persistance.mapper.IDicMapper;
import kopo.poly.persistance.mapper.comm.AbstractMongoDBCommon;
import kopo.poly.util.CmmUtil;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Component("DicMapper")
public class DicMapper extends AbstractMongoDBCommon implements IDicMapper {
    @Autowired
    private MongoTemplate mongodb;


    @Override
    public List<Map<String, Object>> getTitlelist(String colNm) throws Exception {

        // 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
        List<Map<String, Object>> rList = new ArrayList<>();

        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        // Created with Studio 3T, the IDE for MongoDB - https://studio3t.com/

        Document query = new Document();

        Document projection = new Document();
        projection.append("SORTNM", "$SORTNM");
        projection.append("DICNM", "$DICNM");
        projection.append("_id", 0);

        Consumer<Document> processBlock = document ->  {
           String SORTNM= CmmUtil.nvl(document.getString("SORTNM"));
           String DICNM= CmmUtil.nvl(document.getString("DICNM"));

            Map<String,Object>rMap = new LinkedHashMap<>();

            rMap.put("SORTNM", SORTNM);
            rMap.put("DICNM", DICNM);
            rList.add(rMap);

        };

        collection.find(query).projection(projection).forEach(processBlock);
        return rList;
    }
}


