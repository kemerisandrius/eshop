import React from "react";
import './Menu.css'
import { NavLink } from "react-router-dom";

export default () => (
    <div className="menu-container">
        <NavLink to="/products" activeClassName="selected">Product List</NavLink>
        |
        <NavLink to="/orders" activeClassName="selected">Orders</NavLink>
        |
        <NavLink to="/cart" activeClassName="selected">My Cart</NavLink>
    </div>
)
