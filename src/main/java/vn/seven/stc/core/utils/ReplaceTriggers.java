package vn.seven.stc.core.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by huyvv
 * Date: 05/11/2019
 * Time: 10:59 AM
 * for all issues, contact me:
 **/
public class ReplaceTriggers {

    public static final String[] tables_node = {
            "base_advanced_filters",
            "base_attachments",
            "base_audit_log",
            "base_contacts",
            "base_default_configs",
            "base_filters",
            "base_notes",
            "base_notifications",
            "base_operations",
            "base_organizations",
            "base_personal_configs",
            "base_privileges",
            "base_processing",
            "base_representations",
            "base_roles",
            "base_sequences",
            "base_users",

            "master_area",
            "master_attributes",
            "master_bricks",
            "master_classes",
            "master_currencies",
            "master_families",
            "master_material_categories",
            "master_materials",
            "master_phases",
            "master_procedure_info",
            "master_procedure_logs",
            "master_procedures",
            "master_produce_method",
            "master_product_attributes",
            "master_products",
            "master_seasons",
            "master_segments",
            "master_uom_types",
            "master_uoms",
            "master_values",
            "vts_ssccs",
            "vts_lots"
    };

    public static final String[] tables_center = {
            "base_advanced_filters",
            "base_attachments",
            "base_audit_log",
            "base_contacts",
            "base_default_configs",
            "base_filters",
            "base_notes",
            "base_notifications",
            "base_operations",
            "base_organizations",
            "base_personal_configs",
            "base_privileges",
            "base_processing",
            "base_representations",
            "base_roles",
            "base_sequences",
            "base_users",

            "master_attributes",
            "master_bricks",
            "master_classes",
            "master_currencies",
            "master_families",
            "master_material_categories",
            "master_product_attributes",
            "master_products",
            "master_segments",
            "master_uom_types",
            "master_uoms",
            "master_values"
    };

    public static final String[] dont_have_id = {
            "base_organization_user",
            "base_role_privilege",
            "base_user_role",
            "master_area_user",
            "master_procedure_transfers",
            "master_season_area"
    };

    public static String readFileAsString(String fileName){
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void writeFile(String fileName, String data, String[] tables){
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName, true));
            for(String table : tables){
                String tmp = data
                        .replaceAll("\\{\\{TABLE_NAME\\}\\}", table)
                        .replaceAll("\\{\\{TRIGGER_NAME\\}\\}", table.toUpperCase());

                writer.append('\n');
                writer.append(tmp);
                writer.append('\n');
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String template_node = readFileAsString("C:\\Users\\huyvv\\Desktop\\TEMPLATE.sql");
        writeFile("C:\\Users\\huyvv\\Desktop\\TRIGGERS_NODE.sql", template_node, tables_node);

        String template_center = readFileAsString("C:\\Users\\huyvv\\Desktop\\TEMPLATE.sql");
        writeFile("C:\\Users\\huyvv\\Desktop\\TRIGGERS_CENTER.sql", template_center, tables_center);

        String template_null = readFileAsString("C:\\Users\\huyvv\\Desktop\\TEMPLATE_NULL.sql");
        writeFile("C:\\Users\\huyvv\\Desktop\\TRIGGERS_NULL.sql", template_null, dont_have_id);
    }
}
