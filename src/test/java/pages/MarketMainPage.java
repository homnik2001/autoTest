package pages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Util;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class MarketMainPage {



    public MarketMainPage(){
        open("https://market.yandex.ru");
    }

    /**
     * changes the region based on the entered characters
     * @param  input  characters of a new region
     */
    @Step("Изменить регион")
    public MarketMainPage changeRegion(String input){
        $(byXpath("//*[@class='_1KpjX _3UND8']"))
                .shouldBe(Condition.visible).click(); //clicl region selector
        //input 'Сан' text
        $(byXpath("//*[@placeholder='Укажите другой регион']"))
                .shouldBe(Condition.visible).setValue(input);
        //click first option
        $(byXpath("//span[1]/b['Сан']"))
                .shouldBe(Condition.visible).click();
        $(byXpath("//*[@class='zsSJk _16jAB _36y1j LS3-2'] "))
                .shouldBe(Condition.visible).click();
        return this;
    }


    @Step("Перейти в раздел ноутбуков")
    public MarketMainPage toNotebooks(){
        $(byXpath("//*[@class='_2ZRlR']"))
                .shouldBe(Condition.visible).click();
        $(byXpath("//span[text()='Компьютеры' and @class = '_1UCDW']"))
                .shouldBe(Condition.visible).click();
        $(byXpath("//*[@class='_3n_nb _2CF5q _2a56F cia-vs cia-cs']"))
                .shouldBe(Condition.visible).click();
        return this;
    }

    /**
     * select notebooks maker
     * @param notebook mark of the noteBook for search
     */
    @Step("Выбрать производителя и установить границы цен")
    public MarketMainPage selectNotebook(String notebook, int min, int max){
        //click to chowAll
        $(byXpath("//button[text()='Показать всё' and @class ='_1KpjX _2Wg9r']"))
                .shouldBe(Condition.visible).click();
        Util.waiFor_(2);
        //input notebook manufacturer
        $(byXpath("//input[ @name='Поле поиска']"))
                .shouldBe(Condition.visible).setValue(notebook);
        Util.waiFor_(2);
        //select first manufacturer from dropped list
        $(byXpath("//span[@class='_1o8_r _17C4L' and contains(text()," + notebook + ")]"))
                .shouldBe(Condition.visible).click();
        selectPriceBorders(min, max);
        return this;
    }
    @Step("Установить границы цен")
    private MarketMainPage selectPriceBorders(int min, int max){
        $(byXpath("//input[@name='Цена от']")).setValue( String.valueOf(min));
        $(byXpath("//input[@name='Цена до']")).setValue( String.valueOf(max));
        $(byXpath("//button[text() = 'по рейтингу']")).click();
        return this;
    }

    @Step("Открыть страницу ноутбуков")
    public NotebookPage openNotebookPage(){
        Util.waiFor_(3);
        $$(byXpath("//span[@data-tid='ce80a508']")).get(2).click();
        return new NotebookPage();
    }
}
