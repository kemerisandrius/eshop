import React from "react";
import {Field, Form, Formik} from "formik";
import FormikState from "../../components/FormikState/FormikState";
import {setCredentials} from "../../api";

const initialValues = {
    username: '',
    password: ''
}

const onSubmit = values => {
    setCredentials(values)
    console.log(values)
}

export default () => {
    return (
        <Formik
            initialValues={initialValues}
            onSubmit={onSubmit}>
            {(props) => (
                <Form>
                    <div>
                        <label htmlFor="username">Username:</label>
                        <Field name="username" type="text"/>
                    </div>
                    <div>
                        <label htmlFor="password">Password:</label>
                        <Field name="password" type="password"/>
                    </div>
                    <div>
                        <button type="submit">Login</button>
                    </div>
                    <FormikState {...props}/>
                </Form>
            )}
        </Formik>
    )
}
