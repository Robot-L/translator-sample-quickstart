package com.robot.entity;

import com.robot.translator.core.annotation.Dictionary;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Class)班级
 * @author luozhan
 */
@Data
@NoArgsConstructor
@Dictionary(codeColumn = "id", textColumn = "name")
public class Class {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 班级名称
     */
    private String name;

}