package com.robot;

import com.robot.entity.Student;
import com.robot.service.StudentService;
import com.robot.translator.core.Translator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SampleTest {
    @Resource
    JdbcTemplate template;

    @Resource
    StudentService studentService;

    @Test
    public void testTranslate() {

        // 1.List<Map>翻译
        List<Map<String, Object>> maps = template.queryForList("select * from student");
        Translator.parse(maps, Student.class);
        maps.forEach(System.out::println);
        // {id=1, name=张三, class_id=1, sex=0, age=18, class_name=三年一班, sex_name=男}
        // {id=2, name=李四, class_id=3, sex=1, age=20, class_name=三年三班, sex_name=女}
        // {id=3, name=周杰伦, class_id=2, sex=0, age=38, class_name=三年二班, sex_name=男}

        // 2.List<entity>翻译
        List<Student> students = template.query("select * from student", new BeanPropertyRowMapper<>(Student.class));
        Translator.parse(students);
        students.forEach(System.out::println);
        // Student(id=1, name=张三, classId=1, className=三年一班, sex=0, sexName=男, age=18)
        // Student(id=2, name=李四, classId=3, className=三年三班, sex=1, sexName=女, age=20)
        // Student(id=3, name=周杰伦, classId=2, className=三年二班, sex=0, sexName=男, age=38)

        // 3.单个Entity翻译
        Student student = template.queryForObject("select * from student where id=3", new BeanPropertyRowMapper<>(Student.class));
        Translator.parse(student);
        System.out.println(student);

        // 4.单个Map翻译
        Map<String, Object> studentMap = template.queryForMap("select * from student where id=3");
        Translator.parse(studentMap, Student.class);
        System.out.println(studentMap);

    }

    @Test
    public void testAop() {
        System.out.println(studentService.queryAllStudentMap());
        List<Student> students = studentService.queryAllStudents();
        students.forEach(System.out::println);
    }
}