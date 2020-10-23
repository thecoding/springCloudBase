package com.springcloud.base.demo.shardingjdbc.repository;

import com.springcloud.base.demo.shardingjdbc.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository extends BaseRepository<User,Long>{

}
