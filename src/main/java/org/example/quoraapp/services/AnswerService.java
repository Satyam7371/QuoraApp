package org.example.quoraapp.services;

import org.example.quoraapp.dtos.AnswerDto;
import org.example.quoraapp.models.Answer;
import org.example.quoraapp.models.Question;
import org.example.quoraapp.repositories.AnswerRepository;
import org.example.quoraapp.repositories.QuestionRepository;
import org.example.quoraapp.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    private AnswerRepository answerRepository;
    private UserRepository userRepository;
    private QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository, UserRepository userRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    public Optional<Answer> getAnswerById(Long id) {
        return answerRepository.findById(id);
    }

    public List<Answer> getAnswersByQuestionId(Long questionId, int page, int size) {
        return answerRepository.findByQuestionId(questionId, PageRequest.of(page, size)).getContent();
    }

    public Answer createAnswer(AnswerDto  answerDto) {
        Answer answer = new Answer();
        answer.setContent(answerDto.getContent());

        Optional<Question> question = questionRepository.findById(answerDto.getQuestionId());
        question.ifPresent(answer::setQuestion);

        return answerRepository.save(answer);
    }

    public void deleteAnswer(AnswerDto answerDto) {
        answerRepository.deleteById(answerDto.getId());
    }
}
