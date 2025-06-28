package com.example.springchatmemory.controllers;

import com.example.springchatmemory.model.Answer;
import com.example.springchatmemory.model.Question;
import com.example.springchatmemory.services.AIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class QuestionController {

    private final AIService aiService;

    public QuestionController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return aiService.getAnswer(question);
    }

}
