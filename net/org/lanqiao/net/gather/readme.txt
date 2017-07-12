关键词：
开源,源码,open source,java,htmlunit,采集

发布网址：
http://code.google.com/p/grabage/

发布版本：
grabage_0.1_nk
  提供最基础的采集功能，有经验的开发人员可以将其嵌入到系统模块中用于采集数据。
  nk版本的内容检查不算完整，只能匹配部分发布时间，对于部分网页将作者，来源，发布时间混合在一起的情况，未能很好的解决。
  该问题会在将来的版本中处理，不会再更新nk版本。源码中未包含图片等资源采集。

开发说明：
近来研究网站建设的时候发现采集工具这么个东西，用来帮助站长抓取数据，使广大站长摆脱繁琐的复制粘贴。看到一些国产CMS自带采集工具，颇为实用。
想起来造轮子单独写一个采集程序有几个原因：
一，在服务器自动运行的采集工具会消耗服务器的资源，一些免费的站用起来难免战战兢兢，准备随时被K。
二，一些号称免费的工具会打Logo在内容里，要去掉需要付费。
三，正则匹配的网页有一些先天的缺陷，也就是内容不成对，当出现嵌套的div时会让抓取配置变得异常艰难
鉴于此，决定用Java写一个开源的工具，方便同学们采集。本程序可客户端运行，采集后的数据暂未处理，代码开源，所以不用担心程序搜集登录信息，有编程经验者可自行修改。
源码供个人站长及劳苦大众研究使用，但禁止将其已任何形式用于商业活动。
详细使用权限请参阅《GNU General Public License v3》http://www.gnu.org/licenses/gpl.html

再一次强调的是：采集有风险，小心被K站。


简易使用手册：
首先在http://code.google.com/p/grabage/downloads/list 中下载指定的源码版本，如grabage_0.1_nk_src.zip
需要使用的组件可在该路径下下载，有经验的开发人员可自行至其主站上下载最新版本，下面是文件需要的组件列表：
xalan-2.7.1.jar   	 	htmlunit   	 Jun 21  	 3.0 MB  	 0  	 
xml-apis-1.3.04.jar 	htmlunit 	Jun 21 	189 KB 	0 	 
xercesImpl-2.9.1.jar 	htmlunit 	Jun 21 	1.2 MB 	0 	 
serializer-2.7.1.jar 	htmlunit 	Jun 21 	271 KB 	0 	 
sac-1.3.jar 			htmlunit 	Jun 21 	15.4 KB 	0 	 
nekohtml-1.9.14.jar 	htmlunit 	Jun 21 	121 KB 	0 	 
htmlunit-core-js-2.7.jar 	htmlunit 	Jun 21 	889 KB 	0 	 
htmlunit-2.7.jar 			htmlunit 	Jun 21 	878 KB 	0 	 
cssparser-0.9.5.jar 		htmlunit 	Jun 21 	247 KB 	0 	 
commons-logging-1.1.1.jar 	htmlunit 	Jun 21 	59.3 KB 	0 	 
commons-lang-2.4.jar 		htmlunit 	Jun 21 	255 KB 	0 	 
commons-io-1.4.jar 			htmlunit 	Jun 21 	106 KB 	0 	 
commons-httpclient-3.1.jar 	htmlunit 	Jun 21 	297 KB 	0 	 
commons-collections-3.2.1.jar 	htmlunit 	Jun 21 	561 KB 	0 	 
commons-codec-1.4.jar 			htmlunit 	Jun 21 	56.8 KB 	0 	 
dom4j-2.0.0-ALPHA-2.jar 	dom4j 	Jun 21 	332 KB 	0 	 
1 - 16 of 16
下载后 用ide（如eclipse)引入源码，将源码中resource的路径指定到编译路径下,编译运行ReadCenter，如控制台输出>>>LINK>>>http://portal.czol.info/news/money 则正常通过，如报异常，请检查工程路径配置。
技巧1，添加新的采集站时可用read包下的两个测试类测试，缩短时间。
技巧2，路径采用xpath语法，在firefox中有一个叫xpath checker的组件，可以很方便的搜索当前页面的xpath。安装后在页面中点击鼠标右键即可看到。