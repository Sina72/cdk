/* $RCSfile$
 * $Author$
 * $Date$  
 * $Revision$
 *
 * Copyright (C) 1997-2004  The Chemistry Development Kit (CDK) project
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
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */
package org.openscience.cdk.io;

import java.io.IOException;

import org.openscience.cdk.ChemObject;
import org.openscience.cdk.io.listener.ChemObjectIOListener;
import org.openscience.cdk.io.setting.IOSetting;

/**
 * This class is the interface that all IO readers should implement.
 * Programs need only care about this interface for any kind of IO.
 * Currently, database IO and file IO is supported.
 *
 * <p>The easiest way to implement a new ChemObjectReader is to
 * subclass the DefaultChemObjectReader.
 *
 * @cdk.module io
 *
 * @see DefaultChemObjectReader
 *
 * @author Egon Willighagen <egonw@sci.kun.nl>
 **/
public interface ChemObjectIO {

    /**
     * Returns a one-lined format name of the format that this
     * IO filter supports.
     */
    public String getFormatName();
    
    /**
     * Returns whether the given ChemObject can be read or not.
     */
    public boolean accepts(ChemObject object);
    
    /**
     * Closes the Reader's resources.
     */
    public void close() throws IOException;

    /**
     * Returns an Array of IOSettings defined by this reader.
     */
    public IOSetting[] getIOSettings();
    
    /**
     * Adds a ChemObjectIOListener to this ChemObjectReader.
     *
     * @param listener the reader listener to add.
     */
    public void addChemObjectIOListener(ChemObjectIOListener listener);

    /**
     * Removes a ChemObjectIOListener from this ChemObjectReader.
     *
     * @param listener the reader listener to remove.
     */
    public void removeChemObjectIOListener(ChemObjectIOListener listener);
    
}

