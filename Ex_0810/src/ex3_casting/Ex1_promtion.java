package ex3_casting;

public class Ex1_promtion {
	public static void main(String[] args) {
		// 캐스팅(형변환)
		// 1. promotion casting : 큰 자료형에 작은 자료형을 대입하는 것
		// 프로모션 캐스팅은 자동으로 동작한다.
		double d = 100.5;
		int n = 200;
		
		// = 은 오른쪽의 값을 왼쪽으로 변경한다는 뜻이다.
		// double이 int보다 저장공간이 더 커서 아래 식은 오류가 나지 않는다.
		d = n;
		System.out.println("d : " + d);
		// 작은 자료형에 큰 자료형을 넣을 때, 프로모션으로 자동으로 되기는하나 c는 문자형으로 아스키코드에 의해서 정수형태로 형 변환이 된다. 
		char c = 'A';
		int n2 = c + 1;		
		System.out.println("n2 : " + n2);
		
	}
}
