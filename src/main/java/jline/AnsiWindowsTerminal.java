/*
 * Copyright (c) 2002-2016, the original author or authors.
 *
 * This software is distributable under the BSD license. See the terms of the
 * BSD license in the documentation provided with this software.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package jline;

import jline.internal.Configuration;
import org.fusesource.jansi.AnsiConsole;
import org.fusesource.jansi.AnsiOutputStream;
import org.fusesource.jansi.AnsiProcessor;

import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.OutputStream;

/**
 * ANSI-supported {@link WindowsTerminal}.
 *
 * @since 2.0
 */
public class AnsiWindowsTerminal
    extends WindowsTerminal
{
    private final boolean ansiSupported = detectAnsiSupport();
    private static class WindowsAnsiOutputStream extends AnsiOutputStream {
      WindowsAnsiOutputStream(OutputStream os) {
        super(os, new AnsiProcessor(os));
      }
    }

    @Override
    public OutputStream wrapOutIfNeeded(OutputStream out) {
        return wrapOutputStream(out);
    }

    /**
     * Returns an ansi output stream handler. We return whatever was
     * passed if we determine we cannot handle ansi based on Kernel32 calls.
     * 
     * @return an @{link AltWindowAnsiOutputStream} instance or the passed 
     * stream.
     */
    private static OutputStream wrapOutputStream(final OutputStream stream) {
        if (Configuration.isWindows()) {
            // On windows we know the console does not interpret ANSI codes..
            try {
                return new WindowsAnsiOutputStream(stream);
            } catch (Throwable ignore) {
                // this happens when JNA is not in the path.. or
                // this happens when the stdout is being redirected to a file.
            }
            // Use the ANSIOutputStream to strip out the ANSI escape sequences.
            return new AnsiOutputStream(stream, new AnsiProcessor(stream));
        }
        return stream;
    }


    private static boolean detectAnsiSupport() {
      try {
        OutputStream out = new WindowsAnsiOutputStream(new ByteArrayOutputStream());
        try {
            out.close();
            return true;
        }
        catch (Exception e) {
            // ignore;
        }
      } catch (Exception e) {
      }
      return false;
    }

    public AnsiWindowsTerminal() throws Exception {
        super();
    }

    @Override
    public boolean isAnsiSupported() {
        return ansiSupported;
    }

    @Override
    public boolean hasWeirdWrap() {
        return false;
    }
}
