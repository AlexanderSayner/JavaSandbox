package logTests;

import org.junit.Test;
import sayner.sandbox.log.LogbackConfigXml;

public class LogbackConfigXmlTest {

    @Test
    public void testPerformTask() throws Exception {

        System.out.println("Сработал тест");
        LogbackConfigXml logbackConfigXml = new LogbackConfigXml();
        logbackConfigXml.performTask();
    }

}