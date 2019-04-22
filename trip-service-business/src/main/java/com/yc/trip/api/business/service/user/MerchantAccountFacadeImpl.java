package com.yc.trip.api.business.service.user;

import java.util.List;


import org.go.api.core.integration.AbstractDubboNativeService;
import org.go.api.core.util.BeanMapping;
import org.go.framework.core.exception.PendingException;
import org.go.framework.util.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.yc.trip.api.core.constants.ResCode;
import com.yc.trip.api.business.bo.user.MerchantAccountDomain;
import com.yc.trip.api.business.dao.user.MerchantAccountDao;
import com.yc.trip.api.business.dto.user.MerchantAccount;
import com.yc.trip.api.business.facade.user.MerchantAccountFacade;

/**
 * 商户账号信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-04-22 20:40
 */
@Service(version = "1.0.0")
public class MerchantAccountFacadeImpl extends AbstractDubboNativeService implements MerchantAccountFacade {

    @Autowired
    private MerchantAccountDao merchantAccountDao;

    /**
     * 新增商户账号信息
     * 
     * @throws PendingException
     */
    @Override
    public MerchantAccount addMerchantAccount(MerchantAccount merchantAccount) throws PendingException {
        try {
            // 转换成domain对象
            MerchantAccountDomain cond = BeanMapping.map(merchantAccount, MerchantAccountDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.merchantAccountDBParamInvalid.throwException("商户账号信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            merchantAccountDao.addMerchantAccount(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, MerchantAccount.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.merchantAccountDBError, "商户账号信息新增失败");
        }
    }

    /**
     * 修改商户账号信息
     * 
     * @throws PendingException
     */
    @Override
    public MerchantAccount updateMerchantAccount(MerchantAccount merchantAccount) throws PendingException {
        try {
            // 转换成domain对象
            MerchantAccountDomain cond = BeanMapping.map(merchantAccount, MerchantAccountDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.merchantAccountDBParamInvalid.throwException("商户账号信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            merchantAccountDao.updateMerchantAccount(cond);
            return BeanMapping.map(cond, MerchantAccount.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.merchantAccountDBError, "商户账号信息更新失败");
        }
    }

    /**
     * 查询商户账号信息
     * 
     * @throws PendingException
     */
    @Override
    public MerchantAccount getMerchantAccount(MerchantAccount merchantAccount) throws PendingException {
        try {
            // 转换成Domain对象
            MerchantAccountDomain cond = BeanMapping.map(merchantAccount, MerchantAccountDomain.class);
            // 调数据库接口查询对象
            MerchantAccountDomain resultBean = merchantAccountDao.getMerchantAccount(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, MerchantAccount.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.merchantAccountDBError, "商户账号信息查询失败");
        }
    }
    
    /**
     * 查询商户账号信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public MerchantAccount mustGet(MerchantAccount merchantAccount) throws PendingException {
        // 查询单位信息
        MerchantAccount result = getMerchantAccount(merchantAccount);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.merchantAccountDBGetNull.throwException("未查询到商户账号信息");
        }
        return result;
    }

    /**
     * 查询商户账号信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<MerchantAccount> queryMerchantAccountList(MerchantAccount merchantAccount) throws PendingException {
        try {
            // 转换成Domain对象
            MerchantAccountDomain cond = BeanMapping.map(merchantAccount, MerchantAccountDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(merchantAccount.getOrderby())) {
                PageHelper.orderBy(merchantAccount.getOrderby());
            }
            // 调数据库接口查询列表
            List<MerchantAccountDomain> resultList = merchantAccountDao.queryMerchantAccountList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, MerchantAccount.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.merchantAccountDBError, "商户账号信息列表查询失败");
        }
    }

    /**
     * 查询商户账号信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<MerchantAccount> queryMerchantAccountPage(MerchantAccount merchantAccount) throws PendingException {
        try {
            // 对请求参数进行校验
            if (merchantAccount.getPageNo() <= 0 || merchantAccount.getPageSize() <= 0) {
                ResCode.merchantAccountDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(merchantAccount.getPageNo(), merchantAccount.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(merchantAccount.getOrderby())) {
                PageHelper.orderBy(merchantAccount.getOrderby());
            }
            // 转换成Domain对象
            MerchantAccountDomain cond = BeanMapping.map(merchantAccount, MerchantAccountDomain.class);
            // 调数据库接口查询列表
            List<MerchantAccountDomain> resultList = merchantAccountDao.queryMerchantAccountList(cond);
            // 生成分页对象
            PageInfo<MerchantAccountDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, MerchantAccount.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.merchantAccountDBError, "商户账号信息分页查询失败");
        }
    }

}
