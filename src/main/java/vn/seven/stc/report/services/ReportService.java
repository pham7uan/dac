package vn.seven.stc.report.services;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterConfiguration;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    public GeneralEntity getDeviceReport(SearchReportInfo searchReportInfo) {
        searchReportInfo.setTypeExport(ReportConstants.HTML);
        return export(searchReportInfo);
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

            File file = ResourceUtils.getFile("classpath:report.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            Map<String, Object> map = new HashMap<>();
            map.put("fromActiveDate", searchReportInfo.getActiveStartDate() - 86400000);
            map.put("toActiveDate", searchReportInfo.getActiveEndDate() + 86400000);
            map.put("areaList", searchReportInfo.getAreaIds());
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