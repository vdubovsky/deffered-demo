package io.vdubovsky.deffereddemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/deferred")
@Slf4j
public class DeferredDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeferredDemoApplication.class, args);
    }

    @GetMapping
    public DeferredResult<String> getDeferredResult(){
        log.info("Starting get deferred task...");
        DeferredResult<String> result = new DeferredResult<>();
        new Thread(()->{
            try {
                log.info("Waiting...");
                Thread.sleep(10_000);
                log.info("Waiting is completed.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            result.setResult("Hello");
        }).start();

        log.info("Finishing get deferred task.");
        return result;
    }

}
