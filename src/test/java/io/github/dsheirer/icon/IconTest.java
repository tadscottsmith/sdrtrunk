/*
 * *****************************************************************************
 * Copyright (C) 2014-2026 Dennis Sheirer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 * ****************************************************************************
 */
package io.github.dsheirer.icon;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class IconTest
{
    @BeforeAll
    public static void startToolkit() throws Exception
    {
        System.setProperty("glass.platform", "Headless");
        System.setProperty("prism.order", "sw");
        CompletableFuture<Void> started = new CompletableFuture<>();

        try
        {
            Platform.startup(() -> started.complete(null));
        }
        catch(IllegalStateException e)
        {
            started.complete(null);
        }

        started.get(10, TimeUnit.SECONDS);
    }

    @Test
    public void loadsBundledIconWithoutContextClassLoader()
    {
        Thread thread = Thread.currentThread();
        ClassLoader original = thread.getContextClassLoader();

        try
        {
            thread.setContextClassLoader(null);
            Icon icon = new Icon("No Icon", "images/no_icon.png");
            Image image = icon.getFxImage();
            assertNotNull(image);
            assertFalse(image.isError());
            assertSame(image, icon.getFxImage());
        }
        finally
        {
            thread.setContextClassLoader(original);
        }
    }

    @Test
    public void missingBundledIconReturnsNull()
    {
        Icon icon = new Icon("Missing", "images/nonexistent-icon.png");
        assertNull(icon.getFxImage());
        assertNull(icon.getFxImage());
    }
}
