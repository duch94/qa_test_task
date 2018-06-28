import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;


public class LkTest {

    @BeforeMethod
    private void prepareBeforeTests() {
        ChromeOptions options = new ChromeOptions();
        ChromeDriver wd = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(wd);
    }

    @AfterMethod
    private void afterTestOperations() {
        WebDriverRunner.closeWebDriver();
    }

    @Test
    public void userLogins() {
        open(Mocks.Auth.url);
        $(Mocks.Auth.loginPageCssSel).click();
        $(Mocks.Auth.loginFieldCssSel).setValue(Mocks.Auth.login);
        $(Mocks.Auth.passwordFieldCssSel).setValue(Mocks.Auth.password);
        $(Mocks.Auth.loginButtonCssSel).click();
        $(Mocks.Auth.userEmailInHeaderCssSel).shouldHave(text(Mocks.Auth.login));
    }

    @Test
    public void createItem() {
        userLogins();
        $(Mocks.Items.itemsLinkCssSel).click();
        $(Mocks.Items.addItemCssSel).click();
        $(Mocks.Items.itemNameFieldCssSel).setValue(Mocks.Items.itemName);
        $(Mocks.Items.itemTypeFieldCssSel).click();
        $(Mocks.Items.itemTypePieceCssSel).click();
        $(Mocks.Items.itemPriceFieldCssSel).setValue(Mocks.Items.itemPrice);
        $(Mocks.Items.itemQuantityFieldCssSel).setValue(Mocks.Items.itemQuantity);
        $(Mocks.Items.itemNdsFieldCssSel).click();
        $(Mocks.Items.itemNdsValueCssSel).click();
        $(Mocks.Items.createItemButtonCssSel).click();
        $(Mocks.Items.itemsListCssSel).shouldHave(text(Mocks.Items.itemName));
    }

    @Test
    public void createItemWithoutNDS() {
        userLogins();
        $(Mocks.Items.itemsLinkCssSel).click();
        $(Mocks.Items.addItemCssSel).click();
        $(Mocks.Items.itemNameFieldCssSel).setValue(Mocks.Items.itemName);
        $(Mocks.Items.itemTypeFieldCssSel).click();
        $(Mocks.Items.itemTypePieceCssSel).click();
        $(Mocks.Items.itemPriceFieldCssSel).setValue(Mocks.Items.itemPrice);
        $(Mocks.Items.itemQuantityFieldCssSel).setValue(Mocks.Items.itemQuantity);
        $(Mocks.Items.createItemButtonCssSel).click();
        $(Mocks.Items.itemNdsMessageCssSel).shouldHave(text(Mocks.Items.itemNdsMessage));
    }

    @Test
    public void changeNameInProfile() {
        userLogins();
        $(Mocks.Profile.userEmailInHeaderCssSel).click();
        $(Mocks.Profile.userProfileLinkCssSel).click();
        $(Mocks.Profile.userNameFieldCssSel).setValue(Mocks.Profile.userName);
        $(Mocks.Profile.passwordFieldCssSel).setValue(Mocks.Profile.password);
        $(Mocks.Profile.saveChangesButtonCssSel).click();
        $(Mocks.Profile.infoPopUpCssSel).shouldHave(text(Mocks.Profile.infoInPopUp));
    }

    @Test
    public void checkForNoCashboxes() {
        userLogins();
        $(Mocks.Cashboxes.cashboxesLinkCssSel).click();
        $(Mocks.Cashboxes.cashboxesListCssSel).shouldHave(text(Mocks.Cashboxes.promptToAddCashboxes));
    }
}
