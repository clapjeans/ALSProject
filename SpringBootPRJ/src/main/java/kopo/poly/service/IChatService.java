package kopo.poly.service;



import kopo.poly.dto.ChatDTO;

import java.util.List;
import java.util.Set;


public interface IChatService {

    // 채팅 대화 저장하기
   void insertChat(ChatDTO pDTO) throws Exception;

   //채팅방 목록 가져오기
    List<ChatDTO> getChat(ChatDTO pDTO)throws Exception;
}