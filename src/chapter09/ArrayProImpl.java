package chapter09;

/**
 * 可变长增强数组实现类
 */
public class ArrayProImpl implements ArrayPro{
	
	//存储数据的数组，抽象为Object类型，
	private Object[] elementData;
	
	//数组中已有数据的数量
	private int size;
	
	/**
	 * 无参构造方法，默认初始化10个数组空间，size初始化为0
	 */
	public ArrayProImpl() {
		this.elementData = new Object[10];
		this.size = 0;
	}
	
	/**
	 * 带有初始化容量设定的构造方法。根据传入容量初始化数组长度。
	 */
	public ArrayProImpl(int initialCapacity){
		this.elementData = new Object[initialCapacity];
		this.size = 0;
	}
	
	/**
	 * 扩容当前存储空间，在add时需要调用此方法判断是否需要扩容，扩容算法为到达上限即扩大2倍
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
	 * 删除指定下标元素
	 */
	@Override
	public void remove(int index) {
		//新的存储空间，容量少1
		Object[] newArr = new Object[this.size-1];
		//复制原数组到新数组
		for (int i = 0; i < newArr.length; i++) {
			if (i<index) {
				//当小于所删元素索引，直接复制
				newArr[i] = this.elementData[i];
			}else{
				//当大于或等于所删元素时，向后移位复制
				newArr[i] = this.elementData[i+1];
			}
		}
		//设定新数组为当前成员
		this.elementData = newArr;
		//维护长度
		size--;
	}

	/**
	 * 移除此列表中首次出现的指定元素（如果存在）。
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
	 * 用指定的元素替代此列表中指定位置上的元素。
	 * index:要替代元素索引
	 * element:替代的元素
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
		String result = "容器内有" + size+"条数据，内置数组空间为"+this.elementData.length+"，元素分别是：";
		for (int i = 0; i < size; i++) {
			//拼接每一位元素
			result += this.elementData[i];
			if(i!=(size-1)){
				//非末尾追加,
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
