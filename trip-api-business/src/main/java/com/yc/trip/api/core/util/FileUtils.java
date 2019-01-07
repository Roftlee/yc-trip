package com.yc.trip.api.core.util;

import org.go.api.core.constants.Res;
import org.go.framework.core.exception.PendingException;
import org.go.framework.util.common.StringUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件工具类
 */
public class FileUtils {

    /**
     * 创建临时文件
     */
    public static File createTempFile(MultipartFile multipartFile) throws PendingException {
        String filename = multipartFile.getOriginalFilename();
        if(StringUtil.isBlank(filename)) {
            throw Res.SYS_IO_FAIL.toException("上传失败，文件为空");
        }
        String prefix = UUID.randomUUID().toString();
        String suffix = filename.substring(filename.lastIndexOf("."));
        File file = null;
        try {
            file = File.createTempFile(prefix, "." + suffix);
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(file == null) {
            throw Res.SYS_IO_FAIL.toException("上传失败，文件为空");
        }
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
