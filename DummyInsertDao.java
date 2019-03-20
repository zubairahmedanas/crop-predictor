/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.models.CropElementPercentage;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sajid
 */
@Repository("dummyInsertDao")
public class DummyInsertDao extends Parent_Dao {

    public void insertDummy(CropElementPercentage crop) {

        //Here two params may be replaced with only use
        //Possible redundant MapSqlParameterSource
        MapSqlParameterSource paramTwo = new MapSqlParameterSource("cropName", crop.getCrop_name());
        String sql;

        sql = "insert into crop_name(cropName) values(:cropName)";
        template.update(sql, paramTwo);

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(crop);
        sql = "insert into crop_element_percentage(crop_name,C,H,O,N,P,K,S,Ca,Mg,Fe,Mo,B,Cu,Mn,Na,Zn,Ni,Cl,Co,Al,Si,V,Se) "
                + "values(:crop_name,:C,:H,:O,:N,:P,:K,:S,:Ca,:Mg,:Fe,:Mo,:B,:Cu,:Mn,:Na,:Zn,:Ni,:Cl,:Co,:Al,:Si,:V,:Se)";

        template.update(sql, param);

    }

}
