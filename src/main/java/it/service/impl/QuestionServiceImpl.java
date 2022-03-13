package it.service.impl;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import it.domain.Question;
import it.mapper.QuestionMapper;
import it.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service("QuestionService")
public class QuestionServiceImpl implements QuestionService {
   @Autowired
    QuestionMapper questionMapper;
    @Override
    public List<Question> getAll() {
        List<Question> all = questionMapper.findAll();
        return all;
    }

    @Override
    public List<Question> getNewest() {
        List<Question> newest = questionMapper.findNewest();
        return newest;
    }

    @Override
    public int countToday() {
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyyMMdd");
        long todayZero = 0;
        try {
            todayZero = dayFormat.parse(dayFormat.format(new Date())).getTime();
            DateTime dateTime1 = new DateTime(todayZero);
            int i = questionMapper.countByDate(dateTime1);
            return i;
        } catch (ParseException e) {
            return 0;
        }
    }

    @Override
    public Question findByQid(int qid) {
        Question question = questionMapper.findByQid(qid);
        return question;
    }

    @Override
    public int addQuestion(Question question) {
        try {
            int i = questionMapper.addQuestion(question);
                return i;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public PageInfo<Question> findPersonal(int uid, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<Question> byUid = questionMapper.findByUid(uid);
        PageInfo<Question> questionPageInfo = new PageInfo<>(byUid);
        return questionPageInfo;
    }

    @Override
    public int deleteByQid(int qid) {
        try {
            int i = questionMapper.deleteByQid(qid);
            return i;
        } catch (Exception e) {
            System.out.println("删除失败");
            return 0;
        }

    }

    @Override
    public PageInfo<Question> getAllWithoutAn(int currentPage,int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<Question> allWithoutAn = questionMapper.findAllWithoutAn();
        PageInfo<Question> questionPageInfo = new PageInfo<>(allWithoutAn);
        return questionPageInfo;
    }
}
