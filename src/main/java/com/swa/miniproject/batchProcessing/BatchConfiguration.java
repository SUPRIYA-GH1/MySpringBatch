package com.swa.miniproject.batchProcessing;

import com.swa.miniproject.entity.Student;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired private StepBuilderFactory stepBuilderFactory;

    @Autowired private JobBuilderFactory jobBuilderFactory;

    @Autowired private DataSource dataSource;
    @Bean
    public FlatFileItemReader<Student> itemReader(){
        FlatFileItemReader<Student> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new ClassPathResource("students.csv"));
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }

    @Bean
    public LineMapper<Student> lineMapper() {

        DefaultLineMapper<Student> defaultLineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer dlt = new DelimitedLineTokenizer();
        dlt.setDelimiter(",");
        dlt.setStrict(false);
        dlt.setNames(new String[]{"firstName", "lastName", "gpa", "age"});
        defaultLineMapper.setLineTokenizer(dlt);

        BeanWrapperFieldSetMapper<Student> bwfsm = new BeanWrapperFieldSetMapper<>();
        bwfsm.setTargetType(Student.class);
        defaultLineMapper.setFieldSetMapper(bwfsm);

        return defaultLineMapper;
    }

    @Bean
    public JdbcBatchItemWriter<Student> writerToDB(){
        JdbcBatchItemWriter<Student> jbiw = new JdbcBatchItemWriter<>();
        jbiw.setDataSource(dataSource);
        jbiw.setSql("INSERT INTO student (first_name, last_name,gpa,dob) values(:firstName,:lastName,:gpa,:dob)");
        jbiw.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        return jbiw;
    }

    @Bean
    public StudentProcessor itemProcessor() {
        return new StudentProcessor();
    }

    @Bean
    public Step step(){
         return stepBuilderFactory.get("step").<Student,Student>chunk(10)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(writerToDB())
                .build();
    }

    @Bean
    public Job job(){
        return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer()).flow(step()).end().build();
    }


}
