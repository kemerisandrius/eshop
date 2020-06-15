import React, { useState } from 'react';
import productsApi from '../../api/productsApi';

export default () => {
  const [product, setProduct] = useState({});
  const [errors, setErrors] = useState({});

  const handleSubmit = event => {
    event.preventDefault();
    validateProduct(product);
    if (!errors.title && !errors.description && !errors.price) {
      productsApi.createProduct(product);
    }
  }

  const validateProduct = (product) => {
      let errors = {}
      if (!product.price || isNaN(product.price) || product.price <= 0.01) {
        errors = {...errors,  price: 'Price must be a number bigger than 0.01'}
      }
      if (!product.description || product.description === '' || product.description === null) {
        errors = {...errors,  description: 'Description should not be empty'}
      }
      if (!product.title || product.title === '' || product.title === null) {
        errors = {...errors,  title: 'Title should not be empty'}
      }
      setErrors({...errors})
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
        { errors.title && <div>{errors.title}</div> }
      </div>
      <div>
        <label htmlFor="description">Description:</label>
        <input onChange={handleInputChange} type="text" name="description"></input>
        { errors.description && <div>{ errors.description }</div>}
      </div>
      <div>
        <label htmlFor="price">Price:</label>
        <input onChange={handleInputChange} type="text" name="price"></input>
        { errors.price && <div>{ errors.price }</div>}
      </div>
      <div>
        <input type="submit" value="Create"></input>
      </div>
    </form>
  )
}
