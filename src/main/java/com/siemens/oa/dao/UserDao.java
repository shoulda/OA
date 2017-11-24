package com.siemens.oa.dao;

import com.siemens.oa.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {

    /**
     * 查找指定userID的user记录
     *
     * @param id
     * @return
     */
    @Select("select * from User where USERID = #{USERID}")
    public User selectUserById(Integer id);

    /**
     * 查找指定username的user记录
     *
     * @param username
     * @return
     */
    @Select("select * from User where USERNAME = #{USERNAME}")
    public User selectUserByName(String username);

    /**
     * 增加一条新user记录
     *
     * @param user
     */
    @Insert("insert into User(USERNAME,PASSWORD,sex,address) values(#{USERNAME},#{PASSWORD},#{sex},#{address})")
    public void addUser(User user);

    /**
     * 更新指定userID的user记录
     *
     * @param user
     */
    @Update("update User set USERNAME=#{USERNAME},birthday=#{birthday},sex=#{sex},address=#{address} where id=#{id}")
    public void updateUser(User user);

    /**
     * 删除指定userID的user记录
     *
     * @param id
     */
    @Delete("delete from User where USERID = #{USERID}")
    public void deleteUser(Integer id);

    /**
     * 删除user表所有记录
     */
    @Delete("delete from User")
    public void deleteAll();


}
