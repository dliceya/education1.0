package com.bishe.framework.domain.ucenter.ext;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class UserTokenStore extends AuthToken {
    String userId;
    String userType;
    String companyId;
}
