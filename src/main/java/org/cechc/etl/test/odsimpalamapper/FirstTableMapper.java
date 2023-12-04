package org.cechc.etl.test.odsimpalamapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
@Mapper
public interface FirstTableMapper {

    public static final String sql ="SELECT * FROM my_first_table";

    @Select(sql)
    public HashMap<String,String> getColumns();
}

