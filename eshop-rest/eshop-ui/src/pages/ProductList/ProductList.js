import React, {useEffect, useState} from "react"
import productApi from '../../api/productsApi'
import { NavLink } from 'react-router-dom'
import {
    TableBody,
    Table,
    TableHead,
    TableRow,
    TableCell,
    TableContainer,
    Button,
    Paper
} from '@material-ui/core'

export default () => {

    const [products, setProducts] = useState([]);

    useEffect(() => {
        productApi.fetchProducts()
            .then(response => setProducts(response.data))
    }, [])

    return (
        <>
        <TableContainer component={Paper}>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>Title</TableCell>
                        <TableCell>Price</TableCell>
                        <TableCell>Action</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {products.map(product => (
                        <TableRow key={product.title}>
                            <TableCell>{product.title}</TableCell>
                            <TableCell>{product.price}</TableCell>
                            <TableCell>
                                <NavLink to={`products/${product.id}`}>More...</NavLink>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
        <Button variant="contained" color="primary">Kurti produkta</Button>
       </>
    )
}
