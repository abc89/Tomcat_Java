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
 * xml �����ļ���д����
 * 
 * @author e7691
 * 
 */
public class XmlOperate {
	/**
	 * 
	 * @param rpath
	 *            �ļ�·��
	 * @param nodeName
	 *            xml�ڵ���
	 * @return
	 * @throws DataBaseConfigPathError
	 *             ��ݿ������ļ��쳣 ������
	 */
	public List<String> readNodeValues(String rpath, String nodeName)
			throws DataBaseConfigPathError {
		List<String> strings = new ArrayList<String>();
		FileInputStream fi = null;
		java.io.File file = new java.io.File(rpath);
		if (!file.exists()) {
			throw new DataBaseConfigPathError(
					DataBaseConfigPathError.PATH_NOT_EXISTS, rpath);
		}
		try {
			fi = new FileInputStream(rpath);// ��ȡ·���ļ�
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(fi);
			Element root = doc.getRootElement();// ��ȡ��ڵ�
			Element cs = root.getChild("configaction");// ��ȡ �ӽڵ� ÿ���ڵ� pool
														// Ϊһ�����ӳ�����
			List pools = cs.getChildren();
			Element pool = null;
			Iterator allPool = pools.iterator();
			if (allPool.hasNext()) {
				pool = (Element) allPool.next();

				// System.out.println(pool.getText());
				if (pool.getChild(nodeName) != null) {
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

		finally {
			try {
				fi.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return strings;
	}

	private static List<Element> elements = new ArrayList<Element>();

	/**
	 * ���� �������� δ ���µ�����xml�ı� ��ͨ��flush() �����뻺��
	 * 
	 * @param id
	 * @param nodeName
	 *            �ڵ��� ��������
	 * @param value
	 */
	public static void addArticleXml(String id, String nodeName, String value) {

		// �����Ԫ��
		Element element = new Element("article");
		element.setAttribute("id", id);
		Element element1 = new Element(nodeName);
		element1.setText(value);
		element.addContent(element1);
		elements.add(element);
	}

	public static void flush(String fileName) {
		try {

			if (elements.isEmpty()) {
				System.out.println("�������� Ϊ��");
				return;
			}
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(fileName);// ����ĵ�����
			Element root = doc.getRootElement();// ��ø�ڵ�
			int size = elements.size();
			for (int i = 0; i < size; i++) {
				Element element = elements.get(i);
				System.out.println(element.getName());
				root.addContent(element);
			}
			doc.setRootElement(root);

			// �ļ�����
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

	// ���IDֵɾ��һ���ڵ�
	public static void deletePerson(int id) throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		FileInputStream file = new FileInputStream("src/xml/po.xml");
		Document doc = builder.build(file);// ����ĵ�����
		Element root = doc.getRootElement();// ��ø�ڵ�
		List<Element> list = root.getChildren();
		for (Element e : list) {
			// ��ȡIDֵ
			if (Integer.parseInt(e.getAttributeValue("id")) == id) {
				root.removeContent(e);
				break;// ??
			}
		}
	}
}
