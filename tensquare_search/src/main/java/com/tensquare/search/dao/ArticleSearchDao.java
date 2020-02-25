package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/2/23
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
public interface ArticleSearchDao extends ElasticsearchRepository<Article, String> {
    /*** 检索 * @param * @return */
    public Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
