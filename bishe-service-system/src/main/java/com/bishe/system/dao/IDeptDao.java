package com.bishe.system.dao;

import com.bishe.framework.domain.department.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface IDeptDao {

    //根据父部门id查找部门列表
    @Select("select * from bishe_dept where parent = #{parent}")
    public List<Department> getDeptListByParent(String parent);

    //根据部门id查找部门信息
    @Select("select * from bishe_dept where pid = #{pid}")
    public Department getDeptListByPid(String pid);



}
