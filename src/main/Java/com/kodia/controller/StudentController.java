package com.kodia.controller;

import com.kodia.dao.MainDAO;
import com.kodia.model.Student;
import com.kodia.model.University;
import com.kodia.schemas.*;
import com.kodia.service.StudentService;
import com.kodia.service.UniversityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private UniversityService universityService;
    @Autowired
    private StudentService studentService;

    private static Logger logger = LoggerFactory.getLogger(MainDAO.class);


    @GetMapping
    public ResponseEntity<?> students(){
        List<Student> studentList = studentService.getStudentList();
        // if student list is null, return 400(bad request)
        if (studentList.size() == 0)
            ResponseEntity.badRequest();

        List<StudentListItem> studentItemList = new ArrayList<>();
        for (Student student : studentList) {
            studentItemList.add(new StudentListItem(student.getId(),student.getName(),student.getUniversity().getName()));
        }
        logger.info(studentItemList.get(0).getName());
        return ResponseEntity.ok(studentItemList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> students(@PathVariable("id") int id){
        Student student = studentService.getStudentById(id);
        // if student is null, return 404(not found)
        if (student == null){
            StudentNotFound notFound = new StudentNotFound("error",id + " numaralı öğrenci kaydı bulunamadı");
            return ResponseEntity.status(404).body(notFound);
        }
        StudentDetailItem studentDetailItem = new StudentDetailItem();
        UniversityForStudentDetailItem university = new UniversityForStudentDetailItem(student.getUniversity().getId(),
                student.getUniversity().getName(),student.getUniversity().getFoundedAt(),student.getUniversity().getType());

        studentDetailItem.setId(student.getId());
        studentDetailItem.setName(student.getName());
        studentDetailItem.setStartedAt(student.getStartedAt());
        studentDetailItem.setUniversity(university);
        logger.info(studentDetailItem.getName());
        return ResponseEntity.ok(studentDetailItem);
    }

    @PostMapping
    public ResponseEntity<?> students(@RequestParam String name, @RequestParam String started_at, @RequestParam int university){
        University universityByApiId = universityService.getUniversityByApiId(university);
        Boolean isExist = true;
        if (universityByApiId == null)
            isExist = false;
        Student student = studentService.saveStudent(name, started_at, university);

        // if error occurs, return 422(unprocessable entity)
        if (student != null){
            // if university doesn't exist in database, create related university above and send request message
            if (!isExist)
                return ResponseEntity.ok(new CreateStudent(student.getName(),student.getStartedAt(),student.getUniversity().getApiId()));
            StudentSuccessPost studentSuccessPost = new StudentSuccessPost(student.getId(),"success",name + " adlı öğrenci Karabük Üniversitesine başarıyla eklendi.");
            return ResponseEntity.ok(studentSuccessPost);
        }else{
            StudentErrorPost studentErrorPost = new StudentErrorPost("error","Öğrenci eklenirken hata oluştu");
            return ResponseEntity.status(422).body(studentErrorPost);
        }
    }
}
