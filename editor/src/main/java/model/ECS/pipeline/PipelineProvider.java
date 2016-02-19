package main.java.model.ECS.pipeline;

import com.jme3.app.state.AppStateManager;

import main.java.model.EditorPlatform;
import main.java.model.state.DataState;
import main.java.model.tempImport.AppFacade;

public class PipelineProvider {
	
	public Pipeline createRendererPipeline(){
		return new Pipeline(AppFacade.getStateManager(), "Unnamed renderer pipeline");
	}

	public Pipeline createIndependantPipeline(){
		AppStateManager stateManager = new AppStateManager(null);
		stateManager.attach(new DataState(EditorPlatform.getEntityData()));
		Pipeline res = new Pipeline(stateManager, "Unnamed indepandant pipeline");
		Thread thread = new Thread(() -> {
			while (!Thread.currentThread().isInterrupted()) {
				long time = System.currentTimeMillis();
				stateManager.update((float)0.02);
				long nextTick = (long) (time+20);
				long towait = nextTick - System.currentTimeMillis();

				res.tickCount++;
				res.waitTime += towait;
				
				if(towait > 0)
					try {
						Thread.sleep(towait);
					} catch (InterruptedException e) {
						break;
					}
			}
		});
		thread.start();
		return res;
	}
}
