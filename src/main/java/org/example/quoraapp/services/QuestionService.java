package org.example.quoraapp.services;

import org.example.quoraapp.dtos.QuestionDto;
import org.example.quoraapp.models.Question;
import org.example.quoraapp.models.User;
import org.example.quoraapp.models.Tag;
import org.example.quoraapp.repositories.QuestionRepository;
import org.example.quoraapp.repositories.TagRepository;
import org.example.quoraapp.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;
    private UserRepository userRepository;
    private TagRepository tagRepository;

    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    public List<Question> getAllQuestions(int offset, int limit) {
        return questionRepository.findAll(PageRequest.of(offset, limit)).getContent();
    }

    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public Question createQuestion(QuestionDto questionDto) {
        Question question = new Question();
        question.setTitle(questionDto.getTitle());
        question.setContent(questionDto.getContent());

        Optional<User> user = userRepository.findById(questionDto.getUserId());
        user.ifPresent(question::setUser);

        Set<Tag> tags = questionDto.getTagIds().stream()
                .map(tagRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        question.setTags(tags);

        return questionRepository.save(question);
    }
}
