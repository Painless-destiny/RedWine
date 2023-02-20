package com.redwine.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

public class mybatisplusGenertorConfig {
    public static void main(String[] args) {

        // 1、创建代码生成器
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/RedWine", "root", "1234")

        // 2、全局配置
                .globalConfig(builder -> {
                    builder.author("wyh")
                            .outputDir(System.getProperty("user.dir") + "/src/main/java")   //设置输出路径：项目的 java 目录下
                            .commentDate("yyyy-MM-dd hh:mm:ss")   //注释日期
                            .dateType(DateType.ONLY_DATE)   //定义生成的实体类中日期的类型 TIME_PACK=LocalDateTime;ONLY_DATE=Date;
                            .fileOverride()   //覆盖之前的文件
                            .enableSwagger()   //开启 swagger 模式
                            .disableOpenDir();   //禁止打开输出目录，默认打开
                })
                .packageConfig(builder -> {
                    builder.parent("com")  // 设置父包名
                            .moduleName("redwine")  // 设置模块名
                            .entity("entity")  // 设置实体类包名
                            .service("service")  // 设置service包名
                            .serviceImpl("service/serviceImpl")  // 设置serviceImpl包名
                            .mapper("mapper")   //Mapper 包名
                            .xml("mapper")   //Mapper XML 包名
                            .controller("controller")  //Controller 包名
                            .other("utils")  //自定义文件包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapper.xml, System.getProperty("user.dir")+"/src/main/resources/mapper"));    //配置 mapper.xml 路径信息：项目的 resources 目录下
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user")
                            .addTablePrefix("t_", "c_") // 设置过滤表前缀

                    //4.1、Mapper策略配置
                            .mapperBuilder()
                            .superClass(BaseMapper.class)  //设置父类
                            .formatMapperFileName("%sMapper")  //格式化 mapper 文件名称
                            .enableMapperAnnotation()  //开启 @Mapper 注解
                            .formatXmlFileName("%sXml") //格式化 Xml 文件名称
                    //4.2、service 策略配置
                            .serviceBuilder()
                            .formatServiceFileName("%sService") //格式化 service 接口文件名称，%s进行匹配表名，如 UserService
                            .formatServiceImplFileName("%sServiceImpl") //格式化 service 实现类文件名称，%s进行匹配表名，如 UserServiceImpl
                    //4.3 实体类配置策略
                            .entityBuilder()
                            .enableLombok() //开启 Lombok
                            .disableSerialVersionUID()  //不实现 Serializable 接口，不生产 SerialVersionUID
                            .logicDeleteColumnName("delete")
                            .addTableFills(
                                    new Column("create_time", FieldFill.INSERT),
                                    new Column("modify_time", FieldFill.INSERT_UPDATE)
                            )   //添加表字段填充，"create_time"字段自动填充为插入时间，"modify_time"字段自动填充为插入修改时间
                            .enableTableFieldAnnotation()       // 开启生成实体时生成字段注解

                    //4.4、Controller策略配置
                            .controllerBuilder()
                            .formatFileName("%sController") //格式化 Controller 类文件名称，%s进行匹配表名，如 UserController
                            .enableRestStyle();  //开启生成 @RestController 控制器
                })
                .templateEngine(new VelocityTemplateEngine())

                .execute();


    }
}
