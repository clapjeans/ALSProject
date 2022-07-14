package kopo.poly.persistance.mapper;

import kopo.poly.dto.UserInfoDTO;

import java.util.Map;

public interface IUserMapper {
    // 유저 회원가입
    int insertUser(String colNm, UserInfoDTO pDTO) throws Exception;

    // 유저 회원가입 여부, 이메일
    Map<String, String> getUserExistForAJAX(String colNm, String user_email) throws Exception;

    // 유저 로그인
    UserInfoDTO getUser(String colNm, UserInfoDTO pDTO) throws Exception;

    // 유저 비밀번호 변경
    int updateUserPw(String colNm, UserInfoDTO pDTO) throws Exception;

    // 유저 정보 삭제
    int deleteUser(String colNm, UserInfoDTO pDTO) throws Exception;



}
