package vn.seven.stc.core;

import org.apache.poi.util.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.seven.stc.config.SpringContext;
import vn.seven.stc.core.errors.BadRequestAlertException;
import vn.seven.stc.core.utils.Common;
import vn.seven.stc.logs.models.Attachment;
import vn.seven.stc.logs.services.AttachmentService;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FileEndpoint {
    private AttachmentService getAttachmentService() {
        return SpringContext.getBean(AttachmentService.class);
    }

    @RequestMapping(path = "/download", method = RequestMethod.GET)
    public void download(HttpServletResponse response, @RequestParam("filePath") String filePath) throws IOException {
        validateFileName(filePath);
        InputStream input = new FileInputStream(filePath);
        File file = new File(filePath);

        String name = "attachment;filename=" + file.getName();
        response.addHeader("Content-disposition", name);
        response.setContentType("application/ms-excel");
        IOUtils.copy(input, response.getOutputStream());
        response.flushBuffer();
    }

    @RequestMapping(path = "/template", method = RequestMethod.GET)
    public void getTemplate(HttpServletResponse response, @RequestParam("fileName") String fileName) throws IOException{
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream template = classLoader.getResourceAsStream("/template/" + fileName);
        String name = "attachment;filename=" + fileName;
        response.addHeader("Content-disposition", name);
        response.setContentType("application/ms-excel");
        IOUtils.copy(template, response.getOutputStream());
        response.flushBuffer();
    }

    @RequestMapping(path = "/upload", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map<String, String> uploadFileHandler(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "type", required = false) Integer type) {
        Map<String, String> result = new HashMap<>();

        String fileName = file.getOriginalFilename();
        validateFileName(fileName);
        int pos = fileName.lastIndexOf(".");
        String extension = fileName.substring(pos+1);
        String timeStamp = Common.getCurrentDateTime();
        String baseDir = System.getProperty("user.dir");
        String resource = baseDir + "/attachment/";

        try {

            Common.createDirectory(resource);
            // upload excel ra folder riêng
            resource += "images/";

            Common.createDirectory(resource);

            fileName =   timeStamp + "." + extension;
            File convFile = new File(resource + fileName);

            while(convFile.exists()) {
                timeStamp = Common.getCurrentDateTime();
                fileName =  timeStamp + "." + extension;
                convFile = new File(resource + fileName);
                break;
            }

            file.transferTo(convFile);
            // resize image đối với file lớn hơn 500kb
            boolean checkResize = (float) convFile.length() / (Constants.BYTE * Constants.BYTE) >= 0.5;
            if( checkResize && ("jpeg".equals(extension) || "jpg".equals(extension) || "png".equals(extension))){
                // là ảnh thì resize
                fileName = renameAndSizeImage(resource, fileName);
                // xooá ảnh cũ
                convFile.delete();
            }

        } catch (IOException e){
            e.printStackTrace();
        }

        result.put("fileName", fileName);
        if(id !=null){
            Attachment attachment = new Attachment();
            attachment.setUrl(fileName);
            attachment.setName(file.getOriginalFilename());
            attachment.setParentId(id);
            attachment.setType(type);
            attachment.setModel("xxx");
            attachment.setTenantId(0L);
            getAttachmentService().create(attachment);
        }
        return result;
    }

    @RequestMapping(path = "/upload-transfer", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Attachment uploadFileTransfer(
            @RequestParam("file") MultipartFile file,
            @RequestParam("model") String model,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "type", required = false) Integer type) {
        Map<String, String> result = new HashMap<>();

        String fileName = file.getOriginalFilename();
        validateFileName(fileName);
        int pos = fileName.lastIndexOf(".");
        String extension = fileName.substring(pos+1);
        String timeStamp = Common.getCurrentDateTime();
        String baseDir = System.getProperty("user.dir");
        String resource = baseDir + "/attachment/";

        try {

            Common.createDirectory(resource);
            // upload excel ra folder riêng
            resource += "images/";

            Common.createDirectory(resource);

            fileName = model + "_" + timeStamp + "." + extension;
            File convFile = new File(resource + fileName);

            while(convFile.exists()) {
                timeStamp = Common.getCurrentDateTime();
                fileName = model + "_" + timeStamp + "." + extension;
                convFile = new File(resource + fileName);
                break;
            }

            file.transferTo(convFile);
            // resize image đối với file lớn hơn 500kb
            boolean checkResize = (float) convFile.length() / (Constants.BYTE * Constants.BYTE) >= 0.5;
            if( checkResize && ("jpeg".equals(extension) || "jpg".equals(extension) || "png".equals(extension))){
                // là ảnh thì resize
                fileName = renameAndSizeImage(resource, fileName);
                // xooá ảnh cũ
                convFile.delete();
            }

        } catch (IOException e){
            e.printStackTrace();
        }

        result.put("fileName", fileName);
        if(id !=null){
            Attachment attachment = new Attachment();
            attachment.setUrl(fileName);
            attachment.setName(file.getOriginalFilename());
            attachment.setParentId(id);
            if(attachment.getName().contains(".jpeg") || attachment.getName().contains(".jpg") || attachment.getName().contains(".png")){
                attachment.setType(1);
            } else {
                attachment.setType(0);
            }

            attachment.setModel(model);
            attachment.setTenantId(0L);
            getAttachmentService().create(attachment);
            return attachment;
        }
        return null;
    }

    private String renameAndSizeImage(String resource, String fileName){
        try {
            BufferedImage originalImage = ImageIO.read(new File(resource + fileName));//change path to where file is located
            int type = BufferedImage.TYPE_INT_RGB; // type để resize
            float scaleX = (float) 1366 / originalImage.getWidth();
            float scaleY = (float) 1366 / originalImage.getHeight();
            float scale = Math.min(scaleX, scaleY);
            int w = Math.round(originalImage.getWidth() * scale);
            int h = Math.round(originalImage.getHeight() * scale);

            BufferedImage resizeImageJpg = resizeImage(originalImage, type, w, h);
            fileName = "resize_" + fileName;
            ImageIO.write(resizeImageJpg, "jpg", new File(resource + fileName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return fileName;
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type, int IMG_WIDTH, int IMG_HEIGHT) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }

    @RequestMapping(path = "/deleteFileUploaded", method = RequestMethod.DELETE)
    public Map<String, String> deleteFileUploaded(@RequestParam("fileName") String fileName) {
        Map<String, String> result = new HashMap<>();
        String baseDir = System.getProperty("user.dir");
        String resource = baseDir + "/attachment/images/";
        File file = new File(resource + fileName);

        if(!file.exists()) return null;

        try {
            file.delete();
        } catch (Exception e) {
            return null;
        }

        result.put("fileName", fileName);
        return result;
    }

    @RequestMapping(path = "/sample", method = RequestMethod.GET)
    public void getSample(HttpServletResponse response, @RequestParam("fileName") String fileName) throws IOException{
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream template = classLoader.getResourceAsStream("template/export/" + fileName);
        String name = "attachment;filename=" + fileName;
        response.addHeader("Content-disposition", name);
        response.setContentType("application/ms-excel");
        IOUtils.copy(template, response.getOutputStream());
        response.flushBuffer();
    }

    public void validateFileName(String fileName){
        if(fileName.contains("../") || fileName.contains("..\\")){
            throw new BadRequestAlertException("", "Invalid path", "InvalidPath");
        }

        if(fileName.matches("%2e%2e%2f|%2e%2e/|..%2f|%2e%2e%5c|%2e%2e\\|..%5c|%252e%252e%255c|..%255c|..%255c|..%c1%9c|%00")){
            throw new BadRequestAlertException("", "Invalid path", "InvalidPath");
        }

        int pos = fileName.lastIndexOf('.');
        String extension = fileName.substring(pos+1);
        if(!extension.toLowerCase().matches("zip|rar|jpg|jpeg|png|bmp|xlsx|doc|xls|pdf|docx|txt")) {
            throw new BadRequestAlertException("", "Invalid path", "InvalidPath");
        }
    }
}
