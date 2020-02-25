package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * problem数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    /**
     * 最新问题
     * @return
     */
    @Query(value = "SELECT * from tb_problem,tb_pl where id = problemid and labelid = ? ORDER BY replytime DESC",nativeQuery = true)
    Page<Problem> newList(String labelId, Pageable pageable);

    /**
     * 热点问题
     * @return
     */
    @Query(value = "SELECT * from tb_problem,tb_pl where id = problemid and labelid = ? ORDER BY reply DESC",nativeQuery = true)
    Page<Problem> hotList(String labelId, Pageable pageable);

    /**
     * 等待问题
     * @return
     */
    @Query(value = "SELECT * from tb_problem,tb_pl where id = problemid and labelid = ? and reply = 0 ORDER BY createtime DESC",nativeQuery = true)
    Page<Problem> waitList(String labelId, Pageable pageable);
}
