
微服务学习实例，服务参考哔哩哔哩尚硅谷springcloud的课程学习实践，
文档参考学友的CSDN文档：https://blog.csdn.net/u011863024/article/details/114298270


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
