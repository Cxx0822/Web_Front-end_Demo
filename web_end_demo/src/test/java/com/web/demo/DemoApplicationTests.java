package com.web.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void test() {
        boolean result = result();
        System.out.println(result);
        if (result){
            System.out.println("cxx");
        }
    }

    public boolean result(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
}
