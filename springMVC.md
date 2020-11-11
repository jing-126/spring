#SpringMVC
    SpringMVC是一种基于java地实现MVC设计模型的请求驱动类型轻量级web框架，属于SpringFrameWork的后续产品。
    通过一套注解，让java类成为处理请求的控制器，无需实现任何借口。还支持RESTful编程风格的请求
    
    开发步骤：
        1.导入springMVC相关坐标(pom.xml中导入spring-webmvc坐标)
        2.配置servlet（web.xml中配置springMVC核心控制器DispatcherServlet）
        3.编写Controller（POJO 简单的javaBean）和视图页面
        4.将Controller使用注解配置到Spring容器中(@Controller)
        5.配置spring-mvc.xml文件（配置组件扫描）
        6.执行访问测试
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
## SpringMVC的执行流程
    1.用户发送请求到前端控制器DispatcherServlet
    2.DispatcherServlet收到请求调用HandlerMapping处理器映射器。
    3.处理器映射器找到具体的处理器（可以根据xml配置、注解进行查找），生成处理器对象以及处理器拦截器（如果有就生成），
 并返回给DispatcherServlet
    4.DispatcherServlet调用HandlerAdapter处理器适配器
    5.HandlerAdapter经过适配调用具体的处理器（Controller后端控制器）          
    6.Controller执行完成返回ModelAndView
    7.HandlerAdapter将controller执行结果ModelAndView返回给DispatcherServlet
    8.DispatcherServlet将ModelAndView传给ViewResolver视图解析器
    9.ViewResolver解析后返回具体view
    10.DispatcherServlet根据View进行渲染视图（将模型数据填充到视图中）。DispatcherServlet响应用户。
## SpringMVC注解解析
    @RequestMapping（请求映射）
    作用：用于建立请求URL和处理请求方法之间的对应关系    
    位置：
        类上：请求URL的第一级访问目录。如果不写，相当于应用的根目录
        方法上：请求URL的第二级访问目录，与类上使用@RequestMapping标注的一级目录一起组成访问虚拟路径
    属性：
        value：用于指定请求的URL。与path属性作用相同
        method：用于指定请求的方式
        params：用于指定限制请求参数的条件。支持简单的表达式。要求请求参数的key和value必须和配置一样
            params={"user"}请求参数必须有user
            params={"money!100"}请求参数中money不能为100  
            
    @ResponseBody   
    告知springMVC框架 该方法返回值不进行页面跳转 直接响应数据
          
## xml配置解析
    配置组件扫描
        <context:compont-scan base-package="com.xxx.xxx"/>或者
        <context:compont-scan base-package="com.xxx.xxx">
            <context:include-filter type="annotation" expression="注解的全限定名"/>只扫描包含包下的某个注解
            <context:exclude-filter type="annotation" expression="注解的全限定名"/>只扫描除了包下的某个注解
        </context:compont-scan>    
    
    SpringMVC中有默认配置，默认组件在DispatcherServlet.properties配置文件中配置。            
    配置内部资源视图解析器     ViewResolver
    默认配置文件中的默认设置
        REDIRECT_URL_PREFIX="redirect:" --重定向前缀
        FORWARD_URL_PREFIX="forward:" --转发前缀
        prefix=""; --视图名称默认前缀
        suffix=""; --视图名称默认后缀
    <bean id="" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        //  /jsp/ + success + .jsp  前缀+返回值+后缀
        <property name="prefix" value=""></property>//前缀
        <property name="suffix" value=""></property>//后缀
    </bean>        
            
## 知识要点        
    SpringMVC的相关组件
        前端控制器：DispatcherServlet     负责调用其他功能组件
        处理器映射器：HandlerMapping       地址解析返回对应的执行链
        处理器适配器：HandlerAdapter       被前端控制器调用执行处理器
        处理器：Handler
        视图解析器：ViewResolver          将View解析出来
        视图：View
    SpringMVC的注解和配置
        请求映射注解：@RequestMapping
        视图解析器配置：
            REDIRECT_URL_PREFIX="redirect";        
            FORWARD_URL_PREFIX="forward";
            prefix="";
            suffix="";
            
