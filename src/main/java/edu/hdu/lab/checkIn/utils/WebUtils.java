/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.checkIn.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 *
 * @author justin
 */
public class WebUtils {
    
    private static Logger logger = Logger.getLogger(WebUtils.class);
    private static Map<String, Pattern> patternCache = new HashMap<String, Pattern>(1);
    
    /**
     * 将操作返回值以JSON格式形式表示
     * @param resultCode 操作返回值
     * @return 包含返回值的JSON字符串
     */
    public static String generateResult(int resultCode) {
        return
            generateResult(Constants.RESULT_CODE_STRING, resultCode);
    }
    
    /**
     * 生成单个名-值对形式的JSON数据
     * @param name 名称
     * @param value 值
     * @return 单个名-值对形式的JSON数据
     */
    public static String generateResult(String name, Object value) {
        Map<String,Object> resultMap = new HashMap<String, Object>(1);
        resultMap.put(name, value);
        
        return JsonUtils.createGson().toJson(resultMap);
    }
    
    /**
     * 从一个HttpServletRequest的实例中提取出图片和音频文件
     * @param request 一个HttpServletRequest的实例
     * @param imgsListSize 图片数量最多的个数
     * @param filesLocation 文件存储路径
     * @param iOSVoiceFileFormat iOS平台的语音文件的格式
     * @param androidVoiceFileFormat Android平台的语音文件的格式
     * @return 一个包含了图片List和音频文件名的Map
     * @throws IOException 文件存储异常
     */
    public static Map<String, Object> extractImagesAndVoiceFile(HttpServletRequest request, 
                                                                int imgsListSize,
                                                                String filesLocation,
                                                                String iOSVoiceFileFormat,
                                                                String androidVoiceFileFormat) throws IOException {
        List<String> imgsList = new ArrayList<String>(imgsListSize);
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(  
                request.getSession().getServletContext());
        File dirFile = new File(filesLocation);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (!(dirFile.exists()) && !(dirFile.isDirectory())){
            boolean isCreationSuccess = dirFile.mkdirs();
            if (isCreationSuccess) {
                logger.info("Creation of dir succeeded!");
            } else {
                logger.error("Creation of dir failed!");
                return null;
            }
        }

        // 检查form是否有enctype="multipart/form-data"  
        if (multipartResolver.isMultipart(request)) {  
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;  

            Iterator<String> iter = multiRequest.getFileNames();  
            while (iter.hasNext()) {  

                // 由CommonsMultipartFile继承而来,拥有上面的方法.  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if (file != null) {
                    String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
                    String fileName = UUID.randomUUID().toString();
                    String path = filesLocation + "/" + fileName + "." + suffix;  
                    logger.debug("file: " + path);

                    File localFile = new File(path);  
                    file.transferTo(localFile);

                    if (suffix.equalsIgnoreCase(androidVoiceFileFormat)
                        || suffix.equalsIgnoreCase(iOSVoiceFileFormat)) {
                        //音频文件
                        resultMap.put(Constants.MAP_KEY_VOICE_FILE, fileName + "." + suffix);
                    }
                    else {
                        //图片文件
                        imgsList.add(fileName + "." + suffix);
                    }
                }  
            }
            
            resultMap.put(Constants.MAP_KEY_IMAGES_LIST, imgsList);
        }
        
