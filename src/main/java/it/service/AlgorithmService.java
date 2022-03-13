package it.service;

import com.github.pagehelper.PageInfo;
import it.domain.Algorithm;
import it.domain.User;

public interface AlgorithmService {

  PageInfo<Algorithm> findByPage(int uid, int currentPage, int pageSize);
  boolean updateAlgorithm(Algorithm algorithm);
  boolean deleteByAid(Algorithm algorithm);
  boolean uploadAlgorithm(Algorithm algorithm);
  PageInfo<Algorithm> findByKey(String key, int currentPage, int pageSize);


}
