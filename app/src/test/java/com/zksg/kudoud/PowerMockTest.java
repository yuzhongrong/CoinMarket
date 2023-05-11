package com.zksg.kudoud;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import com.zksg.kudoud.test.Hello;
import com.zksg.kudoud.test.PowerMockSample;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Hello.class)
public class PowerMockTest {

    @Test
    public void testGetValue() {
        PowerMockito.mockStatic(Hello.class);

        //当调用getValue1 getValue2 我们期望的结果 555 和 666
        Mockito.when(Hello.getValue1()).thenReturn(555);
        Mockito.when(Hello.getValue2()).thenReturn(666);

        assertEquals(555, Hello.getValue1());
        assertEquals(666, Hello.getValue2());
    }


    @Test
    public void testDoSomethingIfStateReady() throws Exception {
        PowerMockSample sample =PowerMockito.mock(PowerMockSample.class);

        Whitebox.setInternalState(sample, "mState", 1);

        Assert.assertTrue(sample.doSomethingIfStateReady());
    }


}
