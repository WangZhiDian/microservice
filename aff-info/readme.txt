
微服务学习实例，服务参考哔哩哔哩尚硅谷springcloud的课程学习实践，
文档参考学友的CSDN文档：https://blog.csdn.net/u011863024/article/details/114298270

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





