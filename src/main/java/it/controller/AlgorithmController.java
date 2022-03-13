package it.controller;

import com.github.pagehelper.PageInfo;
import it.domain.Algorithm;
import it.domain.ResultInfo;
import it.domain.User;
import it.service.AlgorithmService;
import it.utils.DocxUtils;
import it.utils.DownLoadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@RequestMapping("algorithm")
@Controller("algorithmController")
public class AlgorithmController {
    @Autowired
    private AlgorithmService algorithmService;

    @RequestMapping("query")
    @ResponseBody
    public PageInfo<Algorithm> query(int uid, int currentPage, int pageSize, HttpServletRequest request) {
        PageInfo<Algorithm> byPage = algorithmService.findByPage(uid, currentPage, pageSize);
        request.getSession().setAttribute("uid", uid);
        request.getSession().setAttribute("currentPage", currentPage);
        request.getSession().setAttribute("pageSize", pageSize);
        return byPage;
    }

    //,String algorithmName,String text,String username,Algorithm algorithm
    @RequestMapping("visit")
    public ModelAndView visit(int aid, String algorithmName, String text, String username, Algorithm algorithm) {

        algorithm.setAid(aid);
        algorithm.setAlgorithmName(algorithmName);
        algorithm.setText(text);
        User user = new User();
        user.setUsername(username);
        algorithm.setUser(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("algorithm", algorithm);
        modelAndView.setViewName("Algorithm/Visit");
        return modelAndView;
    }

    @RequestMapping("downLoad")
    public void downLoad(String algorithmName, String text, String username, HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("content-type", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        String agent = request.getHeader("user-agent");
        try {
            String name = DownLoadUtils.getFileName(agent, algorithmName);
            response.setHeader("content-disposition", "attachment;filename=" + name + ".docx");
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] sb;
            algorithmName = algorithmName + "\n";
            username = "作者:" + username + "\n";
            sb = algorithmName.getBytes(StandardCharsets.UTF_8);
            outputStream.write(sb);
            sb = username.getBytes(StandardCharsets.UTF_8);
            outputStream.write(sb);
            sb = text.getBytes(StandardCharsets.UTF_8);
            outputStream.write(sb);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(int aid, String algorithmName, String text, Algorithm algorithm, ResultInfo rs) {
        System.out.println(aid);
        System.out.println(algorithmName);
        algorithm.setAlgorithmName(algorithmName);
        algorithm.setAid(aid);
        algorithm.setText(text);
        System.out.println(algorithm);
        boolean b = algorithmService.updateAlgorithm(algorithm);
        if (b){
            rs.setFlag(true);
        }else {
            rs.setFlag(false);
            rs.setErrorMessage("修改失败，算法名已存在，请重新输入！");
        }
        return rs;
    }
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(int aid,Algorithm algorithm,ResultInfo rs){
        algorithm.setAid(aid);
        boolean b = algorithmService.deleteByAid(algorithm);
    if (b){
        rs.setFlag(true);
    }else {
        rs.setFlag(false);
        rs.setErrorMessage("删除失败！");
    }
    return rs;
    }
    @RequestMapping("upload")
    @ResponseBody
    public void upload(@RequestParam("file") MultipartFile multipartFile,String algorithmName,Algorithm algorithm,HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        // 获取上传的文件名
        String fileName = multipartFile.getOriginalFilename();
        String Suffix=fileName.substring(fileName.lastIndexOf("."));
        PrintWriter writer = response.getWriter();
        try {
            InputStream  inputStream = multipartFile.getInputStream();
            String text;
            if (".docx".equals(Suffix)){
                 text = DocxUtils.dealDocXFile(inputStream);

            }else {
                byte[] bytes= new byte[inputStream.available()];
                inputStream.read(bytes);
                 text = new String(bytes);
            }
            algorithm.setAlgorithmName(algorithmName);
            algorithm.setText(text);
            if (request.getHeader("referer").equals("http://localhost:8080/LinearAlgebra/AlgorithmManage/AlgorithmManage.jsp")
            ){
                User user = (User) request.getSession().getAttribute("admin");
                algorithm.setUser(user);
                boolean b = algorithmService.uploadAlgorithm(algorithm);
                String requestURI = request.getRequestURI();
                if (b)
                {  writer.write("文件上传成功,<a href=\"../AlgorithmManage/AlgorithmManage.jsp\">点击返回</a>\n");
                }else {
                    writer.write("算法名重复，文件上传失败,<a href=\"../AlgorithmManage/AlgorithmManage.jsp\">点击返回</a>\n");

                }
            }
            else {
            User user = (User) request.getSession().getAttribute("user");
            algorithm.setUser(user);
            boolean b = algorithmService.uploadAlgorithm(algorithm);
            String requestURI = request.getRequestURI();
            if (b)
            {  writer.write("文件上传成功,<a href=\"../Algorithm.jsp\">点击返回</a>\n");
            }else {
                writer.write("算法名重复，文件上传失败,<a href=\"../Algorithm.jsp\">点击返回</a>\n");

            }}
        } catch (IOException e) {
            writer.write("文件解析失败,<a href=\"../Algorithm.jsp\">点击返回</a>\n");
        }
    }
    @RequestMapping("findByKey")
    @ResponseBody
    public PageInfo<Algorithm> findByKey(String key,int currentPage,int pageSize){
        PageInfo<Algorithm> byKey = algorithmService.findByKey(key, currentPage, pageSize);
        return byKey;
    }
}
