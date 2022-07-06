package com.sis.service;


import com.sis.dto.student.StudentUploadDto;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExcelFileGenerator {

    public String generateInvalidStudentsExcelSheet(List<StudentUploadDto> data) throws IOException {
        File file;
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Invalid Users");
            createHeader(sheet);
            createEntries(data, sheet);
            file = new File("temp/" + UUID.randomUUID() + "-Invalid-students.xlsx");
            file.createNewFile();
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                workbook.write(fileOutputStream);
            }
        }
        String encode = Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));
        file.delete();
        return encode;
    }

    private void createHeader(XSSFSheet sheet) {
        XSSFRow row = sheet.createRow(0);
        Cell arabicName = row.createCell(0);
        arabicName.setCellValue("arabic-name");
        Cell nationality = row.createCell(1);
        nationality.setCellValue("nationality");
        Cell nationalID = row.createCell(2);
        nationalID.setCellValue("national-id");
        Cell collegeCode = row.createCell(3);
        collegeCode.setCellValue("college-code");
        Cell departmentCode = row.createCell(4);
        departmentCode.setCellValue("department-code");
        Cell errors = row.createCell(5);
        errors.setCellValue("errors");
    }

    private void createEntries(List<StudentUploadDto> data, XSSFSheet sheet) {
        int rowCount = 1;
        for (StudentUploadDto entry : data) {
            XSSFRow row = sheet.createRow(rowCount);
            Cell arabicName = row.createCell(0);
            arabicName.setCellValue(entry.getNameAr());
            Cell nationality = row.createCell(1);
            nationality.setCellValue(entry.getNationality());
            Cell nationalID = row.createCell(2);
            nationalID.setCellValue(entry.getNationalId());
            Cell collegeCode = row.createCell(3);
            collegeCode.setCellValue(entry.getCollegeCode());
            Cell departmentCode = row.createCell(4);
            departmentCode.setCellValue(entry.getDepartmentCode());
            Cell errors = row.createCell(5);
            errors.setCellValue(entry.getErrors());
            rowCount++;
        }
    }

}
