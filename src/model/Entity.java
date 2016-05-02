/**
 * @author cvandera
 *
 */

package model;

import org.json.JSONObject;

public abstract class Entity {

	public abstract JSONObject toJson();
	public abstract void fromJson(JSONObject json);
	
	@Override
	public String toString() {
		return toJson().toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != this.getClass()) return false;
		return this.toString().equals(obj.toString());
	}
}
