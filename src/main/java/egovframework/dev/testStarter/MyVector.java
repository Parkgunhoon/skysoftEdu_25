package egovframework.dev.testStarter;

import com.sun.corba.se.impl.ior.OldObjectKeyTemplateBase;


class MyVector {
	protected Object[] data = null; // 객체를 담기 위한 객체배열을 선언한다.
	protected int capacity = 0; // 용량 //2
	protected int size = 0; // 크기

	public MyVector(int capacity) {
		if (capacity < 0)
			throw new IllegalArgumentException("유효하지 않은 값입니다. :" + capacity);
		this.capacity = capacity;
		data = new Object[capacity];
	}

	public MyVector() {
		this(10); // 크기를 지정하지 않으면 크기를 10으로 한다.
	}

	public boolean isEmpty() { // MyVector가 비어있는지 확인한다.
		return size == 0;
		/*if(size==0)System.out.println("사이즈가 비어있습니다.");
		System.out.println("사이즈 값은"+size+"입니다.");*/
	}

	public int capacity() { // MyVector의 용량(크기)를 반환한다.
		return capacity;
	}

	public int size() { // MyVector에 저장된 객체의 개수를 반환한다.
		return size;
		/*int vecnum = 0;
		for(int i=0;i<data.length;i++){
			vecnum++;
		}
		return vecnum;*/
	}

	public void addSize() { // 사이즈 증가
		size++;
	}

	public void minusSize() { // 사이즈 감소
		size--;
	}

	public void ensureCapacity(int minCapacity) {
		int newCapacity = capacity;

		if (minCapacity > capacity) {
			newCapacity = capacity + 1;
		}

		if (minCapacity > newCapacity) {
			newCapacity = minCapacity;
		}

		setCapacity(newCapacity);
	}

	public void setCapacity(int newCapacity) {
		if (this.capacity == newCapacity)
			return;

		Object[] tmp = new Object[newCapacity];
		System.arraycopy(data, 0, tmp, 0, size);

		this.data = tmp;
		this.capacity = newCapacity;
	}

	public boolean add(Object obj) {
		// 새로운 객체를 저장하기 전에 저장할 공간을 확보한다.
		ensureCapacity(size + 1);
		data[size++] = obj;
		return true;
	}

	public Object get(int index) {

		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("범위를 벗어났습니다.");
		return data[index];
	}

	public Object set(int index, Object obj) {
		if (index >= size)
			throw new ArrayIndexOutOfBoundsException(index);

		Object oldValue = data[index];
		data[index] = obj;
		return oldValue;
	}

	public int indexOf(Object obj, int index) {
		if (obj == null) {
			for (int i = index; i < size; i++) {
				if (data[i] == null)
					return i;
			} // for
		} else {
			for (int i = index; i < size; i++) {
				if (obj.equals(data[i]))
					return i;
			} // for
		}

		return -1;
	}

	public int lastIndexOf(Object obj, int index) {
		if (index >= size)
			throw new IndexOutOfBoundsException(index + " >= " + size);

		if (obj == null) {
			for (int i = index; i >= 0; i--)
				if (data[i] == null)
					return i;
		} else {
			for (int i = index; i >= 0; i--)
				if (obj.equals(data[i]))
					return i;
		}

		return -1;
	}

	public boolean contains(Object obj) {
		return indexOf(obj, 0) >= 0;
	}

	public int indexOf(Object obj) {
		// 찾기 시작할 위치(index)를 지정하지 않으면 처음부터 찾는다.
		return indexOf(obj, 0);
	}


	public int lastIndexOf(Object obj) {
		// 찾기 시작할 위치(index)를 지정하지 않으면 끝부터 찾는다.
		return lastIndexOf(obj, size - 1);
	}

	/**
	 * <PRE>
	 * 1. MethodName    : add
	 * 2. ClassName    : MyVector2
	 * 3. Author        : anonymous
	 * 4. Creation Date    : 2012. 8. 30. 오전 9:37:58
	 * 5. Comment    : 데이터 용량(capacity)에 맞춰 크기(size) 범위 안에서 데이터의 순서(index)를 조정
	 *                               하여 데이터를 추가 한다.
	 * </PRE>
	 *
	 * @return void
	 * @param index
	 *            : 데이터의 순서
	 * @param obj
	 *            : 입력된 객체
	 */
	public void add(int index, Object obj) {
				if(index>size){throw new ArrayIndexOutOfBoundsException(index + " >= " + size);}
				ensureCapacity(size + 1);
				//Object[] tmp = new Object[size];
				System.arraycopy(data, index, data, index+1, size-index);
				//data = tmp;
				data[index] = obj;

				//addSize();

				/*ensureCapacity(size + 1);
				data[size++] = obj;*/

				//System.arraycopy(obj, 0, tmp, index, size);
				//this.data = tmp;
				size++;




		// 1. index의 값이 size보다크면, ArrayIndexOutOfBoundsException
		// 2. ensureCapacity()를 호출해서 새로운 객체가 저장될 공간을 확보한다.
		// 3. 객체배열에서 index위치의 객체와 이후의 객체들을 한칸씩 옆으로 이동한다. (System.arraycopy()사용)
		// 4. 객체배열의 index위치에 새로운 객체(obj)를 저장한다.
		// 5. size의 값을 1 증가시킨다.
	}

