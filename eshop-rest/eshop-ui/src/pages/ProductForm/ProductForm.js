import React from 'react';
import productsApi from '../../api/productsApi';
import { Formik } from 'formik';
import './styles.css';


export default () => {
  return (
    <Formik
      initialValues={{title: '', description: '', price: ''}}
      validate={values => {
        const errors = {};

        if (!values.title) {
          errors.title = 'Required!';
        }

        if (!values.description) {
          errors.description = 'Required!'
        }

        if (!values.price) {
          errors.price = 'Required!'
        } else if (isNaN(values.price)) {
          errors.price = 'Must be a number';
        } else if (values.price < 0.01) {
          errors.price = 'Must be bigger than 0.00'
        }

        return errors;
      }}
      onSubmit={values => {
        productsApi.createProduct(values);
      }}
      >
        {({
          values,
          errors,
          touched,
          handleChange,
          handleBlur,
          handleSubmit
        }) => (
          <form onSubmit={handleSubmit}>
            <div>
              <label htmlFor="title">Title:</label>
              <input
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.title}
                type="text"
                name="title"
              ></input>
              { errors.title && touched.title && <div className="error">{errors.title}</div> }
            </div>
            <div>
              <label htmlFor="description">Description:</label>
              <input
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.description}
                type="text"
                name="description"
              ></input>
              { errors.description && touched.description && <div className="error">{ errors.description }</div>}
            </div>
            <div>
              <label htmlFor="price">Price:</label>
              <input
                onChange={handleChange}
                onBlur={handleBlur}
                value={values.price}
                type="text"
                name="price"
              ></input>
              { errors.price && touched.price && <div className="error">{ errors.price }</div>}
            </div>
            <div>
              <input type="submit" value="Create"></input>
            </div>
         </form>
      )}
    </Formik>
  )
}
