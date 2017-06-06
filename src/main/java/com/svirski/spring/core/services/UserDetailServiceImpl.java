package com.svirski.spring.core.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.svirski.spring.core.daos.UserDAO;
import com.svirski.spring.core.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Vasili_Svirski on 6/7/2017.
 */
@Service("userDetailServiceImpl")
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserDAO userDAO;

    @Autowired
    public UserDetailServiceImpl(@Qualifier("userDAO") UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        List<User> users = userDAO.getAllByName(name);

        if (users == null || users.size() == 0){
            throw new UsernameNotFoundException("User with name=" + name + "not found!");
        }

        User user = users.get(0);

        String roles = user.getRoles();

        List<GrantedAuthority> grantedAuthority = new ArrayList<>();
        if (roles != null){
            String[] roleArray = roles.split(",");
            for (String role : roleArray){
                grantedAuthority.add(new SimpleGrantedAuthority(role));
            }
        }

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getName(), hashMD5(user.getPassword()), grantedAuthority);

        System.out.println(userDetails.toString());
        return userDetails;
    }

    private String hashMD5(String value) {
        StringBuilder code = new StringBuilder();

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] bytes = value.getBytes();
        byte[] digest = messageDigest.digest(bytes);
        for (int i = 0; i < digest.length; ++i) {
            code.append(Integer.toHexString(0x0100 + (digest[i] & 0x00FF)).substring(1));
        }

        return code.toString();
    }
}
