package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import org.openqa.selenium.remote.RemoteWebDriver;


public class BaseClass {

    private static final Logger logger = LogManager.getLogger(BaseClass.class);
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public Properties p;

    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeClass(groups = {"smoke", "regression", "datadriven"})
    @Parameters({ "os", "browser" })
    public void openApp(String os, String br) throws IOException {
        try {
            logger.debug("Loading configuration properties...");
            FileReader file = new FileReader(".//src//test//resources//config.properties");
            p = new Properties();
            p.load(file);

            WebDriver localDriver = null;
            
            if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
				DesiredCapabilities capabilities = new DesiredCapabilities();

				// os
				if (os.equalsIgnoreCase("windows")) {
					capabilities.setPlatform(Platform.WIN11);
				} else if (os.equalsIgnoreCase("mac")) {
					capabilities.setPlatform(Platform.MAC);
				} else {
					System.out.println("No matching os");
					return;
				}

				String gridURL = "http://localhost:4444/wd/hub"; // Update if needed
				
				
			


				switch (br.toLowerCase()) {
				case "chrome":
					ChromeOptions chromeOptions = new ChromeOptions();
					localDriver = new RemoteWebDriver(URI.create(gridURL).toURL(), chromeOptions.merge(capabilities));
					break;

				case "firefox":
					FirefoxOptions firefoxOptions = new FirefoxOptions();
					localDriver = new RemoteWebDriver(URI.create(gridURL).toURL(), firefoxOptions.merge(capabilities));
					break;

				case "edge":
					EdgeOptions edgeOptions = new EdgeOptions();
					localDriver = new RemoteWebDriver(URI.create(gridURL).toURL(), edgeOptions.merge(capabilities));
					break;

				default:
					logger.error("No matching browser found: {}", br);
					return;
				}

			}

        	if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
            // Browser selection
            logger.debug("Initializing browser: " + br);
            switch (br.toLowerCase()) {
                case "chrome":
                    localDriver = new ChromeDriver();
                    break;
                case "edge":
                    localDriver = new EdgeDriver();
                    break;
                case "firefox":
                    localDriver = new FirefoxDriver();
                    break;
                default:
                    logger.error("No matching browser found. Exiting...");
                    return;
            }}

            driver.set(localDriver);
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
            getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            getDriver().get(p.getProperty("appURL"));
            getDriver().manage().window().maximize();
            logger.info("Application opened successfully in " + br + " browser.");
        } catch (IOException e) {
            logger.error("Failed to load properties file: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error while opening application: " + e.getMessage());
            throw e;
        }
    }

    @AfterClass(groups = {"smoke", "regression", "datadriven"})
    public void closeApp() {
        try {
            if (getDriver() != null) {
                getDriver().quit();
                driver.remove();
                logger.info("Application closed successfully.");
            }
        } catch (Exception e) {
            logger.error("Error while closing the application: " + e.getMessage());
        }
    }

    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        try {
            logger.debug("Capturing screenshot for test: " + tname);
            File sourceFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            logger.info("Screenshot captured and saved to: " + targetFilePath);
        } catch (IOException e) {
            logger.error("Failed to capture screenshot: " + e.getMessage());
            throw e;
        }

        return targetFilePath;
    }
}

