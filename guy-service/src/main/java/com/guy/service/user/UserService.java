package com.guy.service.user;

import com.guy.common.request.user.CreateUserRequest;

import java.util.List;

public interface UserService {

    /**
     * 创建一个用户
     */
     Boolean createUser(CreateUserRequest createUserRequest);

    /**
     * 批量生成对象
     */
    Boolean batchCreateUser(List<CreateUserRequest> createUserRequestList);



}
