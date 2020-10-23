package com.springcloud.base.demo.shardingjdbc.repository;

import com.springcloud.base.demo.shardingjdbc.entity.HealthLevel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HealthLevelRepository extends BaseRepository<HealthLevel, Long> {

}
