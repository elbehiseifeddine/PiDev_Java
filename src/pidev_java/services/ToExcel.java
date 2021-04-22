/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pidev_java.utils.MaConnection;

/**
 *
 * @author ahmed
 */
public class ToExcel {

    Connection cnx = MaConnection.getInstance().getCnx();

    private static final String[] columns = {"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre"};

    public void ReclamationExcel() throws IOException {
        try {
            String reqReclamation = "SELECT DATE_FORMAT(date_reclamation,'%M') mois ,COUNT(*) count FROM reclamation GROUP BY mois;";

            Statement stReclamation = cnx.createStatement();
            ResultSet rsReclamation = stReclamation.executeQuery(reqReclamation);

            int Reclamationjanvier = 0;
            int Reclamationfevrier = 0;
            int Reclamationmars = 0;
            int Reclamationavril = 0;
            int Reclamationmai = 0;
            int Reclamationjuin = 0;
            int Reclamationjuillet = 0;
            int Reclamationaout = 0;
            int Reclamationseptemebre = 0;
            int Reclamationoctobre = 0;
            int Reclamationnovembre = 0;
            int Reclamationdecembre = 0;
            while (rsReclamation.next()) {
                if (rsReclamation.getString("mois").equals("January")) {
                    Reclamationjanvier = rsReclamation.getInt("count");
                }
                if (rsReclamation.getString("mois").equals("February")) {
                    Reclamationfevrier = rsReclamation.getInt("count");
                }
                if (rsReclamation.getString("mois").equals("March")) {
                    Reclamationmars = rsReclamation.getInt("count");
                }
                if (rsReclamation.getString("mois").equals("April")) {
                    Reclamationavril = rsReclamation.getInt("count");
                }
                if (rsReclamation.getString("mois").equals("Mai")) {
                    Reclamationmai = rsReclamation.getInt("count");
                }
                if (rsReclamation.getString("mois").equals("June")) {
                    Reclamationjuin = rsReclamation.getInt("count");
                }
                if (rsReclamation.getString("mois").equals("July")) {
                    Reclamationjuillet = rsReclamation.getInt("count");
                }
                if (rsReclamation.getString("mois").equals("August")) {
                    Reclamationaout = rsReclamation.getInt("count");
                }
                if (rsReclamation.getString("mois").equals("September")) {
                    Reclamationseptemebre = rsReclamation.getInt("count");
                }
                if (rsReclamation.getString("mois").equals("October")) {
                    Reclamationoctobre = rsReclamation.getInt("count");
                }
                if (rsReclamation.getString("mois").equals("November")) {
                    Reclamationnovembre = rsReclamation.getInt("count");
                }
                if (rsReclamation.getString("mois").equals("December")) {
                    Reclamationdecembre = rsReclamation.getInt("count");
                }
            }

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Historique Reclamation");
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.RED.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);

// Create a Row
            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowNum = 1;

            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(Reclamationjanvier);
            row.createCell(1).setCellValue(Reclamationfevrier);
            row.createCell(2).setCellValue(Reclamationmars);
            row.createCell(3).setCellValue(Reclamationavril);
            row.createCell(4).setCellValue(Reclamationmai);
            row.createCell(5).setCellValue(Reclamationjuin);
            row.createCell(6).setCellValue(Reclamationjuillet);
            row.createCell(7).setCellValue(Reclamationaout);
            row.createCell(8).setCellValue(Reclamationseptemebre);
            row.createCell(9).setCellValue(Reclamationoctobre);
            row.createCell(10).setCellValue(Reclamationnovembre);
            row.createCell(11).setCellValue(Reclamationdecembre);

            row.getCell(0).setCellStyle(cellStyle);
            row.getCell(1).setCellStyle(cellStyle);
            row.getCell(2).setCellStyle(cellStyle);
            row.getCell(3).setCellStyle(cellStyle);
            row.getCell(4).setCellStyle(cellStyle);
            row.getCell(5).setCellStyle(cellStyle);
            row.getCell(6).setCellStyle(cellStyle);
            row.getCell(7).setCellStyle(cellStyle);
            row.getCell(8).setCellStyle(cellStyle);
            row.getCell(9).setCellStyle(cellStyle);
            row.getCell(10).setCellStyle(cellStyle);
            row.getCell(11).setCellStyle(cellStyle);
            // Resize all columns to fit the content size
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }
            FileOutputStream fileOut = new FileOutputStream("Historique.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
        } catch (SQLException ex) {
            Logger.getLogger(ToExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ToExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void OffreEmploiExcel() throws IOException {
        try {
            String reqOffreEmploi = "SELECT DATE_FORMAT(date_creation,'%M') mois ,COUNT(*) count FROM offre_emploi GROUP BY mois;";

            Statement stOffreEmploi = cnx.createStatement();
            ResultSet rsOffreEmploi = stOffreEmploi.executeQuery(reqOffreEmploi);

            int OffreEmploijanvier = 0;
            int OffreEmploifevrier = 0;
            int OffreEmploimars = 0;
            int OffreEmploiavril = 0;
            int OffreEmploimai = 0;
            int OffreEmploijuin = 0;
            int OffreEmploijuillet = 0;
            int OffreEmploiaout = 0;
            int OffreEmploiseptemebre = 0;
            int OffreEmploioctobre = 0;
            int OffreEmploinovembre = 0;
            int OffreEmploidecembre = 0;
            while (rsOffreEmploi.next()) {
                if (rsOffreEmploi.getString("mois").equals("January")){
                    OffreEmploijanvier = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("February")){
                    OffreEmploifevrier = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("March")){
                    OffreEmploimars = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("April")){
                    OffreEmploiavril = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("Mai")){
                    OffreEmploimai = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("June")){
                    OffreEmploijuin = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("July")){
                    OffreEmploijuillet = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("August")){
                    OffreEmploiaout = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("September")){
                    OffreEmploiseptemebre = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("October")){
                    OffreEmploioctobre = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("November")){
                    OffreEmploinovembre = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("December")){
                    OffreEmploidecembre = rsOffreEmploi.getInt("count");
                }
            }

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Historique Reclamation");
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.RED.getIndex());

            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

// Create a Row
            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowNum = 1;

            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(OffreEmploijanvier);
            row.createCell(1).setCellValue(OffreEmploifevrier);
            row.createCell(2).setCellValue(OffreEmploimars);
            row.createCell(3).setCellValue(OffreEmploiavril);
            row.createCell(4).setCellValue(OffreEmploimai);
            row.createCell(5).setCellValue(OffreEmploijuin);
            row.createCell(6).setCellValue(OffreEmploijuillet);
            row.createCell(7).setCellValue(OffreEmploiaout);
            row.createCell(8).setCellValue(OffreEmploiseptemebre);
            row.createCell(9).setCellValue(OffreEmploioctobre);
            row.createCell(10).setCellValue(OffreEmploinovembre);
            row.createCell(11).setCellValue(OffreEmploidecembre);


            row.getCell(0).setCellStyle(cellStyle);
            row.getCell(1).setCellStyle(cellStyle);
            row.getCell(2).setCellStyle(cellStyle);
            row.getCell(3).setCellStyle(cellStyle);
            row.getCell(4).setCellStyle(cellStyle);
            row.getCell(5).setCellStyle(cellStyle);
            row.getCell(6).setCellStyle(cellStyle);
            row.getCell(7).setCellStyle(cellStyle);
            row.getCell(8).setCellStyle(cellStyle);
            row.getCell(9).setCellStyle(cellStyle);
            row.getCell(10).setCellStyle(cellStyle);
            row.getCell(11).setCellStyle(cellStyle);

// Resize all columns to fit the content size
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }
            FileOutputStream fileOut = new FileOutputStream("HistoriqueOffreEmploi.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
        } catch (SQLException ex) {
            Logger.getLogger(ToExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ToExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
