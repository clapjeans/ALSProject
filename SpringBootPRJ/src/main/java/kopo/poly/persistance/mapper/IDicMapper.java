package kopo.poly.persistance.mapper;

import java.util.List;
import java.util.Map;

public interface IDicMapper {
    List<Map<String, Object>> getTitlelist(String colNm)throws Exception;
}
