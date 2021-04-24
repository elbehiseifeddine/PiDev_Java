///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package pidev_java.utils;
//
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.Rectangle;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//import java.awt.Desktop;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.ArrayList;
//import pidev_java.entities.Freelancer;
//import pidev_java.entities.Societe;
//
///**
// *
// * @author seifeddine
// */
//public class PdfGeneration extends Thread{
//    @Override
//    public void run() {
//        System.out.println("Hello from a thread!");
//    }
//
//    public static void FreelancerListPdf(ArrayList<Freelancer> List) {
//        try {
//            Document document = new Document();
//            //Simple Paragraph Pdf
//            String desktopPath = System.getProperty("user.home") + "/Desktop";
//            System.out.print(desktopPath.replace("\\", "/"));
//            PdfWriter.getInstance(document, new FileOutputStream(desktopPath+"\\FreelancerList.pdf"));
//            document.open();
//            //document.add(new Paragraph("exemple"));
//            //document.close();
//
//            //-------------------------------------------------------
//            //Add Table
//            Font font = new Font();
//            font.setColor(new BaseColor(255, 255, 255));
//            font.setSize(10);
//            PdfPTable table = new PdfPTable(6);
//            table.setWidthPercentage(100);
//            PdfPCell c = new PdfPCell(new Phrase("Nom", font));
//            c.setBackgroundColor(new BaseColor(76, 175, 80));
//                
//            c.setBorder(Rectangle.NO_BORDER);
//            table.addCell(c);
//            c = new PdfPCell(new Phrase("Prenom", font));
//            c.setBackgroundColor(new BaseColor(76, 175, 80));
//            c.setBorder(Rectangle.NO_BORDER);
//            table.addCell(c);
//            c = new PdfPCell(new Phrase("Email", font));
//            c.setBackgroundColor(new BaseColor(76, 175, 80));
//            c.setBorder(Rectangle.NO_BORDER);
//            table.addCell(c);
//            c = new PdfPCell(new Phrase("Adresse", font));
//            c.setBackgroundColor(new BaseColor(76, 175, 80));
//            c.setBorder(Rectangle.NO_BORDER);
//            table.addCell(c);
//            c = new PdfPCell(new Phrase("Sexe", font));
//            c.setBackgroundColor(new BaseColor(76, 175, 80));
//            c.setBorder(Rectangle.NO_BORDER);
//            table.addCell(c);
//            c = new PdfPCell(new Phrase("date Creation", font));
//            c.setBackgroundColor(new BaseColor(76, 175, 80));
//            c.setBorder(Rectangle.NO_BORDER);
//            table.addCell(c);
//            table.setHeaderRows(1);
//            Font font2 = new Font();
//            font2.setColor(new BaseColor(0, 0, 0));
//            font2.setSize(10);
//            for (int i = 0; i < List.size(); i++) {
//                if (i % 2 != 0) {
//                    c = new PdfPCell(new Phrase(List.get(i).getNom(), font2));
//                    c.setBorder(Rectangle.NO_BORDER);
//                    table.addCell(c);
//                    c = new PdfPCell(new Phrase(List.get(i).getPrenom(), font2));
//                    c.setBorder(Rectangle.NO_BORDER);
//                    table.addCell(c);
//                    c = new PdfPCell(new Phrase(List.get(i).getEmail(), font2));
//                    c.setBorder(Rectangle.NO_BORDER);
//                    table.addCell(c);
//                    c = new PdfPCell(new Phrase(List.get(i).getAdresse(), font2));
//                    c.setBorder(Rectangle.NO_BORDER);
//                    table.addCell(c);
//                    c = new PdfPCell(new Phrase(List.get(i).getSexe(), font2));
//                    c.setBorder(Rectangle.NO_BORDER);
//                    table.addCell(c);
//                    c = new PdfPCell(new Phrase(List.get(i).getDate_creation(), font2));
//                    c.setBorder(Rectangle.NO_BORDER);
//                    table.addCell(c);
//                }else{
//                    c = new PdfPCell(new Phrase(List.get(i).getNom(), font2));
//                    c.setBorder(Rectangle.NO_BORDER);
//                    c.setBackgroundColor(new BaseColor(242, 242, 242));
//                    table.addCell(c);
//                    c = new PdfPCell(new Phrase(List.get(i).getPrenom(), font2));
//                    c.setBorder(Rectangle.NO_BORDER);
//                    c.setBackgroundColor(new BaseColor(242, 242, 242));
//                    table.addCell(c);
//                    c = new PdfPCell(new Phrase(List.get(i).getEmail(), font2));
//                    c.setBorder(Rectangle.NO_BORDER);
//                    c.setBackgroundColor(new BaseColor(242, 242, 242));
//                    table.addCell(c);
//                    c = new PdfPCell(new Phrase(List.get(i).getAdresse(), font2));
//                    c.setBorder(Rectangle.NO_BORDER);
//                    c.setBackgroundColor(new BaseColor(242, 242, 242));
//                    table.addCell(c);
//                    c = new PdfPCell(new Phrase(List.get(i).getSexe(), font2));
//                    c.setBorder(Rectangle.NO_BORDER);
//                    c.setBackgroundColor(new BaseColor(242, 242, 242));
//                    table.addCell(c);
//                    c = new PdfPCell(new Phrase(List.get(i).getDate_creation(), font2));
//                    c.setBorder(Rectangle.NO_BORDER);
//                    c.setBackgroundColor(new BaseColor(242, 242, 242));
//                    table.addCell(c);
//                }
//
//            }
//            Paragraph parag=new Paragraph("Liste des Freelancers");
//            parag.setAlignment(Element.ALIGN_CENTER);
//            parag.setSpacingAfter(50f);
//            document.add(parag);
//            document.add(table);
//            document.close();
//            Desktop.getDesktop().open(new File(desktopPath+"\\FreelancerList.pdf"));
//
//        } catch (Exception e) {
//
//        }
//    }
//    public static void SocieteListPdf(ArrayList<Societe> List) {
//        try {
//            Document document = new Document();
//            //Simple Paragraph Pdf
//            String desktopPath = System.getProperty("user.home") + "/Desktop";
//            System.out.print(desktopPath.replace("\\", "/"));
//            PdfWriter.getInstance(document, new FileOutputStream(desktopPath+"\\SocieteList.pdf"));
//            document.open();
//            //document.add(new Paragraph("exemple"));
//            //document.close();
//
//            //-------------------------------------------------------
//            //Add Table
//            Font font = new Font();
//            font.setColor(new BaseColor(255, 255, 255));
//            font.setSize(10);
//            PdfPTable table = new PdfPTable(4);
//            table.setWidthPercentage(100);
//            PdfPCell c = new PdfPCell(new Phrase("Nom", font));
//            c.setBackgroundColor(new BaseColor(76, 175, 80));
//                
//            c.setBorder(Rectangle.NO_BORDER);
//            table.addCell(c);
//           
//            c = new PdfPCell(new Phrase("Email", font));
//            c.setBackgroundColor(new BaseColor(76, 175, 80));
//            c.setBorder(Rectangle.NO_BORDER);
//            table.addCell(c);
//            c = new PdfPCell(new Phrase("Adresse", font));
//            c.setBackgroundColor(new BaseColor(76, 175, 80));
//            c.setBorder(Rectangle.NO_BORDER);
//            table.addCell(c);
//            c = new PdfPCell(new Phrase("date Creation", font));
//            c.setBackgroundColor(new BaseColor(76, 175, 80));
//            c.setBorder(Rectangle.NO_BORDER);
//            table.addCell(c);
//            table.setHeaderRows(1);
//            Font font2 = new Font();
//            font2.setColor(new BaseColor(0, 0, 0));
//            font2.setSize(10);
//            for (int i = 0; i < List.size(); i++) {
//                if (i % 2 != 0) {
//                    c = new PdfPCell(new Phrase(List.get(i).getNom(), font2));
//                    c.setBorder(Rectangle.NO_BORDER);
//                    table.addCell(c);
//                    c = new PdfPCell(new Phrase(List.get(i).getEmail(), font2));
//                    c.setBorder(Rectangle.NO_BORDER);
//                    table.addCell(c);
//                    c = new PdfPCell(new Phrase(List.get(i).getAdresse(), font2));
//                    c.setBorder(Rectangle.NO_BORDER);
//                    table.addCell(c);
//                    c = new PdfPCell(new Phrase(List.get(i).getDate_creation(), font2));
//                    c.setBorder(Rectangle.NO_BORDER);
//                    table.addCell(c);
//                }else{
//                    c = new PdfPCell(new Phrase(List.get(i).getNom(), font2));
//                    c.setBorder(Rectangle.NO_BORDER);
//                    c.setBackgroundColor(new BaseColor(242, 242, 242));
//                    table.addCell(c);
//                    c = new PdfPCell(new Phrase(List.get(i).getEmail(), font2));
//                    c.setBorder(Rectangle.NO_BORDER);
//                    c.setBackgroundColor(new BaseColor(242, 242, 242));
//                    table.addCell(c);
//                    c = new PdfPCell(new Phrase(List.get(i).getAdresse(), font2));
//                    c.setBorder(Rectangle.NO_BORDER);
//                    c.setBackgroundColor(new BaseColor(242, 242, 242));
//                    table.addCell(c);
//                    c = new PdfPCell(new Phrase(List.get(i).getDate_creation(), font2));
//                    c.setBorder(Rectangle.NO_BORDER);
//                    c.setBackgroundColor(new BaseColor(242, 242, 242));
//                    table.addCell(c);
//                }
//
//            }
//            Paragraph parag=new Paragraph("Liste des Societes");
//            parag.setAlignment(Element.ALIGN_CENTER);
//            parag.setSpacingAfter(50f);
//            document.add(parag);
//            document.add(table);
//            document.close();
//            Desktop.getDesktop().open(new File(desktopPath+"\\SocieteList.pdf"));
//
//        } catch (Exception e) {
//
//        }
//    }
//}
