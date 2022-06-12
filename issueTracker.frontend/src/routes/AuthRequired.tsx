import React, { useContext } from 'react';
import { Navigate, Outlet, useLocation } from 'react-router-dom';
import { AppContext } from '../context/AppContextProvider';

const AuthRequired: React.FC<{ children?: JSX.Element }> = ({ children }) => {
	const location = useLocation();
	const { appState } = useContext(AppContext);
	const { isAuth } = appState;

	if (!isAuth) {
		return <Navigate to="/login" state={{ from: location }} replace />;
	}

	return <Outlet />;
};

export default AuthRequired;
