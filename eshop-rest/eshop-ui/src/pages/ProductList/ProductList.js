import React, {useEffect, useState} from "react"
import productApi from '../../api/productsApi'

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
                        <td>More</td>
                    </tr>
                ))}
            </tbody>
        </table>
    )
}
