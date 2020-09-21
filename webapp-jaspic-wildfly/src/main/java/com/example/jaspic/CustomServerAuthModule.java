package com.example.jaspic;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.message.AuthException;
import javax.security.auth.message.AuthStatus;
import javax.security.auth.message.MessageInfo;
import javax.security.auth.message.MessagePolicy;
import javax.security.auth.message.callback.CallerPrincipalCallback;
import javax.security.auth.message.callback.GroupPrincipalCallback;
import javax.security.auth.message.module.ServerAuthModule;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomServerAuthModule implements ServerAuthModule {

    private static final Logger logger = Logger.getLogger(CustomServerAuthModule.class.getName());

    @SuppressWarnings("rawtypes")
    protected static final Class[] supportedMessageTypes = new Class[] { 
        HttpServletRequest.class,
        HttpServletResponse.class 
    };

    private CallbackHandler handler;

    private static final String[] TEST_ROLES = { "user", "super-user", "super-man", "Antonette" };
    private static final String   TEST_USER = "charlie";

    

    private void debugRequest(HttpServletRequest request) {
        logger.info("-- Incoming request -------------------- ");
        logger.info("Scheme     : " + request.getScheme() + "://");
        logger.info("ServerName : " + request.getServerName());
        logger.info("ServerPort : " + request.getServerPort());
        logger.info("RequestURI : " + request.getRequestURI());
        logger.info("QueryString: " + request.getQueryString());
        logger.info("ContextPath: " + request.getContextPath());
        logger.info("PathInfo   : " + request.getPathInfo());
        getSession(request).ifPresent(session->{ logger.info(session.getId()); });
    }

    private boolean authenticationIsNotMandantory(MessageInfo messageInfo) {
        return !Boolean.valueOf((String) messageInfo.getMap().get("javax.security.auth.message.MessagePolicy.isMandatory"));
    }

    private boolean login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (BeanUtils.allNotNull(username, password)) {
            UserService userService = BeanUtils.getBeanByType(UserService.class);
            return (userService.authenticate(username, password)) ;
        }

        return false;
    }

    private boolean logout(HttpServletRequest request) {
        String username=request.getParameter("username");

        if ((request.getUserPrincipal() != null) && (username != null)) { 
            return (username.equals("logout"));
        }
        return false;
    }

    private Optional<HttpSession> getSession(HttpServletRequest request) {
        return Optional.ofNullable(request.getSession(false));
    }

    private void extendCookieExpriry(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if ((cookies != null) && (cookies.length > 0)) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("JSESSIONID")) {
                    cookie.setMaxAge(60);
                    response.addCookie(cookie);
                }
            }
        }
    }

    /**
     * Tell container to register an authentication session.rawtypes")
     * @param messageInfo
     */
    private void informContainerAboutNewSession(MessageInfo messageInfo) {
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) messageInfo.getMap();
        map.put("javax.servlet.http.registerSession", Boolean.TRUE.toString());
    }

    private AuthStatus cleanupSession(HttpServletRequest request, HttpServletResponse response, Subject clientSubject) {
        logger.info("Cleanup session");
        try {
            request.logout();
            getSession(request).ifPresent(HttpSession::invalidate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        clientSubject.getPrincipals().clear();
        try {
            response.sendRedirect(request.getContextPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AuthStatus.SUCCESS;   
    }

    private AuthStatus createSession(MessageInfo messageInfo, HttpServletRequest request, HttpServletResponse response, Subject clientSubject) {
        logger.info("Create session");
        getSession(request).ifPresent(HttpSession::invalidate);

        try {
            handler.handle(new Callback[] { 
                new CallerPrincipalCallback(clientSubject, TEST_USER),
                new GroupPrincipalCallback(clientSubject, TEST_ROLES) 
            });
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        informContainerAboutNewSession(messageInfo);

        return AuthStatus.SUCCESS;        
    }

    private AuthStatus continueSession(HttpServletRequest request, HttpServletResponse response, Subject clientSubject) {
        logger.info("Continue session");
        extendCookieExpriry(request, response);

        Callback[] callbacks = new Callback[] {
                new CallerPrincipalCallback(clientSubject, request.getUserPrincipal()) };
        try {
            handler.handle(callbacks);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return AuthStatus.SUCCESS;           
    }

    @Override
    public AuthStatus validateRequest(MessageInfo messageInfo, Subject clientSubject, Subject serviceSubject)
            throws AuthException {

        HttpServletRequest request = (HttpServletRequest) messageInfo.getRequestMessage();
        HttpServletResponse response = (HttpServletResponse) messageInfo.getResponseMessage();
        debugRequest(request);        
    
        if (authenticationIsNotMandantory(messageInfo)) {
            return AuthStatus.SUCCESS;
        }
        
        if (login(request)) {
            return createSession(messageInfo, request, response, clientSubject);
        }

        if (logout(request)) {
            return cleanupSession(request, response, clientSubject);
        }
        
        if (request.getUserPrincipal() != null) {
            return continueSession(request, response, clientSubject);
        }

        logger.info("Authentication failed");
        return AuthStatus.FAILURE;
    }

    @Override
    public AuthStatus secureResponse(MessageInfo messageInfo, Subject serviceSubject) throws AuthException {
        logger.info("secureResponse");
        return AuthStatus.SEND_SUCCESS;
    }

    @Override
    public void cleanSubject(MessageInfo messageInfo, Subject subject) throws AuthException {
        if (subject != null) {
            subject.getPrincipals().clear();
        }
    }

    @Override
    @SuppressWarnings("rawtypes")
    public void initialize(MessagePolicy requestPolicy, MessagePolicy responsePolicy, CallbackHandler handler,
            Map options) throws AuthException {
        logger.info("initialized");
        this.handler = handler;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Class[] getSupportedMessageTypes() {
        logger.info("getSupportedTypes");
        return supportedMessageTypes;
    }
}
