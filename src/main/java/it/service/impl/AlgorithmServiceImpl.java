package it.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import it.domain.Algorithm;
import it.domain.User;
import it.mapper.AlgorithmMapper;
import it.service.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("algorithmService")
public class AlgorithmServiceImpl implements AlgorithmService {
    @Autowired
    AlgorithmMapper algorithmMapper;

    @Override
    public PageInfo<Algorithm> findByPage(int uid, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<Algorithm> algorithmPage = algorithmMapper.findAlgorithmPage(uid);
        PageInfo<Algorithm> p = new PageInfo<Algorithm>(algorithmPage);
        return p;
    }

    @Override
    public boolean updateAlgorithm(Algorithm algorithm) {
        try {
            int i = algorithmMapper.updateAlgorithm(algorithm);
            if (i == 0) return false;
            else return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean deleteByAid(Algorithm algorithm) {
        try {
            int i = algorithmMapper.deleteByAid(algorithm);
            if (i==0)
            {return false;}
                else return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean uploadAlgorithm(Algorithm algorithm) {
        try {
            int i = algorithmMapper.addAlgorithm(algorithm);
            if (i == 0) {
                return  false;
            }
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public PageInfo<Algorithm> findByKey(String key, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<Algorithm> byKeyWord = algorithmMapper.findByKeyWord(key);
        PageInfo<Algorithm> userPageInfo = new PageInfo<Algorithm>(byKeyWord);
        return userPageInfo;
    }




}
