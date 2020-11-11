# Spring快速入门
    Spring开发步骤
        1.导入坐标
        2.创建Bean
        3.创建applicationContext.xml
        4.在配置文件中进行配置
        5.创建ApplicationContext对象getBean(id);
        
 ## Spring 配置文件   
    Bean标签基本配置
        默认情况下调用的是类中的无参构造函数，如果没有无参构造函数则不能创建成功。
        基本属性:
            id:Bean实例在Spring容器中的唯一标识
            class:Bean的全限定名称  
                  
    Bean标签范围配置
        scope:指对象的作用范围
            取值范围        
            singleton       默认值，单例的
                  Bean的实例化个数：1个
                  Bean的实例化时机：当Spring核心文件被加载时，实例化配置的Bean实例   加载配置文件就创建       
                  Bean的生命周期：
                    
                    对象创建：当应用加载，创建容器时，对象创建。
                    对象运行：只要容器在，对象一直活着
                    对象销毁：当应用卸载，销毁容器时，对象销毁
            prototype       多例的             getBean时创建
                  Bean的实例化个数：多个
                  Bean的实例化时机：当调用getBean()方法时，实例化Bean 
                  Bean的生命周期：
                    对象创建：当使用对象时，创建新的对象实例
                    对象运行：只要对象在使用中，对象一直活着
                    对象销毁：当对象长时间不用时，被java垃圾回收器回收
            request         WEB项目中，Spring创建一个Bean的对象，将对象存入到request域中
            session         WEB项目中，Spring创建一个Bean的对象，将对象存入到session域中    
            global session  WEB项目中，应用在Porlet环境，如果没有Porlet环境那个globalSession相当于session
    Bean声明周期配置
        init-method：指定类中的初始化方法名称
        destroy-method：指定类中销毁方法名称
    Bean实例化三种方式：
        无参构造方法实例化   <bean id="userDao" class="全限定名"></bean>
        工厂静态方法实例化   <bean id="userDao" class="工厂的全限定名" factory-method="方法"></bean>
        工厂实例方法实例化   <bean id="工厂id" class="工厂的全限定名"></bean>   
                         <bean id="userDao" factory-bean="工厂id" factory-method="方法"></bean>    
                    
    Bean的依赖注入（Dependency Injection）
        Spring框架核心IOC的具体实现
        编写程序时，通过控制反转，把对象的创建交给Spring，但是代码中不可能出现没有依赖的情况。
        IOC解耦只是降低依赖关系，但不会消除。
        等框架把持久层对象传入业务层，不用自己获取
        
        方式：
            set方法注入
                private UserDao userDao;
                publice void setUserDao(UserDao userDao){
                    this.userDao = userDao;
                }
                配置文件：
                    <bean id="userDao" class="com.springTest.dao.daoImp.UserDaoImp"></bean>
                    <!--  注入依赖  -->
                    <bean id="userService" class="com.springTest.service.imp.UserServiceImp">
                        <property name="userDao" ref="userDao"></property>
                    </bean>
                    P命名空间注入本质也是set方法注入，但比起set方法注入更加方便，需要在配置文件中引入p命名空间
                        xmlns:p="http://www.springframework.org/schema/p"
                    修改注入方式：
                        <bean id="userService" class="userServiceImp全限定名" p:userDao-ref="userDao"/>
        
            构造方法注入：
                构造方法：
                    public UserServiceImp(UserDao userDao) {
                        this.userDao = userDao;
                    }
                配置文件：
                    <bean id="userService" class="com.springTest.service.imp.UserServiceImp">
                        <constructor-arg name="userDao" ref="userDao"></constructor-arg>
                    </bean>    
        
        Bean的依赖注入的数据类型
            普通数据类型:通过property标签中 name value属性配置
            引用数据类型:使用value-ref引用
            集合数据类型   
                <map>
                    <entry key="user1" value-ref="user1"></entry>
                </map>   
                <list>
                    <value>aaa</value>                   
                </list>  
                <property name="prop">
                    <props>
                        <prop key="p1">1</prop>
                    </props>
                </property>     
            
        *引入其他配置文件(分模块开发)             
            将部分配置拆解到其他配置文件中，在Spring主配置文件中通过import标签进行加载
            <import resource="applicationContext-xxx.xml"/>
 -----------------------------------------------------------------------------------------------------------------------           
    *Spring的重点配置
        <bean>标签
            id属性:在容器中Bean实例的唯一标识，不允许重复
            class属性:要实例化的Bean的全限定名
            scope属性:Bean的作用范围。常用Singleton(默认)和prototype
            <property>标签:属性注入
                name属性:属性名称
                value属性:注入的普通属性值
                ref属性:注入的对象引用值
                <list>标签
                <map>标签
                <properties>标签
            <constructor-arg>标签:构造方法注入                   
        <import>标签:导入其他的Spring的分文件
       
