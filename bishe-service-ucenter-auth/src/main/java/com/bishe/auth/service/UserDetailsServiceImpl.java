package com.bishe.auth.service;

import com.bishe.auth.client.UserClient;
import com.bishe.framework.domain.ucenter.ext.BsUserExt;
import com.bishe.framework.domain.ucenter.response.AuthCode;
import com.bishe.framework.exception.ExceptionCast;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ClientDetailsService clientDetailsService;

    @Autowired
    UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if(authentication==null){
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
            if(clientDetails!=null){
                //密码
                String clientSecret = clientDetails.getClientSecret();
                return new User(username,clientSecret,AuthorityUtils.commaSeparatedStringToAuthorityList(""));
            }
        }

        //远程调用用户中心(bishe-ucenter)根据账号查询用户信息
        BsUserExt userExt = userClient.getUserExt(username);
        if(userExt == null){
            return null;
        }
        //
        if("0".equals(userExt.getStatus())){
            ExceptionCast.cast(AuthCode.AUTH_USERNAME_EXPIRE);
        }

        //获取用户权限密码
        List<String> permissionList = userExt.getPermissions();
        //将权限串中间已逗号隔开
        String permissionString = StringUtils.join(permissionList.toArray(), ",");

        //生成用户jwt令牌实体
        UserJwt userDetails = new UserJwt(userExt.getUsername(),
                userExt.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(permissionString));
        userDetails.setUid(userExt.getUid());
        userDetails.setUsername(userExt.getUsername());
        userDetails.setPid(userExt.getPid());
        userDetails.setQq(userExt.getQq());
        userDetails.setPower("test");
       /* UserDetails userDetails = new org.springframework.security.core.userdetails.User(username,
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(""));*/
//                AuthorityUtils.createAuthorityList("course_get_baseinfo","course_get_list"));
        return userDetails;
    }
}
