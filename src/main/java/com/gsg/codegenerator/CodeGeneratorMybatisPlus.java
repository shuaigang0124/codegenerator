package com.gsg.codegenerator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * TODO mybatis-plus代码生成类
 *
 * @author shuaigang
 * @date 2022/12/6 15:35
 */
public class CodeGeneratorMybatisPlus {

    /**
     * TODO 是否生成相关文件：true  or  false
     * 是否生成实体类
     * 是否生成mapper及其xml映射文件
     * 是否生成业务类及其实现类
     * 是否生成控制层
     */
    public static Boolean[] or = {true, true, true, true};

    /**
     * 【注意】需要改成自己的MySQL/MariaDB的用户名
     */
    public static String username = "root";

    /**
     * 【注意】需要改成自己的MySQL/MariaDB的密码
     */
    public static String password = "";

    /**
     * 【请确认】父级别包名称
     */
    public static String parentPackage = "com.gsg";

    /**
     * 【请确认】模块名称，用于组成包名，将和上面的parentPackage组成完整的包名
     */
    public static String modelName = "blog";

    /**
     * 【请确认】数据库连接参数，需要注意数据库地址、名称是否需要修改
     */
    public static String url = "jdbc:mysql://127.0.0.1:3306/blog?useAffectedRows=true&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true";

    /**
     * 连接数据库的驱动类
     */
    public static String driver = "com.mysql.cj.jdbc.Driver";

    public static String projectPath = "E:\\a-projects\\blog-backEnd";

    /**
     * 代码生成的目标路径
     */
    public static String generateTo = projectPath + "\\src\\main\\java";

    /**
     * mapper.xml的生成路径
     */
    public static String mapperXmlPath = projectPath + "\\src\\main\\resources\\mapper";

    /**
     * Mapper接口的模板文件，不用写后缀 .ftl
     */
    public static String mapperTemplate = "/mapper.java";

    /**
     * 控制器的公共基类，用于抽象控制器的公共方法，null值表示没有父类
     */
    public static String baseControllerClassName;

    /**
     * 业务层的公共基类，用于抽象公共方法
     */
    public static String baseServiceClassName;

    /**
     * 作者名
     */
    public static String author = "shuaigang";

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(generateTo);
        gc.setAuthor(author);
        gc.setOpen(false);
        //设置时间类型为Date
        gc.setDateType(DateType.ONLY_DATE);

        //设置mapper.xml的resultMap
        gc.setBaseResultMap(true);
        mpg.setGlobalConfig(gc);

        if (or[1]) {
            // 自定义配置mapper输出文件路径
            InjectionConfig cfg = new InjectionConfig() {
                @Override
                public void initMap() {
                    // to do nothing
                }
            };
            List<FileOutConfig> focList = new ArrayList<>();
            focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称: 生成到该项目下
//                return projectPath + mapperXmlPath
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                    // 生成到自定盘目录下
                    return  mapperXmlPath
                            + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            });
            cfg.setFileOutConfigList(focList);
            mpg.setCfg(cfg);
            mpg.setTemplate(new TemplateConfig().setXml(null));
            mpg.setTemplate(new TemplateConfig().setMapper(mapperTemplate));
        }

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setDriverName(driver);
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setEntity("entity");
        pc.setModuleName(modelName);
        pc.setParent(parentPackage);
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 字段驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 设置实体类的lombok
        strategy.setEntityLombokModel(true);

        // 设置controller的父类
        if (baseControllerClassName != null) {
            strategy.setSuperControllerClass(baseControllerClassName);
        }
        // 设置服务类的父类
        if (baseServiceClassName != null) {
            strategy.setSuperServiceImplClass(baseServiceClassName);
        }

        // 设置实体类属性对应表字段的注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        // 设置表名
        String tableName = scanner("表名, all全部表(示例：table01,table02,table03)(注意：表明中字段不要与modelName相同)");
        if (!",".contains(tableName)) {
            strategy.setInclude(tableName.split(","));
        }
        strategy.setTablePrefix(pc.getModuleName() + "_");
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);

        TemplateConfig templateConfig = new TemplateConfig();

        if (!or[0]) {
            templateConfig.setEntity(null);
        }
        if (!or[1]) {
            templateConfig.setMapper(null);
//            templateConfig.setXml(null);
        }
        // 设置不生成默认的xml文件
        templateConfig.setXml(null);
        if (!or[2]) {
            templateConfig.setService(null);
            templateConfig.setServiceImpl(null);
        }
        if (!or[3]) {
            templateConfig.setController(null);
        }

        mpg.setTemplate(templateConfig);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
