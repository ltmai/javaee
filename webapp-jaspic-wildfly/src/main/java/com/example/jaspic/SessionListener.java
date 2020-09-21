package com.example.jaspic;

import java.util.logging.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

  private static final Logger logger = Logger.getLogger(SessionListener.class.getName());

  @Override
  public void sessionCreated(HttpSessionEvent se) {
      logger.info("HttpSessionListener#sessionCreated: sessionId = " + se.getSession().getId());
      HttpSession session = se.getSession();
      session.setMaxInactiveInterval(60);//in seconds
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent se) {
      logger.info("HttpSessionListener#sessionDestroyed: sessionId = " + se.getSession().getId());
  }
}