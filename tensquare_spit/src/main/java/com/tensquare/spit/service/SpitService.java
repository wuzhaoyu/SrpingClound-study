package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/2/16
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
@Service
public class SpitService {

    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private MongoTemplate mongoTemplate;


    public SpitService() {
    }

    /*** 查询全部记录 * @return */
    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    /*** 根据主键查询实体 * @param id * @return */
    public Spit findById(String id) {
        Spit spit = spitDao.findById(id).get();
        return spit;
    }

    /*** 增加 * @param spit */
    public void add(Spit spit) {
        spit.set_id(idWorker.nextId() + ""); //主键值 spitDao.save(spit); }
        spit.setPublishtime(new Date());//发布日期
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态
       if(spit.getParentid()!=null && !"".equals(spit.getParentid())){
         //如果存在上级ID,评论
         Query query=new Query();
        query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
        Update update=new Update(); update.inc("comment",1);
        mongoTemplate.updateFirst(query,update,"spit"); }
        spitDao.save(spit);
    }

    /*** 更新 * @param spit */
    public void update(Spit spit) {
        spitDao.save(spit);
    }

    /*** 删除 * @param id */
    public void deleteById(String id) {
        spitDao.deleteById(id);
    }

    public Page<Spit> findByParentid(String parent, int page, int size) {
        return spitDao.findByParentid(parent, PageRequest.of(page, size));
    }

    /*** 点赞 * @param id */
    public void updateThumbup(String id) {
        //第一种效率低 查询所有
        /*Spit spit = spitDao.findById(id).get();
        spit.setThumbup(spit.getThumbup() + 1);
        spitDao.save(spit);*/
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");

    }

}
