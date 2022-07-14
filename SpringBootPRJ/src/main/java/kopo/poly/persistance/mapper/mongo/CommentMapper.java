package kopo.poly.persistance.mapper.mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import kopo.poly.dto.CommetDTO;
import kopo.poly.persistance.mapper.ICommentMapper;
import kopo.poly.persistance.mapper.comm.AbstractMongoDBCommon;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Component("CommentMapper")
public class CommentMapper extends AbstractMongoDBCommon implements ICommentMapper {

    private final MongoTemplate mongodb;

    @Autowired
    public CommentMapper(MongoTemplate mongodb) {
        this.mongodb = mongodb;
    }

    //게시물 등록
    @Override
    @ResponseBody
    public int insertComment(CommetDTO pDTO, String colNm) {
        log.info(this.getClass().getName() + ".insertUser Start!");

        int res =0;

        // 컬렉션이 없다면 컬렉션 생성
        super.createCollection(colNm);

        //저장할 컬렉션 객체 생성
        MongoCollection<Document> collection = mongodb.getCollection(colNm);


        // DTO를 Map 데이터타입으로 변경 한 뒤, 변경된 Map 데이터타입을 Document로 변경하기
        collection.insertOne(new Document(new ObjectMapper().convertValue(pDTO, Map.class)));

        log.info(this.getClass().getName() + ".insertUser End!");

        res =1;  //성공하면 res 1 값을 반환함

        return res;
    }

    //댓글 가져오기
    @Override
    public List<CommetDTO> commentGetList(String seq, String colNm) throws Exception {

        log.info(this.getClass().getName() + "commentGetList mapper start ");

        List<CommetDTO> rList = new ArrayList<>();

        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // Created with Studio 3T, the IDE for MongoDB - https://studio3t.com/



        Document query = new Document();
        query.append("boardseq", seq);


        Document projection = new Document();
        projection.append("user_name", "$user_name");
        projection.append("comments", "$comments");
        projection.append("reg_dt", "$reg_dt");
        projection.append("user_email", "$user_email");
        projection.append("commentseq", "$commentseq");
        projection.append("_id", 0);

        FindIterable<Document> rs = col.find(query);

        for (Document doc : rs) {
            if (doc == null) {
                doc = new Document();
            }

            //조회가 잘되나 출력을 해봄
            String user_name = CmmUtil.nvl(doc.getString("user_name"));
            String comments = CmmUtil.nvl(doc.getString("comments"));
            String reg_dt = CmmUtil.nvl(doc.getString("reg_dt"));
            String  user_email= CmmUtil.nvl(doc.getString("user_email"));
            String  commentseq= CmmUtil.nvl(doc.getString("commentseq"));



            log.info("user_name : " + user_name);
            log.info("comments : " + comments);
            log.info("reg_dt : " + reg_dt);
            log.info("user_email : " + user_email);
            log.info("commentseq : " + commentseq);


            CommetDTO rDTO = new CommetDTO();
            rDTO.setUser_name(user_name);
            rDTO.setComments(comments);
            rDTO.setReg_dt(reg_dt);
            rDTO.setUser_email(EncryptUtil.decAES128CBC(user_email));
            rDTO.setCommentseq(commentseq);



            //레코드 결과를 list에 저장하기
            rList.add(rDTO);

        }


        log.info(this.getClass().getName() + "commentGetList mapper end ");

        return rList;

    }

    //게시물삭제
    @Override
    public int deleteComment(CommetDTO pDTO, String colNm) throws Exception {
        log.info(this.getClass().getName() + ".deleteComment start");

        int res =0;

        MongoCollection<Document> col = mongodb.getCollection(colNm);

        Document query = new Document();
        query.append("boardseq", pDTO.getBoardseq()); //게시물시퀀스값
        query.append("comments", pDTO.getComments()); //댓글 내용


        FindIterable<Document> rs = col.find(query);

        rs.forEach(doc -> col.deleteOne(doc));
        res =1;

        log.info(this.getClass().getName() + ".deleteComment end");

        return res;
    }

