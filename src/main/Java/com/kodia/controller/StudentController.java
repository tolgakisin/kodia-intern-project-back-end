package com.kodia.controller;

import com.kodia.dao.MainDAO;
import com.kodia.model.Student;
import com.kodia.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    private static Logger logger = LoggerFactory.getLogger(MainDAO.class);

    @GetMapping
    public List<Student> students(){
        List<Student> studentList = studentService.getStudentList();
        logger.info(studentList.get(0).getName());
        return studentList;
    }

    @GetMapping(value = "/{id}")
    public Student students(@PathVariable("id") int id){
        Student student = studentService.getStudentById(id);
        logger.info(student.getName());
        return student;
    }

    @PostMapping
    public Boolean students(@RequestParam String name,@RequestParam String started_at,@RequestParam int university){
        Boolean success = studentService.saveStudent(name, started_at, university);
        logger.info(success.toString());
        return success;
    }
}
