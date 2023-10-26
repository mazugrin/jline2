/*
 * Copyright (c) 2002-2016, the original author or authors.
 *
 * This software is distributable under the BSD license. See the terms of the
 * BSD license in the documentation provided with this software.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package jline.internal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import org.fusesource.jansi.AnsiMode;
import org.fusesource.jansi.AnsiColors;
import org.fusesource.jansi.AnsiConsole;
import org.fusesource.jansi.AnsiType;
import org.fusesource.jansi.io.AnsiOutputStream;
import org.fusesource.jansi.io.AnsiProcessor;

/**
 * Ansi support.
 *
 * @author <a href="mailto:gnodet@gmail.com">Guillaume Nodet</a>
 * @since 2.13
 */
public class Ansi {

    public static String stripAnsi(String str) {
        if (str == null) return "";
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            AnsiOutputStream aos =
                new AnsiOutputStream(
                    baos,
                    new AnsiOutputStream.ZeroWidthSupplier(),
                    AnsiMode.Strip,
                    new AnsiProcessor(baos),
                    AnsiType.Emulation,
                    AnsiColors.TrueColor,
                    Charset.defaultCharset(),
                    null,
                    null,
                    false
                );
            aos.write(str.getBytes());
            aos.close();
            return baos.toString();
        } catch (IOException e) {
            return str;
        }
    }

}
