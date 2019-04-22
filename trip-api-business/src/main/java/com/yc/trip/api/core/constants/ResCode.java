package com.yc.trip.api.core.constants;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.go.api.core.bean.ResBean;
import org.go.api.core.constants.Res;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 返回码常量类
 *
 * @author zhan.shu
 */
public class ResCode extends Res {

    // ============================================ 静态MAP =================================
    private static final Map<String, ResBean> _MAP = Maps.newHashMap();

    // ============================================ 常量开始 ==================================

    public static final ResBean brandDBParamInvalid= new ResBean("B0401","参数未通过校验");
    public static final ResBean brandDBError = new ResBean("B0402","数据库操作异常");
    public static final ResBean brandDBGetNull = new ResBean("B0403","未查询到品牌");

    public static final ResBean elementDBParamInvalid= new ResBean("B0401","参数未通过校验");
    public static final ResBean elementDBError = new ResBean("B0402","数据库操作异常");
    public static final ResBean elementDBGetNull = new ResBean("B0403","未查询到权限");

    public static final ResBean motorcadeDBParamInvalid= new ResBean("B0401","参数未通过校验");
    public static final ResBean motorcadeDBError = new ResBean("B0402","数据库操作异常");
    public static final ResBean motorcadeDBGetNull = new ResBean("B0403","未查询到车队");

    public static final ResBean noticeInfoDBParamInvalid= new ResBean("B0401","参数未通过校验");
    public static final ResBean noticeInfoDBError = new ResBean("B0402","数据库操作异常");
    public static final ResBean noticeInfoDBGetNull = new ResBean("B0403","未查询到资讯");

    public static final ResBean operateLogDBParamInvalid= new ResBean("B0501","参数未通过校验");
    public static final ResBean operateLogDBError = new ResBean("B0502","数据库操作异常");
    public static final ResBean operateLogDBGetNull = new ResBean("B0503","未查询到操作日志");

    public static final ResBean orderCreditDBParamInvalid= new ResBean("B0601","参数未通过校验");
    public static final ResBean orderCreditDBError = new ResBean("B0602","数据库操作异常");
    public static final ResBean orderCreditDBGetNull = new ResBean("B0603","未查询到订单积分信息");

    public static final ResBean orderCustomerDBParamInvalid= new ResBean("B0701","参数未通过校验");
    public static final ResBean orderCustomerDBError = new ResBean("B0702","数据库操作异常");
    public static final ResBean orderCustomerDBGetNull = new ResBean("B0703","未查询到订单人员信息");

    public static final ResBean orderInfoDBParamInvalid= new ResBean("B0801","参数未通过校验");
    public static final ResBean orderInfoDBError = new ResBean("B0802","数据库操作异常");
    public static final ResBean orderInfoDBGetNull = new ResBean("B0803","未查询到订单信息");

    public static final ResBean productDBParamInvalid= new ResBean("B0901","参数未通过校验");
    public static final ResBean productDBError = new ResBean("B0902","数据库操作异常");
    public static final ResBean productDBGetNull = new ResBean("B0903","未查询到产品信息");

    public static final ResBean productCommentDBParamInvalid= new ResBean("B1001","参数未通过校验");
    public static final ResBean productCommentDBError = new ResBean("B1002","数据库操作异常");
    public static final ResBean productCommentDBGetNull = new ResBean("B1003","未查询到产品评论信息");

    public static final ResBean productCommentImageDBParamInvalid= new ResBean("B1101","参数未通过校验");
    public static final ResBean productCommentImageDBError = new ResBean("B1102","数据库操作异常");
    public static final ResBean productCommentImageDBGetNull = new ResBean("B1103","未查询到产品评论图片信息");

    public static final ResBean productSortDBParamInvalid= new ResBean("B1201","参数未通过校验");
    public static final ResBean productSortDBError = new ResBean("B1202","数据库操作异常");
    public static final ResBean productSortDBGetNull = new ResBean("B1203","未查询到产品分类信息");

    public static final ResBean providerDBParamInvalid= new ResBean("B1301","参数未通过校验");
    public static final ResBean providerDBError = new ResBean("B1302","数据库操作异常");
    public static final ResBean providerDBGetNull = new ResBean("B1303","未查询到供应商信息");

    public static final ResBean providerBrandRelationDBParamInvalid= new ResBean("B1401","参数未通过校验");
    public static final ResBean providerBrandRelationDBError = new ResBean("B1402","数据库操作异常");
    public static final ResBean providerBrandRelationDBGetNull = new ResBean("B1403","未查询到供应商品牌关联信息");

