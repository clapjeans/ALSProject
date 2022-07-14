package kopo.poly.service.impl;

import com.amazonaws.services.codecommit.model.UserInfo;
import kopo.poly.dto.UserInfoDTO;
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
    public void insertUser(UserInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".insertUser start");

        userMapper.insertUser(colNm, pDTO);

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
    public UserInfoDTO getUser(UserInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".getUser start");

        UserInfoDTO rDTO = new UserInfoDTO();

         rDTO = userMapper.getUser(colNm, pDTO);

        log.info(this.getClass().getName() + ".getUser end");

        return rDTO;
    }

    @Override
    public int updateUserPw(UserInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".updateUserPw start");

        int res = userMapper.updateUserPw(colNm, pDTO);

        log.info(this.getClass().getName() + ".updateUserPw end");

        return res;
    }

    @Override
    public int deleteUser(UserInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".deleteUser start");

        int res = userMapper.deleteUser(colNm, pDTO);

        log.info(this.getClass().getName() + ".deleteUser end");

        return res;
    }

    @Override
    public UserInfoDTO getUserInfo(UserInfoDTO pDTO) {
        return null;
    }

    @Override
    public int UpdateUserPage(UserInfoDTO pDTO) {
        return 0;
    }

    @Override
    public List<UserInfoDTO> getUserList(UserInfoDTO pDTO) {
        return null;
    }


}
