package factory.impl;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeSettings implements IChromeOptions {

    @Override
    public MutableCapabilities settings() {
        ChromeOptions chromeOptions =new ChromeOptions();
        chromeOptions.addArguments("--start-fullscreen");
        return chromeOptions;
    }
}
