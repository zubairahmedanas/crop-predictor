/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import java.util.List;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sajid
 */
@Repository("cropNameDao")
public class CropNameDao extends Parent_Dao {

    public List<String> getAll() {

        String sql = "select * from crop_name";
        MapSqlParameterSource param = new MapSqlParameterSource();

        return template.queryForList(sql, param, String.class);

    }

}
