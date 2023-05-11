package com.zksg.kudoud;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

import android.os.Handler;

import com.zksg.kudoud.test.CommonExample;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.util.concurrent.Executor;

@PrepareForTest({CommonExample.class,File.class})
public class CommonExampleTest {

    @Test
    public void testCallArgumentInstance() {
        //参数是file对象的模拟过程
        File file = PowerMockito.mock(File.class);
        //期望行为是true
        PowerMockito.when(file.exists()).thenReturn(true);

        //实际运行结果
        CommonExample commonExample = new CommonExample();
        Assert.assertTrue(commonExample.callArgumentInstance(file));
    }

    @Test
    public void callCallArgumentInstance2() throws Exception {
        File file = PowerMockito.mock(File.class);
        PowerMockito.whenNew(File.class).withArguments( anyString()).thenReturn(file);
        PowerMockito.when(file.exists()).thenReturn(true);
        CommonExample commonExample = new CommonExample();
        Assert.assertFalse(commonExample.callArgumentInstance( anyString()));

    }







}
