package it.mapper;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import it.domain.User;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface UserMapper {
    User findByUser(User user);
    List<User> findAllUser();
    int addUser(User user);
    int alterUser(User user);
    int deleteByUid(@Param("uid") int uid);
    int alterStatus(User user);

}
