package kopo.poly.persistance.mapper.mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import kopo.poly.dto.BoardDTO;
import kopo.poly.dto.DicDTO;
import kopo.poly.dto.ImgDTO;
import kopo.poly.dto.MapDTO;
import kopo.poly.persistance.mapper.IBoardMapper;
import kopo.poly.persistance.mapper.comm.AbstractMongoDBCommon;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IteratorUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.*;


@Slf4j
@Component("BoardMapper")
public class BoardMapper extends AbstractMongoDBCommon implements IBoardMapper {

    private final MongoTemplate mongodb;

    public BoardMapper(MongoTemplate mongodb) {
        this.mongodb = mongodb;
    }


    //게시판 담기
    @Override
    public int InsertNoticeInfo(BoardDTO pDTO, String colNm) throws Exception {

        log.info(this.getClass().getName() + ".InsertNoticeInfo Start!");

        int res = 0;


        // 컬렉션이 없다면 컬렉션 생성
        super.createCollection(colNm);

        //저장할 컬렉션 객체 생성
        MongoCollection<Document> collection = mongodb.getCollection(colNm);


        // DTO를 Map 데이터타입으로 변경 한 뒤, 변경된 Map 데이터타입을 Document로 변경하기
        collection.insertOne(new Document(new ObjectMapper().convertValue(pDTO, Map.class)));

        log.info(this.getClass().getName() + ".InsertNoticeInfo End!");

        res = 1;

        return res;
    }

//    @Override
//    public List<Document> getNoticeList(String colNm) throws Exception {
//
//
//        log.info(this.getClass().getName() + "getNoticeList info start");
//
//        List<Document> rList = new ArrayList<>();
//
//        MongoCollection<Document> col = mongodb.getCollection(colNm);
//
//        Document query = new Document();
//
//        FindIterable<Document> rs = col.find(query);
//
//        for (Document doc : rs) {
//            if (doc == null) {
//                doc = new Document();
//            }
//
//            String title = CmmUtil.nvl(doc.getString("title"));
//            String user_name = CmmUtil.nvl(doc.getString("user_name"));
//            String save_thumfile_path = CmmUtil.nvl(doc.getString("save_thumfile_path"));
//            String seq = CmmUtil.nvl(doc.getString("seq"));
//
//
//            BoardDTO pDTO = new BoardDTO();
//            pDTO.setUser_name(user_name);
//            pDTO.setTitle(title);
//            pDTO.setSave_thumfile_path(save_thumfile_path);
//            pDTO.setSeq(seq);
//
//
//            //레코드 결과를 list에 저장하기
//            rList.add(pDTO);
//        }
//        return rList;
//
//    }

    //조인할때 쓰는 쿼리
    @Override
    public List<Document> getNoticeList(String colNm) throws Exception {
        log.info(this.getClass().getName() + ".getNoticeList Start!");
        //저장할 컬렉션 객체 생성
        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        //조회 결과를 전달하기 위한 객체 생성하기
        AggregateIterable<Document> rs = null;
        Iterator<Document> cursor = null;
        List<? extends Bson> pipeline = null;

        List<Document> rList = null;


        //조회결과중 출력할 컬럼들

        pipeline = Arrays.asList(
                new Document()
                        .append("$project", new Document()
                                .append("_id", 0)
                                .append("BoardCollection", "$$ROOT")
                        ),
                new Document()
                        .append("$lookup", new Document()
                                .append("localField", "BoardCollection.seq")
                                .append("from", "ImgCollection")
                                .append("foreignField", "seq")
                                .append("as", "ImgCollection")
                        ),
                new Document()
                        .append("$unwind", new Document()
                                .append("path", "$ImgCollection")
                                .append("preserveNullAndEmptyArrays", false)
                        ),
                new Document()
                        .append("$project", new Document()
                                .append("user_name", "$BoardCollection.user_name")
                                .append("save_thumfile_path", "$ImgCollection.save_thumfile_path")
                                .append("title", "$BoardCollection.title")
                                .append("seq", "$BoardCollection.seq")
                                .append("_id", 0)
                        )
        );

        rs = collection.aggregate(pipeline).allowDiskUse(true);
        cursor = rs.iterator();

        rList = IteratorUtils.toList(cursor);

        log.info("rList : " + rList.size());

        cursor = null;
        rs = null;
        collection = null;
        pipeline = null;

        return rList;
    }

