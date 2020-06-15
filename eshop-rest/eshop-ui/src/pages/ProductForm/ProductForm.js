import React from 'react';
import productsApi from '../../api/productsApi';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import './styles.css';
import * as Yup from 'yup';


export default () => {
  return (
    <Formik
      initialValues={{title: '', description: '', price: ''}}
      validationSchema={Yup.object().shape({
        title: Yup.string()
          .required('Title is required'),
        description: Yup.string()
          .required('Description is required'),
        price: Yup.number()
          .typeError('Must be a number')
          .min(0.01, 'Price must be bigger than 0.01')
          .required('Price is required')
      })}
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
