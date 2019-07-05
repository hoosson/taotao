package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.service.ContentService;

/**
 * 内容管理
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper  contentMapper;
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;
	
	
	/*@Override
	public TaotaoResult insertContent(TbContent content) {
		//补全pojo内容
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		
		//添加缓存同步逻辑
		try {
			HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return TaotaoResult.ok();
	}*/
	
	@Override
	public TaotaoResult insertContent(TbContent content) {
		
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		
		//添加缓存同步逻辑
		try {
			HttpClientUtil.doGet(REST_CONTENT_SYNC_URL+REST_CONTENT_SYNC_URL+content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok();
	}

	//分页列表显示
	@Override
	public EUDataGridResult getContentList(long page, long pageSize) {
		TbContentExample example = new TbContentExample();
		// 开始分页
		PageHelper.startPage((int) page, (int) pageSize);
		// 获取查询结果
		List<TbContent> rows = contentMapper.selectByExample(example);
		EUDataGridResult dgr = new EUDataGridResult();
		dgr.setRows(rows);
		// 获取分页信息 商品总数信息
		PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(rows);
		dgr.setTotal(pageInfo.getTotal());
		return dgr;
	}

	//删除
	@Override
	public TaotaoResult deleteContent(String ids) {
		try {
			String[] idsArray = ids.split(",");
			List<Long> values = new ArrayList<Long>();
			for(String id : idsArray) {
				values.add(Long.parseLong(id));
			}
			TbContentExample e = new TbContentExample();
			TbContentExample.Criteria c = e.createCriteria();
			c.andIdIn(values);
			contentMapper.deleteByExample(e);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return TaotaoResult.ok(); 
	}

	//修改
	@Override
	public TaotaoResult updateContent(TbContent content) {
		
		try {
			//更新商品
			TbContentExample e = new TbContentExample();
			Criteria c = e.createCriteria();
			c.andIdEqualTo(content.getId());
			
			TbContent tbContent = contentMapper.selectByPrimaryKey(content.getId());
			
			content.setCreated(tbContent.getCreated());
			content.setUpdated(new Date());

			contentMapper.updateByExample(content, e);
			//添加缓存同步逻辑
			try {
				HttpClientUtil.doGet(REST_CONTENT_SYNC_URL+REST_CONTENT_SYNC_URL+content.getCategoryId());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok();
		
	}

}
