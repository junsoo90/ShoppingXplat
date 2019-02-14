package custom.session;

import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.swing.plaf.synth.SynthSpinnerUI;

import custom.VO.CustomVO;

public class SessionListener implements HttpSessionAttributeListener  {

	public static ConcurrentHashMap<String, String> sessionMap = new ConcurrentHashMap<>();

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println("------------ Listener ---------------");
		
		HttpSession session = event.getSession();
		
		ServletContext context = session.getServletContext();

		String sName = ""; 
		
		Enumeration e = session.getAttributeNames(); 
		while (e.hasMoreElements()) {
			String attributeName = (String) e.nextElement();
			//String attributeValue = (String) session.getAttribute(attributeName);
			System.out.println("attributeName : " + attributeName );
		} 
		
		System.out.println(session.getAttribute("custom"));
		CustomVO vo  = (CustomVO)session.getAttribute("custom");
		String cId = "";
		String customSession = "";
		
		String currentSession = session.getId();
		if(vo != null) {
			cId = vo.getcId();

			String orgIdSession = sessionMap.get(cId);
			System.out.println("orgIdSession = " + orgIdSession);
			
			sessionMap.put(cId, currentSession);
			customSession = sessionMap.get(cId);
			
		}
		
		System.out.println("customSession = " + customSession);
		
		
		System.out.println("session.getid = " + currentSession);
		System.out.println("------------ end ---------------");
		
	}@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {

		System.out.println("************** attributeRemoved *****************");
		
	}@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}

	public void sessionCreated(HttpSessionEvent event) {

		System.out.println("------------ Listener ---------------");
		HttpSession session = event.getSession();
		
		ServletContext context = session.getServletContext();

		String sName = ""; 
		
		Enumeration e = session.getAttributeNames(); 
		while (e.hasMoreElements()) {
			String attributeName = (String) e.nextElement();
			String attributeValue = (String) session.getAttribute(attributeName);
			System.out.println("attributeName : " + attributeName 
					+ " attributeValue : " + attributeValue );
		} 
		CustomVO vo  = (CustomVO)session.getAttribute("custom");
		String cId = "";
		String customSession = "";
		
		if(vo != null) {
			cId = vo.getcId();
			customSession = sessionMap.get(session.getAttribute(cId));
		}
		
		System.out.println("customSession = " + customSession);
		
		
		System.out.println("session.getid = " + session.getId());
		System.out.println("------------ end ---------------");
	}

	public void sessionDestroyed(HttpSessionEvent event) {

		HttpSession session = event.getSession();

		ServletContext context = session.getServletContext();

		Map activeUsers = (Map) context.getAttribute("activeUsers");

		activeUsers.remove(session.getId());

	}


}
