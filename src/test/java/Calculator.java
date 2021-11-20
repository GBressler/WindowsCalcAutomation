import io.appium.java_client.windows.WindowsDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class Calculator {

    private WindowsDriver calcsession = null;
    private WebElement calcresult = null;

    @BeforeClass
    public void setup() {
        System.out.println("setup");
        //Let's us say which app we want to run &
        // determine how we want to setup our session
        //type "cd.." twice to get to C directory, enter Program Files > Win App Driver
        // & type "WinAppDriver.exe"
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
        try {
            calcsession = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

//    @AfterClass
//    public void teardown() {
//        System.out.println("teardown");
//        calcsession.quit();
//    }

    @BeforeMethod
    public void clear() {
        System.out.println("CLEAR");
        calcsession.findElementByName("Clear").click();
    }

    @Test
    public void addition() {
        System.out.println("Addition");
        calcsession.findElementByName("One").click();
        calcsession.findElementByName("Two").click();
        calcsession.findElementByName("Plus").click();
        calcsession.findElementByName("Nine").click();
        calcsession.findElementByName("Equals").click();
        System.out.println(GetDisplayResult());
        Assert.assertEquals(GetDisplayResult(), "21");
    }

    @Test
    public void subtraction() {
        System.out.println("Subtraction");
        calcsession.findElementByName("Nine").click();
        calcsession.findElementByName("Three").click();
        calcsession.findElementByName("Minus").click();
        calcsession.findElementByName("Three").click();
        calcsession.findElementByName("Equals").click();
        System.out.println(GetDisplayResult());
        Assert.assertEquals(GetDisplayResult(), "90");
    }

    //
    @Test
    public void division() {
        System.out.println("Division");
        calcsession.findElementByName("Three").click();
        calcsession.findElementByName("Three").click();
        calcsession.findElementByName("Divide by").click();
        calcsession.findElementByName("Three").click();
        calcsession.findElementByName("Equals").click();
        System.out.println(GetDisplayResult());
        Assert.assertEquals(GetDisplayResult(), "11");
    }

    //
    @Test
    public void multiplication() {
        System.out.println("multiplication");
        calcsession.findElementByName("Three").click();
        calcsession.findElementByName("Three").click();
        calcsession.findElementByName("Multiply by").click();
        calcsession.findElementByName("Three").click();
        calcsession.findElementByName("Equals").click();
        System.out.println(GetDisplayResult());
        Assert.assertEquals(GetDisplayResult(), "99");
    }

    public String GetDisplayResult() {
        calcresult = calcsession.findElementByAccessibilityId("CalculatorResults");
        return calcresult.getText().replace("Display is", "").trim();
    }

    public void ChooseCalculator(String locator) throws InterruptedException {
        System.out.println(locator);
        calcsession.findElementByAccessibilityId("TogglePaneButton").click();
        Thread.sleep(2000);
//        List <WebElement> listofelements = calcsession.findElementsByClassName("ListViewItem");
        List <WebElement> listofelements = calcsession.findElementsByClassName("Microsoft.UI.Xaml.Controls.NavigationViewItem");
        System.out.println(listofelements.size());
        for (int i = 0; i < listofelements.size(); i++) {
            if (listofelements.get(i).getAttribute("Name").equals(locator)){
                listofelements.get(i).click();
                break;
            }
        }
    }

    public void ChooseCalculatorXPATH(String locator) throws InterruptedException {
        System.out.println(locator);
        calcsession.findElementByAccessibilityId("TogglePaneButton").click();
        //Thread.sleep(2000);
        List <WebElement> listofelements = calcsession.findElementsByXPath("//ListItem");
        System.out.println(listofelements.size());
        for (int i = 0; i < listofelements.size(); i++) {
            if (listofelements.get(i).getAttribute("Name").equals(locator)){
                listofelements.get(i).click();
                break;
            }
        }
    }

    public void ChooseCalculatorXPATHSimple(String locator) throws InterruptedException {
        System.out.println(locator);
        calcsession.findElementByAccessibilityId("TogglePaneButton").click();
        //Thread.sleep(2000);
        calcsession.findElementByXPath("//ListItem[contains(@Name,\"" + locator + "\")]").click();
        ///Pane[contains(@Name,\"Escritorio 1\")][@ClassName=\"#32769\"]
        //System.out.println(listofelements.size());
        System.out.println("simple");

    }

    @Test
    public void SelectAnotherCalculatorXPATHSimple() throws InterruptedException {
        System.out.println("Selecting Another Calculator");
        ChooseCalculatorXPATHSimple("Programmer Calculator");
    }

    @Test
    public void SelectAnotherCalculatorXPATH() throws InterruptedException {
        System.out.println("Selecting Another Calculator");
        ChooseCalculatorXPATH("Scientific Calculator");
    }

    @Test
    public void SelectAnotherCalculator() throws InterruptedException {
        System.out.println("Selecting Another Calculator");
        ChooseCalculator("Scientific Calculator");
    }
}
