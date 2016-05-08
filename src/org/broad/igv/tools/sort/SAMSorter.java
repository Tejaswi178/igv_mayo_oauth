/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2007-2015 Broad Institute
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.broad.igv.tools.sort;

import htsjdk.tribble.readers.AsciiLineReader;
import org.broad.igv.Globals;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;

/**
 * @author jrobinso
 */
public class SAMSorter extends Sorter {

    public SAMSorter(File inputFile, File outputFile) {
        super(inputFile, outputFile);
    }

    @Override
    Parser getParser() {
        return new Parser(2, 3);
    }

    @Override
    String writeHeader(AsciiLineReader reader, PrintWriter writer) throws IOException {
        String nextLine = reader.readLine();
        while (nextLine != null && nextLine.startsWith("@")) {
            writer.println(nextLine);
            nextLine = reader.readLine();
        }

        // First alignment row
        return nextLine;
    }


    public static Comparator<SortableRecord> ReadNameComparator = new Comparator<SortableRecord>() {

        public int compare(SortableRecord o1, SortableRecord o2) {
            String[] t1 = Globals.tabPattern.split(o1.getText());
            String[] t2 = Globals.tabPattern.split(o2.getText());
            return t1[0].compareTo(t2[0]);

        }
    };
}
