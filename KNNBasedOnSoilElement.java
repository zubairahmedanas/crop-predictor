///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.services;
//
//import com.dao.CropElementPercentageDao;
//import com.dao.KhotiyanElementPercentageDao;
//import com.models.CropElementPercentage;
//import com.models.Khotiyan_Element_Percentage;
//import com.models.TempCropModel;
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
////import weka.core.Attribute;
////import weka.core.FastVector;
////import weka.core.Instance;
////import weka.core.Instances;
////import weka.core.neighboursearch.LinearNNSearch;
//
///**
// *
// * @author Sajid
// */
//@Service("kNNBasedOnSoilElement")
//public class KNNBasedOnSoilElement {
//
//    @Autowired
//    private KhotiyanElementPercentageDao khotiyanElementPercentageDao;
//
//    @Autowired
//    private CropElementPercentageDao cropElementPercentageDao;
//
//    public List<String> givePrediction(String khotiyanNumber) throws Exception {
//
//        List<CropElementPercentage> tempList = cropElementPercentageDao.getAll();
//
//        Attribute C = new Attribute("C");
//        Attribute H = new Attribute("H");
//        Attribute O = new Attribute("O");
//        Attribute N = new Attribute("N");
//        Attribute P = new Attribute("P");
//        Attribute K = new Attribute("K");
//        Attribute S = new Attribute("S");
//        Attribute Ca = new Attribute("Ca");
//        Attribute Mg = new Attribute("Mg");
//        Attribute Fe = new Attribute("Fe");
//        Attribute Mo = new Attribute("Mo");
//        Attribute B = new Attribute("B");
//        Attribute Cu = new Attribute("Cu");
//        Attribute Mn = new Attribute("Mn");
//        Attribute Na = new Attribute("Na");
//        Attribute Zn = new Attribute("Zn");
//        Attribute Ni = new Attribute("Ni");
//        Attribute Cl = new Attribute("Cl");
//        Attribute Co = new Attribute("Co");
//        Attribute Al = new Attribute("Al");
//        Attribute Si = new Attribute("Si");
//        Attribute V = new Attribute("V");
//        Attribute Se = new Attribute("Se");
//
//        FastVector fvWekaAttributes = new FastVector(23);
//        fvWekaAttributes.addElement(C);
//        fvWekaAttributes.addElement(H);
//        fvWekaAttributes.addElement(O);
//        fvWekaAttributes.addElement(N);
//        fvWekaAttributes.addElement(P);
//        fvWekaAttributes.addElement(K);
//        fvWekaAttributes.addElement(S);
//        fvWekaAttributes.addElement(Ca);
//        fvWekaAttributes.addElement(Mg);
//        fvWekaAttributes.addElement(Fe);
//        fvWekaAttributes.addElement(Mo);
//        fvWekaAttributes.addElement(B);
//        fvWekaAttributes.addElement(Cu);
//        fvWekaAttributes.addElement(Mn);
//        fvWekaAttributes.addElement(Na);
//        fvWekaAttributes.addElement(Zn);
//        fvWekaAttributes.addElement(Ni);
//        fvWekaAttributes.addElement(Cl);
//        fvWekaAttributes.addElement(Co);
//        fvWekaAttributes.addElement(Al);
//        fvWekaAttributes.addElement(Si);
//        fvWekaAttributes.addElement(V);
//        fvWekaAttributes.addElement(Se);
//
//        Instances crops = new Instances("crops", fvWekaAttributes, 100000);
//
//        double[] newInstance;
//
//        for (int i = 0; i < tempList.size(); i++) {
//
//            newInstance = new double[crops.numAttributes()];
//
//            CropElementPercentage tempCrop = tempList.get(i);
//
//            newInstance[0] = tempCrop.getC();
//            newInstance[1] = tempCrop.getH();
//            newInstance[2] = tempCrop.getO();
//            newInstance[3] = tempCrop.getN();
//            newInstance[4] = tempCrop.getP();
//            newInstance[5] = tempCrop.getK();
//            newInstance[6] = tempCrop.getS();
//            newInstance[7] = tempCrop.getCa();
//            newInstance[8] = tempCrop.getMg();
//            newInstance[9] = tempCrop.getFe();
//            newInstance[10] = tempCrop.getMo();
//            newInstance[11] = tempCrop.getB();
//            newInstance[12] = tempCrop.getCu();
//            newInstance[13] = tempCrop.getMn();
//            newInstance[14] = tempCrop.getNa();
//            newInstance[15] = tempCrop.getZn();
//            newInstance[16] = tempCrop.getNi();
//            newInstance[17] = tempCrop.getCl();
//            newInstance[18] = tempCrop.getCo();
//            newInstance[19] = tempCrop.getAl();
//            newInstance[20] = tempCrop.getSi();
//            newInstance[21] = tempCrop.getV();
//            newInstance[22] = tempCrop.getSe();
//
//            crops.add(new Instance(1.0, newInstance));
//
//        }
//
//        //Try-Catch put here for brats who search by non-existing khotiyan number
//        try {
//            Khotiyan_Element_Percentage tempCrop = khotiyanElementPercentageDao.getByKhotianNumberSoil(khotiyanNumber);
//
//            newInstance = new double[crops.numAttributes()];
//
//            newInstance[0] = tempCrop.getC();
//            newInstance[1] = tempCrop.getH();
//            newInstance[2] = tempCrop.getO();
//            newInstance[3] = tempCrop.getN();
//            newInstance[4] = tempCrop.getP();
//            newInstance[5] = tempCrop.getK();
//            newInstance[6] = tempCrop.getS();
//            newInstance[7] = tempCrop.getCa();
//            newInstance[8] = tempCrop.getMg();
//            newInstance[9] = tempCrop.getFe();
//            newInstance[10] = tempCrop.getMo();
//            newInstance[11] = tempCrop.getB();
//            newInstance[12] = tempCrop.getCu();
//            newInstance[13] = tempCrop.getMn();
//            newInstance[14] = tempCrop.getNa();
//            newInstance[15] = tempCrop.getZn();
//            newInstance[16] = tempCrop.getNi();
//            newInstance[17] = tempCrop.getCl();
//            newInstance[18] = tempCrop.getCo();
//            newInstance[19] = tempCrop.getAl();
//            newInstance[20] = tempCrop.getSi();
//            newInstance[21] = tempCrop.getV();
//            newInstance[22] = tempCrop.getSe();
//
//            Instance targetInstance = new Instance(1.0, newInstance);
//
//            LinearNNSearch knn = new LinearNNSearch(crops);
//            int k = 3;
//
//            Instances nearestInstances = knn.kNearestNeighbours(targetInstance, k);
//
//            List<String> predictionList = new ArrayList<>();
//
//            for (int i = 0; i < k; i++) {
//
//                TempCropModel tempModel = new TempCropModel();
//                Instance tempInstance = nearestInstances.instance(i);
//
//                tempModel.setC(tempInstance.value(0));
//                tempModel.setH(tempInstance.value(1));
//                tempModel.setO(tempInstance.value(2));
//                tempModel.setN(tempInstance.value(3));
//                tempModel.setP(tempInstance.value(4));
//                tempModel.setK(tempInstance.value(5));
//                tempModel.setS(tempInstance.value(6));
//                tempModel.setCa(tempInstance.value(7));
//                tempModel.setMg(tempInstance.value(8));
//                tempModel.setFe(tempInstance.value(9));
//                tempModel.setMo(tempInstance.value(10));
//                tempModel.setB(tempInstance.value(11));
//                tempModel.setCu(tempInstance.value(12));
//                tempModel.setMn(tempInstance.value(13));
//                tempModel.setNa(tempInstance.value(14));
//                tempModel.setZn(tempInstance.value(15));
//                tempModel.setNi(tempInstance.value(16));
//                tempModel.setCl(tempInstance.value(17));
//                tempModel.setCo(tempInstance.value(18));
//                tempModel.setAl(tempInstance.value(19));
//                tempModel.setSi(tempInstance.value(20));
//                tempModel.setV(tempInstance.value(21));
//                tempModel.setSe(tempInstance.value(22));
//
//                List<String> tempListOne = cropElementPercentageDao.getCropNameFromElements(tempModel);
//
//                for (String cropName : tempListOne) {
//
//                    if (!predictionList.contains(cropName)) {
//                        predictionList.add(cropName);
//                    }
//
//                }
//
//            }
//
//            return predictionList;
//        } catch (Exception ex) {
//            return new ArrayList<>();
//        }
//
//    }
//
//}
