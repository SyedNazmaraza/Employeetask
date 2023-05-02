package Listeners;

import javax.servlet.ServletContext;

import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class Listeneremp
 *
 */
public class Listeneremp implements ServletContextListener, ServletContextAttributeListener {

    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext cxt = sce.getServletContext();
    	String url = cxt.getInitParameter("Dburl");
    	String user = cxt.getInitParameter("Dbid");
    	String pass = cxt.getInitParameter("Dbpass");
    	Dbclass db = new Dbclass(url,user,pass);
    	cxt.setAttribute("dbmanager", db);
    }
    
    public void contextDestroyed(ServletContextEvent sce)  { 
   }
	
}