## SpringMVC数据响应
    方式：
        1.页面跳转
            *直接返回字符串：字符串与视图解析器的前后缀拼接后跳转
                @RequestMapping("/quick") produces="text/html;charset=UTF-8" produces属性可以设置响应数据编码
                public String save(){
                    System.out.println("userController save running...");
                    return "success";
                }
                <bean id="q" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
                    <property name="prefix" value="/"/>
                    <property name="suffix" value=".jsp"/>
                </bean>
                转发资源地址：/success.jsp
                重定向：redirect:/success.jsp
                
            *通过ModeAndView对象返回
                Model:模型 作用：封装数据
                View:视图  作用：展示数据
                返回ModelAndView对象
                    setViewName设置视图
                    addObject设置模型数据
                几种方式：
                    1.new ModelAndView对象存储视图和模型数据并返回对象
                    2.ModelAndView写到参数中SpringMVC自动注入，设置视图和模型数据返回对象
                    3.使用Model对象存储数据，直接返回字符串视图    
                    4.使用HttpServletRequest对象存储数据，直接返回字符串视图    
        2.回写数据
            直接返回字符串
                *通过SpringMVC框架注入response对象，使用response.getWriter().print()回写数据，不需要视图跳转，业务方法
                返回值为void。
                *将需要回写的字符串直接返回，使用@ResponseBody注解告知SpringMVC框架，方法返回的字符串不进行页面跳转，直接在
                http响应体中返回
            返回对象或集合    
                通过SpringMVC对对象或集合进行json字符串的转换回写，需要配置处理器适配器，进行转换，指定Jackson进行对象或集合的转换
                    <!--配置处理器适配器-->
                    <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">
                        <property name="messageConverters">
                            <list>
                                <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter"></bean>
                            </list>
                        </property>
                    </bean>
                    
                SpringMVC中处理器映射器、处理器适配器、视图解析器称为SpringMVC三大组件
                使用<mvc:annotation-driven>自动加载RequestMappingHandlerMapping（处理器映射器）和RequestMappingHandlerAdapter（处理适配器）    
                在spring-mvc.xml文件中使用<mvc:annotation-driven>替代直接配置组件
                <mvc:annotation-driven/>底层默认集成Jackson进行对象或集合的json格式字符串转换。
                
