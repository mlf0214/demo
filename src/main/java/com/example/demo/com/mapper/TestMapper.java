package com.example.demo.com.mapper;

import com.example.demo.com.pojo.sql.UserBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TestMapper {
    //    INSERT INTO table_name ( field1, field2,...fieldN )
//                       VALUES
//                       ( value1, value2,...valueN );
    @Insert("insert into commoditys(c_name,c_uri,c_advertisement,c_price,c_type) values (#{c_name},#{c_uri},#{c_advertisement},#{c_price},#{c_type})")
    int addCommoditys(String c_name, String c_uri, String c_advertisement, String c_price, String c_type);

    @Insert("insert into test (uri) values (#{uri})")
    void test(String uri);

    //    @Delete("")
    @Select("select * from t_user where id=#{id}")
    UserBean findById(String id);

    @Insert("insert into t_user (sno,password) values (#{sno},#{password})")
    int insertUser(String sno, String password);

    @Delete("delete from t_user where id=#{s}")
    int deleteUserById(String s);
}
