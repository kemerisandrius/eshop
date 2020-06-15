import React, { useState } from 'react';
import productsApi from '../../api/productsApi';

export default () => {
  const [product, setProduct] = useState();

  const handleSubmit = event => {
    event.preventDefault();
    productsApi.createProduct(product);
  }

  const handleInputChange = (event) => {
    const value = event.target.value;
    const name = event.target.name;
    setProduct({ ...product, [name]: value })
  }

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="title">Title:</label>
        <input onChange={handleInputChange} type="text" name="title"></input>
      </div>
      <div>
        <label htmlFor="description">Description:</label>
        <input onChange={handleInputChange} type="text" name="description"></input>
      </div>
      <div>
        <label htmlFor="price">Price:</label>
        <input onChange={handleInputChange} type="text" name="price"></input>
      </div>
      <div>
        <input type="submit" value="Create"></input>
      </div>
    </form>
  )
}
