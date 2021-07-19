package com.shool.ctp.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
/**
 * 自定义Jackson中的核心解析类
 * @author Administrator
 *
 */
public class CustomMapper extends ObjectMapper{
	/**
	 * 
	 */
	private static final long serialVersionUID = -431687360027624006L;

	public CustomMapper() {
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        
        // 设置 SerializationFeature.FAIL_ON_EMPTY_BEANS 为 false
        // 如果在序列化对象的空关联对象时，空的关联对象，将不会参与到对象的序列化
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

}
