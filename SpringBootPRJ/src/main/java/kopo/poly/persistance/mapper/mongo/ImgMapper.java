package kopo.poly.persistance.mapper.mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import kopo.poly.dto.BoardDTO;
import kopo.poly.dto.ImgDTO;
import kopo.poly.persistance.mapper.IImgMapper;
import kopo.poly.persistance.mapper.comm.AbstractMongoDBCommon;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component("ImgMapper")
public class ImgMapper extends AbstractMongoDBCommon implements IImgMapper {

    private final MongoTemplate mongodb;

    public ImgMapper(MongoTemplate mongodb) {
        this.mongodb = mongodb;
    }

    //사진 게시물담기
    @Override
    public int insertFilePath(ImgDTO fDTO, String colnm) throws Exception {
        log.info(this.getClass().getName() + ".InsertNoticeInfo Start!");

        int res = 0;


        // 컬렉션이 없다면 컬렉션 생성
        super.createCollection(colnm);

        //저장할 컬렉션 객체 생성
        MongoCollection<Document> collection = mongodb.getCollection(colnm);

        // DTO를 Map 데이터타입으로 변경 한 뒤, 변경된 Map 데이터타입을 Document로 변경하기
        collection.insertOne(new Document(new ObjectMapper().convertValue(fDTO, Map.class)));

        log.info(this.getClass().getName() + ".InsertNoticeInfo End!");

        res = 1;

        return res;
    }

    @Override
    public ImgDTO getFilePath(String seq, String colnm) {

        log.info(this.getClass().getName() + "getFilePath mapper start");

        MongoCollection<Document> col = mongodb.getCollection(colnm);

        //비교조건
        Document query = new Document();
        query.append("seq", seq);

        //객체를 담기위함
        ImgDTO pDTO = new ImgDTO();

        Document projection = new Document();
        projection.append("save_file_path", "$save_file_path");
        projection.append("save_sfile_name", "$save_sfile_name");
        projection.append("save_file_name", "$save_file_name");
        projection.append("_id", 0);


        FindIterable<Document> rs = col.find(query).projection(projection);

        for (Document doc : rs) {
            if (doc == null) {
                doc = new Document();
            }
            //조회가 잘되나 출력을 해봄
            String save_file_path = CmmUtil.nvl(doc.getString("save_file_path"));
            String save_sfile_name = CmmUtil.nvl(doc.getString("save_sfile_name"));
            String save_file_name = CmmUtil.nvl(doc.getString("save_file_name"));

            log.info("save_file_path :"+save_file_path);
            log.info("save_sfile_name :"+save_sfile_name);
            log.info("save_file_name :"+save_file_name);

            pDTO.setSave_file_path(save_file_path);
            pDTO.setSave_file_name(save_file_name);
            pDTO.setSave_sfile_name(save_sfile_name);

        }


        log.info(this.getClass().getName() + "getFilePath mapper 동작하냐 ");


        return pDTO;
    }

    @Override
    public int deleteFilePath(BoardDTO pDTO, String colNm) throws Exception {


        log.info(this.getClass().getName() + ".deleteFilePath start");
        int res =0;

        MongoCollection<Document> col = mongodb.getCollection(colNm);

        log.info( pDTO.getUser_email());

        Document query = new Document();
        query.append("user_email", pDTO.getUser_email());
        query.append("seq", pDTO.getSeq());

        FindIterable<Document> rs = col.find(query);

        rs.forEach(doc -> col.deleteOne(doc));
        res =1;


        log.info(this.getClass().getName() + ".deleteFilePath end");

        return res;
    }

    @Override
    public int updateFileUpload(ImgDTO fDTO, String colNm) {
        log.info(this.getClass().getName() + ".InsertNoticeInfo Start!");

        int res = 0;

        //저장할 컬렉션 객체 생성
        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        Document query = new Document();
        query.append("seq", fDTO.getSeq());

        FindIterable<Document> rs = collection.find(query);

        // DTO를 Map 데이터타입으로 변경 한 뒤, 변경된 Map 데이터타입을 Document로 변경하기
        rs.forEach(doc -> collection.updateOne(doc,new Document("$set",(new ObjectMapper().convertValue(fDTO, Map.class)))));

        log.info(this.getClass().getName() + ".InsertNoticeInfo End!");

        res = 1;

        return res;
    }


}
