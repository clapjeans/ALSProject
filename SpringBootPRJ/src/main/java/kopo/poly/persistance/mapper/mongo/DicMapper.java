package kopo.poly.persistance.mapper.mongo;

import com.mongodb.MongoException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import kopo.poly.dto.DicDTO;
import kopo.poly.persistance.mapper.IDicMapper;
import kopo.poly.persistance.mapper.comm.AbstractMongoDBCommon;
import kopo.poly.util.CmmUtil;
import kopo.poly.vo.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IteratorUtils;
import org.bson.BsonRegularExpression;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Consumer;

@Slf4j
@Component("DicMapper")
public class DicMapper extends AbstractMongoDBCommon implements IDicMapper {

    private final MongoTemplate mongodb;

    @Autowired
    public DicMapper(MongoTemplate mongodb) {
        this.mongodb = mongodb;
    }


    @Override //컬럼명 , 페이지,가연성분류,검색키워드
    public List<DicDTO> getTitlelist(String colNm, PageInfo paging, String SORTNM, DicDTO pDTO) throws Exception {

        // 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
        List<DicDTO> rlist = new ArrayList<>();

        MongoCollection<Document> col = mongodb.getCollection(colNm);

        //페이징
        int skipSize = (paging.getCurrentPage() - 1) * 10;
        int limitSize = 9;


        Document query = new Document();
        if (!SORTNM.equals("")) {
            query.append("SORTNM", SORTNM);
        }
        if (!pDTO.getKeyword().equals("")) {
            query.append(pDTO.getSort(), new BsonRegularExpression("^.*" + pDTO.getKeyword()+ ".*$", "i"));
        }


        Document projection = new Document();
        projection.append("SORTNM", "$SORTNM");
        projection.append("DICNM", "$DICNM");
        projection.append("_id", 0); //id는 가져오지 않을 때 사용함


        FindIterable<Document> rs =  col.find(query).skip(skipSize).limit(limitSize).projection(projection);

        for(Document doc : rs){
            if(doc==null){
                doc= new Document();
            }
            //조회가 잘되나 출력을 해봄
            String sortnm = CmmUtil.nvl(doc.getString("SORTNM"));
            String dicnm = CmmUtil.nvl(doc.getString("DICNM"));

            log.info("sortnm : "+sortnm);
            log.info("dicnm : "+dicnm);

            DicDTO rDTO = new DicDTO();

            rDTO.setDicnm(dicnm);
            rDTO.setSortnm(sortnm);

            //레코드 결과를 list에 저장하기
            rlist.add(rDTO);

        }


        log.info(this.getClass().getName() + "mongolist mapper 동작하냐 시ㅏㄹ?");


        return rlist;
    }



    //페이지 카운트
    @Override
    public int getlistCount(String colNm, String sortnm) throws Exception {


        log.info(this.getClass().getName()+ "getlistCount Start");

            int count =0;

            List<? extends Bson> pipeline = Arrays.asList(
                    new Document()
                            .append("$match", new Document()
                                    .append("SORTNM", sortnm)
                            ),
                    new Document()
                            .append("$group", new Document()
                                    .append("_id", new Document())
                                    .append("COUNT(SORTNM)", new Document()
                                            .append("$sum", 1)
                                    )
                            ),
                    new Document()
                            .append("$project", new Document()
                                    .append("count", "$COUNT(SORTNM)")
                                    .append("_id", 0)
                            )
            );

        MongoCollection<Document> col = mongodb.getCollection(colNm);
        AggregateIterable<Document> rs = col.aggregate(pipeline).allowDiskUse(true);

        for (Document doc : rs) {

            if (doc == null) {
                doc = new Document();
            }


          count = doc.getInteger("count", 10);

            log.info("count : " + count);
        }


        log.info(this.getClass().getName()+ ". getlistCount end");
        return count;
    }

    //분리수거 배출 방법 세부사항
    @Override
    public List<DicDTO> getInfolist(String colNm, String dicnm) {


        // 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
        List<DicDTO> rList = new ArrayList<>();

        MongoCollection<Document> col = mongodb.getCollection(colNm);

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

        FindIterable<Document> rs = col.find(query).projection(projection);

        for (Document doc : rs) {
            if (doc == null) {
                doc = new Document();
            }
            //조회가 잘되나 출력을 해봄
            String SORTNM = CmmUtil.nvl(doc.getString("SORTNM"));
            String DICNM = CmmUtil.nvl(doc.getString("DICNM"));
            String EXP = CmmUtil.nvl(doc.getString("EXP"));
            String METHOD = CmmUtil.nvl(doc.getString("METHOD"));
            String CARE = CmmUtil.nvl(doc.getString("CARE"));

            log.info("SORTNM : " + SORTNM);
            log.info("DICNM : " + DICNM);

            DicDTO rDTO = new DicDTO();

            rDTO.setDicnm(DICNM);
            rDTO.setSortnm(SORTNM);
            rDTO.setExp(EXP);
            rDTO.setMethod(METHOD);
            rDTO.setCare(CARE);

            //레코드 결과를 list에 저장하기
            rList.add(rDTO);

        }


        log.info(this.getClass().getName() + "mongolist mapper 동작하냐 ");


        return rList;

    }

    @Override
    public int getkeyCount(String colNm, DicDTO pDTO) {


        log.info(this.getClass().getName()+ "getlistCount Start");

        int count =0;

        List<? extends Bson> pipeline = Arrays.asList(
                new Document()
                        .append("$match", new Document()
                                .append(pDTO.getSort(), new BsonRegularExpression("^.*" + pDTO.getKeyword()+ ".*$", "i"))
                        ),
                new Document()
                        .append("$group", new Document()
                                .append("_id", new Document())
                                .append("COUNT("+pDTO.getSort()+")", new Document()
                                        .append("$sum", 1)
                                )
                        ),
                new Document()
                        .append("$project", new Document()
                                .append("count", "$COUNT("+pDTO.getSort()+")")
                                .append("_id", 0)
                        )
        );


        MongoCollection<Document> col = mongodb.getCollection(colNm);
        AggregateIterable<Document> rs = col.aggregate(pipeline).allowDiskUse(true);

        for (Document doc : rs) {

            if (doc == null) {
                doc = new Document();
            }


            count = doc.getInteger("count", 10);

            log.info("count : " + count);
        }


        log.info(this.getClass().getName()+ ". getlistCount end");
        return count;
    }
}


