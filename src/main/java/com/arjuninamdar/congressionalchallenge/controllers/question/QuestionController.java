package com.arjuninamdar.congressionalchallenge.controllers.question;


import java.util.List;

import com.arjuninamdar.congressionalchallenge.models.sql.QuestionInfo;
import com.arjuninamdar.congressionalchallenge.repositories.QuestionInfoRepository;
import com.arjuninamdar.congressionalchallenge.security.CustomUserDetails;
import com.arjuninamdar.congressionalchallenge.util.FileUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    FileUtil fileUtil; 

    @Autowired
    QuestionInfoRepository questionInfoRepository; 

    public QuestionInfo getQuestionSecurity(int questionId) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        QuestionInfo question = questionInfoRepository.findByQuestionIdAndIsEnabled(questionId, true); 
        if(question == null || question.getUserAskedId() != userDetails.getId())
            return null;

        return question;
    }

    
    @PostMapping("/add")
    public ResponseEntity<?> addQuestion(@RequestParam("file") MultipartFile file, @RequestParam String question) throws Exception {
        try {
            CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String fileName = fileUtil.saveFile(file, userDetails.getId()); 

            QuestionInfo questionInfo = new QuestionInfo(userDetails.getId(), question, fileName, java.time.LocalDateTime.now().toString(), true); 
            questionInfoRepository.save(questionInfo); 

            return ResponseEntity.ok("QUESTION_SAVED"); 
        
        } catch (Exception e) {
           System.out.println(e); 
           return ResponseEntity.status(500).body("INTERNAL_SERVER_ERROR"); 
        }
    }


    @GetMapping("/userquestions")
    public ResponseEntity<?> getAllQuestionsById() {
        try {
            CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<QuestionInfo> allQuestions = questionInfoRepository.findByUserAskedIdAndIsEnabled(userDetails.getId(), true); 
            return ResponseEntity.ok(allQuestions); 

        } catch(Exception e) {
            System.out.println(e); 
            return ResponseEntity.status(500).body("INTERNAL_SERVER_ERROR"); 
        }
    }


    @GetMapping("/allunanswered")
    public ResponseEntity<?> getAllUnansweredQuestions() {
        try {
            List<QuestionInfo> unansweredQuestions = questionInfoRepository.findAllUnanswered(); 
            return ResponseEntity.ok(unansweredQuestions); 

        } catch(Exception e) {
            System.out.println(e); 
            return ResponseEntity.status(500).body("INTERNAL_SERVER_ERROR"); 
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getQuestionById(@RequestParam int questionId) {
        try {
            QuestionInfo question = getQuestionSecurity(questionId); 

            if(question == null) 
                return ResponseEntity.status(404).body("NOT_FOUND"); 

            return ResponseEntity.ok(question); 


        } catch (Exception e) {
            System.out.println(e) ; 
            return ResponseEntity.status(500).body("INTERNAL_SERVER_ERROR"); 
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteQuestionById(@RequestParam int questionId) {
        try {
            QuestionInfo question = getQuestionSecurity(questionId); 

            if(question == null) 
                return ResponseEntity.status(404).body("NOT_FOUND"); 

                question.setIsEnabled(false);
                questionInfoRepository.save(question); 
    
                return ResponseEntity.ok("QUESTION_DELETED"); 


        } catch (Exception e) {
            System.out.println(e) ; 
            return ResponseEntity.status(500).body("INTERNAL_SERVER_ERROR"); 
        }
    }
}
