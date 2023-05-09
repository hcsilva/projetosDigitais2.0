package br.com.cardapioDigital.configurations.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class TenantInterceptor implements HandlerInterceptor {

    private static final String TENANT_HEADER_NAME = "idTenant";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String idTenant = request.getHeader(TENANT_HEADER_NAME);
        request.setAttribute("idTenant", idTenant);

        return true;
    }
}
