package it.mapper;

import cn.hutool.core.date.DateTime;
import it.domain.Answer;
import it.domain.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnswerMapper {
    int countByDate(DateTime time);
    List<Answer> findNewest();
    int countAll();
    List<Answer> findByQid(@Param("qid") int qid);
    int addAnswer(Answer answer);
    List<Answer> findByUid(@Param("uid") int uid);
    int deleteByAid(@Param("aid") int aid);
    List<Answer> findAll();

}
