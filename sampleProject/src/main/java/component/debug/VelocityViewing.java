package component.debug;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import com.simsilica.es.EntityComponent;

import logic.processor.rendering.ui.VelocityView;
import util.geometry.geom2d.Point2D;

public class VelocityViewing implements EntityComponent {
	public Map<String, VelocityView> velocities = new HashMap<>();

	public VelocityViewing() {
	}
	
	public void updateVelocity(String name, Point2D vel, Color color, double factor, double elevation, int thickness){
		if(!velocities.containsKey(name)){
			VelocityView view = new VelocityView();
			velocities.put(name, view);
		}
		velocities.get(name).velocity = vel.getMult(factor);
		velocities.get(name).color = color;
		velocities.get(name).elevation = elevation;
		velocities.get(name).thickness = thickness;
	}
}
