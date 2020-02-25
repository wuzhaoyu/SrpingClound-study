package com.tensquare.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Recruit;

import java.util.List;

/**
 * recruit数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{

    /** * 查询最新职位列表(按创建日期降序排序) * @return */
    List<Recruit> findTop6ByStateOrderByCreatetimeDesc(String state); //where state = ? order by createtime desc

    /**
     * 查询最新的招聘信息前六条
     * @param state
     * @return
     */
    List<Recruit> findTop6ByStateNotOrderByCreatetimeDesc(String state); //where state = ? order by createtime desc
	
}
