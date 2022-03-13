package it.controller;

import com.github.pagehelper.PageInfo;
import it.domain.Question;
import it.domain.ResultInfo;
import it.domain.User;
import it.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller("questionController")
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @RequestMapping("count")
    @ResponseBody
    public ResultInfo countToday(ResultInfo rs){
        int i = questionService.countToday();
        rs.setData(i);
        return rs;
    }
    @RequestMapping("all")
    @ResponseBody
    public List<Question> getAll(){
        List<Question> all = questionService.getAll();
        System.out.println(all.get(0).getUploadTime());
        return all;
    }
    @RequestMapping("new")
    @ResponseBody
    public List<Question> getNewest(){

        List<Question> newest = questionService.getNewest();
        return  newest;
    }
    @RequestMapping("getByQid")
    @ResponseBody
    public Question getByQid(int qid){

        Question question = questionService.findByQid(qid);
        return  question;
    }
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo add(String questionName,int uid,String text,ResultInfo rs){
        Question question = new Question();
        User user = new User();
        user.setUid(uid);
        question.setQuestionName(questionName);
        question.setText(text);
        question.setUser(user);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        question.setUploadTime(timestamp);
        int i = questionService.addQuestion(question);
        if (i==0){
            rs.setFlag(false);
            rs.setErrorMessage("发布失败，该标题已存在！");
        }else {
            rs.setFlag(true);
        }
        return rs;
    }
    @RequestMapping("personal")
    @ResponseBody
    public PageInfo<Question> personal(int uid,int currentPage,int pageSize){
        PageInfo<Question> personal = questionService.findPersonal(uid, currentPage, pageSize);
        return personal;
    }
    @RequestMapping("deleteByQid")
    @ResponseBody
    public ResultInfo deleteByQid(int qid,ResultInfo rs){
        int i = questionService.deleteByQid(qid);
        if (i==0){
            rs.setFlag(false);
            rs.setErrorMessage("删除失败");
        }else {
            rs.setFlag(true);
        }
        return rs;


    }
    @RequestMapping("allByPage")
    @ResponseBody
    public PageInfo<Question> allByPage(int currentPage,int pageSize){
        PageInfo<Question> allWithoutAn = questionService.getAllWithoutAn(currentPage, pageSize);
        return allWithoutAn;

    }
}
