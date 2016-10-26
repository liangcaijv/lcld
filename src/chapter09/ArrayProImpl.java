package chapter09;

/**
 * �ɱ䳤��ǿ����ʵ����
 */
public class ArrayProImpl implements ArrayPro{
	
	//�洢���ݵ����飬����ΪObject���ͣ�
	private Object[] elementData;
	
	//�������������ݵ�����
	private int size;
	
	/**
	 * �޲ι��췽����Ĭ�ϳ�ʼ��10������ռ䣬size��ʼ��Ϊ0
	 */
	public ArrayProImpl() {
		this.elementData = new Object[10];
		this.size = 0;
	}
	
	/**
	 * ���г�ʼ�������趨�Ĺ��췽�������ݴ���������ʼ�����鳤�ȡ�
	 */
	public ArrayProImpl(int initialCapacity){
		this.elementData = new Object[initialCapacity];
		this.size = 0;
	}
	
	/**
	 * ���ݵ�ǰ�洢�ռ䣬��addʱ��Ҫ���ô˷����ж��Ƿ���Ҫ���ݣ������㷨Ϊ�������޼�����2��
	 */
	private void ensureCapacity(int nextCapacity){
		if(nextCapacity > this.elementData.length){
			Object[] newArr = new Object[size*2];
			for (int i = 0; i < this.elementData.length; i++) {
				newArr[i] = this.elementData[i];
			}
			this.elementData = newArr;
		}
	}
	
	@Override
	public void add(Object element){
		ensureCapacity(size+1);
		this.elementData[size++] = element;
	}
	
	
	
	/**
	 * ɾ��ָ���±�Ԫ��
	 */
	@Override
	public void remove(int index) {
		//�µĴ洢�ռ䣬������1
		Object[] newArr = new Object[this.size-1];
		//����ԭ���鵽������
		for (int i = 0; i < newArr.length; i++) {
			if (i<index) {
				//��С����ɾԪ��������ֱ�Ӹ���
				newArr[i] = this.elementData[i];
			}else{
				//�����ڻ������ɾԪ��ʱ�������λ����
				newArr[i] = this.elementData[i+1];
			}
		}
		//�趨������Ϊ��ǰ��Ա
		this.elementData = newArr;
		//ά������
		size--;
	}

	/**
	 * �Ƴ����б����״γ��ֵ�ָ��Ԫ�أ�������ڣ���
	 */
	@Override
	public void remove(Object o) {
		// TODO Auto-generated method stub
		for (int i = 0; i < elementData.length; i++) {
			if(o.equals(elementData[i])){
				this.remove(i);
			}
		}
	}

	/**
	 * ��ָ����Ԫ��������б���ָ��λ���ϵ�Ԫ�ء�
	 * index:Ҫ���Ԫ������
	 * element:�����Ԫ��
	 */
	@Override
	public void set(int index, Object element) {
		// TODO Auto-generated method stub
		this.elementData[index] = element;
	}

	@Override
	public Object get(int index) {
		// TODO Auto-generated method stub
		return elementData[index];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	@Override
	public String toString() {
		String result = "��������" + size+"�����ݣ���������ռ�Ϊ"+this.elementData.length+"��Ԫ�طֱ��ǣ�";
		for (int i = 0; i < size; i++) {
			//ƴ��ÿһλԪ��
			result += this.elementData[i];
			if(i!=(size-1)){
				//��ĩβ׷��,
				result+=",";
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		ArrayProImpl arrayProImpl = new ArrayProImpl(2);
		arrayProImpl.add("123");
		arrayProImpl.add("aaa");
		arrayProImpl.add("333");
		System.out.println(arrayProImpl);
		arrayProImpl.remove("aaa");
		System.out.println(arrayProImpl);
	}
}