## SpringMVC获取请求数据
    *获取请求参数：
        参数类型：
            基本类型参数
                Controller中的业务方法的参数名称要与请求参数中的名字一致，参数值会自动映射匹配
            pojo类型参数
                Controller中的业务方法的pojo参数的属性名与请求参数的name一致，参数值自动映射匹配
            数组类型参数
                Controller中的业务方法数组名称与请求参数中的name一致，参数值会自动映射匹配
            集合类型参数
                获取集合参数时，要将集合参数包装到一个pojo中
                当使用ajax提交时，可以指定contentType为json形式，在方法参数位置使用@RequestBody可以直接
                接收集合数据类型，不需要使用pojo进行包装            
    *静态资源访问问题：
        <!--开放资源的访问-->
        <!--<mvc:resources mapping="/js/**" location="/js/"></mvc:resources>-->
        <!--如果SpringMVC找不到相应的资源交给默认的容器（Tomcat）寻找-->
        <mvc:default-servlet-handler/>            
        
    *请求数据乱码问题：
        当post请求时，数据出现乱码，设置一个过滤器来进行编码的过滤
            <filter>
                <filter-name>characterEncodingFilter</filter-name>
                <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
                <init-param>
                    <param-name>encoding</param-name>
                    <param-value>UTF-8</param-value>
                </init-param>
            </filter>
            <filter-mapping>
                <filter-name>characterEncodingFilter</filter-name>
                <url-pattern>/*</url-pattern>
            </filter-mapping>
        
    *参数绑定注解@RequestParam
        当请求的参数名称和Controller的业务方法参数名称不一致时，通过@RequestParam注解显示的绑定
            public void getReq06(@RequestParam(value = "name") String username)
        参数：
            value：请求参数名称
            required：指定的请求参数是否必须包括，默认是true，提交时如果没有此参数就报错
            defaultValue：当没有指定请求参数时，使用指定的默认值赋值
    
    *获取Restful风格参数
        Restful是一种软件架构风格、设计风格，而不是标准，只是提供了一组设计原则和约束条件。主要用于客户端和服务器交互类的软件，
        基于这个风格设计的软件可以更简洁，更有层次，更易于实现缓存机制等。
        
        Restful风格的请求是使用 “URL + 请求方式” 表示一次请求目的，HTTP协议里面四个表示操作方式的的动词：
            GET:用于获取资源
            POST:用于新建资源
            PUT:用于更新资源
            DELETE:用于删除资源        
            
            例：
                /user/1 GET:得到id=1的user
                /user/1 DELETE:删除id=1的user
                /user/1 PUT:更新id=1的user
                /user   POST:新增user
        上述URL地址/user/1中的1就是要获得的请求参数，在SpringMVC中可以使用占位符进行参数绑定。地址/user/1可以写成
        /user/{id}，占位符{id}对应的就是1的值。业务方法中使用@PathVariable注解进行占位符的匹配获取
            @RequestMapping("req07/{username}")
            @ResponseBody
            public void getReq07(@PathVariable("username") String username){
                System.out.println(username);
            }
            
    *自定义类型转换器
        SpringMVC默认提供常用的类型转换器。 字符串-->int等
        但是不是所有的数据都提供转换器，需要自定义转换器。如：日期类型的数据需要自定义转换器
            自定义转换器步骤：
                1.定义转换器类实现Converter<S,T>接口 s：转换前类型 T：转换后
                2.在配置文件中声明转换器
                3.在<annotation-driven>中引用转换器  
    
    *获取请求头
        1.@RequestHeader
          value：请求头名称
          required：是否必须携带次请求头
        2.@CookieValue   
          value：指定cookie的名称
          required：是否携带此cookie
          
    *文件上传
        1.文件上传客户端三要素  
            表单项：type="file"                       
            表单的提交方式必须是：post
            表单的enctype属性是多部分表单形式，enctype="multipaty/form-data"
            
            java web中使用Apache中的fileUpload
            开发步骤：
                导入jar包：commons-fileUpload.jar commons-io.jar
                创建工厂类：
                    DiskFileltemFactory factory = new DiskFileltemFactory();
                创建解析器：
                    ServletFileUpload upload = new ServletFileUpload(factory);    
                使用解析器解析request对象：
                    List<Fileltem> list = upload.parseRequest(request);  
                *一个Fileltem对应一个表单项，Fileltem类的方法：
                    String getField():获取表单项的name的属性值
                    String getName():获取文件字段的文件名，如果为普通字段，返回null
                    String getString():获取字段的内容。如果是普通字段，是value值，文件字段为文件内容
                    String getContentType():获取上传的文件类型，如text/plain、image。如果为普通字段返回值为null
                    String getSize():获取字段内容的大小，单位是字节
                    Boolean isFormField():判断是否是普通表单字段，是为true，否则返回false
                    InputStream getInputStream():获取文件内容的输入流。如果为普通字段，返回value值的输入流      
        文件上传原理
            当form表单修改为多部分表单时，request.getParameter()将失效
            enctype="application/x-www-form-urlencoded"时，form表单的正文内容格式是：
                key=value&key=value&key=value
            当form表单的enctype取值为mutilpart/form-data时，请求正文内容格式就变成多部分形式：
                <input type="text" name="name"> ---> Content-Disposition:form-data;name="name"
                                                     value   
                <input type="file" name="file"> ---> Content-Disposition:form-data;name="file";
                                                     filename="xxx"
                                                     Content-Type:xxx          
                                                     value
        
        SpringMVC实现单文件上传步骤
            1.导入fileupload和io坐标
            2.配置文件上传解析器 CommonsMultipartResolver
                <!--配置文件上传解析器-->
                <bean class="org.springframework.web.multipart.commons.CommonsMultipartResolver" id="multipartResolver">
                    <!--上传文件总大小 5MB 1024*1024*5-->
                    <property name="maxUploadSize" value="5242800"/>
                    <!--上传单个文件的大小-->
                    <property name="maxUploadSizePerFile" value="5242800"/>
                    <!--上传文件的编码类型-->
                    <property name="defaultEncoding" value="UTF-8"/>
                </bean>
            3.编写文件上传代码      
            
## SpringMVC拦截器（interceptor）
    作用：
        SpringMVC的拦截器类似servlet中的过滤器filter，用于对处理器进行预处理和后处理
        将拦截器按一定的顺序联结成一条链（拦截器链），在访问被拦截的方法或字段时，拦截器链中的拦截器就会按其之前定义的顺序被调用
        拦截器也是aop思想的具体实现 
        
    拦截器和过滤器区别
        区别             过滤器                        拦截器
        使用范围       servlet规范中的一部分，任何        SpringMVC框架自己的，只有使用
                     java web工程都可以使用            SpringMVC框架工程才可以使用
        拦截范围       在url-pattren中配置了/*之后，     只会拦截访问的控制器方法，如果访问jsp，html，   
                     可以对所有要访问的资源拦截           css不会进行拦截        
    快速入门：
        1.创建拦截器类实现HandlerInterceptor接口
        2.配置拦截器
        3.测试拦截器的拦截效果                                                               
##SpringMVC异常处理机制
    系统中异常包括两类：预期异常和运行时异常RuntimeException，前者通过捕获异常获得异常信息，后者主要通过规范代码、测试减少
    系统的Dao、Service、Controller出现都通过throws Exception向上抛出，最后由SpringMVC前端控制器交由异常处理器进行异常处理
    
    SpringMVC异常处理两种方式
        1.使用SpringMVC提供的简单异常处理器SimpleMappingExceptionResolver
            <!--异常处理器-->
            <bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
                <!--默认错误视图-->
                <!--<property name="defaultErrorView" value="error"/>-->
                <property name="exceptionMappings">
                    <map>
                        <entry key="java.lang.ClassCastException" value="error1"/>
                        <entry key="com.spring.exception.MyException" value="error2"/>
                    </map>
                </property>
            </bean>
        2.实现Spring的异常处理接口HandlerExceptionResolver自定义自己的异常处理器
            自定义异常处理步骤：  
                1.创建异常处理器类实现HandlerExceptionResolver
                2.配置异常处理器
                3.编写异常页面
                4.测试异常跳转
            