package chapter08;

/**
 * 自定义字符串
 */
public final class MyString {
	
	//成员变量，用来存储字符串内容
	private char[] content;
	
	
	//无参的构造方法
	public MyString(){
		content=new char[0];//创建一个长度为0的char[]数组作为默认值。
	}
	
	//带有初始内容的字符串构造方法
	public MyString(char[] c){
		content = c;//直接把传入的参数作为字符串内容
	}

	//获取内容的字符数组
	public char[] getChars(){
		return content;
	}
	
	
	//将指定字符串连接到此字符串的结尾。
	public MyString concat(MyString str){
		//拼接this.content和str.content
		//创建新的char[],长度为两个char数组长度之和
		char[] newChars = new char[this.content.length+str.content.length];
		for (int i = 0; i < newChars.length; i++) {
			//为新的字符数组赋值
			if(i < this.content.length){
				newChars[i] = this.content[i];
			}else{
				newChars[i] = str.content[i-this.content.length];
			}
		}
		return new MyString(newChars);
	}
	
	//返回一个新的字符串，它是通过用 newChar 替换此字符串中出现的所有 oldChar 得到的。 
	public MyString replace(char oldChar,char newChar){
		//既然要返回新的字符串，那么我们不应该改动原字符串的content,故新创建一个字符数组
		char[] newStr = new char[this.content.length];
		//循环遍历每一个字符内容
		for (int i = 0; i < this.content.length; i++) {
			if(this.content[i] == oldChar){
				//找到要替换的字符即修改为newChar
				newStr[i] = newChar;
			}else{
				//复制原字符到新字符串数组
				newStr[i] = this.content[i];
			}
		}
		//返回一个新的MyString实例
		return new MyString(newStr);
	}
	
	//当且仅当此字符串包含指定的 char 值时，返回 true。 
	public boolean contains(char c){
		for (int i = 0; i < this.content.length; i++) {
			if(this.content[i]==c){
				//如果找到匹配字符，则return true，此时循环终止，方法结束。
				return true;
			}
		}
		//循环完毕，没有找到，返回false
		return false;
	}
	
	//根据给定字符拆分此字符串。 
	public MyString[] split(char c){
		//要知道最终会被分为几个字符串，先要知道字符出现了几次
		int appears = 0;
		for (int i = 0; i < this.content.length; i++) {
			if(this.content[i] == c){
				appears++;
			}
		}
		if(appears>0){
			//如果出现超过一次，创建结果数组，并依次截取字符串赋值给结果数组
			MyString[] result = new MyString[appears+1];//出现n此分隔符，则截取结果为n+1,如aaa.bbb.ccc按.截取结果为aaa,bbb,ccc三个字符串
			int loc=0;//已经截取部分的末位
			int count=0;//已截取次数，截取的次数和截取符出现的次数相同，可以想象为切蛋糕，两刀切成三块
			for (int i = 0; i < this.content.length; i++) {
				if(this.content[i] == c){
					//找到截取符,开始截取
					result[count] = this.subString(loc, i);
					count++;//截取次数+1
					loc = i;//记录已经截取到的位置
					if(count == appears){
						//如果已经截取到最后一个出现的截取符，直接截取到末位
						result[count]=this.subString(loc+1);
						break;
					}
				}
			}
			return result;
		}
		//如果一次都没有出现，则直接返回原字符串，当然也可以返回null,规则我们自己说的算。
		MyString[] myString = new MyString[1];
		myString[0] = this;
		
		return myString;
	}
	
	//返回给定字符在字符串中的位置，默认0位第一位。
	public int indexOf(char c){
		return -1;
	}
	
	//返回当前字符串拥有的字符数。
	public int length(){
		return this.content.length;
	}
	
	//打印当前字符串内容到控制台。
	public void print(){
		for (int i = 0; i < this.content.length; i++) {
			System.out.print(this.content[i]);//逐个输出字符不换行
		}
		System.out.println();//最后输出一个换行符
	}
	
	//返回一个新的字符串，它是此字符串的一个子字符串。该子字符串从指定索引处的字符开始，直到此字符串末尾。
	public MyString subString(int beginIndex){
		
		return this.subString(beginIndex,this.length());
	}
	
	//返回一个新字符串，它是此字符串的一个子字符串。该子字符串从指定的 beginIndex 处开始，直到索引 endIndex - 1 处的字符。因此，该子字符串的长度为 endIndex-beginIndex。 
	public MyString subString(int beginIndex,int endIndex){
		char[] newStr = new char[endIndex-beginIndex];
		int index = 0;
		for (int i = beginIndex; i < endIndex; i++) {
			newStr[index] = this.content[i];
			index++;
		}
		return new MyString(newStr);
	}
	
	public static void main(String[] args) {
////		MyString myString = new MyString(new char[]{'a','b','我'});
////		MyString myString2 = new MyString(new char[]{'c','d'});
//// 		MyString myString3 = myString.concat(myString2);
//// 		myString3.print();
//		
//		MyString myString = new MyString(new char[]{'a','.','c','.','d','.','b','我'});
//		MyString[] ss = myString.split('.');
//		for (int i = 0; i < ss.length; i++) {
//			ss[i].print();
//		}
//		
//		MyString str = new MyString(new char[]{'a','b','c'});
//		str.subString(1).print();
//		str.subString(1,str.length()).print();
	}
}
