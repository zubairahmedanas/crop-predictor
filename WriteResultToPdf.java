/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.models.CropElementPercentage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 *
 * @author Sajid
 */
public class WriteResultToPdf {

    public void doWrite(List<CropElementPercentage> list, String pdfName) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("F:\\Project PDF\\" + pdfName + ".pdf"));
            document.open();

            PdfPTable table = new PdfPTable(1);

            PdfPCell cell = new PdfPCell(new Paragraph("Suggested Crops"));
            table.addCell(cell);

            for (int i = 0; i < list.size(); i++) {

                CropElementPercentage crop = list.get(i);

                cell = new PdfPCell(new Paragraph(crop.getCrop_name()));
                table.addCell(cell);

            }

            document.add(table);
            document.close();
            writer.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
