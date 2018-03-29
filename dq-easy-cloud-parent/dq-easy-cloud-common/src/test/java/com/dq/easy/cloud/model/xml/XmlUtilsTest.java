package com.dq.easy.cloud.model.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.Format.TextMode;
import org.jdom2.output.XMLOutputter;
import org.junit.Before;
import org.junit.Test;

import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqSourceCodeRelativePath;import com.dq.easy.cloud.module.common.json.utils.DqJSONUtils;

public class XmlUtilsTest {
	SAXBuilder builder = new SAXBuilder();
	File xmlFile = new File(DqSourceCodeRelativePath.RESOURCES+"\\test.xml");
	Document doc = null;
	@Before
	public void init() {
		try {
			doc = builder.build(xmlFile);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReadXml() {
		
        Element root = doc.getRootElement(); //获取根元素
        
        System.out.println("---获取第一个子节点和子节点下面的节点信息------");
        List<Element> rmElements = root.getChildren();
        Element rmElement = root.getChild("resultMap");
        System.out.println("resultMap属性的值列表" + rmElement.getChildren("result").size());
        System.out.println("getContentSize:" + rmElement.getChildren("result").size());//第一次输入张三  第二次输出123123
//        for (Content content : rmElement.getContent()) {
//        	System.out.println("content" + content);
//        }
//        System.out.println("result:" + rmElement.getChild("result").getAttributes());
//        for(Element el: rmElement.getChildren()){
//            System.out.println("attribute:" + el.getAttributes());//第一次输入张三  第二次输出123123
//        }
//        
//        System.out.println("---直接在根节点下就遍历所有的子节点---");
//        for(Element el: root.getChildren()){
//            System.out.println(el.getText());//这里输出4行空格
//            System.out.println(el.getChildText("username"));//输出张三   & 1111111112
//            System.out.println(el.getChildText("password"));//输出123123 &  password2
//        }
	}
	
	/**
     * 新增节点
     * @throws Exception
     */
	@Test
    public void testAddXmlElement() throws Exception {
        Element root = doc.getRootElement(); //获取根元素
        Element newEle = new Element("result");//设置新增的person的信息
        newEle.setAttribute("column","update_by");
        newEle.setAttribute("jdbcType","VARCHAR");
        newEle.setAttribute("property","updateBy");
        Element resultMap = root.getChild("resultMap"); 
        List<Element> results = resultMap.getChildren("result");
        for (int i = 0; i < results.size() ;++i) {
        	if (i == 5) {
        		results.get(i).addContent(newEle);
        	}
        }
        resultMap.getAttributes();
        Format format = Format.getRawFormat();
        format.setEncoding("UTF-8");
        format.setTextMode(TextMode.PRESERVE);
        XMLOutputter out = new XMLOutputter(format);
        out.output(doc, new FileWriter(xmlFile)); //写文件
    }
	
    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        SAXBuilder builder = new SAXBuilder();
        //Document doc = builder.build(new File("src/test.xml"));
        //Document doc = builder.build(new FileInputStream("src/test.xml"));
        //Document doc = builder.build(new FileReader("src/test.xml"));
        //Document doc = builder.build(new URL("http://localhost:8080/jdomTest/test.xml"));
        Document doc = builder.build(DqSourceCodeRelativePath.RESOURCES+"\\test.xml");
        
        
        readXmlFile(doc);
        //addXmlElement(doc);
        //updateXmlElement(doc);
//        deleteXmlElement(doc);
    }

    /**
     * 解析xml文件
     * @throws Exception
     */
    public static void readXmlFile(Document doc) throws Exception {
    
        Element root = doc.getRootElement(); //获取根元素
        
        System.out.println("---获取第一个子节点和子节点下面的节点信息------");
        Element e = root.getChild("person"); //获取第一个子元素
        System.out.println("person的属性id的值："+e.getAttributeValue("id")); //获取person的属性值
        System.out.println("person的属性id的值："+e.getAttributes()); //获取person的属性值
        Element rmElement = root.getChild("resultMap");
        System.out.println("resultMap属性的值列表" + rmElement.getAttributes());
        for(Element el: e.getChildren()){
            System.out.println(el.getText());//第一次输入张三  第二次输出123123
            System.out.println(el.getChildText("username"));//这里这么写会是null
            System.out.println(el.getChildText("password"));//这里这么写会是null
        }
        
        System.out.println("---直接在根节点下就遍历所有的子节点---");
        for(Element el: root.getChildren()){
            System.out.println(el.getText());//这里输出4行空格
            System.out.println(el.getChildText("username"));//输出张三   & 1111111112
            System.out.println(el.getChildText("password"));//输出123123 &  password2
        }
    }
    
    /**
     * 新增节点
     * @throws Exception
     */
    public static void addXmlElement(Document doc) throws Exception {
        Element root = doc.getRootElement(); //获取根元素
        
        Element newEle = new Element("person");//设置新增的person的信息
        newEle.setAttribute("id","88888");
        
        Element chiledEle = new Element("username"); //设置username的信息
        chiledEle.setText("addUser");
        newEle.addContent(chiledEle);
        
        Element chiledEle2 = new Element("password"); //设置password的信息
        chiledEle2.setText("addPassword");
        newEle.addContent(chiledEle2);
        
        root.addContent(newEle);
        
        
        XMLOutputter out = new XMLOutputter();
        out.setFormat(Format.getCompactFormat().setEncoding("GBK"));//设置UTF-8编码,理论上来说应该不会有乱码，但是出现了乱码,故设置为GBK
        out.output(doc, new FileWriter("src/test.xml")); //写文件
    }
    
    /**
     * 更新节点
     * @param doc
     * @throws Exception
     */
    public static void updateXmlElement(Document doc) throws Exception {
        Element root = doc.getRootElement(); //获取根元素
        
        //循环person元素并修改其id属性的值
        for(Element el : root.getChildren("person")){
            el.setAttribute("id","haha");
        }
        //循环设置username和password的文本值和添加属性
        for(Element el: root.getChildren()){
            el.getChild("username").setAttribute("nameVal", "add_val").setText("update_text"); 
            el.getChild("password").setAttribute("passVal", "add_val").setText("update_text"); 
        }
        
        XMLOutputter out = new XMLOutputter();
        out.setFormat(Format.getCompactFormat().setEncoding("GBK"));//设置UTF-8编码,理论上来说应该不会有乱码，但是出现了乱码,故设置为GBK
        out.output(doc, new FileWriter("src/test.xml")); //写文件
    }
    
    /**
     * 删除节点和属性
     * @param doc
     * @throws Exception
     */
    public static void deleteXmlElement(Document doc) throws Exception {
        Element root = doc.getRootElement(); //获取根元素
        
        List<Element> personList = root.getChildren("person");
        
        //循环person元素,删除person的id为1的id属性以及username子节点
        for(Element el : personList){
            if(null!=el.getAttribute("id") && "1".equals(el.getAttribute("id").getValue())){
                el.removeAttribute("id");
                el.removeChild("username");
            }
        }
        
        //循环person元素,删除person的id为2的节点
        for(int i=0; i<personList.size(); i++){
            Element el = personList.get(i);
            if(null!=el.getAttribute("id") &&  "2".equals(el.getAttribute("id").getValue())){
                root.removeContent(el);//从root节点上删除该节点
                //警告：此处删除了节点可能会使personList的长度发生变化而发生越界错误,故不能写成for(int i=0,len=personList.size(); i<len; i++)
            }
        }

        XMLOutputter out = new XMLOutputter();
        out.setFormat(Format.getCompactFormat().setEncoding("GBK"));//设置UTF-8编码,理论上来说应该不会有乱码，但是出现了乱码,故设置为GBK
        out.output(doc, new FileWriter("src/test.xml")); //写文件
    }
}