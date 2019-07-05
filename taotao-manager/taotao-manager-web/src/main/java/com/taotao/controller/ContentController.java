package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

/**
 * 内容管理Controller
 * <p>Title: ContentController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2015年9月8日上午11:13:52
 * @version 1.0
 */
@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult insertContent(TbContent content) {
		TaotaoResult result = contentService.insertContent(content);
		return result;
	}
	
	//加载列表
	@RequestMapping("/query/list")
	@ResponseBody
	public EUDataGridResult getContentList(Long page, Long rows){
		EUDataGridResult result = contentService.getContentList(page, rows);
		return result;
	}
	
	//删除
	/*@RequestMapping("/content/delete")
	@ResponseBody
	public TaotaoResult deleteContent(String ids){
		return contentService.deleteContent(ids);
	}
	
	//更新
	@RequestMapping("/rest/content/edit")
	@ResponseBody
	public TaotaoResult updateItem(TbContent content){
		TaotaoResult result=contentService.updateContent(content);
		return result;
	}*/
}
