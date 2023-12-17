package com.arjuninamdar.congressionalchallenge.repositories;

import java.util.List;

import com.arjuninamdar.congressionalchallenge.models.sql.AnswerInfo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnswerInfoRepository extends CrudRepository<AnswerInfo, Integer> {
    public List<AnswerInfo> findByQuestionAnsweredIdAndIsEnabled(int questionAnsweredId, boolean isEnabled); 
}
