package com.siemens.oa.dao;

import com.siemens.oa.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {

    /**
     * 插入一条新用户记录
     *
     * @param user
     */
    @Insert("insert into User(username,password,displayname,status) values(#{username},#{password},#{displayname},#{status})")
    void insertUser(User user);

    /**
     * 根据用户名删除用户
     *
     * @param username
     */
    @Delete("delete from User where username = #{username}")
    void deleteUser(String username);

    /**
     * 根据用户ID更新用户
     *
     * @param user
     */
    @Update("update User set username=#{username},password=#{password},displayname=#{displayname},status=#{status} where userid=#{userid}")
    void updateUser(User user);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    @Select("select * from User where username = #{username}")
    User selectUserByName(String username);

    /**
     * 根据用户名查询用户Id
     *
     * @param username
     * @return
     */
    @Select("select userid from User where username = #{username}")
    Integer selectUserIdByName(String username);

    /**
     * 根据userID查询user信息
     *
     * @param userid
     * @return
     */
    @Select("select * from User where userid=#{userid} ")
    User selectUserById(Integer userid);

    /**
     * 查询所有user
     *
     * @return
     */
    @Select("select * from User")
    List<User> selectAllUser();
}
