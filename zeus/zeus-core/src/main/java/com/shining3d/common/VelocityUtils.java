package com.shining3d.common;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;


public class VelocityUtils {
	
	protected static Logger logger = LoggerFactory.getLogger(VelocityUtils.class);

	public static final String DEFAULT_CHARSET = "utf-8";


	static {
		Properties properties = new Properties();

		properties.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		properties.setProperty(Velocity.ENCODING_DEFAULT, DEFAULT_CHARSET);
		properties.setProperty(Velocity.INPUT_ENCODING, DEFAULT_CHARSET);
		properties.setProperty(Velocity.OUTPUT_ENCODING, DEFAULT_CHARSET);
		Velocity.init(properties);
		Velocity.setProperty(Velocity.FILE_RESOURCE_LOADER_CACHE, true); //使用缓存
	}
	
	public static String parseString(String content, Map params){
		Template template = Velocity.getTemplate(content);
		StringWriter sw = new StringWriter();
		VelocityContext context = new VelocityContext(params);
		template.merge(context, sw);
		return sw.toString();
	}


	/**
	 * 获得指定模板填充后的内容，使用默认引擎
	 * @param templateFileName 模板文件
	 * @param context 上下文（变量值的容器）
	 * @return 模板和内容匹配后的内容
	 */
	public static String getContent(String templateFileName, VelocityContext context) {
		final StringWriter writer = new StringWriter(); //StringWriter不需要关闭
		toWriter(templateFileName, context, writer);
		return writer.toString();
	}

	/**
	 * 生成内容写入流<br>
	 * 会自动关闭Writer
	 * @param templateFileName 模板文件名
	 * @param context 上下文
	 * @param writer 流
	 */
	public static void toWriter(String templateFileName, VelocityContext context, Writer writer) {
		final Template template = Velocity.getTemplate(templateFileName);
		template.merge(context, writer);
	}

	/**
	 * 融合模板和内容
	 * @param templateContent 模板的内容字符串
	 * @param paramMap 上下文
	 * @return 模板和内容匹配后的内容
	 */
	public static String merge(String templateContent, Map<String,Object> paramMap) {
		final StringWriter writer = new StringWriter();
		try {
			VelocityContext paramContext = new VelocityContext(paramMap);
			Velocity.evaluate(paramContext, writer, UUID.randomUUID().toString(), templateContent);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return writer.toString();
	}

}
