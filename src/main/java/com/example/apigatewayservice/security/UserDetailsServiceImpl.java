package com.example.apigatewayservice.security;

import com.example.apigatewayservice.controller.RepositoryController;
import com.example.apigatewayservice.models.UserVO;
import com.example.apigatewayservice.models.UserVODetailsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserVO user = getUserByUsername(s);

        return new UserVODetailsModel(user);
    }

    public UserVO getUserByUsername(String username) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();

        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        ResponseEntity<UserVO> responseEntity = restTemplate.exchange("http://user-service/users/" + username, HttpMethod.GET, entity, UserVO.class);

        return responseEntity.getBody();
    }
}
