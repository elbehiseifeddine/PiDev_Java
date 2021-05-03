/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_java.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Chart;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.charts.XSSFChartAxis;
import org.apache.poi.xssf.usermodel.charts.XSSFChartLegend;
import org.apache.poi.xssf.usermodel.charts.XSSFScatterChartData;
import org.apache.poi.xssf.usermodel.charts.XSSFValueAxis;
import pidev_java.utils.MaConnection;

/**
 *
 * @author ahmed
 */
public class ToExcel {

    Connection cnx = MaConnection.getInstance().getCnx();

    private static final String[] columns = {"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre"};

    public void ReclamationExcel(Workbook workbook) throws IOException {
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
                if (rsReclamation.getString("mois").equals("May")) {
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

            //Workbook workbook = new XSSFWorkbook();
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
            imageheader(sheet, workbook);
            Row headerRow = sheet.createRow(4);

            for (int i = 1; i < columns.length + 1; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i - 1]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowNum = 5;

            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("Nombre reclamation");
            row.createCell(1).setCellValue(Reclamationjanvier);
            row.createCell(2).setCellValue(Reclamationfevrier);
            row.createCell(3).setCellValue(Reclamationmars);
            row.createCell(4).setCellValue(Reclamationavril);
            row.createCell(5).setCellValue(Reclamationmai);
            row.createCell(6).setCellValue(Reclamationjuin);
            row.createCell(7).setCellValue(Reclamationjuillet);
            row.createCell(8).setCellValue(Reclamationaout);
            row.createCell(9).setCellValue(Reclamationseptemebre);
            row.createCell(10).setCellValue(Reclamationoctobre);
            row.createCell(11).setCellValue(Reclamationnovembre);
            row.createCell(12).setCellValue(Reclamationdecembre);

            row.getCell(0).setCellStyle(headerCellStyle);
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
            row.getCell(12).setCellStyle(cellStyle);
//            // Resize all columns to fit the content size
            for (int i = 0; i < columns.length + 1; i++) {
                sheet.autoSizeColumn(i);
            }
            ReclamationLineChart(sheet, workbook);
//            FileOutputStream fileOut = new FileOutputStream("Historique.xlsx");
//            workbook.write(fileOut);
//            fileOut.close();
//            workbook.close();
        } catch (SQLException ex) {
            Logger.getLogger(ToExcel.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(ToExcel.class.getName()).log(Level.SEVERE, null, ex);
//        }
        }
    }

    public void OffreExcel(Workbook workbook) throws IOException {
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
                if (rsOffreEmploi.getString("mois").equals("January")) {
                    OffreEmploijanvier = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("February")) {
                    OffreEmploifevrier = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("March")) {
                    OffreEmploimars = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("April")) {
                    OffreEmploiavril = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("May")) {
                    OffreEmploimai = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("June")) {
                    OffreEmploijuin = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("July")) {
                    OffreEmploijuillet = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("August")) {
                    OffreEmploiaout = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("September")) {
                    OffreEmploiseptemebre = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("October")) {
                    OffreEmploioctobre = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("November")) {
                    OffreEmploinovembre = rsOffreEmploi.getInt("count");
                }
                if (rsOffreEmploi.getString("mois").equals("December")) {
                    OffreEmploidecembre = rsOffreEmploi.getInt("count");
                }
            }

            String reqOffreStage = "SELECT DATE_FORMAT(date_creation,'%M') mois ,COUNT(*) count FROM offre_stage GROUP BY mois;";
            Statement stOffreStage = cnx.createStatement();
            ResultSet rsOffreStage = stOffreStage.executeQuery(reqOffreStage);

            int OffreStagejanvier = 0;
            int OffreStagefevrier = 0;
            int OffreStagemars = 0;
            int OffreStageavril = 0;
            int OffreStagemai = 0;
            int OffreStagejuin = 0;
            int OffreStagejuillet = 0;
            int OffreStageaout = 0;
            int OffreStageseptemebre = 0;
            int OffreStageoctobre = 0;
            int OffreStagenovembre = 0;
            int OffreStagedecembre = 0;
            while (rsOffreStage.next()) {
                if (rsOffreStage.getString("mois").equals("January")) {
                    OffreStagejanvier = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("February")) {
                    OffreStagefevrier = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("March")) {
                    OffreStagemars = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("April")) {
                    OffreStageavril = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("May")) {
                    OffreStagemai = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("June")) {
                    OffreStagejuin = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("July")) {
                    OffreStagejuillet = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("August")) {
                    OffreStageaout = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("September")) {
                    OffreStageseptemebre = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("October")) {
                    OffreStageoctobre = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("November")) {
                    OffreStagenovembre = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("December")) {
                    OffreStagedecembre = rsOffreStage.getInt("count");
                }
            }

            Sheet sheet = workbook.createSheet("Historique Offre ");
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.RED.getIndex());

            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            imageheader(sheet, workbook);
// Create a Row
            Row headerRow = sheet.createRow(4);

