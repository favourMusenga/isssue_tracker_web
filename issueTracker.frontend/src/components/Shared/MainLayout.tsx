import { Link } from '@chakra-ui/react';
import React from 'react';
import { Outlet, Link as RouterLink } from 'react-router-dom';
import { Header } from './Header';
import Sidebar from './SideBar';

const MainLayout: React.FC = () => {
	return (
		<>
			<Sidebar>
				<Outlet />
			</Sidebar>
		</>
	);
};

export default MainLayout;
