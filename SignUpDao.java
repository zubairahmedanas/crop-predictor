/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.models.SignUpInfo;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository("signUpDao")
public class SignUpDao extends Parent_Dao {

    public void createUser(SignUpInfo signUpInfo) {

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(signUpInfo);
        String sql = "insert into users(username,password,landAmount,district,mobileNumber) "
                + "values(:username,:password,:landAmount,:district,:mobileNumber)";

        template.update(sql, params);

        sql = "insert into authorities(username,authority) "
                + "values (:username,:authority)";

        template.update(sql, params);
    }

}
