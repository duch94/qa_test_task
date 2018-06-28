public class Mocks {
    public class Auth {
        public static final String url = "https://kabinet.dreamkas.ru/";
        public static final String loginPageCssSel = "body > div > div.lg-page__header > div > a.lg-page__enter > span";
        public static final String loginFieldCssSel = "#input_0";
        public static final String passwordFieldCssSel = "#input_1";
        public static final String login = "test_qa@gmail.com";
        public static final String password = "test_qa";
        public static final String loginButtonCssSel = "#lk-page > div > div.lk-page__dialog > div > div.lk-dialog__content > div > form > div.lk-dialog-form__buttons > button";
        public static final String userEmailInHeaderCssSel = "#lk-page > div > div.lk-app__header.md-whiteframe-4dp > md-menu > button > div.lk-app__header-menu-label.ng-binding.ng-scope";
    }

    public class Items {
        public static final String itemsLinkCssSel = "#lk-page > div > div.lk-app__body > md-sidenav > div.lk-sidenav__menu > a:nth-child(3) > div > span";
        public static final String addItemCssSel = "#tour_products_fab > md-fab-speed-dial > md-fab-trigger > button";
        public static final String itemNameFieldCssSel = "#k-product-entry__name";
        public static final String itemName = "DivineRapier";
        public static final String itemTypeFieldCssSel = "#k-product-entry__type-toggle";
        public static final String itemTypePieceCssSel = "#k-product-entry__type-menu-options > li:nth-child(1) > div > div.md-tile-content > div";
        public static final String itemPriceFieldCssSel = "#k-product-entry__price";
        public static final String itemPrice = "6000";
        public static final String itemQuantityFieldCssSel = "#k-product-entry__quantity";
        public static final String itemQuantity = "1";
        public static final String itemNdsFieldCssSel = "#k-product-entry__tax-toggle";
        public static final String itemNdsValueCssSel = "#k-product-entry__tax-menu-options > li:nth-child(2) > div > div.md-tile-content > div";
        public static final String createItemButtonCssSel = "#lk-page > div > div.lk-app__body > div > div.lk-app__content-body.ng-scope > div > div > react-component > div > form > div.k-product-entry__body.k-theme-cyan-700 > div.k-product-entry__section.k-product-entry__section--no-top-indent > div.k-product-entry__actions > button.md-btn.md-btn--flat.md-btn--text.md-pointer--hover.md-background--primary.md-background--primary-hover.md-inline-block";
        public static final String itemsListCssSel = "#lk-page > div > div.lk-app__body > div > div.lk-app__content-body.ng-scope > div > div:nth-child(3) > react-component > div > div";

        public static final String itemNdsMessageCssSel = "#k-product-entry__tax-menu > div > div.md-text-field-message-container.md-full-width.md-text--error > div";
        public static final String itemNdsMessage = "Обязательно";
    }

    public class Profile {
        // для этого раздела пришлось писать вручную селекторы для:
        // userEmailInHeaderCssSel
        // passwordFieldCssSel
        // userNameFieldCssSel
        public static final String userEmailInHeaderCssSel = Auth.userEmailInHeaderCssSel;
        public static final String userProfileLinkCssSel = "md-menu-item [href=\"#!/profile\"]";
        public static final String userNameFieldCssSel = "div [name=Name]";
        public static final String userName = "New Brewmaster";
        public static final String passwordFieldCssSel = "div [name=ConfirmPassword]";
        public static final String password = Auth.password;
        public static final String saveChangesButtonCssSel = "#lk-page > div > div.lk-app__body > div > div.lk-app__content-body.ng-scope > div > form > div.lk-form__actions > button";
        public static final String infoPopUpCssSel = "body > md-toast > div";
        public static final String infoInPopUp = "Профиль обновлён";
    }

    public class Cashboxes {
        public static final String cashboxesLinkCssSel = "div [href=\"#!/devices\"]";
        public static final String cashboxesListCssSel = "#lk-page > div > div.lk-app__body > div > div.lk-app__content-body.ng-scope > div > div > div";
        public static final String promptToAddCashboxes = "Чтобы начать работу в Кабинете, подключите первую кассу";
    }
}
