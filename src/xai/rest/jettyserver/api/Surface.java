package xai.rest.jettyserver.api;

import java.io.Serializable;

/**
 * @author Joan-Manuel Marques
 *
 */
public class Surface implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final double PI = 3.1416;

	float rad;
	float alt;
	double surface;
	
	public Surface (float rad, float alt){
		this.rad = rad;
		this.alt = alt;
		this.surface = 2 * PI * rad * (rad + alt);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(alt);
		result = prime * result + Float.floatToIntBits(rad);
		long temp;
		temp = Double.doubleToLongBits(surface);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Surface other = (Surface) obj;
		if (Float.floatToIntBits(alt) != Float.floatToIntBits(other.alt))
			return false;
		if (Float.floatToIntBits(rad) != Float.floatToIntBits(other.rad))
			return false;
		if (Double.doubleToLongBits(surface) != Double.doubleToLongBits(other.surface))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "{rad=" + rad + ", alt=" + alt + ", surface="+ surface + "}";
	}
}