package org.neusoft.neubbs.dao;

import org.neusoft.neubbs.entity.UserDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserMapper.xml 映射接口
 */
@Repository
public interface IUserDAO {
    int saveUser(UserDO user);

    int removeUserById(Integer id);

    UserDO getUserById(Integer id);
    UserDO getUserByName(String name);
    List<UserDO> getAllAdminUser();
    List<UserDO> getAssignDateRegisterUserByYearMonth(Integer year, Integer month);
    List<UserDO> getAllUser();

    int updateUserPasswordByName(String username, String password);
    int updateUserRankByName(String username, String rank);
}