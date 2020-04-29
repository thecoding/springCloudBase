package com.springcloudbase.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


/**
 * Created by Mirko on 2020/4/29.
 * 用户注册信息
 * 测试Hibernate Validator 框架
 */
@Data
public class UserRegisterInfo implements Serializable {


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
    @Min(value = 18,message = "年龄不在允许范围内")
    @Max(value = 50,message = "年龄不在允许范围内")
    @NotNull(message = "年龄不能为空")
    private Integer age;

    /**
     * 身份证
     */
    @Length.List(value = {@Length(min = 15, max = 15,message = "15位"), @Length(min = 18, max = 18,message = "18位")})
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
