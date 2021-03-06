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
import java.util.Collections;
import java.util.List;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.WriteTransaction;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.genius.interfacemanager.renderer.hwvtep.utilities.SouthboundUtils;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev140508.interfaces.Interface;
import org.opendaylight.yang.gen.v1.urn.opendaylight.genius.interfacemanager.rev160406.IfTunnel;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ovsdb.hwvtep.rev150901.HwvtepPhysicalLocatorRef;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ovsdb.hwvtep.rev150901.hwvtep.physical._switch.attributes.Tunnels;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ovsdb.hwvtep.rev150901.hwvtep.physical._switch.attributes.TunnelsBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ovsdb.hwvtep.rev150901.hwvtep.physical._switch.attributes.TunnelsKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.ovsdb.hwvtep.rev150901.hwvtep.tunnel.attributes.BfdParams;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Node;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.node.TerminationPoint;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HwVTEPInterfaceConfigUpdateHelper {
    private static final Logger LOG = LoggerFactory.getLogger(HwVTEPInterfaceConfigUpdateHelper.class);

    public static List<ListenableFuture<Void>> updateConfiguration(DataBroker dataBroker,
            InstanceIdentifier<Node> physicalSwitchNodeId, InstanceIdentifier<Node> globalNodeId,
            Interface interfaceNew, IfTunnel ifTunnel) {
        LOG.info("updating hwvtep configuration for {}", interfaceNew.getName());

        // Create hwvtep through OVSDB plugin
        WriteTransaction transaction = dataBroker.newWriteOnlyTransaction();
        if (globalNodeId != null) {
            updateBfdMonitoring(dataBroker, globalNodeId, physicalSwitchNodeId, ifTunnel);
        } else {
            LOG.debug("specified physical switch is not connected {}", physicalSwitchNodeId);
        }
        return Collections.singletonList(transaction.submit());
    }

    /*
     * BFD monitoring interval and enable/disable attributes can be modified
     */
    public static List<ListenableFuture<Void>> updateBfdMonitoring(DataBroker dataBroker,
            InstanceIdentifier<Node> globalNodeId, InstanceIdentifier<Node> physicalSwitchId, IfTunnel ifTunnel) {
        TunnelsBuilder tunnelsBuilder = new TunnelsBuilder();
        InstanceIdentifier<TerminationPoint> localTEPInstanceIdentifier = SouthboundUtils
                .createTEPInstanceIdentifier(globalNodeId, ifTunnel.getTunnelSource());
        InstanceIdentifier<TerminationPoint> remoteTEPInstanceIdentifier = SouthboundUtils
                .createTEPInstanceIdentifier(globalNodeId, ifTunnel.getTunnelDestination());
        InstanceIdentifier<Tunnels> tunnelsInstanceIdentifier = SouthboundUtils.createTunnelsInstanceIdentifier(
                physicalSwitchId, localTEPInstanceIdentifier, remoteTEPInstanceIdentifier);

        LOG.debug("updating bfd monitoring parameters for the hwvtep {}", tunnelsInstanceIdentifier);
        tunnelsBuilder.setKey(new TunnelsKey(new HwvtepPhysicalLocatorRef(localTEPInstanceIdentifier),
                new HwvtepPhysicalLocatorRef(remoteTEPInstanceIdentifier)));
        List<BfdParams> bfdParams = new ArrayList<>();
        SouthboundUtils.fillBfdParameters(bfdParams, ifTunnel);
        tunnelsBuilder.setBfdParams(bfdParams);
        WriteTransaction transaction = dataBroker.newWriteOnlyTransaction();
        transaction.merge(LogicalDatastoreType.CONFIGURATION, tunnelsInstanceIdentifier, tunnelsBuilder.build(), true);
        return Collections.singletonList(transaction.submit());
    }
}
