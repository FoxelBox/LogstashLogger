package de.doridian.logstashlogger.actions;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

public abstract class LocationAction extends BaseAction {
	private final Location location;

	public LocationAction(Player user, String action, Location location) {
		super(user, action);
		this.location = location;
	}

	@Override
	public JSONObject toJSONObject() {
		final JSONObject thisBlockChange = new JSONObject();
		thisBlockChange.put("x", location.getX());
		thisBlockChange.put("y", location.getY());
		thisBlockChange.put("z", location.getZ());
		thisBlockChange.put("world", location.getWorld().getName());
		return thisBlockChange;
	}
}