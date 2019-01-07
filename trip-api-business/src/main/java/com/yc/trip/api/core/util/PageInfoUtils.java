package com.yc.trip.api.core.util;

import org.go.framework.core.BaseCollectionResponseDto.PageInfo;

import java.util.List;


/**
 * 分页Utils
 * @author shuzhan
 *
 */
public class PageInfoUtils {
	
	/**
	 * 
	 * @param list 
	 * @param pageSize
	 * @param pageNum
	 * @param elementCls
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends PageInfo<E>,E> T  createPageInfo(List<E> list, Integer pageNum, Integer pageSize, Class<E> elementCls){
		T t = (T) new PageInfo<E>();
		int total = list.size();
		int startRow = (pageNum - 1) * pageSize;
		int endRow = pageNum * pageSize  > total  ? total  : pageNum * pageSize ;
		t.setList(list.subList(startRow, endRow));
		t.setPageNum(pageNum);
		t.setPageSize(pageSize);
		t.setTotal(total);
		t.setPages((int)(t.getTotal()-1)/t.getPageSize()+1);
		t.setPageNum(pageNum);
		t.setFirstPage(1);
		t.setLastPage(t.getPages());
		t.setHasPreviousPage(t.getPageNum()>1);
		t.setPrePage(t.getPageNum() >1 ? t.getPageNum() -1 : 1);
		t.setHasNextPage(pageNum < t.getPages());
		t.setNextPage(t.isHasNextPage() ? t.getPageNum() +1 : t.getPageNum());
		t.setStartRow(startRow);
		t.setEndRow(endRow);
		t.setIsLastPage(t.getPageNum() == t.getPages());
		t.setIsFirstPage(t.getPageNum() == 1);
		t.setSize(t.getList().size());
		return t;
	}

}
