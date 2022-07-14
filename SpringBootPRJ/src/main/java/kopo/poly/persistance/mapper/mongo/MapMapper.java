package kopo.poly.persistance.mapper.mongo;


import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import kopo.poly.dto.DicDTO;
import kopo.poly.dto.MapDTO;
import kopo.poly.dto.SearchDTO;
import kopo.poly.persistance.mapper.IMapMapper;
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
    public List<MapDTO> getMapList(PageInfo paging, String gu, String dong, SearchDTO sDTO) {
        //사용할 컬렌션


        // 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
        List<MapDTO> rList = new ArrayList<>();

        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // Created with Studio 3T, the IDE for MongoDB - https://studio3t.com/

        //페이징
        int skipSize = (paging.getCurrentPage() - 1) * 10;
        int limitSize = 9;


        Document query = new Document();
        if (gu != "") {
            query.append("GU_NAME", gu);
        }
        if (dong != "") {
            query.append("GU_PLACE", new BsonRegularExpression("^.*" + dong + ".*$", "i"));
        }
        if (!sDTO.getCategory().equals("")) {
            query.append(sDTO.getCategory(), new BsonRegularExpression("^.*" + sDTO.getKeyword() + ".*$", "i"));
        }


        Document projection = new Document();
        projection.append("GU_NAME", "$GU_NAME");
        projection.append("GU_PLACE", "$GU_PLACE");
        projection.append("DATE", "$DATE");
        projection.append("_id", 0);

        FindIterable<Document> rs = col.find(query).skip(skipSize).limit(limitSize).projection(projection);

        for (Document doc : rs) {
            if (doc == null) {
                doc = new Document();
            }
            String GU_NAME = CmmUtil.nvl(doc.getString("GU_NAME"));
            String GU_PLACE = CmmUtil.nvl(doc.getString("GU_PLACE"));
            String DATE = CmmUtil.nvl(doc.getString("DATE"));
            //제대로 출력되는지 확인
            log.info(this.getClass().getName() + GU_NAME);
            log.info(this.getClass().getName() + GU_PLACE);
            log.info(this.getClass().getName() + DATE);


            MapDTO mDTO = new MapDTO();
            mDTO.setGu_name(GU_NAME);
            mDTO.setGu_place(GU_PLACE);
            mDTO.setDate(DATE);


            //레코드 결과를 list에 저장하기
            rList.add(mDTO);

        }
        log.info(this.getClass().getName() + "mongolist mapper ?");

        return rList;
    }

    @Override //재활용방법 가져오기
    public List<MapDTO> getMapInfoList(MapDTO mDTO) {


        log.info(this.getClass().getName() + "getMapList info start");
        // 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언

        List<MapDTO> rList = new ArrayList<>();

        MongoCollection<Document> col = mongodb.getCollection(colNm);

        Document query = new Document();
        query.append("GU_PLACE", mDTO.getGu_place());
        query.append("GU_NAME", mDTO.getGu_name());


        FindIterable<Document> rs = col.find(query);

        for (Document doc : rs) {
            if (doc == null) {
                doc = new Document();
            }
            String GU_NUM = CmmUtil.nvl(doc.getString("GU_NUM"));
            String GU_NAME = CmmUtil.nvl(doc.getString("GU_NAME"));
            String GU_PLACE = CmmUtil.nvl(doc.getString("GU_PLACE"));
            String PLACE_TY = CmmUtil.nvl(doc.getString("PLACE_TY"));
            String PLACE = CmmUtil.nvl(doc.getString("PLACE"));
            String LIFE_WAY = CmmUtil.nvl(doc.getString("LIFE_WAY"));
            String FOOD_WAY = CmmUtil.nvl(doc.getString("FOOD_WAY"));
            String REC_WAY = CmmUtil.nvl(doc.getString("REC_WAY"));
            String LIFE_DY = CmmUtil.nvl(doc.getString("LIFE_DY"));
            String FOOD_DY = CmmUtil.nvl(doc.getString("FOOD_DY"));
            String REC_DY = CmmUtil.nvl(doc.getString("REC_DY"));
            String LIFE_TM1 = CmmUtil.nvl(doc.getString("LIFE_TM1"));
            String LIFE_TM2 = CmmUtil.nvl(doc.getString("LIFE_TM2"));
            String FOOD_TM1 = CmmUtil.nvl(doc.getString("FOOD_TM1"));
            String FOOD_TM2 = CmmUtil.nvl(doc.getString("FOOD_TM2"));
            String REC_TM1 = CmmUtil.nvl(doc.getString("REC_TM1"));
            String REC_TM2 = CmmUtil.nvl(doc.getString("REC_TM2"));
            String DAYOFF = CmmUtil.nvl(doc.getString("DAYOFF"));
            String MANAGE = CmmUtil.nvl(doc.getString("MANAGE"));
            String DATE = CmmUtil.nvl(doc.getString("DATE"));
            String PHONM = CmmUtil.nvl(doc.getString("PHONM"));
            //제대로 출력되는지 확인
            log.info(this.getClass().getName() + GU_NAME);
            log.info(this.getClass().getName() + GU_PLACE);
            log.info(this.getClass().getName() + DATE);


            MapDTO rDTO = new MapDTO();
            rDTO.setGu_name(GU_NAME);
            rDTO.setGu_place(GU_PLACE);
            rDTO.setGu_num(GU_NUM);
            rDTO.setPlace_ty(PLACE_TY);
            rDTO.setLife_way(LIFE_WAY);
            rDTO.setFood_way(FOOD_WAY);
            rDTO.setRec_way(REC_WAY);
            rDTO.setLife_dy(LIFE_DY);
            rDTO.setFood_dy(FOOD_DY);
            rDTO.setRec_dy(REC_DY);
            rDTO.setLife_tm1(LIFE_TM1);
            rDTO.setLife_tm2(LIFE_TM2);
            rDTO.setFood_tm1(FOOD_TM1);
            rDTO.setFood_tm2(FOOD_TM2);
            rDTO.setRec_tm1(REC_TM1);
            rDTO.setRec_tm2(REC_TM2);
            rDTO.setDayoff(DAYOFF);
            rDTO.setManage(MANAGE);
            rDTO.setDate(DATE);
            rDTO.setPhone(PHONM);
            rDTO.setPlace(PLACE);


            //레코드 결과를 list에 저장하기
            rList.add(rDTO);

        }
        log.info(this.getClass().getName() + "getMapInfoList mapper End");

        return rList;

    }

    @Override
    public int getListCount(SearchDTO sDTO, String gu) throws Exception {
        log.info(this.getClass().getName() + "getlistCount Start");

        AggregateIterable<Document> rs = null;
        int count = 0;
        if (!sDTO.getCategory().equals("")) {
            List<? extends Bson> pipeline = Arrays.asList(
                    new Document()
                            .append("$match", new Document()
                                    .append(sDTO.getCategory(), new BsonRegularExpression("^.*" + sDTO.getKeyword() + ".*$", "i"))
                            ),
                    new Document()
                            .append("$group", new Document()
                                    .append("_id", new Document())
                                    .append("COUNT(" + sDTO.getCategory() + ")", new Document()
                                            .append("$sum", 1)
                                    )
                            ),
                    new Document()
                            .append("$project", new Document()
                                    .append("count", "$COUNT(" + sDTO.getCategory() + ")")
                                    .append("_id", 0)
                            )
            );


            MongoCollection<Document> col = mongodb.getCollection(colNm);
            rs = col.aggregate(pipeline).allowDiskUse(true);
        } else {
            List<? extends Bson> pipeline = Arrays.asList(
                    new Document()
                            .append("$match", new Document()
                                    .append("GU_NAME", gu)
                            ),
                    new Document()
                            .append("$group", new Document()
                                    .append("_id", new Document())
                                    .append("COUNT(*)", new Document()
                                            .append("$sum", 1)
                                    )
                            ),
                    new Document()
                            .append("$project", new Document()
                                    .append("count", "$COUNT(*)")
                                    .append("_id", 0)
                            )
            );

            MongoCollection<Document> col = mongodb.getCollection(colNm);
            rs = col.aggregate(pipeline).allowDiskUse(true);
        }


        for (Document doc : rs) {

            if (doc == null) {
                doc = new Document();
            }


            count = doc.getInteger("count", 10);

            log.info("count : " + count);
        }


        log.info(this.getClass().getName() + ". getlistCount end");
        return count;
    }

    //페이지 전체 갯수
    @Override
    public int getAllListCount() throws Exception {
        log.info(this.getClass().getName() + "getlistCount Start");

        int count = 0; //페이지 갯수

        List<? extends Bson> pipeline = Arrays.asList(
                new Document()
                        .append("$group", new Document()
                                .append("_id", new Document())
                                .append("COUNT(*)", new Document()
                                        .append("$sum", 1)
                                )
                        ),
                new Document()
                        .append("$project", new Document()
                                .append("count", "$COUNT(*)")
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


        log.info(this.getClass().getName() + ". getlistCount end");
        return count;

    }
}