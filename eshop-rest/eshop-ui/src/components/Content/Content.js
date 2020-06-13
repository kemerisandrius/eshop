import React from "react";
import {Redirect, Route, Switch} from "react-router-dom";
import ProductList from "../../pages/ProductList/ProductList";

export default () => (
    <Switch>
        <Redirect exact from="/" to="/products" />

        <Route path="/products">
            <ProductList />
        </Route>
        <Route path="/orders">
            <h1>Orders</h1>
        </Route>
        <Route path="/cart">
            <h1>My Cart</h1>
        </Route>
        <Route>
            <h1>Puslapis nerastas!</h1>
        </Route>
    </Switch>
)
