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
    Sha256Util sha256Util;

    @Autowired
    Aes128Util aes128Util;

    @Test
    @DisplayName("SHA256 암호화 테스트")
    void passwordTest() {
        String password = "admin1234";
        String chkEncryptedPassword="ac9689e2272427085e35b9d3e3e8bed88cb3434828b43b86fc0596cad4c6e270";

        String encryptedPassword = sha256Util.passwordEncrypted(password);

        assertThat(encryptedPassword).isEqualTo(chkEncryptedPassword).as("동일하게 암호화 되었는가?");
    }

    @Test
    @DisplayName("AES128 암호화 테스트")
    void userNameEncryptedTest() {
        String userName = "SHINJAEHO";

        String et = aes128Util.encrypted(userName);
        String decryptedText = aes128Util.decrypted(et);

        System.out.println("decryptedText = " + decryptedText);
        assertThat(userName).isEqualTo(decryptedText).as("AES128 암/복호화 테스트");
    }
}