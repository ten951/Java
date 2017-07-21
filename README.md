# Java
笔记
deisgn 中包含设计模式等 众多工具类的demo.     
otps是 java微服务的实现,spring boot spring cloud       
java微服务的架构包含 Eureka(服务的发现和治理) Ribbon(客户端负载均衡) Hystrix(服务容错保护) Zuul(API网关服务)     
eureka-server:是注册中心服务器,(高可用实现就是多个server相互在对方服务列表中注册)        
>>服务注册: 服务提供者启动的时候会通过发送REST请求的方式将自己注册到Eureka Server       
>>服务同步: 当服务提供者发送注册请求到一个服务注册中心,他会将该请求转发给集群其他注册中心,从而实现注册中心之间的服务同步       


ribbon-consumer: 负载均衡服务器,负责接收客户端请求,分发给特定的指定server名称的服务提供者.