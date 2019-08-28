## fuck-rpc
fresh-rpc k8s mode with flexible path definition.

#### How to use
>1.先引入依赖包

    <dependency>
        <groupId>com.shihang</groupId>
        <artifactId>fuck-rpc-spring-boot-starter</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
    

>2.定义接口

    @Mapper(service = "java2test", namespace = "users")
    public interface TestInterface {
    
        @Command
        int create(String city, User user);
    }

或者

    @Mapper(service = "java2test", path = "users/TestInterface")
    public interface TestInterface {
    
        @Command
        int create(User user);
    }

>3.创建启动类

    @SpringBootApplication
    public class Application implements CommandLineRunner {
        
        @Autowired
        TestInterface test;
    
        @Override
        public void run(String... args) throws Exception {
            User user = new User();
            // ...
            test.create(user);
        }
    }