package com.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import com.databases.DSConfigBean;
import com.exception.DataBaseConfigPathError;
/**
 * xml 配置文件读写操作
 * @author e7691
 *
 */
public class XmlOperate {
	/**
	 * 
	 * @param rpath 文件路径
	 * @param nodeName xml节点名
	 * @return 
	 * @throws DataBaseConfigPathError 数据库配置文件异常 不存在 
	 */
	public List<String> readNodeValues(String rpath,String nodeName) throws DataBaseConfigPathError
	 {
		List<String> strings=new ArrayList<String>();
	  FileInputStream fi = null;
	  java.io.File file=new java.io.File(rpath);
	  if(!file.exists()){
		  throw new DataBaseConfigPathError(DataBaseConfigPathError.PATH_NOT_EXISTS,rpath);
	  }
	  try
	  {
	   fi=new FileInputStream(rpath);//读取路径文件
	   SAXBuilder sb=new SAXBuilder();
	   Document doc=sb.build(fi);
	   Element root=doc.getRootElement();//获取根节点
	   Element cs=root.getChild("configaction");//获取 子节点 每个节点 pool 为一个链接池配置
	   List pools=cs.getChildren();
	   Element pool=null;
	   Iterator allPool=pools.iterator();
	  if(allPool.hasNext())
	   {
	    pool=(Element)allPool.next();
	    
	  // System.out.println(pool.getText());
	    if(pool.getChild(nodeName)!=null){	    	
	    	strings.add(pool.getChild(nodeName).getText());
	    }
	   }
	   
	  } catch (FileNotFoundException e) {  
	   e.printStackTrace();
	  } catch (JDOMException e) {
	   e.printStackTrace();
	  } catch (IOException e) {
	   e.printStackTrace();
	  }
	  
	  finally
	  {
	   try {
	    fi.close();
	   } catch (IOException e) {
	    e.printStackTrace();
	   }
	  }
	  
	  return strings;
	 }

	private static List<Element> elements=new ArrayList<Element>();
	/**
	 *  缓存 更新文章 未 更新到文章xml文本 可通过flush() 更新入缓存
	 * @param id
	 * @param nodeName 节点名 不可数字
	 * @param value
	 */
	 public static void addArticleXml(String id,String nodeName,String value) {
	
	  
	  //添加新元素
	  Element element = new Element("article");
	  element.setAttribute("id",id);
	  Element element1 = new Element(nodeName);
	  element1.setText(value);
	  element.addContent(element1);	
	  elements.add(element);
	 }
	 public static void flush(String fileName){
		 try {
				
			 if(elements.isEmpty()){
				 System.out.println("缓存文章 为空");
				 return;
			 }
		  SAXBuilder builder = new SAXBuilder();
		  Document doc = builder.build(fileName);//获得文档对象
		  Element root = doc.getRootElement();//获得根节点
		  int size=elements.size();
		  for(int i=0;i<size;i++){
			  Element element=elements.get(i);
			  System.out.println(element.getName());
			  root.addContent(element);
		  }
		  doc.setRootElement(root);
		  
		  //文件处理
		  XMLOutputter out = new XMLOutputter();
			out.output(doc, new FileOutputStream(fileName));
			elements.clear();
		 } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 //根据ID值删除一个节点
	 public static void deletePerson(int id) throws JDOMException, IOException {
	  SAXBuilder builder = new SAXBuilder();
	  FileInputStream file = new FileInputStream("src/xml/po.xml");
	  Document doc = builder.build(file);//获得文档对象
	  Element root = doc.getRootElement();//获得根节点
	  List<Element> list = root.getChildren();
	  for(Element e:list) {
	   //获取ID值
	   if(Integer.parseInt(e.getAttributeValue("id"))==id) {
	    root.removeContent(e);
	    break;//??
	   }
	  }
	 }
}
