package com.bishe.framework.domain.ucenter.ext;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthToken {
    String access_token;    //用户身份令牌
    String refresh_token;   //刷新令牌
    String jti;             //用户访问令牌
}
