import React, { useState } from 'react';
import productsApi from '../../api/productsApi';
import {Formik, Form, Field} from 'formik';
import './styles.css';
import * as Yup from 'yup';
import ErrorMessageTranslated from "../../components/ErrorMessageTranslated/ErrorMessageTranslated";

const initialState = {
    title: '',
    description: '',
    price: ''
}

export default () => {

    const [file, setFile] = useState({});

    const handleFileChange = (e) => {
        setFile(e.target.files[0]);
    }

    const validationSchema = Yup.object().shape({
        title: Yup.string()
            .label("common:title")
            .required(),
        description: Yup.string()
            .label("common:description")
            .required(),
        price: Yup.number()
            .label("common:price")
            .typeError()
            .min(0.01)
            .required()
    })

    return (
        <Formik
            initialValues={initialState}
            validationSchema={validationSchema}
            onSubmit={values => {
                productsApi.createProduct(values, file);
            }}
        >
            {() => (
                    <Form>
                        <div>
                            <label htmlFor="title">Title:</label>
                            <Field name="title" type="text"/>
                            <ErrorMessageTranslated className="error" name="title"/>
                        </div>
                        <div>
                            <label htmlFor="description">Description:</label>
                            <Field name="description" type="text"/>
                            <ErrorMessageTranslated className="error" name="description"/>
                        </div>
                        <div>
                            <label htmlFor="price">Price:</label>
                            <Field name="price" type="text"/>
                            <ErrorMessageTranslated className="error" name="price"/>
                        </div>
                        <div>
                            <label htmlFor="file">File:</label>
                            <Field name="files" type="file" onChange={handleFileChange}/>
                        </div>
                        <div>
                            <input type="submit" value="Create"></input>
                        </div>
                    </Form>
                )
            }
        </Formik>
    )
}