        return resultMap;
    }
    
    /**
     * 判断对象o是否为null，若为null，则返回replacemenmt，否则仍返回本身
     * @param o 需要判断的对象
     * @param replacement 可替换对象
     * @return 按照判断结果，若对象o为null，则返回replacemenmt，否则仍返回本身
     */
    public static Object ifNull(Object o, Object replacement) {
        return
                o == null ? replacement : o;
    }
    
    /**
     * 判断字符串s是否为空（NULL、只包含空格、空字符串），如果为空，则返回replaceStr
     * @param s 需要判断的字符串
     * @param replaceStr 可替换的字符串
     * @return 按照判断结果，若字符串s为空，则返回replaceStr，否则仍返回本身
     */
    public static String ifEmpty(String s, String replaceStr) {
        return
                s == null || s.trim().isEmpty() ? replaceStr : s;
    }
    
    /**
     * 查看文件夹是否存在。如果不存在，就创建它。
     * @param dirFile 文件夹
     * @return 若创建不成功，返回false，否则true
     */
    public static boolean checkDirAvailabilityAndCreate(File dirFile) {
        boolean isCreationSuccess = true;
        
        if (!(dirFile.exists()) && !(dirFile.isDirectory())){
            isCreationSuccess = dirFile.mkdirs();
            if (isCreationSuccess) {
                logger.info("Creation of dir succeeded!");
            } else {
                logger.error("Creation of dir failed!");
            }
        }
        
        return isCreationSuccess;
    }
    
    /**
     * 保存文件至指定目录，文件名使用UUID
     * @param file 文件
     * @param location 目录
     * @param fileType 文件类型码，用于确定默认的文件后缀名
     * @return 存储完以后的文件名
     */
    public static String saveFile(CommonsMultipartFile file, String location, int fileType) {
        String fileName = "";
        String suffix = "";
        
        String fallbackExtension = "";
        
        if (fileType == Constants.FILE_TYPE_AUDIO)
            fallbackExtension = Constants.UPLOADED_VOICE_FILE_DEFAULT_POSTFIX;
        else if (fileType == Constants.FILE_TYPE_IMAGE)
            fallbackExtension = Constants.UPLOADED_IMG_FILE_DEFAULT_POSTFIX;
        
        if (file == null || StringUtils.isEmpty(location))
            return fileName;
        
        try {
            logger.info("Received File: " + file.getOriginalFilename());
            suffix = FilenameUtils.getExtension(file.getOriginalFilename());
            suffix = StringUtils.isEmpty(suffix) ? fallbackExtension : suffix;
            fileName = UUID.randomUUID().toString();
            String path = location + "/" + fileName + "." + suffix;  
            logger.debug("file: " + path);

            File localFile = new File(path);  
            file.transferTo(localFile);
        } catch (Exception e) {
            e.printStackTrace();
            fileName = "";
            logger.error("File save failed: " + file.getOriginalFilename() + " TO " + location);
        }
        
        return fileName + "." + suffix;
    }
    
    /**
     * 生成JS的回调函数
     * @param callbackFunction 回调函数名
     * @param params 回调函数的参数 
     * @return JS的回调函数字符串
     */
    public static String generateCallbackResult(String callbackFunction, String params) {
        StringBuffer resultScriptBuffer = new StringBuffer("<script>");
        
        resultScriptBuffer.append(callbackFunction);
        resultScriptBuffer.append("(");
        resultScriptBuffer.append(params);
        resultScriptBuffer.append(")");
        resultScriptBuffer.append("</script>");
        
        return resultScriptBuffer.toString();
    }
    
    /**
     * 生成结果，根据参数返回回调函数（callbackFunction不为null）或是JSON格式的结果码（callbackFunction为null）
     * @param callbackFunction 回调函数
     * @param resultCode 结果码
     * @return 字符串形式的输出结果
     */
    public static String produceResult(String callbackFunction, Integer resultCode) {
        String result = "";
        
        if (callbackFunction == null) 
            result = generateResult(resultCode);
        else
            result = generateCallbackResult(callbackFunction, resultCode.toString());
        
        return result;
    }
    
    /**
     * 检查文件是否为空
     * @param file 要检查的文件对象
     * @return 若为空，返回true，否则false
     */
    public static boolean fileIsEmpty(CommonsMultipartFile file) {
        return
                file == null || file.isEmpty();
    }
    
    /**
     * 补全文件名。如果原文件名（ori）没有后缀名，就将postfix作为文件的后缀名
     * @param ori 原文件名
     * @param postfix 备选的后缀名
     * @return 处理完的后缀名
     */
    public static String populateFilename(String ori, String postfix) {
        String resultFilename = null;
        
        if (!StringUtils.isEmpty(ori))
            resultFilename = StringUtils.isEmpty(FilenameUtils.getExtension(ori)) ? ori + postfix : ori;
        
        return resultFilename;
    }
    
    /**
     * 多媒体文件转码（根据后缀名来决定如何转码）
     * @param oriFilename 源文件名
     * @param targetFilename 目标文件名
     * @param fileLocation 文件存储路径
     * @return 0 表示转码成功
     */
    public static int MultimediaConverter(String oriFilename, String targetFilename, String fileLocation) {
        String converterProg = "ffmpeg";
        int resultCode = 1;
        
        oriFilename = fileLocation + "/" + oriFilename;
        targetFilename = fileLocation + "/" + targetFilename;
        
        ProcessBuilder pb = new ProcessBuilder(converterProg, "-i", oriFilename, targetFilename);
        Process p = null;
        
        try {
            p = pb.start();
            resultCode = p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return resultCode;
    }
    
    /**
     * 编译正则表达式，返回编译后的Pattern对象，并将此Pattern对象放入缓存中，以便下次复用。
     * @param rawPattern 字符串形式的正则表达式
     * @return 编译后的Pattern对象
     */
    public synchronized static Pattern compileRegExpPattern(String rawPattern) {
        if (patternCache.containsKey(rawPattern)) {
            logger.debug("Found cached pattern for " + rawPattern);
            return patternCache.get(rawPattern);
        }
        
        Pattern p = Pattern.compile(rawPattern);
        // Put compiled pattern to cache map for reuse
        patternCache.put(rawPattern, p);
        
        return p;
    }
    
    /**
     * 计算数据偏移
     * @param page 页码
     * @param size 数据量
     * @return 计算出的数据偏移
     */
    public static int computeDataOffset(int page, int size) {
        return size * (page -1);
    }
}
