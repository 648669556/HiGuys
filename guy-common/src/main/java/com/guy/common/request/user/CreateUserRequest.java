package com.guy.common.request.user;

import lombok.Data;

/**
 * @author chenjunhong
 * @createAt 2022-07-14  14:58
 */
@Data
public class CreateUserRequest {
    public String name;
    public String userPassword;
    public Integer dept;

}
