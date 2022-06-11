import React, { useEffect, useState } from 'react';
import { Navigate, Outlet, useLocation } from 'react-router-dom';

const AuthRequired: React.FC<{ children?: JSX.Element }> = ({ children }) => {
	const location = useLocation();
	const [isAuth, setIsAuth] = useState(true);

	useEffect(() => {
		setTimeout(() => {
			setIsAuth((prev) => !prev);
		}, 10000);
	}, []);
	if (!isAuth) {
		return <Navigate to="/login" state={{ from: location }} replace />;
	}

	return <Outlet />;
};

export default AuthRequired;
