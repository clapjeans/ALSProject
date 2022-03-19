package kopo.poly.persistance.mapper.mongo;

import com.mongodb.client.MongoCollection;
import kopo.poly.persistance.mapper.IDicMapper;
import kopo.poly.persistance.mapper.comm.AbstractMongoDBCommon;
import kopo.poly.util.CmmUtil;
import kopo.poly.vo.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonRegularExpression;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

@Component("DicMapper")
@Slf4j
public class DicMapper extends AbstractMongoDBCommon implements IDicMapper {

    private final MongoTemplate mongodb;

    @Autowired
    public DicMapper(MongoTemplate mongodb) {
        this.mongodb = mongodb;
    }


    @Override //컬럼명 , 페이지,가연성분류,검색키워드
    public List<Map<String, Object>> getTitlelist(String colNm, PageInfo paging, String SORTNM, Map<String, String> pMap) throws Exception {

        // 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
        List<Map<String, Object>> rList = new ArrayList<>();

        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        // Created with Studio 3T, the IDE for MongoDB - https://studio3t.com/

        //페이징
        int skipSize = (paging.getCurrentPage() - 1) * 10;
        int limitSize = 9;


        Document query = new Document();
        if (SORTNM != "") {
            query.append("SORTNM", SORTNM);
        }
        if (pMap.get("keyword") != "") {
            query.append(pMap.get("sort"), new BsonRegularExpression("^.*" + pMap.get("keyword") + ".*$", "i"));
        }


        Document projection = new Document();
        projection.append("SORTNM", "$SORTNM");
        projection.append("DICNM", "$DICNM");
        projection.append("_id", 0);

        Consumer<Document> processBlock = document -> {
            String SORT = CmmUtil.nvl(document.getString("SORTNM"));
            String DICNM = CmmUtil.nvl(document.getString("DICNM"));
            //제대로 출력되는지 확인
            log.info(this.getClass().getName() + DICNM);
            Map<String, Object> rMap = new LinkedHashMap<>();

            rMap.put("SORTNM", SORT);
            rMap.put("DICNM", DICNM);
            rList.add(rMap);

        };

        log.info(this.getClass().getName() + "mongolist mapper 동작하냐 시ㅏㄹ?");

        collection.find(query).skip(skipSize).limit(limitSize).projection(projection).forEach(processBlock);
        return rList;
    }

    //갯수가져오기
    @Override
    public int getlistCount(String nm, String colNm) throws Exception {
        log.info(this.getClass().getName() + ".updateUserPwForFind start");
//
//        MongoCollection<Document> collection = mongodb.getCollection(colNm);
//
//        int res;
//        Consumer<Document> processBlock = doc -> {
//             int count = doc.li("COUNT(*)");
//
//        };
//
//
//        List<? extends Bson> pipeline = Arrays.asList(
//                new Document()
//                        .append("$match", new Document()
//                                .append("SORTNM", nm)
//                        ),
//                new Document()
//                        .append("$group", new Document()
//                                .append("_id", new Document())
//                                .append("COUNT(*)", new Document()
//                                        .append("$sum", 1)
//                                )
//                        ),
//                new Document()
//                        .append("$project", new Document()
//                                .append("COUNT(*)", "$COUNT(*)")
//                                .append("_id", 0)
//                        )
//        );
//
//       collection.aggregate(pipeline)
//                .allowDiskUse(true)
//                .forEach(processBlock);
        int res = 0;
        return res;
//
    }

    @Override
    public List<Map<String, String>> getTitlelist(String colNm, String dicnm) {
        // 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
        List<Map<String, String>> rList = new ArrayList<>();

        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        // Created with Studio 3T, the IDE for MongoDB - https://studio3t.com/


        Document query = new Document();
        query.append("DICNM", dicnm);


        Document projection = new Document();
        projection.append("SORTNM", "$SORTNM");
        projection.append("DICNM", "$DICNM");
        projection.append("EXP", "$EXP");
        projection.append("METHOD", "$METHOD");
        projection.append("CARE", "$CARE");
        projection.append("_id", 0);

        Consumer<Document> processBlock = document -> {
            String SORTNM = CmmUtil.nvl(document.getString("SORTNM"));
            String DICNM = CmmUtil.nvl(document.getString("DICNM"));
            String EXP = CmmUtil.nvl(document.getString("EXP"));
            String METHOD = CmmUtil.nvl(document.getString("METHOD"));
            String CARE = CmmUtil.nvl(document.getString("CARE"));


            log.info(this.getClass().getName() + DICNM);
            Map<String, String> rMap = new LinkedHashMap<>();

            rMap.put("SORTNM", SORTNM);
            rMap.put("DICNM", DICNM);
            rMap.put("EXP", EXP);
            rMap.put("METHOD", METHOD);
            rMap.put("CARE", CARE);

            log.info(this.getClass().getName() + METHOD);
            log.info(this.getClass().getName() + DICNM);
            log.info(this.getClass().getName() + EXP);
            log.info(this.getClass().getName() + SORTNM);
            rList.add(rMap);

        };

        log.info(this.getClass().getName() + "mongolist mapper 동작하냐 시ㅏㄹ?");

        collection.find(query).projection(projection).forEach(processBlock);

        return rList;
    }


}


