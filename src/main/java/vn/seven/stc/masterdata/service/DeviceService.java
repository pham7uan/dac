package vn.seven.stc.masterdata.service;

import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.masterdata.models.Device;
import vn.seven.stc.masterdata.models.ReportDeviceSummary;
import vn.seven.stc.masterdata.models.ReportDevicepricingCode;
import vn.seven.stc.masterdata.repositories.DeviceRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceService extends CrudService<Device, Long> {
    private DeviceRepository deviceRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public DeviceService(DeviceRepository deviceRepository){
        this.repository = this.deviceRepository = deviceRepository;
    }

    @Override
    protected void afterCreate(Device entity) {
        super.afterCreate(entity);

        calculateAndSaveData();
    }

    @Override
    protected void afterUpdate(Device old, Device updated) {
        super.afterUpdate(old, updated);

        calculateAndSaveData();
    }

    private void calculateAndSaveData() {
        List<Device> devices = deviceRepository.findAll();

        List<String> pricingCodes = devices.stream().map(device -> device.getPricingCode()).collect(Collectors.toList());
        List<String> columnsPricingCode = getColumnReportDeviceSummary();
        for (String pricingCode: pricingCodes){
            if (!checkExist(pricingCode, columnsPricingCode)) {
                AddColumnReportDeviceSummary(pricingCode);
            }
        }

        List<ReportDeviceSummary> reportDeviceSummaries = getReportDeviceSummary();
        truncateReportDeviceSummary();
        for (ReportDeviceSummary reportDeviceSummary: reportDeviceSummaries){
            updateReportDeviceSummary(reportDeviceSummary);
        }

        List<ReportDevicepricingCode> reportDevicepricingCodes = getPricingCodeReportDeviceSummary();
        for (ReportDevicepricingCode reportDevicepricingCode: reportDevicepricingCodes){
            for (String pricingCode : pricingCodes){
                if (reportDevicepricingCode.getPricingCode().equals(pricingCode)){
                    updatePricingCode(pricingCode, reportDevicepricingCode);
                }
            }
        }

    }

    public List<ReportDeviceSummary> getReportDeviceSummary(){
        String sb = " SELECT area_id,area_name, contract,  sum(total_device), sum(total_pricing), sum(total_none_pricing) " +
                    " FROM ( " +
                    " select area_id,area_name, contract, count(1) as total_device, 0 as total_pricing, 0 as total_none_pricing " +
                    " FROM master_devices " +
                    " GROUP BY area_id,area_name, contract " +
                    " UNION " +
                    " select area_id, area_name, contract, 0 as total_device, count(1) as total_pricing, 0 as total_none_pricing " +
                    " FROM master_devices " +
                    " WHERE active = 1 and pricing_code is not null " +
                    " GROUP BY area_id,area_name, contract " +
                    " UNION " +
                    " select area_id, area_name, contract, 0 as  total_device, 0 as total_pricing, count(1) as total_none_pricing " +
                    " FROM master_devices " +
                    " WHERE active = 1 and pricing_code is null " +
                    " GROUP BY area_id,area_name, contract " +
                    " ) as src " +
                    " GROUP BY area_id,area_name, contract";
        Query q = entityManager.createNativeQuery(sb);
        q = q.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(Device.class));
        return q.getResultList();
    }

    public void updateReportDeviceSummary(ReportDeviceSummary reportDeviceSummary){
        String sb = " UPDATE report_device_summary set  area_name = " + reportDeviceSummary.getAreaName() +
                ", contract = " + reportDeviceSummary.getContract() +
                ",total_device =" + reportDeviceSummary.getTotalDevice() +
                ",total_pricing = " + reportDeviceSummary.getTotalPricing() +
                ",total_none_pricing = " + reportDeviceSummary.getTotalNonePricing() +
                " where area_name = " + reportDeviceSummary.getAreaName() + " and contract = " + reportDeviceSummary.getContract();
        Query q = entityManager.createNativeQuery(sb);
        q.executeUpdate();
    }

    public void truncateReportDeviceSummary(){
        String sb = "TRUNCATE TABLE report_device_summary CASCADE";
        Query q = entityManager.createNativeQuery(sb);
        q.executeUpdate();
    }

    public void AddColumnReportDeviceSummary(String column){
        String sb = "ALTER TABLE report_device_summary ADD " + column + " varchar(255)";
        Query q = entityManager.createNativeQuery(sb);
        q.executeUpdate();
    }

    public List<String> getColumnReportDeviceSummary(){
        String sb = "SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA='dac' AND TABLE_NAME='report_device_summary';";
        Query q = entityManager.createNativeQuery(sb);
        return q.getResultList();
    }

    public boolean checkExist(String column, List<String> compare) {
        Boolean flag = false;
        for (String a: compare){
            if (column == a) {
                flag = true;
            }
        }
        return flag;
    }

    public List<ReportDevicepricingCode> getPricingCodeReportDeviceSummary(){
        String sb = " SELECT area_name, contract, pricing_code, count(1) as total_number_packge_type " +
                "FROM master_devices " +
                "WHERE pricing_code is not null " +
                "GROUP BY area_name, contract, pricing_code";
        Query q = entityManager.createNativeQuery(sb);
        q = q.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(Device.class));
        return q.getResultList();
    }

    public void updatePricingCode(String pricingCode, ReportDevicepricingCode reportDevicepricingCode){
        String sb = " UPDATE report_device_summary set " + pricingCode + " = " + reportDevicepricingCode.getTotalNumberPackgeType() + "where area_name = "
                + reportDevicepricingCode.getAreaName() + " and contract = " + reportDevicepricingCode.getContract();
        Query q = entityManager.createNativeQuery(sb);
        q.executeUpdate();
    }
}

