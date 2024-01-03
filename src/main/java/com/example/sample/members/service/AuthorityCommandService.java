package com.example.sample.members.service;

import com.example.sample.common.types.AuthType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorityCommandService {

    @Transactional
    public void saveMemberAuthority(String userName, AuthType authType) {
        // 해당 유저가 권한을 가지고 있는지 확인 한다.
        // 유저의 권한 정보를 저장 한다
    }

    @Transactional
    public void removeMemberAutority(String userName, AuthType authType) {
        // 해당 유저가 권한을 가지고 있는지 확인 한다.
        // 유저의 권한 정보를 삭제 한다.

    }

    private void verityExistUserAuthority(String userName, AuthType authType) {

    }
}
