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
    Paper,
    TablePagination,
    CircularProgress
} from '@material-ui/core'

import './styles.css';

export default () => {

    const [productsPage, setProductsPage] = useState({ content: [] });
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(25);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        productApi.fetchProducts(page, rowsPerPage)
        .then(response => setProductsPage(response.data))
        .finally(() => setIsLoading(false));
    }, [page, rowsPerPage])

    const handleChangeRowsPerPage = (e) => {
        setRowsPerPage(e.target.value);
    }

    const handleChangePage = (e, newPage) => {
        setPage(newPage);
    }

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
                    {isLoading ? 
                    <TableRow>
                        <TableCell colSpan="3">
                            <CircularProgress class="loader"/>
                            </TableCell>
                    </TableRow> : 
                    productsPage.content.map(product => (
                        <TableRow key={product.title}>
                            <TableCell>{product.title}</TableCell>
                            <TableCell>{product.price}</TableCell>
                            <TableCell>
                                <NavLink to={`products/${product.id}`}>More...</NavLink>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
                <TablePagination
                    rowsPerPageOptions={[25, 50, 100]}
                    rowsPerPage={rowsPerPage}
                    count={productsPage.totalElements}
                    page={page}
                    onChangeRowsPerPage={handleChangeRowsPerPage}
                    onChangePage={handleChangePage}
                />
            </Table>
        </TableContainer>
        <Button variant="contained" color="primary">Kurti produkta</Button>
       </>
    )
}
