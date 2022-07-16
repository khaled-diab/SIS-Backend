package com.sis.service;


import com.sis.dto.security.UserUploadDto;
import com.sis.util.Constants;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
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

    public String generateInvalidUsersExcelSheet(List<UserUploadDto> data, String userType) throws IOException {
        File file;
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Invalid Users");
            createHeader(sheet, userType);
            createEntries(data, sheet, userType);
            file = new File("temp/" + UUID.randomUUID() + "-Invalid-users.xlsx");
            file.createNewFile();
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                workbook.write(fileOutputStream);
            }
        }
        String encode = Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));
        file.delete();
        return encode;
    }

    private void createHeader(XSSFSheet sheet, String userType) {
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
        if (userType.equals(Constants.TYPE_STUDENT)) {
            Cell universityNumber = row.createCell(5);
            universityNumber.setCellValue("university-number");
        } else {
            Cell degreeID = row.createCell(5);
            degreeID.setCellValue("degree-id");
        }
        Cell errors = row.createCell(6);
        errors.setCellValue("errors");
    }

    private void createEntries(List<UserUploadDto> data, XSSFSheet sheet, String userType) {
        int rowCount = 1;
        for (UserUploadDto entry : data) {
            XSSFRow row = sheet.createRow(rowCount);
            Cell arabicName = row.createCell(0);
            arabicName.setCellValue(entry.getNameAr());
            Cell nationality = row.createCell(1);
            nationality.setCellValue(entry.getNationality());
            Cell nationalID = row.createCell(2, CellType.STRING);
            nationalID.setCellValue(entry.getNationalId());
            Cell collegeCode = row.createCell(3);
            collegeCode.setCellValue(entry.getCollegeCode());
            Cell departmentCode = row.createCell(4);
            departmentCode.setCellValue(entry.getDepartmentCode());
            if (userType.equals(Constants.TYPE_STUDENT)) {
                Cell universityNumber = row.createCell(5);
                universityNumber.setCellValue(entry.getUniversityNumber());
            } else {
                Cell degreeID = row.createCell(5);
                degreeID.setCellValue(entry.getDegreeID());
            }
            Cell errors = row.createCell(6);
            errors.setCellValue(entry.getErrors());
            rowCount++;
        }
    }

}