##Spring相应API
    applicationContext:接口类型，代表应用上下文，可以通过其实例获得Spring容器中的Bean对象        
    ApplicationContext的实现类：
        1.ClassPathXmlApplicationContext:
            从类的根路径下加载配置文件
        2.FileSystemXmlApplicationContext:
            从磁盘路径上加载配置文件，配置文件可以在磁盘的任意位置
        3.AnnotationConfigApplicationContext:
            使用注解配置容器对象时，需要使用此类来创建Spring容器。用于读取注解
    getBean()方法使用: 
        参数类型:
            第一种:String name 根据beanid获取 允许存在多个相同类型的bean
            第二种:Class<T> requiredType 根据字节码文件获取  只允许存在一个相同类型的bean，否则报错     
##Spring配置数据源
    数据源(连接池)的作用
        数据源(连接池)提高程序性能
        事先实例化数据源，初始化部分连接资源
        使用连接资源时从数据源中获取
        使用完毕后将连接资源归还给数据源  
        
    抽取jdbc配置文件
        applicationContext.xml加载数据库properties配置文件获得连接信息
        需要引入context命名空间和约束路径
            命名空间:xmlns:context="http://www.springframework.org/schema/context"
            约束路径:http://www.springframework.org/schema/context
                    http://www.springframework.org/schema/context/spring-context.xsd      
            Spring容器加载properties文件
                <context:property-placeholder location="xx.properties"/>
                <property name="" value="${}">                
##Spring注解开发
    Spring轻代码重配置的框架，配置比较繁重，影响开发效率，注解代替xml配置文件可以简化配置，提高开发效率                
    Spring原始注解
        Spring原始注解主要是替代<Bean>的配置
        注解                      说明
        @Component（组件）    使用在类上用于实例化Bean
        @Controller         使用在web层类上用于实例化Bean
        @Service            使用在Service层类上用于实例化Bean
        @Repository         使用在dao层类上用于实例化Bean
        @Autowired          使用在字段上用于根据类型依赖注入                    如果只写Autowired会按照数据类型从Spring容器中进行匹配
        @Qualifier          结合@Autowired一起使用用于根据名称进行依赖注入       和@Autowired配合使用，按照id值从容器中进行匹配 
        @Resource           相当于@Autowired+@Qualifier，按照名称进行注入
        @Value              注入普通属性
        @Scope              标注Bean的作用范围
        @PostConstruct      使用在方法上标注该方法是Bean的初始化方法
        @PreDestroy         使用在方法上标注该方法是Bean的销毁方法
    *使用注解时，需要在配置文件中配置组件扫描，作用是指定哪个包及其子包下的Bean需要进行扫描以便识别使用注解配置的类、字段和方法
    <context:component-scan base-package=""></context:component-scan>    
    
    Spring新注解
        注解                      说明
        @Configuration      用于指定当前类是一个Spring配置类，当创建容器时会从该类上加载注解（标志该类是Spring的核心配置类）
        @ComponentScan      用于指定Spring在初始化容器时要扫描的包。
                            作用和在Spring的xml配置文件中的<context:component-scan base-package="xx"/>一样
        @Bean               使用在方法上，标注将该方法的返回值存储到Spring容器中
        @PropertySource     用于加载properties文件中的配置
        @Import             用于导入其他配置类 
        
