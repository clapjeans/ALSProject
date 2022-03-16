package kopo.poly.persistance.mapper.mongo;



import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import kopo.poly.persistance.mapper.IUserMapper;
import kopo.poly.persistance.mapper.comm.AbstractMongoDBCommon;
import kopo.poly.util.CmmUtil;
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
    public void insertUser(String colNm, Map<String, Object> pMap) throws Exception {

        log.info(this.getClass().getName() + ".insertUser Start!");

        // 컬렉션이 없다면 컬렉션 생성
        super.createCollection(colNm, "user_email");

        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        collection.insertOne(new Document(pMap));

        log.info(this.getClass().getName() + ".insertUser End!");

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
    public Map<String, String> getUser(String colNm, Map<String, String> pMap) throws Exception {

        log.info(this.getClass().getName() + ".getUser start");

        Map<String, String> rMap = new HashMap<>();

        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        // Created with Studio 3T, the IDE for MongoDB - https://studio3t.com/

        Document query = new Document();

        query.append("user_email", pMap.get("user_email"));
        query.append("user_pw", pMap.get("user_pw"));

        Consumer<Document> processBlock = document -> {
            String user_email = document.getString("user_email");
            String user_name = document.getString("user_name");

            rMap.put("user_email", user_email);
            rMap.put("user_name", user_name);
        };

        collection.find(query).forEach(processBlock);

        log.info(this.getClass().getName() + ".getUser end");

        return rMap;
    }

    @Override
    public int updateUserPw(String colNm, Map<String, Object> pMap) throws Exception {

        log.info(this.getClass().getName() + ".updateUserPwForFind start");

        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        Document findQuery = new Document();
        findQuery.append("user_email", pMap.get("user_email"));
        findQuery.append("user_name", pMap.get("user_name"));

        Document updateQuery = new Document();
        updateQuery.append("user_pw", pMap.get("user_pw"));

        UpdateResult updateResults = collection.updateOne(findQuery, new Document("$set", updateQuery));
        int res = (int) updateResults.getMatchedCount();
        log.info("res : " + res);

        log.info(this.getClass().getName() + ".updateUserPwForFind end");

        return res;
    }

    @Override
    public int deleteUser(String colNm, Map<String, Object> pMap) throws Exception {

        log.info(this.getClass().getName() + ".updateUserPwForFind start");

        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        DeleteResult deleteResult = collection.deleteOne(new Document(pMap));
        int res = (int) deleteResult.getDeletedCount();

        log.info(this.getClass().getName() + ".updateUserPwForFind end");

        return res;
    }



}
