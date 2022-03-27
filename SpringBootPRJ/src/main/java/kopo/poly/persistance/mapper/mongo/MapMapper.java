package kopo.poly.persistance.mapper.mongo;



import com.mongodb.client.MongoCollection;
import kopo.poly.persistance.mapper.IMapMapper;
import kopo.poly.persistance.mapper.comm.AbstractMongoDBCommon;
import kopo.poly.util.CmmUtil;
import kopo.poly.vo.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonRegularExpression;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Slf4j
@Component("MapMapper")
public class MapMapper extends AbstractMongoDBCommon implements IMapMapper {

    private final MongoTemplate mongodb;
    private final String colNm = "LocCollection";  //private final 무슨 뜻인지 찾아보기

    @Autowired
    public MapMapper(MongoTemplate mongodb) {
        this.mongodb = mongodb;
    }


    @Override //컬럼명 , 페이지,가연성분류,검색키워드
    public List<Map<String, Object>> getMapList(PageInfo paging, String gu, String dong, Map<String, String> pMap) {
       //사용할 컬렌션


        // 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
        List<Map<String, Object>> rList = new ArrayList<>();

        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        // Created with Studio 3T, the IDE for MongoDB - https://studio3t.com/

        //페이징
        int skipSize = (paging.getCurrentPage() - 1) * 10;
        int limitSize = 9;


        Document query = new Document();
        if (gu != "") {
            query.append("GU_NAME",gu);
        }
        if (dong != "") {
            query.append("GU_PLACE",new BsonRegularExpression("^.*" + dong+ ".*$", "i"));
        }
        if (!pMap.get("keyword").equals("") ) {
            query.append(pMap.get("category"), new BsonRegularExpression("^.*" + pMap.get("keyword") + ".*$", "i"));
        }


        Document projection = new Document();
        projection.append("GU_NAME", "$GU_NAME");
        projection.append("GU_PLACE", "$GU_PLACE");
        projection.append("DATE", "$DATE");
        projection.append("_id", 0);

        Consumer<Document> processBlock = document -> {
            String GU_NAME = CmmUtil.nvl(document.getString("GU_NAME"));
            String GU_PLACE = CmmUtil.nvl(document.getString("GU_PLACE"));
            String DATE = CmmUtil.nvl(document.getString("DATE"));
            //제대로 출력되는지 확인
            log.info(this.getClass().getName() + GU_NAME);
            Map<String, Object> rMap = new LinkedHashMap<>();

            rMap.put("GU_NAME", GU_NAME);
            rMap.put("GU_PLACE", GU_PLACE);
            rMap.put("DATE", DATE);
            rList.add(rMap);

        };

        log.info(this.getClass().getName() + "mongolist mapper ?");

        collection.find(query).skip(skipSize).limit(limitSize).projection(projection).forEach(processBlock);


        return rList;
    }

@Override //재활용방법 가져오기
public List<Map<String, String>> getMapInfoList(Map<String, String> pMap) {
    // 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
    List<Map<String, String>> rList = new ArrayList<>();

    MongoCollection<Document> collection = mongodb.getCollection(colNm);

    Document query = new Document();
    query.append("GU_PLACE",pMap.get("GU_PLACE"));
    query.append("GU_NAME",pMap.get("GU_NAME"));


    Consumer<Document> processBlock = document -> {
        String GU_NUM = CmmUtil.nvl(document.getString("GU_NUM"));
        String GU_NAME = CmmUtil.nvl(document.getString("GU_NAME"));
        String GU_PLACE = CmmUtil.nvl(document.getString("GU_PLACE"));
        String PLACE_TY = CmmUtil.nvl(document.getString("PLACE_TY"));
        String PLACE = CmmUtil.nvl(document.getString("PLACE"));
        String LIFE_WAY = CmmUtil.nvl(document.getString("LIFE_WAY"));
        String FOOD_WAY = CmmUtil.nvl(document.getString("FOOD_WAY"));
        String REC_WAY = CmmUtil.nvl(document.getString("REC_WAY"));
        String LIFE_DY = CmmUtil.nvl(document.getString("LIFE_DY"));
        String FOOD_DY = CmmUtil.nvl(document.getString("FOOD_DY"));
        String REC_DY = CmmUtil.nvl(document.getString("REC_DY"));
        String LIFE_TM1 = CmmUtil.nvl(document.getString("LIFE_TM1"));
        String LIFE_TM2 = CmmUtil.nvl(document.getString("LIFE_TM2"));
        String FOOD_TM1 = CmmUtil.nvl(document.getString("FOOD_TM1"));
        String FOOD_TM2 = CmmUtil.nvl(document.getString("FOOD_TM2"));
        String REC_TM1 = CmmUtil.nvl(document.getString("REC_TM1"));
        String REC_TM2 = CmmUtil.nvl(document.getString("REC_TM2"));
        String DAYOFF = CmmUtil.nvl(document.getString("DAYOFF"));
        String MANAGE = CmmUtil.nvl(document.getString("MANAGE"));
        String DATE = CmmUtil.nvl(document.getString("DATE"));
        String PHONM = CmmUtil.nvl(document.getString("PHONM"));

        //제대로 출력되는지 확인
        log.info(this.getClass().getName() + GU_NAME);

        Map<String, String> rMap = new LinkedHashMap<>();
        rMap.put("GU_NUM", GU_NUM);
        rMap.put("GU_NAME", GU_NAME);
        rMap.put("GU_PLACE", GU_PLACE);
        rMap.put("PLACE_TY", PLACE_TY);
        rMap.put("PLACE", PLACE);
        rMap.put("LIFE_WAY", LIFE_WAY);
        rMap.put("FOOD_WAY", FOOD_WAY);
        rMap.put("REC_WAY", REC_WAY);
        rMap.put("LIFE_DY", LIFE_DY);
        rMap.put("FOOD_DY", FOOD_DY);
        rMap.put("REC_DY", REC_DY);
        rMap.put("LIFE_TM1", LIFE_TM1);
        rMap.put("LIFE_TM2", LIFE_TM2);
        rMap.put("FOOD_TM1", FOOD_TM1);
        rMap.put("FOOD_TM2", FOOD_TM2);
        rMap.put("REC_TM1", REC_TM1);
        rMap.put("REC_TM2", REC_TM2);
        rMap.put("DAYOFF", DAYOFF);
        rMap.put("MANAGE", MANAGE);
        rMap.put("DATE", DATE);
        rMap.put("PHONM", PHONM);

        rList.add(rMap);

    };

    log.info(this.getClass().getName() + "mongolist mapper ?");

    collection.find(query).forEach(processBlock);


    return rList;
}

}
