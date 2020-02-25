package com.tensquare.base.dao;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/2/9
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/

import com.tensquare.base.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/*** 标签数据访问接口 */
public interface LabelDao extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label> {

}
