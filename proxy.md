#代理模式
    当一个对象不能直接使用，可以在客户端和目标对象直接创建一个中介，这个中介就是代理。
    使用代理模式的作用
        1.功能增强:在原有的功能上，增加额外的功能，新增加的功能叫做功能增强
        2.控制访问:代理类不让直接访问目标。 如商家不让用户直接访问厂家
        
    实现代理的方式
        1.静态代理:
            优点:
                1)实现简单   
                2)容易理解
            缺点:
                1)当目标类增加，代理类可能也需要成倍的增加，代理类数量过多。
                2)当增加了接口中的功能时，或者修改了功能时，会影响众多的实现类，影响比较多
            
            实现步骤:
                定义一个接口，定义功能
                创建一个类，实现接口
                创建一个代理类，实现接口，完成功能增强
                创建一个客户端类，调用代理类完成功能 
            
            代理类完成的功能:
                1.目标类中方法的调用
                2.功能增强
        2.动态代理
            使用jdk的反射机制，创建对象的能力，创建的是代理类的对象。不是自己创建的类文件，不需要写java文件
            动态:程序执行时，调用jdk提供的方法创建代理类的对象       
            
            特点:不用创建类文件，代理的目标类是活动的，可设置的 
            实现方式:
                1.jdk动态代理:
                    使用java反射包中的类和接口实现动态代理的功能。
                    反射包java.lang.reflect,里面有三个类:InvocationHandler,Method.Proxy
                2.cglib动态代理:
                    cglib是第三方的工具库，创建代理对象                                          
                    cglib的原理是继承，cglib通过继承目标类，创建它的子类，在子类中重写父类中同名的方法，实现功能的修改
                    cglib是通过继承，重写方法，因此目标类不能是final的，方法也不能是final的
        jdk动态代理
            1.反射，Method类，类中的方法。通过Method可以执行某个方法
            2.jdk动态代理的实现:
                反射包java.lang.reflect,里面有三个类:InvocationHandler,Method.Proxy
                1.InvocationHandler接口(调用处理器):只有一个invoke()方法;这个接口表示代理要干什么
                    invoke():表示代理对象要执行的功能代码
                    方法原型:
                        参数:
                            Object proxy:jdk创建的代理对象，无需赋值
                            Method method:目标类中的方法，jdk提供method对象
                            Object[] args:目标类中方法的参数，jdk提供
                        public Object invoke(Object proxy, Method method, Object[] args)                        
                2.Method类:
                    作用:通过Method可以执行某个目标类的方法，Method.invoke();
                        method.invoke(目标对象,方法的参数)        
                3.Proxy类:核心的对象，创建代理对象
                    方法: 静态方法getProxyInstance()        
                    参数:
                        ClassLoader loader:类加载器，负责向内存中加载对象。使用反射获取对象的ClassLoader
                        Class<?>[] interfaces:接口，目标对象实现的接口，反射获取
                        InvocationHandler h:自己写的代理类要完成的功能
                    返回值:代理对象    
                    public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
            3.实现动态代理的步骤:
                1.创建接口，定义目标类要完成的功能
                2.创建目标类实现接口
                3.创建InvocationHandler接口的实现类，在invoke方法中完成代理类的功能
                    1.调用方法
                    2.增强功能
                4.使用Proxy类的静态方法，创建代理对象，并把返回值转为接口类型。  