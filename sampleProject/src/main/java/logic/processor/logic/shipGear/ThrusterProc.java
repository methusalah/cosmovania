package logic.processor.logic.shipGear;

import com.brainless.alchemist.model.ECS.pipeline.BaseProcessor;
import com.simsilica.es.Entity;

import component.motion.PlanarNeededThrust;
import component.motion.PlanarStance;
import component.motion.ThrustControl;
import component.motion.Thruster;
import logic.commonLogic.Controlling;
import util.math.AngleUtil;
import util.math.Fraction;

public class ThrusterProc extends BaseProcessor {

	@Override
	protected void registerSets() {
		registerDefault(Thruster.class, ThrustControl.class);
	}

	@Override
	protected void onEntityEachTick(Entity e) {
		ThrustControl thrustControl = e.get(ThrustControl.class);
		
		if(thrustControl.isActive()){
			Thruster thruster = e.get(Thruster.class);
			PlanarNeededThrust parentThrust = Controlling.getControl(PlanarNeededThrust.class, e.getId(), entityData);
			if(parentThrust == null)
				return;
			PlanarStance parentStance = Controlling.getControl(PlanarStance.class, e.getId(), entityData);
			if(parentStance == null)
				return;
			
			double activationRate = 0;
			if(!parentThrust.getDirection().isOrigin()){
				double diff = AngleUtil.getSmallestDifference(parentThrust.getDirection().getAngle()-parentStance.orientation.getValue(), thruster.direction.get2D().getAngle());
				if(diff <= thruster.activationAngle.getValue()){
					activationRate = 1;
					if(!thruster.onOff)
						activationRate = 1-(diff/thruster.activationAngle.getValue());
				}
			}
			setComp(e, new Thruster(thruster.direction, thruster.activationAngle, new Fraction(activationRate), thruster.onOff));
		}
	}

}
