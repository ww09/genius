/*
 * Copyright (c) 2016 Ericsson India Global Services Pvt Ltd. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.genius.fcapsapp;

import com.google.common.base.Preconditions;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.DataTreeIdentifier;
import org.opendaylight.controller.md.sal.binding.api.NotificationService;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.genius.fcapsapp.performancecounter.PacketInCounterHandler;
import org.opendaylight.genius.fcapsapp.portinfo.PortNameMapping;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.FlowCapableNode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.Nodes;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FcapsProvider implements AutoCloseable {

    public static Logger s_logger = LoggerFactory.getLogger(FcapsProvider.class);
    private final DataBroker dataBroker;
    private final NotificationService notificationService;
    private final PacketInCounterHandler packetInCounterHandler;
    private final NodeEventListener<FlowCapableNode> nodeEventListener;

    /**
     * Contructor sets the services
     * @param dataBroker instance of databroker
     * @param notificationService instance of notificationservice
     * @param packetInCounterHandler instance of PacketInCounterHandler
     * @param nodeEventListener instance of NodeEventListener
     */
    @Inject
    public FcapsProvider(final DataBroker dataBroker,
                         final NotificationService notificationService,
                         final PacketInCounterHandler packetInCounterHandler,
                         final NodeEventListener nodeEventListener){
        this.dataBroker = Preconditions.checkNotNull(dataBroker, "DataBroker can not be null!");
        s_logger.info("FcapsProvider dataBroker is set");

        this.notificationService = Preconditions.checkNotNull(notificationService, "notificationService can not be null!");
        s_logger.info("FcapsProvider notificationProviderService is set");

        this.packetInCounterHandler = packetInCounterHandler;
        this.nodeEventListener = nodeEventListener;
    }

    @PostConstruct
    public void start() throws Exception {
        PortNameMapping.registerPortMappingBean();
        registerListener(dataBroker);
        notificationService.registerNotificationListener(packetInCounterHandler);
        s_logger.info("FcapsProvider started");
    }

    @PreDestroy
    @Override
    public void close() throws Exception {
        s_logger.info("FcapsProvider closed");
    }

    private void registerListener(DataBroker dataBroker) {
        final DataTreeIdentifier<FlowCapableNode> treeId =
                new DataTreeIdentifier<>(LogicalDatastoreType.OPERATIONAL, getWildCardPath());
        try {
            dataBroker.registerDataTreeChangeListener(treeId, nodeEventListener);
        } catch (Exception e) {
            s_logger.error("Registeration failed on DataTreeChangeListener {}",e);
        }
    }

    private InstanceIdentifier<FlowCapableNode> getWildCardPath() {
        return InstanceIdentifier.create(Nodes.class)
                .child(Node.class)
                .augmentation(FlowCapableNode.class);
    }

}
