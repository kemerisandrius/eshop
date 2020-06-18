import React from "react";
import './Menu.css'
import {NavLink} from "react-router-dom";
import {useTranslation} from "react-i18next";

export default () => {

    const { t } = useTranslation("menu")

    return (
        <div className="menu-container">
            <NavLink to="/products" activeClassName="selected">{t("products")}</NavLink>
            |
            <NavLink to="/orders" activeClassName="selected">{t("orders")}</NavLink>
            |
            <NavLink to="/cart" activeClassName="selected">{t("cart")}</NavLink>
        </div>
    )
}
