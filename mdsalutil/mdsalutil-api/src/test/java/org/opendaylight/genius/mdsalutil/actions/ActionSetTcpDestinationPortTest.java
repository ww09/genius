/*
 * Copyright © 2016, 2017 Red Hat, Inc. and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.genius.mdsalutil.actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import ch.vorburger.xtendbeans.XtendBeanGenerator;
import org.junit.Test;
import org.opendaylight.genius.mdsalutil.ActionInfo;
import org.opendaylight.genius.mdsalutil.ActionType;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.Action;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.SetFieldCase;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.match.layer._4.match.TcpMatch;

/**
 * Test class for {@link ActionSetTcpDestinationPort}.
 */
public class ActionSetTcpDestinationPortTest {
    private static final int PORT = 22;

    private XtendBeanGenerator generator = new XtendBeanGenerator();

    @Test
    public void actionInfoTest() {
        verifyAction(new ActionSetTcpDestinationPort(PORT).buildAction());
    }

    @Test
    public void backwardsCompatibleAction() {
        verifyAction(new ActionInfo(ActionType.set_tcp_destination_port,
            new String[] {Integer.toString(PORT)}).buildAction());
    }

    private void verifyAction(Action action) {
        assertTrue(action.getAction() instanceof SetFieldCase);
        SetFieldCase actionCase = (SetFieldCase) action.getAction();
        assertNotNull(actionCase.getSetField().getLayer4Match());
        assertTrue(actionCase.getSetField().getLayer4Match() instanceof TcpMatch);
        TcpMatch tcpMatch = (TcpMatch) actionCase.getSetField().getLayer4Match();
        assertEquals(PORT, tcpMatch.getTcpDestinationPort().getValue().intValue());
    }

    @Test
    public void generateAction() {
        ActionInfo actionInfo = new ActionSetTcpDestinationPort(PORT);
        assertEquals("new ActionSetTcpDestinationPort(0, " + PORT + ")", generator.getExpression(actionInfo));
    }
}
