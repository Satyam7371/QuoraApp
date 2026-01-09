package org.example.quoraapp.repositories;

import org.example.quoraapp.models.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    Page<Answer> findByQuestionId(Long questionId, Pageable pageable);           // this allow us to fetch the answers in pagination mechanism by pages
}
