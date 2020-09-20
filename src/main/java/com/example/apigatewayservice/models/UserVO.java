package com.example.apigatewayservice.models;

import java.util.Objects;
import java.util.Set;

public class UserVO {

    private String userName;

    private String password;

    private Set<RoleVO> roleVOS;

    public UserVO() {
    }

    public UserVO(UserVO userVO){
        this.password = userVO.getPassword();
        this.roleVOS = userVO.getRoles();
        this.userName = userVO.getUserName();
    }

    public UserVO(String userName, String password, Set<RoleVO> roleVOS) {
        this.userName = userName;
        this.password = password;
        this.roleVOS = roleVOS;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleVO> getRoles() {
        return roleVOS;
    }

    public void setRoles(Set<RoleVO> roleVOS) {
        this.roleVOS = roleVOS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserVO userVO = (UserVO) o;
        return Objects.equals(userName, userVO.userName) &&
                Objects.equals(password, userVO.password) &&
                Objects.equals(roleVOS, userVO.roleVOS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, roleVOS);
    }
}
