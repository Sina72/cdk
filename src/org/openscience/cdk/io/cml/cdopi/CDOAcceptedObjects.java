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
package org.openscience.cdk.io.cml.cdopi;

import java.util.Vector;
import java.util.Enumeration;

/**
 * List of names (String classes) of objects accepted by CDO.
 *
 * @cdk.module io
 *
 * @author Egon Willighagen <egonw@sci.kun.nl>
 */
public class CDOAcceptedObjects {

  private Vector objects;

  /**
   * Constructor.
   */
  public CDOAcceptedObjects () {
    objects = new Vector();
  }

  /**
   * Adds the name of an accepted object.
   *
   * @param object Name of the object
   */
  public void add(String object) {
    objects.addElement(object);
  }

  /**
   * Determine if an object name is contained in this list.
   *
   * @param   object Name of the object to search in the list
   * @return         true if the object is in the list, false otherwise
   */
  public boolean contains(String object) {
    return objects.contains(object);
  }

  /**
   * Returns the names in this list as a Enumeration class. Each element in the
   * Enumeration is of type String.
   *
   * @return The names of the accepted objects
   */
  public Enumeration elements() {
    return objects.elements();
  }
}
