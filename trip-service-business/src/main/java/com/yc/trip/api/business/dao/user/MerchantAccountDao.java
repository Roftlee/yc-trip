package com.yc.trip.api.business.dao.user;

import java.util.List;

import com.yc.trip.api.business.bo.user.MerchantAccountDomain;

/**
 * 商户账号信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-04-22 20:40
 *
 */
public interface MerchantAccountDao {

    /**
     * 新增商户账号信息
     */
    void addMerchantAccount(MerchantAccountDomain merchantAccountDomain);

    /**
     * 修改商户账号信息
     */
    void updateMerchantAccount(MerchantAccountDomain merchantAccountDomain);
    
    /**
     * 查询商户账号信息
     */
    MerchantAccountDomain getMerchantAccount(MerchantAccountDomain merchantAccountDomain);

    /**
     * 查询商户账号信息列表
     */
    List<MerchantAccountDomain> queryMerchantAccountList(MerchantAccountDomain merchantAccountDomain);

}