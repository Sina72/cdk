/* $RCSfile$
 * $Author$    
 * $Date$    
 * $Revision$
 * 
 * Copyright (C) 2004  The Chemistry Development Kit (CDK) project
 * 
 * Contact: cdk-devel@lists.sourceforge.net
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA. 
 * 
 */
package org.openscience.cdk.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.openscience.cdk.AtomContainer;
import org.openscience.cdk.SetOfAtomContainers;

/**
 * Checks the funcitonality of the SetOfMolecules class.
 *
 * @cdk.module test
 *
 * @see org.openscience.cdk.SetOfMolecules
 */
public class SetOfAtomContainersTest extends TestCase {

    public SetOfAtomContainersTest(String name) {
        super(name);
    }

    public void setUp() {}

    public static Test suite() {
        return new TestSuite(SetOfAtomContainersTest.class);
    }
    
    public void testGetAtomContainerCount() {
        SetOfAtomContainers som = new SetOfAtomContainers();
        som.addAtomContainer(new AtomContainer());
        som.addAtomContainer(new AtomContainer());
        som.addAtomContainer(new AtomContainer());
        
        assertEquals(3, som.getAtomContainerCount());
    }
    
    public void testGetAtomContainer() {
        SetOfAtomContainers som = new SetOfAtomContainers();
        som.addAtomContainer(new AtomContainer());
        som.addAtomContainer(new AtomContainer());
        som.addAtomContainer(new AtomContainer());

        assertNotNull(som.getAtomContainer(2)); // third molecule should exist
        assertNull(som.getAtomContainer(3)); // fourth molecule must not exist
    }
    
    public void testGetAtomContainerMultiplier() {
        SetOfAtomContainers som = new SetOfAtomContainers();
        som.addAtomContainer(new AtomContainer());

        assertEquals(1.0, som.getAtomContainerMultiplier(0), 0.00001);
    }
    
    public void testGetAtomContainerMultiplier2() {
        SetOfAtomContainers som = new SetOfAtomContainers();
        som.addAtomContainer(new AtomContainer());

        assertEquals(-1.0, som.getAtomContainerMultiplier(new AtomContainer()), 0.00001);
    }
    
    public void testAddAtomContainer() {
        SetOfAtomContainers som = new SetOfAtomContainers();
        som.addAtomContainer(new AtomContainer());
        som.addAtomContainer(new AtomContainer());
        som.addAtomContainer(new AtomContainer());
        som.addAtomContainer(new AtomContainer());
        som.addAtomContainer(new AtomContainer());

        assertEquals(5, som.getAtomContainerCount());
        
        // now test it to make sure it properly grows the array
        som.addAtomContainer(new AtomContainer());
        som.addAtomContainer(new AtomContainer());

        assertEquals(7, som.getAtomContainerCount());        
    }
    
    public void testAddAtomContainer_AtomContainer_Multiplier() {
        SetOfAtomContainers som = new SetOfAtomContainers();
        som.addAtomContainer(new AtomContainer(), 2.0);
        assertEquals(1, som.getAtomContainerCount());
        assertEquals(2.0, som.getAtomContainerMultiplier(0), 0.00001);
    }
    
    public void testGrowAtomContainerArray() {
        // this test assumes that the growSize = 5 !
        // if not, there is need for the array to grow
        SetOfAtomContainers som = new SetOfAtomContainers();
        
        som.addAtomContainer(new AtomContainer());
        som.addAtomContainer(new AtomContainer());
        som.addAtomContainer(new AtomContainer());
        som.addAtomContainer(new AtomContainer());
        som.addAtomContainer(new AtomContainer());
        som.addAtomContainer(new AtomContainer());
        som.addAtomContainer(new AtomContainer());

        AtomContainer[] mols = som.getAtomContainers();
        assertEquals(7, mols.length);
    }
    
    public void testGetAtomContainers() {
        SetOfAtomContainers som = new SetOfAtomContainers();
        
        AtomContainer[] mols = som.getAtomContainers();
        assertEquals(0, mols.length);
        
        som.addAtomContainer(new AtomContainer());
        som.addAtomContainer(new AtomContainer());
        som.addAtomContainer(new AtomContainer());

        mols = som.getAtomContainers();
        assertEquals(3, mols.length);
        assertNotNull(mols[0]);
        assertNotNull(mols[1]);
        assertNotNull(mols[2]);
    }
}
