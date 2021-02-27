package common;

public class Common {

	// 게시판 페이징 처리를 위한 클래스
	// 내부 클래스 (클래스 안의 클래스)
	public static class Emp{

		// 한 페이지에 보여줄 게시글 수
		public final static int BLOCKLIST = 10;

		// 한 화면에 보여지는 페이지 메뉴 수
		// 1 2 3 
		public final static int BLOCKPAGE = 5;
	}

	// 꼭 모든 페이지의 처리 기법이 같진 않으므로 클래스로 구분을 둔다.
	// 페이징 처리 클래스에서 따로 나누게되면 Common.Board.BLOCKLIST 혹은 Common.Notice.BLOCKLIST 등으로 따로 접근하여 처리하기 용이하다. (관리하기 쉽다.)
	public static class Cv{

		public final static int BLOCKLIST = 10;

		public final static int BLOCKPAGE = 5;
	}

	// 회의실 예약
	public static class Res{

		public final static int BLOCKLIST = 10;

		public final static int BLOCKPAGE = 5;
	}

	// 휴가
	public static class Vac{

		public final static int BLOCKLIST = 3;

		public final static int BLOCKPAGE = 5;
	}

	// 부서
	public static class Dept{

		public final static int BLOCKLIST = 10;

		public final static int BLOCKPAGE = 5;
	}

	// 자료실
	public static class Upload{

		public final static int BLOCKLIST = 2;

		public final static int BLOCKPAGE = 5;
	}

}
