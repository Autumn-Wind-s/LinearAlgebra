package it.service.impl;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import it.domain.Answer;
import it.domain.Question;
import it.mapper.AnswerMapper;
import it.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("AnswerService")
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AnswerMapper answerMapper;

    @Override
    public List<Answer> getNewest() {
        List<Answer> newest = answerMapper.findNewest();
        return newest;
    }

    @Override
    public int countToday() {
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyyMMdd");
        long todayZero = 0;
        try {
            todayZero = dayFormat.parse(dayFormat.format(new Date())).getTime();
            DateTime dateTime1 = new DateTime(todayZero);
            int i = answerMapper.countByDate(dateTime1);
            return i;
        } catch (ParseException e) {
            return 0;
        }
    }

    @Override
    public int countAll() {
        int i = answerMapper.countAll();
        return i;
    }

    @Override
    public List<Answer> getByQid(int qid) {
        List<Answer> byQid = answerMapper.findByQid(qid);
        return byQid;
    }

    @Override
    public int addAnswer(Answer answer) {
        try {
            int i = answerMapper.addAnswer(answer);
            return i;
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public PageInfo<Answer> findPersonal(int uid, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<Answer> byUid = answerMapper.findByUid(uid);
        PageInfo<Answer> answerPageInfo = new PageInfo<>(byUid);
        return answerPageInfo;
    }

    @Override
    public int deleteByAid(int aid) {
        try {
            int i = answerMapper.deleteByAid(aid);
            return i;
        } catch (Exception e) {
            System.out.println("删除错误");
            return 0;
        }
    }

    @Override
    public PageInfo<Answer> findAll(int currentPage, int pageSize) {
      PageHelper.startPage(currentPage,pageSize);
        List<Answer> all = answerMapper.findAll();
        PageInfo<Answer> answerPageInfo = new PageInfo<>(all);
        return answerPageInfo;
    }
}