	/**
	 * <PRE>
	 * 1. MethodName    : remove
	 * 2. ClassName    : MyVector2
	 * 3. Author        : anonymous
	 * 4. Creation Date    : 2012. 8. 30. 오전 10:28:01
	 * 5. Comment    : 데이터 용량(capacity)에 맞춰 크기(size) 범위 안에서 데이터의 순서(index)를 조정
	 *                               하여 데이터를 제거 한다.
	 * </PRE>
	 *
	 * @return Object
	 * @param index
	 * @return
	 */
	public Object remove(int index) {
		if(index<0 || index>=size)throw new IndexOutOfBoundsException(index + ">" + data.length);
		Object oldObj = null;
		oldObj = data[index];
		if(index != size-1){
			//Object[] tmp = new Object[size];
			System.arraycopy(data, index+1, data, index, size-index-1);
			//data = tmp;
		}
			data[size-1] = null;
			minusSize();

		// 1. index가 배열의 범위를 벗어나는지 체크하고, 벗어나면 IndexOutOfBoundsException를 발생시킨다.
		// 2. 삭제하고자하는 데이터를 oldObj에 저장한다.
		// 3. 삭제하고자 하는 객체가 마지막 객체가 아니라면, 배열복사를 통해 빈자리를 채워준다.
		// 4. 마지막 데이터를 null로 한다. 배열은 0 부터 시작하므로 마지막 요소는 index가 size-1이다.
		// 5. size의 값을 1 감소시킨다.
		// 6. oldObj를 반환한다.
		return oldObj;	//작성시 제거후 옳바른 코드를 넣으세요.
	}

	/**
	 * <PRE>
	 * 1. MethodName    : remove
	 * 2. ClassName    : MyVector2
	 * 3. Author        : anonymous
	 * 4. Creation Date    : 2012. 8. 30. 오전 10:43:19
	 * 5. Comment    : 데이터를 삭제한다
	 * </PRE>
	 *
	 * @return boolean
	 * @param obj
	 * @return
	 */
	public boolean remove(Object obj) {
		//boolean flag = false;
		for(int i = 0;i<size;i++){
			if(obj.equals(data[i])){
				remove(i);
				return true;
			}
		}
		// 1. 반복문을 이용해서 객체배열의 모든 요소와 obj가 일치하는지 확인한다.
		// 1.1 일치하면 remove(int index)를 호출해서 삭제하고 true를 반환한다.
		// 1.2 일치하는 것을 찾지 못하면, false를 반환한다.
		return false;	//작성시 제거후 옳바른 코드를 넣으세요.
	}

	/**
	 * <PRE>
	 * 1. MethodName    : clear
	 * 2. ClassName    : MyVector2
	 * 3. Author        : anonymous
	 * 4. Creation Date    : 2012. 8. 30. 오전 10:53:26
	 * 5. Comment    : Vector를 초기화한다 RETURN NOT NULL
	 * </PRE>
	 *
	 * @return void
	 */
	public void clear() {
		for(int i = 0;i<size;i++){
			data[i] = null;
		}
		size = 0;
		//capacity = 0;
		// 코드를 완성하세요.
		// return 값은 null이 아닙니다.
	}

	/**
	 * <PRE>
	 * 1. MethodName    : toArray
	 * 2. ClassName    : MyVector2
	 * 3. Author        : anonymous
	 * 4. Creation Date     : 2012. 8. 30. 오전 11:19:04
	 * 5. Comment    : 배열을 복사한다
	 * </PRE>
	 *
	 * @return Object[]
	 * @return
	 */
	public Object[] toArray() {
		Object[] tmp = new Object[size];
		System.arraycopy(data, 0, tmp, 0, size);
		// 1. 객체배열 data와 같은 크기의 객체배열을 생성한다.
		// 2. 배열의 내용을 복사한다. (System.arraycopy()사용)
		// 3. 생성한 객체배열을 반환한다.
		return tmp;	//작성시 제거후 옳바른 코드를 넣으세요.
	}

	/**
	 * <PRE>
	 * 1. MethodName    : toString
	 * 2. ClassName    : MyVector2
	 * 3. Author        : anonymous
	 * 4. Creation Date    : 2012. 8. 30. 오전 11:19:19
	 * 5. Comment    : 배열객체를 String으로 변경하여 반환한다
	 * </PRE>
	 *
	 * @return
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer(100);

		for(int i = 0;i<size;i++){

				sb.append(data[i]);


			//sb.append(get(i));
		}

		// 1. StringBuffer를 생성한다.
		// 2. 반복문과 get(int i)를 사용해서 배열의 모든 요소에 접근해서 toString()을 호출해서 sb에 저장한다.
		// 3. sb를 String으로 변환해서 반환한다.
		return sb.toString();	//작성시 제거후 옳바른 코드를 넣으세요.
	}

} // class MyVector

class MyVectorEx4 {
	public static void main(String args[]) {
		MyVector v = new MyVector(2);

		v.add("AAA");
		v.add("BBB");
		v.add("CCC");
		v.add("DDD");
		v.add(2, "EEE");
		System.out.println("1. v.toString : " + v.toString() + "\t" + "size:" + v.size() + "\t" + "capacity:" + v.capacity() + "\t" + "isEmpty:" + v.isEmpty());

		v.remove(1); // BBB를 삭제
		System.out.println("2. v.toString : " + v.toString() + "\t" + "size:" + v.size() + "\t" + "capacity:" + v.capacity() + "\t" + "isEmpty:" + v.isEmpty());

		v.remove("CCC");
		System.out.println("3. v.toString : " + v.toString() + "\t" + "size:" + v.size() + "\t" + "capacity:" + v.capacity() + "\t" + "isEmpty:" + v.isEmpty());

		v.clear();
		System.out.println("4. v.toString : " + v.toString() + "\t" + "size:" + v.size() + "\t" + "capacity:" + v.capacity() + "\t" + "isEmpty:" + v.isEmpty());
		//System.out.println(v.toArray());
	} // main
} // class MyVectorEx4

