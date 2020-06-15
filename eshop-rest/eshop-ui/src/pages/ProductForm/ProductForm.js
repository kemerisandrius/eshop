import React from 'react';
import productsApi from '../../api/productsApi';
import { Formik, Form, Field, ErrorMessage } from 'formik';
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
        {() => (
          <Form>
            <div>
              <label htmlFor="title">Title:</label>
              <Field name="title" type="text" />
              <ErrorMessage className="error" name="title" component="div" />
            </div>
            <div>
              <label htmlFor="description">Description:</label>
              <Field name="description" type="text" />
              <ErrorMessage className="error" name="description" component="div" />
            </div>
            <div>
              <label htmlFor="price">Price:</label>
              <Field name="price" type="text" />
              <ErrorMessage className="error" name="price" component="div" />
            </div>
            <div>
              <input type="submit" value="Create"></input>
            </div>
         </Form>
      )}
    </Formik>
  )
}
