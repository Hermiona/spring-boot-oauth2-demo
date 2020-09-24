package com.meerim_task.demo.config.dto;

import lombok.Data;

@Data
public class GoogleOAuth2UserInfo {
    private String name;
    private String email;
    private String sub;
}
