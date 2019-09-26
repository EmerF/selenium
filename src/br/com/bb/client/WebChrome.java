package br.com.bb.client;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebChrome {

    private String baseUrl;
    private String driverPath;
    private String driverName;
    private static WebDriver driver;
    private static WebChrome driverClass;

    public void start() {
        try {
            baseUrl = "http://google.com";
            driverPath = "/home/emerson/Área de Trabalho/gecko/geckodriver";
            System.setProperty("webdriver.chrome.driver","/home/emerson/Área de Trabalho/gecko/chromedriver");	
            driverName = "webdriver.chrome.driver";
            System.setProperty(driverName, driverPath);

            Proxy proxy = new org.openqa.selenium.Proxy();
            proxy.setSslProxy("localhost" + ":" + 8080);
            proxy.setFtpProxy("localhost" + ":" + 8080);
            proxy.setSocksUsername("avishka");
            proxy.setSocksPassword("12345678");

            DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
            desiredCapabilities.setCapability(CapabilityType.PROXY, proxy);


            driver = new ChromeDriver(desiredCapabilities);


            driver.get(baseUrl);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
