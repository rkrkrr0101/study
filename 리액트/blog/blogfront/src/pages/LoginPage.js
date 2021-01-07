import React from 'react';
import AuthForm from '../compo/auth/AuthForm';
import AuthTemplate from '../compo/auth/AuthTemplate';

const LoginPage = () => {
    return (
        <AuthTemplate>
            <AuthForm />
        </AuthTemplate>
    );
};

export default LoginPage;
