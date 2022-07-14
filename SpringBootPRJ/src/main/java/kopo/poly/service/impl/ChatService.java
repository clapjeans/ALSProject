package kopo.poly.service.impl;

import kopo.poly.dto.ChatDTO;
import kopo.poly.persistance.mapper.IChatMapper;
import kopo.poly.service.IChatService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service("ChatService")
public class ChatService implements IChatService {

    @Resource(name = "ChatMapper")
    private IChatMapper chatMapper;

    @Override
    public void insertChat(ChatDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".getRoomList Start!");

        // 채팅 내용 저장
        if (chatMapper.insertChat(pDTO) == 1) {
            log.info("chatMapper.insertChat Success!");

            // 데이터 만료시간 정의(채팅 이후 5분까지만 데이터 저장)
            chatMapper.setTimeOutHour(CmmUtil.nvl(pDTO.getRoomKey()), 5);

        } else {
            log.info("chatMapper.insertChat Fail!");

        }

    }

    @Override
    public List<ChatDTO> getChat(ChatDTO pDTO) throws Exception{
        log.info(this.getClass().getName() + ".getChat Start!");
        return chatMapper.getChat(pDTO);
    }
}