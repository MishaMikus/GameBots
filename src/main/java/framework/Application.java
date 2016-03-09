package framework;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Application {
    private static final Logger LOGGER = Logger.getLogger(Application.class);
    public WebDriver driver;
    public String driverPath = new File("driver\\chromedriver.exe").getAbsolutePath();

    public void startChrome(boolean hidden) throws Exception {
        preparation();
        LOGGER.info("startChrome():START");
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", driverPath);
        if (hidden) {
            LOGGER.info("startChrome():START (hidden mode)");
            options.addArguments("--window-size=800x600");
            options.addArguments("--window-position=-1300,0");
        }

        driver = new ChromeDriver(options);
       // driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
       // driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
       // driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        LOGGER.info("startChrome():SUCCESS");
    }

    private void preparation() throws Exception {
        if (!new File(driverPath).exists()) {
            String zipPath = driverPath + ".zip";
            String zipURL = "http://chromedriver.storage.googleapis.com/" + getText("http://chromedriver.storage.googleapis.com/LATEST_RELEASE") + "/chromedriver_win32.zip";
            LOGGER.info("Start load '" + new File(zipPath).getName() + "' from '" + zipURL + "'");
            FileUtils.copyURLToFile(new URL(zipURL), new File(zipPath));
            LOGGER.info("Finish load '" + new File(zipPath).getName() + "' from '" + zipURL + "'");
            LOGGER.info("Start unzipped '" + zipPath + "' to '" + driverPath + "'");
            unZipIt(zipPath, driverPath);
            new File(zipPath).delete();
            LOGGER.info("Finish unzipped '" + zipPath + "' to '" + driverPath + "'");
        }
    }

    public static String getText(String url) throws Exception {

        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);

        in.close();
        String res = response.toString();
        LOGGER.info("getTextFromURL(" + url + ")=" + res);
        return res;
    }

    public void stopChrome() {
        LOGGER.info("stopChrome():START " + stopChromeAndLog());
    }

    private String stopChromeAndLog() {
        Date start = new Date();
        try {
            driver.close();
            driver.quit();
            return successMessage(start);
        } catch (Exception e) {
            return failMessage(start, e);
        }
    }

    public void switchToFrame(String frameID) {
        LOGGER.info("swichTo(" + frameID + "):START " + switchToFrameAndLog(frameID));
    }

    private String switchToFrameAndLog(String frameID) {
        Date start = new Date();
        try {
            WebElement frame=driver.findElement(By.id(frameID));
            if(frame.isDisplayed()){
            driver.switchTo().frame(frame);}
            return successMessage(start);
        } catch (Exception e) {
            return failMessage(start, e);
        }
    }

    public void get(String url) {
        LOGGER.info("get(" + url + "):START" + getAndLog(url));
    }

    private String getAndLog(String url) {
        Date start = new Date();
        try {
            driver.get(url);
            return successMessage(start);
        } catch (Exception e) {
            return failMessage(start, e);
        }
    }

    public void executeJavascript(String script) {
        LOGGER.info("executeJavascript(" + script + "):START" + executeAndLog(script) + " END");
    }

    private String executeAndLog(String script) {
        try {
            Object res = ((JavascriptExecutor) driver).executeScript(script);
            return res == null ? "" : (String) res;
        } catch (Exception e) {
            return e.getMessage().replaceAll("\\n", "");
        }
    }

    private String failMessage(Date start, Exception e) {
        return " - " + (new Date().getTime() - start.getTime()) + " ms - FAIL - Exception " + e.getLocalizedMessage().replaceAll("\\n", "");
    }

    private String successMessage(Date start) {
        return " - " + (new Date().getTime() - start.getTime()) + " ms - SUCCESS";
    }

    public void unZipIt(String zipFile, String outputFile) {
        byte[] buffer = new byte[1024];
        try {
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                File newFile = new File(outputFile);
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
