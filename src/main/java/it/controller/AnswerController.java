package it.controller;

import com.github.pagehelper.PageInfo;
import it.domain.Answer;
import it.domain.Question;
import it.domain.ResultInfo;
import it.domain.User;
import it.service.AnswerService;
import it.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller("answerController")
@RequestMapping("answer")
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @RequestMapping("countToday")
    @ResponseBody
    public ResultInfo countToday(ResultInfo rs) {
        int i = answerService.countToday();
        rs.setData(i);
        return rs;
    }

    @RequestMapping("countAll")
    @ResponseBody
    public ResultInfo countAll(ResultInfo rs) {
        int i = answerService.countAll();
        rs.setData(i);
        return rs;
    }

    @RequestMapping("new")
    @ResponseBody
    public List<Answer> getNewest() {
        List<Answer> newest = answerService.getNewest();
        return newest;
    }

    @RequestMapping("getByQid")
    @ResponseBody
    public List<Answer> getByQid(int qid) {
        List<Answer> newest = answerService.getByQid(qid);
        return newest;
    }

    @RequestMapping("add")
    @ResponseBody
    public ResultInfo add(int qid, int uid, String text, ResultInfo rs) {
        Answer answer = new Answer();
        User user = new User();
        user.setUid(uid);
        Question question = new Question();
        question.setQid(qid);
        answer.setUser(user);
        answer.setQuestion(question);
        answer.setText(text);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        answer.setPublishtime(timestamp);
        int i = answerService.addAnswer(answer);
        if (i == 0) {
            rs.setFlag(false);
            rs.setErrorMessage("评论失败,请重试！");
        } else {
            rs.setFlag(true);
        }
        return rs;
    }

    @RequestMapping("personal")
    @ResponseBody
    public PageInfo<Answer> personal(int uid, int currentPage, int pageSize) {
        PageInfo<Answer> personal = answerService.findPersonal(uid, currentPage, pageSize);
        return personal;

    }

    @RequestMapping("deleteByAid")
    @ResponseBody
    public ResultInfo deleteByAid(int aid, ResultInfo rs) {
        int i = answerService.deleteByAid(aid);
        if (i == 0) {
            rs.setFlag(false);
            rs.setErrorMessage("删除错误");
        } else {
            rs.setFlag(true);
        }
        return rs;
    }
    @RequestMapping("allByPage")
    @ResponseBody
    public PageInfo<Answer> allByPage(int currentPage,int pageSize){
        PageInfo<Answer> all = answerService.findAll(currentPage, pageSize);
        return all;

    }
}
