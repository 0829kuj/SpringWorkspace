package com.myapp.bbs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.myapp.bbs.model.User;

// 새 인터셉터 클래스를 만들어 인터페이스 HandlerInterceptor를 구현하여 인터셉터 만들기
// 로그인 후 컨트롤러를 거치는 페이지로 이동 시 계속해서 인증된 상태인지 확인
public class LoginCheckInterceptor implements HandlerInterceptor {

	// preHandle메서드: 컨트롤러에 가기 전 Interceptor에서 캐치하여 작업수행
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();	// 세션 불러오기
		User user = (User) session.getAttribute("user");
		if (user == null) {
			// 로그인인증이 안된상태 => 로그인 화면으로 이동
			String url = session.getServletContext().getContextPath() + "/login";
			response.sendRedirect(url);
			System.out.println("LoginInterceptor # preHandle() : 로그인 인증안됨");
			return false;
		}
		
		// 인터셉터 메서드에서 리턴이 true이면 통과, false이면 차단
		System.out.println("LoginInterceptor # preHandle() : 인증됨");
		return true;
	}
}
