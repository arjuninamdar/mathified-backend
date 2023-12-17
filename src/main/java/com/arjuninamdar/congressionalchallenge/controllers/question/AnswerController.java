package com.arjuninamdar.congressionalchallenge.controllers.question;

import com.arjuninamdar.congressionalchallenge.models.http.AnswerRequest;
import com.arjuninamdar.congressionalchallenge.models.sql.AnswerInfo;
import com.arjuninamdar.congressionalchallenge.models.sql.QuestionInfo;
import com.arjuninamdar.congressionalchallenge.repositories.AnswerInfoRepository;
import com.arjuninamdar.congressionalchallenge.repositories.QuestionInfoRepository;
import com.arjuninamdar.congressionalchallenge.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    AnswerInfoRepository answerInfoRepository; 

    @Autowired
    QuestionInfoRepository questionInfoRepository; 

    @Autowired
    QuestionController questionController; 


    @PostMapping("/add")
    public ResponseEntity<?> addQuestion(@RequestBody AnswerRequest answerRequest){
        try {
            CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            QuestionInfo question  = questionInfoRepository.findByQuestionIdAndIsEnabled(answerRequest.getQuestionId(), true); 

            if(question == null)
                return ResponseEntity.status(404).body("NOT_FOUND"); 

            AnswerInfo answerInfo = new AnswerInfo(userDetails.getId(), answerRequest.getAnswer(), question.getQuestionId(), java.time.LocalDateTime.now().toString()); 
            answerInfoRepository.save(answerInfo); 

            return ResponseEntity.ok("ANSWER_SAVED"); 

        } catch(Exception e) {
            System.out.println(e); 
            return ResponseEntity.status(500).body("INTERNAL_SERVER_ERROR"); 
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllByQuestionId(@RequestParam int questionId){
        try {
            
            QuestionInfo questionInfo = questionController.getQuestionSecurity(questionId); 

            if(questionInfo == null) 
                return ResponseEntity.status(404).body("NOT_FOUND");

            return ResponseEntity.ok(answerInfoRepository.findByQuestionAnsweredIdAndIsEnabled(questionId, true)); 

        } catch (Exception e) {
            System.out.println(e); 
            return ResponseEntity.status(500).body("INTERNAL_SERVER_ERROR"); 
        }
    }
    
}
