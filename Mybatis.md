#Mybatis
    原始jdbc操作分析
        原始jdbc存在的问题：
            1.数据库连接创建、释放频繁造成系统资源浪费影响系统性能
            2.SQL语句在代码中硬编码，造成代码不便于维护，实际应用SQL变化的可能较大，SQL变动需要改变java代码
            3.查询操作时，需要手动将结果集中的数据手动封装到实体中。插入操作时，需要手动将实体的数据设置到SQL
            语句的站位符位置
        解决方案：
            1.使用数据库连接池初始化连接资源
            2.将SQL语句抽取到xml配置文件中
            3.使用反射、内省等底层技术，自动将实体与表进行属性与字段的自动映射    
    Mybatis：
        1.优秀的基于java的持久层框架，内部封装jdbc，只需要关注SQL语句本身，不需要处理加载驱动、创建连接、创建statement的繁杂过程
        2.mybatis通过xml或注解的方式将要执行的各种statement配置起来，通过java对象和statement中的SQL的动态参数进行映射生成最终执行
        的SQL语句
        3.mybatis框架执行SQL并将结果映射为java对象 并返回。采用ORM思想解决实体和数据库映射的问题。对jdbc进行封装，屏蔽jdbc api 底层访问
        细节，不用与jdbc api打交道，就可以完成数据库的持久化操作。  
    
    mybatis快速入门：
        1.添加Mybatis坐标
        2.创建数据库表（user表）
        3.创建实体类（user实体类）
        4.编写映射文件（UserMapping.xml）          
        5.编写核心文件（SqlMapConfig.xml）
        6.编写测试类:(更新操作需要提交事务)
            //加载核心配置文件
            InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
            //获取sqlSession工厂对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            //获取sqlSession对象
            SqlSession sqlSession = sqlSessionFactory.openSession();
            //执行SQL语句
            List<User> userList = sqlSession.selectList("userMapper.findAll");
            //释放资源
            sqlSession.close();
            
            *插入操作
                插入语句使用insert标签
                在映射文件中使用parameterType属性指定要插入的数据类型
                Sql语句中使用#{实体属性名}方式引用实体中的属性值
                插入操作使用的api是sqlSession.insert("命名空间.id",实体对象)
                插入操作涉及数据库数据变化，要使用sqlSession对象显示的提交事务，即sqlSession.commit()
            *修改操作
                update标签
                api：sqlSession.update("命名空间.id",实体对象);    
            *删除操作
                delete标签
                SQL语句中使用#{任意字符串}方式引用传递的单个参数 <!--#{id} id可以随便命名 标签内不能使用/**/注释-->
                api：sqlSession.delete("命名空间.id",实体对象);    
            
    mybatis映射文件
        1.映射文件DTD约束头
            <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
        2.mapper
            根标签
            <mapper namespace=""(命名空间，与下面语句的id一起组成查询的标识)>    
                <select id=""(与命名空间组成查询标识) resultType=""(查询结果对应的实体类型)>
                    select * from xxx（要执行的sql语句）
                </select>
                <insert></insert>
                <update></update>
                <delete></delete>
            </mapper>   
        *深入：
            1.动态SQL语句
                if标签：根据实体类的不同取值，使用不同的SQL语句查询，通过if标签判断是否作为查询条件查询。
                where相当于SQL语句中where 1=1
                <where>                
                    <if test="判断条件">
                   
                    </if>
                </where>
                foreach标签: 循环执行SQL的拼接操作
                    select * from xx where 条件 in() 相当于 where 条件 or 条件                
                    <where>
                        <foreach collection="参数集合/数组" open="以什么开始" close="以什么结束" item="项，变量" separator="分隔符">
                        </foreach>
                    </where>
            2.SQL片段抽取
                将重复的SQL语句使用<sql>标签提取出来，使用<include>引用
                <sql id="">sql语句</sql>        
                <include refid="id"/>
    mybatis核心配置文件
        1.DTD约束头
            <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">        
        2.核心配置文件层级关系
            configuration 配置
                properties 属性
                settings   设置
                typeAliases类型别名
                typeHandlers类型处理器
                objectFactory对象工厂
                plugins     插件
                environments 环境
                    environment 环境变量
                        transactionManager 事务管理器
                        DataSource 数据源
                databaseIDProvider 数据库厂商标识
                mappers 映射器 
        3.environments标签
            数据库环境配置，支持多环境配置
            <environments default="id"(指定默认的环境名称)>                            
                <environment id=""(指定当前环境的名称)>
                    <transactionManager type="JDBC"/> 指定事务管理类型为JDBC
                    两种类型：
                        JDBC:直接使用jdbc的提交和回滚设置，依赖于从数据源得到的连接来管理事务作用域
                        MANAGED:容器管理事务的整个生命周期。默认情况下会关闭连接，使用closeConnection属性设置为false来阻止默认关闭的行为
                    <dataSource type="POOLED">指定当前数据源类型是连接池
                    三种类型：
                        UNPOOLED:每次被请求时打开和关闭连接
                        POOLED:利用“池”的概念将JDBC连接对象组织起来
                        JNDI:这个数据源的实现是为了能在 EJB或应用服务器这类容器中使用，容器可以集中或在外部配置数据源，然后放置一个JNDI上下文的引用
                        
                        <property>数据源配置的基本参数
                    </dataSource>
                </environment>
            </environments>    
        4.mapper标签
            加载映射文件
            使用相对于类路径的资源引用，<mapper resource="com\mybatis\mapper\UserMapper.xml"/>
            使用完全限定资源定位符(URL):<mapper url="G:\Spring\mybatis\src\main\resources\com\mybatis\mapper\UserMapper.xml"/>        
            使用映射器接口实现类的完全限定类名 <mapper class="com.mybatis.domain.User"/>                    
            将包内的映射器接口实现全部注册为映射器 <package name="com.mybatis.domain"/>
        5.properties标签
            <!--加载外部properties文件 写在最上面-->
            <properties resource="druid.properties"></properties>    
        6.typeAliases标签
            类型别名是为java类型设置的一个短的名字
            mybatis中默认配置
                String  --> string
                Long    --> long
                Integer --> int
                Double  --> double
                Boolean --> boolean
            原来类型配置：
                select id="findAll" resultType="com.mybatis.domain.User">
                    select * from user;
                </select>  
            配置typeAliases，起别名
                //核心配置文件中配置
                <typeAliases>      
                    <typeAlias type="com.mybatis.domain.User" alias="user"></typeAlias>      
                </typeAliases>      
                //映射文件中配置
                <select id="findAll" resultType="user">
                    sql语句
                </select>
        *深入
            1.typeHandlers标签
                mybatis预处理语句中设置参数或者从结果集中取值时，都要使用类型处理器将获取的值用合适的方式转换成java类型
                类型处理器               java类型                      jdbc类型
                BooleanTypeHandler     java.lang.Boolean.boolean    数据库兼容的BOOLEAN
                ByteTypeHandler        java.lang.Byte.byte          数据库兼容的NUMERIC或BYTE
                ....
                重写类型处理器或创建自己的类型处理器处理不支持或非标准的类型，具体做法：实现org.apache.ibatis.type.TypeHandler接口
                或继承一个便利的类org.apache.ibatis.type.BaseTypeHandler,然后选择性的将它映射到一个JDBC类型
                开发步骤：
                    1.定义转换类继承类BaseTypeHandler<T>
                    2.覆盖4个未实现的方法，
                        其中setNonNullParameter为java程序设置数据到数据库的回调方法
                        getNullableResult为查询时mysql的字符串类型转换成java的Type类型的方法
                    3.在MyBatis核心配置文件中进行注册
                    4.测试转换是否正确
            2.plugins（插件）标签        
                MyBatis可以使用第三方插件进行功能扩展，分页助手PageHelper是将分页的复杂操作进行封装，使用简单的方式获取分页的相关数据
                开发步骤：
                    1.导入通用PageHelper的坐标
                    2.在mybatis核心配置文件中配置PageHelper插件
                        <!--配置分页助手插件-->
                        <plugins>
                            <plugin interceptor="com.github.pagehelper.PageHelper">
                                <!--指定方言-->
                                <property name="dialect" value="mysql"/>
                            </plugin>
                        </plugins>
                    3.测试分页数据获取
                    
                    方法：
                    //设置分页相关参数
                    PageHelper.startPage(当前页,显示条数);
                    //获取分页信息
                    PageInfo<User> pageInfo = new PageInfo<>(all);
                    "当前页:" pageInfo.getPageNum()
                    "条数:"  pageInfo.getPageSize()
                    "总条数:"  pageInfo.getTotal()
                    "总页数:"  pageInfo.getPages()
                    "上一页:"  pageInfo.getPrePage()
                    "下一页:"  pageInfo.getNextPage()
                    "是否为第一页:"  pageInfo.isIsFirstPage()
                    "是否为最后一页:"  pageInfo.isIsLastPage()
                        
    mybatis相应API
        1.sqlSession工厂构建器SqlSessionFactoryBuilder
            常用API：SqlSessionFactory build(InputStream inputStream)
            通过加载mybatis的核心文件的输入流的形式构建一个SqlSessionFactory对象
            InputStream is = Resources.getResourceAsStream("文件名称");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(inputStream);    
            Resources工具类，在org.apache.ibatis.io包中。Resources类可以从类路径、文件系统或web URL中加载资源文件。        
        2.SqlSession工厂对象SqlSessionFactory
            SqlSessionFactory有多个方法创建sqlSession实例。常用：
                openSession()       默认开启一个事务，但不会自动提交。需要手动提交，更新操作数据才会持久化到数据库中
                openSession(Boolean autoCommit) 参数为是否自动提交，ture不需要手动提交事务    
        3.SqlSession会话对象
            执行语句的方法：
                <T> T selectOne(String statement, Object parameter)
                <E> List<E> selectList(String statement, Object Parameter)        
                int insert(String statement, Object Parameter)
                int update(String statement, Object Parameter)
                int delete(String statement, Object Parameter)
            操作事务方法：
                void commit()
                void rollback()  
