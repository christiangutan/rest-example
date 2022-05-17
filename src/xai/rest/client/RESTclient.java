package xai.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import edu.uoc.dpcs.lsim.logger.LoggerManager.Level;
import lsim.library.api.LSimLogger;
import xai.rest.jettyserver.api.Surface;

/**
 * @author Joan-Manuel Marques
 * @author Christian Gutiérrez Antolín
 */

public class RESTclient {

	public double surfaceBase(String address, int port, float value1) {
		LSimLogger.log(Level.INFO, "http://"+address+":"+port+"/cil/surfaceBase/"+value1);
		LSimLogger.log(Level.INFO, "media type: text/plain");

		/* TODO: Completar el código / Completar el codi */

		Client client = ClientBuilder.newClient();
		String result = client
							.target("http://" + address + ":" + port + "/cil" )
							.path("/surfaceBase/" + value1)
							.request(MediaType.TEXT_PLAIN)
							.get()
							.readEntity(String.class);
		
		//LSimLogger.log(Level.INFO, "Surface Base - Result got");
		
		return Double.parseDouble(result);
	}
	
	public double surfaceSide(String address, int port, float value1, float value2) {
		LSimLogger.log(Level.INFO, "http://"+address+":"+port+"/cil/surfaceSide/"+value1+"/"+value2);
		LSimLogger.log(Level.INFO, "media type: application/json");
		
		/* TODO: Completar el código / Completar el codi */
		
		Client client = ClientBuilder.newClient();
		String result = client
							.target("http://" + address + ":" + port + "/cil")
							.path("/surfaceSide/" + value1 + "/" + value2)
							.request("application/json")
							.get()
							.readEntity(String.class);
		
		//LSimLogger.log(Level.INFO, "Surface Side - Result got");
		
		return Double.parseDouble(result);
	}

	public Surface surfaceTotal(String address, int port, float value1, float value2) {
		LSimLogger.log(Level.INFO, "http://"+address+":"+port+"/cil/surfaceTotal/"+value1+"/"+value2);
		LSimLogger.log(Level.INFO, "media type: application/json");

		/* TODO: Completar el código / Completar el codi */
		
		Client client = ClientBuilder.newClient();
		String result = client
							.target("http://" + address + ":" + port + "/cil")
							.path("/surfaceTotal/" + value1 + "/" + value2)
							.request("application/json")
							.get()
							.readEntity(String.class);
		
		//LSimLogger.log(Level.INFO, "Surface Total - Result got");
		
		Surface surface = new Gson().fromJson(result, Surface.class);	
		
		//LSimLogger.log(Level.INFO, "Surface Total - Gson built");
		
		return surface;
	}
}