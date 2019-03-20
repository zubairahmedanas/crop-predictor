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
@Repository("historyDao")
public class HistoryDao extends Parent_Dao {

    public List<Double> getProfit(String khotiyanNumber, String session) {

        String sql = "select profit from history "
                + "where khotiyanNumber=:khotiyanNumber \n"
                + " and farmingSession= :session "
                + "order by year desc";

        MapSqlParameterSource param = new MapSqlParameterSource("session", session);
        param.addValue("khotiyanNumber", khotiyanNumber);

        return template.queryForList(sql, param, Double.class);

    }
}
