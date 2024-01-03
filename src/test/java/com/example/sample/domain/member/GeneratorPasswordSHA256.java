package com.example.sample.domain.member;

import com.example.sample.common.utils.Aes128Util;
import com.example.sample.common.utils.Sha256Util;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class GeneratorPasswordSHA256 {

    @Autowired
    Aes128Util aes128Util;

    @Test
    @DisplayName("AES128 암호화 테스트")
    void userNameEncryptedTest() {
        String userName = "SHINJAEHO";

        String et = aes128Util.encrypted(userName);
        String decryptedText = aes128Util.decrypted(et);

        assertThat(userName).isEqualTo(decryptedText).as("AES128 암/복호화 테스트");
    }
}