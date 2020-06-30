import HTTP from '.'

export default {
    fetchProducts() {
        return HTTP.get('/products')
    },
    fetchProductById(id) {
        return HTTP.get(`/products/${id}`);
    },
    createProduct(product, file) {
        let data = new FormData();
        data.append("file", file);
        data.append("title", product.title);
        data.append("description", product.description);
        data.append("price", product.price);
        return HTTP.post('/products/product', data);
    }
}
