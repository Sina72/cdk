/* Copyright (C) 2007  Stefan Kuhn <shk3@users.sf.net>
 *
 * Contact: cdk-devel@lists.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * All we ask is that proper credit is given for our work, which includes
 * - but is not limited to - adding the above copyright notice to the beginning
 * of your source code files, and to any copyright notice that you may distribute
 * with programs based on this work.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 */
package org.openscience.cdk.libio.cml;

import nu.xom.Document;
import nu.xom.Serializer;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openscience.cdk.Atom;
import org.openscience.cdk.AtomContainer;
import org.openscience.cdk.Bond;
import org.openscience.cdk.CDKTestCase;
import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IChemObjectBuilder;
import org.openscience.cdk.interfaces.ISubstance;
import org.xmlcml.cml.element.CMLBond;
import org.xmlcml.cml.element.CMLMolecule;
import org.xmlcml.cml.element.CMLSubstance;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @cdk.module test-libiocml
 */
public class ConvertorTest extends CDKTestCase {

    /**
     * @cdk.bug 1748257
     */
    @Ignore("moved to MDMoleculeTest")
    public void testBug1748257() {}

    @Test
    public void testCdkBondToCMLBond_Wedge() throws IOException {

        IChemObjectBuilder builder = DefaultChemObjectBuilder.getInstance();
        IBond bond = builder.newInstance(IBond.class);
        bond.setOrder(IBond.Order.SINGLE);
        bond.setStereo(IBond.Stereo.UP);

        Convertor convertor = new Convertor(true, null);
        CMLBond cmlBond = convertor.cdkBondToCMLBond(bond);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Serializer serializer = new Serializer(out, "UTF-8");

        serializer.write(new Document(cmlBond));

        out.close();

        String expected = "<bondStereo dictRef=\"cml:W\">W</bondStereo>";
        String actual = new String(out.toByteArray());

        Assert.assertTrue(actual.contains(expected));

    }
    
    @Test
    public void testCdkSubstanceToCMLSubstance() throws IOException {

        IChemObjectBuilder builder = DefaultChemObjectBuilder.getInstance();
        ISubstance substance = builder.newInstance(ISubstance.class);
        
      //SO3
        IAtomContainer molecule = new AtomContainer();
        Atom S1 = new Atom("S");
        Atom O2 = new Atom("O");
        Atom O3 = new Atom("O");
        Atom O4 = new Atom("O");
        molecule.addAtom(S1);
        molecule.addAtom(O2);
        molecule.addAtom(O3);
        molecule.addAtom(O4);
        Bond b1 = new Bond(S1, O2, IBond.Order.DOUBLE);
        Bond b2 = new Bond(S1, O3, IBond.Order.DOUBLE);
        Bond b3 = new Bond(S1, O4, IBond.Order.DOUBLE);
        molecule.addBond(b1);
        molecule.addBond(b2);
        molecule.addBond(b3);
        
        molecule.setID("ID1");
        
        substance.addAtomContainer(molecule); 
        
      //XeF4
        IAtomContainer moleculeTwo = new AtomContainer();
        Atom Xe1 = new Atom("Xe");
        Atom F2 = new Atom("F");
        Atom F3 = new Atom("F");
        Atom F4 = new Atom("F");
        Atom F5 = new Atom("F");
        moleculeTwo.addAtom(Xe1);
        moleculeTwo.addAtom(F2);
        moleculeTwo.addAtom(F3);
        moleculeTwo.addAtom(F4);
        moleculeTwo.addAtom(F5);
        Bond b_1 = new Bond(Xe1, F2, IBond.Order.SINGLE);
        Bond b_2 = new Bond(Xe1, F3, IBond.Order.SINGLE);
        Bond b_3 = new Bond(Xe1, F4, IBond.Order.SINGLE);
        Bond b_4 = new Bond(Xe1, F5, IBond.Order.SINGLE);
        moleculeTwo.addBond(b_1);
        moleculeTwo.addBond(b_2);
        moleculeTwo.addBond(b_3);
        moleculeTwo.addBond(b_4);
        
        moleculeTwo.setID("ID2");
        
        substance.addAtomContainer(moleculeTwo);
        
        Convertor convertor = new Convertor(true, null);
        CMLSubstance convertedSubstance = convertor.cdkSubstanceToCMLSubstance(substance);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Serializer serializer = new Serializer(out, "UTF-8");

        serializer.write(new Document(convertedSubstance));

        System.out.println(out);
        
        out.close();

        

    }

    @Test
    public void testCdkBondToCMLBond_Hatch() throws IOException {

        IChemObjectBuilder builder = DefaultChemObjectBuilder.getInstance();
        IBond bond = builder.newInstance(IBond.class);
        bond.setOrder(IBond.Order.SINGLE);
        bond.setStereo(IBond.Stereo.DOWN);

        Convertor convertor = new Convertor(true, null);
        CMLBond cmlBond = convertor.cdkBondToCMLBond(bond);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Serializer serializer = new Serializer(out, "UTF-8");

        serializer.write(new Document(cmlBond));

        out.close();

        String expected = "<bondStereo dictRef=\"cml:H\">H</bondStereo>";
        String actual = new String(out.toByteArray());

        Assert.assertTrue(actual.contains(expected));

    }

}
