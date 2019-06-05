package com.kodia.controller;

import com.kodia.dao.MainDAO;
import com.kodia.model.Student;
import com.kodia.model.University;
import com.kodia.schemas.UniversityDetailItem;
import com.kodia.schemas.UniversityListItem;
import com.kodia.schemas.UniversityNotFound;
import com.kodia.schemas.UniversityStudentListItem;
import com.kodia.service.UniversityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    private static Logger logger = LoggerFactory.getLogger(MainDAO.class);

    @GetMapping
    public ResponseEntity<?> universities(){
        List<University> universityList = universityService.getUniversityList();
        // if university list is null, return 400(bad request)
        if (universityList.size() == 0)
            ResponseEntity.badRequest();

        List<UniversityListItem> universityItemList= new ArrayList<>();

        for (University u : universityList) {
            universityItemList.add(new UniversityListItem(u.getId(),u.getName()));
        }

        logger.info(universityItemList.get(0).getName());
        return ResponseEntity.ok(universityItemList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> universities(@PathVariable("id") int id){

        University university = universityService.getUniversityById(id);
        // if university list is null, return 404(not found)
        if (university != null){
            UniversityDetailItem uni = new UniversityDetailItem();
            List<UniversityStudentListItem> uniStudentList = new ArrayList<>();

            uni.setId(university.getId());
            uni.setApiId(university.getApiId());
            uni.setName(university.getName());
            uni.setCity(university.getCity());
            uni.setFoundedAt(university.getFoundedAt());
            uni.setWebPage(university.getWebPage());
            uni.setType(university.getType());
            for (Student student : university.getStudentList()) {
                uniStudentList.add(new UniversityStudentListItem(student.getId(),student.getName(),student.getStartedAt()));
            }
            uni.setStudents(uniStudentList);

            logger.info(uni.getName());
            return ResponseEntity.ok(uni);
        }else{
            UniversityNotFound notFound = new UniversityNotFound("error",id + " numaralı üniversite kaydı bulunamadı");
            return ResponseEntity.status(404).body(notFound);
        }
    }
}
