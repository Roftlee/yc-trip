package com.yc.trip.api.business.facade.user;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.user.MerchantAccount;

/**
 * 商户账号信息相关接口
 * @author My-Toolkits
 * @since 2019-04-22 20:40
 */
public interface MerchantAccountFacade {

    /**
     * 新增商户账号信息
     * @throws PendingException 
     */
    MerchantAccount addMerchantAccount(MerchantAccount merchantAccount) throws PendingException;

    /**
     * 修改商户账号信息
     * @throws PendingException 
     */
    MerchantAccount updateMerchantAccount(MerchantAccount merchantAccount) throws PendingException;
    
    /**
     * 查询商户账号信息
     * @throws PendingException 
     */
    MerchantAccount getMerchantAccount(MerchantAccount merchantAccount) throws PendingException;
    
    /**
     * 查询商户账号信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    MerchantAccount mustGet(MerchantAccount merchantAccount) throws PendingException;

    /**
     * 查询商户账号信息列表
     * @throws PendingException 
     */
    List<MerchantAccount> queryMerchantAccountList(MerchantAccount merchantAccount) throws PendingException;

    /**
     * 查询商户账号信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<MerchantAccount> queryMerchantAccountPage(MerchantAccount merchantAccount) throws PendingException;

}