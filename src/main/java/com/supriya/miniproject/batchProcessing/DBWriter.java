package com.supriya.miniproject.batchProcessing;

import com.supriya.miniproject.entity.Student;
import com.supriya.miniproject.repository.StudentRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<Student> {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void write(List<? extends Student> students) throws Exception {
        System.out.println("======= "+students);
        studentRepository.saveAll(students);
    }
}
