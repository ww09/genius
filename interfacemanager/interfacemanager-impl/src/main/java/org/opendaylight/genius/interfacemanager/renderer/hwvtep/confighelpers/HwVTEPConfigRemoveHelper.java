/*
 * Copyright (c) 2016, 2017 Ericsson India Global Services Pvt Ltd. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.genius.interfacemanager.renderer.hwvtep.confighelpers;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.WriteTransaction;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.genius.interfacemanager.commons.InterfaceManagerCommonUtils;
import org.opendaylight.genius.interfacemanager.commons.InterfaceMetaUtils;
import org.opendaylight.genius.interfacemanager.renderer.hwvtep.utilities.SouthboundUtils;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev140508.interfaces.Interface;
import org.opendaylight.yang.gen.v1.urn.opendaylight.genius.interfacemanager.rev160406.IfTunnel;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Node;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.node.TerminationPoint;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.node.TerminationPointKey;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HwVTEPConfigRemoveHelper {
    private static final Logger LOG = LoggerFactory.getLogger(HwVTEPConfigRemoveHelper.class);

    public static List<ListenableFuture<Void>> removeConfiguration(DataBroker dataBroker, Interface interfaceOld,
            InstanceIdentifier<Node> globalNodeId, InstanceIdentifier<Node> physicalSwitchNodeId) {
        List<ListenableFuture<Void>> futures = new ArrayList<>();
        WriteTransaction defaultOperShardTransaction = dataBroker.newWriteOnlyTransaction();
        WriteTransaction topologyConfigShardTransaction = dataBroker.newWriteOnlyTransaction();
        LOG.info("removing hwvtep configuration for {}", interfaceOld.getName());
        if (globalNodeId != null) {
            IfTunnel ifTunnel = interfaceOld.getAugmentation(IfTunnel.class);
            //removeTunnelTableEntry(defaultOperShardTransaction, ifTunnel, physicalSwitchNodeId);
            removeTerminationEndPoint(topologyConfigShardTransaction, ifTunnel, globalNodeId);
            InterfaceManagerCommonUtils.deleteStateEntry(interfaceOld.getName(), defaultOperShardTransaction);
            InterfaceMetaUtils.removeTunnelToInterfaceMap(physicalSwitchNodeId, defaultOperShardTransaction, ifTunnel);
        }
        futures.add(defaultOperShardTransaction.submit());
        futures.add(topologyConfigShardTransaction.submit());
        return futures;
    }

    private static void removeTerminationEndPoint(WriteTransaction transaction, IfTunnel ifTunnel,
            InstanceIdentifier<Node> globalNodeId) {
        LOG.info("removing remote termination end point {}", ifTunnel.getTunnelDestination());
        TerminationPointKey tpKey = SouthboundUtils
                .getTerminationPointKey(ifTunnel.getTunnelDestination().getIpv4Address().getValue());
        InstanceIdentifier<TerminationPoint> tpPath = SouthboundUtils.createInstanceIdentifier(globalNodeId, tpKey);
        transaction.delete(LogicalDatastoreType.CONFIGURATION, tpPath);
    }
}
