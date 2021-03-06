package logic.processor.logic;

import com.brainless.alchemist.model.ECS.pipeline.Pipeline;
import com.brainless.alchemist.model.ECS.pipeline.BaseProcessor;
import com.simsilica.es.Entity;

import component.lifeCycle.LifeTime;
import component.lifeCycle.ToRemove;

public class LifeTimeProc extends BaseProcessor{

	@Override
	protected void registerSets() {
		registerDefault(LifeTime.class);
	}
	
	@Override
	protected void onEntityEachTick(Entity e) {
		LifeTime life = e.get(LifeTime.class);
		if(life.duration <= 0)
			setComp(e, new ToRemove());
		setComp(e, new LifeTime(life.getDuration() - Pipeline.getMillisPerTick()));
	}
}
