import React from 'react';
import productsApi from '../../api/productsApi';
import {Formik, Form, Field, ErrorMessage} from 'formik';
import './styles.css';
import * as Yup from 'yup';
import {useTranslation} from "react-i18next";

const initialState = {
    title: '',
    description: '',
    price: ''
}



export default () => {

    const validationSchema = Yup.object().shape({
        title: Yup.string()
            .required(),
        description: Yup.string()
            .required(),
        price: Yup.number()
            .typeError()
            .min(0.01)
            .required()
    })

    return (
        <Formik
            initialValues={initialState}
            validationSchema={validationSchema}
            onSubmit={values => {
                productsApi.createProduct(values);
            }}
        >
            {() => {
                return (
                    <Form>
                        <div>
                            <label htmlFor="title">Title:</label>
                            <Field name="title" type="text"/>
                            <ErrorMessage className="error" name="title" component="div"/>
                        </div>
                        <div>
                            <label htmlFor="description">Description:</label>
                            <Field name="description" type="text"/>
                            <ErrorMessage className="error" name="description" component="div"/>
                        </div>
                        <div>
                            <label htmlFor="price">Price:</label>
                            <Field name="price" type="text"/>
                            <ErrorMessage className="error" name="price" component="div"/>
                        </div>
                        <div>
                            <input type="submit" value="Create"></input>
                        </div>
                    </Form>
                )
            }
        }
        </Formik>
    )
}
