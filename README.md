# SPRINGCLOUD
eureka-demo使用eureka作为注册中心，实现服务发现与注册，配置信息如下:


main函数添加


@EnableEurekaServer

    server:
      port: 10087
    spring:
      application:
        name: eureka-service
    eureka:
      client:
       fetch-registry: true
        register-with-eureka: true
       service-url:
          defaultZone: http://127.0.0.1:10087/eureka
      server:
        eviction-interval-timer-in-ms: 1000  # 服务驱逐时间
         enable-self-preservation: false   # 关闭自我保护

produce-demo作为服务提供方，注册服务，配置信息如下：


main函数添加

@EnableDiscoveryClient


    spring:
      datasource:
        url: jdbc:mysql://127.0.0.1:3306/test
        username: root
        password: 123456
        hikari:
          maximum-pool-size: 20
          minimum-idle: 10
        driver-class-name: com.mysql.jdbc.Driver
      application:
        name: produce
    eureka:
      client:
        service-url:
          defaultZone: http://127.0.0.1:10087/eureka
      instance:
        lease-expiration-duration-in-seconds: 90
        lease-renewal-interval-in-seconds: 30
        instance-id: ${spring.application.name}:${server.port}
    server:
      port: 8083
      
      
consumer-demo作为服务消费放，发现服务，配置信息如下：
  
  main函数添加

@EnableDiscoveryClient


@EnableHystrix


@EnableFeignClients
  
  
     spring:
        application:
          name: consumer
        cloud:
          loadbalancer:
            retry:
              enabled: true     #当使用ribbon负载均衡地时候，防止获取挂掉的服务，使用重试机制
      server:
        port: 8081
      eureka:
        client:
          service-url:
            defaultZone: http://127.0.0.1:10087/eureka
          registry-fetch-interval-seconds: 5  #拉取服务
        instance:
          instance-id: ${spring.application.name}:${server.port}

      ribbon:
        NFLoadBalanceRuleClassName: com.netflix.loadbalancer.RandomRule
        ConnectTimeout: 250 # ribbon的连接超时时间
        ReadTimeout: 1000 # ribbon读取数据的超时时间
        OkToRetryOnAllOperations: true #是否对所有操作进行重试
        MaxAutoRetriesNextServer: 1 #切换实例的重试次数
        MaxAutoRetries: 1 #对当前实例的重试次数
      hystrix:
        command:
          default:  #default全局有效，service id指定应用有效
            execution:
              timeout:
                #如果enabled设置为false，则请求超时交给ribbon控制,为true,则超时作为熔断根据
                enabled: true
              isolation:
                thread:
                  timeoutInMilliseconds: 1000 #断路器超时时间，默认1000ms

      #feign.hystrix.enabled: true
      feign:
        hystrix:
          enabled: true
          
          
zuul-demo 作为服务网关，做访问权限控制，接口过滤拦截，配置信息如下：


  main函数添加


@EnableDiscoveryClient


@EnableZuulProxy
  
  
 拉去服务列表，动态路由
 
      spring:
        application:
          name: api-getway
      server:
        port: 9000
      zuul:
        retryable: true
        prefix: /api
        routes:
          produce: /produce/**
      eureka:
        client:
          registry-fetch-interval-seconds: 5
          service-url:
            defaultZone: http://127.0.0.1:10087/eureka
      ribbon:
        NFLoadBalanceRuleClassName: com.netflix.loadbalancer.RandomRule
        ConnectTimeout: 250 # ribbon的连接超时时间
        ReadTimeout: 1000 # ribbon读取数据的超时时间
        OkToRetryOnAllOperations: true #是否对所有操作进行重试
        MaxAutoRetriesNextServer: 1 #切换实例的重试次数
        MaxAutoRetries: 1 #对当前实例的重试次数
      hystrix:
        command:
          default: #default全局有效，service id指定应用有效

      execution:
        isolation:
          thread:
            timeoutInMilliseconds: 60000 #断路器超时时间，默认1000ms
