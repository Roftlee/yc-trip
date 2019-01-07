package com.yc.trip.web.controller.oss;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yc.oss.dto.OssUploadDto;
import com.yc.oss.facade.OssUploadFacade;
import com.yc.trip.web.controller.base.AbstractBaseController;
import org.apache.commons.io.IOUtils;
import org.go.api.core.dto.ResDto;
import org.go.framework.core.exception.PendingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * OSS服务
 *
 * @author: AsiQue
 * @date :2018.08.17 14:39
 */
@RestController
@RequestMapping("/oss")
public class OssController extends AbstractBaseController {

    private static final String prefix = "trip/manage_files";

    @Reference(version = "1.0.0", timeout = 200000)
    private OssUploadFacade ossUploadFacade;// oss文件上传服务

    /**
     * OSS图片文件上传（MultipartFile）
     *
     * @return
     * @throws PendingException
     */
    @RequestMapping(value = "/upload.do", method = RequestMethod.POST)
    public ResDto<String> upload(HttpServletRequest request, HttpServletResponse response) throws PendingException {

        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartHttpServletRequest.getFile("file");

        if (multipartFile.isEmpty()) {
            com.yc.oss.constants.ResCode.ossUploadError.throwException("上传文件不能为空");
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {

            return new ResDto<>(ossUploadFacade.uploadByteArrays(OssUploadDto.builder()
                    .storagePrefix(prefix)
                    .fileName(multipartFile.getOriginalFilename())
                    .fileBytes(IOUtils.toByteArray(inputStream))
                    .build()));
        } catch (Exception e) {
            error("文件上传失败", e);
            throw new PendingException(com.yc.oss.constants.ResCode.ossUploadError.getCode(), com.yc.oss.constants.ResCode.ossUploadError.getInfo(), e);
        }
    }
}
