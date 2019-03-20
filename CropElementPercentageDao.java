/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.models.CropElementPercentage;
import com.models.TempCropModel;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sajid
 */
@Repository("cropElementPercentageDao")
public class CropElementPercentageDao extends Parent_Dao {

    public CropElementPercentage getByName(String cropName) {

        MapSqlParameterSource param = new MapSqlParameterSource("cropName", cropName);
        String sql = "select * from crop_element_percentage where crop_name=:cropName";

        return template.queryForObject(sql, param, BeanPropertyRowMapper.newInstance(CropElementPercentage.class));
    }

    public void updateByname(CropElementPercentage crop) {

        BeanPropertySqlParameterSource paramOne = new BeanPropertySqlParameterSource(crop);

        String sql = "update crop_element_percentage set crop_name=:crop_name , C=:C ,"
                + " H=:H ,O=:O,N=:N,P=:P,K=:K,S=:S,Ca=:Ca,Mg=:Mg,Fe=:Fe,Mo=:Mo,B=:B, "
                + "Cu=:Cu,Mn=:Mn,Na=:Na,Zn=:Zn,Ni=:Ni,Cl=:Cl,Co=:Co,Al=:Al,Si=:Si, "
                + "V=:V,Se=:Se where crop_name=:crop_name";

        template.update(sql, paramOne);

    }

    public void insert(CropElementPercentage crop) {

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(crop);

        //This query may be placed inside CropNameDao
        String sql = "insert into crop_name(cropName) values(:crop_name)";

        template.update(sql, param);

        sql = "insert into crop_element_percentage(crop_name,C,H,O,N,P,K,S,Ca,Mg,Fe,Mo,B,Cu,Mn,Na,Zn,Ni,Cl,Co,Al,Si,V,Se) "
                + "values(:crop_name,:C,:H,:O,:N,:P,:K,:S,:Ca,:Mg,:Fe,:Mo,:B,:Cu,:Mn,:Na,:Zn,:Ni,:Cl,:Co,:Al,:Si,:V,:Se)";

        template.update(sql, param);

    }

    //Here lies a problem
    //Should have used only a String as parameter for this method
    //Try to use minimum parameter for DAO methods(NEW LESSON)
    public void deleteByName(CropElementPercentage crop) {

        String sql = "Delete from crop_element_percentage where crop_name=:crop_name";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(crop);

        template.update(sql, param);

        //This query may be placed inside CropNameDao
        sql = "Delete from crop_name where cropName=:crop_name";

        template.update(sql, param);

    }

    public void batchDeleteByNames(Map<String, String>[] cropMap) {

        String sql = "Delete from crop_element_percentage where crop_name=:crop_name";
        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(cropMap);
        template.batchUpdate(sql, params);

        //This query may be placed inside CropNameDao
        sql = "Delete from crop_name where cropName=:crop_name";

        template.batchUpdate(sql, params);

    }

    public List<String> getCropNameFromElements(TempCropModel crop) {

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(crop);
        String sql = "Select crop_name from crop_element_percentage where "
                + "C=:C AND H=:H AND O=:O AND N=:N AND P=:P AND K=:K AND S=:S AND Ca=:Ca"
                + " AND Mg=:Mg AND Fe=:Fe AND Mo=:Mo AND B=:B AND Cu=:Cu AND Mn=:Mn AND"
                + " Na=:Na AND Zn=:Zn AND Ni=:Ni AND Cl=:Cl AND Co=:Co AND Al=:Al AND"
                + " Si=:Si AND V=:V AND Se=:Se ";

        return template.queryForList(sql, param, String.class);

    }

    public List<CropElementPercentage> getAll() {

        String sql = "Select * from crop_element_percentage";

        return template.query(sql, BeanPropertyRowMapper.newInstance(CropElementPercentage.class));
    }

}
