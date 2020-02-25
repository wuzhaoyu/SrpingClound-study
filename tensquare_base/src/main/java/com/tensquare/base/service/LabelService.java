package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.entity.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/2/9
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    /*** 查询全部标签 * @return */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    /*** 根据ID查询标签 * @return */
    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    /*** 增加标签 * @param label */
    public void add(Label label) {
        label.setId(idWorker.nextId() + "");
    }
    /*** 修改标签 * @param label */
    public void update(Label label){
        labelDao.save(label);
    }
    /*** 删除标签 * @param id **/
    public void deleteById(String id){
        labelDao.deleteById(id);
    }

    /**
     * 条件查询
     * @param label
     * @return
     */
    public List<Label> findSearch(Label label){
      return   labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root  需要查询实体类字段封装类
             * @param criteriaQuery
             * @param cb  封装后的查询条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                //判断条件
                if(label.getLabelname()!=null && label.getLabelname()!=""){
                    // labelname字段 string类型
                    Predicate labelname = cb.like(root.get("labelname").as(String.class),"%" + label.getLabelname() + "%");
                    list.add(labelname);
                }
                if(label.getState()!=null && label.getState()!=""){
                    Predicate labelname = cb.equal(root.get("state").as(String.class), label.getState());
                    list.add(labelname);
                }
                Predicate[] parr = new Predicate[list.size()];
                list.toArray(parr);
                return cb.and(parr);
            }
        });
    }

    public Page<Label> pageQuery(Label label, int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return  labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root  需要查询实体类字段封装类
             * @param criteriaQuery
             * @param cb  封装后的查询条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                //判断条件
                if(label.getLabelname()!=null && label.getLabelname()!=""){
                    // labelname字段 string类型
                    Predicate labelname = cb.like(root.get("labelname").as(String.class),"%" + label.getLabelname() + "%");
                    list.add(labelname);
                }
                if(label.getState()!=null && label.getState()!=""){
                    Predicate labelname = cb.equal(root.get("state").as(String.class), label.getState());
                    list.add(labelname);
                }
                Predicate[] parr = new Predicate[list.size()];
                list.toArray(parr);
                return cb.and(parr);
            }
        },pageable);
    }
    }