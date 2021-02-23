package TestYandex;

import TestCases.Yandex.YandexMarketPage;
import Web.UI.InitialDriver.InitialDriver;
import Web.UI.SeleniumElement.Element;
import Web.UI.SeleniumElement.Windows;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestYandexMarketCopy extends InitialDriver{

    private YandexMarketPage yandexMarketPage;
    private Windows windows;
    Element element;

    @BeforeClass(alwaysRun = true)
    public void initPage() {
        yandexMarketPage = new YandexMarketPage();
        windows = new Windows();
        element = new Element();
        windows.navigate(YandexMarketPage.MARKET_YANDEX_BY);
    }

    @Test(priority = 1)
    public void checkSearchField1() {
        yandexMarketPage.searchProduct(YandexMarketPage.TV);
        yandexMarketPage.closeSwimForm();
        yandexMarketPage.checkCardForm();
        Assert.assertTrue(yandexMarketPage.checkOpenPage(YandexMarketPage.TV));
    }

    @Test(priority = 2)
    public void selectSpecificProduct1() {
        yandexMarketPage.selectBBK();
        Assert.assertTrue(yandexMarketPage.checkOpenProducts("Телевизор BBK"));
    }

    @Test(priority = 3)
    public void checkPhoneAfterCatalog1() {
        yandexMarketPage.goToCatalog();
        yandexMarketPage.checkProductInCatalog("Электроника", "Смартфоны");
        Assert.assertTrue(yandexMarketPage.checkOpenPage("Смартфоны"));
    }

    @Test(priority = 4)
    public void checkMyComments1() {
        yandexMarketPage.checkButtonComments("smartfon");
        Assert.assertTrue(element.isVisibility(YandexMarketPage.myReviewPage));
    }

    @Test(priority = 5)
    public void addToFavorites1() {
        windows.leaveOneTab();
        yandexMarketPage.addToFavorites("smartfon");
        Assert.assertTrue(element.isVisibility(YandexMarketPage.titleFavorites));
    }

    @Test(priority = 6)
    public void deleteFromFavorites1() {
        yandexMarketPage.deleteFromFavorites();
        Assert.assertTrue(element.isVisibility(YandexMarketPage.productDeleteFromBasket));
    }

    @AfterClass()
    public void quit() {
        destroy();
    }

}
