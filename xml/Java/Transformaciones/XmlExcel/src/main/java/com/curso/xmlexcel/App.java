package com.curso.xmlexcel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        try {
            InputStream is = App.class.getResourceAsStream("/recursos/test.xml");
            generarExcel(is);
            System.out.println("Fin");
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void generarExcel(InputStream is) throws ParserConfigurationException, SAXException, IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet spreadSheet = wb.createSheet("spreadSheet");
        spreadSheet.setColumnWidth(0, (256 * 25));
        spreadSheet.setColumnWidth(1, (256 * 25));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(is);
        NodeList nodeList = document.getElementsByTagName("stmt");
        // Creating Rows
        HSSFRow row = spreadSheet.createRow(0);

        HSSFCell cell = row.createCell(1);
        cell.setCellValue("Year 2005");
        cell = row.createCell(2);
        cell.setCellValue("Year 2004");

        HSSFRow row1 = spreadSheet.createRow(1);
        HSSFRow row2 = spreadSheet.createRow(2);
        HSSFRow row3 = spreadSheet.createRow(3);
        HSSFRow row4 = spreadSheet.createRow(4);
        HSSFRow row5 = spreadSheet.createRow(5);
        HSSFRow row6 = spreadSheet.createRow(6);
        HSSFRow row7 = spreadSheet.createRow(7);
        HSSFRow row8 = spreadSheet.createRow(8);
        HSSFRow row9 = spreadSheet.createRow(9);
        HSSFRow row10 = spreadSheet.createRow(10);
        HSSFRow row11 = spreadSheet.createRow(11);

        for (int i = 0; i < nodeList.getLength(); i++) {
            HSSFCellStyle cellStyle = wb.createCellStyle();
            cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
            cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
            cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
            cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);

            switch (i) {
                // Creating column1 (Row label) and column 2 (Year 2005 stmt)
                case 0:

                    cell = row1.createCell(0);
                    cell.setCellValue("Revenue ($)");

                    cell = row1.createCell(1);
                    cell.setCellValue(((Element) (nodeList.item(0)))
                            .getElementsByTagName("revenue").item(0)
                            .getFirstChild().getNodeValue());

                    cell = row2.createCell(0);
                    cell.setCellValue("Cost of Revenue ($)");

                    cell = row2.createCell(1);
                    cell.setCellValue(((Element) (nodeList.item(0)))
                            .getElementsByTagName("costofrevenue").item(0)
                            .getFirstChild().getNodeValue());

                    cell = row3.createCell(0);
                    cell.setCellValue("Research and Development ($)");

                    cell = row3.createCell(1);
                    cell.setCellValue(((Element) (nodeList.item(0)))
                            .getElementsByTagName("researchdevelopment")
                            .item(0).getFirstChild().getNodeValue());

                    cell = row4.createCell(0);
                    cell.setCellValue("Sales and Marketing ($)");

                    cell = row4.createCell(1);
                    cell.setCellValue(((Element) (nodeList.item(0)))
                            .getElementsByTagName("salesmarketing").item(0)
                            .getFirstChild().getNodeValue());

                    cell = row5.createCell(0);
                    cell.setCellValue("General and Administrative ($)");

                    cell = row5.createCell(1);
                    cell.setCellValue(((Element) (nodeList.item(0)))
                            .getElementsByTagName("generaladmin").item(0)
                            .getFirstChild().getNodeValue());

                    cell = row6.createCell(0);
                    cell.setCellValue("Total Operating Expenses ($)");
                    cell.setCellStyle(cellStyle);
                    cell = row6.createCell(1);
                    cell.setCellValue(((Element) (nodeList.item(0)))
                            .getElementsByTagName("totaloperexpenses").item(0)
                            .getFirstChild().getNodeValue());

                    cell.setCellStyle(cellStyle);

                    cell = row7.createCell(0);
                    cell.setCellValue("Operating Income ($)");

                    cell = row7.createCell(1);
                    cell.setCellValue(((Element) (nodeList.item(0)))
                            .getElementsByTagName("operincome").item(0)
                            .getFirstChild().getNodeValue());

                    cell = row8.createCell(0);
                    cell.setCellValue("Investment Income ($)");

                    cell = row8.createCell(1);
                    cell.setCellValue(((Element) (nodeList.item(0)))
                            .getElementsByTagName("invincome").item(0)
                            .getFirstChild().getNodeValue());

                    cell = row9.createCell(0);
                    cell.setCellValue("Income Before Taxes ($)");
                    cell.setCellStyle(cellStyle);

                    cell = row9.createCell(1);
                    cell.setCellValue(((Element) (nodeList.item(0)))
                            .getElementsByTagName("incbeforetaxes").item(0)
                            .getFirstChild().getNodeValue());

                    cell.setCellStyle(cellStyle);

                    cell = row10.createCell(0);
                    cell.setCellValue("Taxes ($)");

                    cell = row10.createCell(1);
                    cell.setCellValue(((Element) (nodeList.item(0)))
                            .getElementsByTagName("taxes").item(0)
                            .getFirstChild().getNodeValue());

                    cell = row11.createCell(0);
                    cell.setCellValue("Net Income ($)");
                    cell.setCellStyle(cellStyle);

                    cell = row11.createCell(1);
                    cell.setCellValue(((Element) (nodeList.item(0)))
                            .getElementsByTagName("netincome").item(0)
                            .getFirstChild().getNodeValue());

                    cell.setCellStyle(cellStyle);

                    break;
                // Creating column 3 (Year 2004 stmt)
                case 1:

                    cell = row1.createCell(2);
                    cell.setCellValue(((Element) (nodeList.item(1)))
                            .getElementsByTagName("revenue").item(0)
                            .getFirstChild().getNodeValue());

                    cell = row2.createCell(2);
                    cell.setCellValue(((Element) (nodeList.item(1)))
                            .getElementsByTagName("costofrevenue").item(0)
                            .getFirstChild().getNodeValue());

                    cell = row3.createCell(2);
                    cell.setCellValue(((Element) (nodeList.item(1)))
                            .getElementsByTagName("researchdevelopment")
                            .item(0).getFirstChild().getNodeValue());

                    cell = row4.createCell(2);
                    cell.setCellValue(((Element) (nodeList.item(1)))
                            .getElementsByTagName("salesmarketing").item(0)
                            .getFirstChild().getNodeValue());

                    cell = row5.createCell(2);
                    cell.setCellValue(((Element) (nodeList.item(1)))
                            .getElementsByTagName("generaladmin").item(0)
                            .getFirstChild().getNodeValue());

                    cell = row6.createCell(2);
                    cell.setCellValue(((Element) (nodeList.item(1)))
                            .getElementsByTagName("totaloperexpenses").item(0)
                            .getFirstChild().getNodeValue());

                    cell.setCellStyle(cellStyle);

                    cell = row7.createCell(2);
                    cell.setCellValue(((Element) (nodeList.item(1)))
                            .getElementsByTagName("operincome").item(0)
                            .getFirstChild().getNodeValue());

                    cell = row8.createCell(2);
                    cell.setCellValue(((Element) (nodeList.item(1)))
                            .getElementsByTagName("invincome").item(0)
                            .getFirstChild().getNodeValue());

                    cell = row9.createCell(2);
                    cell.setCellValue(((Element) (nodeList.item(1)))
                            .getElementsByTagName("incbeforetaxes").item(0)
                            .getFirstChild().getNodeValue());

                    cell.setCellStyle(cellStyle);

                    cell = row10.createCell(2);
                    cell.setCellValue(((Element) (nodeList.item(1)))
                            .getElementsByTagName("taxes").item(0)
                            .getFirstChild().getNodeValue());

                    cell = row11.createCell(2);
                    cell.setCellValue(((Element) (nodeList.item(1)))
                            .getElementsByTagName("netincome").item(0)
                            .getFirstChild().getNodeValue());
                    cell.setCellStyle(cellStyle);
                    break;

                default:
                    break;
            }

        }
        // Outputting to Excel spreadsheet
        FileOutputStream output = new FileOutputStream(new File("IncomeStatements.xls"));
        wb.write(output);
        output.flush();
        output.close();
    }
}
