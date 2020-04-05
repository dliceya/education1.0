package com.bishe.framework.domain.ucenter.request;

import com.bishe.framework.model.request.RequestData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest extends RequestData {

    String username;
    String password;
    String verifycode;

}
