# Spring-Mybaits
通过配置生成代码
------------------------------------------------------------------------------------------------------------------------

1、在pom.xml的文件中添加配置文件

  <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <properties>
          <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
          <spring.version>4.3.17.RELEASE</spring.version>
          <mybatis.version>3.2.7</mybatis.version>
          <aspectj.version>1.7.3</aspectj.version>
          <jdk.version>1.8</jdk.version>
      </properties>

      <build>
          ..... 其他的配置信息
          <plugins>
              <!-- mybaits的快捷方式 -->
              <plugin>
                  <groupId>org.mybatis.generator</groupId>
                  <artifactId>mybatis-generator-maven-plugin</artifactId>
                  <version>1.3.2</version>
                  <configuration>
                      <verbose>true</verbose>
                      <overwrite>true</overwrite>
                  </configuration>
              </plugin>
          <plugins>
      </dependencies>
  </project>
  
------------------------------------------------------------------------------------------------------------------------

2、在resource中添加要配置的信息

 1）、配置数据库的信息：（project.properties）
  #数据库链接配置
  jdbc.url=
  jdbc.driverLocation=/Users/dli/.m2/repository/mysql/mysql-connector-java/5.1.30/mysql-connector-java-5.1.30.jar
  jdbc.driverClassName=com.mysql.jdbc.Driver
  #数据库主机地址
  jdbc.host=127.0.0.1
  #数据库名
  jdbc.database=demo
  #用户名
  jdbc.username=root
  #密码
  jdbc.password=root
  
 2)、配置要生成的信息（generatorConfig.xml）
  <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE generatorConfiguration
          PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
          "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

    <generatorConfiguration>
      <!--导入属性配置-->
      <properties resource="project.properties"/>

      <!--指定特定数据库的jdbc驱动jar包的位置-->
      <classPathEntry location="${jdbc.driverLocation}"/>

      <context id="default" targetRuntime="MyBatis3">

          <!-- optional，旨在创建class时，对注释进行控制 -->
          <commentGenerator>
              <property name="suppressDate" value="true"/>
              <property name="suppressAllComments" value="true"/>
          </commentGenerator>

          <!--jdbc的数据库连接 -->
          <jdbcConnection
                  driverClass="${jdbc.driverClassName}"
                  connectionURL="${jdbc.url}"
                  userId="${jdbc.username}"
                  password="${jdbc.password}">
          </jdbcConnection>


          <!-- 非必需，类型处理器，在数据库类型和java类型之间的转换控制 -->
          <javaTypeResolver>
              <property name="forceBigDecimals" value="false"/>
          </javaTypeResolver>


          <!-- Model模型生成器,用来生成含有主键key的类，记录类 以及查询Example类
              targetPackage     指定生成的model生成所在的包名
              targetProject     指定在该项目下所在的路径
          -->
          <javaModelGenerator targetPackage="com.inxedu.os.edu.entity" targetProject="./src/main/java">
              <!-- 是否允许子包，即targetPackage.schemaName.tableName -->
              <property name="enableSubPackages" value="false"/>
              <!-- 是否对model添加 构造函数 -->
              <property name="constructorBased" value="true"/>
              <!-- 是否对类CHAR类型的列的数据进行trim操作 -->
              <property name="trimStrings" value="true"/>
              <!-- 建立的Model对象是否 不可改变  即生成的Model对象不会有 setter方法，只有构造方法 -->
              <property name="immutable" value="false"/>
          </javaModelGenerator>

          <!--mapper映射文件生成所在的目录 为每一个数据库的表生成对应的SqlMap文件 -->
          <sqlMapGenerator targetPackage="index" targetProject="./src/main/resources">
              <property name="enableSubPackages" value="false"/>
          </sqlMapGenerator>

          <!-- targetPackage：mapper接口dao生成的位置 -->
          <javaClientGenerator type="XMLMAPPER" targetPackage="com.inxedu.os.edu.dao" targetProject="./src/main/java">
              <!-- enableSubPackages:是否让schema作为包的后缀 -->
              <property name="enableSubPackages" value="false"/>
          </javaClientGenerator>


          <table tableName="user" domainObjectName="User" enableCountByExample="false" enableUpdateByExample="false"
                 enableDeleteByExample="false" enableSelectByExample="false" selectByExampleQueryId="false"/>
          <!-- geelynote mybatis插件的搭建 -->
      </context>
    </generatorConfiguration>
   
  ------------------------------------------------------------------------------------------------------------------------
  
  3、点击【Maven Project】->【Plugins】->【mybatis-generator】，右击出现【Run Maven Build】，信息的就会出来了
