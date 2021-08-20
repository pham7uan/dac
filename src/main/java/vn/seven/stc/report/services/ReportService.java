package vn.seven.stc.report.services;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterConfiguration;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import vn.seven.stc.core.Constants;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.core.GeneralEntity;
import vn.seven.stc.core.ReportConstants;
import vn.seven.stc.masterdata.models.Area;
import vn.seven.stc.masterdata.models.Contract;
import vn.seven.stc.masterdata.models.Device;
import vn.seven.stc.masterdata.models.Project;
import vn.seven.stc.masterdata.repositories.AreaRepository;
import vn.seven.stc.masterdata.repositories.ContractRepository;
import vn.seven.stc.masterdata.repositories.DeviceRepository;
import vn.seven.stc.masterdata.repositories.ProjectRepository;
import vn.seven.stc.report.DeviceReport;
import vn.seven.stc.report.SearchReportInfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@Service
@Transactional
public class ReportService extends CrudService<Device, Long> {
    @Value("${baseUrl}")
    private String baseUrl;
    @Autowired
    private DeviceRepository deviceRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    DataSource dataSource;

    public ReportService(DeviceRepository repository){
        this.repository = this.deviceRepository = repository;
    }

//    public GeneralEntity getDeviceReport(SearchReportInfo searchReportInfo) {
//        searchReportInfo.setTypeExport(ReportConstants.HTML);
//        return export(searchReportInfo);
//    }

    public DeviceReport getDeviceReport(SearchReportInfo searchReportInfo) {
        //Tạo câu query
        StringBuilder sql = new StringBuilder("SELECT d.area_name, d.serial, d.mac, d.product_name, d.fw, d.active_date, ");
        sql.append("d.contract, d.po, d.export_code, d.pricing_code, d.pricing_begin_date, d.pricing_end_date, ");
        sql.append("d.pricing_pause_date, d.pricing_change_date, d.active FROM master_devices AS d WHERE 1=1 ");

        if (searchReportInfo.getAreaIds() != null && searchReportInfo.getAreaIds().size() > 0){
            sql.append(" AND area_id IN :areaIds ");
        }
        if (searchReportInfo.getContractIds() != null && searchReportInfo.getContractIds().size() > 0){
            sql.append(" AND contract_id IN :contractIds ");
        }
        if (searchReportInfo.getProjectIds() != null && searchReportInfo.getProjectIds().size() > 0){
            sql.append(" AND project_id IN :projectIds ");
        }
        if (searchReportInfo.getHasActive().equals(1)){
            sql.append(" AND pricing_code IS NOT NULL");
        }else if (searchReportInfo.getHasActive().equals(2)){
            sql.append(" AND pricing_code IS NULL");
        }
        if(searchReportInfo.getActiveStartDate() != null) {
            sql.append(" AND active_date > ").append(searchReportInfo.getActiveStartDate());
        }
        if(searchReportInfo.getActiveEndDate() != null) {
            sql.append(" AND active_date < ").append(searchReportInfo.getActiveEndDate());
        }

        Query query = entityManager.createNativeQuery(sql.toString());

        if(searchReportInfo.getAreaIds() != null && searchReportInfo.getAreaIds().size() > 0) {
            query.setParameter("areaIds", searchReportInfo.getAreaIds());
        }
        if(searchReportInfo.getProjectIds() != null && searchReportInfo.getProjectIds().size() > 0) {
            query.setParameter("projectIds", searchReportInfo.getProjectIds());
        }
        if(searchReportInfo.getContractIds() != null && searchReportInfo.getContractIds().size() > 0) {
            query.setParameter("contractIds", searchReportInfo.getContractIds());
        }

        //Lấy dữ liệu câu query
        List<Device> deviceList = new ArrayList<>();
        DeviceReport deviceReport = new DeviceReport();
        Integer totalDeviceImport = 0;
        Integer totalHasDeviceActive = 0;
        Integer totalHasNotDeviceActive = 0;
        Integer totalHasContract = 0;
        Integer active;
        for (Object object : query.getResultList()){
            Device device = new Device();
            device.setAreaName((((Object[]) object)[0]) != null ? (((Object[]) object)[0]).toString() : null);
            device.setSerial((((Object[]) object)[1]) != null ? (((Object[]) object)[1]).toString() : null);
            device.setMac((((Object[]) object)[2]) != null ? (((Object[]) object)[2]).toString() : null);
            device.setProductName((((Object[]) object)[3]) != null ? (((Object[]) object)[3]).toString() : null);
            device.setFw((((Object[]) object)[4]) != null ? (((Object[]) object)[4]).toString() : null);
            device.setActiveDate((((Object[]) object)[5]) != null ? Long.valueOf((((Object[]) object)[5]).toString()) : null);
            device.setContract((((Object[]) object)[6]) != null ? (((Object[]) object)[6]).toString() : null);
            device.setPo((((Object[]) object)[7]) != null ? (((Object[]) object)[7]).toString() : null);
            device.setExportCode((((Object[]) object)[8]) != null ? (((Object[]) object)[8]).toString() : null);
            device.setPricingCode((((Object[]) object)[9]) != null ? (((Object[]) object)[9]).toString() : null);
            device.setPricingBeginDate((((Object[]) object)[10]) != null ? Long.valueOf((((Object[]) object)[10]).toString()) : null);
            device.setPricingEndDate((((Object[]) object)[11]) != null ? Long.valueOf((((Object[]) object)[11]).toString()) : null);
            device.setPricingPauseDate((((Object[]) object)[12]) != null ? Long.valueOf((((Object[]) object)[12]).toString()) : null);
            device.setPricingChangeDate((((Object[]) object)[13]) != null ? Long.valueOf((((Object[]) object)[13]).toString()) : null);
            totalDeviceImport++;
            totalHasContract++;

            active = Integer.parseInt((((Object[]) object)[14]).toString());
            if (active.equals(0)){
                totalHasNotDeviceActive++;
            }else if (active.equals(1)){
                totalHasDeviceActive++;
            }

            deviceList.add(device);
        }
        deviceReport.setDeviceImports(deviceList);
        deviceReport.setTotalDeviceImport(totalDeviceImport);
        deviceReport.setTotalHasDeviceActive(totalHasDeviceActive);
        deviceReport.setTotalHasNotDeviceActive(totalHasNotDeviceActive);
        deviceReport.setTotalHasContract(totalHasContract);
        return deviceReport;
    }

