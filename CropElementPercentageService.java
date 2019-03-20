/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.dao.CropElementPercentageDao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sajid
 */
@Service("cropElementPercentageService")
public class CropElementPercentageService {

    @Autowired
    private CropElementPercentageDao cropElementPercentageDao;

//    Converting the list to map for batch update
    @SuppressWarnings("unchecked")
    public void batchDeleteByCropNames(List<String> crops) {

        Map<String, String>[] mapArray;
        mapArray = new HashMap[crops.size()];

        Map<String, String> cropMap;

        for (int i = 0; i < crops.size(); i++) {
            cropMap = new HashMap<>();
            cropMap.put("crop_name", crops.get(i));
            mapArray[i] = cropMap;
        }

        cropElementPercentageDao.batchDeleteByNames(mapArray);
    }

}
