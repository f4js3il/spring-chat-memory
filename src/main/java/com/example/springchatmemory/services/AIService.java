package com.example.springchatmemory.services;


import com.example.springchatmemory.model.Answer;
import com.example.springchatmemory.model.Question;


public interface AIService {

    Answer getAnswer(Question question);
}
