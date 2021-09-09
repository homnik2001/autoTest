import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import pages.MarketMainPage;
import pages.NotebookPage;
import utils.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import  org.openqa.selenium.By;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class MainTest {
    MarketMainPage marketMainPage;
    ArrayList<NotebookResult> notebookResults;
    @BeforeClass
    void setUP(){
        Configuration.browser = "chrome";
        notebookResults = new ArrayList<>();
        marketMainPage = new MarketMainPage();
        Util.waiFor_(7);
        marketMainPage.changeRegion("Сан")
                .toNotebooks();
    }

    @Test(dataProvider = "data-provider", dataProviderClass = DataProvider.class)
    public void start(UtilNoteBook notebook, int max){

        max = (max <= notebook.getMax()) ? max : notebook.getMax();
        marketMainPage.selectNotebook(notebook.getName(),
                notebook.getMin(), max);
        NotebookPage notebookPage = marketMainPage
                .openNotebookPage()
                .checkManufacturer(notebook.getName())
                .goToProperties();
                //.takeScreenshot(notebook.getName());


        notebookResults.add(new NotebookResult(
                notebook.getName(),
                notebookPage.getDiagonal(),
                notebookPage.getWeight(notebook.getName())
        ));

//a[@id ='logoPartMarket']/span[contains(text(),'Маркет']
    }
    @AfterMethod
    public void shutdownMethods(){
        closeWindow();
        switchTo().window(0);
        Util.waiFor_(2);
        marketMainPage.toNotebooks();

    }
    @AfterClass
    void afterClass(){
        Util.waiFor_(10);
        closeWebDriver();
    }

}
