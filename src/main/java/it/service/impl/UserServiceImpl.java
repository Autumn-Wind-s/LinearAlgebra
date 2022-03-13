package it.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import it.domain.User;
import it.mapper.UserMapper;
import it.service.UserService;
import it.utils.MailUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(User user) {
        User byUser = this.findUser(user);
        System.out.println(byUser);
        if (byUser == null) {
            return "账号或密码错误!";
        } else if (byUser.getPermissions().equals("普通")) {
            if (byUser.getStatus().equals("正常")) {
                return "普通用户";
            } else return "该用户已被冻结，请联系管理员解冻！";
        } else return "管理员";
    }

    @Override
    public User findUser(User user) {
        return userMapper.findByUser(user);
    }

    @Override
    public String sendValidation(String email, String username) {
        String random = RandomStringUtils.randomAlphabetic(5);
        String text = "尊敬的" + username + "用户您好，您正在进行智极线代网站的注册，以下是您的验证码：" + random + "。请保管好您的个人信息，并在五分钟之内完成注册。如非本人操作，请忽略。";
        boolean flag = MailUtils.sendMail(email, text, "智极线代网站注册验证");
        if (flag) {
            return random;
        } else {
            return "邮箱错误";
        }
    }

    @Override
    public boolean enroll(User user) {
        User byUser = userMapper.findByUser(user);
        if (byUser == null) {
            int i = userMapper.addUser(user);
            if (i == 0) {
                return false;
            } else return true;
        } else return false;
    }

    @Override
    public boolean alterUser(User user) {
        try {
            int i = userMapper.alterUser(user);
            if (i == 0) {
                return false;
            } else return true;

        } catch (Exception e) {
            return false;
        }


    }

    @Override
    public boolean deleteByUid(int uid) {
        try {
            int i = userMapper.deleteByUid(uid);
            if (i == 0) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean alterStatus(User user) {
        try {
            int i = userMapper.alterStatus(user);
            if (i == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public PageInfo<User> findByPage(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<User> allUser = userMapper.findAllUser();
        PageInfo<User> userPageInfo = new PageInfo<>(allUser);
        return userPageInfo;
    }

}
