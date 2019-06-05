package com.kodia.service;

import com.kodia.dao.MainDAO;
import com.kodia.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
public class UniversityService {

    @Autowired
    private MainDAO mainDAO;

    // get all universities
    @Transactional
    public List<University> getUniversityList(){
        return mainDAO.getUniversityList();
    }

    // get university by id
    @Transactional
    public University getUniversityById(int id){
        return mainDAO.getUniversityById(id);
    }

    @Transactional
    public University getUniversityByApiId(int id){
        return mainDAO.getUniversityByApiId(id);
    }

    // save or update university
    @Transactional
    public Boolean saveOrUpdateUniversity(University university){
        University u = mainDAO.getUniversityById(university.getId());

        if (u == null) {
            u = new University();
            u.setId(university.getId());
        }
        u.setName(university.getName());
        u.setCity(university.getCity());
        u.setFoundedAt(university.getFoundedAt());
        u.setType(university.getType());
        u.setWebPage(university.getWebPage());
        u.setCreatedAt(university.getCreatedAt());
        u.setUpdatedAt(university.getUpdatedAt());

        return mainDAO.saveOrUpdateObject(u);
    }

}
