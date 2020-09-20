package com.example.apigatewayservice.controllers;

import com.example.apigatewayservice.beans.JwtConfig;
import com.example.apigatewayservice.models.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RepositoryController {

    private final RestTemplate restTemplate;
    private final JwtConfig jwtConfig;

    @Autowired
    public RepositoryController(RestTemplate restTemplate, JwtConfig jwtConfig) {
        this.restTemplate = restTemplate;
        this.jwtConfig = jwtConfig;
    }

    MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();

    public UserVO getUserByUsername(String username) {

        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        ResponseEntity<UserVO> responseEntity = restTemplate.exchange("http://user-service/user/getUserByUsername?username=" + username, HttpMethod.GET, entity, UserVO.class);

        return responseEntity.getBody();
    }

    @PreAuthorize("hasAuthority('make:orders')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @ApiOperation(notes = "Write the response from /api/login into Authorization header", value = "Make a new order")
    @PostMapping(value = "/newOrder")
    public void newOrder(@RequestBody OrderVO orderVO) {

        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpEntity<OrderVO> request = new HttpEntity<OrderVO>(orderVO, headers);
        HttpEntity<String> response = restTemplate.exchange("http://repository-service/order/newOrder", HttpMethod.POST, request, String.class);
    }

    @PreAuthorize("hasAuthority('see:orders')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @ApiOperation(notes = "Write the response from /api/login into Authorization header", value = "Get all orders by a specific user id")
    @GetMapping(value = "/getOrdersByUserId")
    public List<OrderVO> getOrders(@RequestParam("id") int id) {

        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        ResponseEntity<OrderVO[]> responseEntity = restTemplate.exchange("http://repository-service/order/getOrdersByUserId?id=" + id, HttpMethod.GET, entity, OrderVO[].class);

        return Arrays.asList(responseEntity.getBody());
    }

    @PreAuthorize("hasAuthority('see:orders')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @ApiOperation(notes = "Write the response from /api/login into Authorization header", value = "Get all orders")
    @GetMapping(value = "/getAllOrders")
    public List<OrderVO> getAllOrders() {

        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        ResponseEntity<OrderVO[]> responseEntity = restTemplate.exchange("http://repository-service/order/getAllOrders", HttpMethod.GET, entity, OrderVO[].class);

        return Arrays.asList(responseEntity.getBody());
    }


    @PreAuthorize("hasAuthority('see:stock')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @ApiOperation(notes = "Write the response from /api/login into Authorization header", value = "Get info of all products")
    @GetMapping("/getAllProducts")
    public List<ProductVO> getProducts() {

        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        ResponseEntity<ProductVO[]> responseEntity = restTemplate.exchange("http://repository-service/product/getAllProducts", HttpMethod.GET, entity, ProductVO[].class);

        return Arrays.asList(responseEntity.getBody());
    }

    @PreAuthorize("hasAuthority('add:stock')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @ApiOperation(notes = "Write the response from /api/login into Authorization header", value = "Update products amount")
    @PostMapping("/updateProduct")
    void ProductUpdate(@RequestBody UpdateProductVO updateProductVO) {

        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpEntity<UpdateProductVO> request = new HttpEntity<UpdateProductVO>(updateProductVO, headers);
        HttpEntity<String> response = restTemplate.exchange("http://repository-service/product/updateProduct", HttpMethod.POST, request, String.class);
    }

    @PreAuthorize("hasAuthority('see:changes')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @ApiOperation(notes = "Write the response from /api/login into Authorization header", value = "Get previous updates on products amount")
    @GetMapping("/getUpdateHistory")
    List<UpdateProductVO> updatedProducts() {

        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        ResponseEntity<UpdateProductVO[]> responseEntity = restTemplate.exchange("http://repository-service/product/getUpdateHistory", HttpMethod.GET, entity, UpdateProductVO[].class);

        return Arrays.asList(responseEntity.getBody());
    }

    @PostMapping("/login")
    @ApiOperation(notes = "Try username : mackastamacka, password : macka", value = "Get the Authorization header")
    public String login(@RequestBody Login login) {

        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpEntity<Login> request = new HttpEntity<Login>(login, headers);
        HttpEntity<String> response = restTemplate.exchange("http://api-gateway-service/login", HttpMethod.POST, request, String.class);
        HttpHeaders headers = response.getHeaders();

        return headers.getFirst(jwtConfig.getAuthorizationHeader());
    }
}
