package com.robot.entity;

import com.robot.dict.MyDict;
import com.robot.translator.MyAgeTranslator;
import com.robot.translator.core.annotation.Dictionary;
import com.robot.translator.core.annotation.Translate;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Student)学生
 * @author luozhan
 */
@Data
public class Student {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;
    /**
     * 班级
     */
    @Translate(Class.class)
    private String classId;
    private String className;
    /**
     * 性别
     */
    // 静态字典翻译和枚举翻译，都可以支持
    // @Translate(dictClass = StaticDict.class, groupValue = "sex")
    @Translate(MyDict.SexDict.class)
    private String sex;
    private String sexName;
    /**
     * 年龄
     */
    @Translate(dictionary = @Dictionary(translator = MyAgeTranslator.class), translateField = "tag")
    private Integer age;
    /**
     * 年龄标签，由年龄决定
     */
    private String tag;


}