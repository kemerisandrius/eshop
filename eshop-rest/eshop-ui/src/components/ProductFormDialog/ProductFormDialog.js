import React, {useState} from 'react';
import {
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    Button
} from '@material-ui/core';
import ProductForm from "../../pages/ProductForm/ProductForm";

export default ({open, handleClose}) => {
    return (
        <Dialog open={open} onClose={handleClose}>
            <DialogTitle>Produkto kūrimas</DialogTitle>
            <DialogContent>
                <ProductForm/>
            </DialogContent>
        </Dialog>
    )
}