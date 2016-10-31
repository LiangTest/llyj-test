package com.wirelessqa.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Describable;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

/**
 * Created by LIANGLIANG on 2016/10/28.
 * USE MY FIRST CASE
 */



public class appium {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        File classpathroot = new File(System.getProperty("user.idr"));
        File appDir = new File(classpathroot, "apk");
        File app = new File(appDir,"xiaomimusic.apk");
        System.out.print(app.getAbsolutePath());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(CapabilityType.VERSION, "4.4");
        capabilities.setCapability("app", app.getAbsoluteFile());
        capabilities.setCapability("app-package", "fm.xiaomi.main");
        capabilities.setCapability("app-activity", "fm.xiaomi.bmamba.activity.StartActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test
    public void login() throws Exception {
        //判断某个元素是否显示
        if (driver.findElement(By.name("是否创建桌面快捷方式")).isDisplayed()) {
            driver.findElement(By.name("确定")).click();
        }

        driver.findElement(By.name("我的音乐")).click();
        driver.findElementByName("点击头像登录").click();
        driver.findElementByName("虾米账户登录").click();
        //输入字符
        driver.findElementByName("输入邮箱地址").sendKeys("*********");

        //通过id查找
        driver.findElement(By.id("fm.xiaomi.main:id/edit_password")).sendKeys("******");
        driver.findElementById("fm.xiaomi.main:id/btn_login").click();
        String userName = driver.findElementById("fm.xiaomi.main:id/user_name").getText();

        assertEquals(userName,"laobi");
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