    public static final ResBean purchaseRecordDBParamInvalid= new ResBean("B1501","参数未通过校验");
    public static final ResBean purchaseRecordDBError = new ResBean("B1502","数据库操作异常");
    public static final ResBean purchaseRecordDBGetNull = new ResBean("B1503","未查询到服务购买信息");

    public static final ResBean regionDBParamInvalid= new ResBean("B1601","参数未通过校验");
    public static final ResBean regionDBError = new ResBean("B1602","数据库操作异常");
    public static final ResBean regionDBGetNull = new ResBean("B1603","未查询到地区信息");

    public static final ResBean regionSortDBParamInvalid= new ResBean("B1701","参数未通过校验");
    public static final ResBean regionSortDBError = new ResBean("B1702","数据库操作异常");
    public static final ResBean regionSortDBGetNull = new ResBean("B1703","未查询到地区分类信息");

    public static final ResBean roleDBParamInvalid= new ResBean("B1801","参数未通过校验");
    public static final ResBean roleDBError = new ResBean("B1802","数据库操作异常");
    public static final ResBean roleDBGetNull = new ResBean("B1803","未查询到角色信息");

    public static final ResBean roleElementDBParamInvalid= new ResBean("B1901","参数未通过校验");
    public static final ResBean roleElementDBError = new ResBean("B1902","数据库操作异常");
    public static final ResBean roleElementDBGetNull = new ResBean("B1903","未查询到角色权限信息");

    public static final ResBean salesCreditDBParamInvalid= new ResBean("B2001","参数未通过校验");
    public static final ResBean salesCreditDBError = new ResBean("B2002","数据库操作异常");
    public static final ResBean salesCreditDBGetNull = new ResBean("B2003","未查询到销售人员积分信息");

    public static final ResBean shoppingCarDBParamInvalid= new ResBean("B2101","参数未通过校验");
    public static final ResBean shoppingCarDBError = new ResBean("B2102","数据库操作异常");
    public static final ResBean shoppingCarDBGetNull = new ResBean("B2103","未查询到购物车信息");

    public static final ResBean specialOfferDBParamInvalid= new ResBean("B2201","参数未通过校验");
    public static final ResBean specialOfferDBError = new ResBean("B2202","数据库操作异常");
    public static final ResBean specialOfferDBGetNull = new ResBean("B2203","未查询到优惠活动信息");

    public static final ResBean storeDBParamInvalid= new ResBean("B2301","参数未通过校验");
    public static final ResBean storeDBError = new ResBean("B2302","数据库操作异常");
    public static final ResBean storeDBGetNull = new ResBean("B2303","未查询到门店信息");

    public static final ResBean storeCustomerDBParamInvalid= new ResBean("B2401","参数未通过校验");
    public static final ResBean storeCustomerDBError = new ResBean("B2402","数据库操作异常");
    public static final ResBean storeCustomerDBGetNull = new ResBean("B2403","未查询到门店客户信息");

    public static final ResBean storeSalesDBParamInvalid= new ResBean("B2501","参数未通过校验");
    public static final ResBean storeSalesDBError = new ResBean("B2502","数据库操作异常");
    public static final ResBean storeSalesDBGetNull = new ResBean("B2503","未查询到门店销售信息");

    public static final ResBean trainDBParamInvalid= new ResBean("B2601","参数未通过校验");
    public static final ResBean trainDBError = new ResBean("B2602","数据库操作异常");
    public static final ResBean trainDBGetNull = new ResBean("B2603","未查询到培训信息");

    public static final ResBean tripGuideDBParamInvalid= new ResBean("B2701","参数未通过校验");
    public static final ResBean tripGuideDBError = new ResBean("B2702","数据库操作异常");
    public static final ResBean tripGuideDBGetNull = new ResBean("B2703","未查询到旅游攻略信息");

    public static final ResBean tripInteractDBParamInvalid= new ResBean("B2801","参数未通过校验");
    public static final ResBean tripInteractDBError = new ResBean("B2802","数据库操作异常");
    public static final ResBean tripInteractDBGetNull = new ResBean("B2803","未查询到旅游互动信息");

    public static final ResBean tripShareDBParamInvalid= new ResBean("B2901","参数未通过校验");
    public static final ResBean tripShareDBError = new ResBean("B2902","数据库操作异常");
    public static final ResBean tripShareDBGetNull = new ResBean("B2903","未查询到旅游分享信息");

