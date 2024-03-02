/*  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *   programm:                  PearHarmony TerminalClient
 *   function:                  A simple pear-to-pear terminal massager compatible with other PearHarmony clients
 *   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *   author:                    C. Prickartz (UI + Control + Dataflow) & V. Tica (Network)
 *   edit:                      -
 *   created:                   2024-02-28
 *   last changed:              JJJJ-MM-DD
 *   development environment:   Windows 11 version 23H2, IntelliJ IDEA 2023.3.4 (Ultimate Edition), Amazon Corretto 17.0.10
 *   build system:              Apache Maven - version 3.9.5
 *   additional library's:      Lanterna version 3.1.1 by mabe02 - https://github.com/mabe02/lanterna
 *
 *   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *   testing environment:       Ubuntu version 22.04.4 LTS, OpenJDK 17.0.10
 *                              Windows Server 2012 R2 Standard - Oracle Java 21.0.2
 *
 *   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *   version:                   1.0
 *   License:                   LGPL-2.1 License - Copyright (c) 2024 PearHarmony
 *   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package org.pearharmony;

import org.pearharmony.control.Control;

public class Main {
    public static void main(String[] args) {
        new Control();
    } // start client
}