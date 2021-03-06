/**
 * Copyright (c) 2014-2015 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.core.thing.dto;

import org.eclipse.smarthome.config.core.Configuration;
import org.eclipse.smarthome.core.thing.Channel;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.binding.builder.ChannelBuilder;
import org.eclipse.smarthome.core.thing.type.ChannelTypeUID;

/**
 * The {@link ChannelDTOMapper} is an utility class to map channels into channel data transfer objects (DTOs).
 *
 * @author Stefan Bußweiler - Initial contribution
 * @author Kai Kreuzer - added DTO to channel mapping
 */
public class ChannelDTOMapper {

    /**
     * Maps channel into channel DTO object.
     *
     * @param channel the channel
     * @return the channel DTO object
     */
    public static ChannelDTO map(Channel channel) {
        ChannelTypeUID channelTypeUID = channel.getChannelTypeUID();
        String channelTypeUIDValue = channelTypeUID != null ? channelTypeUID.toString() : null;
        return new ChannelDTO(channel.getUID(), channelTypeUIDValue, channel.getAcceptedItemType().toString(),
                channel.getLabel(), channel.getDescription(), channel.getProperties(), channel.getConfiguration(),
                channel.getDefaultTags());
    }

    /**
     * Maps channel DTO into channel object.
     *
     * @param channelDTO the channel DTO
     * @return the channel object
     */
    public static Channel map(ChannelDTO channelDTO) {
        ChannelUID channelUID = new ChannelUID(channelDTO.uid);
        ChannelTypeUID channelTypeUID = new ChannelTypeUID(channelDTO.channelTypeUID);
        return ChannelBuilder.create(channelUID, channelDTO.itemType)
                .withConfiguration(new Configuration(channelDTO.configuration)).withLabel(channelDTO.label)
                .withDescription(channelDTO.description).withProperties(channelDTO.properties).withType(channelTypeUID)
                .withDefaultTags(channelDTO.defaultTags).build();
    }
}
