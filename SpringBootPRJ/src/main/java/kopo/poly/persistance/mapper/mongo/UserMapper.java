package kopo.poly.persistance.mapper.mongo;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import kopo.poly.dto.MapDTO;
import kopo.poly.dto.UserInfoDTO;
import kopo.poly.persistance.mapper.IUserMapper;
import kopo.poly.persistance.mapper.comm.AbstractMongoDBCommon;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Consumer;

@Slf4j
@Component("UserMapper")
public class UserMapper extends AbstractMongoDBCommon implements IUserMapper {

    private final MongoTemplate mongodb;

    @Autowired
    public UserMapper(MongoTemplate mongodb) {
        this.mongodb = mongodb;
    }

    @Override
    public int insertUser(String colNm, UserInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".insertUser Start!");

        int res =0;

        // 컬렉션이 없다면 컬렉션 생성
        super.createCollection(colNm);

        //저장할 컬렉션 객체 생성
        MongoCollection<Document> collection = mongodb.getCollection(colNm);


        // DTO를 Map 데이터타입으로 변경 한 뒤, 변경된 Map 데이터타입을 Document로 변경하기
        collection.insertOne(new Document(new ObjectMapper().convertValue(pDTO, Map.class)));

        log.info(this.getClass().getName() + ".insertUser End!");

        res =1;

        return res;
    }

    @Override
    public Map<String, String> getUserExistForAJAX(String colNm, String user_email) throws Exception {

        log.info(this.getClass().getName() + ".getUserExistForAJAX Start!");

        Map<String, String> rMap = new HashMap<>();

        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        // Created with Studio 3T, the IDE for MongoDB - https://studio3t.com/

        Document query = new Document();

        query.append("user_email", user_email);

        Consumer<Document> processBlock = document -> rMap.put("res", "exist");

        collection.find(query).forEach(processBlock);

        log.info(this.getClass().getName() + ".getUserExistForAJAX End!");

        return rMap;
    }

    @Override
    public UserInfoDTO getUser(String colNm, UserInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".getUser start");

        // 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        // Created with Studio 3T, the IDE for MongoDB - https://studio3t.com/

        UserInfoDTO rDTO = new UserInfoDTO();

        Document query = new Document();
        query.append("user_email", pDTO.getUser_email());
        query.append("user_pw", pDTO.getUser_pw());

        FindIterable<Document> rs = collection.find(query);

        for (Document doc : rs) {
            if (doc == null) {
                doc = new Document();
            }
            log.info(this.getClass().getName() + ".getUser start");
            String user_email = doc.getString("user_email");
            String user_name = doc.getString("user_name");

            log.info(this.getClass().getName()+"출력이 되는가"+user_email);
            log.info(this.getClass().getName()+user_name);

            rDTO.setUser_email(user_email);
            rDTO.setUser_name(user_name);


        }

        log.info(this.getClass().getName() + ".getUser end");

        return rDTO;
    }

    @Override
    public int updateUserPw(String colNm, UserInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".updateUserPwForFind start");

        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        Document findQuery = new Document();
        findQuery.append("user_email", pDTO.getUser_email());
        findQuery.append("user_name", pDTO.getUser_name());

        Document updateQuery = new Document();
        updateQuery.append("user_pw", pDTO.getUser_pw());


        UpdateResult updateResults = collection.updateOne(findQuery, new Document("$set", updateQuery));

        int res = (int) updateResults.getMatchedCount();

        log.info("res : " + res);

        log.info(this.getClass().getName() + ".updateUserPwForFind end");

        return res;
    }

    @Override
    public int deleteUser(String colNm, UserInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".deleteUser start");

        int res =0;

        MongoCollection<Document> col = mongodb.getCollection(colNm);

        Document query = new Document();
        query.append("user_email", pDTO.getUser_email());
        query.append("user_name", pDTO.getUser_name());


        FindIterable<Document> rs = col.find(query);

        rs.forEach(doc -> col.deleteOne(doc));
         res =1;

        log.info(this.getClass().getName() + ".deleteUser end");

        return res;
    }




}