##Spring集成Junit
    Spring集成Junit步骤
        导入spring集成Junit的坐标
        使用@Runwith注解替换原来的运行期
        使用@ContextConfiguration指定配置文件或配置类
        使用@Autowired注入需要测试的对象
        创建测试方法进行测试
        
##Spring AOP
    AOP:Aspect Oriented Programming的缩写，面向切面编程，是通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术   
        AOP是OOP(面向对象编程)的延续，Spring框架中的一个重要内容，是函数式编程的一种衍生泛型。利用AOP可以对业务逻辑的各个部分进行隔离。
    从而使得业务逻辑各部分之间的耦合度降低，提到程序的可重用性，同时提高开发的效率。  
    
    AOP作用:在程序运行期间，在不修改源码的情况下对方法进行功能增强
    AOP优势:减少重复代码，提高开发效率，并且便于维护
    
    AOP底层实现:
           AOP的底层是通过Spring提供的动态代理技术实现，在运行期间，Spring通过动态代理技术动态的生成代理对象，代理对象方法执行时进行
        增强功能的介入，在去调用目标对象的方法，从而完成功能的增强。
    AOP的动态代理技术:
        常用的动态代理技术
            JDK代理:基于接口的动态代理技术
            cglib代理:基于父类的动态代理技术
    *AOP相关概念
        Target(目标对象):代理的目标对象
        Proxy(代理):一个类被AOP织入增强后，就产生一个结果代理类
        Joinpoint(连接点):是指被拦截到的点。在spring中，这些点指的是方法，spring只支持方法类型的连接点(可以被增强的方法)
        Pointcut(切入点):是指要对哪些Joinpoint进行拦截定义(真正被增强的方法)
        Advice(通知/增强):指拦截到Joinpoint之后所要做的事情就是通知
        Aspect(切面):是切入点和通知的结合
        Weaving(织入):是指把增强对象应用到目标对象来创建新的代理对象的过程。spring采用动态代理织入，而AspectJ采用编译期织入和类装载期织入       
           
    需要编写的内容
        1.编写核心业务代码(目标类中的目标方法)       
        2.编写切面类，切面类中有通知(增强功能方法)
        3.在配置文件中，配置织入关系（哪些通知和哪些连接点进行结合）
    AOP技术实现的内容
            Spring框架监控切入点方法的执行。 一旦监控到切入点方法被运行，使用代理机制，动态创建目标对象的代理对象，根据通知类别，在代理对象的对应位置
        将通知对应的功能织入，完成完整的代码逻辑运行   
###基于xml的AOP开发
    快速入门
        1.导入AOP相关坐标
        2.创建目标接口和目标类(内部有切点)                     
        3.创建切面类（内部有增强方法）
        4.将目标类和切面类的对象创建权交给spring
        5.在applicationContext.xml中配置织入关系
        6.测试代码
    xml配置
        <!--目标对象-->
            <bean id="target" class="com.springTest.aop.Target"></bean>
        <!--切面对象-->
            <bean id="myAspect" class="com.springTest.aop.MyAspect"></bean>
        <!--配置织入:告诉spring框架 哪些方法(切点)需要进行哪些增强（前置、后置）-->
            <aop:config>
                <!--声明切面-->
                <aop:aspect ref="myAspect">
                    <!--切面：切点加通知-->
                    <aop:before method="before" pointcut="execution(public void com.springTest.aop.Target.save())"></aop:before>
                    <aop:before method="before" pointcut="execution(* com.springTest.aop.*.*(..))"></aop:before>
                </aop:aspect>
            </aop:config>
            
        切点表达式：
            语法：
                execution([修饰符] 返回值类型 包名.类名.方法名(参数))                   
                    访问修饰符可以省略
                    返回值类型、包名、类名、方法名可以使用星号*代表任意
                    包名与类名之间的一个点.代表当前包下的类，两个点..表示当前包及其子包下的类
                    参数列表可以使用两个点..表示任意个数，任意类型的参数列表
            抽取：
                execution：执行
                expression：表达式
                <aop:pointcut id="" expression=""/>
                配置中使用pointcut-ref引用    
        通知的类型：
            语法：
                <aop:通知类型 method="切面类中方法名" pointcut="切点表达式"></aop:通知类型>        
            名称              标签                      说明    
            前置通知        <aop:before>            用于配置前置通知。指定增强的方法在切入点方法之前执行
            后置通知        <aop:after-returning>   用于配置后置通知。指定增强的方法在切入点方法之后执行
            环绕通知        <aop:around>            用于配置环绕通知。指定增强的方法在切入点方法之前和之后都执行
            异常抛出通知     <aop:after-throwing>    用于配置异常抛出的通知，指定增强的方法在出现异常时执行
            最终通知        <aop:after>             用于配置最终通知。无论增强方式执行是否有异常都会执行
    *要点
        <aop:config>
            <aop:aspect ref="切面类">
                <aop:通知类型 method="通知方法名称" pointcut="切点表达式"></aop:before>
            </aop:aspect>
        </aop:config>        
