package com.swa.miniproject.batchProcessing;

import com.swa.miniproject.entity.Student;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
public class StudentProcessor implements ItemProcessor<Student,Student> {

    private static final Map<String, LocalDate> DOB= new HashMap<>();

    public StudentProcessor(){
    }


    @Override
    public Student process(Student student) throws Exception {

        Integer age = student.getAge();
        int year = LocalDate.now().getYear() - age;
        LocalDate dob = LocalDate.of(year,1,1);
        student.setDob(dob);

        System.out.println("Converted from "+student.getAge()+" ::: "+dob);
        return student;
    }
}
