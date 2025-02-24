package com.green.springjpa.dummy;

import com.green.springjpa.entity.School;
import com.green.springjpa.entity.SchoolTypeCode;
import com.green.springjpa.entity.Student;
import com.green.springjpa.entity.StudentGradeTypeCode;
import com.green.springjpa.school.SchoolRepository;
import com.green.springjpa.student.StudentRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Locale;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentDummyGenerator {
    @Autowired private StudentRepository studentRepository;
    @Autowired private SchoolRepository schoolRepository;

    Faker faker = new Faker(new Locale("ko"));

    RandomEnumGenerator<StudentGradeTypeCode> studentGradeTypeCodeRandomEnumGenerator
            = new RandomEnumGenerator<>(StudentGradeTypeCode.class);

    @Test
    @Rollback(false)
    void generate() {
        int needForCount = 1000;
        //기존 데이터 삭제
        studentRepository.deleteAll();

        List<School> schoolList = schoolRepository.findAll();
        if(schoolList.size() == 0) { return; }

        for (int i = 0; i < needForCount; i++) {
            StringBuilder sb = new StringBuilder(faker.name().lastName());
            sb.append(faker.name().firstName());

            Student student = Student.builder()
                    .name(sb.toString())
                    //.school( schoolList.get( (int)( Math.random() * schoolList.size() ) ) )
                    .school( schoolList.get( faker.random().nextInt(schoolList.size()) ) )
                    .gradeTypeCode( studentGradeTypeCodeRandomEnumGenerator.getRandomEnum() )
                    .build();

            studentRepository.save(student);
        }
        studentRepository.flush();
    }

    @Test
    void fakerRandom() {

        for(int i=0; i<100; i++) {
            System.out.println(faker.random().nextInt(10));
        }
    }
}
