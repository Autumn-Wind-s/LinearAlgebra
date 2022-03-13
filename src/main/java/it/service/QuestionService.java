package it.service;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.PageInfo;
import it.domain.Question;

import java.util.Date;
import java.util.List;

public interface QuestionService {

    List<Question> getAll();
    List<Question>getNewest();
    int countToday();
    Question findByQid(int qid);
    int addQuestion(Question question);
    PageInfo<Question> findPersonal(int uid,int currentPage,int pageSize);
    int deleteByQid(int qid);
    PageInfo<Question> getAllWithoutAn(int currentPage,int pageSize);
}
