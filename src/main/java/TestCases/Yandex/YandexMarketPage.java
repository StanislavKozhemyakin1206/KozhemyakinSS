package TestCases.Yandex;

import Web.UI.SeleniumElement.Element;
import Web.UI.SeleniumElement.Fields;
import Web.UI.SeleniumElement.Mouse;
import Web.UI.SeleniumElement.Windows;
import org.openqa.selenium.By;

public class YandexMarketPage {

    public static final String MARKET_YANDEX_BY = "https://market.yandex.by/";
    public static final By headerSearch = By.id("header-search");
    public static final By findButton = By.xpath("//button[@type='submit']");
    public static final String TV = "Телевизоры";
    public static final By buttonUnderstand = By.xpath("//span[contains(text(), 'Понятно')]");
    public static final By myReviewPage = By.xpath("//a[contains(text(), 'Мой отзыв о')]");
    public static final By buttonReview = By.xpath("//span[contains(text(), 'Оставить отзыв')]");
    public static final By tabReview = By.xpath("//span[contains(text(), 'Отзывы')]");
    public static final By titleFavorites = By.xpath("//h1[contains(text(), 'Избранное')]");
    public static final By productDeleteFromBasket = By.xpath("//div[contains(text(), 'Товар удалён из избранного')]");
    public static final By deleteBtn = By.xpath("//span[contains(text(), 'Удалить')]");
    public static final By buttonGoToFavorites = By.xpath("//span[contains(text(), 'Перейти в избранное')]");
    Element element = new Element();
    Fields fields = new Fields();
    Mouse mouse = new Mouse();
    Windows windows = new Windows();

    public void searchProduct(String nameProduct) {
        element.isVisibility(headerSearch);
        fields.input(headerSearch, nameProduct);
        element.waitAndClick(findButton);
    }

    public boolean checkOpenPage(String nameProduct) {
        return element.isVisibility(By.xpath("//div[@data-apiary-widget-id='/content/headline']//h1[contains(text(), '" + nameProduct + "')]"));
    }

    public boolean checkOpenProducts(String nameProduct) {
        return element.isVisibility(By.xpath("//a[contains(@title,'" + nameProduct + "')]"));
    }

    public void selectBBK() {
        selectSpecificCharacteristics("BBK");
        selectSpecificCharacteristics("4K UHD");
        selectSpecificCharacteristics("Android");
    }

    public void selectSpecificCharacteristics(String name) {
        mouse.moveMouseTo(By.xpath("//span[text()= '" + name + "']"));
        element.waitAndClick(By.xpath("//span[text()= '" + name + "']"));
    }

    public void closeSwimForm() {
        if (element.isVisibility(buttonUnderstand)) {
            element.waitAndClick(buttonUnderstand);
        }
    }

    public void goToCatalog() {
        element.waitAndClick(By.xpath("//span[contains(text(), 'Каталог')]"));
    }

    public void checkProductInCatalog(String name, String item) {
        mouse.moveMouseTo(By.xpath("//img[@alt= '" + name + "']"));
        element.waitAndClick(By.xpath("//a[text()= '" + item + "']"));
    }

    public void checkButtonComments(String nameProduct) {
        element.waitAndClick(By.xpath("//a[contains(@href, 'product--" + nameProduct + "')]"));
        windows.switchToLastTab();
        element.waitAndClick(tabReview);
        element.waitAndClick(buttonReview);
        windows.switchToLastTab();
    }

    public void addToFavorites(String nameProduct) {
        element.waitAndClick(By.xpath("//a[contains(@href, 'product--" + nameProduct + "')]"));
        windows.switchToLastTab();
        element.waitAndClick(By.xpath("//span[contains(text(), 'В избранное')]/parent::div"));
        element.waitAndClick(buttonGoToFavorites);
    }

    public void deleteFromFavorites() {
        element.waitAndClick(deleteBtn);
        element.isVisibility(productDeleteFromBasket);
    }
}
