package com.tensquare.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * article数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    /**
     * 更新状态 审核
     * @param id
     */
    @Modifying
    @Query(value = "update tb_article set state='1' where id=?",nativeQuery = true)
    public void updateState(String id);

    /**
     * 点赞数 null+1依然为null
     * @param id
     */
    @Modifying
    @Query(value = "update tb_article a set thumbup=thumbup+1 where id=?",nativeQuery = true)
    public void updateThumbup(String id);
	
}
