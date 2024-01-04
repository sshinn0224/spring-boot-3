package com.example.sample.domain.member;

import com.example.sample.members.service.SampleRedisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RedisGetSetTest {

    @Autowired
    private SampleRedisService sampleRedisService;

    final String KEY = "key";
    final String VALUE = "value";


    @BeforeEach
    void basicData() {
        sampleRedisService.setValues(KEY, VALUE);
    }

    @Test
    @DisplayName("Redis에 저장 된 데이터를 조회 할 수 있다.")
    void redidFindTest() {
        // when
        String findValue = sampleRedisService.getValues(KEY);

        // then
        assertThat(VALUE).isEqualTo(findValue);
    }

    @Test
    @DisplayName("Redis에 저장 된 데이터를 삭제 할 수 있다.")
    void redisDeleteTest() {
        // when
        sampleRedisService.removeValues(KEY);

        String expectEmptyValue = sampleRedisService.getValues(KEY);
        System.out.println("expectEmptyValue = " + expectEmptyValue);
        assertThat("false").isEqualTo(expectEmptyValue);
    }
}
