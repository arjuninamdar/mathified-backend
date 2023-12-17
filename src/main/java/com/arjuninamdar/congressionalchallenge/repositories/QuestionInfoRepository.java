package com.arjuninamdar.congressionalchallenge.repositories;

import java.util.List;

import com.arjuninamdar.congressionalchallenge.models.sql.QuestionInfo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionInfoRepository extends CrudRepository<QuestionInfo, Integer> {
    public QuestionInfo findByQuestionIdAndIsEnabled(int questionId, boolean isEnabled);
    

    @Query(value="select * from question_info where question_id not in (select question_answered_id from answer_info) and is_enabled=true", 
    nativeQuery=true)
    public List<QuestionInfo> findAllUnanswered(); 

    
    public List<QuestionInfo> findByUserAskedIdAndIsEnabled(int userAskedId, boolean isEnabled); 

    //select * from question_info where question_id not in (select question_answered_id from answer_info) and is_enabled=true;
    
}
