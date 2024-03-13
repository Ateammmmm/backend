package com.artpro.artpro.chat.service;

import com.artpro.artpro.chat.dto.MessageRequest;
import com.artpro.artpro.chat.entity.Message;
import com.artpro.artpro.chat.mapper.MessageMapper;
import com.artpro.artpro.chat.repository.ChattingRepository;
import com.artpro.artpro.room.entity.ChattingRoom;
import com.artpro.artpro.room.repository.ChattingRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChattingService {

    private final ChattingRepository chattingRepository;
    private final ChattingRoomRepository chattingRoomRepository;
    private final MessageMapper messageMapper;

    public void createMessage(MessageRequest request, Long roomId) {
        ChattingRoom chattingRoom = chattingRoomRepository.findById(roomId)
                .orElseThrow();
        Message message = messageMapper.toEntity(request, chattingRoom);
        chattingRepository.save(message);
    }
}