    @Override
    public BoardDTO getNoticeInfo(BoardDTO pDTO, String colNm) throws Exception {


        MongoCollection<Document> col = mongodb.getCollection(colNm);

        Document query = new Document();
        query.append("seq", pDTO.getSeq()); //pk

        //객체를 담기위함
        BoardDTO rDTO = new BoardDTO();

        Document projection = new Document();
        projection.append("title", "$title");
        projection.append("contents", "$contents");
        projection.append("user_name", "$user_name");
        projection.append("user_email", "$user_email");
        projection.append("reg_dt", "$reg_dt");
        projection.append("seq", "$seq");
        projection.append("_id", 0);

        FindIterable<Document> rs = col.find(query).projection(projection);

        for (Document doc : rs) {

            if (doc == null) {
                doc = new Document();
            }
            //조회가 잘되나 출력을 해봄
            String title = CmmUtil.nvl(doc.getString("title"));
            String contents = CmmUtil.nvl(doc.getString("contents"));
            String user_name = CmmUtil.nvl(doc.getString("user_name"));
            String  user_email= CmmUtil.nvl(doc.getString("user_email"));
            String reg_dt = CmmUtil.nvl(doc.getString("reg_dt"));
            String seq = CmmUtil.nvl(doc.getString("seq"));



            log.info("title : " + title);
            log.info("contents : " + contents);
            log.info("user_name :"+user_name);
            log.info("user_email :"+user_email);
            log.info("reg_dt :"+reg_dt);
            log.info("seq :"+seq);

            rDTO.setTitle(title);
            rDTO.setContents(contents);
            rDTO.setUser_name(user_name);
            rDTO.setUser_email(user_email);
            rDTO.setReg_dt(reg_dt);
            rDTO.setSeq(seq);


        }


        log.info(this.getClass().getName() + "getNoticeInfo mapper 동작하냐 ");


        return rDTO;
    }

    @Override
    public void updateNoticeReadCnt(BoardDTO pDTO) throws Exception {

    }

    @Override
    public int updateNoticeInfo(BoardDTO pDTO,String colNm) throws Exception {
        log.info(this.getClass().getName() + ".updateNoticeInfo Start!");

        int res = 0;

        //저장할 컬렉션 객체 생성
        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        //
        log.info("seq "+pDTO.getSeq());
        Document query = new Document();
        query.append("seq", pDTO.getSeq());

        FindIterable<Document> rs = collection.find(query);

        // DTO를 Map 데이터타입으로 변경 한 뒤, 변경된 Map 데이터타입을 Document로 변경하기
        rs.forEach(doc -> collection.updateOne(doc,new Document("$set",(new ObjectMapper().convertValue(pDTO, Map.class)))));

        log.info(this.getClass().getName() + ".updateNoticeInfo End!");

        res = 1;

        return res;

    }

    @Override
    public int deleteNoticeInfo(BoardDTO pDTO,String colNm) throws Exception {

        log.info(this.getClass().getName() + ".deleteNoticeInfo start");
        int res =0;


        MongoCollection<Document> col = mongodb.getCollection(colNm);

        Document query = new Document();
        query.append("user_email", pDTO.getUser_email());
        query.append("seq", pDTO.getSeq());

        FindIterable<Document> rs = col.find(query);

        rs.forEach(doc -> col.deleteOne(doc));

        res =1;

        log.info(this.getClass().getName() + ".deleteNoticeInfo end");

        return res;
    }


}