            for (int i = 1; i < columns.length + 1; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i - 1]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowNum = 5;

            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("Offre Emploi");
            row.createCell(1).setCellValue(OffreEmploijanvier);
            row.createCell(2).setCellValue(OffreEmploifevrier);
            row.createCell(3).setCellValue(OffreEmploimars);
            row.createCell(4).setCellValue(OffreEmploiavril);
            row.createCell(5).setCellValue(OffreEmploimai);
            row.createCell(6).setCellValue(OffreEmploijuin);
            row.createCell(7).setCellValue(OffreEmploijuillet);
            row.createCell(8).setCellValue(OffreEmploiaout);
            row.createCell(9).setCellValue(OffreEmploiseptemebre);
            row.createCell(10).setCellValue(OffreEmploioctobre);
            row.createCell(11).setCellValue(OffreEmploinovembre);
            row.createCell(12).setCellValue(OffreEmploidecembre);

            row.getCell(0).setCellStyle(headerCellStyle);
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
            row.getCell(12).setCellStyle(cellStyle);

            Row row2 = sheet.createRow(rowNum++);
            row2.createCell(0).setCellValue("Offre Stage");

            row2.createCell(1).setCellValue(OffreStagejanvier);
            row2.createCell(2).setCellValue(OffreStagefevrier);
            row2.createCell(3).setCellValue(OffreStagemars);
            row2.createCell(4).setCellValue(OffreStageavril);
            row2.createCell(5).setCellValue(OffreStagemai);
            row2.createCell(6).setCellValue(OffreStagejuin);
            row2.createCell(7).setCellValue(OffreStagejuillet);
            row2.createCell(8).setCellValue(OffreStageaout);
            row2.createCell(9).setCellValue(OffreStageseptemebre);
            row2.createCell(10).setCellValue(OffreStageoctobre);
            row2.createCell(11).setCellValue(OffreStagenovembre);
            row2.createCell(12).setCellValue(OffreStagedecembre);

