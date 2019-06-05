package com.kodia.service;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodia.dao.MainDAO;
import com.kodia.model.Student;
import com.kodia.model.University;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
public class StudentService {

    @Autowired
    private MainDAO mainDAO;

    // get all students
    @Transactional
    public List<Student> getStudentList(){
        return mainDAO.getStudentList();
    }

    // get student by id
    @Transactional
    public Student getStudentById(int id){
        return mainDAO.getStudentById(id);
    }

    /* save student method
    * if university according to api id doesn't exist in database, save related university to database from json.
    * json url = https://gitlab.com/kodiasoft/intern/2019/snippets/1859421/raw
    */
    @Transactional
    public Student saveStudent(String name, String started_at, int university){
        University universityByApiId = mainDAO.getUniversityByApiId(university);

        University u = null;
        Locale locale= new Locale("tr", "TR");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        Date createdDate = null;
        Date updatedDate = null;
        Date startedDate = null;
        try{
            // get current date
            String current = formatter.format(currentDate);
            // assign current date to related date parameters
            createdDate = formatter.parse(current);
            updatedDate = formatter.parse(current);
            startedDate = formatter.parse(started_at);
        }catch (Exception e){
            e.printStackTrace();
        }

        // if api id incoming from university parameter on method doesn't exist in database, then save it
        if (universityByApiId==null){
            String jsonUrl = "https://gitlab.com/kodiasoft/intern/2019/snippets/1859421/raw";

            try{
                JSONArray jsonArray=new JSONArray(readUrl(jsonUrl));
                u = new University();

                JSONObject jsonObject=jsonArray.getJSONObject(university);

                String foundedYear = jsonObject.getString("founded_at") + "-01-01";
                Date foundedDate = formatter.parse(foundedYear);

                u.setId(0);
                u.setApiId(jsonObject.getInt("id"));
                u.setName(jsonObject.getString("name"));
                u.setCity(jsonObject.getString("city"));
                u.setFoundedAt(foundedDate);
                u.setType(jsonObject.getString("type"));
                u.setWebPage(jsonObject.getString("web_page"));
                u.setCreatedAt(createdDate);
                u.setUpdatedAt(updatedDate);

                Boolean success = mainDAO.saveOrUpdateObject(u);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        // save student
        University uni = mainDAO.getUniversityByApiId(university);
        Student s = new Student();
        s.setId(0);
        s.setName(name);
        s.setStartedAt(startedDate);
        s.setUniversity(uni);
        s.setCreatedAt(createdDate);
        s.setUpdatedAt(updatedDate);
        Boolean success = mainDAO.saveOrUpdateObject(s);
        return s;
    }

    // read json string from url
    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName("UTF-8")));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }


}
