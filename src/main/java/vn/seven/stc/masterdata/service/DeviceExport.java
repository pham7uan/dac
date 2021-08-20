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
import java.util.*;

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

    public String exportExcel(List<Device> devices, String column) throws IOException {
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
        genDevice(devices,raw,fileName, column);
        return  fileName;
    }

    private void genDevice(List<Device> items, SXSSFWorkbook raw, String fileName, String column) throws IOException {
        Workbook workbook = raw.getXSSFWorkbook();

        Sheet requestSheet = workbook.getSheetAt(0);

        genDetailsSheet(requestSheet,items, column);

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

    private void genDetailsSheet(Sheet requestSheet,List<Device> items, String column){
        Map<String, String> map = new HashMap<>();

        int rowNum = 1;
        int stt = 1;
        String[] columns = column.split(",");

        for (String c: columns) {
            map.put(c, c);
        }
        Row headerRow = requestSheet.createRow(0);
        for (int col = 0; col < columns.length; col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(columns[col]);
        }

        Row row = requestSheet.getRow(1);
        Cell cell = row.getCell(0);
        CellStyle normalStyle = cell.getCellStyle();
        for(Device item: items) {
            int columnNum = 0;
            row = requestSheet.createRow(rowNum++);

            if (map.containsKey("serial")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(item.getSerial());
            }

            if (map.containsKey("productName")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(item.getProductName());
            }

            if (map.containsKey("fw")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(item.getFw());
            }

            if (map.containsKey("mac")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(item.getMac());
            }

            if (map.containsKey("imei")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(item.getImei());
            }
            String state = "";
            if (item.getState() == 1) {
                state = "Xuất xưởng";
            } else if(item.getState() == 2) {
                state = "Dang hoạt động";
            } else if (item.getState() == 3) {
                state = "Không hoạt động";
            }
            if (map.containsKey("state")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(state);
            }
            if (map.containsKey("areaName")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(item.getAreaName());
            }

            if (map.containsKey("exportDate")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(Common.convertDateTime(item.getExportDate(), "dd/MM/yyyy"));
            }
            if (map.containsKey("exportCode")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(item.getExportCode());
            }

            if (map.containsKey("deliveryDate")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(Common.convertDateTime(item.getDeliveryDate(), "dd/MM/yyyy"));
            }

            if (map.containsKey("expiredDate")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(Common.convertDateTime(item.getActiveDate(), "dd/MM/yyyy"));
            }

            if (map.containsKey("expiredDate")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(Common.convertDateTime(item.getExpiredDate(), "dd/MM/yyyy"));
            }

            if (map.containsKey("guaranteeExportDate")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(Common.convertDateTime(item.getGuaranteeExportDate(), "dd/MM/yyyy"));
            }

            if (map.containsKey("guaranteeImportDate")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(Common.convertDateTime(item.getGuaranteeImportDate(), "dd/MM/yyyy"));
            }

            if (map.containsKey("recallDate")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(Common.convertDateTime(item.getRecallDate(), "dd/MM/yyyy"));
            }

            if (map.containsKey("customerCode")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(item.getCustomerCode());
            }

            if (map.containsKey("pricingCode")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(item.getPricingCode());
            }

            if (map.containsKey("pricingCycle")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(item.getPricingCycle());
            }

            if (map.containsKey("pricingBeginDate")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(Common.convertDateTime(item.getPricingBeginDate(), "dd/MM/yyyy"));
            }

            if (map.containsKey("pricingEndDate")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(Common.convertDateTime(item.getPricingEndDate(), "dd/MM/yyyy"));
            }

            if (map.containsKey("pricingPauseDate")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(Common.convertDateTime(item.getPricingPauseDate(), "dd/MM/yyyy"));
            }

            if (map.containsKey("pricingChangeDate")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(Common.convertDateTime(item.getPricingChangeDate(), "dd/MM/yyyy"));
            }

            if (map.containsKey("subscriptionStatus")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(item.getSubscriptionStatus());
            }

            if (map.containsKey("originContract")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(item.getOriginContract());
            }

            if (map.containsKey("originPo")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(item.getOriginPo());
            }
            if (map.containsKey("contract")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(item.getContract());
            }
            if (map.containsKey("po")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(item.getPo());
            }

            if (map.containsKey("originAgency")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(item.getOriginAgency());
            }

            if (map.containsKey("locationCode")){
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(item.getLocationCode());
            }

            if (map.containsKey("locationName")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(item.getLocationName());

                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(item.getDescription());
            }
            if (map.containsKey("accountingCode")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(item.getAccountingCode());
            }

            if (map.containsKey("inventoryTransferNumber")) {
                cell = row.createCell(columnNum++);
                cell.setCellStyle(normalStyle);
                cell.setCellValue(item.getInventoryTransferNumber());
            }

            stt++;
        }

    }

}
