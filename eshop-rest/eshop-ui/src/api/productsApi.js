import axios from 'axios'

export default {
    fetchProducts() {
        return axios.get('http://localhost:8080/products')
    },
    createProduct(product) {
        return axios.post('http://localhost:8080/products/product', product);
    }
}