##Mybatis的Dao层实现
    1.传统方式
        加载配置文件，创建sqlSessionfactoryBuilder对象-->获取SqlSessionFactory对象-->SqlSession对象-->执行SQL语句     
    2.代理开发方式 
        mapper接口开发方法只需要编写mapper接口（相当于Dao接口）有mybatis框架根据接口定义创建接口的动态代理对象，代理对象的方法同Dao
        接口实现类方法
        mapper接口开发规范：
            mapper.xml文件中的namespace与mapper接口的全限定名相同
            mapper接口方法名和mapper.xml中定义的每个statement的id相同
            mapper接口方法的输入参数类型和mapper.xml中定义的每个sql的parameterType的类型相同                
            mapper接口方法的输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同  
            
        步骤：
            配置文件：
                namespace与接口的全限定名一致，增删改查的id与接口方法名一致，参数与返回值与方法的一致
            加载配置文件-->创建SqlSessionFactoryBuilder对象-->获取SqlSessionFactory对象-->获取SQLSession对象
            -->sqlsession.getMapper(xxx.class)-->调用接口方法        
##MyBatis多表操作
    使用resultMap标签，手动指定字段与实体属性的映射关系
                <!--column：数据表字段名称-->
                <!--property：实体属性名称-->
                <!--主键-->
                <id column="uid" property="id"></id>
                <!--普通属性-->
                <result column="orderTime" property="orderTime"></result>
                <!--匹配一个属性
                    property：当前实体中的属性名称
                    JavaType：当前实体中属性的类型
                -->
                <association property="user" javaType="user">
                <!--配置集合信息
                    property：集合名称
                    ofType：当前集合中的数据类型
                -->
                <collection property="orderList" ofType="order"></collection>
    *配置方式：
        一对一配置：使用<resultMap>配置     内部引入其他属性可以使用<association>配置       
        一对多配置：使用<resultMap>+<collection>配置
        多对多配置：使用<resultMap>+<collection>配置
