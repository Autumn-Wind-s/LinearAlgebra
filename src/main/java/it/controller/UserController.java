package it.controller;

import com.github.pagehelper.PageInfo;
import it.domain.ResultInfo;
import it.domain.User;
import it.mapper.UserMapper;
import it.service.UserService;
import it.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequestMapping("user")
@Controller("userController")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/userLogin")
    @ResponseBody
    public ResultInfo UserLogin(User user, HttpServletRequest request, ResultInfo resultInfo) throws IOException {
        String login = userService.login(user);
        if (login.equals("普通用户") || login.equals("管理员")) {
            if (login.equals("普通用户")) {
                resultInfo.setFlag(true);
                User user1 = userService.findUser(user);
                request.getSession().setAttribute("user", user1);
                resultInfo.setData(login);
            } else {
                resultInfo.setFlag(false);

                resultInfo.setErrorMessage("该用户为管理员，请转至后台登录！");
            }

        } else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMessage(login);
        }
        return resultInfo;
    }

    @RequestMapping("/adminLogin")
    @ResponseBody
    public ResultInfo AdminLogin(User user, HttpServletRequest request, ResultInfo resultInfo) throws IOException {

        String login = userService.login(user);
        if (login.equals("普通用户") || login.equals("管理员")) {
            if (login.equals("管理员")) {
                resultInfo.setFlag(true);
                User user1 = userService.findUser(user);
                request.getSession().setAttribute("admin", user1);
                resultInfo.setData(login);
            } else {
                resultInfo.setFlag(false);
                resultInfo.setErrorMessage("该用户为普通用户，请转至前台登录！");
            }

        } else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMessage(login);
        }
        return resultInfo;
    }

    @RequestMapping("validation")
    @ResponseBody
    public ResultInfo Validation(String username, String email, HttpServletRequest request, ResultInfo resultInfo) {
        System.out.println(username);
        String s = userService.sendValidation(email, username);
        if (s.equals("邮箱错误")) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMessage(s);
        } else {
            request.getSession().setAttribute("validation", s);
            request.getSession().setAttribute("EnrollUsername", username);
            resultInfo.setFlag(true);
        }
        return resultInfo;
    }

    @RequestMapping("enroll")
    @ResponseBody
    public ResultInfo Enroll(User user, String validation, HttpServletRequest request, ResultInfo rs) throws Exception {
        ResultInfo resultInfo = new ResultInfo();

        if (request.getHeader("referer").equals("http://localhost:8080/LinearAlgebra/UserEnroll.jsp")) {
            if (request.getSession().getAttribute("EnrollUsername") != null) {
                if (user.getUsername().equals(request.getSession().getAttribute("EnrollUsername"))) {
                    if (validation.equals(request.getSession().getAttribute("validation"))) {
                        request.getSession().removeAttribute("validation");
                        request.getSession().removeAttribute("EnrollUsername");
                        boolean flag = userService.enroll(user);
                        if (flag) {
                            resultInfo.setFlag(true);
                        } else {
                            resultInfo.setFlag(false);
                            resultInfo.setErrorMessage("用户名重复！请更换用户名并重新验证。");
                        }
                    } else {
                        resultInfo.setFlag(false);
                        resultInfo.setErrorMessage("验证码错误！");
                    }

                } else {
                    resultInfo.setFlag(false);
                    resultInfo.setErrorMessage("用户名不一致！请更换用户名并重新验证。");
                }
            } else {
                resultInfo.setFlag(false);
                resultInfo.setErrorMessage("请先发送验证码！");

            }
        } else {
            boolean flag = userService.enroll(user);
            if (flag) {
                resultInfo.setFlag(true);
            } else {
                resultInfo.setFlag(false);
                resultInfo.setErrorMessage("用户名重复！请更换用户名并重新验证。");
            }
        }
        return resultInfo;
    }

    @RequestMapping("alter")
    @ResponseBody
    public ResultInfo Alter(User user, HttpServletRequest request, ResultInfo rs) {

        User user1 = (User) request.getSession().getAttribute("user");
        if (user1.getPassword().equals(request.getParameter("prePassword"))) {
            user.setUid(user1.getUid());
            System.out.println(user);
            boolean b = userService.alterUser(user);
            if (b) {
                rs.setFlag(true);
            } else {
                rs.setFlag(false);
                rs.setErrorMessage("修改失败，用户名已存在！");
            }
        } else {
            rs.setFlag(false);
            rs.setErrorMessage("修改失败，原密码错误！");
        }
        return rs;
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo Update(User user, ResultInfo rs) {
        System.out.println(user);
        boolean b = userService.alterUser(user);
        if (b) {
            rs.setFlag(true);
        } else {
            rs.setFlag(false);
            rs.setErrorMessage("修改失败，用户名已存在！");
        }
        return rs;
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo Delete(int uid, ResultInfo rs) {

        boolean b = userService.deleteByUid(uid);
        if (b) {
            rs.setFlag(true);
        } else {
            rs.setFlag(false);
            rs.setErrorMessage("删除失败！");
        }
        return rs;
    }

    @RequestMapping("alterStatus")
    @ResponseBody
    public ResultInfo AlterStatus(User user, ResultInfo rs) {
        System.out.println("ads");
        System.out.println(user);
        if (user.getStatus().equals("解冻")) {
            user.setStatus("正常");
        }
        boolean b = userService.alterStatus(user);
        if (b) {
            rs.setFlag(true);
        } else {
            rs.setFlag(false);
        }
        return rs;
    }

    @RequestMapping("out")
    @ResponseBody
    public ResultInfo Out(HttpServletRequest request, ResultInfo rs) {
        try {
            request.getSession().invalidate();
            rs.setFlag(true);
        } catch (Exception e) {
            rs.setFlag(false);
            rs.setErrorMessage("退出失败");
        }
        return rs;
    }

    @RequestMapping("findByPage")
    @ResponseBody
    public PageInfo<User> FindByPage(int currentPage, int pageSize, HttpServletRequest request) {
        if (currentPage == 0) {
            System.out.println(currentPage);
            currentPage = 1;
        }
        PageInfo<User> byPage = userService.findByPage(currentPage, pageSize);
        request.getSession().setAttribute("userCurrentPage", currentPage);
        return byPage;
    }
}