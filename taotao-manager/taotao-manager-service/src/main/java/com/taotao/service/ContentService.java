package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {

	/**
	 * 插入操作
	 * 
	 * @param content
	 * @return
	 */
	TaotaoResult insertContent(TbContent content);

	/**
	 * 列表
	 * @param page
	 * @param rows
	 * @return
	 */
	EUDataGridResult getContentList(long page, long pageSize);

	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	TaotaoResult deleteContent(String ids);

	/**
	 * 更新操作
	 * 
	 * @param content
	 * @return
	 */
	TaotaoResult updateContent(TbContent content);
	
}