    @Override
    public int getCommetncount(String seq,String colNm) throws Exception {


        log.info("getCommetCount!! start ");

        int count=0;

        MongoCollection<Document> col = mongodb.getCollection(colNm); //객체 생성

        List<? extends Bson> pipeline = Arrays.asList(
                new Document()
                        .append("$project", new Document()
                                .append("_id", 0)
                                .append("ComCollection", "$$ROOT")
                        ),
                new Document()
                        .append("$lookup", new Document()
                                .append("localField", "ComCollection.seq")
                                .append("from", "BoardCollection")
                                .append("foreignField", "seq")
                                .append("as", "BoardCollection")
                        ),
                new Document()
                        .append("$unwind", new Document()
                                .append("path", "$BoardCollection")
                                .append("preserveNullAndEmptyArrays", false)
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
                                .append("COUNT", "$COUNT(*)")
                                .append("_id", 0)
                        )
        );
        AggregateIterable<Document> rs=  col.aggregate(pipeline)
                .allowDiskUse(true);
        for (Document doc : rs) {

            if (doc == null) {
                doc = new Document();
            }

            count = doc.getInteger("count");

            log.info("count : " + count);
        }

        log.info("getCommetCount!!!!!!!!!!1 end ");

        return count;
    }


    //댓글 수정하는 메퍼
    @Override
    public int updateComment(CommetDTO pDTO, String colNm) throws Exception {
        log.info(this.getClass().getName()+".updateComment Start!!!");

        log.info("commentseq  "+pDTO.getCommentseq()); //댓글 시퀀스값
        log.info("comment  "+pDTO.getComments()); //댓글 내용 잘 가져왔는지확인

        int res =0;

        MongoCollection<Document> col = mongodb.getCollection(colNm);

        //document where 역할

        Document query = new Document();
        query.append("commentseq",pDTO.getCommentseq());


        FindIterable<Document> rs = col.find(query);

        //업데이트할 수정 목록
        Document updateDoc = new Document();
        updateDoc.append("comments",pDTO.getComments());
        updateDoc.append("commentseq",pDTO.getCommentseq());
        updateDoc.append("reg_dt",pDTO.getReg_dt());

        rs.forEach(doc ->col.updateOne(doc, new Document("$set",updateDoc)));


        res =1;

        log.info(this.getClass().getName()+".updateComment End!!!");
        return res;
    }

//    @Override
//    public List<CommetDTO> commentList(CommetDTO pDTO, String colNm) throws Exception {
//        // 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
//        List<CommetDTO> rList = new ArrayList<>();
//
//        MongoCollection<Document> col = mongodb.getCollection(colNm);
//
//        // Created with Studio 3T, the IDE for MongoDB - https://studio3t.com/
//
//
//        Document query = new Document();
//        query.append("seq", pDTO.getSeq());
//
//
//        Document projection = new Document();
//        projection.append("user_name", "$user_name");
//        projection.append("comments", "$comments");
//        projection.append("reg_dt", "$reg_dt");
//        projection.append("user_email", "$user_email");
//        projection.append("_id", 0);
//
//        FindIterable<Document> rs = col.find(query);
//
//        for (Document doc : rs) {
//            if (doc == null) {
//                doc = new Document();
//            }
//            //조회가 잘되나 출력을 해봄
//            String user_name = CmmUtil.nvl(doc.getString("user_name"));
//            String comments = CmmUtil.nvl(doc.getString("comments"));
//            String reg_dt = CmmUtil.nvl(doc.getString("reg_dt"));
//            String  user_email= CmmUtil.nvl(doc.getString("user_email"));
//
//
//            log.info("user_name : " + user_name);
//            log.info("comments : " + comments);
//            log.info("reg_dt : " + reg_dt);
//            log.info("user_email :"+user_email);
//
//            CommetDTO rDTO = new CommetDTO();
//
//            rDTO.setUser_name(user_name);
//            rDTO.setComments(comments);
//            rDTO.setReg_dt(reg_dt);
//            rDTO.setUser_email(EncryptUtil.decAES128CBC(user_email));
//
//            //레코드 결과를 list에 저장하기
//            rList.add(rDTO);
//
//        }
//
//
//        log.info(this.getClass().getName() + "mongolist mapper end ");
//
//
//        return rList;
//
//    }

}
