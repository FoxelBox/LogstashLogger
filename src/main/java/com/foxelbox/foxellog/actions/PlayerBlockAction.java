/**
 * This file is part of FoxelLog.
 *
 * FoxelLog is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FoxelLog is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FoxelLog.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.foxelbox.foxellog.actions;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;
import java.util.Map;

public class PlayerBlockAction extends PlayerAndLocationAction {
	private final Material block_from;
	private final Material block_to;

	public PlayerBlockAction(HumanEntity user, Location location, Material block_from, Material block_to) {
		super(user, location);
		this.block_from = block_from;
		this.block_to = block_to;
	}

    protected PlayerBlockAction(Map<String, Object> fields) {
        super(fields);
        this.block_from = Material.getMaterial((String)fields.get("block_from"));
        this.block_to = Material.getMaterial((String)fields.get("block_to"));
    }

    @Override
    protected Map<String, Map<String, Object>> getCustomMappings() throws IOException {
        Map<String, Map<String, Object>> retMap = super.getCustomMappings();

        retMap.put("block_from", builderBasicTypeMapping("string", "not_analyzed", null));
        retMap.put("block_to", builderBasicTypeMapping("string", "not_analyzed", null));

        return retMap;
    }

    @Override
    public String getType() {
        return "player_block_change";
    }

    public XContentBuilder toJSONObject(XContentBuilder builder) throws IOException {
        builder = super.toJSONObject(builder);

		builder.field("block_from", block_from.name());
		builder.field("block_to", block_to.name());

		return builder;
	}
}