##MyBatis注解开发
    1.常用注解
        @Insert:实现新增
        @Update:实现更新            
        @Delete:实现删除            
        @Select:实现查询            
        @Result:实现结果集封装 
        @Results:可以和@Result一起使用，封装多个结果集
        @One:实现一对一结果集封装            
        @Many:实现一对多结果集封装            
    2.实现复杂映射开发
        在xml中实现复杂关系映射，可以使用<resultMap>实现，
        注解可以使用@Results注解，@Result注解，@One注解，@Many注解组合完成复杂关系配置
        
        @Results                代替<resultMap>标签，注解中可以使用单个@Result注解，也可以使用@Result集合
                                格式：@Results({@Result(),@Result()})或@Results({@Result())            
        @Result                 代替<id>标签和<result>标签
                                属性：
                                    column：数据库的列名
                                    property：需要配置的属性名
                                    one：需要使用的@One注解(@Result(one=@One)()))
                                    many:需要使用的@Many注解(@Result(many=@Many)())
        @One(一对一)             代替<assocation>标签，多表查询的关键，注解中用来指定子查询返回单一对象
                                属性：
                                    select：指定用来多表查询的sqlMapper
                                格式：
                                    @Reslut(column="",property="".one=@One(select=""))
        @Many(一对多)            代替<collection>标签，注解中指定子查询返回对象集合
                                格式：
                                    @Result(property="",column="",many=@Many(select=""))                                                  
                                    
        一对一两种配置：
            /*@Results({
                    @Result(column = "oid", property = "id"),
                    @Result(column = "orderTime", property = "orderTime"),
                    @Result(column = "total", property = "total"),
                    @Result(column = "uid", property = "user.id"),
                    @Result(column = "username", property = "user.username"),
                    @Result(column = "password", property = "user.password"),
                    @Result(column = "birthday", property = "user.birthday", typeHandler = DateHandler.class),
            })*/
            @Results({
                    @Result(column = "oid", property = "id"),
                    @Result(column = "orderTime", property = "orderTime"),
                    @Result(column = "total", property = "total"),
                    @Result(
                            property = "user",//要封装的属性名称
                            column = "uid",//根据那个字段查询
                            javaType = User.class,
                            one = @One(select = "com.mybatis_anno.mapper.UserMapper.findById")
                    )
            })              