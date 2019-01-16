package com.jxy.springMvc.security;

import com.jxy.springMvc.entity.UserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service(value = "userDetailService")
public class UserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserDetail userDetail=new UserDetail();
        if(userName.equals("user")){
            userDetail.setUserName("user");
            userDetail.setRole(Roles.USERROLE);
            userDetail.setUserPass("123456");
        }
        List<GrantedAuthority> list=new ArrayList<>();
        list.add( new SimpleGrantedAuthority(Roles.USERROLE));
        list.add( new SimpleGrantedAuthority(Roles.ADMINROLE));
        User user=new User(userDetail.getUserName(),userDetail.getUserPass(),list);
        return user;
    }
}
