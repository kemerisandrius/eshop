import React from "react";
import './Header.css'
import { useTranslation } from "react-i18next";

export default () => {

   const { i18n } = useTranslation()

   const changeLanguage = lang => e => {
       e.preventDefault()
       i18n.changeLanguage(lang)
   }

   return (
        <div className="header-container">

            <div className="site-name">Online Shop</div>

            <div className="header-bar">
                <a href="#">Prisijungti</a>
                <a href="#" onClick={changeLanguage('lt')}>LT</a>
                <a href="#" onClick={changeLanguage('en')}>EN</a>
            </div>
        </div>
   )
}
