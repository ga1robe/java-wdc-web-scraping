package com.github.ga1robe.wdcwebscraping.util;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.jsoup.Jsoup;

import com.github.ga1robe.wdcwebscraping.model.CardContainer;

public class ExcelGenerator {

    private List<CardContainer> listRecords;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelGenerator(List<CardContainer> listRecords) {
        this.listRecords = listRecords;
        workbook = new XSSFWorkbook();
    }

    private void writeHeader() {
        sheet = workbook.createSheet("Records");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "Product Name", style);
        createCell(row, 1, "Price", style);
        createCell(row, 2, "Carts", style);
        createCell(row, 3, "Sprzedano", style);
        createCell(row, 4, "Ocena", style);
        createCell(row, 5, "Serwisy", style);
        createCell(row, 6, "Dodatki", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        }
        else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            try {
                cell.setCellValue((String) Jsoup.parse((String) value).text());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        cell.setCellStyle(style);
    }

    private void write() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (CardContainer record : listRecords) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, record.getTitle(), style);
            createCell(row, columnCount++, record.getPrice() + record.getPriceCurrency(), style);
            createCell(row, columnCount++, record.getCards(), style);
            createCell(row, columnCount++, record.getSold(), style);
            createCell(row, columnCount++, record.getEvaluation(), style);
            createCell(row, columnCount++, record.getServicesContainer(), style);
            if(String.valueOf(record.getSavesContainer()).length() > 0)
                createCell(row, columnCount++, record.getSavesContainer(), style);
            if(String.valueOf(record.getSalesContainer()).length() > 0)
                createCell(row, columnCount++, record.getSalesContainer(), style);
            if(String.valueOf(record.getPlaceHolder()).length() > 0)
                createCell(row, columnCount++, record.getPlaceHolder(), style);
        }
    }

    public void generate(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}