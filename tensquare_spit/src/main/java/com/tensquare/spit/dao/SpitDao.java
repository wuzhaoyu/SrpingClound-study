package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/2/16
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
public interface SpitDao extends MongoRepository<Spit,String> {

    Page<Spit> findByParentid(String parent, Pageable pageable);
}
