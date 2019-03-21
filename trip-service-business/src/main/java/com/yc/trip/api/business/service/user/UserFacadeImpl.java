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
import com.yc.trip.api.business.bo.user.UserDomain;
import com.yc.trip.api.business.dao.user.UserDao;
import com.yc.trip.api.business.dto.user.User;
import com.yc.trip.api.business.facade.user.UserFacade;

/**
 * 用户信息相关接口实现
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:31
 */
@Service(version = "1.0.0")
public class UserFacadeImpl extends AbstractDubboNativeService implements UserFacade {

    @Autowired
    private UserDao userDao;

    /**
     * 新增用户信息
     * 
     * @throws PendingException
     */
    @Override
    public User addUser(User user) throws PendingException {
        try {
            // 转换成domain对象
            UserDomain cond = BeanMapping.map(user, UserDomain.class);
            // 新增时对各字段进行非空校验
            if (!cond.validateInsertFields()) {
                ResCode.userDBParamInvalid.throwException("用户信息新增时参数未通过校验");
            }
            // 调数据库接口进行新增操作
            userDao.addUser(cond);
            // 将新增后回返回（包含自增主键值）
            return BeanMapping.map(cond, User.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userDBError, "用户信息新增失败");
        }
    }

    /**
     * 修改用户信息
     * 
     * @throws PendingException
     */
    @Override
    public User updateUser(User user) throws PendingException {
        try {
            // 转换成domain对象
            UserDomain cond = BeanMapping.map(user, UserDomain.class);
            // 更新或删除操作时，不能所有参数都为空
            if (cond.isAllFiledsNull()) {
                ResCode.userDBParamInvalid.throwException("用户信息更新时参数未通过校验");
            }
            // 调数据库接口进行更新操作
            userDao.updateUser(cond);
            return BeanMapping.map(cond, User.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userDBError, "用户信息更新失败");
        }
    }

    /**
     * 查询用户信息
     * 
     * @throws PendingException
     */
    @Override
    public User getUser(User user) throws PendingException {
        try {
            // 转换成Domain对象
            UserDomain cond = BeanMapping.map(user, UserDomain.class);
            // 调数据库接口查询对象
            UserDomain resultBean = userDao.getUser(cond);
            // 转换返回结果
            return BeanMapping.map(resultBean, User.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userDBError, "用户信息查询失败");
        }
    }
    
    /**
     * 查询用户信息信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    @Override
    public User mustGet(User user) throws PendingException {
        // 查询单位信息
        User result = getUser(user);
        // 若不存在，则抛出异常
        if(result == null){
            ResCode.userDBGetNull.throwException("未查询到用户信息");
        }
        return result;
    }

    /**
     * 查询用户信息列表
     * 
     * @throws PendingException
     */
    @Override
    public List<User> queryUserList(User user) throws PendingException {
        try {
            // 转换成Domain对象
            UserDomain cond = BeanMapping.map(user, UserDomain.class);
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(user.getOrderby())) {
                PageHelper.orderBy(user.getOrderby());
            }
            // 调数据库接口查询列表
            List<UserDomain> resultList = userDao.queryUserList(cond);
            // 转换返回结果
            return BeanMapping.mapList(resultList, User.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userDBError, "用户信息列表查询失败");
        }
    }

    /**
     * 查询用户信息列表 ,分页查询
     * 
     * @throws PendingException
     */
    @Override
    public PageInfo<User> queryUserPage(User user) throws PendingException {
        try {
            // 对请求参数进行校验
            if (user.getPageNo() <= 0 || user.getPageSize() <= 0) {
                ResCode.userDBParamInvalid.throwException("分页参数设置有误");
            }
            // 在上下文中设置分页信息
            PageHelper.startPage(user.getPageNo(), user.getPageSize());
            // 在上下文中设置排序信息
            if (StringUtil.isNotBlank(user.getOrderby())) {
                PageHelper.orderBy(user.getOrderby());
            }
            // 转换成Domain对象
            UserDomain cond = BeanMapping.map(user, UserDomain.class);
            // 调数据库接口查询列表
            List<UserDomain> resultList = userDao.queryUserList(cond);
            // 生成分页对象
            PageInfo<UserDomain> pageInfo = new PageInfo<>(resultList);
            // 对分页对象进行类型转换
            return BeanMapping.mapPage(pageInfo, User.class);
        } catch (Exception ex) {
            // 对异常进行处理
            throw transferException(ex, ResCode.userDBError, "用户信息分页查询失败");
        }
    }

}
