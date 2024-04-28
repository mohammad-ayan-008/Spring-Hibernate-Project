package org.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;


public class DAO {

    @Autowired
    private HibernateTemplate template;

    public DAO(HibernateTemplate template) {
        this.template = template;
    }

    @Transactional(readOnly=false)
    public Integer insert(Student2 std) {
        Integer result = (Integer) template.save(std);
        template.flush();
        return result;
    }
   
    public DAO(){}
    
    public Student2 getStudent(int id){
        return template.get(Student2.class,id);
    }
    
    public List<Student2> getStudents(){
        return template.loadAll(Student2.class);
    }
  
    @Transactional(readOnly=false)
    public void Delete(int id){
        Student2 std=(Student2) template.get(Student2.class,id);
        template.delete(std);
    }
    
    @Transactional(readOnly=false)
    public void update(Student2 std){
        template.update(std);
    }
    
    
}

