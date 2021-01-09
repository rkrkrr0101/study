import React from 'react';
import AuthForm from '../compo/auth/AuthForm';
import AuthTemplate from '../compo/auth/AuthTemplate';

const RegisterPage = () => {
    return (
        <AuthTemplate>
            <AuthForm type="register" />
        </AuthTemplate>
    );
};

export default RegisterPage;