###基于注解的AOP开发
    快速入门：
        基于注解的aop开发步骤“
            1.创建目标接口和目标类(内部有切点)            
            2.创建切面类(内部有增强方法)
            3.将目标类和切面类的对象创建交给spring
            4.在切面类中使用注解配置织入关系
            5，在配置文件中开启组件扫描和AOP的自动代理
                <!--组件扫描-->
                <context:component-scan base-package="com.springTest.aop_anno" ></context:component-scan>
                <!--aop自动代理-->
                <aop:aspectj-autoproxy/>
            6.测试
    注解配置：
        注解通知的类型
            通知的配置语法：@通知注解("切点表达式")        
            前置通知：@Before    后置通知：@AfterReturning    环绕通知：@Around
            异常抛出通知：@AfterThrowing   @最终通知：@After
        切点表达式抽取
            在切面内定义方法，在方法上使用@pointcut注解定义切点表达式，然后在增强注解中进行引用
                @Pointcut("execution(* com.springTest.aop_anno.*.*(..))")
                public void myPoint(){}    
##Spring JdbcTemplate      
    JdbcTemplate开发步骤：
        1.导入spring-jdbc和spring-tx坐标
        2.创建数据库表和实体
        3.创建JdbcTemplate对象
        4.执行数据库操作
