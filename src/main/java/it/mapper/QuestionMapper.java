package it.mapper;

import cn.hutool.core.date.DateTime;
import it.domain.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionMapper {
    List<Question> findNewest();
    List<Question> findAll();
    int countByDate(DateTime time);
    Question findByQid(@Param("qid")int qid);
    int addQuestion(Question question);
    List<Question> findByUid(@Param("uid") int uid);
    int deleteByQid(@Param("qid") int qid);
    List<Question> findAllWithoutAn();

}
