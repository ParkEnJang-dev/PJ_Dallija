package com.spring.dallija.common.config;

import com.spring.dallija.common.anotation.LoginUser;
import com.spring.dallija.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final LoginService loginService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("supportsParameter 실행");

        //@Login 붙어 있는가 확인
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(LoginUser.class);
        boolean hasStringType = String.class.isAssignableFrom(parameter.getParameterType());

        return hasLoginAnnotation && hasStringType;
    }

    //supportsParameter true 면 아래꺼 실행
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        log.info("resolveArgument 실행");

        /*HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        //세션 만들지 않는다.
        HttpSession session = request.getSession(false);
        if (session == null){
            return null;
        }*/

        //없으면 null
        return loginService.getLoginUser();
    }
}
