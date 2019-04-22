package com.yc.trip.web.controller.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageHelper;
import com.yc.oss.dto.OssUploadDto;
import com.yc.oss.facade.OssUploadFacade;
import com.yc.trip.api.business.dto.order.OrderInfo;
import com.yc.trip.api.business.dto.order.OrderManagerInfo;
import com.yc.trip.api.business.dto.product.ProductSort;
import com.yc.trip.api.business.dto.provider.Provider;
import com.yc.trip.api.business.dto.store.Store;
import com.yc.trip.api.business.dto.store.StoreSales;
import com.yc.trip.api.business.enums.user.UserType;
import com.yc.trip.api.business.facade.order.OrderInfoFacade;
import com.yc.trip.api.business.facade.order.OrderManageFacade;
import com.yc.trip.api.business.facade.product.ProductSortFacade;
import com.yc.trip.api.business.facade.store.StoreSalesFacade;
import com.yc.trip.api.business.request.common.KeywordsRequest;
import com.yc.trip.api.business.request.order.OrderQueryRequest;
import com.yc.trip.api.core.constants.ResCode;
import com.yc.trip.api.core.enums.YesNoStatus;
import com.yc.trip.web.constants.SessionKey;
import com.yc.trip.web.controller.base.AbstractBaseController;
import org.apache.commons.io.IOUtils;
import org.go.api.core.dto.ResDto;
import org.go.api.core.util.BeanMapping;
import org.go.framework.base.annotation.MvcValidate;
import org.go.framework.core.exception.PendingException;
import org.go.framework.util.common.StringUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/order")
public class OrderController extends AbstractBaseController {


    @Reference(version = "1.0.0")
    private OrderManageFacade orderFacade;
    @Reference(version = "1.0.0")
    private StoreSalesFacade storeSalesFacade;


    @Reference(version = "1.0.0", timeout = 200000)
    private OssUploadFacade ossUploadFacade;// oss文件上传服务

    @RequestMapping(value = "/getProvider.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<List<Provider>>  getAllProvider()  throws PendingException {
        return new ResDto<>(orderFacade.provideList());
    }

    @RequestMapping(value = "/getStore.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<List<Store>>  getAllStore()  throws PendingException {
        return new ResDto<>(orderFacade.storeList());
    }


    @RequestMapping(value = "/queryOrder.do", method = RequestMethod.POST)
    @MvcValidate
    public ResDto<List<OrderManagerInfo>>  query(@RequestBody OrderQueryRequest request)  throws PendingException {
        UserType userTyp=getSessionUser().getUserType();
        if(userTyp.getName().equals("门店老板")){//如果登录用户为供应商，查询供应商对应的所有订单
            StoreSales  ss=StoreSales.builder().userId(getSessionUser().getUserId()).build();
            request.setStoreId(storeSalesFacade.mustGet(ss).getStoreId()+"");
        }else if(userTyp.getName().equals("代理商")){

        }
        return new ResDto<>(orderFacade.queryOrderyList(OrderManagerInfo.builder().id(request.getId()).customer(request.getCustomer()).storeId(request.getStoreId()).providerId(request.getProviderId()).orderStatus(request.getStatus()).build()));
    }

    private static final String prefix = "trip/manage_files";


    @RequestMapping(value = "/uploadNotice.do", method = RequestMethod.POST)
    public ResDto<String> uploadNotice(HttpServletRequest request, HttpServletResponse response) throws PendingException {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> list = multipartHttpServletRequest.getFiles("file");

        if (list!=null && list.size()!=0) {
            com.yc.oss.constants.ResCode.ossUploadError.throwException("上传文件不能为空");
        }
        List<String> fileList=new ArrayList<String>();
        List<String> realFileName=new ArrayList<String>();
        for(MultipartFile multipartFile:list ) {
            try (InputStream inputStream = multipartFile.getInputStream()) {
                String oringname=multipartFile.getOriginalFilename();
                String file=creatFileName()+oringname.substring(oringname.lastIndexOf("."));
                 new ResDto<>(ossUploadFacade.uploadByteArrays(OssUploadDto.builder()
                        .storagePrefix(prefix)
                        .fileName(file)
                        .fileBytes(IOUtils.toByteArray(inputStream))
                        .build()));
                fileList.add(oringname)   ;
                realFileName.add(file);
            } catch (Exception e) {
                error("文件上传失败", e);
                throw new PendingException(com.yc.oss.constants.ResCode.ossUploadError.getCode(), com.yc.oss.constants.ResCode.ossUploadError.getInfo(), e);
            }
        }
        String orderId=request.getParameter("id");
        orderFacade.addNotice(OrderManagerInfo.builder().id(Long.parseLong(orderId)).fileName(fileList).realFileName(realFileName).build());
        return new ResDto<>("文件上传成功");
    }



    @RequestMapping(value = "/uploadConstract.do", method = RequestMethod.POST)
    public ResDto<String> uploadConstract(HttpServletRequest request, HttpServletResponse response) throws PendingException {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> list = multipartHttpServletRequest.getFiles("file");

        if (list!=null && list.size()!=0) {
            com.yc.oss.constants.ResCode.ossUploadError.throwException("上传文件不能为空");
        }
        List<String> fileList=new ArrayList<String>();
        for(MultipartFile multipartFile:list ) {
            try (InputStream inputStream = multipartFile.getInputStream()) {
                String oringname=multipartFile.getOriginalFilename();
                String file=creatFileName()+oringname.substring(oringname.lastIndexOf("."));
                new ResDto<>(ossUploadFacade.uploadByteArrays(OssUploadDto.builder()
                        .storagePrefix(prefix)
                        .fileName(file)
                        .fileBytes(IOUtils.toByteArray(inputStream))
                        .build()));
                fileList.add(oringname)   ;
            } catch (Exception e) {
                error("文件上传失败", e);
                throw new PendingException(com.yc.oss.constants.ResCode.ossUploadError.getCode(), com.yc.oss.constants.ResCode.ossUploadError.getInfo(), e);
            }
        }
        String orderId=request.getParameter("id");
        orderFacade.addConstract(OrderManagerInfo.builder().id(Long.parseLong(orderId)).picUrl(fileList).build());
        return new ResDto<>("文件上传成功");
    }


    public String  creatFileName(){
        Random random = new Random();
        int length  = 30;
        String numstr = "123456789";
        String chastr_b = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" ;
        String chastr_s = "abcdefghijklmnopqrstuvwxyz";
        String specil = "_";
        String base = numstr+chastr_b+chastr_s+specil;
        StringBuffer sb =  new StringBuffer();

        sb.append(chastr_b.charAt(random.nextInt(chastr_b.length())));
        for(int i =0 ;i <length-2;i++){
            int num = random.nextInt(base.length());
            sb.append(base.charAt(num));
        }
        sb.append(numstr.charAt(random.nextInt(numstr.length())));
        return sb.toString();
    }





}
