package com.example.apigatewayservice.security;

import com.example.apigatewayservice.controllers.RepositoryController;
import com.example.apigatewayservice.models.UserVO;
import com.example.apigatewayservice.models.UserVODetailsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RepositoryController repositoryController;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserVO user = repositoryController.getUserByUsername(s);

        return new UserVODetailsModel(user);
    }
}
