package kopo.poly.service.impl;

import kopo.poly.persistance.mapper.IUserMapper;
import kopo.poly.service.IUserService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("UserService")
public class UserService implements IUserService {



    // mongoDB collection name
    private final String colNm = "UserCollection";

    @Resource(name = "UserMapper")
    private IUserMapper userMapper;

    @Override
    public void insertUser(Map<String, Object> pMap) throws Exception {

        log.info(this.getClass().getName() + ".insertUser start");

        userMapper.insertUser(colNm, pMap);

        log.info(this.getClass().getName() + ".insertUser end");

    }

    @Override
    public Map<String, String> getUserExistForAJAX(String user_email) throws Exception {

        log.info(this.getClass().getName() + ".getUserExistForAJAX start");

        Map<String, String> rMap = userMapper.getUserExistForAJAX(colNm, user_email);

        log.info(this.getClass().getName() + ".getUserExistForAJAX end");

        return rMap;
    }

    @Override
    public Map<String, String> getUser(Map<String, String> pMap) throws Exception {

        log.info(this.getClass().getName() + ".getUser start");

        Map<String, String> rMap = userMapper.getUser(colNm, pMap);

        log.info(this.getClass().getName() + ".getUser end");

        return rMap;
    }

    @Override
    public int updateUserPw(Map<String, Object> pMap) throws Exception {

        log.info(this.getClass().getName() + ".updateUserPw start");

        int res = userMapper.updateUserPw(colNm, pMap);

        log.info(this.getClass().getName() + ".updateUserPw end");

        return res;
    }

    @Override
    public int deleteUser(Map<String, Object> pMap) throws Exception {

        log.info(this.getClass().getName() + ".deleteUser start");

        int res = userMapper.deleteUser(colNm, pMap);

        log.info(this.getClass().getName() + ".deleteUser end");

        return res;
    }




}