##事务控制
    编程式事务控制：       
        相关对象：
            1.PlatformTransactionManager（平台事务管理器）接口是spring的事务管理器，提供常用的操作事务的方法       
                方法：
                    TransactionStatus getTransaction(TransactionDefination defination)  获取事务的状态信息
                    void commit(TransactionStatus status)   提交事务
                    void rollback(TransactionStatus status) 回滚事务
                    PlatformTransactionManager是接口类型，不同的Dao层技术有不同的实现类
                        当Dao层技术是jdbc或mybatis时：
                            org.springframework.jdbc.datasource.DataSourceTransactionManager
                        当Dao层技术是hibernate时：
                            org.springframework.orm.hibernate5.HibernateTransactionManager
                    
            2.TransactionDefinition 事务的定义信息对象
                方法：
                    int getIsolationLevel()         获得事务的隔离级别
                        事务隔离级别，可以解决事务并发产生的问题：脏读、不可重复读和虚读
                            ISOLATION_DEFAULT          默认
                            ISOLATION_READ_UNCOMMITTED 读未提交
                            ISOLATION_READ_COMMITTED   读已提交
                            ISOLATION_REPEATABLE_READ  可重复读
                            ISOLATION_SERIALIZABLE     序列化
                            
                    int getPropogationBehavior()    获得事务的传播行为        
                        事务传播行为：
                            REQUIRED:如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。
                            SUPPORTS:支持当前事务，如果当前没有事务，就以非事务方式执行
                            MANDATORY:使用当前的事务，如果当前没有事务，就抛出异常
                            REQUERS_NEW:新建事务，如果当前在事务中，把当前事务挂起
                            NOT_SUPPORTED:以非事务方式执行操作，如果当前存在事务，就把当前事务挂起
                            NEVER:以非事务方式运行，如果当前存在事务，则抛出异常。
                            NESTED:如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行REQUIRED类似的操作（spring特有）
                            
                    int getTimeout()                获取超时时间
                        默认值是-1，没有超时限制。如果有，以秒为单位设置
                        
                    boolean isReadOnly()            是否只读
                        （建议查询时设置为只读）
            
            3.TransactionStatus 接口提供的事务具体的运行状态
                boolean hasSavepoint()          是否存储回滚点
                boolean isCompleted()           事务是否完成
                boolean isNewTransaction()      是否是新事务
                boolean isRollbackOnly()        事务是否回滚 
                
    声明式事务控制：
        采用声明的方式来控制事务
        作用：
            事务管理不侵入开发的组件
            在不需要事务管理的时候，只要在设定文件上修改一下，即可移去事务管理服务，无需改变代码的重新编译，维护起来更方便
        Spring声明式事务控制底层就是AOP    
        
        使用xml声明式事务控制
            xml配置:
                目标对象配置：
                    <bean id="" class="">    
                平台事务管理器配置：
                    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
                        数据源，数据库连接
                        <property name="dataSource" ref="druid"></property>
                    </bean>    
                事务增强配置：
                    <!--通知 事务的增强-->
                    <!--需引入tx命名空间-->
                    <tx:advice id="txAdvice" transaction-manager="transactionManager">
                        <!--设置事务属性信息-->
                        <tx:attributes>
                            <!--name:增强的方法 切点方法名称 -->
                            <!--isolation:事务的隔离级别-->
                            <!--propagation:事务的传播行为-->
                            <!--timeout:超时时间-->
                            <!--read-only:是否只读-->
                            <tx:method name="transfer" isolation="REPEATABLE_READ" propagation="REQUIRED" timeout="-1" read-only="false"/>
                            <tx:method name="findAll" isolation="REPEATABLE_READ" propagation="REQUIRED" timeout="-1" read-only="true"/>
                            <tx:method name="*" />
                        </tx:attributes>
                    </tx:advice>               
                事务的aop织入配置：
                    <aop:config>
                        <aop:advisor advice-ref="" pointcut="">
                        </aop:advisor>
                    </aop:config>    
        
        基于注解的声明式事务控制
            1.使用@Transactional在需要进行事务控制的类或方法上修饰，注解可用的属性与xml配置方式相同
                （隔离级别isolation超时时间timeout是否只读readonly事务的传播行为propagation）
            2.注解使用在类上，该类下的所有方法都使用同一套注解参数配置
            3.注解使用中方法上，不同的方法可以采用不同的事务参数配置
            4.xml配置文件中要开启事务的注解驱动<tx:annotation-driven transaction-manager=""/>  
            
            配置要点
                平台事务管理器配置（xml方式）  
                事务通知的配置（@Transactional注解配置）
                事务直接驱动的配置<tx:annotation-driven/>
                
## Spring集成web环境
    ApplicationContext应用上下文获取方式
           应用上下文对象是通过new ClasspathXmlApplicationContext(Spring配置文件)方式获取的，
        但是每次从容器中获得Bean时都要编写new ClasspathXmlApplicationContext(Spring配置文件)，这样的弊端是配置文件加载多次，
        应用上下文对象创建多次，影响效率
           在web项目中，可以使用ServletContextListener监听web应用的启动，在启动时，就加载spring配置文件，创建应用上下文对象
        ApplicationContext，将其存储在最大的域ServletContext域中，可以在任意位置从域中获取应用上下文ApplicationContext对象   
                     
    spring提供获取应用上下文的工具
        Spring提供了监听器ContextLoaderListener，内部配置加载Spring配置文件，创建应用上下文对象，并存储到ServletContext域中
        提供客户端工具WebApplicationContextUtils获取上下文对象
        
        使用：
            1.在web.xml中配置ContextLoaderListener监听器（pom.xml中导入spring-web坐标） 
                <!--全局初始化参数-->   
                <context-param>
                    <param-name>contextConfigLocation</param-name>
                    <param-value>classpath:applicationContext.xml</param-value>
                </context-param>                
            2.使用WebApplicationContextUtils获取上下文对象ApplicationContext