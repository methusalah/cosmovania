package logic.processor.rendering.model;

import com.brainless.alchemist.model.ECS.pipeline.Pipeline;
import com.brainless.alchemist.model.ECS.pipeline.BaseProcessor;
import com.simsilica.es.Entity;

import component.assets.Model;
import component.assets.ModelRotation;
import util.math.Angle;
import util.math.AngleUtil;

public class ModelRotationProc extends BaseProcessor {

	@Override
	protected void registerSets() {
		registerDefault(Model.class, ModelRotation.class);
	}
	
	@Override
	protected void onEntityEachTick(Entity e) {
		Model m = e.get(Model.class);
		ModelRotation r = e.get(ModelRotation.class);
		
		double newRoll = r.getxPeriod() != 0? m.getRollFix().getValue()+AngleUtil.FULL/r.getxPeriod()*Pipeline.getSecondPerTick() : m.getRollFix().getValue();
		double newPitch = r.getyPeriod() != 0? m.getPitchFix().getValue()+AngleUtil.FULL/r.getyPeriod()*Pipeline.getSecondPerTick() : m.getPitchFix().getValue();
		double newYaw = r.getzPeriod() != 0? m.getYawFix().getValue()+AngleUtil.FULL/r.getzPeriod()*Pipeline.getSecondPerTick() : m.getYawFix().getValue();
		
		setComp(e, new Model(m.getPath(), m.getScale(), new Angle(AngleUtil.normalize(newYaw)), new Angle(AngleUtil.normalize(newPitch)), new Angle(AngleUtil.normalize(newRoll))));
	}
}
