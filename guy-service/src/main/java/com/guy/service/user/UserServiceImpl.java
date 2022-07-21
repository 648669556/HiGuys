package com.guy.service.user;

import com.guy.common.entity.UserDO;
import com.guy.common.request.user.CreateUserRequest;
import com.guy.common.util.Md5EncryptionHelper;
import com.guy.common.util.SnowFlake;
import com.zhiyi.generalbeanplus.GeneralBeanService;
import com.zhiyi.generalbeanplus.wrapper.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.apache.ibatis.session.SqlSessionFactory;
import org.assertj.core.util.DateUtil;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chenjunhong
 * @createAt 2022-07-14  14:43
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    public GeneralBeanService generalBeanService;
    public SnowFlake snowFlake;
    public RedissonClient redissonClient;
    public SqlSessionFactory sqlSessionFactory;

    @Override
    public @NonNull Boolean createUser(CreateUserRequest createUserRequest) {
        UserDO param = new UserDO();
        param.setUserName(createUserRequest.getName());
        UserDO oldUser = generalBeanService.queryOne(param);
        if (oldUser != null) throw new RuntimeException("用户已存在");
        long uuid = snowFlake.nextId();
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        String md5WithSalt = Md5EncryptionHelper.getMD5WithSalt(createUserRequest.getUserPassword(), salt);
        UserDO userDO = new UserDO();
        userDO.setUuid(uuid);
        userDO.setUserName(createUserRequest.getName());
        userDO.setDeptId(createUserRequest.getDept());
        userDO.setUserPassword(md5WithSalt);
        userDO.setSalt(salt);

        //随机一个时间
        Random random = new Random();
        long currentTimeMillis = System.currentTimeMillis() - 10000000000L + random.nextInt(2000000000);
        String time = DateUtil.formatAsDatetime(new Date(currentTimeMillis));
        userDO.setCreatedAt(time);
        userDO.setUpdatedAt(time);
        return generalBeanService.add(userDO) > 0;
    }

    @Override
    public Boolean batchCreateUser(List<CreateUserRequest> createUserRequestList) {
        List<String> nameList = createUserRequestList.stream().map(CreateUserRequest::getName).collect(Collectors.toList());
        List<UserDO> userDOS = generalBeanService.queryList(new QueryWrapper<>(UserDO.class).lambda().in(UserDO::getUserName, nameList));
        Map<String, String> userMap = userDOS.stream().collect(Collectors.toMap(UserDO::getUserName, UserDO::getUserName));
        Random random = new Random();

        List<UserDO> addList = new ArrayList<>();
        createUserRequestList.forEach(createUserRequest -> {
            String oldUser = userMap.get(createUserRequest.getName());
            if (oldUser != null) throw new RuntimeException("用户已存在");
            long uuid = snowFlake.nextId();
            String salt = UUID.randomUUID().toString().replaceAll("-", "");
            String md5WithSalt = Md5EncryptionHelper.getMD5WithSalt(createUserRequest.getUserPassword(), salt);
            UserDO userDO = new UserDO();
            userDO.setUuid(uuid);
            userDO.setUserName(createUserRequest.getName());
            userDO.setDeptId(createUserRequest.getDept());
            userDO.setUserPassword(md5WithSalt);
            userDO.setSalt(salt);

            //随机一个时间
            long currentTimeMillis = System.currentTimeMillis() - 10000000000L + random.nextInt(2000000000);
            String time = DateUtil.formatAsDatetime(new Date(currentTimeMillis));
            userDO.setCreatedAt(time);
            userDO.setUpdatedAt(time);
            addList.add(userDO);
        });

        long before = System.currentTimeMillis();
        int add = generalBeanService.add(addList, true);
        long now = System.currentTimeMillis();
        System.out.println("批量插入花费时间:" + (now - before) +"ms");
        return add > 0;
    }
}
