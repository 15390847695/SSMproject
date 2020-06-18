package com.heit.SSM.shiro;

import com.heit.SSM.mapper.tbadminMapper;
import com.heit.SSM.model.tbadmin;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyReaml extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(MyReaml.class);

    @Autowired

    private tbadminMapper tbadminMapper1;
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        tbadmin admin = (tbadmin) principalCollection.getPrimaryPrincipal();
        logger.info("userName = " +admin.getUsername() );
        List<String> list = tbadminMapper1.queryUserPerms(admin.getUsername());
        if(!CollectionUtils.isEmpty(list)) {
            for(int i=0;i<list.size();i++){
                if("".equals(list.get(i))){
                    list.remove(i);
                }
            }
        }

        logger.info("list = " +list.size() );

        logger.info("list = " +list.size() );




        //把用户权限放到shiro中
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        Set<String> stringPermissions = new HashSet<String>();




        stringPermissions.addAll(list);




        simpleAuthorizationInfo.setStringPermissions(stringPermissions);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String)authenticationToken.getPrincipal();
        tbadmin admin =  tbadminMapper1.selectByUserName(userName);
        if(ObjectUtils.isEmpty(admin)){
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(admin,admin.getPassword(),"myReaml");
        return simpleAuthenticationInfo;
    }
}
