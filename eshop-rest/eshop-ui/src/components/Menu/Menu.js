import React from "react";
import './Menu.css'
import { NavLink } from "react-router-dom";

export default () => (
    <div className="menu-container">
        <NavLink to="/products">Product List</NavLink>
        |
        <NavLink to="/orders">Orders</NavLink>
        |
        <NavLink to="/cart">My Cart</NavLink>
    </div>
)
