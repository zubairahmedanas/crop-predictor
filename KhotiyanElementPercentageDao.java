/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.models.Khotiyan_Element_Percentage;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sajid
 */
@Repository("khotiyanElementPercentageDao")
public class KhotiyanElementPercentageDao extends Parent_Dao {

    public void insert(Khotiyan_Element_Percentage kep) {

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(kep);
        String sql = "insert into khotiyan_element_percentage(khotiyanNumberSoil,C,H,O,N,P,K,S,Ca,Mg,Fe,Mo,B,Cu,Mn,Na,Zn,Ni,Cl,Co,Al,Si,V,Se) "
                + "values(:khotiyanNumberSoil,:C,:H,:O,:N,:P,:K,:S,:Ca,:Mg,:Fe,:Mo,:B,:Cu,:Mn,:Na,:Zn,:Ni,:Cl,:Co,:Al,:Si,:V,:Se)";

        template.update(sql, param);
    }

    public void deleteByKhotianNumberSoil(String khotiyanNumber) {

        MapSqlParameterSource param = new MapSqlParameterSource("khotiyanNumber", khotiyanNumber);
        String sql = "delete from khotiyan_element_percentage where khotiyanNumberSoil=:khotiyanNumber";

        template.update(sql, param);

    }

    public Khotiyan_Element_Percentage getByKhotianNumberSoil(String khotiyanNumber) {

        MapSqlParameterSource param = new MapSqlParameterSource("khotiyanNumber", khotiyanNumber);
        String sql = "select * from khotiyan_element_percentage where khotiyanNumberSoil=:khotiyanNumber";

        return template.queryForObject(sql, param, BeanPropertyRowMapper.newInstance(Khotiyan_Element_Percentage.class));
    }

}
