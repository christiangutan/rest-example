package xai.rest.jettyserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import edu.uoc.dpcs.lsim.logger.LoggerManager.Level;
import lsim.library.api.LSimLogger;
import xai.rest.jettyserver.api.RestServerAPI;

/**
 * @author Joan-Manuel Marques
 *
 */

public class JettyWebServer extends Thread {
	private int port = Integer.MIN_VALUE;
	private Server jettyServer;
	
	public JettyWebServer(int port){
		this.port = port;
	}
	
	public void run() {
		LSimLogger.log(Level.INFO, "startJettyWebServer");		
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");

		jettyServer = new Server(port);
		jettyServer.setHandler(context);		

		ServletHolder jerseyServlet = context.addServlet(
				ServletContainer.class, "/*");
		jerseyServlet.setInitOrder(0);		

		// Tells the Jersey Servlet which REST service/class to load.
		jerseyServlet.setInitParameter(
				"jersey.config.server.provider.classnames",
				RestServerAPI.class.getCanonicalName());

		try {
			jettyServer.start();			
			LSimLogger.log(Level.INFO, "Jetty server started");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Stops Jetty server after <code>milliseconds</code> time.
	 *  This time should be large enough to allow clients to send the requests.
	 * @param millisecons
	 */
	public void stopJettyServerAfter(long millisecons) {
		LSimLogger.log(Level.INFO, "stopJettyServerAfter "+millisecons+" milliseconds");
		try {
			Thread.sleep(millisecons);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			jettyServer.stop();
			LSimLogger.log(Level.INFO, "Jetty server stopped");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}