            row2.getCell(0).setCellStyle(headerCellStyle);
            row2.getCell(1).setCellStyle(cellStyle);
            row2.getCell(2).setCellStyle(cellStyle);
            row2.getCell(3).setCellStyle(cellStyle);
            row2.getCell(4).setCellStyle(cellStyle);
            row2.getCell(5).setCellStyle(cellStyle);
            row2.getCell(6).setCellStyle(cellStyle);
            row2.getCell(7).setCellStyle(cellStyle);
            row2.getCell(8).setCellStyle(cellStyle);
            row2.getCell(9).setCellStyle(cellStyle);
            row2.getCell(10).setCellStyle(cellStyle);
            row2.getCell(11).setCellStyle(cellStyle);
            row2.getCell(12).setCellStyle(cellStyle);

// Resize all columns to fit the content size
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }
            EmploiScatterChart(sheet, workbook);
        } catch (SQLException ex) {
            Logger.getLogger(ToExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void OffreStageExcel(Workbook workbook) {
        try {
            String reqOffreStage = "SELECT DATE_FORMAT(date_creation,'%M') mois ,COUNT(*) count FROM offre_stage GROUP BY mois;";
            Statement stOffreStage = cnx.createStatement();
            ResultSet rsOffreStage = stOffreStage.executeQuery(reqOffreStage);

            int OffreStagejanvier = 0;
            int OffreStagefevrier = 0;
            int OffreStagemars = 0;
            int OffreStageavril = 0;
            int OffreStagemai = 0;
            int OffreStagejuin = 0;
            int OffreStagejuillet = 0;
            int OffreStageaout = 0;
            int OffreStageseptemebre = 0;
            int OffreStageoctobre = 0;
            int OffreStagenovembre = 0;
            int OffreStagedecembre = 0;
            while (rsOffreStage.next()) {
                if (rsOffreStage.getString("mois").equals("January")) {
                    OffreStagejanvier = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("February")) {
                    OffreStagefevrier = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("March")) {
                    OffreStagemars = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("April")) {
                    OffreStageavril = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("May")) {
                    OffreStagemai = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("June")) {
                    OffreStagejuin = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("July")) {
                    OffreStagejuillet = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("August")) {
                    OffreStageaout = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("September")) {
                    OffreStageseptemebre = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("October")) {
                    OffreStageoctobre = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("November")) {
                    OffreStagenovembre = rsOffreStage.getInt("count");
                }
                if (rsOffreStage.getString("mois").equals("December")) {
                    OffreStagedecembre = rsOffreStage.getInt("count");
                }
            }

            Sheet sheet = workbook.createSheet("Historique Offre Stage");
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
            row.createCell(0).setCellValue(OffreStagejanvier);
            row.createCell(1).setCellValue(OffreStagefevrier);
            row.createCell(2).setCellValue(OffreStagemars);
            row.createCell(3).setCellValue(OffreStageavril);
            row.createCell(4).setCellValue(OffreStagemai);
            row.createCell(5).setCellValue(OffreStagejuin);
            row.createCell(6).setCellValue(OffreStagejuillet);
            row.createCell(7).setCellValue(OffreStageaout);
            row.createCell(8).setCellValue(OffreStageseptemebre);
            row.createCell(9).setCellValue(OffreStageoctobre);
            row.createCell(10).setCellValue(OffreStagenovembre);
            row.createCell(11).setCellValue(OffreStagedecembre);

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
        } catch (SQLException ex) {
            Logger.getLogger(ToExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void FreelancerExcel(Workbook workbook) {
        try {
            String reqFreelancer = "SELECT DATE_FORMAT(date_creation,'%M') mois ,COUNT(*) count FROM freelancer GROUP BY mois;";
            Statement stFreelancer = cnx.createStatement();
            ResultSet rsFreelancer = stFreelancer.executeQuery(reqFreelancer);

            int Freelancerjanvier = 0;
            int Freelancerfevrier = 0;
            int Freelancermars = 0;
            int Freelanceravril = 0;
            int Freelancermai = 0;
            int Freelancerjuin = 0;
            int Freelancerjuillet = 0;
            int Freelanceraout = 0;
            int Freelancerseptemebre = 0;
            int Freelanceroctobre = 0;
            int Freelancernovembre = 0;
            int Freelancerdecembre = 0;
            while (rsFreelancer.next()) {
                if (rsFreelancer.getString("mois").equals("January")) {
                    Freelancerjanvier = rsFreelancer.getInt("count");
                }
                if (rsFreelancer.getString("mois").equals("February")) {
                    Freelancerfevrier = rsFreelancer.getInt("count");
                }
                if (rsFreelancer.getString("mois").equals("March")) {
                    Freelancermars = rsFreelancer.getInt("count");
                }
                if (rsFreelancer.getString("mois").equals("April")) {
                    Freelanceravril = rsFreelancer.getInt("count");
                }
                if (rsFreelancer.getString("mois").equals("May")) {
                    Freelancermai = rsFreelancer.getInt("count");
                }
                if (rsFreelancer.getString("mois").equals("June")) {
                    Freelancerjuin = rsFreelancer.getInt("count");
                }
                if (rsFreelancer.getString("mois").equals("July")) {
                    Freelancerjuillet = rsFreelancer.getInt("count");
                }
                if (rsFreelancer.getString("mois").equals("August")) {
                    Freelanceraout = rsFreelancer.getInt("count");
                }
                if (rsFreelancer.getString("mois").equals("September")) {
                    Freelancerseptemebre = rsFreelancer.getInt("count");
                }
                if (rsFreelancer.getString("mois").equals("October")) {
                    Freelanceroctobre = rsFreelancer.getInt("count");
                }
                if (rsFreelancer.getString("mois").equals("November")) {
                    Freelancernovembre = rsFreelancer.getInt("count");
                }
                if (rsFreelancer.getString("mois").equals("December")) {
                    Freelancerdecembre = rsFreelancer.getInt("count");
                }
            }

            String reqSociete = "SELECT DATE_FORMAT(date_creation,'%M') mois ,COUNT(*) count FROM freelancer GROUP BY mois;";
            Statement stSociete = cnx.createStatement();
            ResultSet rsSociete = stSociete.executeQuery(reqSociete);

            int Societejanvier = 0;
            int Societefevrier = 0;
            int Societemars = 0;
            int Societeavril = 0;
            int Societemai = 0;
            int Societejuin = 0;
            int Societejuillet = 0;
            int Societeaout = 0;
            int Societeseptemebre = 0;
            int Societeoctobre = 0;
            int Societenovembre = 0;
            int Societedecembre = 0;
            while (rsSociete.next()) {
                if (rsSociete.getString("mois").equals("January")) {
                    Societejanvier = rsSociete.getInt("count");
                }
                if (rsSociete.getString("mois").equals("February")) {
                    Societefevrier = rsSociete.getInt("count");
                }
                if (rsSociete.getString("mois").equals("March")) {
                    Societemars = rsSociete.getInt("count");
                }
                if (rsSociete.getString("mois").equals("April")) {
                    Societeavril = rsSociete.getInt("count");
                }
                if (rsSociete.getString("mois").equals("May")) {
                    Societemai = rsSociete.getInt("count");
                }
                if (rsSociete.getString("mois").equals("June")) {
                    Societejuin = rsSociete.getInt("count");
                }
                if (rsSociete.getString("mois").equals("July")) {
                    Societejuillet = rsSociete.getInt("count");
                }
                if (rsSociete.getString("mois").equals("August")) {
                    Societeaout = rsSociete.getInt("count");
                }
                if (rsSociete.getString("mois").equals("September")) {
                    Societeseptemebre = rsSociete.getInt("count");
                }
                if (rsSociete.getString("mois").equals("October")) {
                    Societeoctobre = rsSociete.getInt("count");
                }
                if (rsSociete.getString("mois").equals("November")) {
                    Societenovembre = rsSociete.getInt("count");
                }
                if (rsSociete.getString("mois").equals("December")) {
                    Societedecembre = rsSociete.getInt("count");
                }
            }

            Sheet sheet = workbook.createSheet("Historique Utilisateurs");
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.RED.getIndex());

            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            imageheader(sheet, workbook);
// Create a Row
            Row headerRow = sheet.createRow(4);

            for (int i = 1; i < columns.length + 1; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i - 1]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowNum = 5;

            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("Freelancer");
            row.createCell(1).setCellValue(Freelancerjanvier);
            row.createCell(2).setCellValue(Freelancerfevrier);
            row.createCell(3).setCellValue(Freelancermars);
            row.createCell(4).setCellValue(Freelanceravril);
            row.createCell(5).setCellValue(Freelancermai);
            row.createCell(6).setCellValue(Freelancerjuin);
            row.createCell(7).setCellValue(Freelancerjuillet);
            row.createCell(8).setCellValue(Freelanceraout);
            row.createCell(9).setCellValue(Freelancerseptemebre);
            row.createCell(10).setCellValue(Freelanceroctobre);
            row.createCell(11).setCellValue(Freelancernovembre);
            row.createCell(12).setCellValue(Freelancerdecembre);

            row.getCell(0).setCellStyle(headerCellStyle);
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
            row.getCell(12).setCellStyle(cellStyle);

            Row row2 = sheet.createRow(rowNum++);
            row2.createCell(0).setCellValue("Societe");
            row2.createCell(1).setCellValue(Societejanvier);
            row2.createCell(2).setCellValue(Societefevrier);
            row2.createCell(3).setCellValue(Societemars);
            row2.createCell(4).setCellValue(Societeavril);
            row2.createCell(5).setCellValue(Societemai);
            row2.createCell(6).setCellValue(Societejuin);
            row2.createCell(7).setCellValue(Societejuillet);
            row2.createCell(8).setCellValue(Societeaout);
            row2.createCell(9).setCellValue(Societeseptemebre);
            row2.createCell(10).setCellValue(Societeoctobre);
            row2.createCell(11).setCellValue(Societenovembre);
            row2.createCell(12).setCellValue(Societedecembre);

            row2.getCell(0).setCellStyle(headerCellStyle);
            row2.getCell(1).setCellStyle(cellStyle);
            row2.getCell(2).setCellStyle(cellStyle);
            row2.getCell(3).setCellStyle(cellStyle);
            row2.getCell(4).setCellStyle(cellStyle);
            row2.getCell(5).setCellStyle(cellStyle);
            row2.getCell(6).setCellStyle(cellStyle);
            row2.getCell(7).setCellStyle(cellStyle);
            row2.getCell(8).setCellStyle(cellStyle);
            row2.getCell(9).setCellStyle(cellStyle);
            row2.getCell(10).setCellStyle(cellStyle);
            row2.getCell(11).setCellStyle(cellStyle);
            row2.getCell(12).setCellStyle(cellStyle);

// Resize all columns to fit the content size
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }
            UserScatterChart(sheet, workbook);

        } catch (SQLException ex) {
            Logger.getLogger(ToExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SocieteExcel(Workbook workbook) {
        try {
            String reqSociete = "SELECT DATE_FORMAT(date_creation,'%M') mois ,COUNT(*) count FROM freelancer GROUP BY mois;";
            Statement stSociete = cnx.createStatement();
            ResultSet rsSociete = stSociete.executeQuery(reqSociete);

            int Societejanvier = 0;
            int Societefevrier = 0;
            int Societemars = 0;
            int Societeavril = 0;
            int Societemai = 0;
            int Societejuin = 0;
            int Societejuillet = 0;
            int Societeaout = 0;
            int Societeseptemebre = 0;
            int Societeoctobre = 0;
            int Societenovembre = 0;
            int Societedecembre = 0;
            while (rsSociete.next()) {
                if (rsSociete.getString("mois").equals("January")) {
                    Societejanvier = rsSociete.getInt("count");
                }
                if (rsSociete.getString("mois").equals("February")) {
                    Societefevrier = rsSociete.getInt("count");
                }
                if (rsSociete.getString("mois").equals("March")) {
                    Societemars = rsSociete.getInt("count");
                }
                if (rsSociete.getString("mois").equals("April")) {
                    Societeavril = rsSociete.getInt("count");
                }
                if (rsSociete.getString("mois").equals("May")) {
                    Societemai = rsSociete.getInt("count");
                }
                if (rsSociete.getString("mois").equals("June")) {
                    Societejuin = rsSociete.getInt("count");
                }
                if (rsSociete.getString("mois").equals("July")) {
                    Societejuillet = rsSociete.getInt("count");
                }
                if (rsSociete.getString("mois").equals("August")) {
                    Societeaout = rsSociete.getInt("count");
                }
                if (rsSociete.getString("mois").equals("September")) {
                    Societeseptemebre = rsSociete.getInt("count");
                }
                if (rsSociete.getString("mois").equals("October")) {
                    Societeoctobre = rsSociete.getInt("count");
                }
                if (rsSociete.getString("mois").equals("November")) {
                    Societenovembre = rsSociete.getInt("count");
                }
                if (rsSociete.getString("mois").equals("December")) {
                    Societedecembre = rsSociete.getInt("count");
                }
            }

            Sheet sheet = workbook.createSheet("Historique Societe");
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
            row.createCell(0).setCellValue(Societejanvier);
            row.createCell(1).setCellValue(Societefevrier);
            row.createCell(2).setCellValue(Societemars);
            row.createCell(3).setCellValue(Societeavril);
            row.createCell(4).setCellValue(Societemai);
            row.createCell(5).setCellValue(Societejuin);
            row.createCell(6).setCellValue(Societejuillet);
            row.createCell(7).setCellValue(Societeaout);
            row.createCell(8).setCellValue(Societeseptemebre);
            row.createCell(9).setCellValue(Societeoctobre);
            row.createCell(10).setCellValue(Societenovembre);
            row.createCell(11).setCellValue(Societedecembre);

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
        } catch (SQLException ex) {
            Logger.getLogger(ToExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DemandeExcel(Workbook workbook) throws IOException {
        try {
            String reqDemandeEmploi = "SELECT DATE_FORMAT(date_creation,'%M') mois ,COUNT(*) count FROM demande_emploi GROUP BY mois;";

            Statement stDemandeEmploi = cnx.createStatement();
            ResultSet rsDemandeEmploi = stDemandeEmploi.executeQuery(reqDemandeEmploi);

            int DemandeEmploijanvier = 0;
            int DemandeEmploifevrier = 0;
            int DemandeEmploimars = 0;
            int DemandeEmploiavril = 0;
            int DemandeEmploimai = 0;
            int DemandeEmploijuin = 0;
            int DemandeEmploijuillet = 0;
            int DemandeEmploiaout = 0;
            int DemandeEmploiseptemebre = 0;
            int DemandeEmploioctobre = 0;
            int DemandeEmploinovembre = 0;
            int DemandeEmploidecembre = 0;
            while (rsDemandeEmploi.next()) {
                if (rsDemandeEmploi.getString("mois").equals("January")) {
                    DemandeEmploijanvier = rsDemandeEmploi.getInt("count");
                }
                if (rsDemandeEmploi.getString("mois").equals("February")) {
                    DemandeEmploifevrier = rsDemandeEmploi.getInt("count");
                }
                if (rsDemandeEmploi.getString("mois").equals("March")) {
                    DemandeEmploimars = rsDemandeEmploi.getInt("count");
                }
                if (rsDemandeEmploi.getString("mois").equals("April")) {
                    DemandeEmploiavril = rsDemandeEmploi.getInt("count");
                }
                if (rsDemandeEmploi.getString("mois").equals("May")) {
                    DemandeEmploimai = rsDemandeEmploi.getInt("count");
                }
                if (rsDemandeEmploi.getString("mois").equals("June")) {
                    DemandeEmploijuin = rsDemandeEmploi.getInt("count");
                }
                if (rsDemandeEmploi.getString("mois").equals("July")) {
                    DemandeEmploijuillet = rsDemandeEmploi.getInt("count");
                }
                if (rsDemandeEmploi.getString("mois").equals("August")) {
                    DemandeEmploiaout = rsDemandeEmploi.getInt("count");
                }
                if (rsDemandeEmploi.getString("mois").equals("September")) {
                    DemandeEmploiseptemebre = rsDemandeEmploi.getInt("count");
                }
                if (rsDemandeEmploi.getString("mois").equals("October")) {
                    DemandeEmploioctobre = rsDemandeEmploi.getInt("count");
                }
                if (rsDemandeEmploi.getString("mois").equals("November")) {
                    DemandeEmploinovembre = rsDemandeEmploi.getInt("count");
                }
                if (rsDemandeEmploi.getString("mois").equals("December")) {
                    DemandeEmploidecembre = rsDemandeEmploi.getInt("count");
                }
            }

            String reqDemandeStage = "SELECT DATE_FORMAT(date_creation,'%M') mois ,COUNT(*) count FROM demande_emploi GROUP BY mois;";

            Statement stDemandeStage = cnx.createStatement();
            ResultSet rsDemandeStage = stDemandeStage.executeQuery(reqDemandeStage);

            int DemandeStagejanvier = 0;
            int DemandeStagefevrier = 0;
            int DemandeStagemars = 0;
            int DemandeStageavril = 0;
            int DemandeStagemai = 0;
            int DemandeStagejuin = 0;
            int DemandeStagejuillet = 0;
            int DemandeStageaout = 0;
            int DemandeStageseptemebre = 0;
            int DemandeStageoctobre = 0;
            int DemandeStagenovembre = 0;
            int DemandeStagedecembre = 0;
            while (rsDemandeStage.next()) {
                if (rsDemandeStage.getString("mois").equals("January")) {
                    DemandeStagejanvier = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("February")) {
                    DemandeStagefevrier = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("March")) {
                    DemandeStagemars = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("April")) {
                    DemandeStageavril = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("May")) {
                    DemandeStagemai = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("June")) {
                    DemandeStagejuin = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("July")) {
                    DemandeStagejuillet = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("August")) {
                    DemandeStageaout = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("September")) {
                    DemandeStageseptemebre = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("October")) {
                    DemandeStageoctobre = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("November")) {
                    DemandeStagenovembre = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("December")) {
                    DemandeStagedecembre = rsDemandeStage.getInt("count");
                }
            }

            Sheet sheet = workbook.createSheet("Historique Demande");
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.RED.getIndex());

            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            imageheader(sheet, workbook);
// Create a Row
            Row headerRow = sheet.createRow(4);

            for (int i = 1; i < columns.length + 1; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i - 1]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowNum = 5;

            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue("Demande Emploi");
            row.createCell(1).setCellValue(DemandeEmploijanvier);
            row.createCell(2).setCellValue(DemandeEmploifevrier);
            row.createCell(3).setCellValue(DemandeEmploimars);
            row.createCell(4).setCellValue(DemandeEmploiavril);
            row.createCell(5).setCellValue(DemandeEmploimai);
            row.createCell(6).setCellValue(DemandeEmploijuin);
            row.createCell(7).setCellValue(DemandeEmploijuillet);
            row.createCell(8).setCellValue(DemandeEmploiaout);
            row.createCell(9).setCellValue(DemandeEmploiseptemebre);
            row.createCell(10).setCellValue(DemandeEmploioctobre);
            row.createCell(11).setCellValue(DemandeEmploinovembre);
            row.createCell(12).setCellValue(DemandeEmploidecembre);

            row.getCell(0).setCellStyle(headerCellStyle);
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
            row.getCell(12).setCellStyle(cellStyle);

            Row row2 = sheet.createRow(rowNum++);
            row2.createCell(0).setCellValue("Demande stage");
            row2.createCell(1).setCellValue(DemandeStagejanvier);
            row2.createCell(2).setCellValue(DemandeStagefevrier);
            row2.createCell(3).setCellValue(DemandeStagemars);
            row2.createCell(4).setCellValue(DemandeStageavril);
            row2.createCell(5).setCellValue(DemandeStagemai);
            row2.createCell(6).setCellValue(DemandeStagejuin);
            row2.createCell(7).setCellValue(DemandeStagejuillet);
            row2.createCell(8).setCellValue(DemandeStageaout);
            row2.createCell(9).setCellValue(DemandeStageseptemebre);
            row2.createCell(10).setCellValue(DemandeStageoctobre);
            row2.createCell(11).setCellValue(DemandeStagenovembre);
            row2.createCell(12).setCellValue(DemandeStagedecembre);

            row2.getCell(0).setCellStyle(headerCellStyle);
            row2.getCell(1).setCellStyle(cellStyle);
            row2.getCell(2).setCellStyle(cellStyle);
            row2.getCell(3).setCellStyle(cellStyle);
            row2.getCell(4).setCellStyle(cellStyle);
            row2.getCell(5).setCellStyle(cellStyle);
            row2.getCell(6).setCellStyle(cellStyle);
            row2.getCell(7).setCellStyle(cellStyle);
            row2.getCell(8).setCellStyle(cellStyle);
            row2.getCell(9).setCellStyle(cellStyle);
            row2.getCell(10).setCellStyle(cellStyle);
            row2.getCell(11).setCellStyle(cellStyle);
            row2.getCell(12).setCellStyle(cellStyle);

// Resize all columns to fit the content size
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }
            DemandeLineChart(sheet, workbook);
        } catch (SQLException ex) {
            Logger.getLogger(ToExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DemandeStageExcel(Workbook workbook) throws IOException {
        try {
            String reqDemandeStage = "SELECT DATE_FORMAT(date_creation,'%M') mois ,COUNT(*) count FROM demande_emploi GROUP BY mois;";

            Statement stDemandeStage = cnx.createStatement();
            ResultSet rsDemandeStage = stDemandeStage.executeQuery(reqDemandeStage);

            int DemandeStagejanvier = 0;
            int DemandeStagefevrier = 0;
            int DemandeStagemars = 0;
            int DemandeStageavril = 0;
            int DemandeStagemai = 0;
            int DemandeStagejuin = 0;
            int DemandeStagejuillet = 0;
            int DemandeStageaout = 0;
            int DemandeStageseptemebre = 0;
            int DemandeStageoctobre = 0;
            int DemandeStagenovembre = 0;
            int DemandeStagedecembre = 0;
            while (rsDemandeStage.next()) {
                if (rsDemandeStage.getString("mois").equals("January")) {
                    DemandeStagejanvier = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("February")) {
                    DemandeStagefevrier = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("March")) {
                    DemandeStagemars = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("April")) {
                    DemandeStageavril = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("May")) {
                    DemandeStagemai = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("June")) {
                    DemandeStagejuin = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("July")) {
                    DemandeStagejuillet = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("August")) {
                    DemandeStageaout = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("September")) {
                    DemandeStageseptemebre = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("October")) {
                    DemandeStageoctobre = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("November")) {
                    DemandeStagenovembre = rsDemandeStage.getInt("count");
                }
                if (rsDemandeStage.getString("mois").equals("December")) {
                    DemandeStagedecembre = rsDemandeStage.getInt("count");
                }
            }

            Sheet sheet = workbook.createSheet("Historique Demande Stage");
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
            row.createCell(0).setCellValue(DemandeStagejanvier);
            row.createCell(1).setCellValue(DemandeStagefevrier);
            row.createCell(2).setCellValue(DemandeStagemars);
            row.createCell(3).setCellValue(DemandeStageavril);
            row.createCell(4).setCellValue(DemandeStagemai);
            row.createCell(5).setCellValue(DemandeStagejuin);
            row.createCell(6).setCellValue(DemandeStagejuillet);
            row.createCell(7).setCellValue(DemandeStageaout);
            row.createCell(8).setCellValue(DemandeStageseptemebre);
            row.createCell(9).setCellValue(DemandeStageoctobre);
            row.createCell(10).setCellValue(DemandeStagenovembre);
            row.createCell(11).setCellValue(DemandeStagedecembre);

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

        } catch (SQLException ex) {
            Logger.getLogger(ToExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excelfile() {
        try {

            Workbook workbook = new XSSFWorkbook();
            ReclamationExcel(workbook);
            OffreExcel(workbook);

            FreelancerExcel(workbook);

            DemandeExcel(workbook);

            JFileChooser f = new JFileChooser();
            f.setDialogTitle("save : ");
            f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            f.showSaveDialog(null);
            if (f.getSelectedFile() != null) {
                FileOutputStream fileOut = new FileOutputStream(f.getSelectedFile() + "\\Historique.xlsx");
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();
                System.out.println(f.getSelectedFile());
            }

        } catch (IOException ex) {
            Logger.getLogger(ToExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ReclamationLineChart(Sheet sheet, Workbook workbook) {

        Drawing drawing = sheet.createDrawingPatriarch();

        ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 5, 9, 15, 19);

        Chart chart = drawing.createChart(anchor);
        ((XSSFChart) chart).setTitleText("Nombre de reclamation par mois");

//set "the title overlays the plot area" to false explicitly
        ((XSSFChart) chart).getCTChart().getTitle().addNewOverlay().setVal(false);

        //set font style for title - low level
        //add run properties to title's first paragraph and first text run. Set bold.
        ((XSSFChart) chart).getCTChart().getTitle().getTx().getRich().getPArray(0).getRArray(0).addNewRPr().setB(true);
        //set italic
        ((XSSFChart) chart).getCTChart().getTitle().getTx().getRich().getPArray(0).getRArray(0).getRPr().setI(true);
        //set font size 20pt
        ((XSSFChart) chart).getCTChart().getTitle().getTx().getRich().getPArray(0).getRArray(0).getRPr().setSz(1000);
        //add type face for latin and complex script characters
        ((XSSFChart) chart).getCTChart().getTitle().getTx().getRich().getPArray(0).getRArray(0).getRPr().addNewLatin().setTypeface("Times New Roman");
        ((XSSFChart) chart).getCTChart().getTitle().getTx().getRich().getPArray(0).getRArray(0).getRPr().addNewCs().setTypeface("Times New Roman");

        ChartLegend legend = chart.getOrCreateLegend();

        legend.setPosition(LegendPosition.TOP_RIGHT);

        LineChartData data = chart.getChartDataFactory().createLineChartData();

// Use a category axis for the bottom axis.
        ChartAxis bottomAxis = chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);

        ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);

        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        ChartDataSource<Number> xs = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(4, 4, 1, 12));

        ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(5, 5, 1, 12));

        //ChartDataSource<Number> ys2 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(2, 2, 0, 11));
        LineChartSeries series = data.addSeries(xs, ys1);

        series.setTitle("Reclamation/mois");
        //data.addSeries(xs, ys2);

        chart.plot(data, bottomAxis, leftAxis);

    }

    public void EmploiScatterChart(Sheet sheet, Workbook workbook) {
        XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 5, 9, 15, 19);

        XSSFChart chart = drawing.createChart(anchor);
        XSSFChartLegend legend = chart.getOrCreateLegend();
        legend.setPosition(LegendPosition.TOP_RIGHT);

        ((XSSFChart) chart).setTitleText("Nombre de Offre d'emploi/stage par mois");

//set "the title overlays the plot area" to false explicitly
        ((XSSFChart) chart).getCTChart().getTitle().addNewOverlay().setVal(false);

        //set font style for title - low level
        //add run properties to title's first paragraph and first text run. Set bold.
        ((XSSFChart) chart).getCTChart().getTitle().getTx().getRich().getPArray(0).getRArray(0).addNewRPr().setB(true);
        //set italic
        ((XSSFChart) chart).getCTChart().getTitle().getTx().getRich().getPArray(0).getRArray(0).getRPr().setI(true);
        //set font size 20pt
        ((XSSFChart) chart).getCTChart().getTitle().getTx().getRich().getPArray(0).getRArray(0).getRPr().setSz(1000);
        //add type face for latin and complex script characters
        ((XSSFChart) chart).getCTChart().getTitle().getTx().getRich().getPArray(0).getRArray(0).getRPr().addNewLatin().setTypeface("Times New Roman");
        ((XSSFChart) chart).getCTChart().getTitle().getTx().getRich().getPArray(0).getRArray(0).getRPr().addNewCs().setTypeface("Times New Roman");

        XSSFChartAxis bottomAxis = chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
        XSSFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        //CellRangeAddress crXData = new CellRangeAddress(4, 4, 1, 12);
        //CellRangeAddress crYData1 = new CellRangeAddress(5, 5, 1, 12);
        //CellRangeAddress crYData2 = new CellRangeAddress(6, 6, 1, 12);
        ChartDataSource<Number> dsXData = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(4, 4, 1, 12));
        ChartDataSource<Number> dsYData1 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(5, 5, 1, 12));
        ChartDataSource<Number> dsYData2 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(6, 6, 1, 12));

        XSSFScatterChartData data = chart.getChartDataFactory().createScatterChartData();
        ScatterChartSeries seriesTitler1 = data.addSerie(dsXData, dsYData1);

        ScatterChartSeries seriesTitler2 = data.addSerie(dsXData, dsYData2);

        seriesTitler1.setTitle("Offre Emploi/mois");
        seriesTitler2.setTitle("Offre Stage/mois");
        chart.plot(data, bottomAxis, leftAxis);
    }

    public void imageheader(Sheet sheet, Workbook workbook) {
        try {
            //InputStream inputStream = new FileInputStream("C:\\xampp\\htdocs\\PiDev_Java\\src\\pidev_java\\assets\\Logo complet (1).png");
            InputStream inputStream = new FileInputStream("/pidev_java/assets/Logo complet (1).png");

            byte[] imageBytes = IOUtils.toByteArray(inputStream);

            int pictureureIdx = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_PNG);

            inputStream.close();

            sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 2));
            CreationHelper helper = workbook.getCreationHelper();

            Drawing drawingPicture = sheet.createDrawingPatriarch();

            XSSFClientAnchor anchorPicture = (XSSFClientAnchor) helper.createClientAnchor();

            anchorPicture.setCol1(3);
            anchorPicture.setRow1(3);

            drawingPicture.createPicture(anchorPicture, pictureureIdx);
            sheet.autoSizeColumn(0);
        } catch (IOException ex) {
            Logger.getLogger(ToExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DemandeLineChart(Sheet sheet, Workbook workbook) {

        Drawing drawing = sheet.createDrawingPatriarch();

        ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 5, 9, 15, 19);

        Chart chart = drawing.createChart(anchor);
        ((XSSFChart) chart).setTitleText("Nombre de demande d'emploi/stage par mois");

//set "the title overlays the plot area" to false explicitly
        ((XSSFChart) chart).getCTChart().getTitle().addNewOverlay().setVal(false);

        //set font style for title - low level
        //add run properties to title's first paragraph and first text run. Set bold.
        ((XSSFChart) chart).getCTChart().getTitle().getTx().getRich().getPArray(0).getRArray(0).addNewRPr().setB(true);
        //set italic
        ((XSSFChart) chart).getCTChart().getTitle().getTx().getRich().getPArray(0).getRArray(0).getRPr().setI(true);
        //set font size 20pt
        ((XSSFChart) chart).getCTChart().getTitle().getTx().getRich().getPArray(0).getRArray(0).getRPr().setSz(1000);
        //add type face for latin and complex script characters
        ((XSSFChart) chart).getCTChart().getTitle().getTx().getRich().getPArray(0).getRArray(0).getRPr().addNewLatin().setTypeface("Times New Roman");
        ((XSSFChart) chart).getCTChart().getTitle().getTx().getRich().getPArray(0).getRArray(0).getRPr().addNewCs().setTypeface("Times New Roman");

        ChartLegend legend = chart.getOrCreateLegend();

        legend.setPosition(LegendPosition.TOP_RIGHT);

        LineChartData data = chart.getChartDataFactory().createLineChartData();

// Use a category axis for the bottom axis.
        ChartAxis bottomAxis = chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);

        ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);

        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        ChartDataSource<Number> xs = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(4, 4, 1, 12));

        ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(5, 5, 1, 12));

        ChartDataSource<Number> ys2 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(6, 6, 1, 12));

        LineChartSeries series1 = data.addSeries(xs, ys1);

        series1.setTitle("Demande Emploi/mois");
        LineChartSeries series2 = data.addSeries(xs, ys2);

        series2.setTitle("Demande Stage/mois");

        chart.plot(data, bottomAxis, leftAxis);

    }

    public void UserScatterChart(Sheet sheet, Workbook workbook) {
        XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 5, 9, 15, 19);

        XSSFChart chart = drawing.createChart(anchor);
        XSSFChartLegend legend = chart.getOrCreateLegend();
        legend.setPosition(LegendPosition.TOP_RIGHT);

        ((XSSFChart) chart).setTitleText("Nombre des Utilisateurs par mois");

//set "the title overlays the plot area" to false explicitly
        ((XSSFChart) chart).getCTChart().getTitle().addNewOverlay().setVal(false);

        //set font style for title - low level
        //add run properties to title's first paragraph and first text run. Set bold.
        ((XSSFChart) chart).getCTChart().getTitle().getTx().getRich().getPArray(0).getRArray(0).addNewRPr().setB(true);
        //set italic
        ((XSSFChart) chart).getCTChart().getTitle().getTx().getRich().getPArray(0).getRArray(0).getRPr().setI(true);
        //set font size 20pt
        ((XSSFChart) chart).getCTChart().getTitle().getTx().getRich().getPArray(0).getRArray(0).getRPr().setSz(1000);
        //add type face for latin and complex script characters
        ((XSSFChart) chart).getCTChart().getTitle().getTx().getRich().getPArray(0).getRArray(0).getRPr().addNewLatin().setTypeface("Times New Roman");
        ((XSSFChart) chart).getCTChart().getTitle().getTx().getRich().getPArray(0).getRArray(0).getRPr().addNewCs().setTypeface("Times New Roman");

        XSSFChartAxis bottomAxis = chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
        XSSFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        //CellRangeAddress crXData = new CellRangeAddress(4, 4, 1, 12);
        //CellRangeAddress crYData1 = new CellRangeAddress(5, 5, 1, 12);
        //CellRangeAddress crYData2 = new CellRangeAddress(6, 6, 1, 12);
        ChartDataSource<Number> dsXData = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(4, 4, 1, 12));
        ChartDataSource<Number> dsYData1 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(5, 5, 1, 12));
        ChartDataSource<Number> dsYData2 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(6, 6, 1, 12));

        XSSFScatterChartData data = chart.getChartDataFactory().createScatterChartData();
        ScatterChartSeries seriesTitler1 = data.addSerie(dsXData, dsYData1);

        ScatterChartSeries seriesTitler2 = data.addSerie(dsXData, dsYData2);

        seriesTitler1.setTitle("Freelancers/mois");
        seriesTitler2.setTitle("Societes/mois");
        chart.plot(data, bottomAxis, leftAxis);
    }

}
