package com.robot.service;

import com.robot.entity.Student;
import com.robot.translator.core.annotation.Translator;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 学生服务类
 *
 * @see Translator
 * @author luozhan
 * @create 2020-04
 */
@Service
public class StudentService {
    @Resource
    JdbcTemplate template;

    /**
     * 查询所有学生信息(返回值为List<Map>)
     * 1.指定翻译配置类Student.class
     */
    @Translator(Student.class)
    public List<Map<String, Object>> queryAllStudentMap(){
        return template.queryForList("select * from student");
    }

    /**
     * 查询所有学生信息(返回值为List<Student>)
     * 2.因为返回值中是Student元素，所以可以省略Student.class
     */
    @Translator
    public List<Student> queryAllStudents(){
        return template.query("select * from student", new BeanPropertyRowMapper<>(Student.class));
    }

}