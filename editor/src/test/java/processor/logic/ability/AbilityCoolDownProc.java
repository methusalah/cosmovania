package test.java.processor.logic.ability;

import model.ES.component.ability.Ability;
import model.ES.component.ability.Cooldown;
import model.tempImport.LogicLoop;

import com.simsilica.es.Entity;

import main.java.model.ECS.pipeline.Processor;

public class AbilityCoolDownProc extends Processor {

	@Override
	protected void registerSets() {
		registerDefault(Cooldown.class, Ability.class);
	}
	
	@Override
	protected void onEntityEachTick(Entity e) {
		Cooldown cd = e.get(Cooldown.class);
		Ability t = e.get(Ability.class);
		if(cd.getRemaining() > 0){
			if(t.isTriggered())
				setComp(e, new Ability(t.getName(), false));
			setComp(e, new Cooldown(cd.getRemaining() - LogicLoop.getMillisPerTick(), cd.getDuration()));
		}
	}
}