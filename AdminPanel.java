/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.dao.CropElementPercentageDao;
import com.dao.CropNameDao;
import com.dao.HistoryDao;
import com.dao.KhotiyanElementPercentageDao;
import com.models.CropElementPercentage;
import com.models.CropName;
import com.models.Khotiyan_Element_Percentage;
import com.models.TempCropDeletionModel;
import com.services.CropElementPercentageService;
//import com.services.KNNBasedOnSoilElement;
import com.services.WriteResultToPdf;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Sajid
 */
@Controller
public class AdminPanel {

    @Autowired
    private KhotiyanElementPercentageDao khotiyanElementPercentageDao;

    @Autowired
    private CropNameDao cropNameDao;

    @Autowired
    private CropElementPercentageDao cropElementPercentageDao;

    @Autowired
    private HistoryDao historyDao;

    @Autowired
   // private KNNBasedOnSoilElement classifier;

    //@Autowired
    private CropElementPercentageService cropElementPercentageService;

    @RequestMapping(value = "input_element_aftertest", method = RequestMethod.POST)
    public String doPOST1(@ModelAttribute("kep") Khotiyan_Element_Percentage kep,
            RedirectAttributes ra) {

        try {
            khotiyanElementPercentageDao.insert(kep);
        } catch (DuplicateKeyException ex) {
            khotiyanElementPercentageDao.deleteByKhotianNumberSoil(kep.getKhotiyanNumberSoil());
            khotiyanElementPercentageDao.insert(kep);
        } catch (DataAccessException ex) {
            //the message should be inserted into a jsp page
//            model.addAttribute("unknown khotiyan number", "unknown");
            ra.addFlashAttribute("unknown khotiyan number", "unidentified khotiyan number");

        } catch (Exception ex) {

        }

        return "redirect:/adminPanel";

    }

    @RequestMapping(value = "updateCropInfo", method = RequestMethod.GET)
    public String doGET1(Model model) {

        model.addAttribute("cropName", new CropName());

        List<String> cropNameList = cropNameDao.getAll();
        cropNameList.add(0, "new_crop");
        model.addAttribute("allCrops", cropNameList);

        return "adminPanel";

    }

    @RequestMapping(value = "getCropForUpdate", method = RequestMethod.POST)
    public String doPOST1(@ModelAttribute("crop") CropName cropName, Model model) {

        if (cropName.getCropName().equals("new_crop")) {

            CropElementPercentage crop = new CropElementPercentage();
            crop.setCrop_name("Enter name of the new crop");
            model.addAttribute("cropForUpdate", crop);
            model.addAttribute("actionType", "insert");

        } else {

            model.addAttribute("cropForUpdate", cropElementPercentageDao.getByName(cropName.getCropName()));
            model.addAttribute("actionType", "update");
        }

        return "adminPanel";

    }

    @RequestMapping(value = "updateCrop", method = RequestMethod.POST)
    public String doPOST2(@RequestParam("actionType") String actionType,
            @ModelAttribute("crop") CropElementPercentage crop,
            RedirectAttributes ra,
            Model model) {

        if (actionType.equals("update")) {
            cropElementPercentageDao.updateByname(crop);
        } else {
            try {
                cropElementPercentageDao.insert(crop);
            } catch (DuplicateKeyException e) {
                cropElementPercentageDao.deleteByName(crop);
                cropElementPercentageDao.insert(crop);
            }

        }

        return "redirect:/updateCropInfo";

    }

    @RequestMapping(value = "gotoPrediction", method = RequestMethod.GET)
    public String doGET2(Model model) {

        model.addAttribute("showPrediction", " ");
        return "adminPanel";
    }

    @RequestMapping(value = "getPredictedCrops", method = RequestMethod.POST)
    public String doPOST3(@RequestParam("khotiyanNumber") String khotiyanNumber,
            Model model) {

        List<String> predictedCrops = new ArrayList<>();

        try {
           // predictedCrops = classifier.givePrediction(khotiyanNumber);
        } catch (Exception ex) {
            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<CropElementPercentage> forOutput = new ArrayList<>();

        for (String crop : predictedCrops) {

            forOutput.add(cropElementPercentageDao.getByName(crop));

        }

        WriteResultToPdf writer = new WriteResultToPdf();

        writer.doWrite(forOutput, khotiyanNumber);

        model.addAttribute("predictedCrops", predictedCrops);
        model.addAttribute("showPrediction", " ");
        return "adminPanel";
    }

    @RequestMapping(value = "showDelete", method = RequestMethod.GET)
    public String doGET3(Model model) {

        model.addAttribute("showDeleteForm", "");
        model.addAttribute("tempDeletionModel", new TempCropDeletionModel());
        model.addAttribute("allCrops", cropNameDao.getAll());

        return "adminPanel";

    }

    @RequestMapping(value = "getGraph", method = RequestMethod.GET)
    public String doGET4(Model model) {

        model.addAttribute("showGraph", "");

        return "adminPanel";

    }

    @RequestMapping(value = "performDeletion", method = RequestMethod.POST)
    public String doPOST4(@ModelAttribute("crops") TempCropDeletionModel crops) {

        cropElementPercentageService.batchDeleteByCropNames(crops.getCropsToBeDeleted());

        return "redirect:/adminPanel";
    }

    @RequestMapping(value = "getProfitByKhotiyanNumber", method = RequestMethod.POST)
    public String doPOST5(@RequestParam("khotiyanNumber") String khotiyanNumber,
            Model model) {

        model.addAttribute("showGraph", " ");

        try {
            List<Double> profitInSpring = historyDao.getProfit(khotiyanNumber, "spring");
            List<Double> profitInFall = historyDao.getProfit(khotiyanNumber, "fall");
            List<Double> profitInSummer = historyDao.getProfit(khotiyanNumber, "summer");

            model.addAttribute("profitInSpring", profitInSpring);
            model.addAttribute("profitInFall", profitInFall);
            model.addAttribute("profitInSummer", profitInSummer);

        } catch (Exception ex) {
            model.addAttribute("insufficientDataForGraph", "");
        }

        return "adminPanel";

    }

}
