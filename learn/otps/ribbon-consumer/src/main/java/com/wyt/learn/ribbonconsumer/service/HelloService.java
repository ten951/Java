package com.wyt.learn.ribbonconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.wyt.learn.ribbonconsumer.model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author Darcy
 * Created by Darcy on 2017/7/25.
 */
@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;


    /**
     * 批量请求合并器
     * timerDelayInMilliseconds (合并时间窗设置为100毫秒)
     *
     * @param id 主键
     * @return
     */
    @HystrixCollapser(batchMethod = "findAll", collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds", value = "100")
    })
    public User find(Long id) {
        return null;
    }

    /**
     * 批量查询用户信息
     *
     * @param ids 主键集合
     * @return
     */
    @HystrixCommand
    public List findAll(List<Long> ids) {
        return restTemplate.getForObject("http://USER-SERVICE/users?ids={1}", List.class, StringUtils.join(ids, ","));
    }

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloService() {
        return restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();
    }

    /**
     * 通过主键id查询用户(同步方式)
     *
     * @param id 主键
     * @return
     */
    @CacheResult(cacheKeyMethod = "getUserByIdCacheKey")
    @HystrixCommand(fallbackMethod = "defaultUser")
    public User getUserById(final Long id) {
        return restTemplate.getForObject("http://USER-SERVICE/users/{1}", User.class, id);
    }

    private Long getUserByIdCacheKey(Long id) {
        return id;
    }

    /**
     * 主键查询用户(异步方式)
     * commandKey 命令名称
     * groupKey 组名
     * threadPoolKey 线程池名称
     *
     * @param id 主键
     * @return
     */
    @HystrixCommand(fallbackMethod = "defaultUser", commandKey = "getUserById", groupKey = "UserGroup", threadPoolKey = "getUserByThread")
    public Future<User> getUserByIdAsync(final Long id) {
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                return restTemplate.getForObject("http://USER-SERVICE/users/{1}", User.class, id);
            }
        };
    }

    /**
     * 通过主键查询User(观察者-订阅者模式 observe模式)
     * HystrixCommand(observableExecutionMode = ObservableExecutionMode.LAZY) (toObservable模式)
     *
     * @param id 主键
     * @return
     */
    @CacheResult
    @HystrixCommand(observableExecutionMode = ObservableExecutionMode.EAGER, fallbackMethod = "defaultUser")
    public Observable<User> getUserByIdOb(@CacheKey("id") final Long id) {
        return Observable.create(observer -> {
            if (!observer.isUnsubscribed()) {
                User user = restTemplate.getForObject("http://USER-SERVICE/users/{1}", User.class, id);
                observer.onNext(user);
                observer.onCompleted();
            }
        });
    }


    /**
     * 更新用户并且清除缓存
     * commandKey 命令名称
     * CacheKey 缓存key
     *
     * @param user 用户
     */
    @CacheRemove(commandKey = "getUserById")
    @HystrixCommand()
    public void update(@CacheKey("id") User user) {
        restTemplate.postForObject("http://USER-SERVICE/users/", user, User.class);
    }

    @HystrixCommand(fallbackMethod = "defaultUserSec")
    public User defaultUser() {
        //此处可能是另一个网络请求来获取,所以也有失败的可能
        return new User("First Fallback");
    }

    public User defaultUserSec() {
        return new User("Second Fallback");
    }

    public String helloFallback() {
        return "error";
    }
}
