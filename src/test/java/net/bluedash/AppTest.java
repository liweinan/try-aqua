package net.bluedash;

import junit.framework.TestCase;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(Arquillian.class)
public class AppTest extends TestCase {
    @Inject
    private TemperatureConverter converter;

    @Deployment
    public static Archive<?> deployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(TemperatureConverter.class)
                .addAsManifestResource(
                        new ByteArrayAsset("<beans/>".getBytes()),
                        ArchivePaths.create("beans.xml"));
    }

    @Test
    public void testConvertToCelsius() {
        assertEquals(converter.convertToCelsius(32d), 0d);
        assertEquals(converter.convertToCelsius(212d), 100d);
    }

    @Test
    public void testConvertToFarenheit() {
        assertEquals(converter.convertToFarenheit(0d), 32d);
        assertEquals(converter.convertToFarenheit(100d), 212d);
    }

}
