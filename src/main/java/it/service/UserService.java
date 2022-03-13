package it.service;

import com.github.pagehelper.PageInfo;
import it.domain.User;

public interface UserService {
    String login(User user);
    User  findUser(User user);
    String sendValidation(String email,String username);
    boolean enroll(User user) ;
    boolean alterUser(User user);
    boolean deleteByUid(int uid);
    boolean alterStatus(User user);
    PageInfo<User> findByPage(int currentPage,int pageSize);
}
