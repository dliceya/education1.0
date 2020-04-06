package com.bishe.auth.service;

import com.bishe.auth.client.UserClient;
import com.bishe.framework.domain.ucenter.BsUserExt;
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
        //取出正确密码（hash值）
        String password =userExt.getPassword();
        //用户权限，这里暂时使用静态数据，最终会从数据库读取
        //从数据库获取权限
        UserJwt userDetails = new UserJwt(userExt.getUsername(),
                userExt.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("test, dlice"));
        userDetails.setId(userExt.getUid());
        userDetails.setCompanyId(userExt.getPid());//所属企业
        userDetails.setName(userExt.getUsername());//用户名称
        userDetails.setPower(userExt.getPassword());
       /* UserDetails userDetails = new org.springframework.security.core.userdetails.User(username,
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(""));*/
//                AuthorityUtils.createAuthorityList("course_get_baseinfo","course_get_list"));
        return userDetails;
    }
}
