package com.example.springchatmemory.services;


import com.example.springchatmemory.model.Answer;
import com.example.springchatmemory.model.Question;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AIServiceImpl implements AIService {

    private final ChatModel chatModel;
    private final ChatMemory chatMemory;
    


    public AIServiceImpl(ChatModel chatModel, ChatMemory chatMemory) {
        this.chatModel = chatModel;
        this.chatMemory = chatMemory;
    }

    @Override
    public Answer getAnswer(Question question) {
        String conversationId = Objects.isNull(question.conversationId())? UUID.randomUUID().toString() : question.conversationId();
        Message userMessage = new UserMessage(question.question());
        chatMemory.add(conversationId,userMessage);
        ChatResponse response = chatModel.call(new Prompt(chatMemory.get(conversationId)));
        chatMemory.add(conversationId, response.getResult().getOutput());
        return new Answer(conversationId, response.getResult().getOutput().getText());
    }
}
