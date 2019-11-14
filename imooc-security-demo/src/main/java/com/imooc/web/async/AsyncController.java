package com.imooc.web.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

@RestController
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHandler deferredResultHandler;

    private Logger logger = LoggerFactory.getLogger(AsyncController.class);


    @RequestMapping("/order")
    public String order() throws InterruptedException {
        logger.info("主线程开始");
        Thread.sleep(1000);
        logger.info("主线程返回");
        return "success";
    }

    @RequestMapping("/asyncOrder")
    public Callable<String> asyncOrder() {
        logger.info("主线程开始");

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("副线程开始");
                Thread.sleep(1000);
                logger.info("副线程返回");
                return "success";
            }
        };
        logger.info("主线程返回");


        return callable;
    }


     @RequestMapping("/deferredResultOrder")
     public DeferredResult<String> deferredResultOrder() throws InterruptedException {
         logger.info("主线程开始");
         String orderNumber = RandomStringUtils.randomNumeric(8);
         mockQueue.setPlaceOrder(orderNumber);
         DeferredResult<String> result = new DeferredResult<>();
         deferredResultHandler.getMap().put(orderNumber,result);
         logger.info("主线程返回");
         return result;

     }
}
