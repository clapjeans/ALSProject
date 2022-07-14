package kopo.poly.service;

import kopo.poly.dto.UserInfoDTO;

import java.util.List;
import java.util.Map;

public interface IUserService {
    // 유저 회원가입
    void insertUser(UserInfoDTO pDTO) throws Exception;

    // 유저 회원가입 여부, 이메일
    Map<String, String> getUserExistForAJAX(String user_email) throws Exception;

    // 유저 로그인
   UserInfoDTO getUser(UserInfoDTO pDTO) throws Exception;

    // 유저 비밀번호 변경
    int updateUserPw(UserInfoDTO pDTO) throws Exception;

    // 유저 정보 삭제
    int deleteUser(UserInfoDTO pDTO) throws Exception;

    UserInfoDTO getUserInfo(UserInfoDTO pDTO);

    int UpdateUserPage(UserInfoDTO pDTO);


    List<UserInfoDTO> getUserList(UserInfoDTO pDTO);
}
