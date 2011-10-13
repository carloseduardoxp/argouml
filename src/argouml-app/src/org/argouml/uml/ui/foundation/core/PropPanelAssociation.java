/* $Id: PropPanelAssociation.java 18588 2010-07-28 21:30:25Z bobtarling $
 *****************************************************************************
 * Copyright (c) 2009 Contributors - see below
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    tfmorris
 *****************************************************************************
 *
 * Some portions of this file was previously release using the BSD License:
 */

// Copyright (c) 1996-2007 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation without fee, and without a written
// agreement is hereby granted, provided that the above copyright notice
// and this paragraph appear in all copies. This software program and
// documentation are copyrighted by The Regents of the University of
// California. The software program and documentation are supplied "AS
// IS", without any accompanying services from The Regents. The Regents
// does not warrant that the operation of the program will be
// uninterrupted or error-free. The end-user understands that the program
// was developed for research purposes and is advised not to rely
// exclusively on the program for any reason. IN NO EVENT SHALL THE
// UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
// SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
// ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
// THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
// SUCH DAMAGE. THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
// PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
// CALIFORNIA HAS NO OBLIGATIONS TO PROVIDE MAINTENANCE, SUPPORT,
// UPDATES, ENHANCEMENTS, OR MODIFICATIONS.

package org.argouml.uml.ui.foundation.core;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.argouml.i18n.Translator;
import org.argouml.uml.ui.ActionNavigateContainerElement;
import org.argouml.uml.ui.UMLLinkedList;
import org.argouml.uml.ui.foundation.extension_mechanisms.ActionNewStereotype;

/**
 * The properties panel for a Association.
 * <p>
 * TODO: In UML 2.x an Association is both a Relationship and a Classifier, so
 * this inheritance hierarchy needs to be changed.
 * @deprecated in 0.31.2 by Bob Tarling  This is replaced by the XML property
 * panels module
 */
@Deprecated
public class PropPanelAssociation extends PropPanelRelationship {

    /**
     * The serial version.
     */
    private static final long serialVersionUID = 4272135235664638209L;

    /**
     * The scrollpane with the associationends.
     */
    private JScrollPane assocEndScroll;

    /**
     * The scrollpane with the associationroles this association plays a role
     * in.
     */
    private JScrollPane associationRoleScroll;

    /**
     * Ths scrollpane with the links that implement this association.
     */
    private JScrollPane linksScroll;

    /**
     * Panel for abstract/leaf/root
     */
    private JPanel modifiersPanel;

    /**
     * Construct a property panel for UML Association elements.
     */
    public PropPanelAssociation() {
        this("label.association");
        addField("label.name", getNameTextField());
        addField("label.namespace", getNamespaceSelector());
        add(modifiersPanel);

        addSeparator();

        addField("label.connections", assocEndScroll);

        addSeparator();

        addField("label.association-roles", associationRoleScroll);
        addField("label.association-links", linksScroll);

        addAction(new ActionNavigateContainerElement());
        addAction(new ActionNewStereotype());
        addAction(getDeleteAction());
    }


    /**
     * Construct a property panel for an Association.
     *
     * @param title the title of the panel
     */
    protected PropPanelAssociation(String title) {
        super(title, lookupIcon("Association"));
        initialize();
        JList assocEndList = new UMLLinkedList(
                new UMLAssociationConnectionListModel());
        assocEndScroll = new JScrollPane(assocEndList);
        JList baseList = new UMLLinkedList(
                new UMLAssociationAssociationRoleListModel());
        associationRoleScroll = new JScrollPane(baseList);
        JList linkList = new UMLLinkedList(new UMLAssociationLinkListModel());
        linksScroll = new JScrollPane(linkList);

        // TODO: implement the multiple inheritance of an Association
        // (Generalizable element)

    }

    private void initialize() {

        modifiersPanel = createBorderPanel(
                Translator.localize("label.modifiers"));
        modifiersPanel.add(new UMLGeneralizableElementAbstractCheckBox());
        modifiersPanel.add(new UMLGeneralizableElementLeafCheckBox());
        modifiersPanel.add(new UMLGeneralizableElementRootCheckBox());

    }

}