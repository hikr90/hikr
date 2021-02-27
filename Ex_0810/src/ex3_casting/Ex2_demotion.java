package ex3_casting;

public class Ex2_demotion {
	public static void main(String[] args) {
		
		// 2. demotion casting : 작은 자료형에 큰 자료형을 대입 (값의 손실이 일어날 수 있으므로 자동으로 이뤄지지 않는다.)
		char c = 'A'; // 2byte
		int n = c + 1; // 4byte
		
		// 작은 자료형에 큰 자료형을 대입
		// 변수에서 변수 : 데이터 가용량에따라서 오류
		// 값을 대입 : 문제없음
		// 작은 자료형 = (작은 자료형의 자료타입)큰 자료형
		c = (char)n;
		// 'A'는 65로서 +1한 66값이 문자형으로 변하며 아스키코드에 해당하는 'B'가 나온다.
		System.out.println("c : " + c);
		
		System.out.println("------------------");
		
		float f = 5.5f; // 4.byte
		int num = 0;	// 4byte
		
		num = (int)f;
		// 값이 5가 나오면서 값의 손실이 난다.
		System.out.println("num : " + num);
		
		System.out.println("----------------------------");

		// 형 변환에대한 값의 차이
		//10.59 (앞의 8.43이 형변환 되어 8이 된다.)
		System.out.println((int)8.43+2.59);
		//11 (11.02가 형변환되어 10이 된다.)
		System.out.println((int)(8.43+2.59));
		//10 (8+2가 되어 10이 된다.)
		System.out.println((int)(8.43)+(int)(2.59));
		
		System.out.println("----------------------------");
		
		// CLASS TYPE의 형변환
		// String은 CLASS타입으로 형변환을 위해서는 그 타입에 맞는 식이 있어야한다. 
		// PARSING은 변환의 뜻을 가진다.
		String num1 = "1";
		String num2 = "2";
		
		System.out.println(Integer.parseInt(num1)+Integer.parseInt(num2));

		
		
	}
}
