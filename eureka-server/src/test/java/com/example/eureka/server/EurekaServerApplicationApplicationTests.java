package com.example.eureka.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class EurekaServerApplicationApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void getReverNum(){
        List<Integer> list=new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        Collections.reverse(list);

        for(int i=0;i<3;i++){
            System.out.println(list.get(i));
        }

    }

}
