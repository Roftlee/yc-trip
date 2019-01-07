package com.yc.trip.api.business.cache;

import com.yc.trip.api.core.constants.ResCode;
import org.go.api.core.bean.ResBean;
import org.go.api.core.cache.AbstractBaseLocalCache;
import org.go.framework.core.exception.GoRuntimeException;
import org.go.framework.core.exception.PendingException;

/**
 * 自定义缓存
 *
 * @author 素还真
 */
public abstract class AbstractBaseCustomCache<K, V> extends AbstractBaseLocalCache<K, V> {

    /**
     * 根据缓存Key值获取结果
     * 将父类中的PendingException转换成运行时异常
     *
     * @param key
     * @return
     */
    public V get(K key) {
        try {
            return super.get(key);
        } catch (PendingException ex) {
            throw new GoRuntimeException(ex);
        }
    }

    @Override
    protected ResBean getErrResCode() {
        return ResCode.cacheQueryFailed.info("缓存查询失败[ " + getClass().getSimpleName() + " ]");
    }

}
