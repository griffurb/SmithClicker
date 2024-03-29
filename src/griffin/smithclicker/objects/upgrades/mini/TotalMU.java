package griffin.smithclicker.objects.upgrades.mini;

import java.awt.Color;
import java.awt.Image;
import java.math.BigInteger;
import java.util.List;

import griffin.smithclicker.event.EventManager;
import griffin.smithclicker.event.IncreaseSmithEvent;
import griffin.smithclicker.event.WatchEvent;
import griffin.smithclicker.util.MathUtils;

public class TotalMU extends Miniupgrade {

	private IncreaseType type;
	private BigInteger increase;
	
	public TotalMU(String name, String desc, BigInteger cost, Image img, List<Requirement> requires, IncreaseType type, BigInteger increase, Color color) {
		super(name, desc, cost, img, requires, color);
		this.type = type;
		this.increase = increase;
	}

	@Override
	public void onBuy() {		
		EventManager.registerListener(this);
	}
	
	@WatchEvent
	public void onIncreasedSmiths(IncreaseSmithEvent event) {
		switch(type) {
		case base_add:
			event.increase.add(increase);
			break;
		case multiply_add:
			event.increase = event.increase.multiply(increase);
			break;
		case percent_add:
			event.increase = MathUtils.addPercentGained(event.increase, increase);
			break;
		}
	}
	
}
