package com.socialmedia.socialmediaapp.service.impl;

import com.socialmedia.socialmediaapp.entity.RoleEntity;
import com.socialmedia.socialmediaapp.entity.UserEntity;
import com.socialmedia.socialmediaapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SocialMediaBlogCustomizedUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String usernameorEmail) throws UsernameNotFoundException {

        //Load the user by username or email
        UserEntity userEntity=this.userRepository.findByUsernameOrEmail(usernameorEmail,usernameorEmail)
                .orElseThrow(()->new UsernameNotFoundException("User not found with Email or Username::"+usernameorEmail));

       //Fetch all the roles associated with user
        Set<RoleEntity> userRoles=userEntity.getRoles();

        //covert or map roles into granted authority
        Set<GrantedAuthority> grantAuthoritySet=userRoles.stream().map(role->new SimpleGrantedAuthority(role.getName()))
                .collect((Collectors.toSet()));

        User user= new User(userEntity.getEmail(),userEntity.getPassword(),grantAuthoritySet);
        return null;
    }
}
