package com.meerim_task.demo.service;

import com.meerim_task.demo.config.dto.GoogleOAuth2UserInfo;
import com.meerim_task.demo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class CustomOidcUserService extends OidcUserService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        Map attributes = oidcUser.getAttributes();
        GoogleOAuth2UserInfo userInfo = new GoogleOAuth2UserInfo();
        userInfo.setEmail((String) attributes.get(StandardClaimNames.EMAIL));
        userInfo.setName((String) attributes.get(StandardClaimNames.NAME));
        userInfo.setSub((String) attributes.get(StandardClaimNames.SUB));
        updateUser(userInfo);
        return oidcUser;
    }

    private User updateUser(GoogleOAuth2UserInfo userInfo) {
        return userService.findByUsername(userInfo.getEmail()).orElseGet(() -> {
            var newUser = new User();
            newUser.setUsername(userInfo.getEmail());
            newUser.setFullname(userInfo.getName());
            newUser.setPassword(passwordEncoder.encode(userInfo.getSub()));
            return userService.add(newUser);
        });
    }
}
