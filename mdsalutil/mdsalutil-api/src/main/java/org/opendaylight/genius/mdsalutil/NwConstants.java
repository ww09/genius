/*
 * Copyright (c) 2016 Ericsson India Global Services Pvt Ltd. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.genius.mdsalutil;

import java.math.BigInteger;

public final class NwConstants {

    // EthType Values
    public static final int ETHTYPE_802_1Q            = 0X8100;
    public static final int ETHTYPE_IPV4              = 0X0800;
    public static final int ETHTYPE_IPV6              = 0x86dd;
    public static final int ETHTYPE_ARP               = 0X0806;

    public static final int ETHTYPE_MPLS_UC           = 0X8847;
    public static final int ETHTYPE_PBB               = 0X88E7;

    public static final String MACADDR_SEP = ":";

    public static final String IPV4PREFIX = "/32";
    public static final String IPV6PREFIX = "/128";
    public static final String IPV4_SEP = ".";
    public static final String IPV6_SEP = ":";

    //Protocol Type
    public static final int IP_PROT_ICMP = 1;
    public static final int IP_PROT_TCP = 6;
    public static final int IP_PROT_UDP = 17;
    public static final int IP_PROT_GRE = 47;

    //ARP TYPE
    public static final int ARP_REQUEST = 1;
    public static final int ARP_REPLY = 2;

    //Default Port
    public static final int UDP_DEFAULT_PORT = 4789;


    // Flow Actions
    public static final int ADD_FLOW = 0;
    public static final int DEL_FLOW = 1;
    public static final int MOD_FLOW = 2;

    // Flow Constants
    public static final String FLOWID_SEPARATOR = ".";
    public static final int TABLE_MISS_FLOW = 0;
    public static final int TABLE_MISS_PRIORITY = 0;

    public static final int DEFAULT_ARP_FLOW_PRIORITY = 100;

    // Ingress (w.r.t switch) service indexes
    public static final short DEFAULT_SERVICE_INDEX = 0;
    public static final short SFC_SERVICE_INDEX = 1;
    public static final short SCF_SERVICE_INDEX = 1;
    public static final short ACL_SERVICE_INDEX = 2;
    public static final short INGRESS_COUNTERS_SERVICE_INDEX = 3;
    public static final short SFC_CLASSIFIER_INDEX = 4;
    public static final short DHCP_SERVICE_INDEX = 5;
    public static final short QOS_SERVICE_INDEX = 6;
    public static final short IPV6_SERVICE_INDEX = 7;
    public static final short L3VPN_SERVICE_INDEX = 8;
    public static final short ELAN_SERVICE_INDEX = 9;

    public static final String DHCP_SERVICE_NAME = "DHCP_SERVICE";
    public static final String ACL_SERVICE_NAME = "ACL_SERVICE";
    public static final String QOS_SERVICE_NAME = "QOS_SERVICE";
    public static final String IPV6_SERVICE_NAME = "IPV6_SERVICE";
    public static final String SCF_SERVICE_NAME = "SCF_SERVICE";
    public static final String SFC_SERVICE_NAME = "SFC_SERVICE";
    public static final String L3VPN_SERVICE_NAME = "L3VPN_SERVICE";
    public static final String ELAN_SERVICE_NAME = "ELAN_SERVICE";
    public static final String SFC_CLASSIFIER_SERVICE_NAME = "SFC_CLASSIFIER_SERVICE";
    public static final String DEFAULT_EGRESS_SERVICE_NAME = "DEFAULT_EGRESS_SERVICE";

    // Egress (w.r.t switch) service indexes
    public static final short EGRESS_ACL_SERVICE_INDEX = 6;
    public static final short EGRESS_POLICY_SERVICE_INDEX = 6;
    public static final short EGRESS_COUNTERS_SERVICE_INDEX = 7;
    public static final short EGRESS_SFC_CLASSIFIER_SERVICE_INDEX = 8;
    public static final short DEFAULT_EGRESS_SERVICE_INDEX = 9;

    public static final String EGRESS_ACL_SERVICE_NAME = "EGRESS_ACL_SERVICE";
    public static final String EGRESS_POLICY_SERVICE_NAME = "EGRESS_POLICY_SERVICE";
    public static final String EGRESS_SFC_CLASSIFIER_SERVICE_NAME = "EGRESS_SFC_CLASSIFIER_SERVICE";
    public static final String INGRESS_COUNTERS_SERVICE_NAME = "INGRESS_COUNTERS_SERVICE";
    public static final String EGRESS_COUNTERS_SERVICE_NAME = "EGRESS_COUNTERS_SERVICE";

    public static final BigInteger COOKIE_IPV6_TABLE = new BigInteger("4000000", 16);
    public static final BigInteger COOKIE_QOS_TABLE = new BigInteger("4000001", 16);
    public static final BigInteger VLAN_TABLE_COOKIE = new BigInteger("8000000", 16);
    public static final BigInteger COOKIE_VM_INGRESS_TABLE = new BigInteger("8000001", 16);
    public static final BigInteger COOKIE_VM_LFIB_TABLE = new BigInteger("8000002", 16);
    public static final BigInteger COOKIE_VM_FIB_TABLE =  new BigInteger("8000003", 16);
    public static final BigInteger COOKIE_DNAT_TABLE = new BigInteger("8000004", 16);
    public static final BigInteger COOKIE_TS_TABLE = new BigInteger("8000005", 16);
    public static final BigInteger COOKIE_SNAT_TABLE = new BigInteger("8000006", 16);
    public static final BigInteger EGRESS_DISPATCHER_TABLE_COOKIE = new BigInteger("8000007", 16);
    public static final BigInteger COOKIE_OUTBOUND_NAPT_TABLE = new BigInteger("8000008", 16);
    public static final BigInteger COOKIE_L3_GW_MAC_TABLE = new BigInteger("8000009", 16);
    public static final BigInteger EGRESS_POLICY_CLASSIFIER_COOKIE = new BigInteger("8000230", 16);
    public static final BigInteger EGRESS_POLICY_ROUTING_COOKIE = new BigInteger("8000231", 16);
    public static final BigInteger COOKIE_VXLAN_TRUNK_L2_TABLE = new BigInteger("1200000", 16);
    public static final BigInteger COOKIE_GRE_TRUNK_L2_TABLE = new BigInteger("1400000", 16);
    public static final BigInteger COOKIE_ELAN_INGRESS_TABLE = new BigInteger("8040000", 16);
    public static final BigInteger TUNNEL_TABLE_COOKIE = new BigInteger("9000000", 16);
    public static final BigInteger COOKIE_ARP_RESPONDER = new BigInteger("8220000", 16);

    //Table IDs
    public static final short VLAN_INTERFACE_INGRESS_TABLE = 0;
    public static final short VXLAN_TRUNK_INTERFACE_TABLE = 10;
    public static final short TRUNK_L2_TABLE = 11;
    public static final short GRE_TRUNK_INTERFACE_TABLE = 12;
    public static final short LPORT_DISPATCHER_TABLE = 17;
    public static final short DHCP_TABLE_EXTERNAL_TUNNEL = 18;
    public static final short L3_GW_MAC_TABLE = 19;
    public static final short L3_LFIB_TABLE = 20;
    public static final short L3_FIB_TABLE = 21;
    public static final short L3_SUBNET_ROUTE_TABLE = 22;
    public static final short L3VNI_EXTERNAL_TUNNEL_DEMUX_TABLE = 23;
    public static final short L2VNI_EXTERNAL_TUNNEL_DEMUX_TABLE = 24;
    public static final short PDNAT_TABLE = 25;
    public static final short PSNAT_TABLE = 26;
    public static final short DNAT_TABLE = 27;
    public static final short SNAT_TABLE = 28;
    public static final short INTERNAL_TUNNEL_TABLE = 36;
    public static final short EXTERNAL_TUNNEL_TABLE = 38;
    public static final short ARP_CHECK_TABLE = 43;
    public static final short INBOUND_NAPT_TABLE = 44;
    public static final short IPV6_TABLE = 45;
    public static final short OUTBOUND_NAPT_TABLE = 46;
    public static final short NAPT_PFIB_TABLE = 47;
    public static final short ELAN_BASE_TABLE = 48;
    public static final short ELAN_SMAC_LEARNED_TABLE = 49;
    public static final short ELAN_SMAC_TABLE = 50;
    public static final short ELAN_DMAC_TABLE = 51;
    public static final short ELAN_UNKNOWN_DMAC_TABLE = 52;
    public static final short ELAN_FILTER_EQUALS_TABLE = 55;
    public static final short DHCP_TABLE = 60;
    public static final short SCF_UP_SUB_FILTER_TCP_BASED_TABLE = 70;
    public static final short SCF_DOWN_SUB_FILTER_TCP_BASED_TABLE = 72;
    public static final short SCF_CHAIN_FWD_TABLE = 75;
    public static final short L3_INTERFACE_TABLE = 80;
    public static final short ARP_RESPONDER_TABLE = 81;
    public static final short SFC_TRANSPORT_CLASSIFIER_TABLE = 82;
    public static final short SFC_TRANSPORT_INGRESS_TABLE = 83;
    public static final short SFC_TRANSPORT_PATH_MAPPER_TABLE = 84;
    public static final short SFC_TRANSPORT_PATH_MAPPER_ACL_TABLE = 85;
    public static final short SFC_TRANSPORT_NEXT_HOP_TABLE = 86;
    public static final short SFC_TRANSPORT_EGRESS_TABLE = 87;
    public static final short QOS_DSCP_TABLE = 90;
    public static final short INGRESS_SFC_CLASSIFIER_FILTER_TABLE = 100;
    public static final short INGRESS_SFC_CLASSIFIER_ACL_TABLE = 101;
    public static final short INGRESS_ACL_TABLE = 211;
    public static final short INGRESS_ACL_REMOTE_ACL_TABLE = 212;
    public static final short INGRESS_ACL_FILTER_TABLE = 213;
    public static final short INGRESS_LEARN_TABLE = 212;
    public static final short INGRESS_LEARN_ACL_REMOTE_ACL_TABLE = 213;
    public static final short INGRESS_LEARN_ACL_FILTER_TABLE = 214;
    public static final short INGRESS_COUNTERS_TABLE = 219;
    public static final short EGRESS_LPORT_DISPATCHER_TABLE = 220;
    public static final short EGRESS_SFC_CLASSIFIER_FILTER_TABLE = 221;
    public static final short EGRESS_SFC_CLASSIFIER_NEXTHOP_TABLE = 222;
    public static final short EGRESS_SFC_CLASSIFIER_EGRESS_TABLE = 223;
    public static final short EGRESS_POLICY_CLASSIFIER_TABLE = 230;
    public static final short EGRESS_POLICY_ROUTING_TABLE = 231;
    public static final short EGRESS_ACL_TABLE = 241;
    public static final short EGRESS_ACL_REMOTE_ACL_TABLE = 242;
    public static final short EGRESS_ACL_FILTER_TABLE = 243;
    public static final short EGRESS_LEARN_TABLE = 242;
    public static final short EGRESS_LEARN_ACL_REMOTE_ACL_TABLE = 243;
    public static final short EGRESS_LEARN_ACL_FILTER_TABLE = 244;
    public static final short EGRESS_COUNTERS_TABLE = 249;

    public enum NxmOfFieldType {
        NXM_OF_IN_PORT(0x0000, 0, 2, 16),
        NXM_OF_ETH_DST(0x0000, 1, 6, 48),
        NXM_OF_ETH_SRC(0x0000, 2, 6, 48),
        NXM_OF_ETH_TYPE(0x0000, 3, 2, 16),
        NXM_OF_VLAN_TCI(0x0000, 4, 2, 12),
        NXM_OF_IP_TOS(0x0000, 5, 1, 8),
        NXM_OF_IP_PROTO(0x0000, 6, 1, 8),
        NXM_OF_IP_SRC(0x0000, 7, 4, 32),
        NXM_OF_IP_DST(0x0000, 8, 4, 32),
        NXM_OF_TCP_SRC(0x0000, 9, 2, 16),
        NXM_OF_TCP_DST(0x0000, 10, 2, 16),
        NXM_OF_UDP_SRC(0x0000, 11, 2, 16),
        NXM_OF_UDP_DST(0x0000, 12, 2, 16),
        NXM_OF_ICMP_TYPE(0x0000, 13, 1, 8),
        NXM_OF_ICMP_CODE(0x0000, 14, 1, 8),
        NXM_OF_ARP_OP(0x0000, 15, 2, 16),
        NXM_OF_ARP_SPA(0x0000, 16, 4, 16),
        NXM_OF_ARP_TPA(0x0000, 17, 4, 16),
        NXM_NX_REG0(0x0001, 0, 4, -1),
        NXM_NX_REG1(0x0001, 1, 4, -1),
        NXM_NX_REG2(0x0001, 2, 4, -1),
        NXM_NX_REG3(0x0001, 3, 4, -1),
        NXM_NX_REG4(0x0001, 4, 4, -1),
        NXM_NX_REG5(0x0001, 5, 4, -1),
        NXM_NX_REG6(0x0001, 6, 4, -1),
        NXM_NX_REG7(0x0001, 7, 4, -1);

        long type;
        int flowModHeaderLen;

        NxmOfFieldType(long vendor, long field, long length, int flowModHeaderLen) {
            type = nxmHeader(vendor, field, length);
            this.flowModHeaderLen = flowModHeaderLen;
        }

        private static long nxmHeader(long vendor, long field, long length) {
            return vendor << 16 | field << 9 | length;
        }

        @Deprecated
        public String getHexType() {
            return String.valueOf(type);
        }

        public long getType() {
            return type;
        }

        @Deprecated
        public String getFlowModHeaderLen() {
            return String.valueOf(flowModHeaderLen);
        }

        public int getFlowModHeaderLenInt() {
            return flowModHeaderLen;
        }
    }
}
