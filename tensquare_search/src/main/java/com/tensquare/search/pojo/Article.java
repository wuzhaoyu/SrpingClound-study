package com.tensquare.search.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/2/23
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
@Document(
        indexName = "tensquare_elasticsearch",
        type = "article"
)
@Data
public class Article implements Serializable {

    @Id
    private String id;

    /**
     * index 是否索引 能否被搜索
     * analyzer 指定分词器存储 不分词整体搜索，
     * searchAnalyzer 指定分词器搜索
     */
    @Field(index = true,analyzer = "ik_max_words",searchAnalyzer = "ik_max_words")
    private String title;

    @Field(index = true,analyzer = "ik_max_words",searchAnalyzer = "ik_max_words")
    private String content;

    private String state;

}
