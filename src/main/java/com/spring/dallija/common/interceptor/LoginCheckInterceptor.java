package com.spring.dallija.common.interceptor;

import com.spring.dallija.common.anotation.LoginCheck;
import com.spring.dallija.domain.user.UserRole;
import com.spring.dallija.exception.user.UnAuthUserException;
import com.spring.dallija.exception.user.UnLoginException;
import com.spring.dallija.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {

    private final LoginService loginService;

    @Inject
    private Environment environment;

    public static final String LOG_ID = "logId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //모든 api request 들어온다.

        //테스트 환경에서 동작하지 않도록 설정.
        String[] activeProfiles = environment.getActiveProfiles();
        if ("test".equals(activeProfiles[0])) {
            return true;
        }


        //@RequestMapping : HandlerMethod
        //정적 리소스 : ResourceHttpRequestHandler

        if (handler instanceof HandlerMethod) {
            //호출할 컨트롤러 메서드의 모든 정보가 포함되어있다.
            HandlerMethod hm = (HandlerMethod) handler;
            LoginCheck lc = hm.getMethodAnnotation(LoginCheck.class);

            if (lc == null) {
                log.debug("loginCheck null");
                return true;
            }

            if (loginService.getLoginUser() == null) {
                throw new UnLoginException("로그인 후 이용해주세요.");
            }

            UserRole userRole = lc.userRole();

            switch (userRole) {
                case USER:
                    break;
                case ADMIN:
                    break;
            }

        }

        log.info("REQUEST [{}]", handler);
        return true;
    }


}
