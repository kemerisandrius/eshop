import React, {useEffect, useState} from "react"
import productApi from '../../api/productsApi'
import { NavLink } from 'react-router-dom'
import { Button } from '@material-ui/core'

export default () => {

    const [products, setProducts] = useState([]);

    useEffect(() => {
        productApi.fetchProducts()
            .then(response => setProducts(response.data))
    }, [])

    return (
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                {products.map(product => (
                    <tr key={product.id}>
                        <td>{product.title}</td>
                        <td>{product.price}</td>
                        <td><NavLink to={`/products/${product.id}`} >More</NavLink></td>
                    </tr>
                ))}
            </tbody>
            <Button variant="contained" color="primary">Kurti produktą</Button>
        </table>
    )
}
