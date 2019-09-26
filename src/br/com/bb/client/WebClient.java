package br.com.bb.client;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebClient {
	
	WebDriver driver;

	public void start() {
	System.setProperty("webdriver.gecko.driver","/home/emerson/Área de Trabalho/gecko/geckodriver");	
		
	//Set proxy IP and port. Here PROXYHOST Is proxy IP and PORT Is Port number.
	//You can change both values as per your requirement.
	String PROXY = "localhost:8080";
	//Below given syntaxes will set browser proxy settings using DesiredCapabilities.
	Proxy proxy = new Proxy();
	//String proxy1 [];
	//noProxy.add("google.com")
	//proxy.setNoProxy("bb.com.br");
	proxy.setHttpProxy(PROXY);
	proxy.setProxyType(ProxyType.SYSTEM);
	//proxy.set
	DesiredCapabilities cap = new DesiredCapabilities();
	cap.setCapability(CapabilityType.PROXY, proxy);
	//Use Capabilities when launch browser driver Instance.
	//driver = new ChromeDriver(cap); 
	driver = new FirefoxDriver(cap);
	driver.get("http://www.google.com");
	
	}
	
	private WebDriver createChromeDriver() {
	    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	    Map<String, String> prefs = new Hashtable<>();
	    prefs.put("download.prompt_for_download", "false");
	    prefs.put("download.default_directory", "C:\\Users\\openlmis\\Downloads");
	    String[] switches = { "--start-maximized", "--ignore-certificate-errors" };
	    capabilities.setJavascriptEnabled(true);
	    capabilities.setCapability("chrome.prefs", prefs);
	    capabilities.setCapability("chrome.switches", Arrays.asList(switches));
	    return new ChromeDriver(capabilities);
	}

}
