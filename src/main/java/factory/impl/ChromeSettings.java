package factory.impl;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class ChromeSettings implements IChromeOptions {

    @Override
    public MutableCapabilities settings() {
        ChromeOptions chromeOptions =new ChromeOptions();
        chromeOptions.addArguments("--start-fullscreen");
        chromeOptions.addArguments("--use-fake-ui-for-media-stream");
        return chromeOptions;
    }
}
