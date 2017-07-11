
package org.lanqiao.xml.dom4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 
 * 示范dom4j的基本用法
 * @author 郑未
 * @version 2014年5月10日
 * @see Dom4jDemo
 * @since
 */
public class Dom4jDemo {
	public static void main(String[] args) throws DocumentException, IOException {
//		read();
		write();
	}

	
	/*
	 <root>
	  	<author name="James" location="UK">James Strachan</author>
	  	<author name="Bob" location="US">Bob McWhirter</author>
	  </root>
	 */
	private static void write() throws IOException {
		 Document document = DocumentHelper.createDocument();
	     Element root = document.addElement( "root" );

	        Element author1 = root.addElement( "author" )
	            .addAttribute( "name", "James" )
	            .addAttribute( "location", "UK" )
	            .addText( "James Strachan" );
	        
	        Element author2 = root.addElement( "author" )
	            .addAttribute( "name", "Bob" )
	            .addAttribute( "location", "US" )
	            .addText( "Bob McWhirter" );

	    


	        // 格式化，createPrettyPrint会按比较好看的格式化输出——有缩进
	        OutputFormat format = OutputFormat.createPrettyPrint();
	        format = OutputFormat.createCompactFormat();
	        XMLWriter writer = new XMLWriter(System.out, format );
	        writer.write( document );

	}

	private static void read() throws DocumentException {
		SAXReader reader = new SAXReader();
		//根据相对路径获得文件的输入流
		InputStream in=Dom4jDemo.class.getResourceAsStream("engineers.xml");
		Document document = reader.read(in);//文档对象Document
		
		 // iterate through child elements of root
        /*
		Element root = document.getRootElement();//Element节点，此方法获得根节点
		System.out.println(root.getName());//标签名，节点名
         for ( Iterator<Element> i = root.elementIterator(); i.hasNext(); ) {
            Element element = i.next();
            Node n1=element.node(1);//Dom4j 将元素的各个部分都抽象成了Node
            Node n3=element.node(3);
            Node n5=element.node(5);
            //获取节点的文本值
            System.out.println("engineer[no="+n1.getText()+",name="+n3.getText()+",salary="+n5.getText()+"]");
           
        }*/
        
        List<Node> nos = document.selectNodes("//engineers/engineer/no");//选择节点
        for (Node node : nos) {
			System.out.println(node.getText());
		}
	}
}
