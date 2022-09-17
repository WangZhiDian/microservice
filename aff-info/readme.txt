
微服务学习实例，服务参考哔哩哔哩尚硅谷springcloud的课程学习实践，
文档参考学友的CSDN文档：https://blog.csdn.net/u011863024/article/details/114298270

git add 远程：
git remote set-url origin https://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@github.com/WangZhiDian/springcloud-config.git

一 注册中心：
Eureka启动后，访问url：http://localhost:7001
服务注册，启动类添加注解：@EnableEurekaClient  @EnableEurekaServer


服务发现：启动类添加注解 ：@EnableDiscoveryClient//添加该注解
引用该对象可以查询注册中心的信息：
@Resource
private DiscoveryClient discoveryClient;


注册中心console安装文档：
https://learn.hashicorp.com/tutorials/consul/get-started-install?in=consul/getting-started
http://www.zzvips.com/article/142903.html
启动： consul agent -dev
停止: consul leave

consul agent -bind=0.0.0.0 -client=172.16.40.101 -data-dir=data -node=172.16.40.101 -join=192.168.1.100
consul agent -server -bootstrap -bind=172.16.40.101 -client=172.16.40.101 -data-dir=data -ui -node=172.16.40.101
172.16.40.101 为vm虚机IP


二 负载均衡
使用ribbon组件，1 在RestTemplate上添加@LoadBalance 2 在主启动类上添加@RibbonClient 3 消费端
调用时，url域名为服务端注册的域名 4 可以自定义ribbon规则，在步骤2时，个性化使用
注：个性化定义时，自定义类和主启动类不在同一个包下，否则所有的ribbon调用，都会共享到这个规则
引用包：spring-cloud-starter-netflix-ribbon
ribbon 可以类似理解为用discoveryClient服务发现的方式，从服务提供着中找到一个可以提供服务的节点


三 服务rpc封装调用OpenFeign
feign自带负载均衡配置功能，继承了ribbon，openfeign在feign的基础上，添加了springMvc的解析
将远端的接口调用本地定义后，本地化调用，进一步简化rpc调用,默认使用轮训方式访问服务端
主启动类开启feign注解：@EnableFeignClients
远程定义接口注解：@FeignClient(value = "注册中心的服务提供者名称"） 定义接口中，定义好服务端提供的接口
使用ribbon.timeout配置可以设置feign调用远端服务的超时时间，默认1秒，很可能会超时

四 服务降级Hystrix
Hystrix可以在服务方法执行超时，异常，或者服务当机后，提供一个预设返回值给调用端
服务降级可以配置在服务提供侧，也可以配置在服务调用侧，效果不一样，但目的相似。验证时，可以
在调用侧将application.yaml中feign但超时时间定位1秒，feign注解标识超时响应方法，然后服务侧方法超时定位5秒，服务方法上但降级时间定位3秒来测试几种情况
开启需要在启动类上添加@@EnableHystrix， 需要降级的方法上，添加@HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
                                                   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")})
可以使用@DefaultProperties(defaultFallback = "") 做一个通用的降级方法，用在普通方法上面；但这样可能分不清是本段逻辑超时还是本段逻辑的依赖超时
可以开启Feign调用对Hystrix的继承，使用配置： feign.hystrix.enabled: true

五 服务熔断Hystrix
熔断即指的是监控的方法，在制定的规则下，比如10S内有10个请求，其中60%都失败了，这个时候，断路器开启，
后续请求进来时，直接通过熔断走降级的方法，当默认时间窗口过了后，半熔断开启请求访问，如果正常，则断路器
关闭，如果还是不正常，则断路器重新开始计时
开启的注解仍然是：@HystrixCommand ,其中的HystrixProperties属性指定熔断规则
在主启动类上开启熔断注解
Hystrix除了功能，还有监控界面，通过提供的spring-cloud-starter-netflix-hystrix-dashboard来实现

六 网关GateWay
spring-gateway是基于spring6 Reactor springboot2的基础上实现的，异步非阻塞方式运行
同时集成了ribbon，euraka，hystrix等功能，在网络的位置处于入口处，暴露接口
组成部分：路由Route，断言Predict，拦截器Filter


七 配置中心config
配置中心用来统一管理微服务集群中的配置，通过spring-cloud-config-server创建配置服务端，服务端配置从git代码库拉取配置
和spring-cloud-starter-config 来创建拉去配置的客户端，客户端配置从服务端拉去配置
如果服务端修改了， 客户端需要实时动态刷新，需要在客户端主启动类添加@RefreshScope注解，和spring-cloud-starter-actuator监控
并且先刷新：http://localhost:3355/actuator/refresh，客户端才能监控形式刷新

八 消息总线
消息总线，配合配置中心使用，即不能配置中心每次修改了，都需要手动的调用接口来刷新客户端拉取配置，得配置成自动的
安装rocket时遇到的一个坑: 单机安装好rocketmq后，使用安装命令测试：sh bin/tools.sh org.apache.rocketmq.example.quickstart.Producer
提示：java.lang.IllegalStateException: org.apache.rocketmq.remoting.exception.RemotingConnectException: connect to null failed
原因：tool命令没有写namesrv的变量，导致找不到：https://icode.best/i/81947046528911
rabbitmq安装：https://blog.51cto.com/u_15444123/4715185
rabbitmq安装的erLong和rabbitmq需要配套，否则安装会异常
登录rabitmq guest异常，使用： https://blog.csdn.net/itcsdn_/article/details/108928571

九 中间件屏蔽工具Stream Binder
