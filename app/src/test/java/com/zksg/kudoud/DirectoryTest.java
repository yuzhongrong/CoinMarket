package com.zksg.kudoud;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


import com.zksg.kudoud.test.Directory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Directory.class)
public class DirectoryTest {

    @Test
    public void testCreate() throws Exception {
        String path = "mocked path";
        File mockFile = PowerMockito.mock(File.class);

        PowerMockito.whenNew(File.class).withArguments(path).thenReturn(mockFile);

        PowerMockito.when(mockFile.exists()).thenReturn(false);
        PowerMockito.when(mockFile.mkdirs()).thenReturn(true);

        assertTrue(new Directory().create(path));
    }
}
