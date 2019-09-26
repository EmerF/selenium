package br.com.bb.client;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebClient {
	public static void main(String[] args) {
		String base = "/home/emerson/Documentos/";
		String oldName = "teste.txt";
		String newName = "testeNovo.txt";
		new WebClient().renameFile(base, oldName, newName);
	}

	WebDriver driver;

	public void start() {
		System.setProperty("webdriver.gecko.driver", "/home/emerson/Área de Trabalho/gecko/geckodriver");

		// Set proxy IP and port. Here PROXYHOST Is proxy IP and PORT Is Port number.
		// You can change both values as per your requirement.
		String PROXY = "localhost:8080";
		// Below given syntaxes will set browser proxy settings using
		// DesiredCapabilities.
		Proxy proxy = new Proxy();
		// String proxy1 [];
		// noProxy.add("google.com")
		// proxy.setNoProxy("bb.com.br");
		proxy.setAutodetect(false);
		proxy.setHttpProxy(PROXY);
		proxy.setProxyType(ProxyType.SYSTEM);
		// proxy.set
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.PROXY, proxy);
		// Use Capabilities when launch browser driver Instance.
		// driver = new ChromeDriver(cap);
		driver = new FirefoxDriver(cap);
		driver.get("http://www.google.com");

	}

	private void createChromeDriver() throws IOException {
		String PROXY = "localhost";
		int PORT = 8080;

		com.google.gson.JsonObject json = new com.google.gson.JsonObject();
		json.addProperty("proxyType", "MANUAL");
		json.addProperty("httpProxy", PROXY);
		json.addProperty("httpProxyPort", PORT);
		json.addProperty("sslProxy", PROXY);
		json.addProperty("sslProxyPort", PORT);

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("proxy", json);

		FirefoxBinary firefoxBinary = new FirefoxBinary();
		GeckoDriverService service = new GeckoDriverService.Builder(firefoxBinary)
				.usingDriverExecutable(new File("path to geckodriver")).usingAnyFreePort().usingAnyFreePort().build();
		service.start();

		// GeckoDriver currently needs the Proxy set in RequiredCapabilities
		// driver = new FirefoxDriver(service, cap, cap);
	}

	public void findFile() {
		try (Stream<Path> walk = Files.walk(Paths.get("/home/emerson/Documentos"))) {

			List<String> result = walk.map(x -> x.toString()).filter(f ->  f.contains("Darf IR.pdf"))
					.collect(Collectors.toList());

			result.forEach(System.out::println);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void renameFile(String baseDir, String oldName, String newName) {
		
		File f1 = new File(baseDir+ oldName);
		File f2 = new File(baseDir + newName);
		boolean b = f1.renameTo(f2);
		
	}
	
	

}
