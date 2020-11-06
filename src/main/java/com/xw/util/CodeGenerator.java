
package com.xw.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.xw.bean.DataSource;
import com.xw.bean.DataSourceInfo;
import com.xw.bean.GeneratorFrom;

import java.util.ArrayList;
import java.util.List;


/**
 * mybatis plus 提供的代码生成器
 * 可以快速生成 Entity、Mapper、Mapper XML、Service、Controller 等各个模块的代码
 *
 * @link https://mp.baomidou.com/guide/generator.html
 */


public class CodeGenerator {
    public static void generator(GeneratorFrom generatorFrom,String table) {
        AutoGenerator generator = new AutoGenerator();
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setActiveRecord(generatorFrom.isActiveRecord()); //开启AR模式
        globalConfig.setOutputDir(generatorFrom.getProjectPath()+generatorFrom.getBuildPath()); //生成路径(一般都是生成在此项目的src/main/java下面)
        globalConfig.setAuthor(generatorFrom.getAuthor()); //设置作者
        globalConfig.setFileOverride(generatorFrom.isFileOverride());//第二次生成会把第一次生成的覆盖掉
        globalConfig.setServiceName("%sService");//生成的service接口名字首字母是否为I，这样设置就没有I
        globalConfig.setBaseResultMap(generatorFrom.isBaseResultMap());//生成resultMap
        globalConfig.setBaseColumnList(generatorFrom.isBaseColumnList());//在xml中生成基础列
        globalConfig.setIdType(IdType.AUTO);//主键策略
        globalConfig.setSwagger2(generatorFrom.isSwagger2()); // 实体属性 Swagger2 注解
        globalConfig.setOpen(false);
        generator.setGlobalConfig(globalConfig);

        // 数据源配置

        DataSourceInfo dataSourceInfo = DataSource.dataSourceInfoMap.get(generatorFrom.getDataSource());
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(dataSourceInfo.getUrl());
        dataSourceConfig.setDriverName(dataSourceInfo.getDriverName());
        dataSourceConfig.setUsername(dataSourceInfo.getUsername());
        dataSourceConfig.setPassword(dataSourceInfo.getPassword());
        generator.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName(generatorFrom.getModuleName()); //模块名
        packageConfig.setParent(generatorFrom.getParent());
        generator.setPackageInfo(packageConfig);


        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(com.baomidou.mybatisplus.generator.config.po.TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return generatorFrom.getProjectPath() + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        generator.setCfg(cfg);

        // 配置自定义代码模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        generator.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);//下划线到驼峰的命名方式
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //strategy.setSuperEntityColumns("id"); // 写于父类中的公共字段
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("");
        generator.setStrategy(strategy);
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        strategy.setInclude(table);
        generator.execute();
    }
}


