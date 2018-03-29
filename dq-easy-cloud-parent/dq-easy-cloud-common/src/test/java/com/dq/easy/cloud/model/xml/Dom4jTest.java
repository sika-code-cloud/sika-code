package com.dq.easy.cloud.model.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Before;
import org.junit.Test;

import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqSourceCodeRelativePath;

public class Dom4jTest {
	public static Document load(String filename) {
		Document document = null;
		try {
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(new File(filename)); // 读取XML文件,获得document对象
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}

	Document document;
	static String path = DqSourceCodeRelativePath.RESOURCES + "\\test.xml";

	@Before
	public void init() {
		document = load(path);
	}

	@Test
	public void testRead() {

		Element root = document.getRootElement();
		Element resultMap = root.element("resultMap");
		List<Element> els = resultMap.elements("result");
		for (Element e : els) {
			System.out.println(e.attributeValue("column"));
		}
	}

	@Test
	public void testAdd() throws Exception {

		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(path));
		Element resultMap = document.getRootElement().element("resultMap");
		List list = resultMap.elements();
		
		Element newEle = DocumentHelper.createElement("result");//设置新增的person的信息
        newEle.addAttribute("column","update_by");
        newEle.addAttribute("jdbcType","VARCHAR");
        newEle.addAttribute("property","updateBy");
        list.add(2, newEle); 
		OutputFormat format = OutputFormat.createPrettyPrint();
		format = new OutputFormat();
        format.setIndentSize(4);
        format.setNewlines(true);
        format.setTrimText(true);
        format.setPadText(true);
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(path)), format);
		writer.write(document);
		writer.close();
	}
}
