package it.service;

import com.github.pagehelper.PageInfo;
import it.domain.Answer;
import it.domain.Question;

import java.util.List;

public interface AnswerService {
    List<Answer> getNewest();
    int countToday();
    int countAll();
    List<Answer> getByQid(int qid);
    int addAnswer(Answer answer);
    PageInfo<Answer> findPersonal(int uid, int currentPage, int pageSize);
    int deleteByAid(int aid);
    PageInfo<Answer> findAll(int currentPage,int pageSize);
}
