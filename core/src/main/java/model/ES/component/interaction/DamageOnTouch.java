package model.ES.component.interaction;

import com.simsilica.es.EntityComponent;

import model.ES.richData.Damage;

public class DamageOnTouch implements EntityComponent {
	public final Damage damage;
	
	public DamageOnTouch(Damage damage) {
		this.damage = damage;
	}
}