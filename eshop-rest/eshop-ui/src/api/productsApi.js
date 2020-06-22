import axios from 'axios'

export default {
    fetchProducts() {
        return axios.get('http://localhost:8080/products')
    },
    fetchProductById(id) {
        return axios.get(`http://localhost:8080/products/${id}`);
    },
    createProduct(product, file) {
        let data = new FormData();
        data.append("file", file);
        data.append("title", product.title);
        data.append("description", product.description);
        data.append("price", product.price);
        return axios.post('http://localhost:8080/products/product', data);
    }
}