    public GeneralEntity exportActiveDevice(SearchReportInfo searchReportInfo) {
        return export(searchReportInfo);
    }

    private GeneralEntity export(SearchReportInfo searchReportInfo){
        //Get connection
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }

        if (connection == null) return null;
        try {
            String dir = "/attachment/reports";
            String path = System.getProperty("user.dir") + dir;

            File file = ResourceUtils.getFile("classpath:template/report/report.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            Map<String, Object> map = new HashMap<>();
            map.put("FROM_ACTIVE_DATE", searchReportInfo.getActiveStartDate());
            map.put("TO_ACTIVE_DATE", searchReportInfo.getActiveEndDate());
            map.put("AREA_IDS", searchReportInfo.getAreaIds());
            map.put("PROJECT_IDS", searchReportInfo.getProjectIds());
            map.put("CONTRACT_IDS", searchReportInfo.getContractIds());
            map.put("HAS_ACTIVE", searchReportInfo.getHasActive());
            JasperPrint jp = JasperFillManager.fillReport(jasperReport, map, connection);
            String fileName = "report_" + System.currentTimeMillis();
            switch (searchReportInfo.getTypeExport()){
                case ReportConstants.EXCEL:
                    fileName += ".xlsx";
                    path = path+"\\"+fileName;
                    JRXlsxExporter xlsxExporter = new JRXlsxExporter();
                    xlsxExporter.setExporterInput(new SimpleExporterInput(jp));
                    xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path)));
                    SimpleXlsxExporterConfiguration xlsxExporterConfiguration = new SimpleXlsxExporterConfiguration();
                    xlsxExporter.setConfiguration(xlsxExporterConfiguration);
                    xlsxExporter.exportReport();
                    return new GeneralEntity(baseUrl, "attachment/reports/"+fileName);
                case ReportConstants.PDF:
                    fileName += ".pdf";
                    path = path+"\\"+fileName;
                    JRPdfExporter pdfExporter = new JRPdfExporter();
                    pdfExporter.setExporterInput(new SimpleExporterInput(jp));
                    pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path)));
                    SimplePdfExporterConfiguration pdfExporterConfiguration = new SimplePdfExporterConfiguration();
                    pdfExporter.setConfiguration(pdfExporterConfiguration);
                    pdfExporter.exportReport();
                    return new GeneralEntity(baseUrl, "attachment/reports/"+fileName);
                case ReportConstants.DOCX:
                    fileName += ".docx";
                    path = path+"\\"+fileName;
                    JRDocxExporter docxExporter = new JRDocxExporter();
                    docxExporter.setExporterInput(new SimpleExporterInput(jp));
                    docxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path)));
                    SimpleDocxExporterConfiguration docxExporterConfiguration = new SimpleDocxExporterConfiguration();
                    docxExporter.setConfiguration(docxExporterConfiguration);
                    docxExporter.exportReport();
                    return new GeneralEntity(baseUrl, "attachment/reports/"+fileName);
                case ReportConstants.HTML:
                    fileName += ".html";
                    path = path+"\\"+fileName;
                    JasperExportManager.exportReportToHtmlFile(jp, path);
                    return new GeneralEntity("fileName", baseUrl+dir +"/"+fileName);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}