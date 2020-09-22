package com.springcloudbase.webserver.validator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * Created by Mirko on 2020/5/1.
 */
@Getter
@Setter
@ToString
public class Person {


    /**
     * 姓名
     * {@link javax.validation.constraints.NotNull} 被注释的元素必须不为 null
     * {@link javax.validation.constraints.Null} 被注释的元素必须为 null
     * {@link javax.validation.constraints.NotBlank} 被注释的字符串非 null，并且必须包含一个非空白字符
     * {@link javax.validation.constraints.NotEmpty} 被注释的字符串的不能为 null 也不能为空
     */
    @NotEmpty(message = "姓名不能为空")
    private String name;

    /**
     * 年龄 在18~50之间
     */
    @Min(value = 18,message = "年龄不能小于18")
    @Max(value = 50,message = "年龄不能大于50")
    @NotNull(message = "年龄不能为空")
    private Integer age;

    /**
     * 身份证
     */
    @NotNull(message = "身份证不能为空")
    private String idCard;

    /**
     * 性别
     */
    @NotNull
    private Integer sex;

    /**
     * 出生日期
     */
    @Past(message = "时间不能大于当前时间")
    private Date birthDay;

    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 合同到期时间
     */
    @Future(message = "时间只能大于当前时间")
    private Date endContractDate;

    /**
     * 工资
     */
    @Digits(integer = 11,fraction = 2)
    private Double salary;

    /**
     * 是否能加班
     */
    @AssertTrue(message = "需要接受加班")
    private boolean canOverTime;
}
