package it.mapper;

import it.domain.Algorithm;
import it.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlgorithmMapper {
List<Algorithm> findAlgorithmPage(@Param("uid") int uid);
int updateAlgorithm(Algorithm algorithm);
int deleteByAid(Algorithm algorithm);
int addAlgorithm(Algorithm algorithm);
List<Algorithm> findByKeyWord(@Param("key") String key);

}
