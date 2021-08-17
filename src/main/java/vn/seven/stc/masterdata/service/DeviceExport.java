package vn.seven.stc.masterdata.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.seven.stc.core.Constants;
import vn.seven.stc.core.utils.Common;
import vn.seven.stc.masterdata.models.Device;
import vn.seven.stc.masterdata.repositories.DeviceRepository;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by hiepnd
 * Date: 17/08/2021
 * Time: 9:58 AM
 * Contact me via mail hiepnd@vnpt-technology.vn
 */
@Service
public class DeviceExport {

    private DeviceRepository deviceRepository;

    @Autowired
    public void setDeviceRepository(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public String exportExcel() throws IOException {
        List<Device> results = deviceRepository.findAll();
        String fileName = "export/device/device_export_" + Common.getCurrentDateTime() + ".xlsx";
        ClassLoader classLoader = getClass().getClassLoader();
        String templatePath = Constants.TEMPLATE_DEVICE_EXPORT;;
        SXSSFWorkbook raw;
        try{
            FileInputStream inputStream = new FileInputStream(new File(classLoader.getResource("template").getPath() + templatePath));
            raw =  new SXSSFWorkbook(new XSSFWorkbook(inputStream),100);
        } catch (Exception e){
            InputStream inputStream = classLoader.getResourceAsStream("/template" +templatePath);
            raw = new SXSSFWorkbook(new XSSFWorkbook(inputStream),100);
        }
        genDevice(results,raw,fileName);
        return  fileName;
    }

    private void genDevice(List<Device> items, SXSSFWorkbook raw, String fileName) throws IOException {
        Workbook workbook = raw.getXSSFWorkbook();

        Sheet requestSheet = workbook.getSheetAt(0);

        genDetailsSheet(requestSheet,items);

        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            raw.write(outputStream);
            raw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void genDetailsSheet(Sheet requestSheet,List<Device> items){
        Row row = requestSheet.getRow(0);
        Cell cell = row.getCell(33);
        CellStyle normalStyle = cell.getCellStyle();

        int rowNum = 1;
        int stt = 1;
        for(Device item: items) {
            int columnNum = 0;
            row = requestSheet.createRow(rowNum++);

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(item.getSerial());

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(item.getProductName());

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(item.getFw());

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(item.getMac());

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(item.getImei());

            String state = "";
            if (item.getState() == 1) {
                state = "Xuất xưởng";
            } else if(item.getState() == 2) {
                state = "Dang hoạt động";
            } else if (item.getState() == 3) {
                state = "Không hoạt động";
            }
            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(state);

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(item.getAreaName());

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(Common.convertDateTime(item.getExportDate(),"dd/MM/yyyy"));

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(item.getExportCode());

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(Common.convertDateTime(item.getDeliveryDate(),"dd/MM/yyyy"));

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(Common.convertDateTime(item.getActiveDate(),"dd/MM/yyyy"));

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(Common.convertDateTime(item.getExpiredDate(),"dd/MM/yyyy"));

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(Common.convertDateTime(item.getGuaranteeExportDate(),"dd/MM/yyyy"));

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(Common.convertDateTime(item.getGuaranteeImportDate(),"dd/MM/yyyy"));

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(Common.convertDateTime(item.getRecallDate(),"dd/MM/yyyy"));

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(item.getCustomerCode());

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(item.getPricingCode());

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(item.getPricingCycle());

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(Common.convertDateTime(item.getPricingBeginDate(),"dd/MM/yyyy"));

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(Common.convertDateTime(item.getPricingEndDate(),"dd/MM/yyyy"));

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(Common.convertDateTime(item.getPricingPauseDate(),"dd/MM/yyyy"));

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(Common.convertDateTime(item.getPricingChangeDate(),"dd/MM/yyyy"));

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(item.getSubscriptionStatus());

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(item.getOriginContract());

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(item.getOriginPo());

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(item.getContract());

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(item.getPo());

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(item.getOriginAgency());

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(item.getLocationCode());

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(item.getLocationName());

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(item.getDescription());

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(item.getAccountingCode());

            cell = row.createCell(columnNum++);
            cell.setCellStyle(normalStyle);
            cell.setCellValue(item.getInventoryTransferNumber());

            stt++;
        }

    }

}
