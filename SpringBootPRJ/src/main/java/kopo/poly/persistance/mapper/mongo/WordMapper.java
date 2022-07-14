package kopo.poly.persistance.mapper.mongo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import kopo.poly.dto.ChatDTO;
import kopo.poly.dto.DicDTO;
import kopo.poly.persistance.mapper.IWordMapper;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonRegularExpression;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component("WordMapper")
public class WordMapper implements IWordMapper {

    private final String colNm = "InfoCollection";  //private final 이클래스에서만 사용가능한 상수된 변수

    private final MongoTemplate mongodb;

    @Autowired
    public WordMapper(MongoTemplate mongodb) {
        this.mongodb = mongodb;
    }

    //단어가있는지 없는지 찾기
    @Override
    public List<DicDTO> WordSearch(List<String> rList) throws Exception {

        log.info(this.getClass().getName() + ".insertUser Start!");

        // 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
        List<DicDTO> wordList = new ArrayList<>();

       // String word="";//담을 변수? 찾는 값

        //저장할 컬렉션 객체 생성
        MongoCollection<Document> collection = mongodb.getCollection(colNm);

        for(int i=0;i<rList.size();i++) {
            log.info(this.getClass().getName()+"찾는 단어는 이것입니다 "+ rList.get(i));
            Document query = new Document();
            query.append("DICNM", new BsonRegularExpression("^.*" + rList.get(i)+ ".*$", "i"));

            FindIterable<Document> rs = collection.find(query);

            for (Document doc : rs) {
                if (doc == null) {
                    doc = new Document();

                }else {
                   String word = doc.getString("DICNM");
                   log.info(this.getClass().getName() + "출력이 되는가" + word);

                    DicDTO rDTO = new DicDTO();
                    rDTO.setDicnm(word);
                    wordList.add(rDTO); //비슷한 문자 모두 다담기

                }




            }
        }

        return wordList;
    }
}
