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

        substance.setProperty("Chemical Substance", "nanomaterial");
        substance.setID("Fe3O4");

        // Fe2O3
        IAtomContainer molecule = new AtomContainer();
        Atom Fe1 = new Atom("Fe");
        Atom Fe2 = new Atom("Fe");
        Atom O3 = new Atom("O");
        Atom O4 = new Atom("O");
        Atom O5 = new Atom("O");
        molecule.addAtom(Fe1);
        molecule.addAtom(Fe2);
        molecule.addAtom(O3);
        molecule.addAtom(O4);
        molecule.addAtom(O5);
        Bond b1 = new Bond(O3, Fe1, IBond.Order.DOUBLE);
        Bond b2 = new Bond(Fe1, O4, IBond.Order.SINGLE);
        Bond b3 = new Bond(O4, Fe2, IBond.Order.SINGLE);
        Bond b4 = new Bond(Fe2, O5, IBond.Order.DOUBLE);
        molecule.addBond(b1);
        molecule.addBond(b2);
        molecule.addBond(b3);
        molecule.addBond(b4);
        
        molecule.setID("Fe2O3");
        
        substance.addAtomContainer(molecule);
        
        // FeO
        IAtomContainer moleculeTwo = new AtomContainer();
        Atom Fe = new Atom("Fe");
        Atom O = new Atom("O");
        moleculeTwo.addAtom(Fe);
        moleculeTwo.addAtom(O);
        Bond b_1 = new Bond(Fe, O, IBond.Order.DOUBLE);
        moleculeTwo.addBond(b_1);
        
        moleculeTwo.setID("FeO");
        
        substance.addAtomContainer(moleculeTwo);
        
        Convertor convertor = new Convertor(true, null);
        CMLSubstance convertedSubstance = convertor.cdkSubstanceToCMLSubstance(substance);
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        Serializer serializer = new Serializer(out, "UTF-8");
        
        serializer.write(new Document(convertedSubstance));
        
        out.close();
        
        String expected = "cmlx:npo=\"http://purl.bioontology.org/ontology/npo#\" dictRef=\"npo:NPO_1895\" id=\"Fe3O4";
        String actual = new String(out.toByteArray());
        
        Assert.assertTrue(actual.contains(expected));

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
