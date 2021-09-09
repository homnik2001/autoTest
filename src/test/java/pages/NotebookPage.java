package pages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.google.common.io.Files;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import utils.AllureUtils;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class NotebookPage {

    public NotebookPage(){
        switchTo().window(1);
    }

    @Step("Проверить производителя на соответствие")
    public NotebookPage checkManufacturer(String manufacturer){
        $(byXpath("//li[4]/div/a/span")).shouldHave(text(manufacturer));
        return this;
    }

    @Step("Перейти к характеристикам")
    public NotebookPage goToProperties(){
        $(byXpath("//a[contains(text(), 'Подробные характеристики')] ")).click();
        return this;
    }

    @Step("Сделать скриншот")
    public NotebookPage takeScreenshot(String product) {
        try {
            File file = new File("build/reports/tests/" + product + ".png");
            file.createNewFile();
            AllureUtils.attachScreenshot(Files.toByteArray(
                    new File("build/reports/tests/" + product + ".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Step("Получить диагональ ноутбука")
    public double getDiagonal(){
        return Double.parseDouble(
                $(byXpath("//dl[@id='ekran']/a/dd"))
                        .shouldBe(visible)
                        .text().toString()
                        .split(" ")[0]);
    }

    @Step("Получить вес ноутбука")
    public double getWeight(String name){
        if( $(byXpath("//dl[@id='ves']/a/dd")).isDisplayed()){
            AllureUtils.createStep("Проверить вес ноутбука", Status.PASSED);
            return Double.parseDouble(
                    $(byXpath("//dl[@id='ves']/a/dd"))
                            .shouldBe(visible)
                            .text().toString()
                            .split(" ")[0]);
        }
        else{
            AllureUtils.createStep("Проверить вес ноутбука", Status.BROKEN);
            //Selenide.screenshot(name);
            /*
            try {
               // AllureUtils.attachScreenshot(Files.toByteArray(
                 //       new File("build/reports/tests/" + name + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }

             */
            return 0d;
        }
    }
}
