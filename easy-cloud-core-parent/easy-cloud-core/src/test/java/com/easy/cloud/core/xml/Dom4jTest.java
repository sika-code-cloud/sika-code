package com.easy.cloud.core.xml;

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

import com.easy.cloud.core.common.generator.code.base.constant.EcCodeGenerateConstant.EcSourceCodeRelativePath;

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
	static String path = EcSourceCodeRelativePath.RESOURCES + "\\mybatis\\easy_user_info.xml";
	@Before
	public void init() {
		document = load(path);
	}

	@Test
	public void testRead() throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(path));
		Element root = document.getRootElement();
		Element select = root.element("select");
		System.out.println(select.getText());
		select.setText(select.getText());
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(path)), format);
		writer.write(document);
		writer.close();
	}

	@Test
	public void testAdd() throws Exception {

		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(path));
		Element resultMap = document.getRootElement().element("resultMap");
		List<Element> list = resultMap.elements();
		int index = 0 ;
		for (int i = 0 ; i < list.size(); ++i) {
			if (list.get(i).getName().equals("result")){
				index = i;
			}
		}
		Element newEle = DocumentHelper.createElement("result");//设置新增的person的信息
        newEle.addAttribute("column","update_by");
        newEle.addAttribute("jdbcType","VARCHAR");
        newEle.addAttribute("property","updateBy");
        list.add(index + 1, newEle);
		OutputFormat format = OutputFormat.createPrettyPrint();
        format.setIndentSize(4);
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(path)), format);
		writer.write(document);
		writer.close();
	}
}
