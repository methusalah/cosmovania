package presenter.worldEdition;

import controller.ECS.SceneSelectorState;

public abstract class Tool {

	protected SceneSelectorState selector;
	
	public Tool() {
		super();
	}

	public void setSelector(SceneSelectorState selector){
		this.selector = selector;
	};
	
	public void doPrimary(){}
	public void doSecondary(){}
}