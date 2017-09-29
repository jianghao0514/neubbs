package org.neusoft.neubbs.service;

import org.neusoft.neubbs.entity.UserDO;
import org.neusoft.neubbs.dto.LoginJsonDTO;

/**
 *forum_user表 业务操作接口
 */
public interface IUserService{

    public String saveUserByUser(UserDO user);

    public String removeUserById(Integer id);

    public UserDO getUserById(Integer id);
    public UserDO getUserByName(String name);
    public LoginJsonDTO getLoginJsonByName(String name);

    public String updateUserByUser(UserDO user);

    public String truncateUserTable(String table);
}
