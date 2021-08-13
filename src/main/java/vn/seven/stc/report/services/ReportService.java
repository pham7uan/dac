package vn.seven.stc.report.services;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import vn.seven.stc.core.Constants;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.core.GeneralEntity;
import vn.seven.stc.core.ReportConstants;
import vn.seven.stc.masterdata.models.Device;
import vn.seven.stc.masterdata.repositories.DeviceRepository;
import vn.seven.stc.report.SearchReportInfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ReportService extends CrudService<Device, Long> {
    @Autowired
    private DeviceRepository deviceRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    DataSource dataSource;

    public ReportService(DeviceRepository repository){
        this.repository = this.deviceRepository = repository;
    }

    public List<Device> getDeviceReport(SearchReportInfo searchReportInfo) {
        StringBuilder sql = new StringBuilder("SELECT d.serial,d.active_date,d.product_name,d.contract,d.accounting_code FROM master_devices as d WHERE 1=1 ");
        if (searchReportInfo.getAreaIds() != null && searchReportInfo.getAreaIds().size() > 0){
            sql.append(" AND (area_id IN :areaIds)");
        }

        if (searchReportInfo.getActiveStartDate() != null){
            sql.append(" AND active_date > ").append(searchReportInfo.getActiveStartDate() - 86400000);
        }

        if (searchReportInfo.getActiveEndDate() != null){
            sql.append(" AND active_date < ").append(searchReportInfo.getActiveEndDate() + 86400000);
        }

        Query query = entityManager.createNativeQuery(sql.toString());
        if (searchReportInfo.getAreaIds() != null && searchReportInfo.getAreaIds().size() > 0){
            query.setParameter("areaIds", searchReportInfo.getAreaIds());
        }
        List<Device> deviceList = new ArrayList<>();
        String serial;
        long active_date;
        String product_name;
        String contract;
        String accounting_code;
        for (Object o: query.getResultList()){
            serial = (((Object[]) o)[0]).toString();
            active_date = Long.parseLong((((Object[]) o)[1]).toString());
            product_name = (((Object[]) o)[2]).toString();
            contract = (((Object[]) o)[3]).toString();
            accounting_code = (((Object[]) o)[4]).toString();

            Device device = new Device();
            device.setSerial(serial);
            device.setActiveDate(active_date);
            device.setProductName(product_name);
            device.setContract(contract);
            device.setAccountingCode(accounting_code);

            deviceList.add(device);
        }
        return deviceList;
    }

    public GeneralEntity exportActiveDevice(SearchReportInfo searchReportInfo) {
        //Get connection
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }

        if (connection == null) return null;
        try {
            String path = System.getProperty("user.dir") + "/attachment/report";

            File file = ResourceUtils.getFile("classpath:report.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            Map<String, Object> map = new HashMap<>();
            map.put("fromActiveDate", searchReportInfo.getActiveStartDate() - 86400000);
            map.put("toActiveDate", searchReportInfo.getActiveEndDate() + 86400000);
            JasperPrint jp = JasperFillManager.fillReport(jasperReport, map, connection);


            switch (searchReportInfo.getTypeExport()){
                case ReportConstants.EXCEL:
                    path = path+"\\report_" +System.currentTimeMillis()+".xlsx";
                    JRXlsxExporter xlsxExporter = new JRXlsxExporter();
                    xlsxExporter.setExporterInput(new SimpleExporterInput(jp));
                    xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path)));
                    SimpleXlsxExporterConfiguration xlsxExporterConfiguration = new SimpleXlsxExporterConfiguration();
                    xlsxExporter.setConfiguration(xlsxExporterConfiguration);
                    xlsxExporter.exportReport();
                    return new GeneralEntity("fileName", path);
                case ReportConstants.PDF:
                    path = path+"\\report_" +System.currentTimeMillis()+".pdf";
                    JRPdfExporter pdfExporter = new JRPdfExporter();
                    pdfExporter.setExporterInput(new SimpleExporterInput(jp));
                    pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path)));
                    SimplePdfExporterConfiguration pdfExporterConfiguration = new SimplePdfExporterConfiguration();
                    pdfExporter.setConfiguration(pdfExporterConfiguration);
                    pdfExporter.exportReport();
                    return new GeneralEntity("fileName", path);
                case ReportConstants.DOCX:
                    path = path+"\\report_" +System.currentTimeMillis()+".docx";
                    JRDocxExporter docxExporter = new JRDocxExporter();
                    docxExporter.setExporterInput(new SimpleExporterInput(jp));
                    docxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path)));
                    SimpleDocxExporterConfiguration docxExporterConfiguration = new SimpleDocxExporterConfiguration();
                    docxExporter.setConfiguration(docxExporterConfiguration);
                    docxExporter.exportReport();
                    return new GeneralEntity("fileName", path);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}