    public static final ResBean userDBParamInvalid= new ResBean("B3001","参数未通过校验");
    public static final ResBean userDBError = new ResBean("B3002","数据库操作异常");
    public static final ResBean userDBGetNull = new ResBean("B3003","未查询到用户信息");

    public static final ResBean userRoleDBParamInvalid= new ResBean("B3101","参数未通过校验");
    public static final ResBean userRoleDBError = new ResBean("B3102","数据库操作异常");
    public static final ResBean userRoleDBGetNull = new ResBean("B3103","未查询到用户角色信息");

    public static final ResBean visaDBParamInvalid= new ResBean("B3201","参数未通过校验");
    public static final ResBean visaDBError = new ResBean("B3202","数据库操作异常");
    public static final ResBean visaDBGetNull = new ResBean("B3203","未查询到签证信息");

    public static final ResBean visaAttachmentDBParamInvalid= new ResBean("B3301","参数未通过校验");
    public static final ResBean visaAttachmentDBError = new ResBean("B3302","数据库操作异常");
    public static final ResBean visaAttachmentDBGetNull = new ResBean("B3303","未查询到签证附件信息");

    public static final ResBean visaSortDBParamInvalid= new ResBean("B3401","参数未通过校验");
    public static final ResBean visaSortDBError = new ResBean("B3402","数据库操作异常");
    public static final ResBean visaSortDBGetNull = new ResBean("B3403","未查询到签证分类信息");

    public static final ResBean userPasswordDBParamInvalid= new ResBean("B3501","参数未通过校验");
    public static final ResBean userPasswordDBError = new ResBean("B3502","数据库操作异常");
    public static final ResBean userPasswordDBGetNull = new ResBean("B3503","未查询到用户密码信息");

    public static final ResBean wxAppDBParamInvalid= new ResBean("B3601","参数未通过校验");
    public static final ResBean wxAppDBError = new ResBean("B3602","数据库操作异常");
    public static final ResBean wxAppDBGetNull = new ResBean("B3603","未查询到微信小程序信息");

    public static final ResBean couponsDBParamInvalid= new ResBean("B3701","参数未通过校验");
    public static final ResBean couponsDBError = new ResBean("B3702","数据库操作异常");
    public static final ResBean couponsDBGetNull = new ResBean("B3703","未查询到优惠券");

    public static final ResBean userCouponsDBParamInvalid= new ResBean("B3801","参数未通过校验");
    public static final ResBean userCouponsDBError = new ResBean("B3802","数据库操作异常");
    public static final ResBean userCouponsDBGetNull = new ResBean("B3803","未查询到用户优惠券");

    public static final ResBean specialOfferProductDBParamInvalid= new ResBean("B3901","参数未通过校验");
    public static final ResBean specialOfferProductDBError = new ResBean("B3902","数据库操作异常");
    public static final ResBean specialOfferProductDBGetNull = new ResBean("B3903","未查询到优惠活动产品信息");

    public static final ResBean merchantAccountDBParamInvalid= new ResBean("B4001","参数未通过校验");
    public static final ResBean merchantAccountDBError = new ResBean("B4002","数据库操作异常");
    public static final ResBean merchantAccountDBGetNull = new ResBean("B4003","未查询到商户账号信息");

    // ========================== business服务返回码 ===============================



    // ================================ 统计相关返回码 ====================================


    // ============================= web端返回码 ===================================
    public static final ResBean userNameOrPswError = new ResBean("W0207", "用户名或密码错误");

    public static final ResBean injectFieldInvalid = new ResBean("W0301", "该请求参数注入字段不支持");

    // ================================ 缓存模块相关 ====================================
    public static final ResBean cacheQueryFailed = new ResBean("C0103", "缓存查询失败");



    // ================================ 静态块操作 =================================
    static {
        List<Field> fields = Lists.newArrayList();
        fields.addAll(Arrays.asList(Res.class.getDeclaredFields()));
        fields.addAll(Arrays.asList(ResCode.class.getDeclaredFields()));
        for (Field field : fields) {
            if (field.getType().getName().equals("org.go.api.core.bean.ResBean")) {
                try {
                    ResBean resBean = (ResBean) field.get(null);
                    if (resBean != null) {
                        _MAP.put(resBean.getCode(), resBean);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据code获得返回值对象
     *
     * @param code 返回码
     * @return
     */
    public static final ResBean get(String code) {
        return _MAP.get(code);
    }

}
