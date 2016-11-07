package chapter08;

/**
 * �Զ����ַ���
 */
public final class MyString {
	
	//��Ա�����������洢�ַ�������
	private char[] content;
	
	
	//�޲εĹ��췽��
	public MyString(){
		content=new char[0];//����һ������Ϊ0��char[]������ΪĬ��ֵ��
	}
	
	//���г�ʼ���ݵ��ַ������췽��
	public MyString(char[] c){
		content = c;//ֱ�ӰѴ���Ĳ�����Ϊ�ַ�������
	}

	//��ȡ���ݵ��ַ�����
	public char[] getChars(){
		return content;
	}
	
	
	//��ָ���ַ������ӵ����ַ����Ľ�β��
	public MyString concat(MyString str){
		//ƴ��this.content��str.content
		//�����µ�char[],����Ϊ����char���鳤��֮��
		char[] newChars = new char[this.content.length+str.content.length];
		for (int i = 0; i < newChars.length; i++) {
			//Ϊ�µ��ַ����鸳ֵ
			if(i < this.content.length){
				newChars[i] = this.content[i];
			}else{
				newChars[i] = str.content[i-this.content.length];
			}
		}
		return new MyString(newChars);
	}
	
	//����һ���µ��ַ���������ͨ���� newChar �滻���ַ����г��ֵ����� oldChar �õ��ġ� 
	public MyString replace(char oldChar,char newChar){
		//��ȻҪ�����µ��ַ�������ô���ǲ�Ӧ�øĶ�ԭ�ַ�����content,���´���һ���ַ�����
		char[] newStr = new char[this.content.length];
		//ѭ������ÿһ���ַ�����
		for (int i = 0; i < this.content.length; i++) {
			if(this.content[i] == oldChar){
				//�ҵ�Ҫ�滻���ַ����޸�ΪnewChar
				newStr[i] = newChar;
			}else{
				//����ԭ�ַ������ַ�������
				newStr[i] = this.content[i];
			}
		}
		//����һ���µ�MyStringʵ��
		return new MyString(newStr);
	}
	
	//���ҽ������ַ�������ָ���� char ֵʱ������ true�� 
	public boolean contains(char c){
		for (int i = 0; i < this.content.length; i++) {
			if(this.content[i]==c){
				//����ҵ�ƥ���ַ�����return true����ʱѭ����ֹ������������
				return true;
			}
		}
		//ѭ����ϣ�û���ҵ�������false
		return false;
	}
	
	//���ݸ����ַ���ִ��ַ����� 
	public MyString[] split(char c){
		//Ҫ֪�����ջᱻ��Ϊ�����ַ�������Ҫ֪���ַ������˼���
		int appears = 0;
		for (int i = 0; i < this.content.length; i++) {
			if(this.content[i] == c){
				appears++;
			}
		}
		if(appears>0){
			//������ֳ���һ�Σ�����������飬�����ν�ȡ�ַ�����ֵ���������
			MyString[] result = new MyString[appears+1];//����n�˷ָ��������ȡ���Ϊn+1,��aaa.bbb.ccc��.��ȡ���Ϊaaa,bbb,ccc�����ַ���
			int loc=0;//�Ѿ���ȡ���ֵ�ĩλ
			int count=0;//�ѽ�ȡ��������ȡ�Ĵ����ͽ�ȡ�����ֵĴ�����ͬ����������Ϊ�е��⣬�����г�����
			for (int i = 0; i < this.content.length; i++) {
				if(this.content[i] == c){
					//�ҵ���ȡ��,��ʼ��ȡ
					result[count] = this.subString(loc, i);
					count++;//��ȡ����+1
					loc = i;//��¼�Ѿ���ȡ����λ��
					if(count == appears){
						//����Ѿ���ȡ�����һ�����ֵĽ�ȡ����ֱ�ӽ�ȡ��ĩλ
						result[count]=this.subString(loc+1);
						break;
					}
				}
			}
			return result;
		}
		//���һ�ζ�û�г��֣���ֱ�ӷ���ԭ�ַ�������ȻҲ���Է���null,���������Լ�˵���㡣
		MyString[] myString = new MyString[1];
		myString[0] = this;
		
		return myString;
	}
	
	//���ظ����ַ����ַ����е�λ�ã�Ĭ��0λ��һλ��
	public int indexOf(char c){
		return -1;
	}
	
	//���ص�ǰ�ַ���ӵ�е��ַ�����
	public int length(){
		return this.content.length;
	}
	
	//��ӡ��ǰ�ַ������ݵ�����̨��
	public void print(){
		for (int i = 0; i < this.content.length; i++) {
			System.out.print(this.content[i]);//�������ַ�������
		}
		System.out.println();//������һ�����з�
	}
	
	//����һ���µ��ַ��������Ǵ��ַ�����һ�����ַ����������ַ�����ָ�����������ַ���ʼ��ֱ�����ַ���ĩβ��
	public MyString subString(int beginIndex){
		
		return this.subString(beginIndex,this.length());
	}
	
	//����һ�����ַ��������Ǵ��ַ�����һ�����ַ����������ַ�����ָ���� beginIndex ����ʼ��ֱ������ endIndex - 1 �����ַ�����ˣ������ַ����ĳ���Ϊ endIndex-beginIndex�� 
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
////		MyString myString = new MyString(new char[]{'a','b','��'});
////		MyString myString2 = new MyString(new char[]{'c','d'});
//// 		MyString myString3 = myString.concat(myString2);
//// 		myString3.print();
//		
//		MyString myString = new MyString(new char[]{'a','.','c','.','d','.','b','��'});
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
