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

    @Mapper(service = "requestbin.net", path = "/r/${1}")
    interface RBInterface1 {
    
        @Command
        String get(String code);
    }
    
    @Mapper(service = "requestbin.net", path = "/r/1ffqfn01")
    interface RBInterface2 {
    
        @Command
        String get();
    }

>3.创建启动类

    @SpringBootApplication
    public class Application implements CommandLineRunner {
        
        @Autowired
        RBInterface2 int2;
    
        @Override
        public void run(String... args) throws Exception {
            // ...
            int2.get();
        }
    }