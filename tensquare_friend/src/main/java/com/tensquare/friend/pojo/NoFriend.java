package com.tensquare.friend.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/2/26
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
@Entity
@Table(name = "tb_nofriend")
@IdClass(NoFriend.class)
@Data
public class NoFriend implements Serializable {
    @Id
    private String userid;
    @Id
    private String friendid;



}
